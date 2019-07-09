package com.proper.phip.security.base.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.proper.phip.sys.user.entity.SysUserEntity;
import com.proper.phip.sys.user.mapper.SysUserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务
 */
public class JwtUserService implements UserDetailsService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Value("${com.proper.phip.security.login-repeat}")
    private boolean loginRepeat;

    @Value("${com.proper.phip.security.max-validity}")
    private long maxValidity;

    private static final String PC_TOKEN_KEY = "phip.token.pc.";

    public JwtUserService() {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public UserDetails getUserLoginInfo(String username, String userId) {
        // 从缓存中取得密码
        String salt = redisTemplate.opsForValue().get(PC_TOKEN_KEY + username);
        if (salt == null) {
            salt = "";
        }
        return User.builder().username(username).password(salt).authorities(userId, "user").build();
    }

    public String saveUserLoginInfo(UserDetails user) {
        //  生成随机密码，并保存到缓存中
        String salt = "";
        if (loginRepeat) {
            salt = redisTemplate.opsForValue().get(PC_TOKEN_KEY + user.getUsername());
        }
        if (StringUtils.isEmpty(salt)) {
            salt = UUID.randomUUID().toString().replaceAll("-", "");
        }

        redisTemplate.opsForValue().set(PC_TOKEN_KEY + user.getUsername(), salt, maxValidity, TimeUnit.SECONDS);
        // 使用密钥生成token
        Algorithm algorithm = Algorithm.HMAC256(salt);
        Date date = new Date(System.currentTimeMillis() + 10L * 365 * 24 * 3600 * 1000);
        // 取得用户ID
        String userId = "";
        Iterator iterator = user.getAuthorities().iterator();
        if (((Iterator) iterator).hasNext()) {
            userId = iterator.next().toString();
        }
        return JWT.create()
            .withSubject(user.getUsername())
            .withExpiresAt(date)
            .withIssuedAt(new Date())
            .withKeyId(userId)
            .sign(algorithm);
    }

    public void refreshUserLoginInfo(UserDetails user) {
        // 刷新期限
        Long expire = redisTemplate.getExpire(PC_TOKEN_KEY + user.getUsername());
        if (expire != null) {
            if (maxValidity - expire > 300 || expire < 300) {
                String salt = redisTemplate.opsForValue().get(PC_TOKEN_KEY + user.getUsername());
                if (StringUtils.isNotEmpty(salt)) {
                    redisTemplate.opsForValue().set(PC_TOKEN_KEY + user.getUsername(), salt, maxValidity, TimeUnit.SECONDS);
                }
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 取得用户信息，并进行验证
        SysUserEntity searchEntity = new SysUserEntity();
        searchEntity.setUsername(username);
        SysUserEntity sysUserEntity = sysUserMapper.selectOne(searchEntity);
        if (sysUserEntity == null) {
            throw new UsernameNotFoundException("用户名或密码不正确");
        }
        boolean isEnable = true;
        if (StringUtils.isEmpty(sysUserEntity.getStatus()) || "0".equals(sysUserEntity.getStatus())) {
            isEnable = false;
        }
        return new User(username, passwordEncoder.encode(sysUserEntity.getPassword()),
            isEnable, true, true, true,
            AuthorityUtils.commaSeparatedStringToAuthorityList(sysUserEntity.getId() + "," + "user"));
    }

    public void deleteUserLoginInfo(String username) {
        // 清除缓存中的密码
        redisTemplate.delete(PC_TOKEN_KEY + username);
    }
}

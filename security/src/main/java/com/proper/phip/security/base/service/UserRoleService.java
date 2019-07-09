package com.proper.phip.security.base.service;

import com.proper.phip.core.utils.UrlUtils;
import com.proper.phip.sys.menu.entity.SysMenuUrlEntity;
import com.proper.phip.sys.menu.service.SysMenuService;
import com.proper.phip.sys.role.entity.SysRoleEntity;
import com.proper.phip.sys.role.entity.SysRoleMenuEntity;
import com.proper.phip.sys.role.service.SysRoleService;
import com.proper.phip.sys.user.entity.SysUserRoleEntity;
import com.proper.phip.sys.user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${com.proper.phip.security.url}")
    private boolean urlControl;

    /**
     * 判断用户是否有访问url的权限
     * @return 有权限返回true
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object userInfo = authentication.getPrincipal();
        boolean hasPermission  = false;
        if (urlControl) {
            if (userInfo instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) userInfo;
                String url = request.getRequestURI();
                url = url.substring(contextPath.length());
                List<SysRoleEntity> defaultRoles = sysRoleService.getRoles(null, null, "1");
                List<SysUserRoleEntity> userRoles = sysUserService.getAllRoleByUserId(userDetails.getUsername());
                List<SysRoleMenuEntity> menus = new ArrayList<>();
                for (SysRoleEntity sysRoleEntity : defaultRoles) {
                    List<SysRoleMenuEntity> tmpMenus = sysRoleService.getRoleMenus(sysRoleEntity.getId());
                    menus.addAll(tmpMenus);
                }
                for (SysUserRoleEntity sysUserRoleEntity : userRoles) {
                    List<SysRoleMenuEntity> tmpMenus = sysRoleService.getRoleMenus(sysUserRoleEntity.getRoleId());
                    menus.addAll(tmpMenus);
                }
                for (SysRoleMenuEntity menuEntity : menus) {
                    List<SysMenuUrlEntity> urls = sysMenuService.getMenuUrls(menuEntity.getMenuId());
                    for (SysMenuUrlEntity urlEntity : urls) {
                        if (StringUtils.isNotEmpty(urlEntity.getRequestMethod())
                            && urlEntity.getRequestMethod().indexOf(request.getMethod()) < 0) {
                            continue;
                        }
                        if (UrlUtils.isEqualRestfulUrl(url, urlEntity.getUrl())) {
                            hasPermission = true;
                            break;
                        }
                    }
                }
            }
        } else {
            hasPermission  = true;
        }
        return hasPermission;
    }
}

package com.proper.phip.sys.user.service;

import com.github.pagehelper.PageHelper;
import com.proper.phip.core.i18n.I18NService;
import com.proper.phip.core.restful.Pagination;
import com.proper.phip.core.restful.ReturnException;
import com.proper.phip.core.utils.PinyinUtils;
import com.proper.phip.sys.user.entity.SysUserDeptEntity;
import com.proper.phip.sys.user.entity.SysUserEntity;
import com.proper.phip.sys.user.entity.SysUserRoleEntity;
import com.proper.phip.sys.user.mapper.SysUserDeptMapper;
import com.proper.phip.sys.user.mapper.SysUserMapper;
import com.proper.phip.sys.user.mapper.SysUserRoleMapper;
import com.proper.phip.sys.user.vo.SysUserDeptVO;
import com.proper.phip.sys.user.vo.SysUserVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private I18NService i18NService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserDeptMapper sysUserDeptMapper;

    @Override
    public SysUserEntity getUserById(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public SysUserVO getUserInfoById(String userId) {
        SysUserEntity sysUserEntity = this.getUserById(userId);
        List<SysUserRoleEntity> userRoles = this.getRoleByUserId(userId);
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUserEntity, sysUserVO);
        List<String> roleIds = new ArrayList<>();
        if (userRoles.size() > 0) {
            for (SysUserRoleEntity tmpEntity : userRoles) {
                roleIds.add(tmpEntity.getRoleId());
            }
            sysUserVO.setRoleIds(StringUtils.join(roleIds.toArray(), ","));
        }
        List<SysUserDeptVO> depts = this.getDeptByUserId(userId);
        sysUserVO.setDepts(depts);
        return sysUserVO;
    }

    @Override
    public List<SysUserRoleEntity> getAllRoleByUserId(String userId) {
        return sysUserRoleMapper.getAllRoleByUserId(userId);
    }

    @Override
    public List<SysUserRoleEntity> getRoleByUserId(String userId) {
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setUserOrDeptId(userId);
        sysUserRoleEntity.setRoleType("USER");
        return sysUserRoleMapper.select(sysUserRoleEntity);
    }

    @Override
    public List<SysUserDeptVO> getDeptByUserId(String userId) {
        return sysUserDeptMapper.getDeptByUserId(userId);
    }

    @Override
    public List<SysUserEntity> getUsers(Pagination pagination, String username, String name) {
        if (pagination != null) {
            PageHelper.startPage(pagination.getCurrent(), pagination.getPageSize());
        }
        return sysUserMapper.getUsers(username, name);
    }

    @Override
    public void addUser(SysUserVO sysUserVO, String userId) {
        List<SysUserEntity> users = sysUserMapper.getUsers(sysUserVO.getUsername(), null);
        if (users.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.username-repeat"));
        }

        SysUserEntity sysUserEntity = new SysUserEntity();
        BeanUtils.copyProperties(sysUserVO, sysUserEntity);
        Date date = new Date();
        if (StringUtils.isEmpty(sysUserEntity.getNamePy())) {
            sysUserEntity.setNamePy(PinyinUtils.getPinYinHeadChar(sysUserEntity.getName()));
        }
        sysUserEntity.setStatus("1");
        sysUserEntity.initAddInfo(userId, date);
        sysUserMapper.insertSelective(sysUserEntity);
        insertUserRole(sysUserEntity, sysUserVO.getRoleIds(), date, userId);
        insertUserDept(sysUserEntity, sysUserVO.getDepts(), date, userId);
    }

    @Override
    public void editUser(SysUserVO sysUserVO, String userId) {
        Date date = new Date();
        SysUserEntity sysUserEntity = new SysUserEntity();
        BeanUtils.copyProperties(sysUserVO, sysUserEntity);
        if (StringUtils.isEmpty(sysUserEntity.getNamePy())) {
            sysUserEntity.setNamePy(PinyinUtils.getPinYinHeadChar(sysUserEntity.getName()));
        }
        sysUserEntity.initModifyInfo(userId, date);
        sysUserMapper.updateByPrimaryKeySelective(sysUserEntity);
        if (StringUtils.isEmpty(sysUserVO.getEditUserBasic())) {
            sysUserRoleMapper.deleteByUserOrDeptId(sysUserEntity.getId(), "USER");
            sysUserDeptMapper.deleteByUserId(sysUserEntity.getId());
            insertUserRole(sysUserEntity, sysUserVO.getRoleIds(), date, userId);
            insertUserDept(sysUserEntity, sysUserVO.getDepts(), date, userId);
        }
    }

    @Override
    public void editUserPassword(String userId, Map<String, String> passwordInfo) {
        if (!passwordInfo.get("newPassword").equals(passwordInfo.get("confirmPassword"))) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.password-unequal"));
        }
        SysUserEntity sysUserOld = sysUserMapper.selectByPrimaryKey(userId);
        if (!sysUserOld.getPassword().equals(passwordInfo.get("oldPassword"))) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.password-error"));
        }

        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setId(userId);
        sysUserEntity.setPassword(passwordInfo.get("newPassword"));
        sysUserEntity.initModifyInfo(userId, new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUserEntity);
    }

    /**
     * 新建用户权限
     */
    private void insertUserRole(SysUserEntity sysUserEntity, String roleIds, Date date, String userId) {
        if (StringUtils.isNotEmpty(roleIds)) {
            for (String roleId : roleIds.split(",")) {
                SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
                sysUserRoleEntity.setUserOrDeptId(sysUserEntity.getId());
                sysUserRoleEntity.setRoleType("USER");
                sysUserRoleEntity.setRoleId(roleId);
                sysUserRoleEntity.initAddInfo(userId, date);
                sysUserRoleMapper.insertSelective(sysUserRoleEntity);
            }
        }
    }

    /**
     * 新建用户机构
     */
    private void insertUserDept(SysUserEntity sysUserEntity, List<SysUserDeptVO> userDepts, Date date, String userId) {
        if (userDepts != null) {
            for (SysUserDeptVO sysUserDeptVO : userDepts) {
                SysUserDeptEntity sysUserDeptEntity = new SysUserDeptEntity();
                BeanUtils.copyProperties(sysUserDeptVO, sysUserDeptEntity);
                sysUserDeptEntity.setId(null);
                sysUserDeptEntity.setUserId(sysUserEntity.getId());
                sysUserDeptEntity.initAddInfo(userId, date);
                sysUserDeptMapper.insertSelective(sysUserDeptEntity);
            }
        }
    }
}

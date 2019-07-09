package com.proper.phip.sys.role.service;

import com.github.pagehelper.PageHelper;
import com.proper.phip.core.i18n.I18NService;
import com.proper.phip.core.restful.Pagination;
import com.proper.phip.core.restful.ReturnException;
import com.proper.phip.core.utils.PinyinUtils;
import com.proper.phip.sys.role.entity.SysRoleEntity;
import com.proper.phip.sys.role.entity.SysRoleMenuEntity;
import com.proper.phip.sys.role.mapper.SysRoleMapper;
import com.proper.phip.sys.role.mapper.SysRoleMenuMapper;
import com.proper.phip.sys.user.entity.SysUserRoleEntity;
import com.proper.phip.sys.user.mapper.SysUserRoleMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private I18NService i18NService;

    @Autowired
    private SysRoleCacheService sysRoleCacheService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    @Cacheable(value = "phip.role.default", key = "#isDefault",condition = "#isDefault=='1'")
    public List<SysRoleEntity> getRoles(Pagination pagination, String roleName, String isDefault) {
        if (pagination != null) {
            PageHelper.startPage(pagination.getCurrent(), pagination.getPageSize());
        }
        return sysRoleMapper.getRoles(roleName, null, isDefault);
    }

    @Override
    public void addRole(SysRoleEntity sysRoleEntity, String userId) {
        List<SysRoleEntity> roles = sysRoleMapper.getRoles(null, sysRoleEntity.getRoleCode(), null);
        if (roles.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.code-repeat"));
        }

        if (StringUtils.isEmpty(sysRoleEntity.getRoleNamePy())) {
            sysRoleEntity.setRoleNamePy(PinyinUtils.getPinYinHeadChar(sysRoleEntity.getRoleName()));
        }
        sysRoleEntity.initAddInfo(userId, new Date());
        sysRoleMapper.insert(sysRoleEntity);
        sysRoleCacheService.deleteCacheRole("1");
    }

    @Override
    public void editRole(SysRoleEntity sysRoleEntity, String userId) {
        if (StringUtils.isEmpty(sysRoleEntity.getRoleNamePy())) {
            sysRoleEntity.setRoleNamePy(PinyinUtils.getPinYinHeadChar(sysRoleEntity.getRoleName()));
        }
        sysRoleEntity.initModifyInfo(userId, new Date());
        sysRoleMapper.updateByPrimaryKeySelective(sysRoleEntity);
        sysRoleCacheService.deleteCacheRole("1");
    }

    @Override
    public void deleteRole(String id) {
        // 验证是否已使用
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setRoleId(id);
        List<SysUserRoleEntity> userRoles = sysUserRoleMapper.select(sysUserRoleEntity);
        if (userRoles.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.delete.used"));
        }

        sysRoleMapper.deleteByPrimaryKey(id);
        sysRoleMenuMapper.deleteByRoleId(id);
        sysRoleCacheService.deleteCacheRole("1");
    }

    @Override
    @Cacheable(value = "phip.role.menu")
    public List<SysRoleMenuEntity> getRoleMenus(String roleId) {
        SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
        sysRoleMenuEntity.setRoleId(roleId);
        return sysRoleMenuMapper.select(sysRoleMenuEntity);
    }

    @Override
    @CacheEvict(value = "phip.role.menu", key = "#roleId")
    public void editRoleMenus(List<SysRoleMenuEntity> roleMenus, String roleId, String userId) {
        Date date = new Date();

        sysRoleMenuMapper.deleteByRoleId(roleId);

        for (SysRoleMenuEntity tempEntity : roleMenus) {
            tempEntity.initAddInfo(userId, date);
            sysRoleMenuMapper.insertSelective(tempEntity);
        }
    }
}

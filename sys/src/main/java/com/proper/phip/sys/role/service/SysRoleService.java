package com.proper.phip.sys.role.service;

import com.proper.phip.core.restful.Pagination;
import com.proper.phip.sys.role.entity.SysRoleEntity;
import com.proper.phip.sys.role.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 权限Service
 */
public interface SysRoleService {

    /**
     * 取得权限列表
     * @param pagination 分页信息
     * @param roleName 权限名称
     * @param isDefault 是否默认权限
     * @return 权限列表
     */
    List<SysRoleEntity> getRoles(Pagination pagination, String roleName, String isDefault);

    /**
     * 保存权限
     * @param sysRoleEntity 权限实体
     * @param userId 操作人
     */
    void addRole(SysRoleEntity sysRoleEntity, String userId);

    /**
     * 修改权限
     * @param sysRoleEntity 权限实体
     * @param userId 操作人
     */
    void editRole(SysRoleEntity sysRoleEntity, String userId);

    /**
     * 删除权限
     * @param id 主键
     */
    void deleteRole(String id);

    /**
     * 取得权限菜单列表
     * @param roleId 权限ID
     * @return 权限菜单列表
     */
    List<SysRoleMenuEntity> getRoleMenus(String roleId);

    /**
     * 修改权限菜单
     * @param roleMenus 权限菜单集合
     * @param roleId 权限Id
     * @param userId 操作人
     */
    void editRoleMenus(List<SysRoleMenuEntity> roleMenus, String roleId, String userId);
}

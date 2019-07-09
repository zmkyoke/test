package com.proper.phip.sys.menu.service;

import com.proper.phip.sys.menu.entity.SysMenuEntity;
import com.proper.phip.sys.menu.entity.SysMenuUrlEntity;

import java.util.List;

/**
 * 主菜单Service
 */
public interface SysMenuService {

    /**
     * 取得有权限的菜单列表
     * @param roleIds 权限ID列表
     * @return 菜单列表
     */
    List<SysMenuEntity> getMenuByRoleIds(List<String> roleIds);

    /**
     * 取得菜单列表
     * @return 菜单列表
     * */
    List<SysMenuEntity> getMenus();

    /**
     * 保存菜单
     * @param sysMenuEntity 菜单实体
     * @param userId 操作人
     */
    void addMenu(SysMenuEntity sysMenuEntity, String userId);

    /**
     * 修改菜单
     * @param sysMenuEntity 菜单实体
     * @param userId 操作人
     */
    void editMenu(SysMenuEntity sysMenuEntity, String userId);

    /**
     * 删除菜单
     * @param id 主键
     */
    void deleteMenu(String id);

    /**
     * 取得菜单URL列表
     * @param menuId 菜单ID
     * @return 菜单URL列表
     */
    List<SysMenuUrlEntity> getMenuUrls(String menuId);

    /**
     * 修改菜单URL
     * @param menuUrls 菜单URL实体集合
     * @param menuId 菜单ID
     * @param userId 操作人
     */
    void editMenuUrls(List<SysMenuUrlEntity> menuUrls, String menuId, String userId);
}

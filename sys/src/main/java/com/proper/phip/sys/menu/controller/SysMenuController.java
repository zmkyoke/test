package com.proper.phip.sys.menu.controller;

import com.proper.phip.core.base.BaseController;
import com.proper.phip.core.restful.SimpleResponse;
import com.proper.phip.sys.menu.entity.SysMenuEntity;
import com.proper.phip.sys.menu.entity.SysMenuUrlEntity;
import com.proper.phip.sys.menu.service.SysMenuService;
import com.proper.phip.sys.role.entity.SysRoleEntity;
import com.proper.phip.sys.role.service.SysRoleService;
import com.proper.phip.sys.user.entity.SysUserRoleEntity;
import com.proper.phip.sys.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 主菜单Controller
 */
@RestController
@RequestMapping("/sys")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 取得有权限的菜单列表
     */
    @GetMapping("/role-menus")
    public List<SysMenuEntity> getRoleMenus() {
        String userId = getCurrentUserId();
        List<SysUserRoleEntity> roles = sysUserService.getAllRoleByUserId(userId);
        List<String> roleIds = new ArrayList<>();
        for (SysUserRoleEntity tmp : roles) {
            roleIds.add(tmp.getRoleId());
        }
        List<SysRoleEntity> sysRoles = sysRoleService.getRoles(null, null, "1");
        for (SysRoleEntity tmp : sysRoles) {
            roleIds.add(tmp.getId());
        }
        List<SysMenuEntity> list = new ArrayList<>();
        if (roleIds.size() > 0) {
            list = sysMenuService.getMenuByRoleIds(roleIds);
        }
        return list;
    }

    /**
     * 取得菜单列表
     */
    @GetMapping("/menus")
    public List<SysMenuEntity> getMenus() {
        return sysMenuService.getMenus();
    }

    /**
     * 保存菜单
     **/
    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    public SimpleResponse addMenu(@RequestBody SysMenuEntity sysMenuEntity) {
        sysMenuService.addMenu(sysMenuEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 编辑菜单
     **/
    @RequestMapping(value = "/menus/{id}", method = RequestMethod.PUT)
    public SimpleResponse editMenu(@RequestBody SysMenuEntity sysMenuEntity) {
        sysMenuService.editMenu(sysMenuEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 删除菜单
     **/
    @RequestMapping(value = "/menus/{id}", method = RequestMethod.DELETE)
    public SimpleResponse deleteMenu(@PathVariable String id) {
        sysMenuService.deleteMenu(id);
        return responseSimple("");
    }

    /**
     * 取得菜单URL列表
     */
    @GetMapping("/menus/{id}/urls")
    public List<SysMenuUrlEntity> getMenuUrls(@PathVariable String id) {
        return sysMenuService.getMenuUrls(id);
    }

    /**
     * 编辑菜单URL
     **/
    @RequestMapping(value = "/menus/{id}/urls", method = RequestMethod.PUT)
    public SimpleResponse editMenuUrl(@PathVariable String id, @RequestBody List<SysMenuUrlEntity> menuUrls) {
        sysMenuService.editMenuUrls(menuUrls, id, getCurrentUserId());
        return responseSimple("");
    }
}

package com.proper.phip.sys.role.controller;

import com.proper.phip.core.base.BaseController;
import com.proper.phip.core.restful.Pagination;
import com.proper.phip.core.restful.ResultPage;
import com.proper.phip.core.restful.SimpleResponse;
import com.proper.phip.sys.role.entity.SysRoleEntity;
import com.proper.phip.sys.role.entity.SysRoleMenuEntity;
import com.proper.phip.sys.role.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 权限Controller
 */
@RestController
@RequestMapping("/sys")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 取得权限列表
     **/
    @GetMapping("/page-roles")
    public ResultPage<SysRoleEntity> getPageRoles(HttpServletRequest request, String roleName) {
        Pagination pagination = getPagination(request);
        List<SysRoleEntity> roles = sysRoleService.getRoles(pagination, roleName, null);
        return responsePage(roles);
    }

    /**
     * 取得权限列表
     **/
    @GetMapping("/roles")
    public List<SysRoleEntity> getRoles(String isDefault) {
        List<SysRoleEntity> roles = sysRoleService.getRoles(null, null, isDefault);
        return roles;
    }

    /**
     * 保存权限
     **/
    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public SimpleResponse addRole(@RequestBody SysRoleEntity sysRoleEntity) {
        sysRoleService.addRole(sysRoleEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 编辑权限
     **/
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.PUT)
    public SimpleResponse editRole(@RequestBody SysRoleEntity sysRoleEntity) {
        sysRoleService.editRole(sysRoleEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 删除权限
     **/
    @RequestMapping(value = "/roles/{id}", method = RequestMethod.DELETE)
    public SimpleResponse deleteRole(@PathVariable String id) {
        sysRoleService.deleteRole(id);
        return responseSimple("");
    }

    /**
     * 取得权限菜单列表
     **/
    @GetMapping("/roles/{id}/menus")
    public List<SysRoleMenuEntity> getRoleMenus(@PathVariable String id) {
        return sysRoleService.getRoleMenus(id);
    }

    /**
     * 编辑权限菜单
     **/
    @RequestMapping(value = "/roles/{id}/menus", method = RequestMethod.PUT)
    public SimpleResponse editRoleMenus(@PathVariable String id, @RequestBody List<SysRoleMenuEntity> roleMenus) {
        sysRoleService.editRoleMenus(roleMenus, id, getCurrentUserId());
        return responseSimple("");
    }
}

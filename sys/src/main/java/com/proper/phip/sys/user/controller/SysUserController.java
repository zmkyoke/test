package com.proper.phip.sys.user.controller;

import com.proper.phip.core.base.BaseController;
import com.proper.phip.core.restful.Pagination;
import com.proper.phip.core.restful.ResultPage;
import com.proper.phip.core.restful.SimpleResponse;
import com.proper.phip.sys.user.entity.SysUserEntity;
import com.proper.phip.sys.user.service.SysUserService;
import com.proper.phip.sys.user.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 取得登录的用户信息
     **/
    @GetMapping("/current-user")
    public SysUserVO getCurrentUser() {
        String userId = getCurrentUserId();
        return sysUserService.getUserInfoById(userId);
    }

    /**
     * 取得用户列表
     **/
    @GetMapping("/page-users")
    public ResultPage<SysUserEntity> getPageUsers(HttpServletRequest request, String username, String name) {
        Pagination pagination = getPagination(request);
        List<SysUserEntity> users = sysUserService.getUsers(pagination, username, name);
        return responsePage(users);
    }

    /**
     * 保存用户
     **/
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public SimpleResponse addUser(@RequestBody SysUserVO sysUserVO) {
        sysUserService.addUser(sysUserVO, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 编辑用户
     **/
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public SimpleResponse editUser(@RequestBody SysUserVO sysUserVO) {
        sysUserService.editUser(sysUserVO, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 取得用户
     **/
    @GetMapping(value = "/users/{id}")
    public SysUserVO getUser(@PathVariable String id) {
        return sysUserService.getUserInfoById(id);
    }

    /**
     * 修改密码
     **/
    @RequestMapping(value = "/users/{id}/password", method = RequestMethod.POST)
    public SimpleResponse editUserPassword(@PathVariable String id, @RequestBody Map<String, String> map) {
        sysUserService.editUserPassword(id, map);
        return responseSimple("");
    }
}

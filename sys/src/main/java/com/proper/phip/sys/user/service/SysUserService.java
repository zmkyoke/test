package com.proper.phip.sys.user.service;

import com.proper.phip.core.restful.Pagination;
import com.proper.phip.sys.user.entity.SysUserEntity;
import com.proper.phip.sys.user.entity.SysUserRoleEntity;
import com.proper.phip.sys.user.vo.SysUserDeptVO;
import com.proper.phip.sys.user.vo.SysUserVO;

import java.util.List;
import java.util.Map;

public interface SysUserService {

    /**
     * 使用用户ID取得用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    SysUserEntity getUserById(String userId);

    /**
     * 使用用户ID取得用户信息
     * @param userId 用户ID
     * @return 用户信息
     */
    SysUserVO getUserInfoById(String userId);

    /**
     * 使用用户ID取得用户权限列表（包括用户+用户科室权限）
     * @param userId 用户ID
     * @return 用户权限列表
     */
    List<SysUserRoleEntity> getAllRoleByUserId(String userId);

    /**
     * 使用用户ID取得用户权限列表
     * @param userId 用户ID
     * @return 用户权限列表
     */
    List<SysUserRoleEntity> getRoleByUserId(String userId);

    /**
     * 使用用户ID取得用户科室列表
     * @param userId 用户ID
     * @return 用户科室列表
     */
    List<SysUserDeptVO> getDeptByUserId(String userId);

    /**
     * 取得用户列表
     * @param pagination 分页信息
     * @param username 用户名
     * @param name 姓名
     * @return 用户列表
     */
    List<SysUserEntity> getUsers(Pagination pagination, String username, String name);

    /**
     * 新建用户
     * @param sysUserVO 用户实体
     * @param userId 操作人
     */
    void addUser(SysUserVO sysUserVO, String userId);

    /**
     * 修改用户
     * @param sysUserVO 用户实体
     * @param userId 操作人
     */
    void editUser(SysUserVO sysUserVO, String userId);

    /**
     * 修改用户密码
     * @param userId 用户ID
     * @param passwordInfo 密码信息
     */
    void editUserPassword(String userId, Map<String, String> passwordInfo);
}

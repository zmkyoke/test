package com.proper.phip.sys.user.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.user.dto.SysUserRoleUrlDTO;
import com.proper.phip.sys.user.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户权限Mapper
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

    /**
     * 用户有权限的url
     * @param username 用户名
     * @return 用户有权限的url列表
     */
    @Select("SELECT U.USERNAME, MU.URL, MU.REQUEST_METHOD"
        + " FROM PHIP_SYS_USER U JOIN PHIP_SYS_USER_ROLE UR ON U.ID = UR.USER_OR_DEPT_ID AND UR.ROLE_TYPE = 'USER'"
        + " JOIN PHIP_SYS_ROLE_MENU RM ON UR.ROLE_ID = RM.ROLE_ID JOIN PHIP_SYS_MENU_URL MU ON RM.MENU_ID = MU.MENU_ID"
        + " WHERE U.USERNAME = #{username}"
        + " UNION ALL"
        + " SELECT U.USERNAME, MU.URL, MU.REQUEST_METHOD"
        + " FROM PHIP_SYS_USER U JOIN PHIP_SYS_USER_DEPT UD ON U.ID = UD.USER_ID"
        + " JOIN PHIP_SYS_USER_ROLE UR ON UD.DEPT_ID = UR.USER_OR_DEPT_ID AND UR.ROLE_TYPE = 'DEPT'"
        + " JOIN PHIP_SYS_ROLE_MENU RM ON UR.ROLE_ID = RM.ROLE_ID JOIN PHIP_SYS_MENU_URL MU ON RM.MENU_ID = MU.MENU_ID"
        + " WHERE U.USERNAME = #{username}"
        + " UNION ALL"
        + " SELECT #{username} AS USERNAME, MU.URL, MU.REQUEST_METHOD"
        + " FROM PHIP_SYS_ROLE R JOIN PHIP_SYS_ROLE_MENU RM ON R.ID = RM.ROLE_ID JOIN PHIP_SYS_MENU_URL MU ON RM.MENU_ID = MU.MENU_ID"
        + " WHERE R.IS_DEFAULT = '1'")
    List<SysUserRoleUrlDTO> getUserRoleUrlByUsername(@Param("username") String username);

    /**
     * 使用用户ID取得用户权限列表
     * @param userId 用户ID
     * @return 用户权限列表
     */
    @Select("SELECT UR.* FROM PHIP_SYS_USER_ROLE UR WHERE UR.ROLE_TYPE='USER' AND UR.USER_OR_DEPT_ID=#{userId}"
        + " UNION"
        + " SELECT UR.* FROM PHIP_SYS_USER_ROLE UR JOIN PHIP_SYS_USER_DEPT UD ON UR.USER_OR_DEPT_ID = UD.ID"
        + " WHERE UR.ROLE_TYPE='DEPT' AND UD.USER_ID=#{userId}")
    List<SysUserRoleEntity> getAllRoleByUserId(@Param("userId") String userId);

    /**
     * 删除用户权限列表
     * @param userOrDeptId 用户或者科室ID
     */
    @Select("delete from PHIP_SYS_USER_ROLE where USER_OR_DEPT_ID=#{userOrDeptId} AND ROLE_TYPE=#{roleType}")
    void deleteByUserOrDeptId(@Param("userOrDeptId") String userOrDeptId, @Param("roleType") String roleType);
}

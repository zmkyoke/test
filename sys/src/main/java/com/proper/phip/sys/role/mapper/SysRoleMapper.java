package com.proper.phip.sys.role.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.role.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 权限Mapper
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    /**
     * 取得权限列表
     * @param roleName 权限名称
     * @param roleCode 权限编码
     * @return 权限列表
     */
    @Select("<script>select * from phip_sys_role where 1 = 1"
        + "<if test='roleName!=null'> and role_name like '%'||#{roleName}||'%'</if>"
        + "<if test='roleCode!=null'> and role_code = #{roleCode}</if>"
        + "<if test='isDefault!=null'> and is_default = #{isDefault}</if>"
        + " order by role_code</script>")
    List<SysRoleEntity> getRoles(@Param("roleName") String roleName, @Param("roleCode") String roleCode,
                                 @Param("isDefault") String isDefault);
}

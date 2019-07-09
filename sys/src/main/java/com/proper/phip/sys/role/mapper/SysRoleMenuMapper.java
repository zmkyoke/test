package com.proper.phip.sys.role.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.menu.entity.SysMenuUrlEntity;
import com.proper.phip.sys.role.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 权限菜单Mapper
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenuEntity> {

    /**
     * 取得权限url
     * @param roleId 权限Id
     */
    @Select("SELECT U.* FROM PHIP_SYS_ROLE_MENU R JOIN PHIP_SYS_MENU_URL U ON R.MENU_ID = U.MENU_ID WHERE R.ROLE_ID = #{roleId}")
    List<SysMenuUrlEntity> getRoleUrls(@Param("roleId") String roleId);

    /**
     * 删除权限菜单
     * @param roleId 权限ID
     */
    @Delete("delete from PHIP_SYS_ROLE_MENU where role_id = #{roleId}")
    void deleteByRoleId(@Param("roleId") String roleId);
}

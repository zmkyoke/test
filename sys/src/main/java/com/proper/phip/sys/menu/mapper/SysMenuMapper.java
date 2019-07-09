package com.proper.phip.sys.menu.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.menu.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单Mapper
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {

    /**
     * 取得有权限的菜单列表
     * @param roleIds 用户权限ID集合
     * @return 菜单列表
     */
    @Select("<script>"
        + " SELECT distinct M.* FROM PHIP_SYS_MENU M JOIN PHIP_SYS_ROLE_MENU RM ON M.ID = RM.MENU_ID"
        + " WHERE M.NODE_TYPE != 'FUNC' AND RM.ROLE_ID IN "
        + "<foreach item='item' index='index' collection='roleIds' open='(' separator=',' close=')'>#{item}</foreach>"
        + " ORDER BY SEQ_NUM"
        + "</script>")
    List<SysMenuEntity> getMenuByRoleIds(@Param("roleIds") List<String> roleIds);

    /**
     * 取得菜单列表
     * @param code 菜单编码
     * @return 菜单列表
     */
    @Select("<script>select * from phip_sys_menu where 1=1"
        + "<if test='code!=null'> and code = #{code}</if>"
        + " order by seq_num</script>")
    List<SysMenuEntity> getMenus(@Param("code") String code);

    /**
     * 删除菜单
     * @param ids 主键集合
     */
    @Delete("<script>delete from PHIP_SYS_MENU where id in"
        + "<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach>"
        + "</script>")
    void deleteByIds(@Param("ids") List<String> ids);
}

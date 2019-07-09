package com.proper.phip.sys.menu.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.menu.entity.SysMenuUrlEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 菜单使用URL Mapper
 */
public interface SysMenuUrlMapper extends BaseMapper<SysMenuUrlEntity> {

    /**
     * 取得菜单URL列表
     * @param menuId 菜单ID
     * @return 菜单URL列表
     */
    @Select("<script>select * from phip_sys_menu_url where 1=1"
        + "<if test='menuId!=null'> and menu_id = #{menuId}</if>"
        + " order by url</script>")
    List<SysMenuUrlEntity> getMenuUrls(@Param("menuId") String menuId);

    /**
     * 删除菜单URL
     * @param menuIds 菜单主键集合
     */
    @Delete("<script>delete from PHIP_SYS_MENU_URL where menu_id in"
        + "<foreach item='item' index='index' collection='menuIds' open='(' separator=',' close=')'>#{item}</foreach>"
        + "</script>")
    void deleteByMenuIds(@Param("menuIds") List<String> menuIds);
}

package com.proper.phip.sys.user.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.user.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户Mapper
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 取得用户列表
     * @param username 用户名
     * @param name 姓名
     * @return 用户列表
     */
    @Select("<script>select * from phip_sys_user where 1=1"
        + "<if test=\"username!=null and username!=''\"> and username = #{username}</if>"
        + "<if test=\"name!=null and name!=''\"> and name like '%'||#{name}||'%'</if>"
        + "order by username</script>")
    List<SysUserEntity> getUsers(@Param("username") String username, @Param("name") String name);
}

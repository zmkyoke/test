package com.proper.phip.sys.user.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.menu.entity.SysMenuEntity;
import com.proper.phip.sys.user.entity.SysUserDeptEntity;
import com.proper.phip.sys.user.vo.SysUserDeptVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户科室Mapper
 */
@Mapper
public interface SysUserDeptMapper extends BaseMapper<SysUserDeptEntity> {

    /**
     * 取得用户科室列表
     * @param deptIds 用户名
     * @return 用户科室列表
     */
    @Select("<script>"
        + " SELECT * FROM PHIP_SYS_USER_DEPT WHERE DEPT_ID IN"
        + "<foreach item='item' index='index' collection='deptIds' open='(' separator=',' close=')'>#{item}</foreach>"
        + "</script>")
    List<SysUserDeptEntity> getUserDeptByDeptId(@Param("deptIds") List<String> deptIds);

    /**
     * 取得用户科室
     * @param userId 用户ID
     * @return 用户科室
     */
    @Select("SELECT UD.ID, UD.USER_ID, UD.COMPANY_ID, UD.REGION_ID, UD.DEPT_ID, UD.MAIN_FLG, UD.LEADER_FLG,"
        + " C.NAME AS COMPANY_NAME, R.REGION_NAME, D.DEPT_NAME"
        + " FROM PHIP_SYS_USER_DEPT UD LEFT JOIN PHIP_SYS_COMPANY C ON UD.COMPANY_ID = C.ID"
        + " LEFT JOIN PHIP_SYS_REGION R ON UD.REGION_ID = R.ID LEFT JOIN PHIP_SYS_DEPT D ON UD.DEPT_ID = D.ID"
        + " WHERE UD.USER_ID = #{userId}")
    List<SysUserDeptVO> getDeptByUserId(@Param("userId") String userId);

    /**
     * 删除用户科室列表
     * @param userId 用户ID
     */
    @Select("delete from PHIP_SYS_USER_DEPT where USER_ID=#{userId}")
    void deleteByUserId(@Param("userId") String userId);
}

package com.proper.phip.sys.dept.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.dept.entity.SysDeptEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 科室Mapper
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {

    /**
     * 取得科室列表
     * @param companyRegionId 机构或分支机构ID
     * @param deptCode 科室编码
     * @return 科室列表
     */
    @Select("<script>SELECT * FROM PHIP_SYS_DEPT WHERE STATUS = '1'"
        + "<if test='companyRegionId!=null'> and company_region_id = #{companyRegionId}</if>"
        + "<if test='deptCode!=null'> and dept_code = #{deptCode}</if>"
        + " ORDER BY SEQ_NUM"
        + "</script>")
    List<SysDeptEntity> getDepts(@Param("companyRegionId") String companyRegionId, @Param("deptCode") String deptCode);
}

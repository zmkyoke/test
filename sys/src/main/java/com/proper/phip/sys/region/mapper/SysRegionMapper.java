package com.proper.phip.sys.region.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.region.entity.SysRegionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 分支机构Mapper
 */
@Mapper
public interface SysRegionMapper extends BaseMapper<SysRegionEntity> {

    /**
     * 取得分支机构列表
     * @param regionName 分支机构名称
     * @param regionCode 分支机构编码
     * @return 分支机构列表
     */
    @Select("<script>select * from phip_sys_region where status = '1'"
        + "<if test='regionName!=null'> and region_name like '%'||#{regionName}||'%'</if>"
        + "<if test='regionCode!=null'> and region_code = #{regionCode}</if>"
        + "<if test='companyId!=null'> and company_id = #{companyId}</if>"
        + "order by region_code</script>")
    List<SysRegionEntity> getRegions(@Param("regionName") String regionName, @Param("regionCode") String regionCode,
                                     @Param("companyId") String companyId);
}

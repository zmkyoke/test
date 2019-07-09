package com.proper.phip.sys.company.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.company.entity.SysCompanyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 机构Mapper
 */
@Mapper
public interface SysCompanyMapper extends BaseMapper<SysCompanyEntity> {

    /**
     * 取得机构列表
     * @param name 机构名称
     * @param code 机构编码
     * @return 机构列表
     */
    @Select("<script>select * from phip_sys_company where status = '1'"
        + "<if test='name!=null'> and name like '%'||#{name}||'%'</if>"
        + "<if test='code!=null'> and code = #{code}</if>"
        + "order by code</script>")
    List<SysCompanyEntity> getCompanies(@Param("name") String name, @Param("code") String code);
}

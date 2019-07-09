package com.proper.phip.sys.param.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.param.entity.SysParamItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 参数项目Mapper
 */
@Mapper
public interface SysParamItemMapper extends BaseMapper<SysParamItemEntity> {

    /**
     * 取得参数项目列表
     * @param itemCode 项目编码
     * @return 参数项目列表
     */
    @Select("<script>SELECT * FROM PHIP_SYS_PARAM_ITEM WHERE 1=1"
        + "<if test='itemCode!=null'> and ITEM_CODE = #{itemCode}</if>"
        + " ORDER BY ITEM_CODE"
        + "</script>")
    List<SysParamItemEntity> getParamItems(@Param("itemCode") String itemCode);
}

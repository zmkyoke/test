package com.proper.phip.sys.batch.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.batch.entity.SysBatchEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 批处理设置Mapper
 */
@Mapper
public interface SysBatchMapper extends BaseMapper<SysBatchEntity> {

    /**
     * 取得批处理列表
     * @param code 批处理编码
     * @return 批处理列表
     */
    @Select("<script>SELECT * FROM PHIP_SYS_BATCH WHERE 1=1"
        + "<if test='code!=null'> and code = #{code}</if>"
        + "<if test='status!=null'> and status = #{status}</if>"
        + " ORDER BY code</script>")
    List<SysBatchEntity> getBatchs(@Param("code") String code, @Param("status") String status);
}

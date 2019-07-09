package com.proper.phip.sys.batch.mapper;

import com.proper.phip.core.base.BaseMapper;
import com.proper.phip.sys.batch.entity.SysBatchLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 批处理执行日志Mapper
 */
@Mapper
public interface SysBatchLogMapper extends BaseMapper<SysBatchLogEntity> {

    @Select("select * from phip_sys_batch_log where batch_id=#{batchId} order by start_time desc")
    List<SysBatchLogEntity> getBatchLogs(@Param("batchId") String batchId);
}

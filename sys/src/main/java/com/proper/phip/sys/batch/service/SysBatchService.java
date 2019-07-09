package com.proper.phip.sys.batch.service;

import com.proper.phip.core.restful.Pagination;
import com.proper.phip.sys.batch.entity.SysBatchEntity;
import com.proper.phip.sys.batch.entity.SysBatchErrEntity;
import com.proper.phip.sys.batch.entity.SysBatchLogEntity;

import java.util.List;

/**
 * 批处理设置Service
 */
public interface SysBatchService {

    /**
     * 取得批处理列表
     * @return 批处理列表
     */
    List<SysBatchEntity> getBatchs();

    /**
     * 保存批处理
     * @param sysBatchEntity 批处理实体
     * @param userId 操作人
     */
    void addBatch(SysBatchEntity sysBatchEntity, String userId);

    /**
     * 修改批处理
     * @param sysBatchEntity 批处理实体
     * @param userId 操作人
     */
    void editBatch(SysBatchEntity sysBatchEntity, String userId);

    /**
     * 删除批处理
     * @param id 主键
     */
    void deleteBatch(String id);

    /**
     * 取得批处理日志列表
     * @param pagination 分页信息
     * @param batchId 批处理ID
     * @return 批处理日志列表
     */
    List<SysBatchLogEntity> getBatchLogs(Pagination pagination, String batchId);

    /**
     * 执行开始批处理日志
     * @param batchId 批处理ID
     * @param userId 操作人
     * @return 批处理日志ID
     */
    String startBatchLog(String batchId, String userId);

    /**
     * 执行结束批处理日志
     * @param batchId 批处理ID
     * @param logId 批处理日志ID
     * @param execErrNum 执行错误数量
     * @param userId 操作人
     */
    void endBatchLog(String batchId, String logId, int execErrNum, String userId);

    /**
     * 保存执行错误信息
     * @param sysBatchErrEntity 错误信息实体
     */
    void addBatchErr(SysBatchErrEntity sysBatchErrEntity);
}

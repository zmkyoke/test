package com.proper.phip.sys.batch.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 批处理执行日志表
 */
@Table(name = "PHIP_SYS_BATCH_LOG")
public class SysBatchLogEntity extends BaseEntity {

    // 批处理ID  表：PHIP_SYS_BATCH
    private String batchId;

    // 启动时间
    private String startTime;

    // 结束时间
    private String endTime;

    // 执行结果  1:成功;0:失败
    private String execResult;

    // 执行失败记录数
    private Integer execErrNum;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExecResult() {
        return execResult;
    }

    public void setExecResult(String execResult) {
        this.execResult = execResult;
    }

    public Integer getExecErrNum() {
        return execErrNum;
    }

    public void setExecErrNum(Integer execErrNum) {
        this.execErrNum = execErrNum;
    }
}

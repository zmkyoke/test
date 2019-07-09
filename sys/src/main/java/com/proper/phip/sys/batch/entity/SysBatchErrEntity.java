package com.proper.phip.sys.batch.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 批处理执行错误信息表
 */
@Table(name = "PHIP_SYS_BATCH_ERR")
public class SysBatchErrEntity extends BaseEntity {

    // 批处理ID  表：PHIP_SYS_BATCH
    private String batchId;

    // 批处理执行ID  表：PHIP_SYS_BATCH_LOG
    private String batchLogId;

    // 错误数据
    private String content;

    // 错误原因
    private String cause;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchLogId() {
        return batchLogId;
    }

    public void setBatchLogId(String batchLogId) {
        this.batchLogId = batchLogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}

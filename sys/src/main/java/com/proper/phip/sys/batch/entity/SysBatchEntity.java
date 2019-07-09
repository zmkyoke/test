package com.proper.phip.sys.batch.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 批处理设置表
 */
@Table(name = "PHIP_SYS_BATCH")
public class SysBatchEntity extends BaseEntity {

    // 批次编码
    private String code;

    // 批次名称
    private String name;

    // 批次程序
    private String program;

    // 批次类型  1:固定时间;2:间隔时间
    private String type;

    // 状态  1:启用;0:停用
    private String status;

    // 最后执行时间
    private String lastExecTime;

    // 最后执行结果  1:成功;0:失败
    private String lastExecResult;

    // 备注
    private String remark;

    // 最后执行失败记录数
    private Integer lastExecErrNum;

    // 固定时间类别  DAY:每天;WEEK:每周;MONTH:每月
    private String fixedType;

    // 固定日期
    private String fixedDay;

    // 固定时间
    private String fixedTime;

    // 间隔时间类别  SECOND:秒;MINUE:分;HOUR:小时
    private String intervalType;

    // 间隔时间
    private Integer intervalTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastExecTime() {
        return lastExecTime;
    }

    public void setLastExecTime(String lastExecTime) {
        this.lastExecTime = lastExecTime;
    }

    public String getLastExecResult() {
        return lastExecResult;
    }

    public void setLastExecResult(String lastExecResult) {
        this.lastExecResult = lastExecResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getLastExecErrNum() {
        return lastExecErrNum;
    }

    public void setLastExecErrNum(Integer lastExecErrNum) {
        this.lastExecErrNum = lastExecErrNum;
    }

    public String getFixedType() {
        return fixedType;
    }

    public void setFixedType(String fixedType) {
        this.fixedType = fixedType;
    }

    public String getFixedDay() {
        return fixedDay;
    }

    public void setFixedDay(String fixedDay) {
        this.fixedDay = fixedDay;
    }

    public String getFixedTime() {
        return fixedTime;
    }

    public void setFixedTime(String fixedTime) {
        this.fixedTime = fixedTime;
    }

    public String getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    public Integer getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(Integer intervalTime) {
        this.intervalTime = intervalTime;
    }
}

package com.proper.phip.sys.batch.job;

import com.alibaba.fastjson.JSON;
import com.proper.phip.sys.batch.entity.SysBatchErrEntity;
import com.proper.phip.sys.batch.service.SysBatchService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 批处理作业基类
 */
public abstract class BaseJobBean extends QuartzJobBean {

    @Autowired
    private SysBatchService sysBatchService;

    private static final String BATCH_USER = "FC_BATCH";

    @Override
    public void executeInternal(JobExecutionContext context) {
        // 执行开始批处理日志
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String userId = jobDataMap.getString("userId");
        if (StringUtils.isEmpty(userId)) {
            userId = BATCH_USER;
        }
        String logId = sysBatchService.startBatchLog(jobDataMap.getString("batchId"), userId);

        int execErrNum = 0;
        try {
            execErrNum = executeBusiness(context, logId, userId);
        } catch (Exception e) {
            e.printStackTrace();
            execErrNum = 1;
        }

        // 执行结束批处理日志
        sysBatchService.endBatchLog(jobDataMap.getString("batchId"), logId, execErrNum, userId);
    }

    /**
     * 保存执行错误消息
     * @param context 定时任务信息
     * @param logId 执行ID
     * @param data 执行数据
     * @param e 错误原因
     */
    protected void addBatchErr(JobExecutionContext context, String logId, Object data, Exception e) {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String userId = jobDataMap.getString("userId");
        if (StringUtils.isEmpty(userId)) {
            userId = BATCH_USER;
        }
        SysBatchErrEntity sysBatchErrEntity = new SysBatchErrEntity();
        sysBatchErrEntity.setBatchId(jobDataMap.getString("batchId"));
        sysBatchErrEntity.setBatchLogId(logId);
        sysBatchErrEntity.setContent(JSON.toJSONString(data));
        sysBatchErrEntity.setCause(ExceptionUtils.getStackTrace(e));
        sysBatchErrEntity.initAddInfo(userId, new Date());
        sysBatchService.addBatchErr(sysBatchErrEntity);
    }

    /**
     * 定时任务执行方法
     * @param context 定时任务信息
     * @param logId 执行ID
     * @param userId 用户ID
     * @return 执行错误数
     */
    protected abstract int executeBusiness(JobExecutionContext context, String logId, String userId);
}

package com.proper.phip.sys.batch.service;

import com.github.pagehelper.PageHelper;
import com.proper.phip.core.i18n.I18NService;
import com.proper.phip.core.restful.Pagination;
import com.proper.phip.core.restful.ReturnException;
import com.proper.phip.sys.batch.entity.SysBatchEntity;
import com.proper.phip.sys.batch.entity.SysBatchErrEntity;
import com.proper.phip.sys.batch.entity.SysBatchLogEntity;
import com.proper.phip.sys.batch.mapper.SysBatchErrMapper;
import com.proper.phip.sys.batch.mapper.SysBatchLogMapper;
import com.proper.phip.sys.batch.mapper.SysBatchMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SysBatchServiceImpl implements SysBatchService {

    @Autowired
    private I18NService i18NService;

    @Autowired
    private SysBatchMapper sysBatchMapper;

    @Autowired
    private SysBatchLogMapper sysBatchLogMapper;

    @Autowired
    private SysBatchErrMapper sysBatchErrMapper;

    @Override
    public List<SysBatchEntity> getBatchs() {
        return sysBatchMapper.getBatchs(null, null);
    }

    @Override
    public void addBatch(SysBatchEntity sysBatchEntity, String userId) {
        List<SysBatchEntity> batchs = sysBatchMapper.getBatchs(sysBatchEntity.getCode(), null);
        if (batchs.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.code-repeat"));
        }
        sysBatchEntity.initAddInfo(userId, new Date());
        sysBatchMapper.insertSelective(sysBatchEntity);
    }

    @Override
    public void editBatch(SysBatchEntity sysBatchEntity, String userId) {
        sysBatchEntity.initModifyInfo(userId, new Date());
        sysBatchMapper.updateByPrimaryKeySelective(sysBatchEntity);
    }

    @Override
    public void deleteBatch(String id) {
        SysBatchEntity searchEntity = sysBatchMapper.selectByPrimaryKey(id);
        if ("1".equals(searchEntity.getStatus())) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.delete.started"));
        }
        sysBatchMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysBatchLogEntity> getBatchLogs(Pagination pagination, String batchId) {
        if (pagination != null) {
            PageHelper.startPage(pagination.getCurrent(), pagination.getPageSize());
        }
        return sysBatchLogMapper.getBatchLogs(batchId);
    }

    @Override
    public String startBatchLog(String batchId, String userId) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SysBatchLogEntity sysBatchLogEntity = new SysBatchLogEntity();
        sysBatchLogEntity.setBatchId(batchId);
        sysBatchLogEntity.setStartTime(df.format(date));
        sysBatchLogEntity.initAddInfo(userId, date);
        sysBatchLogMapper.insertSelective(sysBatchLogEntity);
        return sysBatchLogEntity.getId();
    }

    @Override
    public void endBatchLog(String batchId, String logId, int execErrNum, String userId) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String execResult = "1";
        if (execErrNum > 0) {
            execResult = "0";
        }
        SysBatchLogEntity sysBatchLogEntity = new SysBatchLogEntity();
        sysBatchLogEntity.setId(logId);
        sysBatchLogEntity.setEndTime(df.format(date));
        sysBatchLogEntity.setExecResult(execResult);
        sysBatchLogEntity.setExecErrNum(execErrNum);
        sysBatchLogEntity.initModifyInfo(userId, date);

        SysBatchEntity sysBatchEntity = new SysBatchEntity();
        sysBatchEntity.setId(batchId);
        sysBatchEntity.setLastExecTime(df.format(date));
        sysBatchEntity.setLastExecResult(execResult);
        sysBatchEntity.setLastExecErrNum(execErrNum);

        sysBatchLogMapper.updateByPrimaryKeySelective(sysBatchLogEntity);
        sysBatchMapper.updateByPrimaryKeySelective(sysBatchEntity);
    }

    @Override
    public void addBatchErr(SysBatchErrEntity sysBatchErrEntity) {
        sysBatchErrMapper.insertSelective(sysBatchErrEntity);
    }
}

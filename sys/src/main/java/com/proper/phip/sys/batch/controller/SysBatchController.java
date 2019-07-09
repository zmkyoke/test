package com.proper.phip.sys.batch.controller;

import com.proper.phip.core.base.BaseController;
import com.proper.phip.core.restful.Pagination;
import com.proper.phip.core.restful.ResultPage;
import com.proper.phip.core.restful.SimpleResponse;
import com.proper.phip.sys.batch.entity.SysBatchEntity;
import com.proper.phip.sys.batch.entity.SysBatchLogEntity;
import com.proper.phip.sys.batch.service.SysBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 批处理设置Controller
 */
@RestController
@RequestMapping("/sys")
public class SysBatchController extends BaseController {

    @Autowired
    private SysBatchService sysBatchService;

    /**
     * 取得批处理列表
     **/
    @GetMapping("/batchs")
    public List<SysBatchEntity> getBatchs() {
        return sysBatchService.getBatchs();
    }

    /**
     * 保存批处理
     **/
    @RequestMapping(value = "/batchs", method = RequestMethod.POST)
    public SimpleResponse addBatch(@RequestBody SysBatchEntity sysbatchEntity) {
        sysBatchService.addBatch(sysbatchEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 编辑批处理
     **/
    @RequestMapping(value = "/batchs/{id}", method = RequestMethod.PUT)
    public SimpleResponse editBatch(@RequestBody SysBatchEntity sysBatchEntity) {
        sysBatchService.editBatch(sysBatchEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 删除批处理
     **/
    @RequestMapping(value = "/batchs/{id}", method = RequestMethod.DELETE)
    public SimpleResponse deleteBatch(@PathVariable String id) {
        sysBatchService.deleteBatch(id);
        return responseSimple("");
    }

    /**
     * 取得批处理日志列表
     **/
    @GetMapping("/batchs/{id}/page-logs")
    public ResultPage<SysBatchLogEntity> getBatchLogs(HttpServletRequest request, @PathVariable String id) {
        Pagination pagination = getPagination(request);
        List<SysBatchLogEntity> logs = sysBatchService.getBatchLogs(pagination, id);
        return responsePage(logs);
    }
}

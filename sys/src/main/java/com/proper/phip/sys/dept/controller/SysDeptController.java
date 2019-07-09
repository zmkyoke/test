package com.proper.phip.sys.dept.controller;

import com.proper.phip.core.base.BaseController;
import com.proper.phip.core.restful.SimpleResponse;
import com.proper.phip.sys.dept.entity.SysDeptEntity;
import com.proper.phip.sys.dept.service.SysDeptService;
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
 * 科室Controller
 */
@RestController
@RequestMapping("/sys")
public class SysDeptController extends BaseController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 取得科室列表
     **/
    @GetMapping("/depts")
    public List<SysDeptEntity> getDepts(HttpServletRequest request, String companyRegionId) {
        return sysDeptService.getDepts(companyRegionId);
    }

    /**
     * 保存科室
     **/
    @RequestMapping(value = "/depts", method = RequestMethod.POST)
    public SimpleResponse addDept(@RequestBody SysDeptEntity sysDeptEntity) {
        sysDeptService.addDept(sysDeptEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 编辑科室
     **/
    @RequestMapping(value = "/depts/{id}", method = RequestMethod.PUT)
    public SimpleResponse editDept(@RequestBody SysDeptEntity sysDeptEntity) {
        sysDeptService.editDept(sysDeptEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 删除科室
     **/
    @RequestMapping(value = "/depts/{id}", method = RequestMethod.DELETE)
    public SimpleResponse deleteDept(@PathVariable String id) {
        sysDeptService.deleteDept(id);
        return responseSimple("");
    }
}

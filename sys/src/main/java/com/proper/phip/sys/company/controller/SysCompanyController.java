package com.proper.phip.sys.company.controller;

import com.proper.phip.core.base.BaseController;
import com.proper.phip.core.restful.Pagination;
import com.proper.phip.core.restful.ResultPage;
import com.proper.phip.core.restful.SimpleResponse;
import com.proper.phip.sys.company.entity.SysCompanyEntity;
import com.proper.phip.sys.company.service.SysCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/sys")
public class SysCompanyController extends BaseController {

    @Autowired
    private SysCompanyService sysCompanyService;

    /**
     * 取得机构列表
     **/
    @GetMapping("/page-companies")
    public ResultPage<SysCompanyEntity> getPageCompanies(HttpServletRequest request, String name) {
        Pagination pagination = getPagination(request);
        List<SysCompanyEntity> companies = sysCompanyService.getCompanies(pagination, name, null);
        return responsePage(companies);
    }

    /**
     * 取得机构列表
     **/
    @GetMapping("/companies")
    public List<SysCompanyEntity> getCompanies() {
        return sysCompanyService.getCompanies(null, null, null);
    }

    /**
     * 保存机构
     **/
    @RequestMapping(value = "/companies", method = RequestMethod.POST)
    public SimpleResponse addCompany(@RequestBody SysCompanyEntity sysCompanyEntity) {
        sysCompanyService.addCompany(sysCompanyEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 编辑机构
     **/
    @RequestMapping(value = "/companies/{id}", method = RequestMethod.PUT)
    public SimpleResponse editCompany(@RequestBody SysCompanyEntity sysCompanyEntity) {
        sysCompanyService.editCompany(sysCompanyEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 删除机构
     **/
    @RequestMapping(value = "/companies/{id}", method = RequestMethod.DELETE)
    public SimpleResponse deleteCompany(@PathVariable String id) {
        sysCompanyService.deleteCompany(id);
        return responseSimple("");
    }
}

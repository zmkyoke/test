package com.proper.phip.sys.region.controller;

import com.proper.phip.core.base.BaseController;
import com.proper.phip.core.restful.Pagination;
import com.proper.phip.core.restful.ResultPage;
import com.proper.phip.core.restful.SimpleResponse;
import com.proper.phip.sys.region.entity.SysRegionEntity;
import com.proper.phip.sys.region.service.SysRegionService;
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
 * 分支机构Controller
 */
@RestController
@RequestMapping("/sys")
public class SysRegionController extends BaseController {

    @Autowired
    private SysRegionService sysRegionService;

    /**
     * 取得分支机构列表
     **/
    @GetMapping("/page-regions")
    public ResultPage<SysRegionEntity> getPageRegions(HttpServletRequest request, String regionName, String companyId) {
        Pagination pagination = getPagination(request);
        List<SysRegionEntity> regions = sysRegionService.getRegions(pagination, regionName, null, companyId);
        return responsePage(regions);
    }

    /**
     * 取得分支机构列表
     **/
    @GetMapping("/regions")
    public List<SysRegionEntity> getRegions(HttpServletRequest request, String regionName, String companyId) {
        return sysRegionService.getRegions(null, regionName, null, companyId);
    }

    /**
     * 保存分支机构
     **/
    @RequestMapping(value = "/regions", method = RequestMethod.POST)
    public SimpleResponse addRegion(@RequestBody SysRegionEntity sysRegionEntity) {
        sysRegionService.addRegion(sysRegionEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 编辑分支机构
     **/
    @RequestMapping(value = "/regions/{id}", method = RequestMethod.PUT)
    public SimpleResponse editRegion(@RequestBody SysRegionEntity sysRegionEntity) {
        sysRegionService.editRegion(sysRegionEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 删除分支机构
     **/
    @RequestMapping(value = "/regions/{id}", method = RequestMethod.DELETE)
    public SimpleResponse deleteRegion(@PathVariable String id) {
        sysRegionService.deleteRegion(id);
        return responseSimple("");
    }
}

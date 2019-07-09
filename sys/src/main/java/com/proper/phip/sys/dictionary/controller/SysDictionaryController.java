package com.proper.phip.sys.dictionary.controller;

import com.proper.phip.core.base.BaseController;
import com.proper.phip.core.restful.SimpleResponse;
import com.proper.phip.sys.dictionary.entity.SysDictionaryItemEntity;
import com.proper.phip.sys.dictionary.entity.SysDictionaryTypeEntity;
import com.proper.phip.sys.dictionary.service.SysDictionaryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典Controller
 */
@RestController
@RequestMapping("/sys/dictionary")
public class SysDictionaryController extends BaseController {

    @Autowired
    private SysDictionaryService sysDictionaryService;

    /**
     * 取得字典类别列表
     **/
    @GetMapping("/types")
    public List<SysDictionaryTypeEntity> getDictionaryTypes(HttpServletRequest request) {
        return sysDictionaryService.getDictionaryTypes();
    }

    /**
     * 保存字典类别
     **/
    @RequestMapping(value = "/types", method = RequestMethod.POST)
    public SimpleResponse addDictionaryType(@RequestBody SysDictionaryTypeEntity sysDictionaryTypeEntity) {
        sysDictionaryService.addDictionaryType(sysDictionaryTypeEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 编辑字典类别
     **/
    @RequestMapping(value = "/types/{id}", method = RequestMethod.PUT)
    public SimpleResponse editDictionaryType(@RequestBody SysDictionaryTypeEntity sysDictionaryTypeEntity) {
        sysDictionaryService.editDictionaryType(sysDictionaryTypeEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 删除字典类别
     **/
    @RequestMapping(value = "/types/{id}", method = RequestMethod.DELETE)
    public SimpleResponse deleteDictionaryType(@PathVariable String id) {
        sysDictionaryService.deleteDictionaryType(id);
        return responseSimple("");
    }

    /**
     * 取得字典项目列表
     **/
    @GetMapping("/items")
    public List<SysDictionaryItemEntity> getDictionaryItems(String typeId) {
        return sysDictionaryService.getDictionaryItems(typeId);
    }

    /**
     * 批量取得字典项目
     **/
    @GetMapping("/batch-items")
    public Map<String, List<SysDictionaryItemEntity>> getDictionaryBatchItems(String typeCodes, String status) {
        List<String> typeCodeList = Arrays.asList(typeCodes.split(","));
        Map<String, List<SysDictionaryItemEntity>> map = new HashMap<>();
        for (String typeCode : typeCodeList) {
            List<SysDictionaryItemEntity> items = sysDictionaryService.getCacheDictionaryItems(typeCode);
            if (StringUtils.isNotEmpty(status)) {
                for (int i = items.size() - 1; i >= 0; i--) {
                    if (!items.get(i).getStatus().equals(status)) {
                        items.remove(i);
                    }
                }
            }
            map.put(typeCode, items);
        }
        return map;
    }

    /**
     * 保存项目列表
     **/
    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public SimpleResponse addDictionaryItem(@RequestBody SysDictionaryItemEntity sysDictionaryItemEntity) {
        sysDictionaryService.addDictionaryItem(sysDictionaryItemEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 编辑项目列表
     **/
    @RequestMapping(value = "/items/{id}", method = RequestMethod.PUT)
    public SimpleResponse editDictionaryItem(@RequestBody SysDictionaryItemEntity sysDictionaryItemEntity) {
        sysDictionaryService.editDictionaryItem(sysDictionaryItemEntity, getCurrentUserId());
        return responseSimple("");
    }
}

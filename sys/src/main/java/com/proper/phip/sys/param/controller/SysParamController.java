package com.proper.phip.sys.param.controller;

import com.proper.phip.core.base.BaseController;
import com.proper.phip.core.restful.SimpleResponse;
import com.proper.phip.sys.param.entity.SysParamItemEntity;
import com.proper.phip.sys.param.entity.SysParamValueEntity;
import com.proper.phip.sys.param.service.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 参数Controller
 */
@RestController
@RequestMapping("/sys")
public class SysParamController extends BaseController {

    @Autowired
    private SysParamService sysParamService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 取得参数项目列表
     **/
    @GetMapping("/params/items")
    public List<SysParamItemEntity> getParamItems() {
        return sysParamService.getParamItems();
    }

    /**
     * 保存参数项目
     **/
    @RequestMapping(value = "/params/items", method = RequestMethod.POST)
    public SimpleResponse addParamItem(@RequestBody SysParamItemEntity sysParamItemEntity) {
        sysParamService.addParamItem(sysParamItemEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 编辑参数项目
     **/
    @RequestMapping(value = "/params/items/{id}", method = RequestMethod.PUT)
    public SimpleResponse editParamItem(@RequestBody SysParamItemEntity sysParamItemEntity) {
        sysParamService.editParamItem(sysParamItemEntity, getCurrentUserId());
        return responseSimple("");
    }

    /**
     * 取得参数值列表
     **/
    @GetMapping("/params/values")
    public List<SysParamValueEntity> getParamValues() {
        return sysParamService.getParamValues();
    }

    /**
     * 修改参数值
     **/
    @RequestMapping(value = "/params/values", method = RequestMethod.PUT)
    public SimpleResponse editParamValues(@RequestBody Map<String, String> map) {
        sysParamService.editParamValues(map, getCurrentUserId());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            redisTemplate.delete("phip.param.value::" + entry.getKey());
        }
        return responseSimple("");
    }
}

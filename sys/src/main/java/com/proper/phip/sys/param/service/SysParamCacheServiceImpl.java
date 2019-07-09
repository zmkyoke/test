package com.proper.phip.sys.param.service;

import com.proper.phip.sys.param.entity.SysParamValueEntity;
import com.proper.phip.sys.param.mapper.SysParamValueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SysParamCacheServiceImpl implements SysParamCacheService {

    @Autowired
    private SysParamValueMapper sysParamValueMapper;

    @Override
    @Cacheable(value = "phip.param.value")
    public SysParamValueEntity getCacheParamValue(String itemCode) {
        SysParamValueEntity searchEntity = new SysParamValueEntity();
        searchEntity.setItemCode(itemCode);
        return sysParamValueMapper.selectOne(searchEntity);
    }

    @Override
    @CacheEvict(value = "phip.param.value")
    public void deleteCacheParamValue(String itemCode) {}
}

package com.proper.phip.sys.param.service;

import com.proper.phip.sys.param.entity.SysParamValueEntity;

public interface SysParamCacheService {

    /**
     * 取得参数值
     * @param itemCode 项目编码
     * @return 参数值
     */
    SysParamValueEntity getCacheParamValue(String itemCode);

    /**
     * 删除缓存参数值
     * @param itemCode 项目编码
     */
    void deleteCacheParamValue(String itemCode);
}

package com.proper.phip.sys.region.service;

import com.proper.phip.core.restful.Pagination;
import com.proper.phip.sys.region.entity.SysRegionEntity;

import java.util.List;

/**
 * 分支机构Service
 */
public interface SysRegionService {

    /**
     * 取得分支机构列表
     * @param pagination 分页信息
     * @param regionName 分支机构名称
     * @return 分支机构列表
     */
    List<SysRegionEntity> getRegions(Pagination pagination, String regionName, String regionCode, String companyId);

    /**
     * 保存分支机构
     * @param sysRegionEntity 分支机构实体
     * @param userId 操作人
     */
    void addRegion(SysRegionEntity sysRegionEntity, String userId);

    /**
     * 修改分支机构
     * @param sysRegionEntity 分支机构实体
     * @param userId 操作人
     */
    void editRegion(SysRegionEntity sysRegionEntity, String userId);

    /**
     * 删除分支机构
     * @param id 主键
     */
    void deleteRegion(String id);
}

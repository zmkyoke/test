package com.proper.phip.sys.company.service;

import com.proper.phip.core.restful.Pagination;
import com.proper.phip.sys.company.entity.SysCompanyEntity;

import java.util.List;

/**
 * 机构Service
 */
public interface SysCompanyService {

    /**
     * 取得机构列表
     * @param pagination 分页信息
     * @param name 机构名称
     * @return 机构列表
     */
    List<SysCompanyEntity> getCompanies(Pagination pagination, String name, String code);

    /**
     * 保存机构
     * @param sysCompanyEntity 机构实体
     * @param userId 操作人
     */
    void addCompany(SysCompanyEntity sysCompanyEntity, String userId);

    /**
     * 修改机构
     * @param sysCompanyEntity 机构实体
     * @param userId 操作人
     */
    void editCompany(SysCompanyEntity sysCompanyEntity, String userId);

    /**
     * 删除机构
     * @param id 主键
     */
    void deleteCompany(String id);
}

package com.proper.phip.sys.dept.service;

import com.proper.phip.sys.dept.entity.SysDeptEntity;

import java.util.List;

/**
 * 科室Service
 */
public interface SysDeptService {

    /**
     * 取得科室列表
     * @param companyRegionId 所属机构ID或分支机构ID
     * @return 科室列表
     * */
    List<SysDeptEntity> getDepts(String companyRegionId);

    /**
     * 保存科室
     * @param sysDeptEntity 科室实体
     * @param userId 操作人
     */
    void addDept(SysDeptEntity sysDeptEntity, String userId);

    /**
     * 修改科室
     * @param sysDeptEntity 科室实体
     * @param userId 操作人
     */
    void editDept(SysDeptEntity sysDeptEntity, String userId);

    /**
     * 删除科室
     * @param id 主键
     */
    void deleteDept(String id);
}

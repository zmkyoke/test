package com.proper.phip.sys.dictionary.service;

import com.proper.phip.sys.dictionary.entity.SysDictionaryItemEntity;
import com.proper.phip.sys.dictionary.entity.SysDictionaryTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典Service
 */
public interface SysDictionaryService {

    /**
     * 取得字典类别列表
     * @return 字典类别列表
     * */
    List<SysDictionaryTypeEntity> getDictionaryTypes();

    /**
     * 保存字典类别
     * @param sysDictionaryTypeEntity 字典类别实体
     * @param userId 操作人
     */
    void addDictionaryType(SysDictionaryTypeEntity sysDictionaryTypeEntity, String userId);

    /**
     * 修改字典类别
     * @param sysDictionaryTypeEntity 字典类别实体
     * @param userId 操作人
     */
    void editDictionaryType(SysDictionaryTypeEntity sysDictionaryTypeEntity, String userId);

    /**
     * 删除字典类别
     * @param id 主键
     */
    void deleteDictionaryType(String id);

    /**
     * 取得字典项目列表
     * @param typeId 字典类别ID
     * @return 字典项目列表
     * */
    List<SysDictionaryItemEntity> getDictionaryItems(String typeId);

    /**
     * 批量取得字典项目列表
     * @param typeCode 类别编码
     * @return 字典项目列表
     * */
    List<SysDictionaryItemEntity> getCacheDictionaryItems(String typeCode);

    /**
     * 保存字典项目
     * @param sysDictionaryItemEntity 字典项目实体
     * @param userId 操作人
     */
    void addDictionaryItem(SysDictionaryItemEntity sysDictionaryItemEntity, String userId);

    /**
     * 修改字典项目
     * @param sysDictionaryItemEntity 字典项目实体
     * @param userId 操作人
     */
    void editDictionaryItem(SysDictionaryItemEntity sysDictionaryItemEntity, String userId);
}

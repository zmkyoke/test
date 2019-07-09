package com.proper.phip.sys.dictionary.service;

import com.proper.phip.core.i18n.I18NService;
import com.proper.phip.core.restful.ReturnException;
import com.proper.phip.core.utils.PinyinUtils;
import com.proper.phip.sys.dictionary.entity.SysDictionaryItemEntity;
import com.proper.phip.sys.dictionary.entity.SysDictionaryTypeEntity;
import com.proper.phip.sys.dictionary.mapper.SysDictionaryItemMapper;
import com.proper.phip.sys.dictionary.mapper.SysDictionaryTypeMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {

    @Autowired
    private I18NService i18NService;

    @Autowired
    private SysDictionaryTypeMapper sysDictionaryTypeMapper;

    @Autowired
    private SysDictionaryItemMapper sysDictionaryItemMapper;

    @Override
    public List<SysDictionaryTypeEntity> getDictionaryTypes() {
        return sysDictionaryTypeMapper.getDictionaryTypes(null);
    }

    @Override
    public void addDictionaryType(SysDictionaryTypeEntity sysDictionaryTypeEntity, String userId) {
        List<SysDictionaryTypeEntity> types = sysDictionaryTypeMapper.getDictionaryTypes(sysDictionaryTypeEntity.getTypeCode());
        if (types.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.code-repeat"));
        }
        if (StringUtils.isEmpty(sysDictionaryTypeEntity.getTypeNamePy())) {
            sysDictionaryTypeEntity.setTypeNamePy(PinyinUtils.getPinYinHeadChar(sysDictionaryTypeEntity.getTypeName()));
        }
        sysDictionaryTypeEntity.initAddInfo(userId, new Date());
        sysDictionaryTypeMapper.insertSelective(sysDictionaryTypeEntity);
    }

    @Override
    public void editDictionaryType(SysDictionaryTypeEntity sysDictionaryTypeEntity, String userId) {
        if (StringUtils.isEmpty(sysDictionaryTypeEntity.getTypeNamePy())) {
            sysDictionaryTypeEntity.setTypeNamePy(PinyinUtils.getPinYinHeadChar(sysDictionaryTypeEntity.getTypeName()));
        }
        sysDictionaryTypeEntity.initModifyInfo(userId, new Date());
        sysDictionaryTypeMapper.updateByPrimaryKeySelective(sysDictionaryTypeEntity);
    }

    @Override
    public void deleteDictionaryType(String id) {
        // 验证是否已使用
        SysDictionaryTypeEntity sysDictionaryTypeEntity = new SysDictionaryTypeEntity();
        sysDictionaryTypeEntity.setParentId(id);
        List<SysDictionaryTypeEntity> types = sysDictionaryTypeMapper.select(sysDictionaryTypeEntity);
        if (types.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.delete.down"));
        }
        SysDictionaryItemEntity sysDictionaryItemEntity = new SysDictionaryItemEntity();
        sysDictionaryItemEntity.setTypeId(id);
        List<SysDictionaryItemEntity> items = sysDictionaryItemMapper.select(sysDictionaryItemEntity);
        if (items.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.delete.used"));
        }

        sysDictionaryTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysDictionaryItemEntity> getDictionaryItems(String typeId) {
        return sysDictionaryItemMapper.getDictionaryItems(typeId, null, null, null);
    }

    @Override
    @Cacheable(value = "phip.dictionary.item")
    public List<SysDictionaryItemEntity> getCacheDictionaryItems(String typeCode) {
        return sysDictionaryItemMapper.getDictionaryItems(null, typeCode, null, null);
    }

    @Override
    @CacheEvict(value = "phip.dictionary.item", key = "#sysDictionaryItemEntity.typeCode")
    public void addDictionaryItem(SysDictionaryItemEntity sysDictionaryItemEntity, String userId) {
        List<SysDictionaryItemEntity> items = sysDictionaryItemMapper.getDictionaryItems(
            sysDictionaryItemEntity.getTypeId(), null, sysDictionaryItemEntity.getItemCode(), null);
        if (items.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.code-repeat"));
        }

        if (StringUtils.isEmpty(sysDictionaryItemEntity.getItemNamePy())) {
            sysDictionaryItemEntity.setItemNamePy(PinyinUtils.getPinYinHeadChar(sysDictionaryItemEntity.getItemName()));
        }
        sysDictionaryItemEntity.initAddInfo(userId, new Date());
        sysDictionaryItemMapper.insertSelective(sysDictionaryItemEntity);
    }

    @Override
    @CacheEvict(value = "phip.dictionary.item", key = "#sysDictionaryItemEntity.typeCode")
    public void editDictionaryItem(SysDictionaryItemEntity sysDictionaryItemEntity, String userId) {
        if (StringUtils.isNotEmpty(sysDictionaryItemEntity.getItemName())
                && StringUtils.isEmpty(sysDictionaryItemEntity.getItemNamePy())) {
            sysDictionaryItemEntity.setItemNamePy(PinyinUtils.getPinYinHeadChar(sysDictionaryItemEntity.getItemName()));
        }
        sysDictionaryItemEntity.initModifyInfo(userId, new Date());
        sysDictionaryItemMapper.updateByPrimaryKeySelective(sysDictionaryItemEntity);
    }
}

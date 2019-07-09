package com.proper.phip.sys.param.service;

import com.proper.phip.core.i18n.I18NService;
import com.proper.phip.core.restful.ReturnException;
import com.proper.phip.core.utils.PinyinUtils;
import com.proper.phip.sys.param.entity.SysParamItemEntity;
import com.proper.phip.sys.param.entity.SysParamValueEntity;
import com.proper.phip.sys.param.mapper.SysParamItemMapper;
import com.proper.phip.sys.param.mapper.SysParamValueMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class SysParamServiceImpl implements SysParamService {

    @Autowired
    private I18NService i18NService;

    @Autowired
    private SysParamItemMapper sysParamItemMapper;

    @Autowired
    private SysParamValueMapper sysParamValueMapper;

    @Autowired
    private SysParamCacheService sysParamCacheService;

    @Override
    public List<SysParamItemEntity> getParamItems() {
        return sysParamItemMapper.getParamItems(null);
    }

    @Override
    public void addParamItem(SysParamItemEntity sysParamItemEntity, String userId) {
        List<SysParamItemEntity> paramItems = sysParamItemMapper.getParamItems(sysParamItemEntity.getItemCode());
        if (paramItems.size() > 0) {
            throw new ReturnException(i18NService.getMessage("msg.return.error.edit.code-repeat"));
        }

        if (StringUtils.isEmpty(sysParamItemEntity.getItemNamePy())) {
            sysParamItemEntity.setItemNamePy(PinyinUtils.getPinYinHeadChar(sysParamItemEntity.getItemName()));
        }
        sysParamItemEntity.initAddInfo(userId, new Date());
        sysParamItemMapper.insertSelective(sysParamItemEntity);
    }

    @Override
    public void editParamItem(SysParamItemEntity sysParamItemEntity, String userId) {
        if (StringUtils.isEmpty(sysParamItemEntity.getItemNamePy())) {
            sysParamItemEntity.setItemNamePy(PinyinUtils.getPinYinHeadChar(sysParamItemEntity.getItemName()));
        }
        sysParamItemEntity.initModifyInfo(userId, new Date());
        sysParamItemMapper.updateByPrimaryKeySelective(sysParamItemEntity);
    }

    @Override
    public List<SysParamValueEntity> getParamValues() {
        return sysParamValueMapper.selectAll();
    }

    @Override
    public void editParamValues(Map<String, String> map, String userId) {
        Date date = new Date();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            SysParamValueEntity queryEntity = new SysParamValueEntity();
            queryEntity.setItemCode(entry.getKey());
            SysParamValueEntity sysParamValueEntity = sysParamValueMapper.selectOne(queryEntity);
            if (sysParamValueEntity == null) {
                SysParamValueEntity sysParamValueNew = new SysParamValueEntity();
                sysParamValueNew.setItemCode(entry.getKey());
                sysParamValueNew.setParamValue(entry.getValue());
                sysParamValueNew.initAddInfo(userId, date);
                sysParamValueMapper.insertSelective(sysParamValueNew);
            } else {
                if (!sysParamValueEntity.getParamValue().equals(entry.getValue())) {
                    sysParamValueEntity.setParamValue(entry.getValue());
                    sysParamValueEntity.initModifyInfo(userId, date);
                    sysParamValueMapper.updateByPrimaryKeySelective(sysParamValueEntity);
                }
            }
            sysParamCacheService.deleteCacheParamValue(entry.getKey());
        }
    }

    @Override
    public String getParamValueString(String itemCode) {
        String value = "";
        SysParamValueEntity valueEntity = sysParamCacheService.getCacheParamValue(itemCode);
        if (valueEntity != null) {
            value = valueEntity.getParamValue();
        }
        return value;
    }

    @Override
    public boolean getParamValueBoolean(String itemCode) {
        boolean value = false;
        SysParamValueEntity valueEntity = sysParamCacheService.getCacheParamValue(itemCode);
        if (valueEntity != null) {
            value = "true".equals(valueEntity.getParamValue());
        }
        return value;
    }

    @Override
    public int getParamValueInt(String itemCode) {
        int value = 0;
        SysParamValueEntity valueEntity = sysParamCacheService.getCacheParamValue(itemCode);
        if (valueEntity != null) {
            value = Integer.parseInt(valueEntity.getParamValue());
        }
        return value;
    }

    @Override
    public double getParamValueDouble(String itemCode) {
        double value = 0.0;
        SysParamValueEntity valueEntity = sysParamCacheService.getCacheParamValue(itemCode);
        if (valueEntity != null) {
            value = Double.parseDouble(valueEntity.getParamValue());
        }
        return value;
    }
}

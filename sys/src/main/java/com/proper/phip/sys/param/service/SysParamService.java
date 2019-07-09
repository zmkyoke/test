package com.proper.phip.sys.param.service;

import com.proper.phip.sys.param.entity.SysParamItemEntity;
import com.proper.phip.sys.param.entity.SysParamValueEntity;

import java.util.List;
import java.util.Map;

/**
 * 参数Service
 */
public interface SysParamService {

    /**
     * 取得参数项目列表
     * @return 参数项目列表
     * */
    List<SysParamItemEntity> getParamItems();

    /**
     * 保存参数项目
     * @param sysParamItemEntity 参数项目实体
     * @param userId 操作人
     */
    void addParamItem(SysParamItemEntity sysParamItemEntity, String userId);

    /**
     * 修改参数项目
     * @param sysParamItemEntity 参数项目实体
     * @param userId 操作人
     */
    void editParamItem(SysParamItemEntity sysParamItemEntity, String userId);

    /**
     * 取得参数值列表
     * @return 参数值列表
     */
    List<SysParamValueEntity> getParamValues();

    /**
     * 修改参数值
     * @param map 参数值集合
     * @param userId 操作人
     */
    void editParamValues(Map<String, String> map, String userId);

    /**
     * 取得参数值
     * @param itemCode 项目编码
     * @return 参数值列表
     */
    String getParamValueString(String itemCode);

    /**
     * 取得参数值
     * @param itemCode 项目编码
     * @return 参数值列表
     */
    boolean getParamValueBoolean(String itemCode);

    /**
     * 取得参数值
     * @param itemCode 项目编码
     * @return 参数值列表
     */
    int getParamValueInt(String itemCode);

    /**
     * 取得参数值
     * @param itemCode 项目编码
     * @return 参数值列表
     */
    double getParamValueDouble(String itemCode);
}

package com.proper.phip.sys.param.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 参数项目表
 */
@Table(name = "PHIP_SYS_PARAM_ITEM")
public class SysParamItemEntity extends BaseEntity {

    // 项目编码
    private String itemCode;

    // 项目名称
    private String itemName;

    // 项目名称拼音
    private String itemNamePy;

    // 上级ID
    private String parentId;

    // 节点类型  PATH:路径;ITEM:项目
    private String nodeType;

    // 输入方式  DIC:数据字典;INP:文本框;CHE:选择框
    private String inputMode;

    // 使用数据字典类别编码
    private String dicTypeCode;

    // 输入类型验证  STR:字符串;INT:整数;NUM:数字
    private String inputType;

    // 输入描述
    private String describe;

    // 备注
    private String remark;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNamePy() {
        return itemNamePy;
    }

    public void setItemNamePy(String itemNamePy) {
        this.itemNamePy = itemNamePy;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getInputMode() {
        return inputMode;
    }

    public void setInputMode(String inputMode) {
        this.inputMode = inputMode;
    }

    public String getDicTypeCode() {
        return dicTypeCode;
    }

    public void setDicTypeCode(String dicTypeCode) {
        this.dicTypeCode = dicTypeCode;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

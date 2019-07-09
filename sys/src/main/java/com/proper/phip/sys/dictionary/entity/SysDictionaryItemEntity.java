package com.proper.phip.sys.dictionary.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 数据字典项目表
 */
@Table(name = "PHIP_SYS_DICTIONARY_ITEM")
public class SysDictionaryItemEntity extends BaseEntity {

    // 项目编码
    private String itemCode;

    // 项目名称
    private String itemName;

    // 项目名称拼音
    private String itemNamePy;

    // 类别ID  表：PHIP_SYS_DICTIONARY_TYPE
    private String typeId;

    // 类别编码  表：PHIP_SYS_DICTIONARY_TYPE
    private String typeCode;

    // 状态 1:启用;0:停用
    private String status;

    // 备注
    private String remark;

    // 序号
    private String seqNum;

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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(String seqNum) {
        this.seqNum = seqNum;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}

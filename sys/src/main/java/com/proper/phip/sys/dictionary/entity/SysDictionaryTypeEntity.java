package com.proper.phip.sys.dictionary.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 数据字典类别表
 */
@Table(name = "PHIP_SYS_DICTIONARY_TYPE")
public class SysDictionaryTypeEntity extends BaseEntity {

    // 类别编码
    private String typeCode;

    // 类别名称
    private String typeName;

    // 类别名称拼音
    private String typeNamePy;

    // 上级ID
    private String parentId;

    // 节点类型  PATH:路径;TYPE:类别
    private String nodeType;

    // 编码规则
    private String codeRule;

    // 备注
    private String remark;

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeNamePy() {
        return typeNamePy;
    }

    public void setTypeNamePy(String typeNamePy) {
        this.typeNamePy = typeNamePy;
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

    public String getCodeRule() {
        return codeRule;
    }

    public void setCodeRule(String codeRule) {
        this.codeRule = codeRule;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

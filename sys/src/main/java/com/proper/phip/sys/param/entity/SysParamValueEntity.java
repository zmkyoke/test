package com.proper.phip.sys.param.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 参数值表
 */
@Table(name = "PHIP_SYS_PARAM_VALUE")
public class SysParamValueEntity extends BaseEntity {

    // 参数项目编码  表：PHIP_SYS_PARAM_ITEM
    private String itemCode;

    // 参数项目编码
    private String paramValue;

    // 适用机构ID 为空时全机构有效
    private String companyRegionId;

    // 适用机构类别  COM:机构;REG:分支机构
    private String companyRegionType;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getCompanyRegionId() {
        return companyRegionId;
    }

    public void setCompanyRegionId(String companyRegionId) {
        this.companyRegionId = companyRegionId;
    }

    public String getCompanyRegionType() {
        return companyRegionType;
    }

    public void setCompanyRegionType(String companyRegionType) {
        this.companyRegionType = companyRegionType;
    }
}

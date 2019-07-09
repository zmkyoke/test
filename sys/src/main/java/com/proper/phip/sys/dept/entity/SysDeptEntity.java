package com.proper.phip.sys.dept.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 科室表
 */
@Table(name = "PHIP_SYS_DEPT")
public class SysDeptEntity extends BaseEntity {

    // 科室编码
    private String deptCode;

    // 科室名称
    private String deptName;

    // 科室名称拼音
    private String deptNamePy;

    // 上级科室id
    private String parentId;

    // 科室名称简称
    private String deptNameAbs;

    // 所属机构ID或分支机构ID
    private String companyRegionId;

    // 门诊科室  1:是;0:否
    private String opdFitFlg;

    // 急诊科室  1:是;0:否
    private String emgFitFlg;

    // 住院科室  1:是;0:否
    private String ipdFitFlg;

    // 体检科室  1:是;0:否
    private String hrmFitFlg;

    // 序号
    private Integer seqNum;

    // 唯一科室编码
    private String uniqueCode;

    // 上级机构类别  COM:机构;REG:分支机构
    private String parentType;

    // 状态  1:正常;0:删除
    private String status;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptNamePy() {
        return deptNamePy;
    }

    public void setDeptNamePy(String deptNamePy) {
        this.deptNamePy = deptNamePy;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDeptNameAbs() {
        return deptNameAbs;
    }

    public void setDeptNameAbs(String deptNameAbs) {
        this.deptNameAbs = deptNameAbs;
    }

    public String getCompanyRegionId() {
        return companyRegionId;
    }

    public void setCompanyRegionId(String companyRegionId) {
        this.companyRegionId = companyRegionId;
    }

    public String getOpdFitFlg() {
        return opdFitFlg;
    }

    public void setOpdFitFlg(String opdFitFlg) {
        this.opdFitFlg = opdFitFlg;
    }

    public String getEmgFitFlg() {
        return emgFitFlg;
    }

    public void setEmgFitFlg(String emgFitFlg) {
        this.emgFitFlg = emgFitFlg;
    }

    public String getIpdFitFlg() {
        return ipdFitFlg;
    }

    public void setIpdFitFlg(String ipdFitFlg) {
        this.ipdFitFlg = ipdFitFlg;
    }

    public String getHrmFitFlg() {
        return hrmFitFlg;
    }

    public void setHrmFitFlg(String hrmFitFlg) {
        this.hrmFitFlg = hrmFitFlg;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.proper.phip.sys.user.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 用户表
 */
@Table(name = "PHIP_SYS_USER")
public class SysUserEntity extends BaseEntity {

    // 用户名
    private String username;

    // 密码
    private String password;

    // 状态  1：启用；0：停用
    private String status;

    // 姓名
    private String name;

    // 姓名拼音
    private String namePy;

    // 性别编码  数据字典:SYS_SEX
    private String sexCode;

    // 出生日期
    private String birthDate;

    // 证件类别编码  数据字典:SYS_IDTYPE
    private String idType;

    // 证件号码
    private String idNo;

    // 电子邮箱
    private String email;

    // 备注
    private String remark;

    // 联系电话
    private String phone;

    // 职称  数据字典:SYS_TITLE
    private String titleCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePy() {
        return namePy;
    }

    public void setNamePy(String namePy) {
        this.namePy = namePy;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }
}

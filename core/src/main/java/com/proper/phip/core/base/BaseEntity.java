package com.proper.phip.core.base;

import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类基类
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @KeySql(order = ORDER.BEFORE, genId = UUIdGenId.class)
    private String id;

    // 创建人ID
    private String optCreateUser;

    // 创建时间
    private Date optCreateDate;

    // 最后修改人ID
    private String optModifyUser;

    // 最后修改时间
    private Date optModifyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptCreateUser() {
        return optCreateUser;
    }

    public void setOptCreateUser(String optCreateUser) {
        this.optCreateUser = optCreateUser;
    }

    public Date getOptCreateDate() {
        return optCreateDate == null ? null : (Date)optCreateDate.clone();
    }

    public void setOptCreateDate(Date optCreateDate) {
        this.optCreateDate = (optCreateDate == null ? null : (Date)optCreateDate.clone());
    }

    public String getOptModifyUser() {
        return optModifyUser;
    }

    public void setOptModifyUser(String optModifyUser) {
        this.optModifyUser = optModifyUser;
    }

    public Date getOptModifyDate() {
        return optModifyDate == null ? null : (Date)optModifyDate.clone();
    }

    public void setOptModifyDate(Date optModifyDate) {
        this.optModifyDate = (optModifyDate == null ? null : (Date)optModifyDate.clone());
    }

    public void initAddInfo(String userId, Date date) {
        setOptCreateDate(date);
        setOptCreateUser(userId);
        setOptModifyDate(date);
        setOptModifyUser(userId);
    }

    public void initModifyInfo(String userId, Date date) {
        setOptModifyDate(date);
        setOptModifyUser(userId);
    }
}

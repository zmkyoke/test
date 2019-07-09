package com.proper.phip.sys.menu.entity;

import com.proper.phip.core.base.BaseEntity;

import javax.persistence.Table;

/**
 * 菜单表
 */
@Table(name = "PHIP_SYS_MENU")
public class SysMenuEntity extends BaseEntity {

    // 菜单编码
    private String code;

    // 菜单名称
    private String name;

    // 菜单名称拼音
    private String namePy;

    // 上级菜单ID
    private String parentId;

    // 节点类型  PATH:路径;PAGE:页面;FUNC:页面内部项
    private String nodeType;

    // 菜单名称简称
    private String nameAbs;

    // 路由地址
    private String path;

    // 图标
    private String icon;

    // 序号
    private Integer seqNum;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public String getNameAbs() {
        return nameAbs;
    }

    public void setNameAbs(String nameAbs) {
        this.nameAbs = nameAbs;
    }
}

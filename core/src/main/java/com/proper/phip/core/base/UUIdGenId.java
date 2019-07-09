package com.proper.phip.core.base;

import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.EntityTable;
import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

/**
 * 表主键UUID生成类
 */
public class UUIdGenId implements GenId<String> {

    @Override
    public String genId(String table, String column) {
        return UUID.randomUUID().toString();
    }
}

package com.proper.phip.core.utils.dto;

import com.proper.phip.core.utils.enums.MyCellTypeEnum;
import org.apache.poi.ss.usermodel.CellType;

public class PoiCellDTO {

    public PoiCellDTO() {}

    public PoiCellDTO(String name) {
        this.name = name;
        this.width = 0;
    }

    public PoiCellDTO(String name, int width) {
        this.name = name;
        this.width = width;
    }

    public PoiCellDTO(Object value, int width, MyCellTypeEnum cellType) {
        this.value = value;
        this.width = width;
        this.cellType = cellType;
    }

    public PoiCellDTO(Object value, MyCellTypeEnum cellType) {
        this.value = value;
        this.cellType = cellType;
    }

    // 列名称
    private String name;

    // 列值
    private Object value;

    // 列宽
    private int width;

    // 单元格类型
    private MyCellTypeEnum cellType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public MyCellTypeEnum getCellType() {
        return cellType;
    }

    public void setCellType(MyCellTypeEnum cellType) {
        this.cellType = cellType;
    }
}

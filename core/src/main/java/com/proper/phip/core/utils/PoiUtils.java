package com.proper.phip.core.utils;

import com.proper.phip.core.utils.dto.PoiCellDTO;
import com.proper.phip.core.utils.enums.MyCellTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * POI工具类
 */
public class PoiUtils {

    /**
     * 创建excel
     * @param sheetName 页名称
     * @param heads 头内容
     * @param datas 数据内容
     * @return excel
     */
    public HSSFWorkbook createExcel(String sheetName, List<PoiCellDTO> heads, List<List<PoiCellDTO>> datas) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);

        CellStyle headCellStyle = wb.createCellStyle();
        headCellStyle.setAlignment(HorizontalAlignment.CENTER);
        HSSFFont headFont = wb.createFont();
        headFont.setBold(true);
        headCellStyle.setFont(headFont);

        CellStyle dateCellStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        dateCellStyle.setDataFormat(format.getFormat("yyyy/m/d"));

        HSSFRow row = null;
        int rowIndex = 0;

        if (heads != null && heads.size() > 0) {
            row = sheet.createRow(rowIndex++);
            for (int i = 0; i < heads.size(); i++) {
                PoiCellDTO poiCellDTO = heads.get(i);
                Cell cell = row.createCell(i);
                cell.setCellStyle(headCellStyle);
                cell.setCellValue(poiCellDTO.getName());
                if (poiCellDTO.getWidth() > 0) {
                    sheet.setColumnWidth(i, poiCellDTO.getWidth());
                }
            }
        }

        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                List<PoiCellDTO> rowDatas = datas.get(i);
                row = sheet.createRow(rowIndex++);
                for (int j = 0; j < rowDatas.size(); j++) {
                    PoiCellDTO poiCellDTO = rowDatas.get(j);
                    Cell cell = row.createCell(j);
                    if (poiCellDTO.getValue() != null) {
                        if (MyCellTypeEnum.STRING.equals(poiCellDTO.getCellType())) {
                            cell.setCellValue(poiCellDTO.getValue().toString());
                        }
                        if (MyCellTypeEnum.DOUBLE.equals(poiCellDTO.getCellType())) {
                            cell.setCellValue(Double.parseDouble(poiCellDTO.getValue().toString()));
                        }
                        if (MyCellTypeEnum.DATE.equals(poiCellDTO.getCellType())) {
                            cell.setCellValue((Date) poiCellDTO.getValue());
                            cell.setCellStyle(dateCellStyle);
                        }
                    } else {
                        cell.setCellValue("");
                    }
                    if (poiCellDTO.getWidth() > 0) {
                        sheet.setColumnWidth(poiCellDTO.getWidth(), i);
                    }
                }
            }
        }
        return wb;
    }

    public String getCellValueString(HSSFCell cell) {
        if (cell != null) {
            CellType cellType = cell.getCellType();
            if (CellType.STRING.equals(cellType)) {
                return cell.getStringCellValue().trim();
            } else if (CellType.NUMERIC.equals(cellType)) {
                DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
                return decimalFormat.format(cell.getNumericCellValue());
            } else {
                return cell.toString().trim();
            }
        }
        return "";
    }

    public Date getCellValueDate(HSSFCell cell) {
        if (cell != null) {
            return cell.getDateCellValue();
        }
        return null;
    }

    public String getCellValueFDate(HSSFCell cell, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        if (cell != null) {
            return df.format(cell.getDateCellValue());
        }
        return null;
    }

    public Double getCellValueDouble(HSSFCell cell) {
        if (cell != null) {
            return cell.getNumericCellValue();
        }
        return null;
    }
}

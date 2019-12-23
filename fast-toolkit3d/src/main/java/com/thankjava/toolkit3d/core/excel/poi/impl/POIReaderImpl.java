package com.thankjava.toolkit3d.core.excel.poi.impl;

import com.thankjava.toolkit.bean.utils.TimeType;
import com.thankjava.toolkit.core.utils.TimeUtil;
import com.thankjava.toolkit3d.bean.excel.poi.DefaultNumberType;
import com.thankjava.toolkit3d.bean.excel.poi.FormatCellValueType;
import com.thankjava.toolkit3d.core.excel.poi.POIReader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 处理Excel读取
 * @Author: acexy@thankjava.com
 * @Date: 2019-06-04
 **/
class POIReaderImpl implements POIReader {

    // --
    private boolean isSkipFirstRow = false;
    private int sheetIndex = 0;
    private String defaultValue = "";
    private int maxRowNumber = -1;
    private int maxColumnNumber = -1;
    private boolean autoTrim = false;
    private Map<String, FormatCellValueType> formatConfig = new HashMap<>();
    private TimeType defaultTimeFormatType = TimeType.DEFAULT;
    private boolean ignoreDataFormatError = false;
    private DefaultNumberType defaultNumberType = DefaultNumberType.DOUBLE;

    // --

    Workbook workbook;
    Sheet sheet;

    POIReaderImpl(String filePath) {
        try {
            workbook = new HSSFWorkbook(new FileInputStream(filePath));
        } catch (IOException | OfficeXmlFileException e) {
            if (e instanceof OfficeXmlFileException) {
                try {
                    workbook = new XSSFWorkbook(new FileInputStream(filePath));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                e.printStackTrace();
            }
        }
    }

    private FormatCellValueType getCellValueType(int rowIndex, int columnIndex, Cell cell) {
        FormatCellValueType type = formatConfig.get(rowIndex + ";" + columnIndex);
        if (type == null) {
            type = formatConfig.get("*;" + columnIndex);
        }
        if (type == null) {
            type = formatConfig.get(rowIndex + ";*");
        }
        return type;
    }

    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    private String getCellValue(int rowIndex, int columnIndex, Cell cell) {

        if (cell == null) {
            return defaultValue;
        }

        String value = defaultValue;

        FormatCellValueType formatCellValueType = getCellValueType(rowIndex, columnIndex, cell);

        if (formatCellValueType == null) {
            switch (cell.getCellTypeEnum()) {
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case NUMERIC:
                    switch (defaultNumberType) {
                        case DOUBLE:
                            value = String.valueOf(cell.getNumericCellValue());
                            break;
                        case INTEGER:
                            value = String.valueOf((int) cell.getNumericCellValue());
                        default:
                            value ="";
                            break;
                    }
                    break;
                case FORMULA:
                    value = cell.getCellFormula();
                    break;
                case BLANK:
                    value = defaultValue;
                    break;
                case ERROR:
                    break;
                default:
                    value = cell.getStringCellValue();
                    if (value != null && autoTrim) {
                        value = value.trim();
                    }
            }
        } else {
            // 命中用户自定义数据格式化规则
            try {
                switch (formatCellValueType) {
                    case DATE:
                        value = TimeUtil.formatDate(defaultTimeFormatType, cell.getDateCellValue());
                        break;
                    case NUMBER_DOUBLE:
                        value = String.valueOf(cell.getNumericCellValue());
                        break;
                    case NUMBER_INTEGER:
                        value = String.valueOf((long) cell.getNumericCellValue());
                        break;
                    case STRING:
                        value = cell.getStringCellValue();
                        if (value != null && autoTrim) {
                            value = value.trim();
                        }
                    default:
                        value = "";
                        break;
                }
            } catch (Throwable e) {
                if (!ignoreDataFormatError) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    @Override
    public List<String[]> readAll() {

        List<String[]> data = new ArrayList<>();

        sheet = workbook.getSheetAt(sheetIndex);

        int maxRowNumber = this.maxRowNumber < 0 ? sheet.getLastRowNum() : this.maxRowNumber;

        int maxColumnNumber;

        Row row;
        Cell cell;
        String[] columns = null;

        for (int rowIndex = 0; rowIndex <= maxRowNumber; rowIndex++) {

            if (isSkipFirstRow && rowIndex == 0) {
                continue;
            }

            row = sheet.getRow(rowIndex);

            maxColumnNumber = this.maxColumnNumber < 0 ? row.getLastCellNum() : this.maxColumnNumber;
            columns = new String[maxColumnNumber + 1];

            for (int columnIndex = 0; columnIndex <= maxColumnNumber; columnIndex++) {
                if (row == null) {
                    columns[columnIndex] = defaultValue;
                } else {
                    cell = row.getCell(columnIndex);
                    columns[columnIndex] = getCellValue(rowIndex, columnIndex, cell);
                }
            }

            data.add(columns);
        }

        return data;
    }

    @Override
    public String getPositionCellValue(int rowNumber, int columnNumber) {

        if (rowNumber < 1 || columnNumber < 1) {
            return defaultValue;
        }

        Row row = sheet.getRow(rowNumber - 1);
        if (row == null) {
            return defaultValue;
        }

        Cell cell = row.getCell(columnNumber - 1);
        if (cell == null) {
            return defaultValue;
        }

        return getCellValue(rowNumber - 1, columnNumber - 1, cell);
    }

}

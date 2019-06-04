package com.thankjava.toolkit3d.core.excel.poi;


import com.thankjava.toolkit.bean.utils.TimeType;
import com.thankjava.toolkit.core.reflect.ReflectUtil;
import com.thankjava.toolkit.core.utils.TimeUtil;
import com.thankjava.toolkit3d.bean.excel.poi.DefaultNumberType;
import com.thankjava.toolkit3d.bean.excel.poi.FormatCellValueType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desc: POI Excel处理工具
 * @Author: acexy@thankjava.com
 * @Date: 2019-06-03
 **/
public class POIExcelBuilder {

    /**
     * 是否忽略第一行读取
     */
    private boolean isSkipFirstRow = false;

    /**
     * 读取的sheet
     */
    private int sheetIndex = 0;

    /**
     * 读取异常或者为空的默认值
     */
    private String defaultValue = "";

    /**
     * 读取的最大行数 从1开始
     */
    private int maxRowNumber = -1;

    /**
     * 读取的最大列数 从1开始
     */
    private int maxColumnNumber = -1;

    /**
     * 设置值自动使用trim函数去掉空格
     */
    private boolean autoTrim = false;

    /**
     * 自定义格式类型配置
     */
    private Map<String, FormatCellValueType> formatConfig = new HashMap<>();

    /**
     * 默认时间格式格式化
     */
    private TimeType defaultTimeFormatType = TimeType.DEFAULT;

    /**
     * 忽略参数读取错误
     */
    private boolean ignoreDataFormatError = false;

    /**
     * 用于指定获取单元格数据，将其格式化指定格式类型
     */
    private DefaultNumberType defaultNumberType = DefaultNumberType.DOUBLE;


    // ------

    private String filePath;


    /**
     * POIReader构建器
     *
     * @param filePath
     */
    public POIExcelBuilder(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 设置忽略Excel第一行值读取 默认不忽略
     *
     * @return
     */
    public POIExcelBuilder setSkipFirstRow() {
        isSkipFirstRow = true;
        return this;
    }

    /**
     * 设置读取哪一个sheet 默认获取第一个sheet
     *
     * @param index 从1开始
     * @return
     */
    public POIExcelBuilder setReadSheetIndex(int index) {
        if (index - 1 >= 0) {
            sheetIndex = index - 1;
        }
        return this;
    }

    /**
     * 设置读取异常或者为空时的默认值
     *
     * @param value
     * @return
     */
    public POIExcelBuilder setDefaultValue(String value) {
        defaultValue = value;
        return this;
    }

    /**
     * 设置读取的最大行数 从1开始
     *
     * @param number
     * @return
     */
    public POIExcelBuilder setMaxRowNumber(int number) {
        if (number >= 1) {
            this.maxRowNumber = number - 1;
        }
        return this;
    }

    /**
     * 设置读取的最大列数 从1开始
     *
     * @param number
     * @return
     */
    public POIExcelBuilder setMaxColumnNumber(int number) {
        if (number >= 1) {
            this.maxColumnNumber = number - 1;
        }
        return this;
    }

    /**
     * 设置单元格值自动使用trim函数去除空格
     *
     * @return
     */
    public POIExcelBuilder setValueAutoTrim() {
        this.autoTrim = true;
        return this;
    }


    /**
     * 指定某一单元格数据格式类型
     * 同一个单元格格式化权重: 定点单元格格式化 > 指定列 > 指定行
     *
     * @param rowNumber     从1开始
     * @param columnNumber  从1开始
     * @param cellValueType
     * @return
     */
    public POIExcelBuilder setPositionFormatCellValueType(int rowNumber, int columnNumber, FormatCellValueType cellValueType) {
        if (columnNumber >= 1 && rowNumber >= 1) {
            formatConfig.put((rowNumber - 1) + ";" + (columnNumber - 1), cellValueType);
        }
        return this;
    }

    /**
     * 指定某一列的单元格数据格式类型
     * 同一个单元格格式化权重: 定点单元格格式化 > 指定列 > 指定行
     *
     * @param rowNumber     从1开始
     * @param cellValueType
     * @return
     */
    public POIExcelBuilder setRowFormatCellValueType(int rowNumber, FormatCellValueType cellValueType) {
        if (rowNumber >= 1) {
            formatConfig.put((rowNumber - 1) + ";*", cellValueType);
        }
        return this;
    }

    /**
     * 忽略数据类型读取格式化异常问题 如果错误则取默认值
     *
     * @return
     */
    public POIExcelBuilder setIgnoreDataFormatError() {
        this.ignoreDataFormatError = true;
        return this;
    }

    /**
     * 设置莫一行的单元格数据格式类型
     * 同一个单元格格式化权重: 定点单元格格式化 > 指定列 > 指定行
     *
     * @param columnNumber  从1开始
     * @param cellValueType
     * @return
     */
    public POIExcelBuilder setColumnFormatCellValueType(int columnNumber, FormatCellValueType cellValueType) {
        if (columnNumber >= 1) {
            formatConfig.put("*;" + (columnNumber - 1), cellValueType);
        }
        return this;
    }

    /**
     * 设置默认时间格式化格式
     * @param timeType
     * @return
     */
    public POIExcelBuilder setDefaultTimeFormatType(TimeType timeType) {
        if (timeType != null) defaultTimeFormatType = timeType;
        return this;
    }

    /**
     * 创建POIReader API 实例
     *
     * @return
     */
    public POIReader buildReader() {

        try {

            Class clazz = Class.forName("com.thankjava.toolkit3d.core.excel.poi.impl.POIReaderImpl");
            Constructor constructor = clazz.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            Object poiReader = constructor.newInstance(filePath);

            // 设置初始值
            ReflectUtil.setFieldVal(poiReader, "sheetIndex", sheetIndex);
            ReflectUtil.setFieldVal(poiReader, "isSkipFirstRow", isSkipFirstRow);
            ReflectUtil.setFieldVal(poiReader, "defaultValue", defaultValue);
            ReflectUtil.setFieldVal(poiReader, "maxRowNumber", maxRowNumber);
            ReflectUtil.setFieldVal(poiReader, "maxColumnNumber", maxColumnNumber);
            ReflectUtil.setFieldVal(poiReader, "autoTrim", autoTrim);
            ReflectUtil.setFieldVal(poiReader, "formatConfig", formatConfig);
            ReflectUtil.setFieldVal(poiReader, "defaultTimeFormatType", defaultTimeFormatType);
            ReflectUtil.setFieldVal(poiReader, "ignoreDataFormatError", ignoreDataFormatError);
            ReflectUtil.setFieldVal(poiReader, "defaultNumberType", defaultNumberType);

            return (POIReader) poiReader;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}

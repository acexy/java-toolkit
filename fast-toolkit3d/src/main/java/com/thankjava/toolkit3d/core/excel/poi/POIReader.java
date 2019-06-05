package com.thankjava.toolkit3d.core.excel.poi;

import java.util.List;

/**
 * @Desc: POI Excel Reader
 * @Author: acexy@thankjava.com
 * @Date: 2019-06-04
 **/
public interface POIReader {

    /**
     * 将excel文件读取为内存List<String[]>数据
     *
     * @return
     */
    List<String[]> readAll();

    /**
     * 获取指定单元格的值
     *
     * @param rowNumber 从1开始
     * @param columnNumber  从1开始
     * @return
     */
    String getPositionCellValue(int rowNumber, int columnNumber);
}

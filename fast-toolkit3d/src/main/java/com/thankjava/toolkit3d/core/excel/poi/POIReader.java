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
     * @return
     */
    List<String[]> readAll();

    String getPositionValue(int rowIndex,int cellIndex);
}

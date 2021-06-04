package com.thankjava.toolkit.core.utils;

import com.thankjava.toolkit.bean.utils.TimeType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 对java Date 进行的常规操作
 * <p>Function: TimeUtil</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2014年12月10日 下午8:40:49
 */
public final class TimeUtil {

    private static final Map<String, SimpleDateFormat> SIMPLE_DATE_FORMAT_MAP = new HashMap<>();

    private TimeUtil() {
    }

    /**
     * 将date Str 转换为 Date 类型
     * <p>Function: parseDate</p>
     * <p>Description: </p>
     *
     * @param timeType
     * @param date
     * @return
     * @author acexy@thankjava.com
     * @date 2015年6月18日 上午9:59:42
     */
    public static Date parseDate(TimeType timeType, String date) {
        return parseDate(timeType.type, date);
    }

    /**
     * 将date Str 转换为 Date 类型
     *
     * @param timeTypeString
     * @param date
     * @return
     */
    public static Date parseDate(String timeTypeString, String date) {
        SimpleDateFormat sdf = getDateFormat(timeTypeString);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定格式的SimpleDateFormat
     * <p>Function: getDateFormat</p>
     * <p>Description: </p>
     *
     * @return
     * @author acexy@thankjava.com
     * @date 2015年6月18日 上午10:00:31
     */
    public static SimpleDateFormat getDateFormat(String timeTypeString) {
        if (timeTypeString == null) {
            timeTypeString = TimeType.DEFAULT.type;
        }
        SimpleDateFormat sdf = SIMPLE_DATE_FORMAT_MAP.get(timeTypeString);
        if (sdf == null) {
            sdf = new SimpleDateFormat(timeTypeString);
            SIMPLE_DATE_FORMAT_MAP.put(timeTypeString, sdf);
        }
        return sdf;
    }

    /**
     * 格式化日期
     * <p>Function: formatDate</p>
     * <p>Description: </p>
     *
     * @param timeType
     * @param date
     * @return
     * @author acexy@thankjava.com
     * @date 2015年6月18日 上午10:01:09
     */
    public static String formatDate(TimeType timeType, Date date) {
        return formatDate(timeType.type, date);
    }

    /**
     * 格式化日期
     *
     * @param timeTypeString
     * @param date
     * @return
     */
    public static String formatDate(String timeTypeString, Date date) {
        return getDateFormat(timeTypeString).format(date);
    }

    /**
     * 将时间按照指定偏移单位和偏移量生成新的时间
     * <p>Function: offsetDate</p>
     * <p>Description: </p>
     *
     * @param date
     * @param calendarUnit
     * @param dateOffset
     * @return
     * @author acexy@thankjava.com
     * @date 2015年6月18日 上午10:01:26
     */
    public static Date offsetDate(Date date, int calendarUnit, int dateOffset) {
        if (date == null) {
            return null;
        }

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(calendarUnit, dateOffset);

        return ca.getTime();
    }

}

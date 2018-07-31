package com.thankjava.toolkit.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 对java Date 进行的常规操作
 * <p>Function: TimeHelper</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2014年12月10日 下午8:40:49
 */
public final class TimeHelper {

    private TimeHelper() {
    }

    private static final Map<TimeType, SimpleDateFormat> simpleDateFormatMap = new HashMap<TimeType, SimpleDateFormat>();

    public enum TimeType {

        DEFAULT("yyyy-MM-dd|HH:mm:ss"),
        yyyyMMddHH("yyyyMMddHH"),
        yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS"),
        yyyyMMddHHmmss("yyyyMMddHHmmss"),
        yyyyMMdd("yyyyMMdd"),
        HHmmss("HHmmss"),

        yyyy1MM1dd1HH1mm("yyyy/MM/dd/HH/mm"),;

        public String type;

        private TimeType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * 将date Str 转换为 Date 类型
     * <p>Function: parseDate</p>
     * <p>Description: </p>
     *
     * @param date
     * @param timeType
     * @return
     * @author acexy@thankjava.com
     * @date 2015年6月18日 上午9:59:42
     * @version 1.0
     */
    public static Date parseDate(String date, TimeType timeType) {
        SimpleDateFormat sdf = getDateFormat(timeType);
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
     * @param timeType
     * @return
     * @author acexy@thankjava.com
     * @date 2015年6月18日 上午10:00:31
     * @version 1.0
     */
    public static SimpleDateFormat getDateFormat(TimeType timeType) {
        if (timeType == null) {
            timeType = TimeType.DEFAULT;
        }
        SimpleDateFormat sdf = simpleDateFormatMap.get(timeType);
        if (sdf == null) {
            sdf = new SimpleDateFormat(timeType.getType());
            simpleDateFormatMap.put(timeType, sdf);
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
     * @version 1.0
     */
    public static String formatDate(TimeType timeType, Date date) {
        return getDateFormat(timeType).format(date);
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
     * @version 1.0
     */
    public static Date offsetDate(Date date, int calendarUnit, int dateOffset) {
        if (date == null) {
            return date;
        }

        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(calendarUnit, dateOffset);

        return ca.getTime();
    }
}

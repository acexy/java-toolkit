package utils;

import java.util.Calendar;
import java.util.Date;

import com.thankjava.toolkit.utils.TimeUtil;
import com.thankjava.toolkit.utils.TimeUtil.TimeType;

public class TimeUtilTest {


    public static void main(String[] args) {



//        System.out.println(TimeUtil.parseDate(TimeType.yyyyMMdd, "19700101").getTime());
//        System.out.println(TimeUtil.formatDate(TimeType.yyyyMMddHHmmssSSS, new Date(1471590350942L)));

        Date dateNow = TimeUtil.offsetDate(new Date(0L), Calendar.DAY_OF_YEAR, 73050);
//        System.out.println(dateNow.getTime());
//        System.out.println(new Date().getTime());

        System.out.println(TimeUtil.formatDate(TimeType.DEFAULT, dateNow));
    }
}
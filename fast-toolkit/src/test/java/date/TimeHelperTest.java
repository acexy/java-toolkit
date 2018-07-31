package date;

import java.util.Calendar;
import java.util.Date;

import com.thankjava.toolkit.date.TimeHelper;
import com.thankjava.toolkit.date.TimeHelper.TimeType;

public class TimeHelperTest {


    public static void main(String[] args) {
        System.out.println(TimeHelper.formatDate(TimeType.yyyyMMddHHmmssSSS, new Date(1471590350942L)));

        Date dateNow = TimeHelper.offsetDate(new Date(), Calendar.DAY_OF_YEAR, -32);
        System.out.println(dateNow.getTime());
        System.out.println(new Date().getTime());

        System.out.println(TimeHelper.formatDate(TimeType.yyyy1MM1dd1HH1mm, new Date()));
    }
}
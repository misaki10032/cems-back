package com.cems.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName UserController
 * @Author 陈新予
 * @Date 2021/3/20 10:21
 * @Version 1.0
 */
public class DateUtil {
    public    Date strToDate(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = simpleDateFormat.parse(s);
        return date;
    }
    public    String dateToString(Date d){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String str = simpleDateFormat.format(d);
        return str;
    }
    public int daymidnow(Date Date1, Date Date2) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(Date1);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(Date2);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }
}

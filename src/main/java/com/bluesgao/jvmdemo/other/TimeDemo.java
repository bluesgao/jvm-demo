package com.bluesgao.jvmdemo.other;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeDemo {
    public static void main(String[] args) {
        Long mtime = 1595473600801L;
        System.out.println(millisecondToDate(mtime));
    }

    /**
     * 秒转换为指定格式的日期
     * @param second
     * @return
     */
    public static String secondToDate(long second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(second * 1000);//转换为毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(date);
        return dateString;
    }

    public static String millisecondToDate(long millisecond){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisecond);//毫秒
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String dateString = format.format(date);
        return dateString;
    }
}

package android.rain.tool;

import android.icu.text.SimpleDateFormat;

import java.util.Date;

public class Time {
    private SimpleDateFormat simpleDateFormat;// HH:mm:ss
    //获取当前时间
    private Date date;
    public String getTimeYearMonthDate(){
        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
    public String getTimeYearMonthDateHMS(){
        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
    public String getYear(){
        simpleDateFormat = new SimpleDateFormat("yyyy年");
        date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
    public String getMonth(){
        simpleDateFormat = new SimpleDateFormat("MM月");
        date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
    public String getDate(){
        simpleDateFormat = new SimpleDateFormat("dd日");
        date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
    public String getYM(){
        simpleDateFormat = new SimpleDateFormat("yyyy年MM月");
        date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
    public String getMD(){
        simpleDateFormat = new SimpleDateFormat("MM月dd日");
        date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }
}

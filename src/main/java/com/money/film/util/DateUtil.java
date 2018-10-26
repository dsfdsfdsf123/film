package com.money.film.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author liugang
 * @create 2018/10/24 23:18
 **/
public class DateUtil {

    public static String getCurrentDateStr()throws Exception{
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }
}

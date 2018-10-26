package com.money.film.util;

/**
 * @author liugang
 * @create 2018/10/27 0:43
 **/
public class StringUtil {

    /**
     * 判断不为空
     * @param str
     * @return
     */
    public static Boolean isNotEmpty(String str){
        if (str!=null && !"".equals(str)){
            return true;
        }else {
            return false;
        }
    }
}

package com.netdisk.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	/**
	 * 手机号
	 * @param str
	 * @return
	 */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String s="^[1](([3|5|8][\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\d]{8}$";// 验证手机号
        if(StringUtil.isNotEmpty(str)){
            p = Pattern.compile(s);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }
   
    
    public static boolean isEmail(String str) {
    	Pattern p = null;
        Matcher m = null;
        boolean b = false;
        String s= "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";// 验证邮箱
        if(StringUtil.isNotEmpty(str)){
            p = Pattern.compile(s);
            m = p.matcher(str);
            b = m.matches();
        }
        return b;
    }
    
    
}

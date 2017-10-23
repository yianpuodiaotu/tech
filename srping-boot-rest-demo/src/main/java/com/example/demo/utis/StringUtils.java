package com.example.demo.utis;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * 字符串帮助类
 * @author 高炎
 *
 */
public class StringUtils {
	
	 /**
     * 判断字符串是否为空，
     *
     * @param 字符串
     * @return 为空返回true
     * 不为空返回false
     */
	 @Deprecated
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)) {
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为空，
     * @param obj
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static boolean isNull(Object obj) {

        if(obj == null) return true;
        String type = obj.getClass().getSimpleName();

        switch (type) {
            case "String":
                String str = (String) obj;
                if (str == null || str.isEmpty() || str.equals("null") || str.equals(""))
                    return true;
                break;
            case "List":
            case "ArrayList":
            case "LinkedList":
                List list = (List) obj;
                if (list == null || list.isEmpty())
                    return true;
                break;
            case "Map":
            case "HashMap":
            case "LinkedHashMap":
            case "TreeMap":
                Map map = (Map) obj;
                if (map == null || map.isEmpty())
                    return true;
                break;
            default:
                /**
                 * 在判断一次
                 */
                if (null == obj || "".equals(obj)||"null".equals(obj)||"".equals(obj.toString().trim())) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * @param str      目标值
     * @param defValue 默认值
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }

    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 去掉字符串中的空格
     *
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        if (!isEmpty(str)) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            return m.replaceAll("");
        }
        return str;
    }
    
}

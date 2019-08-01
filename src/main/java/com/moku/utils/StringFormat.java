package com.moku.utils;

//import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;

/**
 * 字符串格式化
 * @author wull
 */
public class StringFormat {

    /**
     * 模仿C#格式化字符串
     *
     * @param str
     * @param args
     * @return
     */
    public static String format(String str, String... args) {
        for (int i = 0; i < args.length; i++) {
            str = str.replaceFirst("\\{\\}", args[i]);
        }
        return str;
    }

    public static String format(String str, Object... args) {
        for (int i = 0; i < args.length; i++) {
            str = str.replaceFirst("\\{\\}", String.valueOf(args[i]));
        }
        return str;
    }

    /**
     * 安全链接字符串
     *
     * @param strs
     * @return
     */
    public static String softLink(String... strs) {
        StringBuffer sb = new StringBuffer();
        for (String s : strs) {
            sb.append(StringUtils.isEmpty(s) ? "" : s);
        }
        return sb.toString();
    }

    /**
     * 安全去首位链接字符串
     *
     * @param strs
     * @return
     */
    public static String softTrimLink(String... strs) {
        StringBuffer sb = new StringBuffer();
        for (String s : strs) {
            sb.append(StringUtils.trimToEmpty(s));
        }
        return sb.toString();
    }

    /**
     * 安全toString
     *
     * @param str
     * @return
     */
    public static String toString(Object str) {
        if (null == str)
            return null;
        return str.toString();
    }


    /**
     * 将数组轻而易举的转换成用逗号分隔的字符串
     *
     * @param strs
     * @return
     */
    public static String formatByComma(String... strs) {
        return StringFormat.formatByComma(true, strs);
    }

    /**
     * 将数组轻而易举的转换成用逗号分隔的字符串
     *
     * @param strs
     * @return
     */
    public static String formatByComma(boolean filterNull, String... strs) {
        if (!filterNull)
            ArrayUtils.removeElement(strs, null);
        String str = ArrayUtils.toString(strs);
        return StringUtils.substring(str, 1, str.length() - 1);
    }

    /**
     * 在数组每个元素前后追加数据
     *
     * @param array
     * @param head
     * @param end
     * @return
     */
    public static String[] formatEcho(String head, String end, String... array) {
        if (ObjectUtils.isEmpty(array))
            return null;
        for (int i = 0; i < array.length; i++) {
            if (null == array[i])
                continue;
            array[i] = head + array[i] + end;
        }
        return array;
    }

    /**
     * 把用","分割的字符串，转换成数组
     *
     * @param str
     * @return
     */
    public static String[] splitByComma(String str) {
        return StringUtils.split(str, ",");
    }

   /* public static void main(String[] args) {
      *//*  String[] stringArray = { "Red", "Orange", "Blue", "Brown", "Red" };
        System.out.println(StringFormat.formatByComma(stringArray));*//*

        String unitId="unitId";
        Integer sort=0;
        String parentIds="parentIds";
        String str = StringFormat.format("time={},unitId={},sort={},parentIds={}", new Object[]{System.currentTimeMillis(),unitId, sort,""});
        System.out.println(str);
    }*/
}

package com.moku.utils;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

public class URLDecoderTest {


    /**
     *   //将普通字符串转换成

     //application/x-www-form-urlencoded字符串

     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String URLencode(final String str)  {

        //必须强调的是编码方式必须正确，如baidu的是gb2312，而google的是UTF-8
        String urlStr =null;
         if (!StringUtils.isEmpty(str)){
             try {
                 urlStr = URLEncoder.encode(str, "utf-8");
             } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
             }
             System.out.println(urlStr);
         }

        return urlStr;
    }

    /**
     *   //将application/x-www-form-urlencoded字符串

     //转换成普通字符串
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String URLdecode(final String str)  {

         String keyWord=null;
        //必须强调的是编码方式必须正确，如baidu的是gb2312，而google的是UTF-8
            if (!StringUtils.isEmpty(str)){
                try {
                    keyWord = URLDecoder.decode(str, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
              System.out.println(keyWord);
        return keyWord;
    }


    /**
     *   检测请求的返回的数据中是否包含
     *   上架的名称
     * @param appName
     * @param ltr
     * @return
     */
    public static boolean  isExitsShelf(String appName, List<String> ltr) {
        if(null!=ltr && ltr.size()!=0){

            for (int i=0;i<ltr.size();i++){
                if (ltr.contains(appName) ){
                    return  true;
                }
            }
        }

        return false;
    }


    /**
     *    将HTML 实体字符集转化成中文字符
     * @param string
     * @return
     */
    public static String HTML2Java(String string) {
        String str=null;
        if (!StringUtils.isEmpty(string)){
            str = StringEscapeUtils.unescapeHtml4(string);
//            System.out.println(str);
            return str;
        }
        return str;
    }

}

package com.test;


import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class EscapeUtils {

    public static void main(String[] args) {
        String s = "中文转字符的HTML实体表示形式";
      /*  System.out.println(StringEscapeUtils.escapeHtml(s));


        String u = "&#20013;&#25991;&#36716;&#23383;&#31526;&#30340;HTML&#23454;&#20307;&#34920;&#31034;&#24418;&#24335;";
        System.out.println(StringEscapeUtils.unescapeHtml(u));
        }
        */

        String str = "&#x4f60;&#x597d;&#x94f6;&#x884c;";
      test(str);
}







        public static String test(String string) {
            String str=null;
            if (!StringUtils.isEmpty(string)){
                str = StringEscapeUtils.unescapeHtml4(string);
                System.out.println(str);
                return str;
            }

            return str;
        }



    }
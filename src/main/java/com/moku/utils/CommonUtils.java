package com.moku.utils;

import com.moku.model.PktVo;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtils {



    public static Map<String, List<PktVo>> GroupList(List<PktVo> skuVoList) {
        /*2、分组算法**/
        Map<String, List<PktVo>> skuIdMap = new HashMap<>();
        for (PktVo skuVo : skuVoList) {
            List<PktVo> tempList = skuIdMap.get(skuVo.getMarket());
            /*如果取不到数据,那么直接new一个空的ArrayList**/
            if (tempList == null) {
                tempList = new ArrayList<>();
                tempList.add(skuVo);
                skuIdMap.put(skuVo.getMarket(), tempList);
            }
            else {
                /*某个sku之前已经存放过了,则直接追加数据到原来的List里**/
                tempList.add(skuVo);
            }
        }

        /*3、遍历map,验证结果**/
        for(String skuId : skuIdMap.keySet()){
            System.out.println(skuIdMap.get(skuId));
        }

        return skuIdMap;
    }



    /**
     * 去除前后指定字符
     * @param source 目标字符串
     * @param beTrim 要删除的指定字符
     * @return 删除之后的字符串
     * 调用示例：System.out.println(trim(", ashuh  ",","));
     */
    public  static  String trim(String source, String beTrim) {
        if(source==null){
            return "";
        }
        source = source.trim(); // 循环去掉字符串首的beTrim字符
        if(source.isEmpty()){
            return "";
        }
        String beginChar = source.substring(0, 1);
        if (beginChar.equalsIgnoreCase(beTrim)) {
            source = source.substring(1, source.length());
            beginChar = source.substring(0, 1);
        }
        // 循环去掉字符串尾的beTrim字符
        String endChar = source.substring(source.length() - 1, source.length());
        if (endChar.equalsIgnoreCase(beTrim)) {
            source = source.substring(0, source.length() - 1);
            endChar = source.substring(source.length() - 1, source.length());
        }
        return source;
    }

    /**
     * 返回两个string类型日期之间相差的天数
     * @param smdate
     * @param bdate
     * @return
     */
    public static int daysBetween(String smdate,String bdate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try{
            cal.setTime(sdf.parse(smdate));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            time2 = cal.getTimeInMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}

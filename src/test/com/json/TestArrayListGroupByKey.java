package com.json;

import com.moku.model.PktVo;

import java.util.*;

public class TestArrayListGroupByKey {

    public static void main(String[] args) {
        /*1、准备数据**/
        PktVo sku1 = new PktVo("1L","p1","100L");
        PktVo sku2 = new PktVo("2L","p2","101L");
        PktVo sku3 = new PktVo("3L","p3","102L");
        PktVo sku4 = new PktVo("3L","p4","103L");
        PktVo sku5 = new PktVo("2L","p5","100L");
        PktVo sku6 = new PktVo("5L","p6","100L");

        List<PktVo> PktVoList = Arrays.asList(new PktVo [] {sku1,sku2,sku3,sku4,sku5,sku6});
        GroupList(PktVoList);


    }

    public static void GroupList(List<PktVo> skuVoList) {
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
    }
}

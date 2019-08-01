package com.moku.factory;


import com.moku.*;

public class MarketFactory {



    private MarketFactory() {
    }

    /**
     * 静态工厂
     * @param shapeType
     * @return
     */
    public static AppServer getInstance(String shapeType)
    {
        return getShape(shapeType);
    }



    /*
       为了以后的扩展性，可以考虑ioc 机制，备用
     */
    public static AppServer choose(String shortName) {
        AppServer hero = null;
        try {
//            String fullName = HeroesFactory.init().getProperty(shortName);
            hero = (AppServer) Class.forName(shortName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hero;
    }


    //使用 getShape 方法获取对象
    public static AppServer getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("Baidu")){
            return new BaiduMarket();
        } else if(shapeType.equalsIgnoreCase("Huawei")){
            return new HuaweiMarket();
        } else if(shapeType.equalsIgnoreCase("360")){
            return new Market360();
        } else if(shapeType.equalsIgnoreCase("MeiZu")){
            return new MeiZuMarket();
        } else if(shapeType.equalsIgnoreCase("WanDouJia")){
            return new WanDouJiaMarket();
        } else if(shapeType.equalsIgnoreCase("XiaoMi")){
            return new XiaoMiMarket();
        } else if(shapeType.equalsIgnoreCase("YingYongBao")){
            return new YingYongBao();
        }
        return null;
    }
}
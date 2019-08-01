package com.moku.service;


import com.moku.AppServer;
import com.moku.factory.MarketFactory;
import com.moku.utils.Constant;
import com.moku.utils.StringFormat;
import com.moku.utils.URLDecoderTest;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class YingYongbaoImpl implements ICheck {

    private Logger logger=Logger.getLogger(YingYongbaoImpl.class);

    Map<String, String> pnsMap=null;


    //todo 这里需要拓展，业务要求是500个，待整理,这个不现实，超过100多个后，应用应用市场很难加载了，也很难得到总的记录数
    public YingYongbaoImpl() {
        pnsMap = new HashMap<>();
        pnsMap.put("0", "");
        pnsMap.put("1", "MTA=");
        pnsMap.put("2", "MjA=");
        pnsMap.put("3", "MzA=");
        pnsMap.put("4", "NTA=");
        pnsMap.put("6", "NjA=");
        pnsMap.put("7", "NzA=");
        pnsMap.put("8", "NDA=");
        pnsMap.put("9", "ODA=");
    }

    @Override
    public boolean check(String str) {
        try {
           if (APPbaocheck(str)){
               return true;
           }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * 应用宝的检测方法
     * @param key
     * @throws IOException
     */
    public  boolean APPbaocheck(String key) throws IOException {
        String  sid="0"; //默认
//        createPNSHashMap();

//        AppServer app= MarketFactory.getInstance("YingYongBao");
        AppServer app= MarketFactory.choose("com.moku.YingYongBao");
        for(Map.Entry<String,String>  entry:pnsMap.entrySet()){
            if ( app.PostDatas(URLDecoderTest.URLencode(key),entry.getValue(),sid)

                    ){ //找到Ok,不再进行轮询访问。
                String str = StringFormat.format("content={}", new Object[]{key + Constant.OK});
                  logger.info(str);

                 return true;
            }
        }
        return  false;
    }

    /**
     *   因为分页请求的参数还没找到规则，暂时用配置的方式
     *
     */
    public void createPNSHashMap() {

    }
}

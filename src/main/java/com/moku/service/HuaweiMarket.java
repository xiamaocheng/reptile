package com.moku.service;

import com.moku.AppServer;
import com.moku.factory.MarketFactory;
import com.moku.utils.Constant;
import com.moku.utils.StringFormat;


public class HuaweiMarket implements ICheck  {

    private org.apache.log4j.Logger logger= org.apache.log4j.Logger.getLogger(HuaweiMarket.class);
    @Override
    public boolean check(String str) {
        AppServer app= MarketFactory.getInstance("huawei");

        for (int i=0;i<50;i++) {


           // 搜索关键字以及搜索结果的页码（可为空，默认为1）
            if( app.getDatas(str, i+"")){
                str = StringFormat.format("content={}", new Object[]{str+ Constant.OK});
                logger.info(str);
                return  true;
            }
        }

        return false;
    }
}

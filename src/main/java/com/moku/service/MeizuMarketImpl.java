package com.moku.service;

import com.moku.AppServer;
import com.moku.factory.MarketFactory;
import com.moku.utils.Constant;
import com.moku.utils.StringFormat;
import com.moku.utils.URLDecoderTest;

public class MeizuMarketImpl  implements ICheck {
    private org.apache.log4j.Logger logger= org.apache.log4j.Logger.getLogger(MeizuMarketImpl.class);
    @Override
    public boolean check(String str) {
        AppServer app= MarketFactory.getInstance("meizu");
            //魅族分页查询，这里仅仅查询3页，可以拓展直至查完，
            for (int i=0;i<=50;i++) {
                if (app.getDatas(URLDecoderTest.URLencode(str), i+"")){

                    str = StringFormat.format("content={}", new Object[]{str+ Constant.OK});
                    logger.info(str);

                    return  true;
                }

            }


        return false;
    }
}

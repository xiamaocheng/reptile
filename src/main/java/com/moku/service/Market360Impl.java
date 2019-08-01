package com.moku.service;

import com.moku.AppServer;
import com.moku.factory.MarketFactory;
import com.moku.model.Info;
import com.moku.utils.Constant;
import com.moku.utils.StringFormat;
import org.apache.log4j.Logger;

import java.util.List;



public class Market360Impl implements ICheck {
    private Logger logger=Logger.getLogger(Market360Impl.class);

    @Override
    public boolean check(String str) {

        List<Info> infos=null;
        AppServer app= MarketFactory.getInstance("360");
        for (int i=0;i<50;i++){ //只需要查询10 页，直接返回就可以了，没有全部查完。不再考虑总的查询记录数。太耗时了。
             //只要有一个返回true,不再查询
//            infos = getSearchInfos("qq", i+"");// 搜索关键字以及搜索结果的页码（可为空，默认为1）
          // 搜索关键字以及搜索结果的页码（可为空，默认为1）
            if( app.getDatas(str, i + "")){
                str = StringFormat.format("content={}", new Object[]{str+ Constant.OK});
                logger.info(str);
                return  true;
            }
        }


        return false;
    }
}

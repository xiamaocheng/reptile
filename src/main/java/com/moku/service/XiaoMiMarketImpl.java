package com.moku.service;

import com.moku.AppServer;
import com.moku.factory.MarketFactory;
import com.moku.utils.Constant;
import com.moku.utils.StringFormat;

public class XiaoMiMarketImpl implements ICheck {
    private org.apache.log4j.Logger logger= org.apache.log4j.Logger.getLogger(XiaoMiMarketImpl.class);
    @Override
    public boolean check(String str) {
        AppServer app= MarketFactory.getInstance("XiaoMi");
        // 执行的小米策略是先到展示区查询，如果有true 直接返回，返回false 直接查询全部，都没有返回false

          //1.查展示区

            // 搜索关键字以及搜索结果的页码
           if( app.getDatas(str, "")){
                logger.info(""+str+"已经找到了，休息几秒再次检测");
                return  true;
            }else
            {
                //小米分页查询，这里仅仅查询50页
                for (int i=1;i<=50;i++) {
                    if (app.getDatas(str, i+"")){

                        str = StringFormat.format("content={}", new Object[]{str+ Constant.OK});
                        logger.info(str);
                        return  true;
                    }

                }

            }
        return false;
    }
}

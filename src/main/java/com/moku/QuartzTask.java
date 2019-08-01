package com.moku;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.moku.dao.IAppDao;

import com.moku.dao.ITaskDao;
import com.moku.model.App;
import com.moku.model.PktVo;
import com.moku.model.Task;
import com.moku.model.Task;
import com.moku.service.ICheck;
import com.moku.service.SendMsgService;
import com.moku.utils.CommonUtils;
import com.moku.utils.Constant;
import com.moku.utils.StringFormat;
import com.moku.utils.ThreadPoolManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("taskJob")
public class QuartzTask {


    private static Logger logger = Logger.getLogger(QuartzTask.class);

    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    @Autowired
    private ICheck appbaoImpl;
    @Autowired
    private SendMsgService sendMsgService;
    @Autowired
    private ICheck market_360Impl;

    @Autowired
    private ICheck huaweiMarket;

    @Autowired
    private ICheck xiaoMiMarketImpl;

    @Autowired
    private ICheck meizuMarketImpl;


    @Autowired
    private ICheck baiduMarketImpl;


    @Autowired
    private ICheck wanDouJiaMarketImpl;

    Map<String, List<PktVo>> searchList = null; //这里不能直接赋值


    @Autowired
    private ITaskDao dao;

    @Autowired
    private IAppDao appDao;
    List<String> ids = new ArrayList<>();


    List<PktVo> beans = new ArrayList<>();
    PktVo bean = null;
    private long quaryStart;
    private long endquery;

    private int size;


    public QuartzTask() {

    }


    private CubbyHole cubbyhole = new CubbyHole();

    @Scheduled(cron = "*/1 * * * * ?")
    public void testDb() throws IOException {  //3分钟查一次
        //1.获取查询数据 ，这里使用了全表扫描
        logger.info("...................................bbb....................................");
      //  searchList = getSearchList();
        logger.info(" 查询请求列表耗时" + (endquery - quaryStart) / 1000 + "s");
        //1.1 查询要匹配的发送记录数
        for (String skuId : searchList.keySet()) {
            List<PktVo> strlist = searchList.get(skuId);
//            logger.info(this.getClass().getName()+strlist);
            size = strlist.size();
            for (PktVo pkt : strlist) {
                //应用宝市场：
                if (pkt.getMarket().equals("tencent")) {
                    productRequest(pkt);
                }

                //小米市场
                if (pkt.getMarket().equals("xiaomi")) {
                    productRequest(pkt);

                }

                //华为市场
                if (pkt.getMarket().equals("huawei")) {
                    productRequest(pkt);

                }

                //魅族市场
                if (pkt.getMarket().equals("meizu")) {
                    productRequest(pkt);
                }
                //百度市场
                if (pkt.getMarket().equals("baiduMobile")) { //baiduMobile
                    productRequest(pkt);
                }

                //360市场
                if (pkt.getMarket().equals("360Mobile")) {
                    productRequest(pkt);
                }

                //豌豆荚市场
                if (pkt.getMarket().equals("wandoujia")) {
                    productRequest(pkt);
                }

            }


        }


    }

    public void productRequest(PktVo pkt) {
        logger.info("产生请求开始");
        cubbyhole.put(pkt.getSoftwareName());
        logger.info("本周期内产生请求" + pkt.getSoftwareName());
    }

    @Scheduled(cron = "*/1 * * * * ?")
    public void testTask() throws IOException {
        System.out.println("1111");
        if (searchList == null) {
            return;   // 通知生产
        } else {
            System.out.println("Not Null = " + JSONObject.toJSONString(searchList));
            logger.info("消费执行");
            for (int i = 0; i < size; i++) {
                logger.info("开始消费" + cubbyhole.get());
                //2.进行搜索，以后应考虑异步通知
                doTask1();
                doTask2();

//                Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        doTask1();
//                    }
//                };
//                Runnable t2 = new Runnable() {
//                    @Override
//                    public void run() {
//
//                        doTask2();
//                    }
//                };
//                //这里可以考虑优先级
//                ThreadPoolManager.getDefault().addExecuteTask(runnable);
//                ThreadPoolManager.getDefault().addExecuteTask(t2);
            }
        }


    }

    public void doTask2() {
        if (!huaweiMarket.check(cubbyhole.get())

                ) {       //检测华为市场上的应用下架时，通知信息
            doNotifyInfo(cubbyhole.get() + "华为应用");
        }

        if (!baiduMarketImpl.check(cubbyhole.get())

                ) {       //检测百度市场上的应用下架时，通知信息
            doNotifyInfo(cubbyhole.get() + "百度应用市场");
        }

        if (!market_360Impl.check(cubbyhole.get())

                ) {        //检测360上的应用下架时，通知信息
            doNotifyInfo(cubbyhole.get() + "360应用市场");
        }
        if (!wanDouJiaMarketImpl.check(cubbyhole.get())

                ) {        //检测豌豆荚上的应用下架时，通知信息
            doNotifyInfo(cubbyhole.get() + "豌豆荚应用市场");
        }
    }

    public void doTask1() {
        if (!appbaoImpl.check(cubbyhole.get())) {       //检测应用宝上的应用下架时，通知信息
            doNotifyInfo(cubbyhole.get() + "应用宝");
        }


        if (!xiaoMiMarketImpl.check(cubbyhole.get())

                ) {       //检测小米上的应用下架时，通知信息
            doNotifyInfo(cubbyhole.get() + "小米应用");
        }


        if (!meizuMarketImpl.check(cubbyhole.get())

                ) {       //检测魅族市场上的应用下架时，通知信息
            doNotifyInfo(cubbyhole.get() + "魅族应用商店");
        }
    }


    public List getInitList(Task user) {
        String dataids = user.getApps();

        //去除前后的[]
        String aa = CommonUtils.trim(dataids, "[");
        String datas = CommonUtils.trim(aa, "]");
        //截取获得字符串数组
        String[] strArray = datas.split(",");

//        ["123","123","123"]

//        IN ("123","123","123")

        for (int i = 0; i < strArray.length; i++) {
            //截取前后的为引号
            ids.add(strArray[i].replace("\"", ""));
        }

        //取出
        List<App> list = null;

        for (int i = 0; i < ids.size(); i++) {
//            System.out.println(i + ":" + ids.get(i));
            List<App> app = appDao.selectUser1(ids.get(i));
            for (int j = 0; j < app.size(); j++) {
                //过滤高价
                if (app.get(j).getApp_id().equals("9")) {
//                    System.out.println(app.toString());
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(app.get(j));
                }
            }
        }
        return list;
    }

    /**
     * 查询应用配置信息
     *
     * @return
     */
    public Map<String, List<PktVo>> getSearchList() {
        quaryStart = System.currentTimeMillis();
        String id = ""; //全部查询
        Task user = dao.selectTask(id);
//        SELECT  t.apps FROM f_task_setting  t WHERE  id="8a8a8bdb4fa23e4a014fa24d0efc0001"

//        SELECT  t.apps FROM test_1
//        System.out.println(user.getApps());
//        List<App> alist = getInitList(user);
//        System.out.println("  查询出数据：=" + alist.size());
        for (int i = 0; i < 2; i++) {


            // 做一个分组，  主要就是当你在某个市场搜索某个关键词的时候，能够同时检测好几个软件
            String market="meizu";
            String softwareName="aa";
            String keyword="美女";
            bean = new PktVo(market, softwareName, keyword);
            beans.add(bean);
        }
        endquery = System.currentTimeMillis();

        return CommonUtils.GroupList(beans);

    }


    /**
     * 发送通知信息
     *
     * @param
     */
    public void doNotifyInfo(String info) {
        //装成json 格式
      /*  map2.put("content", info + Constant.STRING);
        map.put("msgtype", "text");
        map.put("text", map2);
        String textMsg = new Gson().toJson(map);
        String str = StringFormat.format("content={}", new Object[]{textMsg}); //格式化字段
        sendMsgService.Notify(textMsg);
        logger.info(str);  //log 可以考虑aop 方法*/
    }


    public static void main(String[] args) {

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:spring-*.xml");
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath*:spring-*.xml"});


        QuartzTask t = (QuartzTask) classPathXmlApplicationContext.getBean("quartzTask");
//        t.testTask();
    }


}
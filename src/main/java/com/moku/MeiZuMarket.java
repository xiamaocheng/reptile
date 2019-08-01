package com.moku;

/**
 * 向服务器请求数据的对应格式的数据
 *
 *
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moku.utils.*;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MeiZuMarket extends  AppServer{
   private static Logger log = Logger.getLogger(MeiZuMarket.class.getClass());

     String VISIT_URL_MeiZu = null;

    public void  init() {
        VISIT_URL_MeiZu= PropertiesUtils.getProperty("VISIT_URL_MeiZu");
    }

    @Override
    public boolean getDatas(String str, String page) {
        String url=null;
             init();

        // 目标搜索url
        if (page == null || page == "") {
             url = String.format(VISIT_URL_MeiZu, str, 0, "18");
        } else {
             url = String.format(VISIT_URL_MeiZu, str, ""+Integer.parseInt(page)*18 , "18"); //这里默认每一页查询的数量是常量
        }


        String responseStr = null;

        try {
            responseStr = ConnUtils.testGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //1.check data
        if(responseStr == null || StringUtils.isEmpty(responseStr)){
            log.info("魅族应用商店返回报文是空的");
        }
        //2.parse json and
//        System.out.println("魅族应用商店返回List="+getNextPageUrl(responseStr));

        // / 3.return result

        if(  URLDecoderTest.isExitsShelf(URLDecoderTest.URLdecode(str),getNextPageUrl(responseStr))){
            return  true;
        }

        return false;

    }


    /**
     * 解析JSON数据
     * 这里直接取出魅族的应用商店的数值，比较简单
     *
     * @param responseStr
     * @return
     */
    public  List<String> getNextPageUrl(String responseStr){
        List list=new ArrayList<>();
        JSONObject jsonObject = (JSONObject) JSON.parse(responseStr);
//        JSONObject jsonObjects = (JSONObject) jsonObject.get("obj");
        JSONObject jsonObjects = jsonObject.getJSONObject("value");
        if (jsonObjects!=null){
//            System.out.println(""+jsonObject);
            JSONObject jsonitem = (JSONObject)JSON.parse(jsonObjects.toJSONString()); //
            if (jsonitem !=null){
                String appDetailslist = jsonitem.getString("list");
//                System.out.println("aa"+appDetailslist);
                JSONArray  array=jsonitem.getJSONArray("list");
                if (!StringUtils.isEmpty(array)){
                    for (int i=0;i<array.size();i++){
//                        System.out.println(array.getString(i));
                        String json = array.getString(i);
                        if (!StringUtils.isEmpty(json)){
                            JSONObject jsondata = (JSONObject)JSON.parse(json); //
                            if (jsondata.getString("name")!=null  ){
                                 System.out.println("Meizu Name="+URLDecoderTest.HTML2Java(jsondata.getString("name")));
                                list.add( URLDecoderTest.HTML2Java(jsondata.getString("name")) );

                            }
                        }

                    }
                    System.out.println("MeiZu APPlist="+list.size());
                    return  list;
                }

                }

            }else{

           String  str = StringFormat.format("content={}", new Object[]{Constant.RET_STRING});
            log.info(str);
            System.out.println("  get obj failed!");


        }
        return null;
    }

    @Override
    public List<?> getSearchInfos(String str, String page) throws Exception {
        return null;
    }


    @Override
    public boolean PostDatas(String kw, String pns, String sid) throws IOException {
        return false;
    }




}
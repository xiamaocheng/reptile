package com.moku;

/**
 * 向服务器请求数据的对应格式的数据
 *
 *
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moku.utils.ConnUtils;
import com.moku.utils.PropertiesUtils;
import com.moku.utils.StringFormat;
import com.moku.utils.URLDecoderTest;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class YingYongBao  extends  AppServer{
   private static Logger log = Logger.getLogger(YingYongBao.class.getClass());

    String VISIT_URL = null;

    public void init() {
        VISIT_URL= PropertiesUtils.getProperty("VISIT_URL");
    }

    String url=null;
    /**
     *   获取服务器数据
     * @throws IOException
     */
    public  boolean PostDatas(String kw,String pns,String sid) throws IOException {
         init();

         url = String.format(VISIT_URL,kw,pns,sid);

        String responseStr = ConnUtils.PostRequest(url);


          //1.check data
        if(responseStr == null || StringUtils.isEmpty(responseStr)){

               log.info("应用宝报文是空的");

        }
        //2.parse json and
        System.out.println("应用宝List="+getNextPageUrl(responseStr));
        // 3.return result
        if(  URLDecoderTest.isExitsShelf(URLDecoderTest.URLdecode(kw),getNextPageUrl(responseStr))){

             return  true;
        }
              return false;

    }




    /**
     * 解析JSON数据
     * 这里直接取出appName的数值，比较简单
     *
     * @param responseStr
     * @return
     */
    public  List<String> getNextPageUrl(String responseStr){
        List list=new ArrayList<>();
        JSONObject jsonObject = (JSONObject) JSON.parse(responseStr);
//        JSONObject jsonObjects = (JSONObject) jsonObject.get("obj");
        JSONObject jsonObjects = jsonObject.getJSONObject("obj");
        if (jsonObjects!=null){
//            System.out.println(""+jsonObject);
            JSONObject jsonitem = (JSONObject)JSON.parse(jsonObjects.toJSONString()); //
            if (jsonitem !=null){
                String appDetailslist = jsonitem.getString("appDetails");
//                System.out.println("aa"+appDetailslist);
                JSONArray  array=jsonitem.getJSONArray("appDetails");
                if (!StringUtils.isEmpty(array)){
                    for (int i=0;i<array.size();i++){
                        System.out.println(array.getString(i));
                        String json = array.getString(i);
                        if (!StringUtils.isEmpty(json)){
                            JSONObject jsondata = (JSONObject)JSON.parse(json); //
                            if (jsondata.getString("appName")!=null  ){
                                list.add(jsondata.getString("appName"));
                            }
                        }

                    }
                    return  list;
                }

                }

            }else{

            String string="应用宝请求返回数据失败！";
            String str = StringFormat.format("content={}", new Object[]{string});
            log.info(str);
            System.out.println("  get obj failed!");
            throw new RuntimeException("应用宝服务器返回失败!");

        }
        return null;
    }

    @Override
    public List<?> getSearchInfos(String str, String page) throws Exception {
        return null;
    }


    @Override
    public boolean getDatas(String str, String page) {
        return false;
    }



}
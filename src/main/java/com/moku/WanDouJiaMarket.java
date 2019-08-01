package com.moku;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moku.utils.ConnUtils;
import com.moku.utils.PropertiesUtils;
import com.moku.utils.URLDecoderTest;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WanDouJiaMarket extends AppServer {
    private Logger logger = Logger.getLogger(WanDouJiaMarket.class);
    String searchUrl_WAN =null;
    String searchALLUrl_WAN = null;


    public WanDouJiaMarket() {

    }


    private void init() {
        searchUrl_WAN = PropertiesUtils.getProperty("searchUrl_WAN");
        searchALLUrl_WAN = PropertiesUtils.getProperty("searchALLUrl_WAN");
    }


    @Override
    public boolean getDatas(String str, String page) {
        List<String> resultlist = null;
        try {
            resultlist = getSearchInfos(str, page);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return URLDecoderTest.isExitsShelf(URLDecoderTest.URLdecode(str), resultlist);
    }


    /**
     * 根据关键字获取豌豆荚应用商店搜索结果列表信息。
     */
    public List<String> getSearchInfos(String searchWord, String pager) throws Exception {
           init();

        //先判断是不是首页
        // 目标搜索url
        if (pager == null || pager == "" || pager.equals("0")) {
            searchUrl_WAN = searchUrl_WAN + "key=" + searchWord + "&source=index";
        } else {
            searchUrl_WAN = (searchALLUrl_WAN += "page=" + pager + "&key=" + searchWord);
        }
//            searchUrl="https://www.wandoujia.com/search?key=%E6%AF%8F%E6%97%A5%E8%B5%9A%E7%82%B9&source=index";
        System.out.println("URL=" + searchUrl_WAN);

        String html = ConnUtils.testGet(searchUrl_WAN);
//        System.out.println("result=" + html);
        List<String> infoList = ParseFilterHTML(html);
        return infoList;

    }



    /**
     * 解析豌豆荚的返回集合
     *
     * @param html 要解析的html
     * @return 返回的解析的数据集合
     */
    public List<String> ParseFilterHTML(String html) {
        // 数据存放的集合
        List<String> infoList = new ArrayList<String>();
        // 所去所有节点
        Document doc = Jsoup.parse(html);
        // 获取目标根节点
        Elements elements = doc.getElementsByClass("col-left"); //有空格的不容易get
        // 有2个元素节点，但是是需要去第一个即可
        if (elements.size() > 0) {  //html 解析
            Element element = elements.get(0);
            Elements alist = element.getElementsByTag("a");

            //filter  collections
            for (int i = 0; i < alist.size(); i++) {
                String appName = alist.get(i).text();

                if (!appName.equals("") && !appName.equals("查看")) { //三种类别,把空的数值也给过滤掉
                    infoList.add(appName);
                    if (appName.equals("查看更多")) {  //截取0到查看更多：查看更多break 掉
                        break;
                    }

                } else {
                    continue;
                }
            }

            //filter key
            for (int i = 0; i < infoList.size(); i++) {

                //2.关键字过滤
                if ("查看更多".equals(infoList.get(i))) {
                    infoList.remove(i);
                }

            }
            //only for show
            for (int i = 0; i < infoList.size(); i++) {
                System.out.println(" Filter wandoujia AppName=" + infoList.get(i));
            }
        } else {     //返回的有时是另一种JSON解析方式，然后再解析
            // 先取出json 的数值
            getNextPageUrl(html);
//              throw  new  RuntimeException(" JSON数据解析");

        }


        System.out.println("wandoujia Size=" + infoList.size());
        return infoList;
    }


    /**
     * 解析JSON数据
     * 这里直接取出appName的数值，比较简单
     *
     * @param responseStr
     * @return
     */
    public List<String> getNextPageUrl(String responseStr) {
        List list = new ArrayList<>();
        JSONObject jsonObject = (JSONObject) JSON.parse(responseStr);
//        JSONObject jsonObjects = (JSONObject) jsonObject.get("obj");
        JSONObject jsonObjects = jsonObject.getJSONObject("data");
        if (jsonObjects != null) {
//            System.out.println(""+jsonObject);
            JSONObject jsonitem = (JSONObject) JSON.parse(jsonObjects.toJSONString()); //
            if (jsonitem != null) {
                String appDetailslist = jsonitem.getString("content");
//                System.out.println("aa"+appDetailslist);

                //拼成html 格式的再进行解析
                if ((!appDetailslist.startsWith("<html>"))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("<html><div class='col-left'>  " + appDetailslist);
                    sb.append("</div>");
                    sb.append("/html");
                     ParseFilterHTML(sb.toString());
                }
            }

        } else {

            logger.info(this.getClass().getName() + " 豌豆荚请求返回数据失败！ ");
            throw new RuntimeException("豌豆荚请求服务器返回失败!");

        }
        return null;
    }

    @Override
    public boolean PostDatas(String kw, String pns, String sid) throws IOException {
        return false;
    }



}

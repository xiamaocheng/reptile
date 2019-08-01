package com.moku;

import com.moku.model.Info;
import com.moku.utils.*;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Market360 extends  AppServer{

    private static String TAG = "CoreService";
    private Logger logger=Logger.getLogger(Market360.class);
    String searchUrl_360= null;
    public void  init() {

        searchUrl_360= PropertiesUtils.getProperty("searchUrl_360");
    }

    /**
     *
     * 根据关键字获取360应用市场搜索结果列表信息。
     *  这样解析太复杂，直接可以过滤比较，待以后改进
     */
    public  List<Info> getSearchInfos(String searchWord, String pager) throws Exception {
        // 搜索的链接
                 init();
        // 数据存放的集合
        List<Info> infoList = new ArrayList<Info>();
        // 目标搜索url
        if (pager == null || pager == "") {
            searchUrl_360 += searchWord;
        } else {
            searchUrl_360 += searchWord + "&page=" + pager;
        }
//        System.out.println("URL="+searchUrl);
//         searchUrl  ="http://zhushou.360.cn/search/index/?kw=每日赚点&page=2";
        // 获取源码
//        String html = ConnUtils.look(searchUrl);


        String html=  ConnUtils.testGet(searchUrl_360);
//        System.out.println("result="+html);
        // 所去所有节点
        Document doc = Jsoup.parse(html);
        // 获取目标根节点
        Elements elements = doc.getElementsByClass("SeaCon");
        // 只有一个元素节点
        Element element = elements.get(0);
        // 获取搜索信息
        String searchInfo = element.getElementsByClass("title_tr").get(0)
                .getElementsByClass("red")
                .get(0).text();
        // System.out.println(TAG + "：" + searchInfo);
        // 找到核心数据li列表
        Elements liElements = element.getElementsByTag("li");
        for (Element e : liElements) {
            Info info = new Info();
            //设置搜索信息
            info.setSearchNums(searchInfo);
            //获取app图标
            String appIocn = e.getElementsByTag("dt").get(0)
                    .getElementsByTag("a").get(0)
                    .getElementsByTag("img")
                    .get(0).attr("_src");
            info.setImgPath(appIocn);
            // 获取应用名
            String appName = e.getElementsByTag("dd").get(0)
                    .getElementsByTag("a").get(0).text();
            info.setName(appName);
            // 获取描述信息
            String appTips = e.getElementsByTag("dd").get(0)
                    .getElementsByTag("p").get(0).text();
            info.setTips(appTips);
            // 找到评分以及下载量
            String starsAndDownInfo = e.getElementsByClass("sdlft")
                    .get(0).text();
            info.setStars(starsAndDownInfo.substring(0,
                    starsAndDownInfo.indexOf("分 ") + 1));
            info.setDownNums(starsAndDownInfo.substring(starsAndDownInfo
                    .indexOf("分 ") + 2));
            //System.out.println(TAG + ":" + info.getStars() + "*"
            // + info.getDownNums());// ok
            // 开始获取下载链接
            String downPath = e.getElementsByClass("seaDown").get(0)
                    .getElementsByTag("a").attr("href");
            info.setDownPath(downPath);
            infoList.add(info);

            System.out.println(TAG + ":" + info);
        }
        return infoList;

    }


    //todo  这里的异常需要分类处理，这里直接抛出了异常
    @Override
    public  boolean getDatas(String str, String page) {

        List<Info> resultlist = null;
        try {
            resultlist = getSearchInfos(str, page);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("请求数据失败！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (resultlist.size()==0 ){ //两种情况，一种是查询不到，另一种是查询到结束了当然返回查询为0。
             str = StringFormat.format("content={}", new Object[]{Constant.RET_STRING});
                  logger.info(str);
        }
             ArrayList comlist=new ArrayList();
             for(int i=0;i<resultlist.size();i++){
                  String list = resultlist.get(i).getName();
                   comlist.add(list);
             }

            return URLDecoderTest.isExitsShelf(str,comlist);
    }








    @Override
    public boolean PostDatas(String kw, String pns, String sid) throws IOException {
        return false;
    }

    @Override
    public List<String> getNextPageUrl(String responseStr) {
        return null;
    }
}

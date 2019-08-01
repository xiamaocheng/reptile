package com.moku;

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


public class HuaweiMarket extends AppServer {
    private Logger logger=Logger.getLogger(HuaweiMarket.class);
    String searchUrl_huawei = null;


    public void init() {
        searchUrl_huawei= PropertiesUtils.getProperty("searchUrl_huawei");
    }

    @Override
    public boolean getDatas(String str, String page) {
        List<String> resultlist=null;
        try {
              resultlist = getSearchInfos(str, page);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return URLDecoderTest.isExitsShelf(str,resultlist);
    }


    /**
     *
     * 根据关键字获取华为应用市场搜索结果列表信息。
     */
    public  List<String> getSearchInfos(String searchWord, String pager) throws Exception {
        // 数据存放的集合
        List<String > infoList = new ArrayList<String >();

        // 搜索的链接
                   init();
        // 目标搜索url
        if (pager == null || pager == "") {
            searchUrl_huawei += searchWord;
        } else {
            searchUrl_huawei += searchWord + "/" + pager;
        }
        System.out.println("URL="+searchUrl_huawei);
//       String  searchUrl="http://app.hicloud.com/search/aa/1";
        String html=  ConnUtils.testGet(searchUrl_huawei);
//        System.out.println("result="+html);
        // 所去所有节点
          Document doc = Jsoup.parse(html);
        // 获取目标根节点
         Elements elements = doc.getElementsByClass("unit-main");
        // 有2个元素节点，但是是需要去第一个即可
        Element element = elements.get(0);
        Elements alist = element.getElementsByTag("a");
        for (Element e : alist) {
            String appName =    e.text();

            if(appName.equals("下载")|| appName.equals("")){//空的和下载不影响直接过滤了
                continue;
            }
            System.out.println("huawei AppName="+appName);
            infoList.add(appName);

        }
       System.out.println("huawei Size="+infoList.size());
        return infoList;

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

package com.moku;

import com.moku.utils.ConnUtils;
import com.moku.utils.PropertiesUtils;
import com.moku.utils.URLDecoderTest;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class XiaoMiMarket extends  AppServer{
    private Logger logger=Logger.getLogger(XiaoMiMarket.class);

      String searchUrl=null;
      String searchALLUrl=null;


    public XiaoMiMarket() {

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
     * 根据关键字获取小米应用商店搜索结果列表信息。
     */
    public  List<String> getSearchInfos(String searchWord, String pager) throws Exception {
              init();

        //先判断是不是首页
        // 目标搜索url
        if (pager == null || pager == "") {
             searchUrl = searchUrl+searchWord;
        } else {

            searchUrl= ( searchALLUrl += searchWord + "&typeall=phone&page=" + pager);
        }
//            searchUrl="http://app.mi.com/searchAll?keywords=QQ&typeall=phone&page=2";
        System.out.println("URL="+searchUrl);

        String html=  ConnUtils.testGet(searchUrl);
        System.out.println("result="+html);
        List<String> infoList = ParseFilterHTML(html);
        return infoList;

    }

    private void init() {
        searchUrl = PropertiesUtils.getProperty("searchUrl");
        searchALLUrl = PropertiesUtils.getProperty("searchALLUrl");
    }

    /**
     *  解析小米的返回集合
     * @param html  要解析的html
     * @return      返回的解析的数据集合
     */
    public List<String> ParseFilterHTML(String html) {
        // 数据存放的集合
        List<String > infoList = new ArrayList<String >();
        // 所去所有节点
        Document doc = Jsoup.parse(html);
        // 获取目标根节点
        Elements elements = doc.getElementsByClass("applist");
        // 有2个元素节点，但是是需要去第一个即可
        Element element = elements.get(0);
        Elements alist = element.getElementsByTag("a");

        //filter  collections
        for(int i=0;i<alist.size();i++){
            String appName =    alist.get(i).text();

            if (i%3==1 && !appName.equals("")){ //三种类别,把空的数值也给过滤掉
                System.out.println(" Filter xiaomi AppName="+appName);
                infoList.add(appName);
            }else{  //偶数的小米分类也不要了
                  continue;
            }
        }
        System.out.println("xiaomi Size="+infoList.size());
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

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

public class BaiduMarket  extends  AppServer {

     String  BAIDU_VISIT_URL =null;

    public void init() {
        BAIDU_VISIT_URL= PropertiesUtils.getProperty("BAIDU_VISIT_URL");
    }

    private Logger logger=Logger.getLogger(BaiduMarket.class);
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

        return URLDecoderTest.isExitsShelf(URLDecoderTest.URLdecode(str),resultlist);  //decode 解码后再比较
    }


    /**
     *
     * 根据关键字获取百度应用市场搜索结果列表信息。
     */
    public  List<String> getSearchInfos(String str, String page) throws Exception {

        String searchUrl=null;
                     init();
        // 目标搜索url
        if (page == null || page == "") {
            searchUrl = String.format(BAIDU_VISIT_URL, str, 0+"");
        } else {
            searchUrl = String.format(BAIDU_VISIT_URL, str, page); //
        }
//    searchUrl="https://shouji.baidu.com/s?data_type=app&multi=0&ajax=1&wd=%E6%9C%9F%E8%B4%A7%E8%B4%B5%E9%87%91%E5%B1%9E2018%E7%89%88&page=0";

        String html=  ConnUtils.testGet(searchUrl);
//        System.out.println("result="+html);
        List<String> infoList = ParseFilterHTML(html);
        return infoList;

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
        Elements elements = doc.getElementsByClass("app-list");
         if(elements.size()>0)// 证明获取不到对应的标签列上的数值,连续请求不是每一次都有数据的
         {
             // 有2个元素节点，但是是需要去第一个即可
             Element element = elements.get(0);
             Elements alist = element.getElementsByTag("a");

             //filter  collections
             for(int i=0;i<alist.size();i++){
                 String appName =    alist.get(i).text();

                 if ( !appName.equals("") && !appName.equals("扫描二维码") && !appName.equals("装进手机")){ //直接过滤标签数值
                     System.out.println(" Filter baidu AppName="+appName);
                     infoList.add(appName);
                 }else{
                     continue;
                 }
             }

         }else{
             logger.info( "error="+"百度市场 标签数据返回失败！");

         }



        System.out.println("baidu Size="+infoList.size());
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

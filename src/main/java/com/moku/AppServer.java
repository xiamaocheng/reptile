package com.moku;

import java.io.IOException;
import java.util.List;

public  abstract  class AppServer {

    /**
     * get 方式请求数据
     * @param str  请求的数据
     * @param page 请求的页的数目
     * @return
     */
    public abstract boolean getDatas(String str,String page);

    /**
     * Post  方式请求数据
     * @param kw   关键字
     * @param pns  pns 设置
     * @param sid  sid 设置
     * @return
     * @throws IOException
     */

    public  abstract boolean PostDatas(String kw,String pns,String sid) throws IOException;

    /**
     * 获取下一页的URL或解析URL
     * @param responseStr
     * @return
     */
    public abstract List<String> getNextPageUrl(String responseStr);


    /**
     *   获取网页查询信息
     * @param str
     * @param page
     * @return
     * @throws Exception
     */
    public abstract  List<?> getSearchInfos(String str, String page) throws Exception ;

}

package com.moku.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;


public class ConnUtils {


    private  static Logger logger=Logger.getLogger(ConnUtils.class);



    public static  String PostRequest(String url) throws IOException {


        //创建http客户端
//        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        //创建http request(GET)
        HttpPost  request = new HttpPost(url);

        //设置http request header
//        request.setHeader("authorization", "oauth c3cef7c66a1843f8b3a9e6a1e3160e20");
        //执行http请求
        CloseableHttpResponse response = httpClient.execute(request);
        //打印response
        String str = EntityUtils.toString(response.getEntity());
        logger.info("URL="+url+"\t"+"elapsed="+System.currentTimeMillis()+"\t"+"status="+response.getStatusLine().getStatusCode());
        close(httpClient, response);
        return str;
    }


    public static CloseableHttpClient getHttpClient() {

        CloseableHttpClient httpClient = HttpClients.createDefault();//如果不采用连接池就是这种方式获取连接*/
        return httpClient;
    }




    public static String  testGet(String url) throws Exception {


        String resStr=null;
        //创建一个httpclient对象
//		CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        //创建URIBuilder
        URIBuilder uri = new URIBuilder(url);


        //创建httpGet对象
        HttpGet hg = new HttpGet(uri.build());
        hg.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        //设置请求的报文头部的编码
        hg.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));

        //设置期望服务端返回的编码
        hg.setHeader(new BasicHeader("Accept", "application/json;text/plain;charset=utf-8"));  // 这里多加了Meizu JSON 格式的

        //请求服务
        CloseableHttpResponse response = httpClient.execute(hg);

        //获取响应码
        int statusCode = response.getStatusLine().getStatusCode();
        logger.info("URL="+uri+"\t"+"elapsed="+System.currentTimeMillis()+"\t"+"status="+statusCode);
        if (statusCode == 200) {

            //获取返回实例entity
            HttpEntity entity = response.getEntity();

            //通过EntityUtils的一个工具方法获取返回内容
            resStr = EntityUtils.toString(entity, "utf-8");

            //输出
//			System.out.println("请求成功,请求返回内容为: " + resStr);
        } else {

            //输出
            System.out.println("请求失败,错误码为: " + statusCode);
        }
        close(httpClient, response);


        return resStr;
    }

    public static void close(CloseableHttpClient httpClient, CloseableHttpResponse response) throws IOException {
        //关闭response和client
        response.close();
        httpClient.close();
    }

}

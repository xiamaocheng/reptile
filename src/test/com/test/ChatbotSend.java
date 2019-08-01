package com.test;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class ChatbotSend {

    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=aff3ce6a3112c5ac3204a466dd198057762cea9d29e9abf3e222a7eba17bd5c6";

  /*  public static void main(String args[]) throws Exception{

//       String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"我就是我, 是不一样的烟火\"}}";
         String textMsg="{\n" +
                 "     \"msgtype\": \"markdown\",\n" +
                 "     \"markdown\": {\"title\":\"杭州天气\",\n" +
                 "\"text\":\"#### 杭州天气  \\n > 9度，@1825718XXXX 西北风1级，空气良89，相对温度73%\\n\\n > ![screenshot](http://i01.lw.aliimg.com/media/lALPBbCc1ZhJGIvNAkzNBLA_1200_588.png)\\n  > ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \"\n" +
                 "     },\n" +
                 "    \"at\": {\n" +
                 "        \"atMobiles\": [\n" +
                 "            \"18352417539\"\n" +
                 "        ], \n" +
                 "        \"isAtAll\": false\n" +
                 "    }\n" +
                 " }";
        sendToChatbot(textMsg);
    }*/

    public static void sendToChatbot(final String msg) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();

        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        String textMsg = msg;
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
}
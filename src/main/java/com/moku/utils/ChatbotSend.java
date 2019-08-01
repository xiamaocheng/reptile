package com.moku.utils;

import com.moku.service.SendMsgService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class ChatbotSend  implements SendMsgService {
    private Logger logger=Logger.getLogger(ChatbotSend.class);

    private  HttpClient httpclient=null;
    private  HttpPost httppost=null;
    private  ChatbotSend() {
         httpclient = HttpClients.createDefault();

    }
    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=aff3ce6a3112c5ac3204a466dd198057762cea9d29e9abf3e222a7eba17bd5c6";


    @Override
    public void Notify(String strings)  {
        sendToChatbot(strings);
    }

    public   void sendToChatbot(final String msg)  {


        try {
        httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        String textMsg = msg;
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
        }catch ( Exception e){
             String string=e.getMessage();
             String str = StringFormat.format("content={}", new Object[]{string});
             this.logger.info(str);
        }

    }



}
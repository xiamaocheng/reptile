package com.json;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Group {

    public static void main(String[] args) throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("content","上架中");
        map.put("msgtype", "text");
        map.put("text",  map2);
        String textMsg = new Gson().toJson(map);

        System.out.println("send Msg="+textMsg);



    }



}

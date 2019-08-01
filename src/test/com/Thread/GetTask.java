package com.Thread;

import java.util.concurrent.*;

public class GetTask implements Callable<String> {

    private String name;

    private Integer sleepTimes;

    public GetTask(String name, Integer sleepTimes) {
        this.name = name;
        this.sleepTimes = sleepTimes;
    }




    public String call() throws Exception {

      /*  if (pkt.getMarket().equals("tencent")){

            if (!  appbaoImpl.check(pkt.getSoftwareName())){       //检测应用宝上的应用下架时，通知信息
                doNotifyInfo(pkt.getSoftwareName()+"应用宝");

            }
        }*/

        return "this is content : hello " + this.name;
    }



}

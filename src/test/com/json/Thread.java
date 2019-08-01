/*
package com.json;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

public class Thread {

    public List<Feed> lastestFeeds(String wxId) {
        ThreadPoolExecutor executor = ...;
        CountDownLatch latch = new CountDownLatch(7);
        List<Callable<String>> tasks = new ArrayList<>(7);
        tasks.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //1. 获取你得好友列表
                return ...;
            }
        });
        tasks.add(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //2. 去掉把你删除的好友
                return ...;
            }
        });

        });
        try {
//          List<Future<String>> futureList = executor.invokeAll(tasks);
            List<Future<String>> futureList = executor.invokeAll(tasks, 500, TimeUnit.MILLISECONDS);
            for(Future<String> future : futureList) {
                if(future.isCancelled()) {
                    //处理
                }
                try {
                    future.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
*/

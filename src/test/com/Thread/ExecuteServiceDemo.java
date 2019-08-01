package com.Thread;

import java.util.concurrent.*;

public class ExecuteServiceDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService(executorService);
        ExecuteServiceDemo executeServiceDemo = new ExecuteServiceDemo();
        // 十个
        long startTime = System.currentTimeMillis();

            GetTask getContentTask = new GetTask("micro" + 1, 10);

            completionService.submit(getContentTask);

        System.out.println("提交完任务，主线程空闲了, 可以去做一些事情。");
        // 假装做了8秒种其他事情
        try {
            Thread.sleep(8000);
            System.out.println("主线程做完了，等待结果");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            // 做完事情要结果

                Future<String> result = completionService.take();
                System.out.println(result.get());

            long endTime = System.currentTimeMillis();
            System.out.println("耗时 : " + (endTime - startTime) / 1000);
        }  catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
}

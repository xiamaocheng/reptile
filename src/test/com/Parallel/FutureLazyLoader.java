/*
package com.Parallel;

import com.yang.domain.BaseResult;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

*/
/**
 * @Description:
 * @Author: yangzl2008
 * @Date: 2016/1/9 19:06
 *//*

public class FutureLazyLoader {

    @Test
    public void test() {

        long start = System.currentTimeMillis();

        BaseResult baseResult1 = method1();// 耗时操作1，时间 time1
        BaseResult baseResult2 = method2();// 耗时操作2，时间 time2

        long end = System.currentTimeMillis();

        //总耗时 time = time1 + time2
        System.out.println("baseResult1 is " + baseResult1 + "\nbaseResult2 is " + baseResult2 + "\ntime cost is " + (end - start));
    }

    private BaseResult method1() {
        BaseResult baseResult = new BaseResult();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        baseResult.setCode(1);
        baseResult.setMsg("method1");
        return baseResult;
    }

    private BaseResult method2() {
        BaseResult baseResult = new BaseResult();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        baseResult.setCode(1);
        baseResult.setMsg("method2");
        return baseResult;
    }

}
*/

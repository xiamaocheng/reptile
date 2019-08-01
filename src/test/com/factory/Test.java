package com.factory;


/**
 * 客户端测试类
 *
 * @author Leo
 */
public class Test {
    public static void main(String[] args) {
        // 直接生产产品
        Sender senderEmail = SendFactory.produceEmail();
        // 发货
        senderEmail.send();
    }

}

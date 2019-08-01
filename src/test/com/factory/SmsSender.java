package com.factory;

class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("使用短信发送...");
    }

}

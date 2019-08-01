package com.factory;

class EmailSender implements Sender {

    @Override
    public void send() {
        System.out.println("使用电子邮箱发送...");
    }

}

package com.factory;

class SendFactory {
    public static Sender produceEmail() {
        return new EmailSender();
    }

    public static Sender produceSms() {
        return new SmsSender();
    }
}


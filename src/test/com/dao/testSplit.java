package com.dao;

public class testSplit {

    public static void main(String[] args) {
        StringBuffer strB = new StringBuffer();
        //目标字符串
        String str = ",A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,";
        //截取获得字符串数组
        String[] strArray = str.split(",");
        for (int i = 0; i < strArray.length; i++) {
            System.out.println(i + ":" + strArray[i]);

        }
        System.out.println("result:" + strB);

    }
}

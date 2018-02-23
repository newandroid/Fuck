package com.example;

/**
 * Created by Administrator on 2017/11/18.
 */

public class BaseObject {
    static {
        System.out.println("BaseObject init");
    }
    private int fakeInt = 0;
    private String fakeStr = "init";

    public String getFakeStr() {
        return fakeStr;
    }

    private void setFakeStr(String fakeStr) {
        this.fakeStr = fakeStr;
    }



    private void setFakeInt(int fakeInt) {
        this.fakeInt = fakeInt;
    }

    public int getFakeInt(){
        return fakeInt;
    }
}

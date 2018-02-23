package com.example.fullcontainer;

/**
 * Created by Administrator on 2017/11/23.
 */

public class SimpleAddress {
    private String value;

    public SimpleAddress(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + " " + value;
    }
}

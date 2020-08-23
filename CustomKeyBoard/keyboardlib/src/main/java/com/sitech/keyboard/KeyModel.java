package com.sitech.keyboard;

/**
 * Create
 * author  lingxu
 * date  2020/5/14 10:19.
 */
public class KeyModel {

    public KeyModel(int code,String label){
        this.code = code;
        this.label = label;
    }

    private String label;
    private int code;

    public String getLabel() {
        return label;
    }

    public int getCode() {
        return code;
    }
}
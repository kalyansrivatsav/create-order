package com.lti.dto;

public enum status {
    INPROCESS(401);

    private int value;

    public int getValue() {
        return value;
    }

    private status(int value){
        this.value=value;
    }
}

package com.mongo.mongo.query;

public class OneColAndData {
    private String keyData;
    private String keyValue;

    public OneColAndData(String keyData, String keyValue) {
        this.keyData = "\"" + keyData + "\"";
        this.keyValue = "\"" + keyValue + "\"";
    }

    @Override
    public String toString() {
        return keyData + ":" + keyValue;
    }
}

package com.karthiek.myapp.pojo;

public enum TradeType {
    B("B","Buy Stocks"),
    S("S","Sell Stocks");

    public String getCode() {
        return code;
    }

    private final String code;
    private final String desc;


    TradeType(String code, String desc) {
        this.code=code;
        this.desc=desc;
    }
}

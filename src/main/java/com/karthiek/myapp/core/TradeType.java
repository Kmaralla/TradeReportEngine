package com.karthiek.myapp.core;

public enum TradeType {
    BUY("B","Buy Stocks"),
    SELL("S","Sell Stocks");

    private final String code;
    private final String desc;


    TradeType(String code, String desc) {
        this.code=code;
        this.desc=desc;
    }
}

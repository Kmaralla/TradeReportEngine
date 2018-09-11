package com.karthiek.myapp.core;

import java.math.BigDecimal;

/**
 * This class reads PriceperUnit,Units,AgrredFx and returns
 * the Total USD Amount of the Trade.
 * Using BigDecimal to have Good Precision since dealing with Money
 */
public class TradeAmount {
    private BigDecimal pricePerUnit;
    private int units;
    private BigDecimal agreedFx;

    public TradeAmount(BigDecimal pricePerUnit, int units, BigDecimal agreedFx) {
        this.pricePerUnit = pricePerUnit;
        this.units = units;
        this.agreedFx = agreedFx;
    }
    public BigDecimal getTotalPrice(){
        return  pricePerUnit.
                multiply(BigDecimal.valueOf(units)).
                multiply(agreedFx);
    }
}

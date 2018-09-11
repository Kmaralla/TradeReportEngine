package com.karthiek.myapp.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class TradeDetails {

    private final String entity;
    private final TradeType type;
    private final Date instructionDate;
    private Date settlementDate;
    private final TradeAmount tradeAmount;
    private final String currency;

    public TradeDetails(String entity,
                        TradeType type,
                        String currency,
                        Date instructionDate,
                        Date settlementDate,
                        TradeAmount tradeAmount) {
        this.entity          = entity;
        this.type            = type;
        this.currency        = currency;
        this.instructionDate = instructionDate;
        this.settlementDate  = settlementDate;
        this.tradeAmount     = tradeAmount;
    }

    public String getEntity() {
        return entity;
    }

    public TradeType getType() {
        return type;
    }

    public Date getInstructionDate() {
        return instructionDate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount.getTotalPrice();
    }

    public String getCurrency() {
        return currency;
    }

    public void setSettlementDate(Date updatedSettlementDate) {
        this.settlementDate = updatedSettlementDate;
    }
}

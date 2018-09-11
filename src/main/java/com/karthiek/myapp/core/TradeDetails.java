package com.karthiek.myapp.core;

public class TradeDetails {

    private final String entity;
    private final TradeType type;
    private final LocalDate instructionDate;
    private final LocalDate settlementDate;
    private final TradeAmount tradeAmount;

    public TradeDetails(String entity,
                        TradeType type,
                        LocalDate instructionDate,
                        LocalDate settlementDate,
                        TradeAmount tradeAmount) {
        this.entity = entity;
        this.type = type;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.tradeAmount = tradeAmount;
    }
}

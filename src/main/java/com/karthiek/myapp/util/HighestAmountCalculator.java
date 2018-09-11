package com.karthiek.myapp.util;

import com.karthiek.myapp.pojo.TradeDetails;
import com.karthiek.myapp.pojo.TradeType;

import static java.util.stream.Collectors.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

public class HighestAmountCalculator {
    private static HashSet<TradeDetails> tradeDetails;

    public static void printReport(HashSet tradeDetailsSet,StringBuilder builder) {
        tradeDetails = tradeDetailsSet;
        printDailyIncomingHighestAmount(builder);
        printDailyOutgoingHighestAmount(builder);
    }

    private static void printDailyIncomingHighestAmount(StringBuilder builder) {
        Map<Date, BigDecimal> incomingMap = printDailyHighestAmount(TradeType.S);
        builder.append("Date")
                .append("                            |    ")
                .append("Daily Incoming Highest Amount")
                .append("\n");
        printMap(incomingMap,builder);
    }

    private static void printDailyOutgoingHighestAmount(StringBuilder builder) {
        Map<Date, BigDecimal> outgoingMap = printDailyHighestAmount(TradeType.B);
        builder.append("Date")
                .append("                            |    ")
                .append("Daily Outgoing Highest Amount")
                .append("\n");
        printMap(outgoingMap,builder);
    }



    private static Map<Date, BigDecimal> printDailyHighestAmount(TradeType tradeType) {
        return tradeDetails.stream()
                    .filter(details -> details.getType().equals(tradeType))
                    .collect(groupingBy(TradeDetails::getSettlementDate,              //GroupBy SettlementDate
                                         mapping(TradeDetails::getTradeAmount,        //getAmount for each Record
                                         reducing(BigDecimal.ZERO, BigDecimal::add))) //Reduce function for adding
                    );
    }

    private static void printMap(Map<Date, BigDecimal> map,StringBuilder builder) {

        for(Map.Entry<Date,BigDecimal> entry:map.entrySet()){
            builder.append(entry.getKey())
                    .append("    |    ")
                    .append(entry.getValue())
                    .append("\n\n");
        }
    }
}

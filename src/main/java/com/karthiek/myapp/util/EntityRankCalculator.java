package com.karthiek.myapp.util;

import com.karthiek.myapp.pojo.RankingEntities;
import com.karthiek.myapp.pojo.TradeDetails;
import com.karthiek.myapp.pojo.TradeType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.*;

public class EntityRankCalculator {

    public static void printReport(HashSet tradeDetailsSet,StringBuilder builder) {

        printDailyIncomingEntityRanking(tradeDetailsSet, builder);
        printDailyOutgoingEntityRanking(tradeDetailsSet, builder);
    }

    private static void printDailyIncomingEntityRanking(HashSet tradeDetailsSet, StringBuilder builder) {
        Map<Date, LinkedList<RankingEntities>> incomingMap = printDailyEntityRanking(tradeDetailsSet, TradeType.S);
        builder .append("Incoming:Settlement Date")
                .append("        |     ")
                .append("Rank")
                .append("                   |    ")
                .append("Entity")
                .append("\n");
        printEntitiesMap(incomingMap,builder);
    }


    private static void printDailyOutgoingEntityRanking(HashSet tradeDetailsSet, StringBuilder builder) {
        Map<Date, LinkedList<RankingEntities>> outgoingMap = printDailyEntityRanking(tradeDetailsSet, TradeType.B);
        builder .append("Outgoing:Settlement Date")
                .append("        |     ")
                .append("Rank")
                .append("                   |    ")
                .append("Entity")
                .append("\n");
        printEntitiesMap(outgoingMap,builder);
    }

    private static Map<Date, LinkedList<RankingEntities>> printDailyEntityRanking(HashSet<TradeDetails> tradeDetailsSet, TradeType tradeType) {
        final Map<Date, LinkedList<RankingEntities>> ranking = new HashMap<>();
        //HashSet<TradeDetails> copyOfOriginal = tradeDetails;

        tradeDetailsSet.stream()
                    .filter(details -> details.getType().equals(tradeType))
                    .collect(groupingBy(TradeDetails::getSettlementDate, toSet()))
                    .forEach((date, tradeDetails) -> {

                        final AtomicInteger rank = new AtomicInteger(0);
                        final LinkedList<RankingEntities> ranks = tradeDetails.stream()
                                                    //Sort the Amount
                                                       .sorted((a, b) -> b.getTradeAmount().compareTo(a.getTradeAmount()))
                                                    //Assign Incremented Rank to each Entity
                                                       .map(details -> new RankingEntities(rank.incrementAndGet(), details.getEntity(), date))
                                                    //Collect by adding it to LinkedList
                                                       .collect(toCollection(LinkedList::new));

                    ranking.put(date, ranks);});

        return ranking;
    }

    private static void printEntitiesMap(Map<Date, LinkedList<RankingEntities>> rankingMap , StringBuilder builder) {

        for (Map.Entry<Date,LinkedList<RankingEntities>> entry: rankingMap.entrySet()) {
            for (RankingEntities rankingEntities : entry.getValue()) {
                builder .append(entry.getKey())
                        .append("    |      ")
                        .append(rankingEntities.getRank())
                        .append("                     |    ")
                        .append(rankingEntities.getEntity())
                        .append("\n\n");
            }
        }
    }
}

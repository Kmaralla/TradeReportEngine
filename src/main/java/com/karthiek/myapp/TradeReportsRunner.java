package com.karthiek.myapp;

import com.karthiek.myapp.core.InitTradeDetailsStore;
import com.karthiek.myapp.core.WorkingDayChecker;
import com.karthiek.myapp.util.EntityRankCalculator;
import com.karthiek.myapp.util.HighestAmountCalculator;
import com.karthiek.myapp.util.TradeUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * @author kmaralla
 * @Date 10/09/18
 *
 * This is the main class which does the following up on invoking:
 * 1.Daily Incoming Total amount
 * 2.Daily Outgoing Total amount
 * 3.Incoming Highest Entity Ranking
 * 4.Outgoing Highest Entity Ranking
 *
 * To avoid Tight coupling with input in code, this reads input from externalized file
 *
 */
public class TradeReportsRunner {

    private static StringBuilder builder = new StringBuilder();//StringBuilder not synchronied but faster as assumption is single-Threaded

    public static void main(String[] args){
        try{
            InitTradeDetailsStore detailsStore = readInputAndStore();
            printReports(detailsStore);
        }catch(Exception e){
            System.err.println("Exception while reading input file:"+e.getMessage());
            throw new RuntimeException("Exception:",e);
        }
    }

    /**
     * Reads the input.txt file into List.Avoiding scanner here due to its obvious issues.
     * Parsing eachline and creating object for each Record.
     * Adding each object into Set
     *
     * @throws Exception
     */
    private static InitTradeDetailsStore readInputAndStore() throws Exception{
        List<String> lines                 = Collections.emptyList();
                     lines                 = Files.readAllLines(
                                                                Paths.get(
                                                                        ClassLoader.getSystemResource(TradeUtils.INPUT_FILE_PATH).toURI()),
                                                                StandardCharsets.UTF_8);
       return new InitTradeDetailsStore(lines);

    }

    private static void printReports(InitTradeDetailsStore detailsStore) {

        HashSet tradeDetailsSet = detailsStore.getTradeDetailsSet();

        WorkingDayChecker.updateIfNotWorkingDay(tradeDetailsSet);//Update SettlementDate to immediate Working Day
        HighestAmountCalculator.printReport(tradeDetailsSet, builder);//Print HighestAmount for Buy/Sell daily
        EntityRankCalculator.printReport(tradeDetailsSet, builder);//Rank Entities for Buy/Sell daily

        System.out.println("Reporting Engine starting...");
        System.out.println(builder);
        System.out.println("Reporting Engine Exiting...");

    }

    public static StringBuilder getBuilder() {
        return builder;
    }

}

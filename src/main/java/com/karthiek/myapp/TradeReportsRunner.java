package com.karthiek.myapp;

import com.karthiek.myapp.util.EntityRankCalculator;
import com.karthiek.myapp.util.HighestAmountCalculator;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;

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
    private static final String INPUT_FILE_PATH = "input.txt";

    public static void main(String[] args){
        try{
            readInputAndStore();
            printReports();
        }catch(Exception e){
            System.err.println("Exception while reading input file:"+e);
        }
    }

    private static void readInputAndStore() throws Exception{
        List<String> lines = Collections.emptyList();
        lines = Files.readAllLines(Paths.get(INPUT_FILE_PATH), StandardCharsets.UTF_8);
    }

    private static void printReports() {
        HighestAmountCalculator.printReport();
        EntityRankCalculator.printReport();

    }

}

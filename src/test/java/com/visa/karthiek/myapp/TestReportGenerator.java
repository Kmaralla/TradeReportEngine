package com.visa.karthiek.myapp;

import com.karthiek.myapp.core.InitTradeDetailsStore;
import com.karthiek.myapp.pojo.TradeDetails;
import com.karthiek.myapp.util.EntityRankCalculator;
import com.karthiek.myapp.util.HighestAmountCalculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class TestReportGenerator {

    private ArrayList<String> listOfLines;
    private HashSet<TradeDetails> tradeDetailsSet;

    @Before
    public void setup() throws Exception {
        listOfLines = new ArrayList<String>();
        listOfLines.add("foo S 0.50 SGP 01Jan2016 03Jan2016 300 100.25");//Sunday
        listOfLines.add("bar B 0.50 AED 01Jan2016 01Jan2016 100 100.25");//Friday

        InitTradeDetailsStore store = new InitTradeDetailsStore(listOfLines);
        tradeDetailsSet = store.getTradeDetailsSet();

    }

    @Test
    public void testHighestAmountCalculator() {
        try {
            StringBuilder builder = new StringBuilder();
            Assert.assertTrue(builder.length() == 0);

            HighestAmountCalculator.printReport(tradeDetailsSet, builder);
            Assert.assertTrue(builder.length() > 0);
            Assert.assertTrue(builder.indexOf("Jan 03") < builder.indexOf("Jan 01"));
        } catch (Exception e) {
            Assert.fail("Exception:" + e);
        }
    }

    @Test
    public void testEnitityRankCalculator() {
        try {
            StringBuilder builder = new StringBuilder();
            Assert.assertTrue(builder.length() == 0);

            EntityRankCalculator.printReport(tradeDetailsSet, builder);
            Assert.assertTrue(builder.length() > 0);
            Assert.assertTrue(builder.indexOf("foo") < builder.indexOf("bar"));
        } catch (Exception e) {
            Assert.fail("Exception:" + e);
        }
    }

    @After
    public void destroy() {
        listOfLines.clear();
        tradeDetailsSet.clear();
    }


}

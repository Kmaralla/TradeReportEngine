package com.visa.karthiek.myapp;

import com.karthiek.myapp.core.InitTradeDetailsStore;
import com.karthiek.myapp.core.WorkingDayChecker;
import com.karthiek.myapp.pojo.TradeDetails;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class TestInitTradeDetailsStore {

    private ArrayList<String> listOfLines;
    private HashSet<TradeDetails> tradeDetailsSet;

    @Before
    public void setup() {
        listOfLines = new ArrayList<String>();
        listOfLines.add("foo S 0.50 SGP 01Jan2016 03Jan2016 300 100.25");//Sunday
        listOfLines.add("bar B 0.50 AED 01Jan2016 01Jan2016 100 100.25");//Friday

    }

    @Test
    public void testWorkingDayChecker() {
        try {
            InitTradeDetailsStore store = new InitTradeDetailsStore(listOfLines);
            tradeDetailsSet = store.getTradeDetailsSet();
            for (TradeDetails details : tradeDetailsSet) {
                Calendar c = new GregorianCalendar();
                c.setTime(details.getSettlementDate());
                Assert.assertTrue(c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
            }

            WorkingDayChecker.updateIfNotWorkingDay(tradeDetailsSet);

            for (TradeDetails details : tradeDetailsSet) {
                Calendar c = new GregorianCalendar();
                c.setTime(details.getSettlementDate());
                Assert.assertFalse(c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
            }

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

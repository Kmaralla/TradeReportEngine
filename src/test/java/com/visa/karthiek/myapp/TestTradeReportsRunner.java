package com.visa.karthiek.myapp;

import com.karthiek.myapp.TradeReportsRunner;
import org.junit.Assert;
import org.junit.Test;

//This Integration tests End to End functionality
// TODO:Need to add more unit tests to test individual piece of code
public class TestTradeReportsRunner {
    @Test
    public void testMainClass() {
        Assert.assertEquals(TradeReportsRunner.getBuilder().length(), 0);
        TradeReportsRunner.main(null);
        Assert.assertFalse(TradeReportsRunner.getBuilder().length() == 0);
    }
}

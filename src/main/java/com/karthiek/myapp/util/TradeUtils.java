package com.karthiek.myapp.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TradeUtils {
    private String[] differentWeekendCurrencies = new String[]{"AED","SAR"};
    public Date getSettlementDate(String currency, Date settlementDate){
        while(isWeekEnd(currency,settlementDate)){
            settlementDate=forwardOneDay(settlementDate);
        }
        return settlementDate;
    }

    private Date forwardOneDay(Date settlementDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(settlementDate);
        calendar.add(Calendar.DATE,1);
        return calendar.getTime();

    }

    /**
     * If currency is AED or SAR, weekend is Friday and Saturday
     * else weekend is Saturday and sunday
     *
     * @param currency
     * @param settlementDate
     * @return
     */
    private boolean isWeekEnd(String currency, Date settlementDate) {
        Calendar c = new GregorianCalendar();
        c.setTime(settlementDate);

        for(String givenCurrency:differentWeekendCurrencies){
            if(givenCurrency==currency){//For Currencies AED and SAR
                if(c.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY || c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY ){
                    return true;
                }
            }
        }

        //For other currencies
        if(c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY ){
            return true;
        }

        return false;

    }
}

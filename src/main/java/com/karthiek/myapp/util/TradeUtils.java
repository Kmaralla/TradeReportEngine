package com.karthiek.myapp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author kmaralla
 * @Date 10/09/18
 * This Utility class has:
 * 1.Constant fields
 * 2.Utility static methods to do certain Task
 */
public class TradeUtils {
    public  static final  String   SPACE_DELIMITER              = " ";
    public  static final  String   INPUT_FILE_PATH              = "input.txt";
    private static final  String[] differentWeekendCurrencies   = new String[]{"AED","SAR"};
    private static final  String   datePattern                  = "DDMMMYYYY";


    public static Date convertToDate(String dateInString) throws ParseException {
        DateFormat format = new SimpleDateFormat(datePattern, Locale.ENGLISH);
        return format.parse(dateInString);
    }

    public static Date getSettlementDate(String currency, Date settlementDate){
        while(isWeekEnd(currency,settlementDate)){
            settlementDate=forwardOneDay(settlementDate);
        }
        return settlementDate;
    }


    /**
     * If currency is AED or SAR, weekend is Friday and Saturday
     * else weekend is Saturday and sunday
     *
     * @param currency
     * @param settlementDate
     * @return
     */
    private static boolean isWeekEnd(String currency, Date settlementDate) {
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

    private static Date forwardOneDay(Date settlementDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(settlementDate);
        calendar.add(Calendar.DATE,1);
        return calendar.getTime();

    }
}

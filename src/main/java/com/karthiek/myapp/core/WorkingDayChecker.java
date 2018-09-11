package com.karthiek.myapp.core;

import com.karthiek.myapp.pojo.TradeDetails;
import com.karthiek.myapp.util.TradeUtils;

import java.util.Date;
import java.util.HashSet;

/**
 * @author kmaralla
 * @date 10/09/18
 * This method checks if the given SettlementDate falls on Weekend
 * If yes, update SettlementDate to next immediate working day
 * Call Utility methods which does work
 */

public class WorkingDayChecker {
    public static void updateIfNotWorkingDay(HashSet<TradeDetails> tradeDetailsSet) {
        for (TradeDetails tradeDetails : tradeDetailsSet) {
            Date updatedSettlementDate = TradeUtils.updateSettlementDateIfWeekend(tradeDetails.getCurrency(), tradeDetails.getSettlementDate());
            tradeDetails.setSettlementDate(updatedSettlementDate);//TODO avoid setting if not changed
        }
    }
}

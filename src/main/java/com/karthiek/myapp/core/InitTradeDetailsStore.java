package com.karthiek.myapp.core;

import com.karthiek.myapp.pojo.TradeAmount;
import com.karthiek.myapp.pojo.TradeDetails;
import com.karthiek.myapp.pojo.TradeType;
import com.karthiek.myapp.util.TradeUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author kmaralla
 * @Date 10/09/18
 * This class parses each line we read, Creates Object and add into set.
 */
public class InitTradeDetailsStore {
    private HashSet<TradeDetails> tradeDetailsSet;

    public InitTradeDetailsStore(List<String> lines) throws Exception {
        try {
            tradeDetailsSet = new HashSet<>(lines.size());

            for (String line : lines) {
                String[] clientInstructions = line.split(TradeUtils.SPACE_DELIMITER);
                String entity               = clientInstructions[0];
                TradeType type              = TradeType.valueOf(clientInstructions[1]);
                BigDecimal agreedFx         = new BigDecimal(clientInstructions[2]);
                String currency             = clientInstructions[3];
                Date instructionDate        = TradeUtils.convertToDate(clientInstructions[4]);
                Date settlementDate         = TradeUtils.convertToDate(clientInstructions[5]);
                int units                   = Integer.valueOf(clientInstructions[6]);
                BigDecimal pricePerUnit     = new BigDecimal(clientInstructions[7]);

                TradeAmount amount          = new TradeAmount(pricePerUnit, units, agreedFx);
                TradeDetails tradeDetails   = new TradeDetails(entity,type,currency,instructionDate,settlementDate,amount);

                tradeDetailsSet.add(tradeDetails);
            }
            System.out.println("Input file is succesfully loaded and Parsed into memory");
        }catch (Exception e){
            System.err.println("Exception while parsing the input:"+e);
            throw e;
        }
    }

    /**
     * This method returns all teh Data stored inside Set of TradeDetails objects.
     * @return
     */
    public HashSet<TradeDetails> getTradeDetailsSet() {
        return tradeDetailsSet;
    }
}

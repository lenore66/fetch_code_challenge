package org.example.util;

import org.example.models.Receipt;

import javax.validation.constraints.NotBlank;

public class ReceiptPointsCalculatorUtil {

    public  Integer caculatePoints(Receipt receipt){

        Integer totalPoints = 0;

       totalPoints += countLettersInRetailer(receipt.getRetailer());
        //rounded dollar amount boolean
        //every item in reciept
        //trimmed length of the deceription is a multiple of three
        // total is greater than 10.00
        // purchase date is odd.
        //purchase is after 2:00pm and before 4:00pm

        return 0;
    }

    private static Integer countLettersInRetailer(String retailer) {
        char[] charArray = retailer.toCharArray();
        return charArray.length;
    }

}

package org.example.util;

import org.example.models.Item;
import org.example.models.Receipt;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReceiptPointsCalculatorUtil {

    public Double calculatePoints(Receipt receipt) {

        Double totalPoints = 0.0;

        totalPoints += countLettersInRetailer(receipt.getRetailer());

        if (isTotalNumberRound(Double.parseDouble(receipt.getTotal()))) {
            totalPoints += 50;
        }
        if (isTotalNumberQuarter(Double.parseDouble(receipt.getTotal()))) {
            totalPoints += 25;
        }
        totalPoints = 5 * getPointsForItems(receipt.getItems());

        totalPoints = getPointsFromItemsDescription(receipt.getItems());
        if (isGreaterThanTen(receipt.getTotal())) {
            totalPoints += 5;
        }
        ;
        // purchase date is odd.
        if (isPurchaseDateOdd(receipt.getPurchaseDate())) {
            totalPoints += 6;
        }

        //purchase is after 2:00pm and before 4:00pm
        if (isPurchaseIsMadeInAfternoon(receipt.getPurchaseTime())) {
            totalPoints += 10;
        }
        ;
        return totalPoints;
    }

    private boolean isPurchaseIsMadeInAfternoon(String purchaseTime) {
        LocalTime localTime = LocalTime.parse(purchaseTime);

        return (localTime.isAfter(LocalTime.of(14, 00)) && localTime.isBefore(LocalTime.of(16, 00)));
    }

    private boolean isPurchaseDateOdd(@NotBlank(message = "Field 'purchaseDate' is required") String purchaseDate) {
        LocalDate localDate = LocalDate.parse(purchaseDate);
        return localDate.getDayOfMonth() % 2 == 1;
    }

    private boolean isGreaterThanTen(@NotBlank(message = "Field 'total' is required") String total) {
        return Double.parseDouble(total) > 10;
    }

    private static final double POINT_MULTIPLIER = 0.2;

    private Double getPointsFromItemsDescription(List<Item> items) {
        return items.stream()
                .filter(this::isEligibleForPoints)
                .mapToDouble(item -> (Double.parseDouble(item.getPrice()) * POINT_MULTIPLIER))
                .sum();
    }

    private boolean isEligibleForPoints(Item item) {
        return item.getShortDescription().trim().length() % 3 == 0;
    }

    private Double getPointsForItems(List<Item> items) {
        Double countOfItems = Double.valueOf(items.stream().count());
        return countOfItems % 2;
    }

    private Integer countLettersInRetailer(String retailer) {
        char[] charArray = retailer.toCharArray();
        return charArray.length;
    }

    private boolean isTotalNumberRound(Double total) {
        return total % 1 == 0;
    }

    private boolean isTotalNumberQuarter(Double total) {
        return total % 25 == 0;
    }
}

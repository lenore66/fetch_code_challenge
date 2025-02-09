package org.example.util;

import org.example.models.Item;
import org.example.models.Receipt;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Configuration
public class ReceiptPointsCalculatorUtil {

    public Double calculatePoints(Receipt receipt) {

        Double totalPoints = 0.0;

        totalPoints += countLettersInRetailer(receipt.retailer);

        if (isTotalNumberRound(Double.parseDouble(receipt.total))) {
            totalPoints += 50;
        }

        if (isTotalNumberQuarter(Double.parseDouble(receipt.total))) {
            totalPoints += 25;
        }
        totalPoints += getPointsForItems(receipt.items);

        totalPoints += getPointsFromItemsDescription(receipt.items);
        if (isGreaterThanTen(receipt.total)) {
            totalPoints += 5;
        }
        ;

        if (isPurchaseDateOdd(receipt.purchaseDate)) {
            totalPoints += 6;
        }


        if (isPurchaseIsMadeInAfternoon(receipt.purchaseTime)) {
            totalPoints += 10;
        }

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


    private Double getPointsFromItemsDescription(List<Item> items) {
        return items.stream()
                .filter(this::isEligibleForPoints)
                .mapToDouble(item -> (Double.parseDouble(item.price) * 0.2))
                .sum();
    }

    private boolean isEligibleForPoints(Item item) {
        return item.shortDescription.trim().length() % 3 == 0;
    }

    private Double getPointsForItems(List<Item> items) {
        Double countOfItems = Double.valueOf(items.size());

        return  5.0 * (countOfItems /2);
    }

    private Double countLettersInRetailer(String retailer) {
        return (double) retailer.chars()
                .filter(Character::isLetterOrDigit)
                .count();

    }

    private boolean isTotalNumberRound(Double total) {
        return total % 1 == 0;
    }

    private boolean isTotalNumberQuarter(Double total) {
        return total % 0.25 == 0;
    }
}

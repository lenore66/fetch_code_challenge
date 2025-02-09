package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.models.Id;
import org.example.models.Receipt;
import org.example.models.ReceiptsPoints;
import org.example.repoisitory.ReceiptRepository;
import org.example.util.ReceiptPointsCalculatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;


    @Autowired
    ReceiptPointsCalculatorUtil receiptPointsCalculatorUtil;

    public ReceiptsPoints getReceiptsPoints(String reciptId) {
        ReceiptsPoints receiptsPoints = new ReceiptsPoints();

        Receipt receipt = Optional.of(receiptRepository.findById(reciptId).get()).orElseThrow(() -> new EntityNotFoundException("Receipt not found for ID: " + reciptId));

        if (receipt != null) {
            receiptsPoints.points = receiptPointsCalculatorUtil.calculatePoints(receipt);
            return receiptsPoints;
        } else {
            return null;
        }


    }

    @Override
    public Id processReceipt(Receipt receipt) {
        Id newId = new Id();
        ensureReceiptHasId(receipt);

        receiptRepository.save(receipt);

        System.out.println(receipt.id + " id was saved to Database");
        newId.id = receipt.id;
        return newId;
    }

    private void ensureReceiptHasId(Receipt receipt) {
        if (receipt.id == null) {
            receipt.id = UUID.randomUUID().toString();
        }
    }


}

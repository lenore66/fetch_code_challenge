package org.example.service;

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

    public ReceiptsPoints getReceiptsPoints(Id reciptId) {
        ReceiptsPoints receiptsPoints = new ReceiptsPoints();

        Receipt receipt = receiptRepository.findById(reciptId).get();

        receiptsPoints.points =  receiptPointsCalculatorUtil.calculatePoints(receipt);

        return receiptsPoints;

    }

    @Override
    public Id processReceipt(Receipt receipt) {
    if(receipt.getId() == null) {
        Id id = new Id();
      id.id = UUID.randomUUID().toString();
      receipt.setId(id);
      System.out.println(" Id saved for receipt is " + receipt.getId());
     }

        receiptRepository.save(receipt);
        return receipt.getId();
    }


}

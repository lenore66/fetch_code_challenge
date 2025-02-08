package org.example.service;

import lombok.AllArgsConstructor;
import org.example.models.Id;
import org.example.models.Receipt;
import org.example.models.ReceiptsPoints;
import org.example.repoisitory.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    ReceiptRepository receiptRepository;


    public ReceiptsPoints getReceiptsPoints(String reciptId){
        Id id = new Id(reciptId);
        Receipt receipt = receiptRepository.findById(id);
       //Recpit point logic here


        return receiptsPoints;

    }

    @Override
    public Id processReceipt(Receipt receipt) {
        Id id = new Id (UUID.randomUUID().toString());
        receipt.setId(id);
        receiptRepository.save(receipt);
     return id;
    }


}

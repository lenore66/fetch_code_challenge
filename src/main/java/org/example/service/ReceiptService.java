package org.example.service;

import org.example.models.Id;
import org.example.models.Receipt;
import org.example.models.ReceiptsPoints;

public interface ReceiptService {
    ReceiptsPoints getReceiptsPoints(Id id);
    Id processReceipt(Receipt receiptId);
}

package org.example.controller;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.models.Id;
import org.example.models.Receipt;
import org.example.models.ReceiptsPoints;
import org.example.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ReceiptController {

    @Autowired
   private  ReceiptService receiptService;


   @GetMapping("/receipts/{id}/points")
   public ReceiptsPoints getPoints(@PathVariable Id id) {
       return receiptService.getReceiptsPoints(id);
   }
   //TODO add 401 and 404 handling
   @PostMapping("/receipts/process")
    public Id getReceipt(@RequestBody Receipt receipt) {

       return receiptService.processReceipt(receipt);

   }
}

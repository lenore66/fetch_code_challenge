package org.example.controller;

import lombok.AllArgsConstructor;
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

   @RequestMapping(value = "receipts/{id}/points" , method = RequestMethod.GET)
   public ReceiptsPoints getPoints(@PathVariable(name = "id", required = true) String id) {
       return receiptService.getReceiptsPoints(id);
   }


  @RequestMapping(value = "receipts/process", method = RequestMethod.POST )
    public @ResponseBody Id getReceipt(@RequestBody(required = true) Receipt receipt ) {
       return receiptService.processReceipt(receipt);
   }
}

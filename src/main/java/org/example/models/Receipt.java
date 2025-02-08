package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {

    @NotBlank(message = "Field 'retailer' is required")
    private String retailer;
    @NotBlank(message = "Field 'purchaseDate' is required")
    private String purchaseDate;
    @NotBlank(message = "Field 'purchaseTime' is required")
    private String purchaseTime;
    @NotBlank(message = "Field 'items' is required")
    private List<Item> items;
    @NotBlank(message = "Field 'total' is required")
    private String total;
    private Id id;

}
package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Entity
@Table(name = "RECEIPTS")
public class Receipt {

    @NotBlank(message = "Field 'retailer' is required")
    @Column(name = "RETAILER", nullable = false)
    @JsonProperty("retailer")
    public String retailer;

    @NotBlank(message = "Field 'purchaseDate' is required")
    @Column(name = "PURCHASE_DATE", nullable = false)
    @JsonProperty("purchaseDate")
    public String purchaseDate;

    @NotBlank(message = "Field 'purchaseTime' is required")
    @Column(name = "PURCHASE_TIME")
    @JsonProperty("purchaseTime")
    public String purchaseTime;


    @NotBlank(message = "Field 'items' is required")
    @JsonProperty("items")
    @ElementCollection
    public List<Item> items;

    @NotBlank(message = "Field 'total' is required")
    @Column(name = "TOTAL")
    @JsonProperty("total")
    public String total;

    @Id
    @Column(name = "ID")
    @JsonProperty("id")
    public String id;

}
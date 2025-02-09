package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Item implements Serializable {


    @Column(name = "PRICE" ,nullable = false)
    @JsonProperty("price")
    public String price;


    @Column(name = "SHORT_DESCRIPTION", nullable = false)
    @JsonProperty("shortDescription")
    public String shortDescription;


}


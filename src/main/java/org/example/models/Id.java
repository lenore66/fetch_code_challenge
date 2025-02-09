package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.jackson.Jacksonized;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class Id {
     @JsonProperty("id")
     public String id;
}

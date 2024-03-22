package ru.yarn.yarnstore.entities.playLoadClasses;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String productName;
    private long productId;
    private double price;
    private int quantity;
}

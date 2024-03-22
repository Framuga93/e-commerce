package ru.yarn.yarnstore.entities.playLoadClasses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    private String name;
    private double price;
    private int quantity;

}

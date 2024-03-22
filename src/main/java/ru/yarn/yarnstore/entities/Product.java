package ru.yarn.yarnstore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.lang.NonNull;

@Entity
@Data
@NoArgsConstructor
public class Product {

    public Product(@NonNull String name, @NonNull Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    private Double price;

    private int quantity;
}

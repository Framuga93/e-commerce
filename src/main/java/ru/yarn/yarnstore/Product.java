package ru.yarn.yarnstore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column
    String name;

    @Column
    Double price;

    @Column
    String pictureUrl;
}

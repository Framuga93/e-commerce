package gb.demochkin.ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
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

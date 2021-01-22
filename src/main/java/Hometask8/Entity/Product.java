package Hometask8.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products_table")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "cost_fld")
    private int cost;
}
package kln.project.farmerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "shop_product")
public class ShopProduct implements Serializable {

    @EmbeddedId
    ShopProductId shopproductid;


    //available
    @Column(name = "amount",nullable = false)
    private Integer amount;

    @Column(name="price",nullable = false, precision = 10, scale = 2)
    private Double price;

    // Constructors, getters, and setters
}

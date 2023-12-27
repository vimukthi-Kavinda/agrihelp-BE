package kln.project.marketservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "shop_product")
public class ShopProduct implements Serializable {

    @EmbeddedId
    ShopProductId shopProductId;


    //available
    @Column(name = "amount",nullable = false)
    private Integer amount;

    @Column(name="price",nullable = false, precision = 10, scale = 2)
    private Double price;

    // Constructors, getters, and setters
}

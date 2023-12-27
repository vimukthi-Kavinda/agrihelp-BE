package kln.project.farmerservice.model.entity;


import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    //fertilizer pesticide machine

    @Column(name="category",length = 10, nullable = false)
    private String category;

    @Column(name = "product_name", length = 20, nullable = false)
    private String productname;

    @Column(name = "price",nullable = false, precision = 8, scale = 2)
    private Double price;

    @Column(name = "manufactured_by")
    private String manufacturedBy;

    @Column(name = "imported_by")
    private String importedBy;

    @Column(name = "exp_date")
    private String expDate;

    @Column(name="img_name",length = 100)
    private String imgname;

    @Column(name="usge")
    private String usge;
    // Constructors, getters, and setters
}


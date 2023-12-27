package kln.project.shopservice.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "product_pesticide")
public class ProductPesticide implements Serializable {

    @EmbeddedId
    ProductPesticideId productPesticideId;


    // Constructors, getters, and setters
}


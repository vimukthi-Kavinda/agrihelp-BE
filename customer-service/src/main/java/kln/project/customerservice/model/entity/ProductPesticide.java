package kln.project.customerservice.model.entity;

import jakarta.persistence.*;
import kln.project.customerservice.model.entity.ProductPesticideId;
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


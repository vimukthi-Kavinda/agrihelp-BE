package kln.project.officerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "product_pesticide")
public class ProductPesticide implements Serializable {

    @EmbeddedId
    ProductPesticideId productpesticideid;


    // Constructors, getters, and setters
}


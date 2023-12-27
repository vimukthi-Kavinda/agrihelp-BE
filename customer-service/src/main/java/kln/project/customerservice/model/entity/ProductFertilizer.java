package kln.project.customerservice.model.entity;

import jakarta.persistence.*;
import kln.project.customerservice.model.entity.ProductFertilizerId;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "product_fertilizer")
public class ProductFertilizer implements Serializable {
    @EmbeddedId
    private ProductFertilizerId productFertilizerId;



    // Constructors, getters, and setters
}


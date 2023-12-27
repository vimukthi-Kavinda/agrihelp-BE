package kln.project.componentservice.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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


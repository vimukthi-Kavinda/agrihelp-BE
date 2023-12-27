package kln.project.farmerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "product_fertilizer")
public class ProductFertilizer implements Serializable {
    @EmbeddedId
    private ProductFertilizerId productfertilizerid;



    // Constructors, getters, and setters
}


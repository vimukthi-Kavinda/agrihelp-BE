package kln.project.componentservice.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "farmer_crop")

public class FarmerCrop implements Serializable {

    @EmbeddedId
   private FarmerCropId farmerCropId;

    @Column(name = "expected_harvest")
    private Double expectedHarvest;

    // Constructors, getters, and setters
}

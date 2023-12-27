package kln.project.customerservice.model.entity;


import jakarta.persistence.*;
import kln.project.customerservice.model.entity.FarmerCropId;
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

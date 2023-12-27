package kln.project.customerservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FarmerCropId implements Serializable {


    @ManyToOne
    @JoinColumn(name = "farmer_id", referencedColumnName = "farmer_id")
    private Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "cropcode", referencedColumnName = "cropcode")
    private Crop crop;
}

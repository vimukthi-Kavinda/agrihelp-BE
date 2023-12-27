package kln.project.marketservice.model.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CropDiseaseId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "cropcode", referencedColumnName = "cropcode")
    private Crop crop;


    @ManyToOne
    @JoinColumn(name = "deciese_code", referencedColumnName = "deciese_code")
    private Disease disease;
}

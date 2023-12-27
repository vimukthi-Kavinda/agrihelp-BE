package kln.project.customerservice.model.entity;

import jakarta.persistence.*;
import kln.project.customerservice.model.entity.CropDiseaseId;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "crop_deciese")
@Data
public class CropDisease implements Serializable {

    @EmbeddedId
    private CropDiseaseId cropDiseaseId;




}


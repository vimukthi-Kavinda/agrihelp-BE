package kln.project.farmerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "crop_deciese")
@Data
public class CropDisease implements Serializable {

    @EmbeddedId
    private CropDiseaseId cropdiseaseid;




}


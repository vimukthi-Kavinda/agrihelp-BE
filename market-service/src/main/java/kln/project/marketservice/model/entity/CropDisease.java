package kln.project.marketservice.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "crop_deciese")
@Data
public class CropDisease implements Serializable {

    @EmbeddedId
    private CropDiseaseId cropdiseaseid;




}


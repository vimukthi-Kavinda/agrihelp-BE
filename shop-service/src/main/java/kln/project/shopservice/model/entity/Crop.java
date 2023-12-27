package kln.project.shopservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "crop")
public class Crop {
    @Id
    @Column(name = "cropcode", length = 8)
    private String cropCode;

    @Column(name = "crop_breed_name", length = 15)
    private String cropbreedname;

    @Column(name = "scientific_name", length = 30)
    private String scientificName;

    @Column(name = "watermgt", length = 750)
    private String watermgt;

    @Column(name = "weedmgt", length = 750)
    private String weedmgt;

    @Column(name = "fertilizerusg", length = 750)
    private String fertilizerusg;
    // Constructors, getters, and setters
}


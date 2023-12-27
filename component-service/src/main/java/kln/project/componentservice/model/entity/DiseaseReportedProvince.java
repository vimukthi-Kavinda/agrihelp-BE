package kln.project.componentservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "deciese_reported_province")

public class DiseaseReportedProvince implements Serializable {

    @EmbeddedId
    DiseaseReportedProvinceId diseaseReportedProvinceId;



    @Column(name = "first_reported_day")
    private Date firstReportedDay;

    // Constructors, getters, and setters
}

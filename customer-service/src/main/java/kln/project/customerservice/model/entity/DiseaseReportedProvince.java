package kln.project.customerservice.model.entity;

import jakarta.persistence.*;
import kln.project.customerservice.model.entity.DiseaseReportedProvinceId;
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

package kln.project.officerservice.model.entity;

import jakarta.persistence.*;
import kln.project.officerservice.model.entity.Province;
import lombok.Data;

@Entity
@Data
@Table(name = "district")
public class District {
    @Id
    @Column(name = "districtid", length = 6)
    private String districtId;

    @Column(name = "district_name", length = 15)
    private String districtName;

    @ManyToOne
    @JoinColumn(name = "provinceid", referencedColumnName = "provinceid")
    private Province province;

    // Constructors, getters, and setters
}
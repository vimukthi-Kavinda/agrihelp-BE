package kln.project.farmerservice.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "area")
@Getter
@Setter
public class Area {
    @Id
    @Column(name = "areacode", length = 6)
    private String areacode;

    @Column(name = "area_name", length = 15)
    private String areaname;

    @ManyToOne
    @JoinColumn(name = "districtid", referencedColumnName = "districtid")
    private District district;

    // Constructors, getters, and setters
}
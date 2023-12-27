package kln.project.shopservice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "area")
@Getter
@Setter
public class Area {
    @Id
    @Column(name = "areacode", length = 6)
    private String areaCode;

    @Column(name = "area_name", length = 15)
    private String areaName;

    @ManyToOne
    @JoinColumn(name = "districtid", referencedColumnName = "districtid")
    private District district;

    // Constructors, getters, and setters
}
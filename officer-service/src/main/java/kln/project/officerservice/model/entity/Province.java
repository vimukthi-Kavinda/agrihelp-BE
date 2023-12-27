package kln.project.officerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "province")
public class Province {
    @Id
    @Column(name = "provinceid", length = 4)
    private String provinceId;

    @Column(name = "province_name", length = 15)
    private String provinceName;

    // Constructors, getters, and setters
}

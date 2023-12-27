package kln.project.marketservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

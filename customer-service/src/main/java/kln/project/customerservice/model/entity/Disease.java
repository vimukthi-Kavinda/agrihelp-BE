package kln.project.customerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "deciese")
@Data
public class Disease {
    @Id
    @Column(name = "deciese_code", length = 8)
    private String diseaseCode;

    @Column(name = "deciese_name", length = 15)
    private String diseaseName;

    @Column(name = "root_cause", length = 50)
    private String rootCause;

    @Column(name = "deciese_description", length = 150)
    private String diseaseDescription;

    @Column(name = "spreading_method", length = 20)
    private String spreadingMethod;

    @Column(length = 150)
    private String remedies;

    @ManyToOne
    @JoinColumn(name = "posted_officer", referencedColumnName = "officer_id")
    private Officer postedofficer;

    // Constructors, getters, and setters
}

package kln.project.farmerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "officer")
public class Officer {
    @Id
    @Column(name = "officer_id", length = 10)
    private String officerId;

    @Column(length = 20)
    private String username;

    @Column(length = 25)
    private String password;

    @Column(length = 10)
    private String nic;

    @Column(name = "officer_name", length = 20)
    private String officerName;

    @Column(length = 15)
    private String address;

    @Column(length = 12)
    private String telno;

    @Column(length = 35)
    private String email;

    @ManyToOne
    @JoinColumn(name = "assigned_area", referencedColumnName = "areacode")
    private Area assignedArea;

    @ManyToOne
    @JoinColumn(name = "verified_officer", referencedColumnName = "officer_id")
    private Officer verifiedOfficer;

    @ManyToOne
    @JoinColumn(name = "specialty",referencedColumnName = "specialtycode")
    private Officerspecialty specialty;

    // Constructors, getters, and setters
}

package kln.project.marketservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "temp_officer")
public class TempOfficer {
    @Id
    @Column(name = "officer_id", length = 10)
    private String officerId;

    @Column(unique = true,name = "username",length = 20)
    private String username;

    @Column(name = "password",length = 25)
    private String password;

    @Column(name = "nic",length = 10)
    private String nic;

    @Column(name = "officer_name", length = 20)
    private String officerName;

    @Column(name = "address",length = 15)
    private String address;

    @Column(name = "telno",length = 12)
    private String telno;

    @Column(name = "email",length = 35)
    private String email;

    @ManyToOne
    @JoinColumn(name = "assigned_area", referencedColumnName = "areacode")
    private Area assignedArea;

    @ManyToOne
    @JoinColumn(name = "specialty",referencedColumnName = "specialtycode")
    private Officerspecialty specialty;
}

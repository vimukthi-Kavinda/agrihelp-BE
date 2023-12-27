package kln.project.marketservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "farmer")
public class Farmer {
    @Id
    @Column(name = "farmer_id", length = 10)
    private String farmerId;

    @Column(name = "username",length = 20)
    private String username;

    @Column(name = "password",length = 25)
    private String password;

    @Column(name="nic",length = 10)
    private String nic;

    @Column(name="name" ,length = 32)
    private String name;

    @ManyToOne
    @JoinColumn(name = "area_code", referencedColumnName = "areacode")
    private Area area;

    @Column(name="telno",length = 12)
    private String telno;

    @Column(name="email",length = 35)
    private String email;

    @Column(name="address",length = 35)
    private String address;

    @ManyToOne
    @JoinColumn(name = "authorized_officer", referencedColumnName = "officer_id")
    private Officer authorizedOfficer;

    // Constructors, getters, and setters
}

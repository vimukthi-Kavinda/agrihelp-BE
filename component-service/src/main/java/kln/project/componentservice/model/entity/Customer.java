package kln.project.componentservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")

public class Customer {
    @Id
    @Column(name="nic",length = 10)
    private String nic;

    @Column(name="username",length = 20)
    private String username;

    @Column(name="password",length = 25)
    private String password;

    @Column(name = "customer_name", length = 20)
    private String customerName;

    @Column(name = "contact_no", length = 12)
    private String contactNo;

    @Column(name="email",length = 35)
    private String email;

    @Column(name="address",length = 35)
    private String address;

    @ManyToOne
    @JoinColumn(name = "districtid", referencedColumnName = "districtid")
    private District district;

    @ManyToOne
    @JoinColumn(name = "accepted_officer", referencedColumnName = "officer_id")
    private Officer acceptedOfficer;

    // Constructors, getters, and setters
}


package kln.project.customerservice.model.entity;

import jakarta.persistence.*;
import kln.project.customerservice.model.entity.District;
import kln.project.customerservice.model.entity.ShopOwner;
import lombok.Data;

@Entity
@Data
@Table(name = "shop")
public class Shop {
    @Id
    @Column(name = "shop_reg_no", length = 10)
    private String shopRegNo;

    @ManyToOne
    @JoinColumn(name = "districtid", referencedColumnName = "districtid")
    private District district;

    @ManyToOne
    @JoinColumn(name = "owner_nic", referencedColumnName = "owner_nic")
    private ShopOwner ownerNic;

    @Column(name = "location_coord", length = 20)
    private String locationCoord;

    @Column(name = "shop_contact_no", length = 12)
    private String shopContactNo;

    @Column(name = "shopname", length = 20)
    private String shopname;

    @Column(name = "address",length = 35)
    private String address;

    @Column(name = "password",length = 20)
    private String password;

    // Constructors, getters, and setters
}


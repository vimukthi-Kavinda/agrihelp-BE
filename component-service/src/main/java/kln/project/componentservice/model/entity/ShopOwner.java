package kln.project.componentservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "shop_owner")

public class ShopOwner {
    @Id
    @Column(name = "owner_nic", length = 15)
    private String ownerNic;

    @Column(name = "owner_name", length = 20)
    private String ownerName;

    @Column(name = "owner_contact_no", length = 12)
    private String ownerContactNo;

    @Column(length = 35)
    private String email;

    // Constructors, getters, and setters
}


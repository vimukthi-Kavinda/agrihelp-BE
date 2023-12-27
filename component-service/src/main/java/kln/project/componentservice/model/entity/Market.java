package kln.project.componentservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "market")

public class Market {
    @Id
    @Column(name = "market_id", length = 8)
    private String marketId;

    @Column(name = "market_name", length = 20)
    private String marketName;

    @ManyToOne
    @JoinColumn(name = "areacode", referencedColumnName = "areacode")
    private Area area;

    @Column(length = 35)
    private String address;

    @Column(name = "market_location_coord", length = 15)
    private String marketLocationCoord;

    @Column(name = "password",length = 20)
    private String password;

    @Column(name = "mailaddress",length = 25)
    private String mailaddress;

    @Column(name = "contactno",length = 12)
    private String contactno;
    // Constructors, getters, and setters
}


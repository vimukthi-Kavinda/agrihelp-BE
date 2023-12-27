package kln.project.customerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "market_crop_price")
public class MarketCropPrice {

    @EmbeddedId
    MarketCropPriceId marketcroppriceid;

    @Column(name = "unit_price", precision = 8, scale = 2)
    private Double unitPrice;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "last_updated_time")
    private Date lastUpdatedTime;

    @Column(name = "verified_usr_count")
    private Integer verifiedUserCount;

    // Constructors, getters, and setters
}

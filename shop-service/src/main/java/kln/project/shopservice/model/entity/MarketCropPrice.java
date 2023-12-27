package kln.project.shopservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "market_crop_price")
public class MarketCropPrice {

    @EmbeddedId
    MarketCropPriceId marketCropPriceId;

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

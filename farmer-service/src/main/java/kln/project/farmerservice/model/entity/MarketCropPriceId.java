package kln.project.farmerservice.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketCropPriceId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "market_id", referencedColumnName = "market_id")
    private Market market;


    @ManyToOne
    @JoinColumn(name = "cropcode", referencedColumnName = "cropcode")
    private Crop crop;
}

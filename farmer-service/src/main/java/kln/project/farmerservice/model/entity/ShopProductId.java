package kln.project.farmerservice.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopProductId implements Serializable {


    @ManyToOne
    @JoinColumn(name = "shop_reg_no", referencedColumnName = "shop_reg_no")
    private Shop shop;


    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}

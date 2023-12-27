package kln.project.farmerservice.model.bean;


import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class ShopProductDataBean {
    private Integer productId;

    //fertilizer pesticide machine

    private String category;


    private String productName;


    private Double price;
    private Double amount;

    private String manufacturedBy;

    @Column(name = "imported_by", length = 20)
    private String importedBy;


    private Date expDate;
}

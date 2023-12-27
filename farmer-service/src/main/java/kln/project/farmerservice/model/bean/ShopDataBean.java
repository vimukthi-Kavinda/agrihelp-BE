package kln.project.farmerservice.model.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kln.project.farmerservice.model.entity.District;
import kln.project.farmerservice.model.entity.ShopOwner;
import lombok.Data;


@Data
public class ShopDataBean {

    private String shopRegNo;

    private String shopName;

    private String district;


    private String ownerNic;


    private String locationCoord;

    private String shopContactNo;


    private String address;


    //shop owner

    private String ownerName;


    private String ownerContactNo;

    //search parameters
    private String product;

    private String productName;


    // Constructors, getters, and setters
}

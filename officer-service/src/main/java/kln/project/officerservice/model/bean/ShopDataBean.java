package kln.project.officerservice.model.bean;

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

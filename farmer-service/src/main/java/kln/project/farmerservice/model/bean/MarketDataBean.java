package kln.project.farmerservice.model.bean;

import lombok.Data;

@Data
public class MarketDataBean {

    private String marketId;

    private String marketName;

    private String area;
    private String areaName;

    private String address;

    private String marketLocationCoord;

    private String productId;
    private String productName;
    private String productPrice;


    //search parameter
    private String district;
    private String password;
    private String mailaddress;
    private String contactno;

    private String amount;

}

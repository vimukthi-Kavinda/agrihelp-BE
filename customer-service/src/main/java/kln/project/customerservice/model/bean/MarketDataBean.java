package kln.project.customerservice.model.bean;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kln.project.customerservice.model.entity.Area;
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

private String contactno;
private String mailaddress;

    //search parameter
    private String district;

    private String amount;

}

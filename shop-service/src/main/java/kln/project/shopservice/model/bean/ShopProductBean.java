package kln.project.shopservice.model.bean;


import lombok.Data;

@Data
public class ShopProductBean {

    private String productid;
    private String productname;

    private String shopname;
    private String shopid;

    private String amount;
    private String price;


}

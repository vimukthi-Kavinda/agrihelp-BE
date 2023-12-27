package kln.project.marketservice.model.bean;


import lombok.Data;

@Data
public class MarketCropDataBean {

    private String cropcode;
    private String cropname;


    private String marketid;
    private String marketname;

    private String amount;
    private String unitprice;


}

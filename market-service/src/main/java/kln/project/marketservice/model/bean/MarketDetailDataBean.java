package kln.project.marketservice.model.bean;


import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kln.project.marketservice.model.entity.Area;
import lombok.Data;

@Data
public class MarketDetailDataBean {

    private String marketId;


    private String marketName;


    private String area;
    private String areaDesc;
    private String address;

    private String contactno;
    private String mailaddress;

    private boolean verified;
    private String coord;

    private String password;
    private String oldPassword;
    private String userrole;



}

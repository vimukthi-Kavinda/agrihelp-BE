package kln.project.farmerservice.model.bean;


import lombok.Data;

@Data
public class FarmerDetailDataBean {

    private String farmerId;


    private String username;


    private String password;
    private String oldPassword;

    private String nic;


    private String name;


    private String area;
    private String areaDesc;


    private String telno;


    private String email;


    private String address;

    private String userrole;
    private boolean verified;
}

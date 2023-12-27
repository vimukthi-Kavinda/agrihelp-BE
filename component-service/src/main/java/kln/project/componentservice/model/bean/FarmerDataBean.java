package kln.project.componentservice.model.bean;


import lombok.Data;

@Data
public class FarmerDataBean {


    private String farmerId;

    private String username;

    private String password;

    private String nic;

    private String name;

    private String area;

    private String telno;

    private String email;

    private String address;


    //search para

    private String district;
    private String product;//crop



}

package kln.project.shopservice.model.bean;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDetailDataBean {
    private  String shopregno;
    private  String shopname;
    private  String shopaddress;
    private  String shopcontact;
    private String district;
    private String password;
    private String oldPassword;

    private  String ownernic;
    private  String ownername;
    private  String ownercontact;
    private  String ownermail;

    private String userrole;
    private boolean verified;


}

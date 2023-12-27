package kln.project.componentservice.model.bean;


import lombok.Data;

@Data
public class CustomerDetailDataBean {


    private String nic;
    private String username;
    private String userrole;

    private String password;

    private String customerName;

    private String contactNo;
    private String address;
    private String email;

    private String district;
    private String districtName;

    private boolean verified;
}

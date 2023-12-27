package kln.project.customerservice.model.bean;


import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kln.project.customerservice.model.entity.District;
import lombok.Data;

@Data
public class CustomerDetailDataBean {


    private String nic;
    private String username;
    private String userrole;

    private String password;
    private String oldPassword;

    private String customerName;

    private String contactNo;
    private String address;
    private String email;

    private String district;
    private String districtName;

    private boolean verified;
}

package kln.project.officerservice.model.bean;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kln.project.officerservice.model.entity.District;
import lombok.Data;

@Data
public class CustomerDataBean {

    private String nic;


    private String username;


    private String password;


    private String customerName;


    private String contactNo;


    private String email;


    private String address;


    private String districtId;
}

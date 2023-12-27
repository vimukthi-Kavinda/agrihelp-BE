package kln.project.farmerservice.model.bean;


import lombok.Data;

@Data
public class OfficerDetailDataBean {

    private String officerId;


    private String username;


    private String password;


    private String nic;


    private String officerName;


    private String address;


    private String telno;


    private String email;


    private String assignedArea;
    private String assignedAreaDesc;

    private boolean verified;

    private String userrole;

    private String specialty;

   private String district;

}

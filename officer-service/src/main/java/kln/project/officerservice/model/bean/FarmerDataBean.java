package kln.project.officerservice.model.bean;


import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kln.project.officerservice.model.entity.Area;
import lombok.Data;

@Data
public class FarmerDataBean {


    private String farmerId;


    private String username;


    private String password;


    private String nic;


    private String name;


    private String areaId;


    private String telno;


    private String email;


    private String address;
}

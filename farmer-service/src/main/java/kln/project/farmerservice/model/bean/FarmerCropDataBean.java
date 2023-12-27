package kln.project.farmerservice.model.bean;


import lombok.Data;

@Data
public class FarmerCropDataBean {
    private String farmerid;
    private String cropid;
    private String cropName;
    private String amount;
}

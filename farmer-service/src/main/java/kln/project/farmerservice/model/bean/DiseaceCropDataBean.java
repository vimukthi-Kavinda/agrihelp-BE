package kln.project.farmerservice.model.bean;


import lombok.Data;

@Data
public class DiseaceCropDataBean {
    private String diseaceCode;
    private String diseaceName;
    private String cropId;
    private String cropName;


    //recomanded pesticides
    private String pesticidename;
    private String pesticidecode;



}

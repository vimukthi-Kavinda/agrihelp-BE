package kln.project.farmerservice.model.bean;


import lombok.Data;

@Data
public class PlagueDetailBean {

    private String diseaseCode;


    private String diseaseName;

    private String startDate;

    private String rootCause;


    private String diseaseDescription;

    private String spreadingMethod;


    private String remedies;


    private String province;
    private String provinceName;

    private String crop;
    private String cropName;
}

package kln.project.officerservice.model.bean;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PlagueDetailBean {

    private String diseaseCode;

    private String diseaseName;

    private String rootCause;

    private String diseaseDescription;

    private String spreadingMethod;

    private String remedies;

    private String[] pesticideProducts;

    private String[] effectioncrops;

    private String province;
    private String provinceName;

    private String crop;
    private String cropName;

    private String startDate;
}

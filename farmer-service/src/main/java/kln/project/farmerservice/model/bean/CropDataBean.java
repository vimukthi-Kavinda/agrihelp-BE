package kln.project.farmerservice.model.bean;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CropDataBean {

    private String cropid;
    private String cropname;
    private String scientificname;
    private String watermng;
    private String fertilizermng;
    private String weedmng;


    private String[] fertilizers;

}

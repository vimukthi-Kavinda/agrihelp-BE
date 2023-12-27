package kln.project.officerservice.model.bean;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CropDataBean {

    private String cropid;
    private String cropname;
    private String scientificname;

    private String watermgt;


    private String weedmgt;


    private String fertilizerusg;
    private String[] fertilizers;

}

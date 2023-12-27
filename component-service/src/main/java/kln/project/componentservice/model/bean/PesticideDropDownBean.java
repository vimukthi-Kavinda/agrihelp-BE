package kln.project.componentservice.model.bean;


import lombok.Data;

import java.util.List;

@Data
public class PesticideDropDownBean {
    List<DropDownBean> peslist;
    List<DropDownBean> croplist;
}

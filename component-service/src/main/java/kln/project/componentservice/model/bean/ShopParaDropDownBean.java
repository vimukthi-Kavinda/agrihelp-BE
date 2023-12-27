package kln.project.componentservice.model.bean;


import lombok.Data;

import java.util.List;

@Data
public class ShopParaDropDownBean {

    List<DropDownBean> districtlist;
    List<DropDownBean> productlist;



}

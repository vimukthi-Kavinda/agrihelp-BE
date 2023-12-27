package kln.project.componentservice.model.bean;


import lombok.Data;

import java.util.List;

@Data
public class DistrictSpecialtyBean {

   private List<DropDownBean> district;
   private List<DropDownBean> specialty;

}

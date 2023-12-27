package kln.project.componentservice.model.bean;

import lombok.Data;

import java.util.List;

@Data
public class ReportDropDownBean {
    List<DropDownBean> diseaselist;
    List<DropDownBean> provincelist;
    List<DropDownBean> croplist;

}

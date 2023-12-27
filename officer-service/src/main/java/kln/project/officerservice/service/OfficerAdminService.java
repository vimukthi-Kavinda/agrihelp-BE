package kln.project.officerservice.service;

import kln.project.officerservice.model.bean.DataTableBean;
import kln.project.officerservice.model.bean.RequestBean;
import kln.project.officerservice.model.bean.ResponseBean;

public interface OfficerAdminService {
    ResponseBean confirmFarmerReg(String farmerid, RequestBean requestBean, ResponseBean responseBean);

    ResponseBean rejectFarmerReg(String farmerid, RequestBean requestBean, ResponseBean responseBean);

    ResponseBean confirmCustomerReg(String nic, RequestBean requestBean, ResponseBean responseBean);

    ResponseBean rejectCustomerReg(String nic, RequestBean requestBean, ResponseBean responseBean);

    ResponseBean confirmPostReg(String postid, RequestBean requestBean, ResponseBean responseBean);

    ResponseBean rejectPostReg(String postid, RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getFarmerReqList(RequestBean requestBean);

    DataTableBean getCustomerReqList(RequestBean requestBean);

    DataTableBean getPostReqList(RequestBean requestBean);

    ResponseBean rejectOfficerReg(String id, RequestBean requestBean, ResponseBean responseBean);

    ResponseBean confirmOfficerReg(String id, RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getOfficerReqList(RequestBean requestBean);
}

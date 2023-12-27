package kln.project.customerservice.service;

import kln.project.customerservice.model.bean.DataTableBean;
import kln.project.customerservice.model.bean.RequestBean;
import kln.project.customerservice.model.bean.ResponseBean;

public interface CustomerService {
    ResponseBean updateprice(RequestBean requestBean, ResponseBean responseBean) throws Exception;

    DataTableBean getFarmerList(RequestBean requestBean);

    ResponseBean viewFamerCrop(String marcketid, ResponseBean responseBean);

    DataTableBean viewnearmarkets(RequestBean requestBean);

    ResponseBean viewMarketPrice(String marcketid, ResponseBean responseBean);

    DataTableBean officerList(RequestBean requestBean);
}

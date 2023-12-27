package kln.project.farmerservice.service;

import kln.project.farmerservice.model.bean.DataTableBean;
import kln.project.farmerservice.model.bean.RequestBean;
import kln.project.farmerservice.model.bean.ResponseBean;

public interface FarmerService {
    DataTableBean getPlagueList(RequestBean requestBean);

    DataTableBean getShopList(RequestBean requestBean);

    DataTableBean viewnearmarkets(RequestBean requestBean);

    ResponseBean viewMarketPrice(String marcketid, ResponseBean responseBean);

    ResponseBean viewshopitems(String shopid, ResponseBean responseBean);


    //effecting crops
    ResponseBean viewPlageDetails(String plagueid, ResponseBean responseBean);


    DataTableBean getMyCropList(RequestBean requestBean);

    ResponseBean deleteMyCrop(String bankcode, String branchcode, ResponseBean responseBean);

    ResponseBean addMyCrop(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean editMyCrop(RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getAllCropList(RequestBean requestBean);

    ResponseBean viewCropFertilizer(String cropcode, ResponseBean responseBean);

    DataTableBean officerList(RequestBean requestBean);

    ResponseBean viewProductDetails(String productid, ResponseBean responseBean);

    ResponseBean getRecomandedpestlist(String diseaseid, ResponseBean responseBean);
}

package kln.project.shopservice.service;

import kln.project.shopservice.model.bean.DataTableBean;
import kln.project.shopservice.model.bean.RequestBean;
import kln.project.shopservice.model.bean.ResponseBean;

public interface ShopHelpService {
    ResponseBean addnewproduct(RequestBean requestBean, ResponseBean responseBean) throws Exception;

    ResponseBean addproductstore(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean editproduct(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean delete(String id,RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getList(RequestBean requestBean);

    ResponseBean viewProductDetails(String productid, ResponseBean responseBean);
}

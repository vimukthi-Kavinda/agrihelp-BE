package kln.project.shopservice.service;

import kln.project.shopservice.model.bean.DataTableBean;
import kln.project.shopservice.model.bean.RequestBean;
import kln.project.shopservice.model.bean.ResponseBean;

public interface ShopMainService {
    ResponseBean login(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean signin(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean signinowner(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean deleteowneraccount(String myid, RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getownerinfo(RequestBean requestBean);

    ResponseBean updateownerinfo(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean deletestore(String myid, RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getstoreinfo(RequestBean requestBean);

    ResponseBean updatestoreinfo(RequestBean requestBean, ResponseBean responseBean);
}

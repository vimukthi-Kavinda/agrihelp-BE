package kln.project.marketservice.service;


import kln.project.marketservice.model.bean.DataTableBean;
import kln.project.marketservice.model.bean.RequestBean;
import kln.project.marketservice.model.bean.ResponseBean;

public interface MarketMainService {
    ResponseBean login(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean signin(RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getinfo(RequestBean requestBean);

    ResponseBean updateInfo(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean deleteAccount(String myid, RequestBean requestBean, ResponseBean responseBean);
}

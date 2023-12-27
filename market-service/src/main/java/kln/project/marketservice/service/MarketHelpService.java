package kln.project.marketservice.service;


import kln.project.marketservice.model.bean.DataTableBean;
import kln.project.marketservice.model.bean.RequestBean;
import kln.project.marketservice.model.bean.ResponseBean;

public interface MarketHelpService {
    ResponseBean add(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean edit(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean delete(String cropcode,RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getList(RequestBean requestBean);
}

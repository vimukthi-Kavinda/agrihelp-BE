package kln.project.customerservice.service;

import kln.project.customerservice.model.bean.DataTableBean;
import kln.project.customerservice.model.bean.RequestBean;
import kln.project.customerservice.model.bean.ResponseBean;

public interface CustomerMainService {
    ResponseBean login(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean signin(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean updateInfo(RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getinfo(RequestBean requestBean);

    ResponseBean deleteAccount(String myid, RequestBean requestBean, ResponseBean responseBean);
}

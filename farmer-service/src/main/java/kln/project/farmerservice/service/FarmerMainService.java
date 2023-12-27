package kln.project.farmerservice.service;

import kln.project.farmerservice.model.bean.DataTableBean;
import kln.project.farmerservice.model.bean.RequestBean;
import kln.project.farmerservice.model.bean.ResponseBean;

public interface FarmerMainService {
    ResponseBean login(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean signin(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean deleteAccount(String myid, RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getinfo(RequestBean requestBean);

    ResponseBean updateInfo(RequestBean requestBean, ResponseBean responseBean);
}

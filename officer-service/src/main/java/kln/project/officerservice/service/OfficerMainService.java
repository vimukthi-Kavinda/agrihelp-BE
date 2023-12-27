package kln.project.officerservice.service;

import kln.project.officerservice.model.bean.DataTableBean;
import kln.project.officerservice.model.bean.RequestBean;
import kln.project.officerservice.model.bean.ResponseBean;

public interface OfficerMainService {

    ResponseBean login(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean signin(RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getinfo(RequestBean requestBean);

    ResponseBean updateInfo(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean deleteAccount(String myid, RequestBean requestBean, ResponseBean responseBean);
}

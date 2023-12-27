package kln.project.customerservice.service;

import kln.project.customerservice.model.bean.DataTableBean;
import kln.project.customerservice.model.bean.RequestBean;
import kln.project.customerservice.model.bean.ResponseBean;

public interface CustomerCommunityService {

    DataTableBean gettipPosts(RequestBean requestBean);

    DataTableBean getNewsPosts(RequestBean requestBean);


    DataTableBean getCustomerNotification(RequestBean requestBean);

    DataTableBean getCustomerProductNotification(RequestBean requestBean);
}

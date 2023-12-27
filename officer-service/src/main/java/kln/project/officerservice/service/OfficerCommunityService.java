package kln.project.officerservice.service;

import kln.project.officerservice.model.bean.DataTableBean;
import kln.project.officerservice.model.bean.RequestBean;
import kln.project.officerservice.model.bean.ResponseBean;

public interface OfficerCommunityService {
    ResponseBean deletePostComment(RequestBean requestBean,String username, ResponseBean responseBean);


    ResponseBean addPostComment(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean reactpost(RequestBean requestBean, ResponseBean responseBean);

    DataTableBean getMyPosts(RequestBean requestBean);

    DataTableBean getNewsPosts(RequestBean requestBean);

    DataTableBean gettipPosts(RequestBean requestBean);

    DataTableBean getnewPosts(RequestBean requestBean);

    ResponseBean addDisease(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean deleteDisease(String id, ResponseBean responseBean);

    DataTableBean getPostReactsComments(RequestBean requestBean);

    ResponseBean viewPlageDetails(String plagueid, ResponseBean responseBean);

    DataTableBean getPlagueList(RequestBean requestBean);

    ResponseBean reportDisease(RequestBean requestBean, ResponseBean responseBean) throws Exception;

    ResponseBean addCrop(RequestBean requestBean, ResponseBean responseBean);

    DataTableBean officerList(RequestBean requestBean);

    DataTableBean getOfficerNotification(RequestBean requestBean);

    DataTableBean getOfficerPlagueNotification(RequestBean requestBean);

    DataTableBean getOfficerProductNotification(RequestBean requestBean);

    ResponseBean viewProductDetails(String productid, ResponseBean responseBean);

    ResponseBean getRecomandedpestlist(String diseaseid, ResponseBean responseBean);
}

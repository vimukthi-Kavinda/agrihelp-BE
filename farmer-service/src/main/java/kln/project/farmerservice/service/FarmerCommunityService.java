package kln.project.farmerservice.service;

import kln.project.farmerservice.model.bean.DataTableBean;
import kln.project.farmerservice.model.bean.RequestBean;
import kln.project.farmerservice.model.bean.ResponseBean;

public interface FarmerCommunityService {
    DataTableBean getnewPosts(RequestBean requestBean);

    DataTableBean gettipPosts(RequestBean requestBean);

    DataTableBean getNewsPosts(RequestBean requestBean);

    DataTableBean getMyPosts(RequestBean requestBean);

    ResponseBean reactpost(RequestBean requestBean, ResponseBean responseBean);

    ResponseBean addPostComment(RequestBean requestBean, ResponseBean responseBean);

   // ResponseBean deletePostComment(String postid, String username, String commentid, ResponseBean responseBean);
   ResponseBean deletePostComment( RequestBean requestBean,String username,  ResponseBean responseBean);
    DataTableBean getPostReactsComments(RequestBean requestBean);

    DataTableBean getFarmerProductNotification(RequestBean requestBean);

    DataTableBean getFarmerNotification(RequestBean requestBean);

    DataTableBean getFarmerPlagueNotification(RequestBean requestBean);
}

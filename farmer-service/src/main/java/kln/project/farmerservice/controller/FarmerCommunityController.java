package kln.project.farmerservice.controller;


import kln.project.farmerservice.model.bean.DataTableBean;
import kln.project.farmerservice.model.bean.RequestBean;
import kln.project.farmerservice.model.bean.ResponseBean;
import kln.project.farmerservice.service.FarmerCommunityService;
import kln.project.farmerservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farmercommunity")
public class FarmerCommunityController {

    Logger logger = LoggerFactory.getLogger(FarmerCommunityController.class);
    @Autowired
    private ResponseBean responseBean;



    @Autowired
    FarmerCommunityService farmerCommunityService;



    @PostMapping(value = "/getnewposts")
    public ResponseBean getnewPosts(@RequestBody RequestBean requestBean,
                                    @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerCommunityController.getnewPosts");
        logger.debug(requestID + " - " + "FarmerCommunityController.getnewPosts - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerCommunityController.getnewPosts - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerCommunityService.getnewPosts(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerCommunityController.getnewPosts - " + "Posts retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/gettipposts")
    public ResponseBean gettipPosts(@RequestBody RequestBean requestBean,
                                    @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerCommunityController.gettipPosts");
        logger.debug(requestID + " - " + "FarmerCommunityController.gettipPosts - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerCommunityController.gettipPosts - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerCommunityService.gettipPosts(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerCommunityController.gettipPosts - " + "Posts retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/getnewsposts")
    public ResponseBean getNewsPosts(@RequestBody RequestBean requestBean,
                                     @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerCommunityController.getNewsPosts");
        logger.debug(requestID + " - " + "FarmerCommunityController.getNewsPosts - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerCommunityController.getNewsPosts - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerCommunityService.getNewsPosts(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerCommunityController.getNewsPosts - " + "Posts retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/getmyposts")
    public ResponseBean getMyPosts(@RequestBody RequestBean requestBean,
                                   @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerCommunityController.getMyPosts");
        logger.debug(requestID + " - " + "FarmerCommunityController.getMyPosts - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerCommunityController.getMyPosts - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerCommunityService.getMyPosts(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerCommunityController.getMyPosts - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/reactpost")
    public ResponseBean reactpost(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerCommunityController.reactpost");
        logger.debug(requestID + " - " + "FarmerCommunityController.reactpost - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerCommunityController.reactpost - " + "initializing add process");
        responseBean = farmerCommunityService.reactpost(requestBean, responseBean);
        logger.info(requestID + " - " + "FarmerCommunityController.reactpost - " + "Add  completed");

        return responseBean;
    }


    @PostMapping(value = "/addpostcomment")
    public ResponseBean addPostComment(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerCommunityController.addPostComment");
        logger.debug(requestID + " - " + "FarmerCommunityController.addPostComment - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerCommunityController.addPostComment - " + "initializing add process");
        responseBean = farmerCommunityService.addPostComment(requestBean, responseBean);
        logger.info(requestID + " - " + "FarmerCommunityController.addPostComment - " + "Add  completed");

        return responseBean;
    }



    //@PostMapping(value = "/deletepostcomment/{postid}/{commentid}")
    //public ResponseBean deleteArea(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String postid, @PathVariable String commentid) throws Exception {

    @PostMapping(value = "/deletepostcomment")
    public ResponseBean deleteArea(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {

            logger.info(requestID + " - " + "FarmerCommunityController.deletePostComment");
        logger.debug(requestID + " - " + "FarmerCommunityController.deletePostComment - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerCommunityController.deletePostComment - " + "initializing delete process");
        responseBean = farmerCommunityService.deletePostComment(requestBean,requestBean.getUsername(), responseBean);
        logger.info(requestID + " - " + "FarmerCommunityController.deletePostComment - " + "Delete completed");

        return responseBean;
    }




    @PostMapping(value = "/getpostreactscomments")
    public ResponseBean getPostReactsComments(@RequestBody RequestBean requestBean,
                                              @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.getPostReactsComments");
        logger.debug(requestID + " - " + "FarmerServiceController.getPostReactsComments - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.getPostReactsComments - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerCommunityService.getPostReactsComments(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerServiceController.getPostReactsComments - " + "Posts retrieval completed");

        return responseBean;
    }



    @PostMapping(value = "/farmernotification")
    public ResponseBean getFarmerNotification(@RequestBody RequestBean requestBean,
                                                     @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.farmernotification");
        logger.debug(requestID + " - " + "FarmerServiceController.farmernotification - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.farmernotification - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerCommunityService.getFarmerNotification(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerServiceController.farmernotification - " + "Posts retrieval completed");

        return responseBean;
    }



    @PostMapping(value = "/farmerproductnotification")
    public ResponseBean getFarmerProductNotification(@RequestBody RequestBean requestBean,
                                              @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.farmerproductnotification");
        logger.debug(requestID + " - " + "FarmerServiceController.farmerproductnotification - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.farmerproductnotification - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerCommunityService.getFarmerProductNotification(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerServiceController.farmerproductnotification - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/farmerplaguenotification")
    public ResponseBean getFarmerPlagueNotification(@RequestBody RequestBean requestBean,
                                                     @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.farmerplaguenotification");
        logger.debug(requestID + " - " + "FarmerServiceController.farmerplaguenotification - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.farmerplaguenotification - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerCommunityService.getFarmerPlagueNotification(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerServiceController.farmerplaguenotification - " + "Posts retrieval completed");

        return responseBean;
    }
}

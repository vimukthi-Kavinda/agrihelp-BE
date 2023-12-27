package kln.project.officerservice.controller;


import kln.project.officerservice.model.bean.DataTableBean;
import kln.project.officerservice.model.bean.RequestBean;
import kln.project.officerservice.model.bean.ResponseBean;
import kln.project.officerservice.model.entity.Officer;
import kln.project.officerservice.service.OfficerCommunityService;
import kln.project.officerservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/officercommunity")
public class OfficerCommunityController {

    Logger logger = LoggerFactory.getLogger(OfficerCommunityController.class);
    @Autowired
    private ResponseBean responseBean;


    
    @Autowired
    OfficerCommunityService officerCommunityService;

    @PostMapping(value = "/getnewposts")
    public ResponseBean getnewPosts(@RequestBody RequestBean requestBean,
                                    @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.getnewPosts");
        logger.debug(requestID + " - " + "OfficerCommunityController.getnewPosts - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.getnewPosts - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = officerCommunityService.getnewPosts(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerCommunityController.getnewPosts - " + "Posts retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/gettipposts")
    public ResponseBean gettipPosts(@RequestBody RequestBean requestBean,
                                    @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.gettipPosts");
        logger.debug(requestID + " - " + "OfficerCommunityController.gettipPosts - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.gettipPosts - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = officerCommunityService.gettipPosts(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerCommunityController.gettipPosts - " + "Posts retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/getnewsposts")
    public ResponseBean getNewsPosts(@RequestBody RequestBean requestBean,
                                     @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.getNewsPosts");
        logger.debug(requestID + " - " + "OfficerCommunityController.getNewsPosts - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.getNewsPosts - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = officerCommunityService.getNewsPosts(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerCommunityController.getNewsPosts - " + "Posts retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/getmyposts")
    public ResponseBean getMyPosts(@RequestBody RequestBean requestBean,
                                   @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.getMyPosts");
        logger.debug(requestID + " - " + "OfficerCommunityController.getMyPosts - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.getMyPosts - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = officerCommunityService.getMyPosts(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerCommunityController.getMyPosts - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/reactpost")
    public ResponseBean reactpost(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.reactpost");
        logger.debug(requestID + " - " + "OfficerCommunityController.reactpost - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.reactpost - " + "initializing add process");
        responseBean = officerCommunityService.reactpost(requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerCommunityController.reactpost - " + "Add  completed");

        return responseBean;
    }


    @PostMapping(value = "/addpostcomment")
    public ResponseBean addPostComment(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.addPostComment");
        logger.debug(requestID + " - " + "OfficerCommunityController.addPostComment - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.addPostComment - " + "initializing add process");
        responseBean = officerCommunityService.addPostComment(requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerCommunityController.addPostComment - " + "Add  completed");

        return responseBean;
    }

    //@PostMapping(value = "/deletepostcomment/{postid}/{commentid}")
    @PostMapping(value = "/deletepostcomment")
    public ResponseBean deletePostComment(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.deletePostComment");
        logger.debug(requestID + " - " + "OfficerCommunityController.deletePostComment - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.deletePostComment - " + "initializing delete process");
        responseBean = officerCommunityService.deletePostComment(requestBean,requestBean.getUsername(), responseBean);
        logger.info(requestID + " - " + "OfficerCommunityController.deletePostComment - " + "Delete completed");

        return responseBean;
    }



    @PostMapping(value = "/adddisease")
    public ResponseBean addDisease(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.adddisease");
        logger.debug(requestID + " - " + "OfficerCommunityController.adddisease - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.adddisease - " + "initializing adddisease process");
        responseBean = officerCommunityService.addDisease(requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerCommunityController.adddisease - " + " completed");
        return responseBean;
    }


    @PostMapping(value = "/deletedisease/{id}")
    public ResponseBean deleteDisease(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String id) throws Exception {
        logger.info(requestID + " - " + "AreaController.deletepost");
        logger.debug(requestID + " - " + "AreaController.deletepost - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "AreaController.deletepost - " + "initializing delete post process");
        responseBean = officerCommunityService.deleteDisease(id, responseBean);
        logger.info(requestID + " - " + "AreaController.deletepost - " + "Delete post completed");

        return responseBean;
    }

    @PostMapping(value = "/getpostreactscomments")
    public ResponseBean getPostReactsComments(@RequestBody RequestBean requestBean,
                                              @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerPostController.getPostReactsComments");
        logger.debug(requestID + " - " + "OfficerPostController.getPostReactsComments - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerPostController.getPostReactsComments - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = officerCommunityService.getPostReactsComments(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerPostController.getPostReactsComments - " + "Posts retrieval completed");

        return responseBean;
    }
    
    
    //plague


    @PostMapping(value = "/plaguedetails/{plagueid}")
    public ResponseBean viewPlageDetails(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String plagueid) throws Exception {
        logger.info(requestID + " - " + "OfficerPostController.view");
        logger.debug(requestID + " - " + "OfficerPostController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerPostController.view - " + "initializing view  process");
        responseBean = officerCommunityService.viewPlageDetails(plagueid, responseBean);
        logger.info(requestID + " - " + "OfficerPostController.view - " + "viewing completed");

        return responseBean;
    }

    @PostMapping(value = "/plaguelist")
    public ResponseBean getPlagueList(@RequestBody RequestBean requestBean,
                                      @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerPostController.getPlagueList");
        logger.debug(requestID + " - " + "OfficerPostController.getPlagueList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerPostController.getPlagueList - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = officerCommunityService.getPlagueList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerPostController.getPlagueList - " + "Posts retrieval completed");

        return responseBean;
    }


    //add to plague province table
    @PostMapping(value = "/reportdisease")
    public ResponseBean reportDisease(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.reportDisease");
        logger.debug(requestID + " - " + "OfficerCommunityController.reportDisease - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.reportDisease - " + "initializing reportDisease process");
        responseBean = officerCommunityService.reportDisease(requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerCommunityController.reportDisease - " + " completed");
        return responseBean;
    }

    @PostMapping(value = "/addcrop")
    public ResponseBean addCrop(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.addCrop");
        logger.debug(requestID + " - " + "OfficerCommunityController.addCrop - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.addCrop - " + "initializing addCrop process");
        responseBean = officerCommunityService.addCrop(requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerCommunityController.addCrop - " + " completed");
        return responseBean;
    }




    @PostMapping(value = "/officerlist")
    public ResponseBean officerList(@RequestBody RequestBean requestBean,
                                    @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.officerlist");
        logger.debug(requestID + " - " + "OfficerCommunityController.officerlist - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.officerlist - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = officerCommunityService.officerList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerCommunityController.officerlist - " + "Posts retrieval completed");

        return responseBean;
    }




    @PostMapping(value = "/officernotification")
    public ResponseBean getOfficerNotification(@RequestBody RequestBean requestBean,
                                    @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.officernotification");
        logger.debug(requestID + " - " + "OfficerCommunityController.officernotification - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.officernotification - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = officerCommunityService.getOfficerNotification(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerCommunityController.officernotification - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/officerplaguenotification")
    public ResponseBean getOfficerPlagueNotification(@RequestBody RequestBean requestBean,
                                               @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.getOfficerPlagueNotification");
        logger.debug(requestID + " - " + "OfficerCommunityController.getOfficerPlagueNotification - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.getOfficerPlagueNotification - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = officerCommunityService.getOfficerPlagueNotification(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerCommunityController.officernotification - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/officerproductnotification")
    public ResponseBean getOfficerProductNotification(@RequestBody RequestBean requestBean,
                                               @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.getOfficerProductNotification");
        logger.debug(requestID + " - " + "OfficerCommunityController.getOfficerProductNotification - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.getOfficerProductNotification - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = officerCommunityService.getOfficerProductNotification(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerCommunityController.getOfficerProductNotification - " + "Posts retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/productdetails/{productid}")
    public ResponseBean viewProductDetails(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String productid) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.view");
        logger.debug(requestID + " - " + "OfficerCommunityController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.view - " + "initializing view  process");
        responseBean = officerCommunityService.viewProductDetails(productid, responseBean);
        logger.info(requestID + " - " + "OfficerCommunityController.view - " + "viewing completed");

        return responseBean;
    }


    @PostMapping(value = "/getrecomandedpestlist/{diseaseid}")
    public ResponseBean getRecomandedpestlist(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String diseaseid) throws Exception {
        logger.info(requestID + " - " + "OfficerCommunityController.view");
        logger.debug(requestID + " - " + "OfficerCommunityController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerCommunityController.view - " + "initializing view  process");
        responseBean = officerCommunityService.getRecomandedpestlist(diseaseid, responseBean);
        logger.info(requestID + " - " + "OfficerCommunityController.view - " + "viewing completed");

        return responseBean;
    }
    
}

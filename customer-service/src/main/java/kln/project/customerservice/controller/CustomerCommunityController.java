package kln.project.customerservice.controller;


import kln.project.customerservice.model.bean.DataTableBean;
import kln.project.customerservice.model.bean.RequestBean;
import kln.project.customerservice.model.bean.ResponseBean;
import kln.project.customerservice.service.CustomerCommunityService;
import kln.project.customerservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customercommunity")
public class CustomerCommunityController {


    Logger logger = LoggerFactory.getLogger(CustomerCommunityController.class);
    @Autowired
    private ResponseBean responseBean;



    @Autowired
    CustomerCommunityService customerCommunityService;




    @PostMapping(value = "/gettipposts")
    public ResponseBean gettipPosts(@RequestBody RequestBean requestBean,
                                    @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerCommunityController.gettipPosts");
        logger.debug(requestID + " - " + "CustomerCommunityController.gettipPosts - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerCommunityController.gettipPosts - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = customerCommunityService.gettipPosts(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "CustomerCommunityController.gettipPosts - " + "Posts retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/getnewsposts")
    public ResponseBean getNewsPosts(@RequestBody RequestBean requestBean,
                                     @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerCommunityController.getNewsPosts");
        logger.debug(requestID + " - " + "CustomerCommunityController.getNewsPosts - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerCommunityController.getNewsPosts - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = customerCommunityService.getNewsPosts(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "CustomerCommunityController.getNewsPosts - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/customernotification")
    public ResponseBean getCustomerNotification(@RequestBody RequestBean requestBean,
                                     @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerCommunityController.customernotification");
        logger.debug(requestID + " - " + "CustomerCommunityController.customernotification - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerCommunityController.customernotification - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = customerCommunityService.getCustomerNotification(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "CustomerCommunityController.customernotification - " + "Posts retrieval completed");

        return responseBean;
    }



    @PostMapping(value = "/customerproductnotification")
    public ResponseBean getCustomerProductNotification(@RequestBody RequestBean requestBean,
                                                @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerCommunityController.getCustomerProductNotification");
        logger.debug(requestID + " - " + "CustomerCommunityController.getCustomerProductNotification - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerCommunityController.getCustomerProductNotification - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = customerCommunityService.getCustomerProductNotification(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "CustomerCommunityController.getCustomerProductNotification - " + "Posts retrieval completed");

        return responseBean;
    }


}

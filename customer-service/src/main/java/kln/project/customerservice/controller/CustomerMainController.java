package kln.project.customerservice.controller;


import kln.project.customerservice.model.bean.DataTableBean;
import kln.project.customerservice.model.bean.RequestBean;
import kln.project.customerservice.model.bean.ResponseBean;
import kln.project.customerservice.service.CustomerMainService;
import kln.project.customerservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customermain")
public class CustomerMainController {


    Logger logger = LoggerFactory.getLogger(CustomerMainController.class);
    @Autowired
    private ResponseBean responseBean;


    @Autowired
    CustomerMainService customerMainService;

    @PostMapping(value = "/login")
    public ResponseBean login(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerMainController.login");
        logger.debug(requestID + " - " + "CustomerMainController.login - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerMainController.login - " + "initializing login process");
        responseBean = customerMainService.login(requestBean, responseBean);
        logger.info(requestID + " - " + "CustomerMainController.login - " + "Login completed");
        return responseBean;
    }

    @PostMapping(value = "/signin")
    public ResponseBean signin(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerMainController.signin");
        logger.debug(requestID + " - " + "CustomerMainController.signin - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerMainController.signin - " + "initializing signin process");
        responseBean = customerMainService.signin(requestBean, responseBean);
        logger.info(requestID + " - " + "CustomerMainController.signin - " + "Signin completed");
        return responseBean;
    }



    @PostMapping(value = "/updateinfo")
    public ResponseBean updateInfo(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerMainController.updateInfo");
        logger.debug(requestID + " - " + "CustomerMainController.updateInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerMainController.updateInfo - " + "initializing signin process");
        responseBean = customerMainService.updateInfo(requestBean, responseBean);
        logger.info(requestID + " - " + "CustomerMainController.updateInfo - " + "Signin completed");
        return responseBean;
    }

    @PostMapping(value = "/getinfo")
    public ResponseBean getInfo(@RequestBody RequestBean requestBean,
                                     @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerMainController.getInfo");
        logger.debug(requestID + " - " + "CustomerMainController.getInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerMainController.getInfo - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = customerMainService.getinfo(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "CustomerMainController.getInfo - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/deleteaccount/{myid}")
    public ResponseBean deleteAccount(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String myid) throws Exception {
        logger.info(requestID + " - " + "CustomerMainController.delete");
        logger.debug(requestID + " - " + "CustomerMainController.delete - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerMainController.delete - " + "initializing delete  process");
        responseBean = customerMainService.deleteAccount(myid, requestBean,responseBean);
        logger.info(requestID + " - " + "CustomerMainController.delete - " + "Delete  completed");

        return responseBean;
    }

}

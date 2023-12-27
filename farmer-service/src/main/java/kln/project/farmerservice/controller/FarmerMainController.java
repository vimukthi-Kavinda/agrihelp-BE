package kln.project.farmerservice.controller;

import kln.project.farmerservice.model.bean.DataTableBean;
import kln.project.farmerservice.model.bean.RequestBean;
import kln.project.farmerservice.model.bean.ResponseBean;
import kln.project.farmerservice.service.FarmerMainService;
import kln.project.farmerservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/farmermain")
public class FarmerMainController {
    Logger logger = LoggerFactory.getLogger(FarmerMainController.class);
    @Autowired
    private ResponseBean responseBean;


    @Autowired
    FarmerMainService farmerMainService;

    @PostMapping(value = "/login")
    public ResponseBean login(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerMainController.login");
        logger.debug(requestID + " - " + "FarmerMainController.login - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerMainController.login - " + "initializing login process");
        responseBean = farmerMainService.login(requestBean, responseBean);
        logger.info(requestID + " - " + "FarmerMainController.login - " + "Login completed");
        return responseBean;
    }

    @PostMapping(value = "/signin")
    public ResponseBean signin(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerMainController.signin");
        logger.debug(requestID + " - " + "FarmerMainController.signin - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerMainController.signin - " + "initializing signin process");
        responseBean = farmerMainService.signin(requestBean, responseBean);
        logger.info(requestID + " - " + "FarmerMainController.signin - " + "Signin completed");
        return responseBean;
    }





    @PostMapping(value = "/updateinfo")
    public ResponseBean updateInfo(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerMainController.updateInfo");
        logger.debug(requestID + " - " + "FarmerMainController.updateInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerMainController.updateInfo - " + "initializing signin process");
        responseBean = farmerMainService.updateInfo(requestBean, responseBean);
        logger.info(requestID + " - " + "FarmerMainController.updateInfo - " + "Signin completed");
        return responseBean;
    }

    @PostMapping(value = "/getinfo")
    public ResponseBean getInfo(@RequestBody RequestBean requestBean,
                                @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerMainController.getInfo");
        logger.debug(requestID + " - " + "FarmerMainController.getInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerMainController.getInfo - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerMainService.getinfo(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerMainController.getInfo - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/deleteaccount/{myid}")
    public ResponseBean deleteAccount(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String myid) throws Exception {
        logger.info(requestID + " - " + "FarmerMainController.delete");
        logger.debug(requestID + " - " + "FarmerMainController.delete - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerMainController.delete - " + "initializing delete  process");
        responseBean = farmerMainService.deleteAccount(myid, requestBean,responseBean);
        logger.info(requestID + " - " + "FarmerMainController.delete - " + "Delete  completed");

        return responseBean;
    }


}

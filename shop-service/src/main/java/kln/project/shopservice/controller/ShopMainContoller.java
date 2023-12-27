package kln.project.shopservice.controller;


import kln.project.shopservice.model.bean.DataTableBean;
import kln.project.shopservice.model.bean.RequestBean;
import kln.project.shopservice.model.bean.ResponseBean;
import kln.project.shopservice.service.ShopMainService;
import kln.project.shopservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopmain")
public class ShopMainContoller {


    Logger logger = LoggerFactory.getLogger(ShopMainContoller.class);
    @Autowired
    private ResponseBean responseBean;

    @Autowired
    ShopMainService shopMainService;


    @PostMapping(value = "/login")
    public ResponseBean login(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ShopMainContoller.login");
        logger.debug(requestID + " - " + "ShopMainContoller.login - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ShopMainContoller.login - " + "initializing login process");
        responseBean = shopMainService.login(requestBean, responseBean);
        logger.info(requestID + " - " + "ShopMainContoller.login - " + "Login completed");
        return responseBean;
    }

    @PostMapping(value = "/signin")
    public ResponseBean signin(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ShopMainContoller.signin");
        logger.debug(requestID + " - " + "ShopMainContoller.signin - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ShopMainContoller.signin - " + "initializing signin process");
        responseBean = shopMainService.signin(requestBean, responseBean);
        logger.info(requestID + " - " + "ShopMainContoller.signin - " + "Signin completed");
        return responseBean;
    }

    @PostMapping(value = "/signinowner")
    public ResponseBean signinowner(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ShopMainContoller.signinowner");
        logger.debug(requestID + " - " + "ShopMainContoller.signinowner - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ShopMainContoller.signinowner - " + "initializing signin process");
        responseBean = shopMainService.signinowner(requestBean, responseBean);
        logger.info(requestID + " - " + "ShopMainContoller.signinowner - " + "Signin completed");
        return responseBean;
    }






    @PostMapping(value = "/updatestoreinfo")
    public ResponseBean updatestoreinfo(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerMainController.updateInfo");
        logger.debug(requestID + " - " + "CustomerMainController.updateInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerMainController.updateInfo - " + "initializing signin process");
        responseBean = shopMainService.updatestoreinfo(requestBean, responseBean);
        logger.info(requestID + " - " + "CustomerMainController.updateInfo - " + "Signin completed");
        return responseBean;
    }

    @PostMapping(value = "/getstoreinfo")
    public ResponseBean getstoreinfo(@RequestBody RequestBean requestBean,
                                @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerMainController.getInfo");
        logger.debug(requestID + " - " + "CustomerMainController.getInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerMainController.getInfo - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = shopMainService.getstoreinfo(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "CustomerMainController.getInfo - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/deletestore/{myid}")
    public ResponseBean deletestore(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String myid) throws Exception {
        logger.info(requestID + " - " + "CustomerMainController.delete");
        logger.debug(requestID + " - " + "CustomerMainController.delete - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerMainController.delete - " + "initializing delete  process");
        responseBean = shopMainService.deletestore(myid, requestBean,responseBean);
        logger.info(requestID + " - " + "CustomerMainController.delete - " + "Delete  completed");

        return responseBean;
    }


    @PostMapping(value = "/updateownerinfo")
    public ResponseBean updateownerinfo(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerMainController.updateInfo");
        logger.debug(requestID + " - " + "CustomerMainController.updateInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerMainController.updateInfo - " + "initializing signin process");
        responseBean = shopMainService.updateownerinfo(requestBean, responseBean);
        logger.info(requestID + " - " + "CustomerMainController.updateInfo - " + "Signin completed");
        return responseBean;
    }

    @PostMapping(value = "/getownerinfo")
    public ResponseBean getownerinfo(@RequestBody RequestBean requestBean,
                                @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerMainController.getInfo");
        logger.debug(requestID + " - " + "CustomerMainController.getInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerMainController.getInfo - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = shopMainService.getownerinfo(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "CustomerMainController.getInfo - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/deleteowneraccount/{myid}")
    public ResponseBean deleteowneraccount(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String myid) throws Exception {
        logger.info(requestID + " - " + "CustomerMainController.delete");
        logger.debug(requestID + " - " + "CustomerMainController.delete - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerMainController.delete - " + "initializing delete  process");
        responseBean = shopMainService.deleteowneraccount(myid, requestBean,responseBean);
        logger.info(requestID + " - " + "CustomerMainController.delete - " + "Delete  completed");

        return responseBean;
    }

}

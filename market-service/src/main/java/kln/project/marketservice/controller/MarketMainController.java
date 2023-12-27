package kln.project.marketservice.controller;



import kln.project.marketservice.model.bean.DataTableBean;
import kln.project.marketservice.model.bean.RequestBean;
import kln.project.marketservice.model.bean.ResponseBean;
import kln.project.marketservice.service.MarketMainService;
import kln.project.marketservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marketmain")
public class MarketMainController {

    Logger logger = LoggerFactory.getLogger(MarketMainController.class);

    @Autowired
    private ResponseBean responseBean;

    @Autowired
    MarketMainService marketMainService;


    @PostMapping(value = "/login")
    public ResponseBean login(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "MarketMainController.login");
        logger.debug(requestID + " - " + "MarketMainController.login - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "MarketMainController.login - " + "initializing login process");
        responseBean = marketMainService.login(requestBean, responseBean);
        logger.info(requestID + " - " + "MarketMainController.login - " + "Login completed");
        return responseBean;
    }

    @PostMapping(value = "/signin")
    public ResponseBean signin(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "MarketMainController.signin");
        logger.debug(requestID + " - " + "MarketMainController.signin - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "MarketMainController.signin - " + "initializing signin process");
        responseBean = marketMainService.signin(requestBean, responseBean);
        logger.info(requestID + " - " + "MarketMainController.signin - " + "Signin completed");
        return responseBean;
    }



    @PostMapping(value = "/updateinfo")
    public ResponseBean updateInfo(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "MarketMainController.updateInfo");
        logger.debug(requestID + " - " + "MarketMainController.updateInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "MarketMainController.updateInfo - " + "initializing signin process");
        responseBean = marketMainService.updateInfo(requestBean, responseBean);
        logger.info(requestID + " - " + "MarketMainController.updateInfo - " + "Signin completed");
        return responseBean;
    }

    @PostMapping(value = "/getinfo")
    public ResponseBean getInfo(@RequestBody RequestBean requestBean,
                                @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "MarketMainController.getInfo");
        logger.debug(requestID + " - " + "MarketMainController.getInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "MarketMainController.getInfo - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = marketMainService.getinfo(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "MarketMainController.getInfo - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/deleteaccount/{myid}")
    public ResponseBean deleteAccount(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String myid) throws Exception {
        logger.info(requestID + " - " + "MarketMainController.delete");
        logger.debug(requestID + " - " + "MarketMainController.delete - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "MarketMainController.delete - " + "initializing delete  process");
        responseBean = marketMainService.deleteAccount(myid, requestBean,responseBean);
        logger.info(requestID + " - " + "MarketMainController.delete - " + "Delete  completed");

        return responseBean;
    }

}

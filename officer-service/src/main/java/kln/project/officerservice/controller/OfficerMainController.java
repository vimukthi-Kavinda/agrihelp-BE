package kln.project.officerservice.controller;


import kln.project.officerservice.model.bean.DataTableBean;
import kln.project.officerservice.model.bean.RequestBean;
import kln.project.officerservice.model.bean.ResponseBean;
import kln.project.officerservice.service.OfficerMainService;
import kln.project.officerservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/officermain")
public class OfficerMainController {

    //try to create different controllers for admin , post , community services as
    // @RequestMapping("/officeradmin"),@RequestMapping("/officerpost")
    //map them in git yml file ..officer-service/officeradmin/confirmfarmerreg

    Logger logger = LoggerFactory.getLogger(OfficerMainController.class);
    @Autowired
    private ResponseBean responseBean;

    @Autowired
    OfficerMainService officerMainService;


    @PostMapping(value = "/login")
    public ResponseBean login(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerMainController.login");
        logger.debug(requestID + " - " + "OfficerMainController.login - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerMainController.login - " + "initializing login process");
        responseBean = officerMainService.login(requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerMainController.login - " + "Login completed");
        return responseBean;
    }

    @PostMapping(value = "/signin")
    public ResponseBean signin(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerMainController.signin");
        logger.debug(requestID + " - " + "OfficerMainController.signin - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerMainController.signin - " + "initializing signin process");
        responseBean = officerMainService.signin(requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerMainController.signin - " + "Signin completed");
        return responseBean;
    }





    @PostMapping(value = "/updateinfo")
    public ResponseBean updateInfo(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerMainController.updateInfo");
        logger.debug(requestID + " - " + "OfficerMainController.updateInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerMainController.updateInfo - " + "initializing signin process");
        responseBean = officerMainService.updateInfo(requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerMainController.updateInfo - " + "Signin completed");
        return responseBean;
    }

    @PostMapping(value = "/getinfo")
    public ResponseBean getInfo(@RequestBody RequestBean requestBean,
                                @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerMainController.getInfo");
        logger.debug(requestID + " - " + "OfficerMainController.getInfo - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerMainController.getInfo - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = officerMainService.getinfo(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerMainController.getInfo - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/deleteaccount/{myid}")
    public ResponseBean deleteAccount(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String myid) throws Exception {
        logger.info(requestID + " - " + "OfficerMainController.delete");
        logger.debug(requestID + " - " + "OfficerMainController.delete - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerMainController.delete - " + "initializing delete  process");
        responseBean = officerMainService.deleteAccount(myid, requestBean,responseBean);
        logger.info(requestID + " - " + "OfficerMainController.delete - " + "Delete  completed");

        return responseBean;
    }



///




}

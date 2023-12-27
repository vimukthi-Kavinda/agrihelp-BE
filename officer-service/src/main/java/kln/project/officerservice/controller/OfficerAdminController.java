package kln.project.officerservice.controller;

import kln.project.officerservice.model.bean.DataTableBean;
import kln.project.officerservice.model.bean.RequestBean;
import kln.project.officerservice.model.bean.ResponseBean;
import kln.project.officerservice.service.OfficerAdminService;
import kln.project.officerservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/officeradmin")
public class OfficerAdminController {

    Logger logger = LoggerFactory.getLogger(OfficerAdminController.class);
    @Autowired
    private ResponseBean responseBean;

    @Autowired
    OfficerAdminService officerAdminService;



    @PostMapping(value = "/confirmfarmerreg/{farmerid}")
    public ResponseBean confirmFarmerReg(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String farmerid) throws Exception {
        logger.info(requestID + " - " + "OfficerController.confirm");
        logger.debug(requestID + " - " + "OfficerController.confirm - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.confirm - " + "initializing confirm process");
        responseBean = officerAdminService.confirmFarmerReg(farmerid, requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerController.confirm - " + "Confirm completed");

        return responseBean;
    }

    @PostMapping(value = "/rejectfarmerreg/{farmerid}")
    public ResponseBean rejectFarmerReg(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String farmerid) throws Exception {
        logger.info(requestID + " - " + "OfficerController.reject");
        logger.debug(requestID + " - " + "OfficerController.reject - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.reject - " + "initializing reject  process");
        responseBean = officerAdminService.rejectFarmerReg(farmerid, requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerController.reject - " + "Reject completed");

        return responseBean;
    }

    @PostMapping(value = "/confirmcustomerreg/{nic}")
    public ResponseBean confirmCustomerReg(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String nic) throws Exception {
        logger.info(requestID + " - " + "OfficerController.confirm");
        logger.debug(requestID + " - " + "OfficerController.confirm - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.confirm - " + "initializing confirm process");
        responseBean = officerAdminService.confirmCustomerReg(nic, requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerController.confirm - " + "Confirm completed");

        return responseBean;
    }


    @PostMapping(value = "/rejectcustomerreg/{nic}")
    public ResponseBean rejectCustomerReg(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String nic) throws Exception {
        logger.info(requestID + " - " + "OfficerController.reject");
        logger.debug(requestID + " - " + "OfficerController.reject - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.reject - " + "initializing reject  process");
        responseBean = officerAdminService.rejectCustomerReg(nic, requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerController.reject - " + "Reject completed");

        return responseBean;
    }
    @PostMapping(value = "/confirmofficerreg/{id}")
    public ResponseBean confirmOfficerReg(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String id) throws Exception {
        logger.info(requestID + " - " + "OfficerController.confirm");
        logger.debug(requestID + " - " + "OfficerController.confirm - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.confirm - " + "initializing confirm process");
        responseBean = officerAdminService.confirmOfficerReg(id, requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerController.confirm - " + "Confirm completed");

        return responseBean;
    }
    @PostMapping(value = "/rejectofficerreg/{id}")
    public ResponseBean rejectOfficerReg(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String id) throws Exception {
        logger.info(requestID + " - " + "OfficerController.reject");
        logger.debug(requestID + " - " + "OfficerController.reject - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.reject - " + "initializing reject  process");
        responseBean = officerAdminService.rejectOfficerReg(id, requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerController.reject - " + "Reject completed");

        return responseBean;
    }

    @PostMapping(value = "/confirmpostreg/{postid}")
    public ResponseBean confirmPostReg(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String postid) throws Exception {
        logger.info(requestID + " - " + "OfficerController.confirm");
        logger.debug(requestID + " - " + "OfficerController.confirm - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.confirm - " + "initializing confirm process");
        responseBean = officerAdminService.confirmPostReg(postid, requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerController.confirm - " + "Confirm completed");

        return responseBean;
    }

    @PostMapping(value = "/rejectpostreg/{postid}")
    public ResponseBean rejectPostReg(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String postid) throws Exception {
        logger.info(requestID + " - " + "OfficerController.reject");
        logger.debug(requestID + " - " + "OfficerController.reject - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.reject - " + "initializing reject  process");
        responseBean = officerAdminService.rejectPostReg(postid, requestBean, responseBean);
        logger.info(requestID + " - " + "OfficerController.reject - " + "Reject completed");

        return responseBean;
    }

    @PostMapping(value = "/getfarmerreqlist")
    public ResponseBean getFarmerReqList(@RequestBody RequestBean requestBean,
                                         @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerController.getDualAuthList");
        logger.debug(requestID + " - " + "OfficerController.getDualAuthList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "initializing bin list retrieval process");
        DataTableBean dataTableBean = officerAdminService.getFarmerReqList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "Bin list retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/getcustomerreqlist")
    public ResponseBean getCustomerReqList(@RequestBody RequestBean requestBean,
                                           @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerController.getDualAuthList");
        logger.debug(requestID + " - " + "OfficerController.getDualAuthList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "initializing bin list retrieval process");
        DataTableBean dataTableBean = officerAdminService.getCustomerReqList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "Bin list retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/getpostreqlist")
    public ResponseBean getPostReqList(@RequestBody RequestBean requestBean,
                                       @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerController.getDualAuthList");
        logger.debug(requestID + " - " + "OfficerController.getDualAuthList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "initializing bin list retrieval process");
        DataTableBean dataTableBean = officerAdminService.getPostReqList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "Bin list retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/getofficerreqlist")
    public ResponseBean getOfficerReqList(@RequestBody RequestBean requestBean,
                                       @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerController.getDualAuthList");
        logger.debug(requestID + " - " + "OfficerController.getDualAuthList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "initializing bin list retrieval process");
        DataTableBean dataTableBean = officerAdminService.getOfficerReqList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "Bin list retrieval completed");

        return responseBean;
    }
}

package kln.project.customerservice.controller;

import kln.project.customerservice.model.bean.DataTableBean;
import kln.project.customerservice.model.bean.RequestBean;
import kln.project.customerservice.model.bean.ResponseBean;
import kln.project.customerservice.service.CustomerService;
import kln.project.customerservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/customerhelpservice")
@RestController
public class CustomerServiceController {

    Logger logger = LoggerFactory.getLogger(CustomerServiceController.class);
    @Autowired
    private ResponseBean responseBean;

    @Autowired
    CustomerService customerService;

    //update price
    //send market id with product id in body
    @PostMapping(value = "/updateprice")
    public ResponseBean updateprice(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerServiceController.updateprice");
        logger.debug(requestID + " - " + "CustomerServiceController.updateprice - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerServiceController.updateprice - " + "initializing update process");
        responseBean = customerService.updateprice(requestBean, responseBean);
        logger.info(requestID + " - " + "CustomerServiceController.updateprice - " + "Edit completed");

        return responseBean;
    }

    @PostMapping(value = "/farmerlist")
    public ResponseBean getFarmerList(@RequestBody RequestBean requestBean,
                                    @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerServiceController.getFarmerList");
        logger.debug(requestID + " - " + "CustomerServiceController.getFarmerList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerServiceController.getFarmerList - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = customerService.getFarmerList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "CustomerServiceController.getFarmerList - " + "Posts retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/viewfamercrop/{farmerid}")
    public ResponseBean viewFamerCrop(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String farmerid) throws Exception {
        logger.info(requestID + " - " + "CustomerServiceController.view");
        logger.debug(requestID + " - " + "CustomerServiceController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerServiceController.view - " + "initializing view  process");
        responseBean = customerService.viewFamerCrop(farmerid, responseBean);
        logger.info(requestID + " - " + "CustomerServiceController.view - " + "viewing completed");

        return responseBean;
    }



    @PostMapping(value = "/marketlist")
    public ResponseBean viewnearmarkets(@RequestBody RequestBean requestBean,
                                        @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerServiceController.viewnearmarkets");
        logger.debug(requestID + " - " + "CustomerServiceController.viewnearmarkets - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerServiceController.viewnearmarkets - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = customerService.viewnearmarkets(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "CustomerServiceController.viewnearmarkets - " + "Posts retrieval completed");

        return responseBean;
    }




    @PostMapping(value = "/viewmarketprice/{marcketid}")
    public ResponseBean viewMarketPrice(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String marcketid) throws Exception {
        logger.info(requestID + " - " + "CustomerServiceController.view");
        logger.debug(requestID + " - " + "CustomerServiceController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerServiceController.view - " + "initializing view  process");
        responseBean = customerService.viewMarketPrice(marcketid, responseBean);
        logger.info(requestID + " - " + "CustomerServiceController.view - " + "viewing completed");

        return responseBean;
    }


    @PostMapping(value = "/officerlist")
    public ResponseBean officerList(@RequestBody RequestBean requestBean,
                                        @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "CustomerServiceController.officerlist");
        logger.debug(requestID + " - " + "CustomerServiceController.officerlist - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "CustomerServiceController.officerlist - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = customerService.officerList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "CustomerServiceController.officerlist - " + "Posts retrieval completed");

        return responseBean;
    }


    
}

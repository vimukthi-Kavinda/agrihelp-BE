package kln.project.farmerservice.controller;

import kln.project.farmerservice.model.bean.DataTableBean;
import kln.project.farmerservice.model.bean.RequestBean;
import kln.project.farmerservice.model.bean.ResponseBean;
import kln.project.farmerservice.service.FarmerService;
import kln.project.farmerservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/farmerhelpservice")
public class FarmerServiceController {


    Logger logger = LoggerFactory.getLogger(FarmerServiceController.class);
    @Autowired
    private ResponseBean responseBean;



    @Autowired
    FarmerService farmerService;


    @PostMapping(value = "/shoplist")
    public ResponseBean getShopList(@RequestBody RequestBean requestBean,
                                      @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.getShopList");
        logger.debug(requestID + " - " + "FarmerServiceController.getShopList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.getShopList - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerService.getShopList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerServiceController.getShopList - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/marketlist")
    public ResponseBean viewnearmarkets(@RequestBody RequestBean requestBean,
                                    @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.viewnearmarkets");
        logger.debug(requestID + " - " + "FarmerServiceController.viewnearmarkets - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.viewnearmarkets - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerService.viewnearmarkets(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerServiceController.viewnearmarkets - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/plaguelist")
    public ResponseBean getPlagueList(@RequestBody RequestBean requestBean,
                                    @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.getPlagueList");
        logger.debug(requestID + " - " + "FarmerServiceController.getPlagueList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.getPlagueList - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerService.getPlagueList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerServiceController.getPlagueList - " + "Posts retrieval completed");

        return responseBean;
    }



    @PostMapping(value = "/viewmarketprice/{marcketid}")
    public ResponseBean viewMarketPrice(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String marcketid) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.view");
        logger.debug(requestID + " - " + "FarmerServiceController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.view - " + "initializing view  process");
        responseBean = farmerService.viewMarketPrice(marcketid, responseBean);
        logger.info(requestID + " - " + "FarmerServiceController.view - " + "viewing completed");

        return responseBean;
    }

    @PostMapping(value = "/viewshopitems/{shopid}")
    public ResponseBean viewshopitems(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String shopid) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.view");
        logger.debug(requestID + " - " + "FarmerServiceController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.view - " + "initializing view  process");
        responseBean = farmerService.viewshopitems(shopid, responseBean);
        logger.info(requestID + " - " + "FarmerServiceController.view - " + "viewing completed");

        return responseBean;
    }

    @PostMapping(value = "/plaguedetails/{plagueid}")
    public ResponseBean viewPlageDetails(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String plagueid) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.view");
        logger.debug(requestID + " - " + "FarmerServiceController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.view - " + "initializing view  process");
        responseBean = farmerService.viewPlageDetails(plagueid, responseBean);
        logger.info(requestID + " - " + "FarmerServiceController.view - " + "viewing completed");

        return responseBean;
    }



    ////////////crop mgt

    @PostMapping(value = "/mycroplist")
    public ResponseBean getMyCropList(@RequestBody RequestBean requestBean,
                                      @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.getMyCropList");
        logger.debug(requestID + " - " + "FarmerServiceController.getMyCropList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.getMyCropList - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerService.getMyCropList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerServiceController.getMyCropList - " + "Posts retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/deletemycrop/{farmerid}/{cropcode}")
    public ResponseBean deleteMyCrop(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String farmerid, @PathVariable String cropcode) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.deleteMyCrop");
        logger.debug(requestID + " - " + "FarmerServiceController.deleteMyCrop - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.deleteMyCrop - " + "initializing delete process");
        responseBean = farmerService.deleteMyCrop(farmerid,cropcode, responseBean);
        logger.info(requestID + " - " + "FarmerServiceController.deleteMyCrop - " + "Delete  completed");

        return responseBean;
    }


    @PostMapping(value = "/addmycrop")
    public ResponseBean addMyCrop(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.addMyCrop");
        logger.debug(requestID + " - " + "FarmerServiceController.addMyCrop - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.addMyCrop - " + "initializing add process");
        responseBean = farmerService.addMyCrop(requestBean, responseBean);
        logger.info(requestID + " - " + "FarmerServiceController.addMyCrop - " + "Add  completed");

        return responseBean;
    }

    //amount
    @PostMapping(value = "/editmycrop")
    public ResponseBean editMyCrop(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.editMyCrop");
        logger.debug(requestID + " - " + "FarmerServiceController.editMyCrop - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.editMyCrop - " + "initializing edit process");
        responseBean = farmerService.editMyCrop(requestBean, responseBean);
        logger.info(requestID + " - " + "FarmerServiceController.editMyCrop - " + "Edit  completed");

        return responseBean;
    }


    @PostMapping(value = "/allcroplist")
    public ResponseBean getAllCropList(@RequestBody RequestBean requestBean,
                                      @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.getAllCropList");
        logger.debug(requestID + " - " + "FarmerServiceController.getAllCropList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.getAllCropList - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerService.getAllCropList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerServiceController.getAllCropList - " + "Posts retrieval completed");

        return responseBean;
    }

    @PostMapping(value = "/viewcropfertilizer/{cropcode}")
    public ResponseBean viewCropFertilizer(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String cropcode) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.view");
        logger.debug(requestID + " - " + "FarmerServiceController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.view - " + "initializing view  process");
        responseBean = farmerService.viewCropFertilizer(cropcode, responseBean);
        logger.info(requestID + " - " + "FarmerServiceController.view - " + "viewing completed");

        return responseBean;
    }


    @PostMapping(value = "/officerlist")
    public ResponseBean officerList(@RequestBody RequestBean requestBean,
                                    @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.officerlist");
        logger.debug(requestID + " - " + "FarmerServiceController.officerlist - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.officerlist - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = farmerService.officerList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "FarmerServiceController.officerlist - " + "Posts retrieval completed");

        return responseBean;
    }


    @PostMapping(value = "/productdetails/{productid}")
    public ResponseBean viewProductDetails(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String productid) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.view");
        logger.debug(requestID + " - " + "FarmerServiceController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.view - " + "initializing view  process");
        responseBean = farmerService.viewProductDetails(productid, responseBean);
        logger.info(requestID + " - " + "FarmerServiceController.view - " + "viewing completed");

        return responseBean;
    }


    @PostMapping(value = "/getrecomandedpestlist/{diseaseid}")
    public ResponseBean getRecomandedpestlist(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String diseaseid) throws Exception {
        logger.info(requestID + " - " + "FarmerServiceController.view");
        logger.debug(requestID + " - " + "FarmerServiceController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerServiceController.view - " + "initializing view  process");
        responseBean = farmerService.getRecomandedpestlist(diseaseid, responseBean);
        logger.info(requestID + " - " + "FarmerServiceController.view - " + "viewing completed");

        return responseBean;
    }

}

package kln.project.shopservice.controller;

import kln.project.shopservice.model.bean.DataTableBean;
import kln.project.shopservice.model.bean.RequestBean;
import kln.project.shopservice.model.bean.ResponseBean;
import kln.project.shopservice.service.ShopHelpService;
import kln.project.shopservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopservice")
public class ShopServiceController {

    Logger logger = LoggerFactory.getLogger(ShopServiceController.class);
    @Autowired
    private ResponseBean responseBean;

    @Autowired
    ShopHelpService shopMainService;


    @PostMapping(value = "/addnewproduct")
    public ResponseBean addnewproduct(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ShopServiceController.add");
        logger.debug(requestID + " - " + "ShopServiceController.add - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ShopServiceController.add - " + "initializing add  process");
        responseBean = shopMainService.addnewproduct(requestBean, responseBean);
        logger.info(requestID + " - " + "ShopServiceController.add - " + "Add  completed");

        return responseBean;
    }

    @PostMapping(value = "/addproductstore")
    public ResponseBean addproductstore(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ShopServiceController.add");
        logger.debug(requestID + " - " + "ShopServiceController.add - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ShopServiceController.add - " + "initializing add  process");
        responseBean = shopMainService.addproductstore(requestBean, responseBean);
        logger.info(requestID + " - " + "ShopServiceController.add - " + "Add  completed");

        return responseBean;
    }

    @PostMapping(value = "/editproduct")
    public ResponseBean editproduct(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ShopServiceController.edit");
        logger.debug(requestID + " - " + "ShopServiceController.edit - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ShopServiceController.edit - " + "initializing edit  process");
        responseBean = shopMainService.editproduct(requestBean, responseBean);
        logger.info(requestID + " - " + "ShopServiceController.edit - " + "Edit  completed");

        return responseBean;
    }

    @PostMapping(value = "/deleteproduct/{id}")
    public ResponseBean delete(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String id) throws Exception {
        logger.info(requestID + " - " + "ShopServiceController.delete");
        logger.debug(requestID + " - " + "ShopServiceController.delete - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ShopServiceController.delete - " + "initializing delete  process");
        responseBean = shopMainService.delete(id, requestBean,responseBean);
        logger.info(requestID + " - " + "ShopServiceController.delete - " + "Delete  completed");

        return responseBean;
    }



    @PostMapping(value = "/myproductlist")
    public ResponseBean getList(@RequestBody RequestBean requestBean,
                                @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ShopServiceController.getList");
        logger.debug(requestID + " - " + "ShopServiceController.getList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ShopServiceController.getList - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = shopMainService.getList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "ShopServiceController.getList - " + "Branch list retrieval completed");

        return responseBean;
    }



    @PostMapping(value = "/productdetails/{productid}")
    public ResponseBean viewProductDetails(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String productid) throws Exception {
        logger.info(requestID + " - " + "ShopServiceController.view");
        logger.debug(requestID + " - " + "ShopServiceController.view - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ShopServiceController.view - " + "initializing view  process");
        responseBean = shopMainService.viewProductDetails(productid, responseBean);
        logger.info(requestID + " - " + "ShopServiceController.view - " + "viewing completed");

        return responseBean;
    }
}

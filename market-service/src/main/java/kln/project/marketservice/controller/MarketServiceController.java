package kln.project.marketservice.controller;



import kln.project.marketservice.model.bean.DataTableBean;
import kln.project.marketservice.model.bean.RequestBean;
import kln.project.marketservice.model.bean.ResponseBean;
import kln.project.marketservice.service.MarketHelpService;
import kln.project.marketservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marketservice")
public class MarketServiceController {


    Logger logger = LoggerFactory.getLogger(MarketServiceController.class);
    @Autowired
    private ResponseBean responseBean;

    @Autowired
    MarketHelpService marketMainService;


    @PostMapping(value = "/addcrop")
    public ResponseBean add(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "MarketMainController.add");
        logger.debug(requestID + " - " + "MarketMainController.add - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "MarketMainController.add - " + "initializing add  process");
        responseBean = marketMainService.add(requestBean, responseBean);
        logger.info(requestID + " - " + "MarketMainController.add - " + "Add  completed");

        return responseBean;
    }

    @PostMapping(value = "/editcrop")
    public ResponseBean edit(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "MarketMainController.edit");
        logger.debug(requestID + " - " + "MarketMainController.edit - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "MarketMainController.edit - " + "initializing edit  process");
        responseBean = marketMainService.edit(requestBean, responseBean);
        logger.info(requestID + " - " + "MarketMainController.edit - " + "Edit  completed");

        return responseBean;
    }

    @PostMapping(value = "/deletecrop/{cropcode}")
    public ResponseBean delete(@RequestBody RequestBean requestBean, @RequestHeader String requestID,@PathVariable String cropcode) throws Exception {
        logger.info(requestID + " - " + "MarketMainController.delete");
        logger.debug(requestID + " - " + "MarketMainController.delete - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "MarketMainController.delete - " + "initializing delete  process");
        responseBean = marketMainService.delete(cropcode, requestBean,responseBean);
        logger.info(requestID + " - " + "MarketMainController.delete - " + "Delete  completed");

        return responseBean;
    }



    @PostMapping(value = "/mycroplist")
    public ResponseBean getList(@RequestBody RequestBean requestBean,
                                @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "MarketMainController.getList");
        logger.debug(requestID + " - " + "MarketMainController.getList - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "MarketMainController.getList - " + "initializing  list retrieval process");
        DataTableBean dataTableBean = marketMainService.getList(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(dataTableBean);
        logger.info(requestID + " - " + "MarketMainController.getList - " + "list retrieval completed");

        return responseBean;
    }
}

package kln.project.farmerservice.controller;

import kln.project.farmerservice.model.bean.DataTableBean;
import kln.project.farmerservice.model.bean.RequestBean;
import kln.project.farmerservice.model.bean.ResponseBean;
import kln.project.farmerservice.service.FarmerPostService;
import kln.project.farmerservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/farmerpost")
public class FarmerPostController {

    Logger logger = LoggerFactory.getLogger(FarmerPostController.class);
    @Autowired
    private ResponseBean responseBean;


    @Autowired
    FarmerPostService farmerPostService;


    @PostMapping(value = "/uploadpost")
    public ResponseBean uploadpost(
            @RequestBody RequestBean requestBean,
            @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "FarmerPostController.uploadpost");
        logger.debug(requestID + " - " + "FarmerPostController.uploadpost - " + "clientip    - " + requestBean.getClient_ip());


        logger.info(requestID + " - " + "FarmerPostController.uploadpost - " + "initializing post upload process");

        byte[] fileContent=requestBean.getMultipartFile();
        String fileName=requestBean.getFileNm();
        long fileSize=requestBean.getFileSize();


        //String fileType=(String) requestBean.getRequestBody();
        String un=requestBean.getUsername();

        responseBean = farmerPostService.uploadPost(fileContent,fileName, fileSize,requestBean,un);



        logger.info(requestID + " - " + "FarmerPostController.uploadpost - " + "post upload completed");

        return responseBean;
    }


    @PostMapping(value = "/deletepost/{postid}")
    public ResponseBean deletepost(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String postid) throws Exception {
        logger.info(requestID + " - " + "FarmerPostController.deletepost");
        logger.debug(requestID + " - " + "FarmerPostController.deletepost - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "FarmerPostController.deletepost - " + "initializing delete post process");
        responseBean = farmerPostService.deletepost(postid,requestBean.getUsername(), responseBean);
        logger.info(requestID + " - " + "FarmerPostController.deletepost - " + "Delete post completed");

        return responseBean;
    }


}

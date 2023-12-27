package kln.project.officerservice.controller;

import kln.project.officerservice.model.bean.DataTableBean;
import kln.project.officerservice.model.bean.RequestBean;
import kln.project.officerservice.model.bean.ResponseBean;
import kln.project.officerservice.service.OfficerPostService;
import kln.project.officerservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/officerpost")
public class OfficerPostController {

    Logger logger = LoggerFactory.getLogger(OfficerPostController.class);
    @Autowired
    private ResponseBean responseBean;


    @Autowired
    OfficerPostService officerPostService;

    @PostMapping(value = "/uploadpost")
    public ResponseBean uploadpost(
            @RequestBody RequestBean requestBean,
            @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "OfficerPostController.uploadpost");
        logger.debug(requestID + " - " + "OfficerPostController.uploadpost - " + "clientip    - " + requestBean.getClient_ip());


        logger.info(requestID + " - " + "OfficerPostController.uploadpost - " + "initializing post upload process");

        byte[] fileContent=requestBean.getMultipartFile();
        String fileName=requestBean.getFileNm();
        long fileSize=requestBean.getFileSize();


        //String fileType=(String) requestBean.getRequestBody();
        String un=requestBean.getUsername();

        responseBean = officerPostService.uploadPost(fileContent,fileName, fileSize,requestBean,un);



        logger.info(requestID + " - " + "OfficerPostController.uploadpost - " + "post upload completed");

        return responseBean;
    }


    @PostMapping(value = "/deletepost/{postid}")
    public ResponseBean deletepost(@RequestBody RequestBean requestBean, @RequestHeader String requestID, @PathVariable String postid) throws Exception {
        logger.info(requestID + " - " + "OfficerPostController.deletepost");
        logger.debug(requestID + " - " + "OfficerPostController.deletepost - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "OfficerPostController.deletepost - " + "initializing delete post process");
        responseBean = officerPostService.deletepost(postid,requestBean.getUsername(), responseBean);
        logger.info(requestID + " - " + "OfficerPostController.deletepost - " + "Delete post completed");

        return responseBean;
    }




}

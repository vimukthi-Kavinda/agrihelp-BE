package kln.project.mainservice.controller.officer;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kln.project.mainservice.bean.ResponseBean;
import kln.project.mainservice.controller.officer.OfficerController;
import kln.project.mainservice.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("kln_farming_project/officer")
public class OfficerController {
    Logger logger = LoggerFactory.getLogger(OfficerController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private Environment env;


    @ApiOperation(value = "officer management login", notes = "This service logins officer")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                   @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                   @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url=null;
        service_url = env.getProperty("kln_farming_project.officer.login");
        if(service_url!=null)
        //  ObjectMapper mapper = new ObjectMapper();
        //ClientBean clientBean = mapper.convertValue(responseBean.getContent(), ClientBean.class);
        {
            logger.info(requestID + " - " + "OfficerController.login - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //add a new officer- dual auth

    @ApiOperation(value = "officer management signin", notes = "This service logins officer")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signin(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                    @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                    @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url=null;
        service_url = env.getProperty("kln_farming_project.officer.signin");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "OfficerController.signin - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }





    //update details
    @ApiOperation(value = "officer management updateInfo", notes = "This service updates customer")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/updateinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateInfo(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                        @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                        @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url=null;
        service_url = env.getProperty("kln_farming_project.officer.updateinfo");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "officerController.updateinfo - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //delete profile

    @ApiOperation(value = "officer management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/deleteaccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAccount(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @PathVariable String id) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.deleteaccount") + "//" + id;

        logger.info(requestID + " - " + "officerController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //get my details


    @ApiOperation(value = "officer detail view", notes = "This service view officer price")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInfo(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                     @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
                                    // @PathVariable String myid
                                     ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.getinfo");//+ "//" + myid;


        logger.info(requestID + " - " + "officerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }
















    ///////////////////////////////////////////


    //post a post -> upload a img -no dual auth

    @ApiOperation(value = "post upload  loading", notes = "This service returns officer post upload details ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/uploadpost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadpost(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                        @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                        //@RequestBody(required = false) Object requestBody,
                                        @RequestParam("posttype") String postType,
                                        @RequestParam("postsubject") String postsubject,@RequestParam(name="cropcode",required = false) String cropcode, @RequestParam("postdesc") String postdesc,//post description has #tags -> considerd as tag
                                        @RequestParam(name="imgfile",required = false) MultipartFile multipartFile

    )
            throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.uploadpost");


        logger.info(requestID + " - " + "OfficerController.upload - " + "request redirecting to core service...");

        //get multipart file content and name
        byte[] fileContent = null;
        long fileSize = 0;
        String fileName = null;
        if(multipartFile!=null) {
            fileContent = multipartFile.getBytes();
            fileSize = multipartFile.getSize();
            fileName = multipartFile.getOriginalFilename();
        }

        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, postType,postsubject,postdesc, requestID, fileContent, fileSize, fileName,cropcode);



        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //delete post
    @ApiOperation(value = "Farmer management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/deletepost/{postid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePost(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                        @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                        @PathVariable String postid) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.deletepost") + "//" + postid;

        logger.info(requestID + " - " + "officerController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "management confirm Farmer Reg", notes = "This service confirm Farmer Reg")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/confirmfarmerreg/{farmerid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmFarmerReg(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                     @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                     @PathVariable String farmerid) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.confirmfarmerreg") + "//" + farmerid;

       logger.info(requestID + " - " + "OfficerController.confirm - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "management reject ", notes = "This service reject alert")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/rejectfarmerreg/{alertType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> rejectFarmerReg(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                    @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                    @PathVariable String alertType) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.rejectfarmerreg") + "//" + alertType;

        logger.info(requestID + " - " + "OfficerController.reject - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //cust

    @ApiOperation(value = "management confirm ", notes = "This service confirm alert")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/confirmcustomerreg/{nic}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmCustomerReg(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                              @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                              @PathVariable String nic) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.confirmcustomerreg") + "//" + nic;

        logger.info(requestID + " - " + "OfficerController.confirm - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "management reject ", notes = "This service reject alert")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/rejectcustomerreg/{nic}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> rejectCustomerReg(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                             @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                             @PathVariable String nic) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.rejectcustomerreg") + "//" + nic;

        logger.info(requestID + " - " + "OfficerController.reject - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //officer
    @ApiOperation(value = "management confirm ", notes = "This service confirm ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/confirmofficerreg/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmOfficerReg(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                            @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                            @PathVariable String id) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.confirmofficerreg") + "//" + id;

        logger.info(requestID + " - " + "OfficerController.confirm - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "management reject ", notes = "This service reject alert")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/rejectofficerreg/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> rejectOfficerReg(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                           @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                           @PathVariable String id) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.rejectofficerreg") + "//" + id;

        logger.info(requestID + " - " + "OfficerController.reject - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }
    
    //////////
    

    //post

    @ApiOperation(value = "management confirm ", notes = "This service confirm alert")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/confirmpostreg/{postid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmPostReg(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                              @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                              @PathVariable String postid) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.confirmpostreg") + "//" + postid;

        logger.info(requestID + " - " + "OfficerController.confirm - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "management reject ", notes = "This service reject alert")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/rejectpostreg/{postid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> rejectPostReg(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                             @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                             @PathVariable String postid) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.rejectpostreg") + "//" + postid;

        logger.info(requestID + " - " + "OfficerController.reject - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //officer join req
    @ApiOperation(value = "Alert configuration alert dual auth list loading", notes = "This service returns alert dual auth record list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getfarmerreqlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFarmerReqList(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                             @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                             @RequestBody(required = false) Object requestBody,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "farmerId,desc") String[] sort,
                                             @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.getfarmerreqlist");

        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);
        

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //cus join req
    @ApiOperation(value = "Alert configuration alert dual auth list loading", notes = "This service returns alert dual auth record list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getcustomerreqlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerReqList(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                             @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                             @RequestBody(required = false) Object requestBody,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "nic,desc") String[] sort,
                                             @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.getcustomerreqlist");

        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //post publish req
    @ApiOperation(value = "Alert configuration alert dual auth list loading", notes = "This service returns alert dual auth record list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getpostreqlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPostReqList(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                             @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                             @RequestBody(required = false) Object requestBody,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "postedTime,desc") String[] sort,
                                             @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.getpostreqlist");

        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "Alert configuration alert dual auth list loading", notes = "This service returns alert dual auth record list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getofficerreqlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOfficerReqList(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                            @RequestHeader(value = "username") String username, @RequestHeader(value = "userrole") String userrole,
                                            @RequestBody(required = false) Object requestBody,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "officerId,desc") String[] sort,
                                            @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.getofficerreqlist");

        logger.info(requestID + " - " + "OfficerController.getDualAuthList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }







    //get 25 posts sort by date -> list
    @ApiOperation(value = "Officer data list loading", notes = "This service returns  data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getnewposts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getnewPosts(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                         @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                         @RequestBody(required = false) Object requestBody,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                         @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.getnewposts");

        logger.info(requestID + " - " + "OfficerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "Officer data list loading", notes = "This service returns  data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/gettipposts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> gettipPosts(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                         @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                         @RequestBody(required = false) Object requestBody,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                         @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.gettipposts");

        logger.info(requestID + " - " + "OfficerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }
    @ApiOperation(value = "Officer data list loading", notes = "This service returns  data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getnewsposts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getNewsPosts(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                          @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                          @RequestBody(required = false) Object requestBody,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                          @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.getnewsposts");

        logger.info(requestID + " - " + "OfficerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "Officer data list loading", notes = "This service returns  data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getmyposts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMyPosts(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                        @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                        @RequestBody(required = false) Object requestBody,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                        @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.getmyposts");

        logger.info(requestID + " - " + "OfficerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }
    //react post -add if already added then update
    @ApiOperation(value = "officer management react", notes = "This service rect to posts")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/reactpost", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reactpost(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                       @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                       @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url=null;
        service_url = env.getProperty("kln_farming_project.officer.reactpost");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "OfficerController.signin - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //comment post - crud
    //add -postid come in body
    @ApiOperation(value = "officer management add ", notes = "This service add ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/addpostcomment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPostComment(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                            @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole, @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.addpostcomment");

        logger.info(requestID + " - " + "OfficerController.add - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, requestBody, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //edit- postid and comment id come in body


    //delete postid and comment id come in body
    @ApiOperation(value = "officer management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
   // @PostMapping(value = "/deletepostcomment/{postid}/{commentid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/deletepostcomment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePostComment(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                               @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                              // @PathVariable String postid,@PathVariable String commentid) throws Exception {
                                               @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.deletepostcomment") ;

        logger.info(requestID + " - " + "OfficerController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, requestBody, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    
    @ApiOperation(value = "officer management add disease", notes = "This service add disease")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/adddisease", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addDisease(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                    @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                    @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url=null;
        service_url = env.getProperty("kln_farming_project.officer.adddisease");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "OfficerController.signin - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "Officer management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/deletedisease/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteDisease(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                        @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                        @PathVariable String id) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.deletedisease") + "//" + id;

        logger.info(requestID + " - " + "officerController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }




    @ApiOperation(value = "post data list loading", notes = "This service returns  data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getpostreactscomments", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPostReactsComments(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                   @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                                   @RequestBody(required = false) Object requestBody,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                                   @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.getpostreactscomments");

        logger.info(requestID + " - " + "officerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //initially search by area is must..and other search conditiions
    @ApiOperation(value = "Plague data list loading", notes = "This service returns Plague data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/plaguelist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPlagueList(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @RequestBody(required = false) Object requestBody,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "firstReportedDay,desc") String[] sort,
                                           @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.plaguelist");

        logger.info(requestID + " - " + "officerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "plague view", notes = "This service view plague details")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/plaguedetails/{plagueid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewPlageDetails(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                              @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                              @PathVariable String plagueid) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.plaguedetails")+ "//" + plagueid;


        logger.info(requestID + " - " + "officerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "officer management", notes = "This service report disease")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/reportdisease", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reportDisease(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                    @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                    @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url=null;
        service_url = env.getProperty("kln_farming_project.officer.reportdisease");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "OfficerController.reportdisease - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    @ApiOperation(value = "officer management add ", notes = "This service add ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/addcrop", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCrop(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                            @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole, @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.addcrop");

        logger.info(requestID + " - " + "OfficerController.add - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, requestBody, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    ///officer list

    @ApiOperation(value = "Officer data list loading", notes = "This service returns  data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/officerlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOfficerList(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                          @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                          @RequestBody(required = false) Object requestBody,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "officerName,desc") String[] sort,
                                          @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.officerlist");

        logger.info(requestID + " - " + "OfficerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "Farmer notification list loading", notes = "This service returns  notification list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/officernotification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOfficerNotification(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                   @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                                   @RequestBody(required = false) Object requestBody,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                                   @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.officernotification");

        logger.info(requestID + " - " + "OfficerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    @ApiOperation(value = "Farmer notification list loading", notes = "This service returns  notification list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/officerproductnotification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOfficerProductNotification(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                    @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                                    @RequestBody(required = false) Object requestBody,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                                    @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.officerproductnotification");

        logger.info(requestID + " - " + "OfficerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "Farmer notification list loading", notes = "This service returns  notification list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/officerplaguenotification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOfficerPlagueNotification(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                    @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                                    @RequestBody(required = false) Object requestBody,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                                    @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.officerplaguenotification");

        logger.info(requestID + " - " + "OfficerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "product view", notes = "This service view product details")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/productdetails/{productid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewProductDetails(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                                @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                                @PathVariable String productid) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.productdetails")+ "//" + productid;


        logger.info(requestID + " - " + "OfficerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "product view", notes = "This service view product details")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getrecomandedpestlist/{diseaseid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRecomandedPestlist(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                                   @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                                   @PathVariable String diseaseid) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.officer.getrecomandedpestlist")+ "//" + diseaseid;


        logger.info(requestID + " - " + "OfficerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }
}

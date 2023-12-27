package kln.project.mainservice.controller.market;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kln.project.mainservice.bean.ResponseBean;
import kln.project.mainservice.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("kln_farming_project/market")
public class MarketController {

    Logger logger = LoggerFactory.getLogger(MarketController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private Environment env;


    @ApiOperation(value = "officer management login", notes = "This service logins")
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
        service_url = env.getProperty("kln_farming_project.market.login");
        if(service_url!=null)
        //  ObjectMapper mapper = new ObjectMapper();
        //ClientBean clientBean = mapper.convertValue(responseBean.getContent(), ClientBean.class);
        {
            logger.info(requestID + " - " + "MarketController.login - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //add a new officer- dual auth

    @ApiOperation(value = "market management signin", notes = "This service registers ")
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
        service_url = env.getProperty("kln_farming_project.market.signin");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "MarketController.signin - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    //update details
    @ApiOperation(value = "market management updateInfo", notes = "This service updates customer")
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
        service_url = env.getProperty("kln_farming_project.market.updateinfo");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "MarketController.updateinfo - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //delete profile

    @ApiOperation(value = "market management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/deleteaccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAccount(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @PathVariable String id) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.market.deleteaccount") + "//" + id;

        logger.info(requestID + " - " + "MarketController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //get my details


    @ApiOperation(value = "market detail view", notes = "This service view market price")
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
        String service_url = env.getProperty("kln_farming_project.market.getinfo");//+ "//" + myid;


        logger.info(requestID + " - " + "MarketController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



















    //////////////////////////////////////////////
    
    
    
    

    //add
    @ApiOperation(value = "officer management add ", notes = "This service add ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/addcrop", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCrop(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                     @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole, @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.market.addcrop");

        logger.info(requestID + " - " + "MarketController.add - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, requestBody, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    @ApiOperation(value = "Plague data list loading", notes = "This service returns Plague data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/mycroplist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> mycroplist(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @RequestBody(required = false) Object requestBody,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "amount,desc") String[] sort,
                                           @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.market.mycroplist");

        logger.info(requestID + " - " + "MarketController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "market management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/deletecrop/{cropcode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCrop(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @PathVariable String cropcode) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.market.deletecrop") + "//" + cropcode;

        logger.info(requestID + " - " + "MarketController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

  @ApiOperation(value = "market management edit ", notes = "This service edit ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/editcrop", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editCrop(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                     @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole, @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.market.editcrop");

        logger.info(requestID + " - " + "MarketController.add - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, requestBody, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }
    
    
}

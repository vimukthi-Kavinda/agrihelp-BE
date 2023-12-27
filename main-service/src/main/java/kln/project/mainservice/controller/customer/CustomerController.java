package kln.project.mainservice.controller.customer;


import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("kln_farming_project/customer")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private Environment env;


    @ApiOperation(value = "Customer management login", notes = "This service logins customer")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                 @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                 @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url=null;
         service_url = env.getProperty("kln_farming_project.customer.login");
        if(service_url!=null)
          //  ObjectMapper mapper = new ObjectMapper();
            //ClientBean clientBean = mapper.convertValue(responseBean.getContent(), ClientBean.class);
        {
            logger.info(requestID + " - " + "CustomerController.login - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //add a new customer- dual auth

    @ApiOperation(value = "Customer management signin", notes = "This service logins customer")
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
        service_url = env.getProperty("kln_farming_project.customer.signin");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "CustomerController.signin - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

//update details
    @ApiOperation(value = "Customer management updateInfo", notes = "This service updates customer")
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
        service_url = env.getProperty("kln_farming_project.customer.updateinfo");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "CustomerController.updateinfo - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //delete profile

    @ApiOperation(value = "Customer management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/deleteaccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAccount(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @PathVariable String id) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.customer.deleteaccount") + "//" + id;

        logger.info(requestID + " - " + "CustomerController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //get my details


    @ApiOperation(value = "customer detail view", notes = "This service view market price")
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
        String service_url = env.getProperty("kln_farming_project.customer.getinfo");//+ "//" + myid;


        logger.info(requestID + " - " + "CustomerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }















    /////////////////////////////////////////////////////////

    
    //update price
    //send market id with product id in body
    @ApiOperation(value = "Customer management edit price", notes = "This service edit price")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/updateprice", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateprice(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                         @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                  @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.customer.updateprice");

       logger.info(requestID + " - " + "CustomerController.edit - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
       

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //get near by farmers-> serch by farmer location (must) and other factord

    @ApiOperation(value = "customer data list loading", notes = "This service returns  data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/farmerlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFarmerList(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @RequestBody(required = false) Object requestBody,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "farmerId,asc") String[] sort,
                                     @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.customer.farmerlist");

        logger.info(requestID + " - " + "CustomerController.getList - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }




    @ApiOperation(value = "market price view", notes = "This service view market price")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/viewfamercrop/{farmerid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewFamerCrop(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                             @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                             @PathVariable String farmerid) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.customer.viewfamercrop")+ "//" + farmerid;


        logger.info(requestID + " - " + "CustomerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    // get near by markets filter by area
    
    @ApiOperation(value = "customer data list loading", notes = "This service returns  data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/marketlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewnearmarkets(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @RequestBody(required = false) Object requestBody,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "marketId,asc") String[] sort,
                                           @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.customer.marketlist");

        logger.info(requestID + " - " + "CustomerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //get market price list of products
    
    @ApiOperation(value = "market price view", notes = "This service view market price")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/viewmarketprice/{marcketid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewMarketPrice(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                  @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                  @PathVariable String marcketid) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.customer.viewmarketprice")+ "//" + marcketid;


        logger.info(requestID + " - " + "CustomerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }
    
    
    //post a post -> upload a img -dual auth




    /////////////////////////////////////////////////////////////////////////////



//get 25 posts sort by date -> list

    @ApiOperation(value = "customer data list loading", notes = "This service returns  data list ")
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
        String service_url = env.getProperty("kln_farming_project.customer.gettipposts");

        logger.info(requestID + " - " + "CustomerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }
    @ApiOperation(value = "customer data list loading", notes = "This service returns  data list ")
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
        String service_url = env.getProperty("kln_farming_project.customer.getnewsposts");

        logger.info(requestID + " - " + "CustomerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



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
        String service_url = env.getProperty("kln_farming_project.customer.officerlist");

        logger.info(requestID + " - " + "CustomerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);


        return responseEntity;
    }



    @ApiOperation(value = "Customer notification list loading", notes = "This service returns  notification list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/customernotification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerNotification(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                            @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                            @RequestBody(required = false) Object requestBody,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                            @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.customer.customernotification");

        logger.info(requestID + " - " + "CustomerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "Customer notification list loading", notes = "This service returns  notification list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/customerproductnotification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerProductnotification(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                     @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                                     @RequestBody(required = false) Object requestBody,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                                     @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.customer.customerproductnotification");

        logger.info(requestID + " - " + "CustomerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }
}

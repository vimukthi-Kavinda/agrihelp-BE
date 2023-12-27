package kln.project.mainservice.controller.shop;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kln.project.mainservice.bean.ProductDataBean;
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
@RequestMapping("kln_farming_project/shop")
public class ShopController {

    Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private Environment env;



    @ApiOperation(value = "shop management login", notes = "This service logins shop")
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
        service_url = env.getProperty("kln_farming_project.shop.login");
        if(service_url!=null)
        //  ObjectMapper mapper = new ObjectMapper();
        //ClientBean clientBean = mapper.convertValue(responseBean.getContent(), ClientBean.class);
        {
            logger.info(requestID + " - " + "ShopController.login - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "shop management signin", notes = "This service logins shop")
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
        service_url = env.getProperty("kln_farming_project.shop.signin");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "ShopController.signin - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }





    //update details
    @ApiOperation(value = "shop management updateInfo", notes = "This service updates customer")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/updatestoreinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateInfo(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                        @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                        @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url=null;
        service_url = env.getProperty("kln_farming_project.shop.updatestoreinfo");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "ShopController.updateinfo - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //delete profile

    @ApiOperation(value = "shop management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/deletestore/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAccount(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @PathVariable String id) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.shop.deletestore") + "//" + id;

        logger.info(requestID + " - " + "MarketController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //get my details


    @ApiOperation(value = "shop detail view", notes = "This service view market price")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getstoreinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getInfo(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                     @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
                                   //  @PathVariable String myid
                                     ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.shop.getstoreinfo");//+ "//" + myid;


        logger.info(requestID + " - " + "ShopController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }











    @ApiOperation(value = "shop management signin", notes = "This service signin owner shop")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/signinowner", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> signinOwner(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                    @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                    @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url=null;
        service_url = env.getProperty("kln_farming_project.shop.signinowner");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "ShopController.signinowner - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }




    //update details
    @ApiOperation(value = "shop management updateInfo", notes = "This service updates customer")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/updateownerinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateOwnerInfo(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                        @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                        @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url=null;
        service_url = env.getProperty("kln_farming_project.shop.updateownerinfo");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "ShopController.updateinfo - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //delete profile

    @ApiOperation(value = "Shop management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/deleteowneraccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteOwnerAccount(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @PathVariable String id) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.shop.deleteowneraccount") + "//" + id;

        logger.info(requestID + " - " + "ShopController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //get my details


    @ApiOperation(value = "Shop detail view", notes = "This service view market price")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/getownerinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOwnerInfo(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                     @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
                                    // @PathVariable String myid
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.shop.getownerinfo");//+ "//" + myid;


        logger.info(requestID + " - " + "shopController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }




    ////////////////////////////////////////////////


    //add new product


    @ApiOperation(value = "officer management add ", notes = "This service add ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/addnewproduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewProduct(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                     @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           //@RequestBody Object requestBody) throws Exception {
                                           @RequestParam("productname") String productName,
                                           @RequestParam("price") String price,@RequestParam(name="manufacturedby",required = false) String manufacturedBy, @RequestParam("category") String category,
                                           @RequestParam(name="importedby",required = false) String importedBy,@RequestParam(name="expdate",required = false) String expdate,
                                           @RequestParam(name="imgfile",required = false) MultipartFile multipartFile,
                                           @RequestParam(name="usage",required = false) String usage,
                                           @RequestParam(name="amount",required = false) String amount
    )throws Exception{
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.shop.addnewproduct");

        ProductDataBean pb=new ProductDataBean();
        pb.setCategory(category);
        pb.setPrice(price);
        pb.setProductname(productName);
        pb.setManufacturedby(manufacturedBy);
        pb.setImportedby(importedBy);
        pb.setExpdate(expdate);
        pb.setUsage(usage);
        pb.setAmount(amount);

        if(multipartFile!=null) {
            pb.setFileContent(multipartFile.getBytes());
            pb.setFileSize(multipartFile.getSize());
            pb.setFileName(multipartFile.getOriginalFilename());
        }

        logger.info(requestID + " - " + "MarketController.add - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, pb, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //add product to shop
    @ApiOperation(value = "officer management add ", notes = "This service add ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/addproductstore", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addproductstore(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                     @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole, @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.shop.addproductstore");

        logger.info(requestID + " - " + "MarketController.add - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, requestBody, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    @ApiOperation(value = "Plague data list loading", notes = "This service returns Plague data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/myproductlist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> myproductlist(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                        @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                        @RequestBody(required = false) Object requestBody,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "price,desc") String[] sort,
                                        @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.shop.myproductlist");

        logger.info(requestID + " - " + "MarketController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "market management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/deleteproduct/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProduct(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                        @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                        @PathVariable String id) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.shop.deleteproduct") + "//" + id;

        logger.info(requestID + " - " + "MarketController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "market management edit ", notes = "This service edit ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/editproduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editProduct(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                      @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole, @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.shop.editproduct");

        logger.info(requestID + " - " + "MarketController.add - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, requestBody, requestID);


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
        String service_url = env.getProperty("kln_farming_project.shop.productdetails")+ "//" + productid;


        logger.info(requestID + " - " + "shopController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


}

package kln.project.mainservice.controller.farmer;


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
@RequestMapping("kln_farming_project/farmer")
public class FarmerController {
    Logger logger = LoggerFactory.getLogger(FarmerController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private Environment env;


    @ApiOperation(value = "farmer management login", notes = "This service logins farmer")
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
        service_url = env.getProperty("kln_farming_project.farmer.login");
        if(service_url!=null)
        //  ObjectMapper mapper = new ObjectMapper();
        //ClientBean clientBean = mapper.convertValue(responseBean.getContent(), ClientBean.class);
        {
            logger.info(requestID + " - " + "FarmerController.login - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //add a new farmer- dual auth

    @ApiOperation(value = "farmer management signin", notes = "This service logins farmer")
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
        service_url = env.getProperty("kln_farming_project.farmer.signin");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "FarmerController.signin - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }




    //update details
    @ApiOperation(value = "farmer management updateInfo", notes = "This service updates customer")
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
        service_url = env.getProperty("kln_farming_project.farmer.updateinfo");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "FarmerController.updateinfo - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //delete profile

    @ApiOperation(value = "farmer management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/deleteaccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAccount(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @PathVariable String id) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.deleteaccount") + "//" + id;

        logger.info(requestID + " - " + "FarmerController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //get my details


    @ApiOperation(value = "farmer detail view", notes = "This service view market price")
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
        String service_url = env.getProperty("kln_farming_project.farmer.getinfo");//+ "//" + myid;


        logger.info(requestID + " - " + "FarmerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


















    /////////////////////////////////////////
    
    
    //post a post -> upload a img -dual auth

    @ApiOperation(value = "post upload  loading", notes = "This service returns farmer post upload details ")
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
        String service_url = env.getProperty("kln_farming_project.farmer.uploadpost");


        logger.info(requestID + " - " + "FarmerController.upload - " + "request redirecting to core service...");

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
        String service_url = env.getProperty("kln_farming_project.farmer.deletepost") + "//" + postid;

        logger.info(requestID + " - " + "FarmerController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    // get near by markets filter by area

    @ApiOperation(value = "farmer data list loading", notes = "This service returns  data list ")
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
        String service_url = env.getProperty("kln_farming_project.farmer.marketlist");

        logger.info(requestID + " - " + "farmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


  /*  @ApiOperation(value = "market view", notes = "This service view market by areacode")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/viewmarkets/{areacode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewnearmarkets(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                             @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                             @PathVariable String areacode) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.viewmarkets")+ "//" + areacode;


        logger.info(requestID + " - " + "FarmerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }*/

    //get market price

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
        String service_url = env.getProperty("kln_farming_project.farmer.viewmarketprice")+ "//" + marcketid;


        logger.info(requestID + " - " + "FarmerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //get nearby shops -search by shop location is must and other factors
    @ApiOperation(value = "EOD file upload data list loading", notes = "This service returns area data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/shoplist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getShopList(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @RequestBody(required = false) Object requestBody,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "shopname,asc") String[] sort,
                                           @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.shoplist");

        logger.info(requestID + " - " + "FarmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //get shop goods
    @ApiOperation(value = "shop item view", notes = "This service view market price")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/viewshopitems/{shopid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewShopItems(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                             @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                             @PathVariable String shopid) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.viewshopitems")+ "//" + shopid;


        logger.info(requestID + " - " + "FarmerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


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
        String service_url = env.getProperty("kln_farming_project.farmer.plaguelist");

        logger.info(requestID + " - " + "FarmerController.getList - " + "request redirecting to core service...");
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
        String service_url = env.getProperty("kln_farming_project.farmer.plaguedetails")+ "//" + plagueid;


        logger.info(requestID + " - " + "FarmerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }





    //get 25 posts sort by date -> list
    //react post -add update
    //comment post - crud




    //get 25 posts sort by date -> list
    @ApiOperation(value = "farmer data list loading", notes = "This service returns  data list ")
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
        String service_url = env.getProperty("kln_farming_project.farmer.getnewposts");

        logger.info(requestID + " - " + "farmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "farmer data list loading", notes = "This service returns  data list ")
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
        String service_url = env.getProperty("kln_farming_project.farmer.gettipposts");

        logger.info(requestID + " - " + "farmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }
    @ApiOperation(value = "farmer data list loading", notes = "This service returns  data list ")
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
        String service_url = env.getProperty("kln_farming_project.farmer.getnewsposts");

        logger.info(requestID + " - " + "farmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "farmer data list loading", notes = "This service returns  data list ")
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
        String service_url = env.getProperty("kln_farming_project.farmer.getmyposts");

        logger.info(requestID + " - " + "farmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }
    //react post -add if already added then update
    @ApiOperation(value = "Customer management react", notes = "This service rect to posts")
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
        service_url = env.getProperty("kln_farming_project.farmer.reactpost");
        if(service_url!=null)
        {
            logger.info(requestID + " - " + "farmerController.signin - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, requestBody, requestID);
        }

        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //comment post - crud
    //add -postid come in body
    @ApiOperation(value = "Customer management add ", notes = "This service add ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/addpostcomment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPostComment(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                            @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole, @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.addpostcomment");

        logger.info(requestID + " - " + "farmerController.add - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, requestBody, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //edit- postid and comment id come in body


    //delete postid and comment id come in body
    @ApiOperation(value = "Customer management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    //@PostMapping(value = "/deletepostcomment/{postid}/{commentid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/deletepostcomment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePostComment(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                               @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                             //  @PathVariable String postid,@PathVariable String commentid) throws Exception {
                                               @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
      //  String service_url = env.getProperty("kln_farming_project.farmer.deletepostcomment") + "//" + postid+ "//" + commentid;
        String service_url = env.getProperty("kln_farming_project.farmer.deletepostcomment");
        logger.info(requestID + " - " + "farmerController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, requestBody, requestID);


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
        String service_url = env.getProperty("kln_farming_project.farmer.getpostreactscomments");

        logger.info(requestID + " - " + "farmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    //////////
    //crop mgt


    @ApiOperation(value = "data list loading", notes = "This service returns data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/mycroplist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMyCropList(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @RequestBody(required = false) Object requestBody,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "expectedHarvest,desc") String[] sort,
                                           @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.mycroplist");

        logger.info(requestID + " - " + "FarmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "Farmer management delete ", notes = "This service delete ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/deletemycrop/{farmerid}/{cropcode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteMyCrop(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                               @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                                 @PathVariable String farmerid,@PathVariable String cropcode) throws Exception {

        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
         String service_url = env.getProperty("kln_farming_project.farmer.deletemycrop") + "//" + farmerid+ "//" + cropcode;
        logger.info(requestID + " - " + "farmerController.delete - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "Farmer management add ", notes = "This service add ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/addmycrop", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMyCrop(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                            @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole, @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.addmycrop");

        logger.info(requestID + " - " + "farmerController.add - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, requestBody, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "Farmer management edit ", notes = "This service edit ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/editmycrop", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> editMyCrop(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                            @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole, @RequestBody Object requestBody) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.editmycrop");

        logger.info(requestID + " - " + "farmerController.edit - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp,  userrole, username, requestBody, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    ////allcrops

    @ApiOperation(value = "data list loading", notes = "This service returns data list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/allcroplist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCropList(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                           @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                           @RequestBody(required = false) Object requestBody,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "cropCode,desc") String[] sort,
                                           @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.allcroplist");

        logger.info(requestID + " - " + "FarmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "plague view", notes = "This service view plague details")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/viewcropfertilizer/{cropcode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewCropFertilizer(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,

                                              @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                              @PathVariable String cropcode) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.viewcropfertilizer")+ "//" + cropcode;


        logger.info(requestID + " - " + "FarmerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


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
        String service_url = env.getProperty("kln_farming_project.farmer.officerlist");

        logger.info(requestID + " - " + "FarmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "Farmer notification list loading", notes = "This service returns  notification list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/farmernotification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFarmerNotification(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                     @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                                     @RequestBody(required = false) Object requestBody,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                                     @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.farmernotification");

        logger.info(requestID + " - " + "FarmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "Farmer notification list loading", notes = "This service returns  notification list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/farmerproductnotification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFarmerProductNotification(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                   @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                                   @RequestBody(required = false) Object requestBody,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                                   @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.farmerproductnotification");

        logger.info(requestID + " - " + "FarmerController.getList - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username,  requestBody, page, size, sort, search, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    @ApiOperation(value = "Farmer notification list loading", notes = "This service returns  notification list ")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/farmerplaguenotification", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getFarmerPlagueNotification(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                          @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole,
                                                          @RequestBody(required = false) Object requestBody,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(defaultValue = "postedtime,desc") String[] sort,
                                                          @RequestParam(defaultValue = "false") boolean search) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.farmer.farmerplaguenotification");

        logger.info(requestID + " - " + "FarmerController.getList - " + "request redirecting to core service...");
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
        String service_url = env.getProperty("kln_farming_project.farmer.productdetails")+ "//" + productid;


        logger.info(requestID + " - " + "FarmerController.view - " + "request redirecting to core service...");
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
        String service_url = env.getProperty("kln_farming_project.farmer.getrecomandedpestlist")+ "//" + diseaseid;


        logger.info(requestID + " - " + "FarmerController.view - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }




}

package kln.project.mainservice.controller.component;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kln.project.mainservice.bean.ClientBean;
import kln.project.mainservice.bean.ResponseBean;
import kln.project.mainservice.service.CommonService;
import kln.project.mainservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("kln_farming_project/component")
public class ComponentController {


    Logger logger = LoggerFactory.getLogger(ComponentController.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private Environment env;



    @ApiOperation(value = "Access Alert Configuration", notes = "This service loads alert configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessdistrict", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessAndLoadDistrict(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                   @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
                                                   ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessdistrict");



            logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
            responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }




    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessarea", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessAndLoadArea(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                   @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessarea");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessandloadpesticide", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessAndLoadPesticide(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                               @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessandloadpesticide");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

    //owner drop down

    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessandloaddisease", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessAndLoadDisease(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                    @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessandloaddisease");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessandloadprovince", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessAndLoadProvince(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                    @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessandloadprovince");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessdiseaseandprovince", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessDiseaseAndProvince(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                   @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessdiseaseandprovince");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accesscropandprovince", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessCropAndProvince(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                      @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accesscropandprovince");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessandloadfertilizer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessandloadfertilizer(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                   @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessandloadfertilizer");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessandloadcrop", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessandloadcrop(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                     @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessandloadcrop");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessdistrictandproduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessprovinceandproduct(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                               @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessdistrictandproduct");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }



    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessdistrictandcrop", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessdistrictandcrop(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                      @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessdistrictandcrop");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }


    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessproductinshop", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessProductInShop(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                      @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessproductinshop");

        logger.info(requestID + " - " + "ComponentController.accessProductInShop - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }




    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessdistrictandspeciality", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessdistrictandSpeciality(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                   @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessdistrictandspeciality");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }




    @ApiOperation(value = "Access  Configuration", notes = "This service loads  configuration")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 200, message = "Successful retrieval", response = ResponseBean.class, responseContainer = "List")})
    @PostMapping(value = "/accessareaandspeciality", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> accessAreaandSpeciality(@ApiParam(hidden = true, required = true) @RequestHeader(value = "x-real-ip") String clientIp,
                                                         @RequestHeader(value = "username") String username,@RequestHeader(value = "userrole") String userrole
    ) throws Exception {
        ResponseBean responseBean = null;
        ResponseEntity<?> responseEntity;
        String requestID = UUID.randomUUID().toString();
        String service_url = env.getProperty("kln_farming_project.component.accessareaandspeciality");

        logger.info(requestID + " - " + "ComponentController.accessAndLoad - " + "request redirecting to core service...");
        responseBean = commonService.getResponse(service_url, clientIp, userrole, username, null, requestID);


        responseEntity = new ResponseEntity<>(responseBean, HttpStatus.OK);

        return responseEntity;
    }

}

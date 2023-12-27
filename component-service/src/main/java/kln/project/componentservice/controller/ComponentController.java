package kln.project.componentservice.controller;


import kln.project.componentservice.model.bean.*;
import kln.project.componentservice.service.Dropdownservice;
import kln.project.componentservice.util.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/component")
public class ComponentController {

    Logger logger = LoggerFactory.getLogger(ComponentController.class);
    @Autowired
    private ResponseBean responseBean;


    @Autowired
    Dropdownservice dropdownservice;




    @PostMapping(value = "/accessdistrict")
    public ResponseBean accessAndLoadDistrict(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessdistrict");
        logger.debug(requestID + " - " + "ComponentController.accessdistrict - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessdistrict - " + "initializing  configuration loading process");
        DistrictDropDownBean accessBean = dropdownservice.accessAndLoadDistrict();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessdistrict - " + "configuration loading completed");

        return responseBean;
    }



    @PostMapping(value = "/accessarea")
    public ResponseBean accessAndLoadArea(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessarea");
        logger.debug(requestID + " - " + "ComponentController.accessarea - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessarea - " + "initializing  configuration loading process");
        AreaDropDownBean accessBean = dropdownservice.accessAndLoadArea();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessarea - " + "configuration loading completed");

        return responseBean;
    }



    @PostMapping(value = "/accessandloadpesticide")
    public ResponseBean accessAndLoadPesticide(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessandloadpesticide");
        logger.debug(requestID + " - " + "ComponentController.accessandloadpesticide - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessandloadpesticide - " + "initializing  configuration loading process");
        PesticideDropDownBean accessBean = dropdownservice.accessAndLoadPesticide();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessandloadpesticide - " + "configuration loading completed");

        return responseBean;
    }


    @PostMapping(value = "/accessandloaddisease")
    public ResponseBean accessAndLoadDisease(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessandloaddisease");
        logger.debug(requestID + " - " + "ComponentController.accessandloaddisease - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessandloaddisease - " + "initializing  configuration loading process");
        DiseaseDropDownBean accessBean = dropdownservice.accessAndLoadDisease();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessandloaddisease - " + "configuration loading completed");

        return responseBean;
    }


    @PostMapping(value = "/accessandloadprovince")
    public ResponseBean accessAndLoadProvince(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessandloadprovince");
        logger.debug(requestID + " - " + "ComponentController.accessandloadprovince - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessandloadprovince - " + "initializing  configuration loading process");
        ProvinceDropDownBean accessBean = dropdownservice.accessAndLoadProvince();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessandloadprovince - " + "configuration loading completed");

        return responseBean;
    }

    @PostMapping(value = "/accessdiseaseandprovince")
    public ResponseBean accessDiseaseAndProvince(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessdiseaseandprovince");
        logger.debug(requestID + " - " + "ComponentController.accessdiseaseandprovince - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessdiseaseandprovince - " + "initializing  configuration loading process");
        ReportDropDownBean accessBean = dropdownservice.accessDiseaseAndProvince();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessdiseaseandprovince - " + "configuration loading completed");

        return responseBean;
    }


    @PostMapping(value = "/accesscropandprovince")
    public ResponseBean accessCropAndProvince(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accesscropandprovince");
        logger.debug(requestID + " - " + "ComponentController.accesscropandprovince - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accesscropandprovince - " + "initializing  configuration loading process");
        ReportDropDownBean accessBean = dropdownservice.accessCropAndProvince();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accesscropandprovince - " + "configuration loading completed");

        return responseBean;
    }


    @PostMapping(value = "/accessandloadfertilizer")
    public ResponseBean accessandloadfertilizer(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessandloadfertilizer");
        logger.debug(requestID + " - " + "ComponentController.accessandloadfertilizer - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessandloadfertilizer - " + "initializing  configuration loading process");
        PesticideDropDownBean accessBean = dropdownservice.accessandloadfertilizer();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessandloadfertilizer - " + "configuration loading completed");

        return responseBean;
    }




    @PostMapping(value = "/accessandloadcrop")
    public ResponseBean accessandloadcrop(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessandloadcrop");
        logger.debug(requestID + " - " + "ComponentController.accessandloadcrop - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessandloadcrop - " + "initializing  configuration loading process");
        CropDropDownBean accessBean = dropdownservice.accessandloadcrop();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessandloadcrop - " + "configuration loading completed");

        return responseBean;
    }

    @PostMapping(value = "/accessdistrictandproduct")
    public ResponseBean accessProvinceAndProduct(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessdistrictandproduct");
        logger.debug(requestID + " - " + "ComponentController.accessdistrictandproduct - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessdistrictandproduct - " + "initializing  configuration loading process");
        ShopParaDropDownBean accessBean = dropdownservice.accessprovinceandproduct();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessdistrictandproduct - " + "configuration loading completed");

        return responseBean;
    }

    @PostMapping(value = "/accessdistrictandcrop")
    public ResponseBean accessdistrictandcrop(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessdistrictandcrop");
        logger.debug(requestID + " - " + "ComponentController.accessdistrictandcrop - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessdistrictandcrop - " + "initializing  configuration loading process");
        ShopParaDropDownBean accessBean = dropdownservice.accessdistrictandcrop();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessdistrictandcrop - " + "configuration loading completed");

        return responseBean;
    }




    @PostMapping(value = "/accessproductinshop")
    public ResponseBean accessProductinShop(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessproductinshop");
        logger.debug(requestID + " - " + "ComponentController.accessproductinshop - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessproductinshop - " + "initializing  configuration loading process");
        ShopParaDropDownBean accessBean = dropdownservice.accessProductinShop(requestBean);
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessproductinshop - " + "configuration loading completed");

        return responseBean;
    }




    @PostMapping(value = "/accessdistrictandspeciality")
    public ResponseBean accessdistrictandSpeciality(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessdistrictandspeciality");
        logger.debug(requestID + " - " + "ComponentController.accessdistrictandspeciality - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessdistrictandspeciality - " + "initializing  configuration loading process");
        DistrictSpecialtyBean accessBean = dropdownservice.accessdistrictandSpeciality();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessdistrictandspeciality - " + "configuration loading completed");

        return responseBean;
    }



    @PostMapping(value = "/accessareaandspeciality")
    public ResponseBean accessAreaandspeciality(@RequestBody RequestBean requestBean, @RequestHeader String requestID) throws Exception {
        logger.info(requestID + " - " + "ComponentController.accessareaandspeciality");
        logger.debug(requestID + " - " + "ComponentController.accessareaandspeciality - " + "clientip    - " + requestBean.getClient_ip());

        logger.info(requestID + " - " + "ComponentController.accessareaandspeciality - " + "initializing  configuration loading process");
        DistrictSpecialtyBean accessBean = dropdownservice.accessAreaandSpeciality();
        responseBean.setResponseCode(ResponseCode.RSP_SUCCESS);
        responseBean.setResponseMsg(null);
        responseBean.setContent(accessBean);

        //set the audit trace values

        logger.info(requestID + " - " + "ComponentController.accessareaandspeciality - " + "configuration loading completed");

        return responseBean;
    }
}

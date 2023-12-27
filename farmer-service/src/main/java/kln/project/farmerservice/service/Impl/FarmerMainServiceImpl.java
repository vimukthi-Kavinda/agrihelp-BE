package kln.project.farmerservice.service.Impl;

import kln.project.farmerservice.model.bean.DataTableBean;
import kln.project.farmerservice.model.bean.FarmerDetailDataBean;
import kln.project.farmerservice.model.bean.RequestBean;
import kln.project.farmerservice.model.bean.ResponseBean;
import kln.project.farmerservice.model.entity.Area;
import kln.project.farmerservice.model.entity.Farmer;
import kln.project.farmerservice.model.entity.TempFarmer;
import kln.project.farmerservice.repository.AreaRepository;
import kln.project.farmerservice.repository.FarmerRepository;
import kln.project.farmerservice.repository.TempFarmerRepository;
import kln.project.farmerservice.service.FarmerMainService;
import kln.project.farmerservice.util.EncryptingModule;
import kln.project.farmerservice.util.MessageVarList;
import kln.project.farmerservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class FarmerMainServiceImpl implements FarmerMainService {
    
    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    FarmerRepository farmerRepository;
    
    @Autowired
    TempFarmerRepository tempfarmerRepository;
            
    @Autowired
    AreaRepository areaRepository;
    @Override
    public ResponseBean login(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        FarmerDetailDataBean dataBean = modelMapper.map(requestBean.getRequestBody(), FarmerDetailDataBean.class);

        FarmerDetailDataBean ob = null;


        if (dataBean.getUsername() == null || dataBean.getUsername().equals("")) {
            ob = new FarmerDetailDataBean();
            message = MessageVarList.USER_NAME_NULL;
            ob.setVerified(false);
        } else if (dataBean.getPassword() == null || dataBean.getPassword().equals("")) {
            ob = new FarmerDetailDataBean();
            message = MessageVarList.PASSWORD_NULL;
            ob.setVerified(false);
        } else {
            Farmer farmer = farmerRepository.findByUsername(dataBean.getUsername());
            if (farmer==null) {
                ob = new FarmerDetailDataBean();
                message = MessageVarList.USER_NAME_NOT_FOUND;
                ob.setVerified(false);
            } else {
                Farmer o = farmer;
                if (dataBean.getPassword().equals(EncryptingModule.decrypt(o.getPassword()))) {

                    message = MessageVarList.SUCCESS_LOGIN;

                    ob = new FarmerDetailDataBean();
                    ob.setVerified(true);
                    ob.setFarmerId(o.getFarmerId());
                    ob.setUsername(o.getUsername());
                    ob.setNic(o.getNic());
                    ob.setName(o.getName());
                    ob.setAddress(o.getAddress());
                    ob.setTelno(o.getTelno());
                    ob.setEmail(o.getEmail());

                    ob.setArea(o.getArea().getAreacode());
                    ob.setAreaDesc(o.getArea().getAreaname());
                    ob.setUserrole("farmer");

                    responsCode = ResponseCode.RSP_SUCCESS;
                } else {
                    ob=new FarmerDetailDataBean();
                    message = MessageVarList.FAIL_LOGIN;
                    ob.setVerified(false);
                }


            }
        }

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(ob);

        return responseBean;


    }

    @Override
    public ResponseBean signin(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        FarmerDetailDataBean ob = modelMapper.map(requestBean.getRequestBody(), FarmerDetailDataBean.class);

        String errMsg = null;
        errMsg = validateFarmerBean(ob);

        Farmer t=farmerRepository.findByUsername(ob.getUsername());
        Optional<Farmer> t1=farmerRepository.findById(ob.getFarmerId());
        Optional<TempFarmer> t2=tempfarmerRepository.findById(ob.getFarmerId());
        if (errMsg != null) {
            message=errMsg;
        }
        else if(t1.isPresent()){
            message="Already registered";
        }else if(t2.isPresent()){
            message="Already requested to register";
        }


        else if(t!=null){
            message="User name taken";
        }

        else {

            TempFarmer o=new TempFarmer();
            o.setFarmerId(ob.getFarmerId());
            o.setName(ob.getName());
            o.setNic(EncryptingModule.encrypt(ob.getNic()));
            o.setEmail(EncryptingModule.encrypt(ob.getEmail()));
            o.setAddress(EncryptingModule.encrypt(ob.getAddress()));
            o.setTelno(EncryptingModule.encrypt(ob.getTelno()));

            o.setUsername(ob.getUsername());
            o.setPassword(EncryptingModule.encrypt(ob.getPassword()));

            Optional<Area> a=areaRepository.findById(ob.getArea());
            if(a.isPresent())
                o.setArea(a.get());

            tempfarmerRepository.saveAndFlush(o);
            message=MessageVarList.SUCCESS_ADD;
            responsCode=ResponseCode.RSP_SUCCESS;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;

    }

    @Override
    public ResponseBean deleteAccount(String myid, RequestBean requestBean, ResponseBean responseBean) {
return null;
    }

    @Override
    public DataTableBean getinfo(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();

        FarmerDetailDataBean sb=new FarmerDetailDataBean();

        Farmer shp=farmerRepository.findByUsername(requestBean.getUsername());
        if(shp!=null){

            sb.setFarmerId(shp.getFarmerId());
            sb.setName(shp.getName());
            sb.setNic(EncryptingModule.decrypt(shp.getNic()));
            sb.setAddress(EncryptingModule.decrypt(shp.getAddress()));
            sb.setTelno(EncryptingModule.decrypt(shp.getTelno()));
            sb.setEmail(EncryptingModule.decrypt(shp.getEmail()));



            postDataBeans.add(sb);
        }

        dataTableBean.setList(postDataBeans);

        return dataTableBean;


    }

    @Override
    public ResponseBean updateInfo(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        FarmerDetailDataBean ob = modelMapper.map(requestBean.getRequestBody(), FarmerDetailDataBean.class);

        Farmer cst=farmerRepository.findByUsername(requestBean.getUsername());
        if(cst!=null){
            if(ob.getPassword()!=null&&!ob.getPassword().equals("")){
                String k=EncryptingModule.decrypt(cst.getPassword());
                if(ob.getOldPassword()!=null&&!ob.getOldPassword().equals("")&&k.equals(ob.getOldPassword()))
                {cst.setPassword(EncryptingModule.encrypt(ob.getPassword()));}
                else{
                    responseBean.setResponseCode(responsCode);
                    responseBean.setResponseMsg(MessageVarList.PASSWORD_MISMATCH);
                    responseBean.setContent(null);

                    return responseBean;
                }
            }
            if(ob.getTelno()!=null&&!ob.getTelno().equals("")){
                cst.setTelno(EncryptingModule.encrypt(ob.getTelno()));
            }
            if(ob.getAddress()!=null&&!ob.getAddress().equals("")){
                cst.setAddress(EncryptingModule.encrypt(ob.getAddress()));
            }
            if(ob.getEmail()!=null&&!ob.getEmail().equals("")){
                cst.setEmail(EncryptingModule.encrypt(ob.getEmail()));
            }
            farmerRepository.saveAndFlush(cst);
            responsCode=ResponseCode.RSP_SUCCESS;
            message=MessageVarList.SUCCESS_UPDATE;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;

    }

    private String validateFarmerBean(FarmerDetailDataBean ob) {
        String errMsg = null;

        if(ob.getFarmerId()==null||ob.getFarmerId().equals("")){
            errMsg=MessageVarList.FARMER_ID_NULL;
        }
        if(ob.getUsername()==null||ob.getUsername().equals("")){
            errMsg=MessageVarList.USER_NAME_NULL;
        }
        if(ob.getPassword()==null||ob.getPassword().equals("")){
            errMsg=MessageVarList.PASSWORD_NULL;
        }

        if(ob.getNic()==null ||ob.getNic().equals("")){
            errMsg=MessageVarList.FARMER_NIC_NULL;
        }
        if(ob.getName()==null ||ob.getName().equals("")){
            errMsg=MessageVarList.FARMER_NAME_NULL;
        }
        if(ob.getAddress()==null ||ob.getAddress().equals("")){
            errMsg=MessageVarList.FARMER_ADDRESS_NULL;
        }
        if(ob.getTelno()==null ||ob.getTelno().equals("")){
            errMsg=MessageVarList.FARMER_TELNO_NULL;
        }
        if(ob.getEmail()==null ||ob.getEmail().equals("")){
            errMsg=MessageVarList.FARMER_EMAIL_NULL;
        }
        if(ob.getArea()==null ||ob.getArea().equals("")){
            errMsg=MessageVarList.FARMER_ASSIGNED_AREA_NULL;
        }
        return errMsg;
    }
}

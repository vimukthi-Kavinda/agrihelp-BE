package kln.project.shopservice.service.impl;

import kln.project.shopservice.model.bean.*;
import kln.project.shopservice.model.entity.District;
import kln.project.shopservice.model.entity.Shop;
import kln.project.shopservice.model.entity.ShopOwner;
import kln.project.shopservice.repository.DistrictRepository;
import kln.project.shopservice.repository.ShopOwnerRepository;
import kln.project.shopservice.repository.ShopRepository;
import kln.project.shopservice.service.ShopMainService;
import kln.project.shopservice.util.MessageVarList;
import kln.project.shopservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ShopMainServiceImpl implements ShopMainService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ShopOwnerRepository shopOwnerRepository;

    @Override
    public ResponseBean login(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        ShopDetailDataBean marketDetailDataBean = modelMapper.map(requestBean.getRequestBody(), ShopDetailDataBean.class);

        ShopDetailDataBean ob ;
        if (marketDetailDataBean.getShopregno() == null || marketDetailDataBean.getShopregno().equals("")) {
            message = MessageVarList.ID_NULL;
            ob = new ShopDetailDataBean();
            ob.setVerified(false);
        } else if (marketDetailDataBean.getPassword() == null || marketDetailDataBean.getPassword().equals("")) {
            message = MessageVarList.PASSWORD_NULL;
            ob = new ShopDetailDataBean();
            ob.setVerified(false);
        } else {
            Optional<Shop> officer = shopRepository.findById(marketDetailDataBean.getShopregno());
            if (!officer.isPresent()) {
                message = "Shop "+MessageVarList.NOT_FOUND;
                ob = new ShopDetailDataBean();
                ob.setVerified(false);
            } else {
                Shop o = officer.get();
                if (marketDetailDataBean.getPassword().equals(o.getPassword())) {

                    message = MessageVarList.SUCCESS_LOGIN;

                    ob = new ShopDetailDataBean();
                    ob.setVerified(true);
                    ob.setShopregno(o.getShopRegNo());
                    ob.setShopname(o.getShopname());
                    ob.setShopaddress(o.getAddress());
                    ob.setDistrict(o.getDistrict().getDistrictId());
                    ob.setShopcontact(o.getShopContactNo());
                    ob.setOwnernic(o.getOwnerNic().getOwnerNic());
                    ob.setUserrole("shop");

                    responsCode = ResponseCode.RSP_SUCCESS;
                } else {
                    message = MessageVarList.FAIL_LOGIN;
                    ob = new ShopDetailDataBean();
                    ob.setVerified(false);
                }


            }
        }

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(ob);

        return responseBean;


    }



    @Autowired
    DistrictRepository districtRepository;

    @Override
    public ResponseBean signin(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        ShopDetailDataBean ob = modelMapper.map(requestBean.getRequestBody(), ShopDetailDataBean.class);

        String errMsg = null;
        errMsg = validateShopBean(ob);

        Optional<ShopOwner>ow=shopOwnerRepository.findById(ob.getOwnernic());
        Optional<Shop>t1=shopRepository.findById(ob.getShopregno());
        if (errMsg != null) {
            message=errMsg;
        } else if (t1.isPresent()) {
            message="already registered";
        } else if(!ow.isPresent()){
            message="Owner is not registered";

        }

        else {


            Shop o=new Shop();
            o.setShopRegNo(ob.getShopregno());
            o.setShopname(ob.getShopname());

            Optional<District>a=districtRepository.findById(ob.getDistrict());
            if(a.isPresent())
                o.setDistrict(a.get());
            //o.setMarketLocationCoord(ob.getCoord());
            o.setAddress(ob.getShopaddress());
            o.setOwnerNic(ow.get());
            o.setShopContactNo(ob.getShopcontact());
            o.setPassword(ob.getPassword());



            shopRepository.saveAndFlush(o);
            message=MessageVarList.SUCCESS_ADD;
            responsCode=ResponseCode.RSP_SUCCESS;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;

    }



    @Override
    public ResponseBean signinowner(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        ShopOwnerDataBean ob = modelMapper.map(requestBean.getRequestBody(), ShopOwnerDataBean.class);

        String errMsg = null;
        errMsg = validateOwnerBean(ob);


        Optional<ShopOwner>t1=shopOwnerRepository.findById(ob.getOwnernic());
        if (errMsg != null) {
            message=errMsg;
        } else if (t1.isPresent()) {
            message="already registered";
        }


        else {


            ShopOwner o=new ShopOwner();
            o.setOwnerNic(ob.getOwnernic());
            o.setOwnerName(ob.getOwnername());
            o.setOwnerContactNo(ob.getOwnercontact());
            o.setEmail(ob.getOwnermail());


            shopOwnerRepository.saveAndFlush(o);
            message=MessageVarList.SUCCESS_ADD;
            responsCode=ResponseCode.RSP_SUCCESS;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;

    }

    @Override
    public ResponseBean deleteowneraccount(String myid, RequestBean requestBean, ResponseBean responseBean) {
//no deletion..
        return null;
    }

    @Override
    public DataTableBean getownerinfo(RequestBean requestBean) {
return null;
    }

    @Override
    public ResponseBean updateownerinfo(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        ShopOwnerDataBean ob = modelMapper.map(requestBean.getRequestBody(), ShopOwnerDataBean.class);
return null;
    }

    @Override
    public ResponseBean deletestore(String myid, RequestBean requestBean, ResponseBean responseBean) {

        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        Optional<Shop>sh=shopRepository.findById(myid);
        if(sh.isPresent()){
            shopRepository.delete(sh.get());
            message=MessageVarList.SUCCESS_DELETE;
            responsCode=ResponseCode.RSP_SUCCESS;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;

    }

    @Override
    public DataTableBean getstoreinfo(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();

        ShopDetailDataBean sb=new ShopDetailDataBean();

        Optional<Shop>sh=shopRepository.findById(requestBean.getUsername());
        if(sh.isPresent()){
        Shop shp=sh.get();
        sb.setShopregno(shp.getShopRegNo());
        sb.setShopaddress(shp.getAddress());
        sb.setShopname(shp.getShopname());
        sb.setShopcontact(shp.getShopContactNo());

        ShopOwner so=shp.getOwnerNic();
        sb.setOwnername(so.getOwnerName());
        sb.setOwnercontact(so.getOwnerContactNo());
        sb.setOwnernic(so.getOwnerNic());
        sb.setOwnermail(so.getEmail());

postDataBeans.add(sb);
        }

        dataTableBean.setList(postDataBeans);

        return dataTableBean;


    }

    @Override
    public ResponseBean updatestoreinfo(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        ShopDetailDataBean ob = modelMapper.map(requestBean.getRequestBody(), ShopDetailDataBean.class);

        Optional<Shop>sp=shopRepository.findById(requestBean.getUsername());
        if(sp.isPresent()){
            Shop cst=sp.get();
            ShopOwner so=cst.getOwnerNic();
            if(ob.getPassword()!=null&&!ob.getPassword().equals("")){
                if(ob.getOldPassword()!=null&&!ob.getOldPassword().equals("")&&cst.getPassword().equals(ob.getOldPassword()))
                {cst.setPassword(ob.getPassword());}
                else{
                    responseBean.setResponseCode(responsCode);
                    responseBean.setResponseMsg(MessageVarList.PASSWORD_MISMATCH);
                    responseBean.setContent(null);

                    return responseBean;
                }
            }
            if(ob.getShopcontact()!=null&&!ob.getShopcontact().equals("")){
                cst.setShopContactNo(ob.getShopcontact());
            }
            if(ob.getOwnercontact()!=null&&!ob.getOwnercontact().equals("")){
                so.setOwnerContactNo(ob.getOwnercontact());
            }
            if(ob.getOwnermail()!=null&&!ob.getOwnermail().equals("")){
                so.setEmail(ob.getOwnermail());
            }
            shopRepository.saveAndFlush(cst);
            shopOwnerRepository.saveAndFlush(so);
            responsCode=ResponseCode.RSP_SUCCESS;
            message=MessageVarList.SUCCESS_UPDATE;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }

    private String validateOwnerBean(ShopOwnerDataBean ob) {
        String errMsg = null;

        if(ob.getOwnernic()==null||ob.getOwnernic().equals("")){
            errMsg=MessageVarList.ID_NULL;
        }
        if(ob.getOwnername()==null||ob.getOwnername().equals("")){
            errMsg=MessageVarList.NAME_NULL;
        }
        if(ob.getOwnermail()==null ||ob.getOwnermail().equals("")){
            errMsg=MessageVarList.MAIL_ADDRESS_NULL;
        }
        if(ob.getOwnercontact()==null ||ob.getOwnercontact().equals("")){
            errMsg=MessageVarList.CONTACT_NO_NULL;
        }
        return errMsg;
    }


    private String validateShopBean(ShopDetailDataBean ob) {
        String errMsg = null;

        if(ob.getShopregno()==null||ob.getShopregno().equals("")){
            errMsg=MessageVarList.ID_NULL;
        }
        if(ob.getShopname()==null||ob.getShopname().equals("")){
            errMsg=MessageVarList.NAME_NULL;
        }
        if(ob.getPassword()==null||ob.getPassword().equals("")){
            errMsg=MessageVarList.PASSWORD_NULL;
        }

        if(ob.getDistrict()==null ||ob.getDistrict().equals("")){
            errMsg=MessageVarList.AREA_NULL;
        }
        if(ob.getShopaddress()==null ||ob.getShopaddress().equals("")){
            errMsg=MessageVarList.ADDRESS_NULL;
        }
        if(ob.getOwnernic()==null ||ob.getOwnernic().equals("")){
            errMsg=MessageVarList.OWNER_ID_NULL;
        }
        if(ob.getShopcontact()==null ||ob.getShopcontact().equals("")){
            errMsg=MessageVarList.CONTACT_NO_NULL;
        }
        return errMsg;
    }
}

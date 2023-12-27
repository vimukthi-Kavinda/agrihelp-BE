package kln.project.marketservice.service.impl;



import kln.project.marketservice.model.bean.DataTableBean;
import kln.project.marketservice.model.bean.MarketDetailDataBean;
import kln.project.marketservice.model.bean.RequestBean;
import kln.project.marketservice.model.bean.ResponseBean;
import kln.project.marketservice.model.entity.Area;
import kln.project.marketservice.model.entity.Market;
import kln.project.marketservice.repository.AreaRepository;
import kln.project.marketservice.repository.MarketRepository;
import kln.project.marketservice.service.MarketMainService;
import kln.project.marketservice.util.MessageVarList;
import kln.project.marketservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MarketMainServiceImpl implements MarketMainService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    MarketRepository  marketRepository;


    @Override
    public ResponseBean login(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        MarketDetailDataBean marketDetailDataBean = modelMapper.map(requestBean.getRequestBody(), MarketDetailDataBean.class);

        MarketDetailDataBean ob ;


        if (marketDetailDataBean.getMarketId() == null || marketDetailDataBean.getMarketId().equals("")) {
            message = MessageVarList.ID_NULL;
            ob = new MarketDetailDataBean();
            ob.setVerified(false);
        } else if (marketDetailDataBean.getPassword() == null || marketDetailDataBean.getPassword().equals("")) {
            message = MessageVarList.PASSWORD_NULL;
            ob = new MarketDetailDataBean();
            ob.setVerified(false);
        } else {
            Optional<Market> officer = marketRepository.findById(marketDetailDataBean.getMarketId());
            if (!officer.isPresent()) {
                message = "Market "+MessageVarList.NOT_FOUND;
                ob = new MarketDetailDataBean();
                ob.setVerified(false);
            } else {
                Market o = officer.get();
                if (marketDetailDataBean.getPassword().equals(o.getPassword())) {

                    message = MessageVarList.SUCCESS_LOGIN;

                    ob = new MarketDetailDataBean();
                    ob.setVerified(true);
                    ob.setMarketId(o.getMarketId());
                    ob.setMarketName(o.getMarketname());
                    ob.setArea(o.getArea().getAreaCode());
                    ob.setAreaDesc(o.getArea().getAreaName());
                    ob.setAddress(o.getAddress());
                    ob.setUserrole("market");

                    responsCode = ResponseCode.RSP_SUCCESS;
                } else {
                    message = MessageVarList.FAIL_LOGIN;
                    ob = new MarketDetailDataBean();
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
    AreaRepository areaRepository;

    @Override
    public ResponseBean signin(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        MarketDetailDataBean ob = modelMapper.map(requestBean.getRequestBody(), MarketDetailDataBean.class);

        String errMsg = null;
        errMsg = validateMarketBean(ob);

        Market t=marketRepository.findByMarketname(ob.getMarketName());
        Optional<Market>t1=marketRepository.findById(ob.getMarketId());
        if (errMsg != null) {
            message=errMsg;
        } else if (t1.isPresent()) {
            message="already registered";
        } else if(t!=null){
            message="User name taken";
        }


        else {


            Market o=new Market();
            o.setMarketId(ob.getMarketId());
            o.setMarketname(ob.getMarketName());

            Optional<Area>a=areaRepository.findById(ob.getArea());
            if(a.isPresent())
            o.setArea(a.get());
            //o.setMarketLocationCoord(ob.getCoord());
            o.setAddress(ob.getAddress());
            o.setContactno(ob.getContactno());
            o.setMailaddress(ob.getMailaddress());
            o.setPassword(ob.getPassword());


            marketRepository.saveAndFlush(o);
            message=MessageVarList.SUCCESS_ADD;
            responsCode=ResponseCode.RSP_SUCCESS;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;

    }

    @Override
    public DataTableBean getinfo(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();

        MarketDetailDataBean sb=new MarketDetailDataBean();

        Optional<Market>sh=marketRepository.findById(requestBean.getUsername());
        if(sh.isPresent()){
            Market shp=sh.get();
            sb.setMarketId(shp.getMarketId());
            sb.setAddress(shp.getAddress());
            sb.setMarketName(shp.getMarketname());
            sb.setContactno(shp.getContactno());
            sb.setMailaddress(shp.getMailaddress());

            postDataBeans.add(sb);
        }

        dataTableBean.setList(postDataBeans);

        return dataTableBean;


    }

    @Override
    public ResponseBean updateInfo(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        MarketDetailDataBean ob = modelMapper.map(requestBean.getRequestBody(), MarketDetailDataBean.class);
        Optional<Market> cs=marketRepository.findById(requestBean.getUsername());
        if(cs.isPresent()){
            Market cst=cs.get();
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
            if(ob.getContactno()!=null&&!ob.getContactno().equals("")){
                cst.setContactno(ob.getContactno());
            }
            if(ob.getMailaddress()!=null&&!ob.getMailaddress().equals("")){
                cst.setMailaddress(ob.getMailaddress());
            }
            marketRepository.saveAndFlush(cst);
            responsCode=ResponseCode.RSP_SUCCESS;
            message=MessageVarList.SUCCESS_UPDATE;
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

    private String validateMarketBean(MarketDetailDataBean ob) {
        String errMsg = null;

        if(ob.getMarketId()==null||ob.getMarketId().equals("")){
            errMsg=MessageVarList.ID_NULL;
        }
        if(ob.getMarketName()==null||ob.getMarketName().equals("")){
            errMsg=MessageVarList.NAME_NULL;
        }
        if(ob.getPassword()==null||ob.getPassword().equals("")){
            errMsg=MessageVarList.PASSWORD_NULL;
        }

        if(ob.getArea()==null ||ob.getArea().equals("")){
            errMsg=MessageVarList.AREA_NULL;
        }
        if(ob.getAddress()==null ||ob.getAddress().equals("")){
            errMsg=MessageVarList.ADDRESS_NULL;
        }
        if(ob.getMailaddress()==null ||ob.getMailaddress().equals("")){
            errMsg=MessageVarList.MAIL_ADDRESS_NULL;
        }
        if(ob.getContactno()==null ||ob.getContactno().equals("")){
            errMsg=MessageVarList.CONTACT_NO_NULL;
        }
        return errMsg;
    }
}

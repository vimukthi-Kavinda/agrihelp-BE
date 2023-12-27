package kln.project.officerservice.service.Impl;

import kln.project.officerservice.model.bean.DataTableBean;
import kln.project.officerservice.model.bean.OfficerDetailDataBean;
import kln.project.officerservice.model.bean.RequestBean;
import kln.project.officerservice.model.bean.ResponseBean;
import kln.project.officerservice.model.entity.Area;
import kln.project.officerservice.model.entity.Officer;
import kln.project.officerservice.model.entity.Officerspecialty;
import kln.project.officerservice.model.entity.TempOfficer;
import kln.project.officerservice.repository.AreaRepository;
import kln.project.officerservice.repository.OfficerRepository;
import kln.project.officerservice.repository.OfficerspecialtyRepository;
import kln.project.officerservice.repository.TempOfficerRepository;
import kln.project.officerservice.service.OfficerMainService;
import kln.project.officerservice.util.EncryptingModule;
import kln.project.officerservice.util.MessageVarList;
import kln.project.officerservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class OfficerMainServiceImpl implements OfficerMainService {


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    TempOfficerRepository tempofficerRepository;

    @Autowired
    OfficerRepository officerRepository;

    @Autowired
    AreaRepository areaRepository;


    @Override
    public ResponseBean login(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        OfficerDetailDataBean officerDataBean = modelMapper.map(requestBean.getRequestBody(), OfficerDetailDataBean.class);

        OfficerDetailDataBean ob ;


        if (officerDataBean.getUsername() == null || officerDataBean.getUsername().equals("")) {
            message = MessageVarList.USER_NAME_NULL;
            ob = new OfficerDetailDataBean();
            ob.setVerified(false);
        } else if (officerDataBean.getPassword() == null || officerDataBean.getPassword().equals("")) {
            message = MessageVarList.PASSWORD_NULL;
            ob = new OfficerDetailDataBean();
            ob.setVerified(false);
        } else {
            Officer officer = officerRepository.findByUsername(officerDataBean.getUsername());
            if (officer==null) {
                message = MessageVarList.USER_NAME_NOT_FOUND;
                ob = new OfficerDetailDataBean();
                ob.setVerified(false);
            } else {
                Officer o = officer;
                if (officerDataBean.getPassword().equals(EncryptingModule.decrypt(o.getPassword()))) {

                    message = MessageVarList.SUCCESS_LOGIN;

                    ob = new OfficerDetailDataBean();
                    ob.setVerified(true);
                    ob.setOfficerId(o.getOfficerId());
                    ob.setUsername(o.getUsername());
                    ob.setNic(o.getNic());
                    ob.setOfficerName(o.getOfficerName());
                    ob.setAddress(o.getAddress());
                    ob.setTelno(o.getTelno());
                    ob.setEmail(o.getEmail());

                    ob.setAssignedArea(o.getAssignedArea().getAreaCode());
                    ob.setAssignedAreaDesc(o.getAssignedArea().getAreaName());
                    ob.setUserrole("officer");

                    responsCode = ResponseCode.RSP_SUCCESS;
                } else {
                    message = MessageVarList.FAIL_LOGIN;
                    ob = new OfficerDetailDataBean();
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
    OfficerspecialtyRepository officerspecialtyRepository;

    @Override
    public ResponseBean signin(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        OfficerDetailDataBean ob = modelMapper.map(requestBean.getRequestBody(), OfficerDetailDataBean.class);

        String errMsg = null;
        errMsg = validateOfficerBean(ob);

        Officer t=officerRepository.findByUsername(ob.getUsername());
        Optional<Officer>t1=officerRepository.findById(ob.getOfficerId());
        TempOfficer t2=tempofficerRepository.findByUsername(ob.getUsername());
        if (errMsg != null) {
            message=errMsg;
        } else if (t1.isPresent()) {
            message="already registered";
        } else if(t!=null){
            message="User name taken";
        }
         else if (t2!=null){
            message="User already requested";
        }

        else {

            TempOfficer o=new TempOfficer();
            o.setOfficerId(ob.getOfficerId());
            o.setOfficerName(ob.getOfficerName());
            o.setNic(EncryptingModule.encrypt(ob.getNic()));
            o.setEmail(EncryptingModule.encrypt(ob.getEmail()));
            o.setAddress(EncryptingModule.encrypt(ob.getAddress()));
            o.setTelno(EncryptingModule.encrypt(ob.getTelno()));
            Optional<Officerspecialty>os=officerspecialtyRepository.findById(ob.getSpecialty());
            if(os.isPresent())o.setSpecialty(os.get());

            o.setUsername(ob.getUsername());
            o.setPassword(EncryptingModule.encrypt(ob.getPassword()));

            Optional<Area>a=areaRepository.findById(ob.getAssignedArea());
            if(a.isPresent())
            o.setAssignedArea(a.get());

            tempofficerRepository.saveAndFlush(o);
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

        OfficerDetailDataBean sb=new OfficerDetailDataBean();

        Officer shp=officerRepository.findByUsername(requestBean.getUsername());
        if(shp!=null){

            sb.setOfficerId(shp.getOfficerId());
            sb.setOfficerName(shp.getOfficerName());
            sb.setNic(EncryptingModule.decrypt(shp.getNic()));
            sb.setAddress(EncryptingModule.decrypt(shp.getAddress()));
            sb.setTelno(EncryptingModule.decrypt(shp.getTelno()));
            sb.setEmail(EncryptingModule.decrypt(shp.getEmail()));
            sb.setSpecialty(shp.getSpecialty().getSpecialtydesc());


            postDataBeans.add(sb);
        }

        dataTableBean.setList(postDataBeans);

        return dataTableBean;


    }

    @Override
    public ResponseBean updateInfo(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        OfficerDetailDataBean ob = modelMapper.map(requestBean.getRequestBody(), OfficerDetailDataBean.class);
        Officer cst=officerRepository.findByUsername(requestBean.getUsername());
        if(cst!=null){
            if(ob.getPassword()!=null&&!ob.getPassword().equals("")){
               String k= EncryptingModule.decrypt(cst.getPassword());
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
            officerRepository.saveAndFlush(cst);
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

    private String validateOfficerBean(OfficerDetailDataBean ob) {
        String errMsg = null;

        if(ob.getOfficerId()==null||ob.getOfficerId().equals("")){
            errMsg=MessageVarList.OFFICER_ID_NULL;
        }
        if(ob.getUsername()==null||ob.getUsername().equals("")){
            errMsg=MessageVarList.USER_NAME_NULL;
        }
        if(ob.getPassword()==null||ob.getPassword().equals("")){
            errMsg=MessageVarList.PASSWORD_NULL;
        }

        if(ob.getNic()==null ||ob.getNic().equals("")){
            errMsg=MessageVarList.OFFICER_NIC_NULL;
        }
        if(ob.getOfficerName()==null ||ob.getOfficerName().equals("")){
            errMsg=MessageVarList.OFFICER_NAME_NULL;
        }
        if(ob.getAddress()==null ||ob.getAddress().equals("")){
            errMsg=MessageVarList.OFFICER_ADDRESS_NULL;
        }
        if(ob.getTelno()==null ||ob.getTelno().equals("")){
            errMsg=MessageVarList.OFFICER_TELNO_NULL;
        }
        if(ob.getEmail()==null ||ob.getEmail().equals("")){
            errMsg=MessageVarList.OFFICER_EMAIL_NULL;
        }
        if(ob.getAssignedArea()==null ||ob.getAssignedArea().equals("")){
            errMsg=MessageVarList.OFFICER_ASSIGNED_AREA_NULL;
        }
        return errMsg;
    }
}

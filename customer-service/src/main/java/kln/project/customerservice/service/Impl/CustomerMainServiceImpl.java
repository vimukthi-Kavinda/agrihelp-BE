package kln.project.customerservice.service.Impl;

import kln.project.customerservice.model.bean.CustomerDetailDataBean;
import kln.project.customerservice.model.bean.DataTableBean;
import kln.project.customerservice.model.bean.RequestBean;
import kln.project.customerservice.model.bean.ResponseBean;
import kln.project.customerservice.model.entity.Customer;
import kln.project.customerservice.model.entity.District;
import kln.project.customerservice.model.entity.TempCustomer;
import kln.project.customerservice.repository.CustomerRepository;
import kln.project.customerservice.repository.DistrictRepository;
import kln.project.customerservice.repository.TempCustomerRepository;
import kln.project.customerservice.service.CustomerMainService;
import kln.project.customerservice.util.EncryptingModule;
import kln.project.customerservice.util.MessageVarList;
import kln.project.customerservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerMainServiceImpl implements CustomerMainService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    private TempCustomerRepository tempCustomerRepository;

    @Override
    public ResponseBean login(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        CustomerDetailDataBean customerDataBean = modelMapper.map(requestBean.getRequestBody(), CustomerDetailDataBean.class);

        CustomerDetailDataBean ob = new CustomerDetailDataBean();


        if (customerDataBean.getUsername() == null || customerDataBean.getUsername().equals("")) {
            message = MessageVarList.USER_NAME_NULL;
            ob.setVerified(false);
        } else if (customerDataBean.getPassword() == null || customerDataBean.getPassword().equals("")) {
            message = MessageVarList.PASSWORD_NULL;
            ob.setVerified(false);
        } else {
            Customer customer =null;
            customer=customerRepository.findByUsername(customerDataBean.getUsername());
            if (customer==null) {
                message = MessageVarList.USER_NAME_NOT_FOUND;
                ob.setVerified(false);
            } else {
                Customer o = customer;

                if (customerDataBean.getPassword().equals(EncryptingModule.decrypt(o.getPassword()))) {
                    message = MessageVarList.SUCCESS_LOGIN;

                    ob = new CustomerDetailDataBean();
                    ob.setVerified(true);
                    ob.setUsername(o.getUsername());
                    ob.setNic(o.getNic());
                    ob.setCustomerName(o.getCustomerName());
                    ob.setAddress(o.getAddress());
                    ob.setContactNo(o.getContactNo());
                    ob.setEmail(o.getEmail());
                    ob.setDistrict(o.getDistrict().getDistrictId());
                    ob.setDistrictName(o.getDistrict().getDistrictName());
                    
                    ob.setUserrole("customer");

                    responsCode = ResponseCode.RSP_SUCCESS;
                } else {
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
        CustomerDetailDataBean ob = modelMapper.map(requestBean.getRequestBody(), CustomerDetailDataBean.class);

        String errMsg = null;
        errMsg = validateCustometBean(ob);

        Optional<TempCustomer>tc=tempCustomerRepository.findById(ob.getNic());
        Customer t=customerRepository.findByUsername(ob.getUsername());
        if (errMsg != null) {
            message=errMsg;
        }
        if(tc.isPresent()){

            message="already requested";
        }        else if(t!=null){
            message="User name taken";
        }

        else {

          //  TempCustomer o=new TempCustomer();
            Customer o=new Customer();
            o.setCustomerName(ob.getCustomerName());
            o.setNic(EncryptingModule.encrypt(ob.getNic()));
            o.setEmail(EncryptingModule.encrypt(ob.getEmail()));
            o.setAddress(EncryptingModule.encrypt(ob.getAddress()));
            o.setContactNo(EncryptingModule.encrypt(ob.getContactNo()));

            o.setUsername(ob.getUsername());
            o.setPassword(EncryptingModule.encrypt(ob.getPassword()));

            Optional<District>a=districtRepository.findById(ob.getDistrict());
            if(a.isPresent())
                o.setDistrict(a.get());

            customerRepository.saveAndFlush(o);
            //tempCustomerRepository.saveAndFlush(o);
            message=MessageVarList.SUCCESS_ADD;
            responsCode=ResponseCode.RSP_SUCCESS;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;

    }

    @Override
    public ResponseBean updateInfo(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        CustomerDetailDataBean ob = modelMapper.map(requestBean.getRequestBody(), CustomerDetailDataBean.class);

        Customer cst=customerRepository.findByUsername(requestBean.getUsername());
        if(cst!=null){
            if(ob.getPassword()!=null&&!ob.getPassword().equals("")){
                String k=EncryptingModule.decrypt(cst.getPassword());
                if(ob.getOldPassword()!=null&&!ob.getOldPassword().equals("")&&k.equals(ob.getOldPassword()))
                {

                    cst.setPassword(EncryptingModule.encrypt(ob.getPassword()));

                }
                else{
                    responseBean.setResponseCode(responsCode);
                    responseBean.setResponseMsg(MessageVarList.PASSWORD_MISMATCH);
                    responseBean.setContent(null);

                    return responseBean;
                }
            }
            if(ob.getContactNo()!=null&&!ob.getContactNo().equals("")){
                cst.setContactNo(EncryptingModule.encrypt(ob.getContactNo()));
            }
            if(ob.getAddress()!=null&&!ob.getAddress().equals("")){
                cst.setAddress(EncryptingModule.encrypt(ob.getAddress()));
            }
            if(ob.getEmail()!=null&&!ob.getEmail().equals("")){
                cst.setEmail(EncryptingModule.encrypt(ob.getEmail()));
            }
            customerRepository.saveAndFlush(cst);
            responsCode=ResponseCode.RSP_SUCCESS;
            message=MessageVarList.SUCCESS_UPDATE;
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

        CustomerDetailDataBean sb=new CustomerDetailDataBean();

        Customer shp=customerRepository.findByUsername(requestBean.getUsername());
        if(shp!=null){

            sb.setNic(EncryptingModule.decrypt(shp.getNic()));
            sb.setCustomerName(shp.getCustomerName());

            sb.setAddress(EncryptingModule.decrypt(shp.getAddress()));
            sb.setContactNo(EncryptingModule.decrypt(shp.getContactNo()));
            sb.setEmail(EncryptingModule.decrypt(shp.getEmail()));



            postDataBeans.add(sb);
        }

        dataTableBean.setList(postDataBeans);

        return dataTableBean;


    }

    @Override
    public ResponseBean deleteAccount(String myid, RequestBean requestBean, ResponseBean responseBean) {
return null;
    }

    private String validateCustometBean(CustomerDetailDataBean ob) {
        String errMsg = null;


        if(ob.getUsername()==null||ob.getUsername().equals("")){
            errMsg=MessageVarList.USER_NAME_NULL;
        }
        if(ob.getPassword()==null||ob.getPassword().equals("")){
            errMsg=MessageVarList.PASSWORD_NULL;
        }

        if(ob.getNic()==null ||ob.getNic().equals("")){
            errMsg=MessageVarList.CUSTOMER_NIC_NULL;
        }
        if(ob.getCustomerName()==null ||ob.getCustomerName().equals("")){
            errMsg=MessageVarList.CUSTOMER_NAME_NULL;
        }
        if(ob.getAddress()==null ||ob.getAddress().equals("")){
            errMsg=MessageVarList.CUSTOMER_ADDRESS_NULL;
        }
        if(ob.getContactNo()==null ||ob.getContactNo().equals("")){
            errMsg=MessageVarList.CUSTOMER_TELNO_NULL;
        }
        if(ob.getEmail()==null ||ob.getEmail().equals("")){
            errMsg=MessageVarList.CUSTOMER_EMAIL_NULL;
        }
        if(ob.getDistrict()==null ||ob.getDistrict().equals("")){
            errMsg=MessageVarList.CUSTOMER_ASSIGNED_AREA_NULL;
        }
        return errMsg;
    }
}

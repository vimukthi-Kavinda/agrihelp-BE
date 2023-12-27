package kln.project.officerservice.service.Impl;

import kln.project.officerservice.model.bean.*;
import kln.project.officerservice.model.entity.*;
import kln.project.officerservice.repository.*;
import kln.project.officerservice.service.OfficerAdminService;
import kln.project.officerservice.util.Common;
import kln.project.officerservice.util.EncryptingModule;
import kln.project.officerservice.util.MessageVarList;
import kln.project.officerservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class OfficerAdminServiceImpl implements OfficerAdminService {



    @Autowired
    TempFarmerRepository tempfarmerRepository;

    @Autowired
    TempPostRepository tempPostRepository;
    @Autowired
    private OfficerRepository officerRepository;
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private TempCustomerRepository tempCustomerRepository;


    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationTypeRepository notificationTypeRepository;


    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostTypesRepository postTypesRepository;


    @Override
    public ResponseBean confirmFarmerReg(String farmerid, RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<TempFarmer>tf=tempfarmerRepository.findById(farmerid);
        if(tf.isPresent()){
            TempFarmer t=tf.get();
            Farmer n=new Farmer();
            n.setFarmerId(t.getFarmerId());
            n.setName(t.getName());
            n.setNic(t.getNic());
            n.setAddress(t.getAddress());
            n.setTelno(t.getTelno());
            n.setEmail(t.getEmail());
            n.setArea(t.getArea());
            n.setUsername(t.getUsername());
            n.setPassword(t.getPassword());
            Officer of=officerRepository.findByUsername(requestBean.getUsername());
            n.setAuthorizedOfficer(of);

            farmerRepository.saveAndFlush(n);
            tempfarmerRepository.delete(t);

            message= MessageVarList.COMMON_SUCC_CONFIRM;
            responsCode=ResponseCode.RSP_SUCCESS;

        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;


    }

    @Override
    public ResponseBean rejectFarmerReg(String farmerid, RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<TempFarmer>tf=tempfarmerRepository.findById(farmerid);
        if(tf.isPresent()){
            TempFarmer t=tf.get();
            tempfarmerRepository.delete(t);
            message=MessageVarList.COMMON_SUCC_REJECT;
            responsCode=ResponseCode.RSP_SUCCESS;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }

    @Override
    public ResponseBean confirmCustomerReg(String nic, RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<TempCustomer>tf=tempCustomerRepository.findById(nic);
        if(tf.isPresent()){
            TempCustomer t=tf.get();
            Customer n=new Customer();

            n.setCustomerName(t.getCustomerName());
            n.setNic(t.getNic());
            n.setAddress(t.getAddress());
            n.setContactNo(t.getContactNo());
            n.setEmail(t.getEmail());
            n.setDistrict(t.getDistrict());
            n.setUsername(t.getUsername());
            n.setPassword(t.getPassword());
            Officer of=officerRepository.findByUsername(requestBean.getUsername());
            n.setAcceptedOfficer(of);

            customerRepository.saveAndFlush(n);
            tempCustomerRepository.delete(t);

            message= MessageVarList.COMMON_SUCC_CONFIRM;
            responsCode=ResponseCode.RSP_SUCCESS;

        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }

    @Override
    public ResponseBean rejectCustomerReg(String nic, RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<TempCustomer>tf=tempCustomerRepository.findById(nic);
        if(tf.isPresent()){
            TempCustomer t=tf.get();
            tempCustomerRepository.delete(t);
            message=MessageVarList.COMMON_SUCC_REJECT;
            responsCode=ResponseCode.RSP_SUCCESS;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }


    @Override
    public ResponseBean confirmPostReg(String postid, RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<TempPost>tf=tempPostRepository.findById(Integer.valueOf(postid));
        if(tf.isPresent()){
            TempPost pb=tf.get();
            Post p=new Post();

            p.setPostdesc(pb.getPostDesc());
            p.setPostsubject(pb.getPostSubject());

            p.setPostId(pb.getPostId());


            p.setPosttype(pb.getPostType());
            p.setPosteduser(pb.getPostedUser());
            p.setPostedtime(pb.getPostedTime());
            if(pb.getImgName()!=null){
                p.setImgName(pb.getImgName());
            }
            Officer of=officerRepository.findByUsername(requestBean.getUsername());
            p.setPostAcceptedOfficer(of);



            postRepository.saveAndFlush(p);
            tempPostRepository.delete(pb);

            if(pb.getPostType().getPosttypecode().equals("tip")){

                Notification n=new Notification();
                Timestamp t= Timestamp.from(ZonedDateTime.now().toInstant());
                n.setOriginusername(pb.getPostedUser());
                n.setEndusername("all");
                n.setMessage(MessageVarList.TIP_POST_NOTIFICATION+pb.getPostSubject());

                n.setPostedtime(t);
                Optional<NotificationType> nt=notificationTypeRepository.findById("CMNT");
                if(nt.isPresent())n.setNotificationtype(nt.get());
                notificationRepository.saveAndFlush(n);
            }

            message= MessageVarList.COMMON_SUCC_CONFIRM;
            responsCode=ResponseCode.RSP_SUCCESS;

        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }

    @Override
    public ResponseBean rejectPostReg(String postid, RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<TempPost>tf=tempPostRepository.findById(Integer.valueOf(postid));
        if(tf.isPresent()){
            TempPost t=tf.get();
            tempPostRepository.delete(t);
            message=MessageVarList.COMMON_SUCC_REJECT;
            responsCode=ResponseCode.RSP_SUCCESS;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }

    @Autowired
    Common common;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public DataTableBean getFarmerReqList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> tempFarmerDataBeans = new ArrayList<>();
        Page<TempFarmer> tempFarmers;
        Specification<TempFarmer> specification = null;

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        tempFarmers = tempfarmerRepository.findAll(specification, paging);

        if (tempFarmers != null) {
            dataTableBean.setCount(tempFarmers.getTotalElements());
            dataTableBean.setPagecount(tempFarmers.getTotalPages());
        }

        tempFarmers.forEach((tempFarmer) -> {
            FarmerDataBean tempDataBean = modelMapper.map(tempFarmer, FarmerDataBean.class);

            tempDataBean.setNic(EncryptingModule.decrypt(tempFarmer.getNic()));
            tempDataBean.setTelno(EncryptingModule.decrypt(tempFarmer.getTelno()));
            tempDataBean.setEmail(EncryptingModule.decrypt(tempFarmer.getEmail()));
            tempDataBean.setAddress(EncryptingModule.decrypt(tempFarmer.getAddress()));

            tempDataBean.setAreaId(tempFarmer.getArea().getAreaCode());
            tempFarmerDataBeans.add(tempDataBean);
        });

        dataTableBean.setList(tempFarmerDataBeans);

        return dataTableBean;
    }

    @Override
    public DataTableBean getCustomerReqList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> tempFarmerDataBeans = new ArrayList<>();
        Page<TempCustomer> tempFarmers;
        Specification<TempCustomer> specification = null;

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        tempFarmers = tempCustomerRepository.findAll(specification, paging);

        if (tempFarmers != null) {
            dataTableBean.setCount(tempFarmers.getTotalElements());
            dataTableBean.setPagecount(tempFarmers.getTotalPages());
        }

        tempFarmers.forEach((tempFarmer) -> {
            CustomerDataBean tempDataBean = modelMapper.map(tempFarmer, CustomerDataBean.class);

            tempDataBean.setNic(EncryptingModule.decrypt(tempFarmer.getNic()));
            tempDataBean.setEmail(EncryptingModule.decrypt(tempFarmer.getEmail()));
            tempDataBean.setAddress(EncryptingModule.decrypt(tempFarmer.getAddress()));
            tempDataBean.setContactNo(EncryptingModule.decrypt(tempFarmer.getContactNo()));

            tempDataBean.setDistrictId(tempFarmer.getDistrict().getDistrictId());
            tempFarmerDataBeans.add(tempDataBean);
        });

        dataTableBean.setList(tempFarmerDataBeans);

        return dataTableBean;
    }

    @Autowired
    PathsRepository pathsRepository;

    @Override
    public DataTableBean getPostReqList(RequestBean requestBean) {
        String un= requestBean.getUsername();
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> tempFarmerDataBeans = new ArrayList<>();
        Page<TempPost> tempFarmers;
        Specification<TempPost> specification = null;

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        tempFarmers = tempPostRepository.findAll(specification, paging);

        if (tempFarmers != null) {
            dataTableBean.setCount(tempFarmers.getTotalElements());
            dataTableBean.setPagecount(tempFarmers.getTotalPages());
        }

        for(TempPost temp:tempFarmers)  {

            if(temp.getPostedUser().equals(un))continue;

            PostDataBean tempDataBean = new PostDataBean();
            tempDataBean.setPostId(temp.getPostId());
            tempDataBean.setPostSubject(temp.getPostSubject());
            tempDataBean.setPostDesc(temp.getPostDesc());
            if(temp.getImgName()!=null&&!temp.getImgName().equals("")) {
                try {
                    Paths path=pathsRepository.findByFilepathname("postpath");
                    if(path!=null) {
                        String imgPath = path.getPath();
                        Resource resource = new FileSystemResource(imgPath + temp.getImgName());
                        String imageData = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
                        tempDataBean.setImageName(temp.getImgName());
                        tempDataBean.setImageData(imageData);


                    }
                    // tempDataBean.setImage(getPostImage(temp.getImgName()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            tempDataBean.setPostedTime(Common.formatDatetoString(temp.getPostedTime()));
            tempDataBean.setPosttype(temp.getPostType().getPosttypecode());
            tempDataBean.setPosteduser(temp.getPostedUser());
            tempFarmerDataBeans.add(tempDataBean);
        }

        dataTableBean.setList(tempFarmerDataBeans);

        return dataTableBean;
    }

    @Autowired
    TempOfficerRepository tempOfficerRepository;



    @Override
    public ResponseBean rejectOfficerReg(String id, RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<TempOfficer>tf=tempOfficerRepository.findById(id);
        if(tf.isPresent()){
            TempOfficer t=tf.get();
            tempOfficerRepository.delete(t);
            message=MessageVarList.COMMON_SUCC_REJECT;
            responsCode=ResponseCode.RSP_SUCCESS;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }


    @Autowired
    AreaRepository areaRepository;

    @Override
    public ResponseBean confirmOfficerReg(String id, RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<TempOfficer>tf=tempOfficerRepository.findById(id);
        if(tf.isPresent()){
            TempOfficer ob=tf.get();
            Officer o=new Officer();

            o.setOfficerId(ob.getOfficerId());
            o.setOfficerName(ob.getOfficerName());
            o.setNic(ob.getNic());
            o.setEmail(ob.getEmail());
            o.setAddress(ob.getAddress());
            o.setTelno(ob.getTelno());

            o.setUsername(ob.getUsername());
            o.setPassword(ob.getPassword());
            o.setSpecialty(ob.getSpecialty());


                o.setAssignedArea(ob.getAssignedArea());
            Officer of=officerRepository.findByUsername(requestBean.getUsername());

            o.setVerifiedOfficer(of);

            officerRepository.saveAndFlush(o);
            tempOfficerRepository.delete(ob);

            message= MessageVarList.COMMON_SUCC_CONFIRM;
            responsCode=ResponseCode.RSP_SUCCESS;

        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }

    @Override
    public DataTableBean getOfficerReqList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> tempFarmerDataBeans = new ArrayList<>();
        Page<TempOfficer> tempFarmers;
        Specification<TempOfficer> specification = null;

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        tempFarmers = tempOfficerRepository.findAll(specification, paging);

        if (tempFarmers != null) {
            dataTableBean.setCount(tempFarmers.getTotalElements());
            dataTableBean.setPagecount(tempFarmers.getTotalPages());
        }

        tempFarmers.forEach((temp) -> {
            OfficerDetailDataBean tempDataBean = new OfficerDetailDataBean();
            tempDataBean.setOfficerId(temp.getOfficerId());
            tempDataBean.setOfficerName(temp.getOfficerName());
            tempDataBean.setUsername(temp.getUsername());
            tempDataBean.setPassword(temp.getPassword());
            tempDataBean.setNic(EncryptingModule.decrypt(temp.getNic()));
            tempDataBean.setAddress(EncryptingModule.decrypt(temp.getAddress()));
            tempDataBean.setTelno(EncryptingModule.decrypt(temp.getTelno()));
            tempDataBean.setEmail(EncryptingModule.decrypt(temp.getEmail()));
            tempDataBean.setSpecialty(temp.getSpecialty().getSpecialtydesc());
            tempDataBean.setAssignedArea(temp.getAssignedArea().getAreaCode());

            tempDataBean.setAssignedAreaDesc(temp.getAssignedArea().getAreaName());

            tempFarmerDataBeans.add(tempDataBean);
        });

        dataTableBean.setList(tempFarmerDataBeans);

        return dataTableBean;
    }



    private Path foundfile = null;
    private byte[] getPostImage(String imgName) throws IOException {
        byte[] img=null;

        Resource resource = null;
        Paths path=pathsRepository.findByFilepathname("postpath");
        if(path!=null){
            String imgPath=path.getPath();
            Files.list(java.nio.file.Paths.get(imgPath)).forEach(file -> {
                if (file.getFileName().toString().startsWith(imgName)) {
                    foundfile = file;
                }

            });
            if (foundfile != null) {
                resource = new UrlResource(foundfile.toUri());
            }
            img= Files.readAllBytes(resource.getFile().toPath());

        }
        return img;
    }
}

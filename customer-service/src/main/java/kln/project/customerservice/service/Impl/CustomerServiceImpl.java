package kln.project.customerservice.service.Impl;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import kln.project.customerservice.model.bean.*;
import kln.project.customerservice.model.entity.*;
import kln.project.customerservice.repository.*;
import kln.project.customerservice.service.CustomerService;
import kln.project.customerservice.util.Common;
import kln.project.customerservice.util.EncryptingModule;
import kln.project.customerservice.util.MessageVarList;
import kln.project.customerservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MarketCropPriceRepository marketCropPriceRepository;

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    CropRepository cropRepository;


    @Autowired
    Common common;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    FarmerRepository farmerRepository;
    @Autowired
    private FarmerCropRepository farmerCropRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public ResponseBean updateprice(RequestBean requestBean, ResponseBean responseBean) throws Exception {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        MarketDataBean marketDataBean = modelMapper.map(requestBean.getRequestBody(), MarketDataBean.class);

        if(marketDataBean.getMarketId()==null||marketDataBean.getMarketId().equals("")){
message="Market Id is null";
        } else if (marketDataBean.getProductId()==null||marketDataBean.getProductId().equals("")) {
message="Product Id is Null";
        } else if (marketDataBean.getProductPrice()==null||marketDataBean.getProductPrice().equals("")) {
message="Product price is null";
        }else{


            Optional<Market>m=marketRepository.findById(marketDataBean.getMarketId());
            Optional<Crop>c=cropRepository.findById(marketDataBean.getProductId());
            if(m.isPresent()) {

                if(c.isPresent()) {
                    Optional<MarketCropPrice> mkp = marketCropPriceRepository.findById(new MarketCropPriceId(m.get(),c.get()));

                    if(mkp.isPresent()){
                        MarketCropPrice mc=mkp.get();
                        Double price=mc.getUnitPrice();
                        Integer count=mc.getVerifiedUserCount();

                        price=price+Double.parseDouble(marketDataBean.getProductPrice())/++count;

                        mc.setLastUpdatedTime(Common.getCurrentDate());
                        mc.setUnitPrice(price);
                        mc.setVerifiedUserCount(count);

                        marketCropPriceRepository.saveAndFlush(mc);

                        message= "Price "+MessageVarList.SUCCESS_UPDATE;
                        responsCode=ResponseCode.RSP_SUCCESS;

                    }else{
                        message="No product in this market";
                    }
                }
                else{
                    message="No product found";
                }
            }else{
                message="No market found";
            }

        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;

    }


    //default - farmers in district
    //search with parameters
    @Override
    public DataTableBean getFarmerList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> farmerDataBeans = new ArrayList<>();
        Page<Farmer> farmers;
        Specification<Farmer> specification = null;

        FarmerDataBean farmerDataBean =new FarmerDataBean();
        if(requestBean.getRequestBody()!=null)
               farmerDataBean = modelMapper.map(requestBean.getRequestBody(), FarmerDataBean.class);

       /* if(farmerDataBean.getDistrict()==null||farmerDataBean.getDistrict().equals("")) {
            String name = requestBean.getUsername();
            Customer cus = customerRepository.findByUsername(name);
            if (cus != null)
                farmerDataBean.setDistrict(cus.getDistrict().getDistrictId());
        }*/




        List<String>areacodes=new ArrayList<>();
        List<String>productFarmer=new ArrayList<>();

        if (requestBean.isSearch()) {
            specification = farmerSpecification(farmerDataBean);
        }else{
            if(farmerDataBean.getDistrict()!=null){
            Optional<District>d=districtRepository.findById(farmerDataBean.getDistrict());
            if(d.isPresent()) {
                //get areas for customer district
                List<Area> ar = areaRepository.findByDistrict(d.get());
                ar.forEach(a->{
                    areacodes.add(a.getAreacode());
                });
            }
            }
        }

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        farmers = farmerRepository.findAll(specification, paging);

        if (farmers != null) {
            dataTableBean.setCount(farmers.getTotalElements());
            dataTableBean.setPagecount(farmers.getTotalPages());
        }

        if (requestBean.isSearch()) {
            if(farmerDataBean.getDistrict()!=null&&!farmerDataBean.getDistrict().equals("")){
                Optional<District>d=districtRepository.findById(farmerDataBean.getDistrict());
                if(d.isPresent()) {
                    //get areas for customer district
                    List<Area> ar = areaRepository.findByDistrict(d.get());
                    ar.forEach(a->{
                        areacodes.add(a.getAreacode());
                    });
                }
            }
            if(farmerDataBean.getProduct()!=null&&!farmerDataBean.getProduct().equals("")){
                Optional<Crop>cr=cropRepository.findById(farmerDataBean.getProduct());
                if(cr.isPresent()){
                    List<FarmerCrop>fc=farmerCropRepository.findByFarmerCropIdCrop(cr.get());
                    fc.forEach(f->{
                        productFarmer.add(f.getFarmerCropId().getFarmer().getFarmerId());
                    });
                }
            }

        }



        for(Farmer f :farmers){
            if(areacodes.size()>0&&!areacodes.contains(f.getArea().getAreacode())){
                continue;
            }
            if(productFarmer.size()>0&&!productFarmer.contains(f.getFarmerId())){
                continue;
            }
            FarmerDataBean fdb=new FarmerDataBean();
            fdb.setName(f.getName());
            fdb.setFarmerId(f.getFarmerId());
            fdb.setNic(EncryptingModule.decrypt(f.getNic()));
            fdb.setEmail(EncryptingModule.decrypt(f.getEmail()));
            fdb.setTelno(EncryptingModule.decrypt(f.getTelno()));
            fdb.setArea(f.getArea().getAreacode());
            fdb.setAddress(EncryptingModule.decrypt(f.getAddress()));

            farmerDataBeans.add(fdb);
        }
        dataTableBean.setList(farmerDataBeans);
        return dataTableBean;
    }

    private Specification<Farmer> farmerSpecification(FarmerDataBean fb) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            if (fb.getName() != null && !fb.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("name"),"%"+ fb.getName()+"%"));
            }
            if (fb.getNic() != null && !fb.getNic().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("nic"),"%"+ fb.getNic()+"%"));
            }
            if (fb.getAddress() != null && !fb.getAddress().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("address"), "%"+fb.getAddress()+"%"));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public ResponseBean viewFamerCrop(String farmerid, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        List<CropDataBean>cdb=new ArrayList<>();
        if(farmerid==null||farmerid.equals("")){
            message="Empty Id";
        }else{

            Optional<Farmer>f=farmerRepository.findById(farmerid);
            if(f.isPresent()) {
                List<FarmerCrop> fc = farmerCropRepository.findByFarmerCropIdFarmer(f.get());
                fc.forEach(fc1->{
                    CropDataBean db=new CropDataBean();
                    db.setCropcode(fc1.getFarmerCropId().getCrop().getCropCode());
                    db.setCropName(fc1.getFarmerCropId().getCrop().getCropbreedname());
                    db.setHarverst(String.valueOf(fc1.getExpectedHarvest()));
                    cdb.add(db);
                });
                responsCode = ResponseCode.RSP_SUCCESS;
                message="Data found";
                responseBean.setContent(cdb);
            }else{
                message="No farner found";
            }

        }
        responseBean.setResponseCode(responsCode);
responseBean.setResponseMsg(message);
        return responseBean;

    }


    //default - markets in district
    //search with parameters
    @Override
    public DataTableBean viewnearmarkets(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> marketDataBeans = new ArrayList<>();
        Page<Market> markets;
        Specification<Market> specification = null;
        MarketDataBean marketDataBean = new MarketDataBean();
        if(requestBean.getRequestBody()!=null)
        marketDataBean=modelMapper.map(requestBean.getRequestBody(), MarketDataBean.class);


       /* if(marketDataBean.getDistrict()==null||marketDataBean.getDistrict().equals("")) {
            String name = requestBean.getUsername();
            Customer cus = customerRepository.findByUsername(name);
            if (cus != null)
                marketDataBean.setDistrict(cus.getDistrict().getDistrictId());
        }*/



        List<String>areacodes=new ArrayList<>();
        List<String>productMarket=new ArrayList<>();

        if (requestBean.isSearch()) {
            specification = marketSpecification(marketDataBean);
        }else{
            if(marketDataBean.getDistrict()!=null) {
                Optional<District> d = districtRepository.findById(marketDataBean.getDistrict());
                if (d.isPresent()) {
                    //get areas for customer district
                    List<Area> ar = areaRepository.findByDistrict(d.get());
                    ar.forEach(a -> {
                        areacodes.add(a.getAreacode());
                    });
                }
            }
        }

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        markets = marketRepository.findAll(specification, paging);

        if (requestBean.isSearch()) {
            if(marketDataBean.getDistrict()!=null&&!marketDataBean.getDistrict().equals("")){
                Optional<District>d=districtRepository.findById(marketDataBean.getDistrict());
                if(d.isPresent()) {
                    //get areas for customer district
                    List<Area> ar = areaRepository.findByDistrict(d.get());
                    ar.forEach(a->{
                        areacodes.add(a.getAreacode());
                    });
                }
            }

            if(marketDataBean.getProductName()!=null&&!marketDataBean.getProductName().equals("")){
                Crop p=cropRepository.findByCropbreedname(marketDataBean.getProductName());
                if(p!=null)
                    marketDataBean.setProductId(p.getCropCode());
            }


            if(marketDataBean.getProductId()!=null&&!marketDataBean.getProductId().equals("")){
                Optional<Crop>cr=cropRepository.findById(marketDataBean.getProductId());
                if(cr.isPresent()){
                    List<MarketCropPrice>fc=marketCropPriceRepository.findByMarketcroppriceidCrop(cr.get());
                    fc.forEach(f->{
                        productMarket.add(f.getMarketcroppriceid().getMarket().getMarketId());
                    });
                }
            }

        }

        for(Market f :markets){
            if(requestBean.isSearch()&&marketDataBean.getProductId()!=null&&!marketDataBean.getProductId().equals("")&&productMarket.size()==0)break;

            if(areacodes.size()>0&&!areacodes.contains(f.getArea().getAreacode())){
                continue;
            }
            if(productMarket.size()>0&&!productMarket.contains(f.getMarketId())){
                continue;
            }
            MarketDataBean fdb=new MarketDataBean();
            fdb.setMarketId(f.getMarketId());
            fdb.setMarketName(f.getMarketName());
            fdb.setAddress(f.getAddress());
            Area a=f.getArea();
            fdb.setArea(a.getAreacode());
            fdb.setAreaName(a.getAreaName());
            fdb.setContactno(f.getContactno());
            fdb.setMailaddress(f.getMailaddress());



            marketDataBeans.add(fdb);
        }
        dataTableBean.setList(marketDataBeans);
        return dataTableBean;




    }

    private Specification<Market> marketSpecification(MarketDataBean fb) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            if (fb.getMarketName() != null && !fb.getMarketName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("marketName"),"%"+ fb.getMarketName()+"%"));
            }

            if (fb.getAddress() != null && !fb.getAddress().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("address"), "%"+fb.getAddress()+"%"));
            }
            if (fb.getArea() != null && !fb.getArea().isEmpty()) {
                Join<Market, Area> join = root.join("area");
                predicates.add(criteriaBuilder.like(join.get("areacode"), "%" + fb.getArea() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public ResponseBean viewMarketPrice(String marcketid, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        List<MarketDataBean>cdb=new ArrayList<>();
        if(marcketid==null||marcketid.equals("")){
            message="Empty Id";
        }else{

            Optional<Market>f=marketRepository.findById(marcketid);
            if(f.isPresent()) {
                List<MarketCropPrice> fc = marketCropPriceRepository.findByMarketcroppriceidMarket(f.get());
                fc.forEach(fc1->{
                    MarketDataBean db=new MarketDataBean();
                    db.setProductId(fc1.getMarketcroppriceid().getCrop().getCropbreedname());
                    db.setProductPrice(String.valueOf(fc1.getUnitPrice()));
                    db.setAmount(String.valueOf(fc1.getAmount()));


                    cdb.add(db);
                });
                responsCode = ResponseCode.RSP_SUCCESS;
                message="Data found";
                responseBean.setContent(cdb);
            }else{
                message="No Market found";
            }

        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        return responseBean;

    }

    @Override
    public DataTableBean officerList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> alertDataBeans = new ArrayList<>();
        Page<Officer> alerts;
        Specification<Officer> specification = null;
        List<String>areas=new ArrayList<>();

        if (requestBean.isSearch() && requestBean.getRequestBody() != null) {
            OfficerDetailDataBean alertInputBean = modelMapper.map(requestBean.getRequestBody(), OfficerDetailDataBean.class);

            if(alertInputBean.getDistrict()!=null&&!alertInputBean.getDistrict().equals(""))
            {
                Optional<District>d=districtRepository.findById(alertInputBean.getDistrict());
                if(d.isPresent()){
                    List<Area>a=areaRepository.findByDistrict(d.get());
                    a.forEach(a1->{
                        areas.add(a1.getAreacode());
                    });

                }
            }

            specification = makeSpecification(alertInputBean);
        }

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        alerts = officerRepository.findAll(specification, paging);

        if (alerts != null) {
            dataTableBean.setCount(alerts.getTotalElements());
            dataTableBean.setPagecount(alerts.getTotalPages());
        }

        for(Officer alert: alerts){
            Area assignAr=alert.getAssignedArea();
            if(areas.size()>0&&!areas.contains(assignAr.getAreacode())) continue;

            OfficerDetailDataBean alertDataBean = new OfficerDetailDataBean();

            alertDataBean.setOfficerId(alert.getOfficerId());
            alertDataBean.setOfficerName(alert.getOfficerName());
            alertDataBean.setTelno(EncryptingModule.decrypt(alert.getTelno()));
            alertDataBean.setEmail(EncryptingModule.decrypt(alert.getEmail()));
            alertDataBean.setAddress(EncryptingModule.decrypt(alert.getAddress()));
            alertDataBean.setAssignedAreaDesc(alert.getAssignedArea().getAreaName());
            alertDataBean.setSpecialty(alert.getSpecialty().getSpecialtydesc());

            alertDataBeans.add(alertDataBean);
        }

        dataTableBean.setList(alertDataBeans);

        return dataTableBean;
    }

    @Autowired
    OfficerRepository officerRepository;
    private Specification<Officer> makeSpecification(OfficerDetailDataBean alertInputBean) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();



            if (alertInputBean.getSpecialty() != null && !alertInputBean.getSpecialty().isEmpty()) {
                Join<Officerspecialty, Officer> statusJoin = root.join("specialty");
                predicates.add(criteriaBuilder.equal(statusJoin.get("specialtycode"), alertInputBean.getSpecialty()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }
}

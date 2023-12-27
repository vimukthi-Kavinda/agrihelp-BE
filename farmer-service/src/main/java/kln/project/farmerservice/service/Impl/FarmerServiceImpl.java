package kln.project.farmerservice.service.Impl;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import kln.project.farmerservice.model.bean.*;
import kln.project.farmerservice.model.entity.*;
import kln.project.farmerservice.repository.*;
import kln.project.farmerservice.service.FarmerService;
import kln.project.farmerservice.util.Common;
import kln.project.farmerservice.util.EncryptingModule;
import kln.project.farmerservice.util.MessageVarList;
import kln.project.farmerservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class FarmerServiceImpl implements FarmerService {

    @Autowired
    FarmerRepository farmerRepository;


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    Common common;

    @Autowired
    MarketRepository marketRepository;


    @Autowired
    CropRepository cropRepository;

    @Autowired
    MarketCropPriceRepository marketCropPriceRepository;


    @Autowired
    ShopProductRepository shopProductRepository;


    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DiseaseReportedProvinceRepository diseaseReportedProvinceRepository;


    @Autowired
    CropDiseaseRepository cropDiseaseRepository;







    @Override
    public DataTableBean getPlagueList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> marketDataBeans = new ArrayList<>();
        Page<DiseaseReportedProvince> markets;
        Specification<DiseaseReportedProvince> specification = null;

        PlagueDetailBean marketDataBean =new PlagueDetailBean();
        if(requestBean.isSearch()&&requestBean.getRequestBody()!=null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            marketDataBean =  modelMapper.map(requestBean.getRequestBody(), PlagueDetailBean.class);
            if(marketDataBean.getCrop().equals(""))marketDataBean.setCrop(null);
            if(marketDataBean.getDiseaseCode().equals(""))marketDataBean.setDiseaseCode(null);
            if(marketDataBean.getProvince().equals(""))marketDataBean.setProvince(null);

        }
       /* else {
            String name = requestBean.getUsername();
            Officer cus = officerRepository.findByUsername(name);
            if(cus!=null) {
                Province farArea = cus.getAssignedArea().getDistrict().getProvince();

                // marketDataBean.setDistrict(cus.getArea().getDistrict().getDistrictId());
                marketDataBean.setProvince(farArea.getProvinceId());
            }
        }*/



        List<String>diseaceListbyCrop=new ArrayList<>();

        //specification = plagueSpecification(marketDataBean);


        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        markets = diseaseReportedProvinceRepository.findAll(specification, paging);

        if (requestBean.isSearch()) {

            if(marketDataBean.getCropName()!=null&&!marketDataBean.getCropName().equals("")){
                Crop p=cropRepository.findByCropbreedname(marketDataBean.getCropName());
                if(p!=null)
                    marketDataBean.setCrop(String.valueOf(p.getCropCode()));
            }
            //get possible deceases a crop can get
            if(marketDataBean.getCrop()!=null&&!marketDataBean.getCrop().equals("")){
                Optional<Crop>cr=cropRepository.findById(marketDataBean.getCrop());
                if(cr.isPresent()){
                    List<CropDisease>fc=cropDiseaseRepository.findByCropdiseaseidCrop(cr.get());
                    fc.forEach(f->{
                        diseaceListbyCrop.add(f.getCropdiseaseid().getDisease().getDiseaseCode());
                    });
                }
            }

        }



        for(DiseaseReportedProvince f :markets){
            if(requestBean.isSearch()&&marketDataBean.getCropName()!=null&&!marketDataBean.getCropName().equals("")&&diseaceListbyCrop.size()==0)
                break;
            if(requestBean.isSearch()&&marketDataBean.getCrop()!=null&&!marketDataBean.getCrop().equals("")&&diseaceListbyCrop.size()==0)
                break;


            if(diseaceListbyCrop.size()>0&&!diseaceListbyCrop.contains(f.getDiseasereportedprovinceid().getDisease().getDiseaseCode())){
                continue;
            }

            if(marketDataBean.getProvince()!=null){
                if(!f.getDiseasereportedprovinceid().getProvince().getProvinceId().equals(marketDataBean.getProvince())){
                    continue;
                }
            }

            if(marketDataBean.getDiseaseCode()!=null){
                if(!f.getDiseasereportedprovinceid().getDisease().getDiseaseCode().equals(marketDataBean.getDiseaseCode())){
                    continue;
                }
            }

            PlagueDetailBean fdb=new PlagueDetailBean();
            fdb.setDiseaseCode(f.getDiseasereportedprovinceid().getDisease().getDiseaseCode());
            fdb.setDiseaseName(f.getDiseasereportedprovinceid().getDisease().getDiseaseName());
            fdb.setDiseaseDescription(f.getDiseasereportedprovinceid().getDisease().getDiseaseDescription());
            fdb.setSpreadingMethod(f.getDiseasereportedprovinceid().getDisease().getSpreadingMethod());
            fdb.setRootCause(f.getDiseasereportedprovinceid().getDisease().getRootCause());
            fdb.setStartDate(Common.formatDatetoString(f.getFirstReportedDay()));
            fdb.setRemedies(f.getDiseasereportedprovinceid().getDisease().getRemedies());
            fdb.setProvince(f.getDiseasereportedprovinceid().getProvince().getProvinceId());
            fdb.setProvinceName(f.getDiseasereportedprovinceid().getProvince().getProvinceName());

            marketDataBeans.add(fdb);
        }
        dataTableBean.setList(marketDataBeans);
        return dataTableBean;

    }


    //plague effect crops



    @Override
    public DataTableBean getShopList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> marketDataBeans = new ArrayList<>();
        Page<Shop> markets;
        Specification<Shop> specification = null;

        ShopDataBean marketDataBean =new ShopDataBean();
        if(requestBean.isSearch()&&requestBean.getRequestBody()!=null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            marketDataBean = modelMapper.map(requestBean.getRequestBody(), ShopDataBean.class);
        if(marketDataBean.getProduct().equals(""))marketDataBean.setProduct(null);
        }
        else {
            String name = requestBean.getUsername();
            Farmer cus = farmerRepository.findByUsername(name);
            if(cus!=null) {
                District farArea = cus.getArea().getDistrict();

                // marketDataBean.setDistrict(cus.getArea().getDistrict().getDistrictId());
               // marketDataBean.setDistrict(farArea.getDistrictId());

            }
        }



        List<String>productShop=new ArrayList<>();

        specification = shopSpecification(marketDataBean);


        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        markets = shopRepository.findAll(specification, paging);

        if (requestBean.isSearch()) {
            try{
                if(marketDataBean.getProduct()!=null){
                Integer.valueOf(marketDataBean.getProduct());}}
            catch(Exception e){
                dataTableBean.setList(null);
                return dataTableBean;
            }



            if(marketDataBean.getProductName()!=null&&!marketDataBean.getProductName().equals("")){
                Product p=productRepository.findByProductname(marketDataBean.getProductName());
                if(p!=null)
                marketDataBean.setProduct(String.valueOf(p.getProductId()));

            }

            if(marketDataBean.getProduct()!=null&&!marketDataBean.getProduct().equals("")){
                Optional<Product>cr=productRepository.findById(Integer.valueOf(marketDataBean.getProduct()));
                if(cr.isPresent()){
                    List<ShopProduct>fc=shopProductRepository.findByShopproductidProduct(cr.get());
                    fc.forEach(f->{
                        productShop.add(f.getShopproductid().getShop().getShopregno());
                    });
                }
            }

        }



        for(Shop f :markets){
            if(requestBean.isSearch()&&marketDataBean.getProduct()!=null&&!marketDataBean.getProduct().equals("")&&productShop.size()==0)break;
            if(requestBean.isSearch()&&marketDataBean.getProductName()!=null&&!marketDataBean.getProductName().equals("")&&productShop.size()==0)break;

            if(productShop.size()>0&&!productShop.contains(f.getShopregno())){
                continue;
            }
            ShopDataBean fdb=new ShopDataBean();
            fdb.setShopRegNo(f.getShopregno());
            fdb.setShopName(f.getShopname());
            fdb.setAddress(f.getAddress());
            fdb.setDistrict(f.getDistrict().getDistrictName());
            //fdb.setDistrict(f.getDistrict().getDistrictId());
            fdb.setShopContactNo(f.getShopContactNo());
            fdb.setOwnerName(f.getOwnerNic().getOwnerName());
            fdb.setOwnerContactNo(f.getOwnerNic().getOwnerContactNo());

            marketDataBeans.add(fdb);
        }
        dataTableBean.setList(marketDataBeans);
        return dataTableBean;

    }

    private Specification<Shop> shopSpecification(ShopDataBean fb) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            if (fb.getShopRegNo() != null && !fb.getShopRegNo().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("shopregno"),"%"+ fb.getShopRegNo()+"%"));
            }
            if (fb.getShopName() != null && !fb.getShopName().isEmpty()&&!fb.getShopName().equals("")) {
                predicates.add(criteriaBuilder.like(root.<String>get("shopname"),"%"+ fb.getShopName()+"%"));
            }
            if (fb.getAddress() != null && !fb.getAddress().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("address"), "%"+fb.getAddress()+"%"));
            }
            if (fb.getDistrict() != null && !fb.getDistrict().isEmpty()) {
                Join<Shop, District> join = root.join("district");
                predicates.add(criteriaBuilder.equal(join.get("districtId"),  fb.getDistrict() ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public DataTableBean viewnearmarkets(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> marketDataBeans = new ArrayList<>();
        Page<Market> markets;
        Specification<Market> specification = null;

        MarketDataBean marketDataBean =new MarketDataBean();
        if(requestBean.isSearch()&&requestBean.getRequestBody()!=null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            marketDataBean= modelMapper.map(requestBean.getRequestBody(), MarketDataBean.class);
        }
        else {

            String name = requestBean.getUsername();
            Farmer cus = farmerRepository.findByUsername(name);
            if(cus!=null){
            Area farArea = cus.getArea();

            // marketDataBean.setDistrict(cus.getArea().getDistrict().getDistrictId());
            marketDataBean.setArea(farArea.getAreacode());
            }
        }



        List<String>productMarket=new ArrayList<>();

        specification = marketSpecification(marketDataBean);


        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        markets = marketRepository.findAll(specification, paging);

        if (requestBean.isSearch()) {
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
            if(requestBean.isSearch()&&marketDataBean.getProductName()!=null&&!marketDataBean.getProductName().equals("")&&productMarket.size()==0)break;

            if(productMarket.size()>0&&!productMarket.contains(f.getMarketId())){
                continue;
            }
            MarketDataBean fdb=new MarketDataBean();
            fdb.setMarketId(f.getMarketId());
            fdb.setMarketName(f.getMarketName());
            fdb.setAddress(f.getAddress());
            fdb.setArea(f.getArea().getAreacode());
            fdb.setAreaName(f.getArea().getAreaname());

            fdb.setContactno(f.getContactno());
            fdb.setMailaddress(f.getMailaddress());
            fdb.setDistrict(f.getArea().getDistrict().getDistrictName());




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
                    db.setMarketId(fc1.getMarketcroppriceid().getMarket().getMarketId());
                    db.setMarketName(fc1.getMarketcroppriceid().getMarket().getMarketName());
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
    public ResponseBean viewshopitems(String shopid, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        List<ShopProductDataBean>cdb=new ArrayList<>();
        if(shopid==null||shopid.equals("")){
            message="Empty Id";
        }else{

            Optional<Shop>f=shopRepository.findById(shopid);
            if(f.isPresent()) {
                List<ShopProduct> fc = shopProductRepository.findByShopproductidShop(f.get());
                fc.forEach(fc1->{
                    ShopProductDataBean db=new ShopProductDataBean();
                    Product p=fc1.getShopproductid().getProduct();
                    db.setProductId(p.getProductId());
                    db.setProductName(p.getProductname());
                    db.setAmount(fc1.getAmount().doubleValue());
                    db.setPrice(p.getPrice());
                    db.setCategory(p.getCategory());

                    cdb.add(db);
                });
                responsCode = ResponseCode.RSP_SUCCESS;
                message="Data found";
                responseBean.setContent(cdb);
            }else{
                message="No Shop found";
            }

        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        return responseBean;
    }
@Autowired
DiseaseRepository diseaseRepository;

@Autowired
    ProductPesticideRepository productpesticideRepository;
    @Override
    public ResponseBean viewPlageDetails(String diseacecode, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        List<DiseaceCropDataBean>cdb=new ArrayList<>();
        if(diseacecode==null||diseacecode.equals("")){
            message="Empty Id";
        }else{

            Optional<Disease>f=diseaseRepository.findById(diseacecode);
            if(f.isPresent()) {
                List<CropDisease> fc = cropDiseaseRepository.findByCropdiseaseidDisease(f.get());
                fc.forEach(fc1->{
                    DiseaceCropDataBean db=new DiseaceCropDataBean();
                    db.setDiseaceCode(fc1.getCropdiseaseid().getDisease().getDiseaseCode());
                    db.setDiseaceName(fc1.getCropdiseaseid().getDisease().getDiseaseName());
                    db.setCropName(fc1.getCropdiseaseid().getCrop().getCropbreedname());
                    db.setCropId(fc1.getCropdiseaseid().getCrop().getCropCode());
                    cdb.add(db);
                });

               // List<ProductPesticide>pes=productpesticideRepository.findByProductpesticideidRecomandeddisease(f.get());



                responsCode = ResponseCode.RSP_SUCCESS;
                message="Data found";
                responseBean.setContent(cdb);
            }else{
                message="No Shop found";
            }
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        return responseBean;
    }


    @Autowired
    FarmerCropRepository farmerCropRepository;
    @Autowired
    private ProductFertilizerRepository productFertilizerRepository;

    @Override
    public DataTableBean getMyCropList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> dataBeans = new ArrayList<>();
        Page<FarmerCrop> farmerCrops;
        Specification<FarmerCrop> specification = null;


       // specification = makeSpecification(requestBean.getUsername());

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        farmerCrops = farmerCropRepository.findAll(specification, paging);

        if (farmerCrops != null) {
            dataTableBean.setCount(farmerCrops.getTotalElements());
            dataTableBean.setPagecount(farmerCrops.getTotalPages());
        }

        for(FarmerCrop b :farmerCrops) {

            Farmer f=b.getFarmerCropId().getFarmer();
            if(!f.getUsername().equals(requestBean.getUsername())){
                continue;
            }
            FarmerCropDataBean dataBean1 = new FarmerCropDataBean();
            dataBean1.setFarmerid(f.getFarmerId());
            dataBean1.setCropid(b.getFarmerCropId().getCrop().getCropCode());
            dataBean1.setCropName(b.getFarmerCropId().getCrop().getCropbreedname());
            dataBean1.setAmount(String.valueOf(b.getExpectedHarvest()));
            dataBeans.add(dataBean1);
        }

        dataTableBean.setList(dataBeans);

        return dataTableBean;
    }

    @Override
    public ResponseBean deleteMyCrop(String farmerid, String cropcode, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<Farmer>frm=farmerRepository.findById(farmerid);
        if(frm.isPresent()){
            Optional<Crop> crp = cropRepository.findById(cropcode);

            if (crp.isPresent()) {

                Optional<FarmerCrop>fc=farmerCropRepository.findById(new FarmerCropId(frm.get(),crp.get()));
                if (fc.isPresent()) {

                    farmerCropRepository.delete(fc.get());
                    responsCode=ResponseCode.RSP_SUCCESS;
                    message=MessageVarList.SUCCESS_DELETE;
                }else{
                    message="Crop"+MessageVarList.COMMON_NOT_FOUND+" for farmer";
                }


            } else {
                message = "Crop"+MessageVarList.COMMON_NOT_FOUND;
            }
        }else{
            message="Farmer"+MessageVarList.COMMON_NOT_FOUND;

        }        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }

    @Override
    public ResponseBean addMyCrop(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        FarmerCropDataBean dataBean = modelMapper.map(requestBean.getRequestBody(), FarmerCropDataBean.class);

        if(dataBean.getCropid()==null){
            message="No crop selected";
        }else{
            Farmer f= farmerRepository.findByUsername(requestBean.getUsername());
            Optional<Crop> c= cropRepository.findById(dataBean.getCropid());

            if(f==null){
                message="Farmer"+MessageVarList.COMMON_NOT_FOUND;
            }else if(!c.isPresent()){
                message="Crop"+MessageVarList.COMMON_NOT_FOUND;
            }
            else {

                Optional<FarmerCrop> po = farmerCropRepository.findById(new FarmerCropId(f,c.get()));
                if (!po.isPresent()) {

                    FarmerCrop fcp=new FarmerCrop();
                    fcp.setFarmerCropId(new FarmerCropId(f,c.get()));
                    if(dataBean.getAmount()!=null&&!dataBean.getAmount().equals("")){
                    fcp.setExpectedHarvest(Double.parseDouble(dataBean.getAmount()));}
                    else{
                        fcp.setExpectedHarvest(0.0);
                    }
                    farmerCropRepository.saveAndFlush(fcp);
                message=MessageVarList.SUCCESS_ADD;
                responsCode=ResponseCode.RSP_SUCCESS;


                } else {

                    FarmerCrop fcp= po.get();
                    if(dataBean.getAmount()!=null&&!dataBean.getAmount().equals("")){
                        fcp.setExpectedHarvest(Double.parseDouble(dataBean.getAmount()));}
                    else{
                        fcp.setExpectedHarvest(0.0);
                    }
                    farmerCropRepository.saveAndFlush(fcp);
                    message=MessageVarList.SUCCESS_UPDATE;
                    responsCode=ResponseCode.RSP_SUCCESS;
                   // message = "Crop" + MessageVarList.COMMON_ALREADY_EXIST+" for farmer";

                }

            }
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }

    @Override
    public ResponseBean editMyCrop(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        FarmerCropDataBean dataBean = modelMapper.map(requestBean.getRequestBody(), FarmerCropDataBean.class);

        if(dataBean.getCropid()==null){
            message="No crop selected";
        } else if (dataBean.getAmount()==null) {
            message="Amount is null";
        } else{
            Farmer f= farmerRepository.findByUsername(requestBean.getUsername());
            Optional<Crop> c= cropRepository.findById(dataBean.getCropid());

            if(f==null){
                message="Farmer"+MessageVarList.COMMON_NOT_FOUND;
            }else if(!c.isPresent()){
                message="Crop"+MessageVarList.COMMON_NOT_FOUND;
            }
            else {

                Optional<FarmerCrop> po = farmerCropRepository.findById(new FarmerCropId(f,c.get()));
                if (po.isPresent()) {

                    FarmerCrop fcp=po.get();
                    fcp.setFarmerCropId(new FarmerCropId(f,c.get()));
                    if(dataBean.getAmount()!=null&&!dataBean.getAmount().equals("")){
                        fcp.setExpectedHarvest(Double.parseDouble(dataBean.getAmount()));}

                    farmerCropRepository.saveAndFlush(fcp);



                } else {
                    message = "Crop" + MessageVarList.COMMON_NOT_FOUND+" for farmer";

                }

            }
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }

    @Autowired
    OfficerRepository officerRepository;

    @Override
    public DataTableBean getAllCropList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> cropDataBeans = new ArrayList<>();
        Page<Crop> crops;
        Specification<Crop> specification = null;

        if (requestBean.isSearch() && requestBean.getRequestBody() != null) {
            CropDataBean cropDataBean = modelMapper.map(requestBean.getRequestBody(), CropDataBean.class);
            specification = makeSpecification(cropDataBean);
        }

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        crops = cropRepository.findAll(specification, paging);

        if (crops != null) {
            dataTableBean.setCount(crops.getTotalElements());
            dataTableBean.setPagecount(crops.getTotalPages());
        }

        crops.forEach((crp) -> {
            CropDataBean db = new CropDataBean();
            db.setCropid(crp.getCropCode());
            db.setCropname(crp.getCropbreedname());
            db.setScientificname(crp.getScientificName());
            db.setWeedmng(crp.getWeedmgt());
            db.setWatermng(crp.getWatermgt());
            db.setFertilizermng(crp.getFertilizerusg());
            cropDataBeans.add(db);
        });

        dataTableBean.setList(cropDataBeans);

        return dataTableBean;
    }

    private Specification<Crop> makeSpecification(CropDataBean cropDataBean) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (cropDataBean.getCropname() != null && !cropDataBean.getCropname().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("cropbreedname"), "%" + cropDataBean.getCropname() + "%"));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }

    @Override
    public ResponseBean viewCropFertilizer(String cropcode, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        List<FertilizerDataBean>cdb=new ArrayList<>();
        if(cropcode==null||cropcode.equals("")){
            message="Empty Id";
        }else{

            Optional<Crop>f=cropRepository.findById(cropcode);
            if(f.isPresent()) {
                List<ProductFertilizer> fc = productFertilizerRepository.findByProductfertilizeridRecomandedcrop(f.get());
                fc.forEach(fc1->{
                    FertilizerDataBean db=new FertilizerDataBean();
                    db.setProductid(String.valueOf(fc1.getProductfertilizerid().getProduct().getProductId()));
                    db.setProductname(fc1.getProductfertilizerid().getProduct().getProductname());
                    db.setPrice(String.valueOf(fc1.getProductfertilizerid().getProduct().getPrice()));
                    db.setManufacturedby(fc1.getProductfertilizerid().getProduct().getManufacturedBy());
                    cdb.add(db);
                });
                responsCode = ResponseCode.RSP_SUCCESS;
                message="Data found";
                responseBean.setContent(cdb);
            }else{
                message="No Shop found";
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
            alertDataBean.setAssignedAreaDesc(alert.getAssignedArea().getAreaname());
            alertDataBean.setSpecialty(alert.getSpecialty().getSpecialtydesc());

            alertDataBeans.add(alertDataBean);
        }

        dataTableBean.setList(alertDataBeans);

        return dataTableBean;
    }


    @Autowired
    PathsRepository pathsRepository;


    @Override
    public ResponseBean viewProductDetails(String productid, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        ProductDataBean pb=null;
        Optional<Product>p1=productRepository.findById(Integer.valueOf(productid));
        if(p1.isPresent()){
            Product p=p1.get();
            pb=new ProductDataBean();
            pb.setCategory(p.getCategory());
            pb.setProductid(String.valueOf(p.getProductId()));
            pb.setProductname(p.getProductname());
            pb.setPrice(String.valueOf(p.getPrice()));
            pb.setManufacturedby(p.getManufacturedBy());
            pb.setImportedby(p.getImportedBy());
            pb.setExpdate(p.getExpDate());
            pb.setUsage(p.getUsge());

            if(p.getImgname()!=null&&!p.getImgname().equals("")) {
                try {
                    Paths path=pathsRepository.findByFilepathname("prodimg");
                    if(path!=null) {
                        String imgPath = path.getPath();
                        Resource resource = new FileSystemResource(imgPath + p.getImgname());
                        String imageData = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
                        pb.setImageName(p.getImgname());
                        pb.setImageData(imageData);


                    }
                    // tempDataBean.setImage(getPostImage(temp.getImgName()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }


            //shops

            List<ShopDataBean>sdb=new ArrayList<>();
            List<ShopProduct>sprod=shopProductRepository.findByShopproductidProduct(p);
            if(sprod!=null){
                sprod.forEach((sp)->{
                    Shop s=sp.getShopproductid().getShop();
                    ShopDataBean sb=new ShopDataBean();
                    sb.setShopName(s.getShopname());
                    sb.setAddress(s.getAddress());
                    sb.setShopContactNo(s.getShopContactNo());
                    sb.setOwnerContactNo(s.getOwnerNic().getOwnerContactNo());
                    sdb.add(sb);
                });

            }

pb.setShopdetails(sdb);

            responsCode=ResponseCode.RSP_SUCCESS;
            message=MessageVarList.COMMON_FOUND;
        }

        responseBean.setContent(pb);
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        return responseBean;

    }
@Autowired
ProductPesticideRepository productPesticideRepository;





    @Override
    public ResponseBean getRecomandedpestlist(String diseaseid, ResponseBean responseBean) {

        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        List<ProductDataBean>pdb=new ArrayList<>();
        Optional<Disease>d=diseaseRepository.findById(diseaseid);
        if(d.isPresent()) {
            List<ProductPesticide> ps = productPesticideRepository.findByProductpesticideidRecomandeddisease(d.get());

            ps.forEach((p)->{
                ProductDataBean db=new ProductDataBean();
                db.setProductid(String.valueOf(p.getProductpesticideid().getProduct().getProductId()));
                db.setProductname(p.getProductpesticideid().getProduct().getProductname());
                db.setManufacturedby(p.getProductpesticideid().getProduct().getManufacturedBy());
                pdb.add(db);
            });
            responsCode=ResponseCode.RSP_SUCCESS;
            message=MessageVarList.COMMON_FOUND;

        }
        responseBean.setContent(pdb);
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        return responseBean;

    }

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

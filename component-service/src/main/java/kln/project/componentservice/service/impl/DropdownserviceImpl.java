package kln.project.componentservice.service.impl;

import kln.project.componentservice.model.bean.*;
import kln.project.componentservice.model.entity.*;
import kln.project.componentservice.repository.*;
import kln.project.componentservice.service.Dropdownservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DropdownserviceImpl implements Dropdownservice {


    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    AreaRepository areaRepository;



    @Override
    public DistrictDropDownBean accessAndLoadDistrict() {

        DistrictDropDownBean db=new DistrictDropDownBean();
        List<DropDownBean> districtlist=new ArrayList<>();
        List<District>dl=districtRepository.findAll();
        for (District d:dl
             ) {
            DropDownBean t=new DropDownBean();
            t.setCode(d.getDistrictId());
            t.setDesc(d.getDistrictName());
            districtlist.add(t);

        }
        db.setDistrictlist(districtlist);
        return db;
    }


    @Override
    public AreaDropDownBean accessAndLoadArea() {

        AreaDropDownBean db=new AreaDropDownBean();
        List<DropDownBean> arealist=new ArrayList<>();
        List<Area>dl=areaRepository.findAll();
        for (Area d:dl
        ) {
            DropDownBean t=new DropDownBean();
            t.setCode(d.getAreacode());
            t.setDesc(d.getAreaName());
            arealist.add(t);

        }
        db.setArealist(arealist);
        return db;
    }

    @Autowired
    ProductRepository productRepository;


    @Override
    public PesticideDropDownBean accessAndLoadPesticide() {
        PesticideDropDownBean db=new PesticideDropDownBean();
        List<DropDownBean> peslist=new ArrayList<>();
        List<Product>dl=productRepository.findByCategory("pesticide");
        for (Product d:dl
        ) {
            DropDownBean t=new DropDownBean();
            t.setCode(String.valueOf(d.getProductId()));
            t.setDesc(d.getProductName());
            peslist.add(t);

        }
        db.setPeslist(peslist);
        db.setCroplist(getCrops());
        return db;

    }
@Autowired
ProvinceRepository provinceRepository;


    @Override
    public ProvinceDropDownBean accessAndLoadProvince() {
        ProvinceDropDownBean db=new ProvinceDropDownBean();
        List<DropDownBean> peslist=new ArrayList<>();
        List<Province>dl=provinceRepository.findAll();
        for (Province d:dl
        ) {
            DropDownBean t=new DropDownBean();
            t.setCode(String.valueOf(d.getProvinceId()));
            t.setDesc(d.getProvinceName());
            peslist.add(t);

        }
        db.setProvincelist(peslist);
        return db;
    }

    @Autowired
    DiseaseRepository diseaseRepository;



    @Override
    public DiseaseDropDownBean accessAndLoadDisease() {
        DiseaseDropDownBean db=new DiseaseDropDownBean();
        List<DropDownBean> peslist=new ArrayList<>();
        List<Disease>dl=diseaseRepository.findAll();
        for (Disease d:dl
        ) {
            DropDownBean t=new DropDownBean();
            t.setCode(String.valueOf(d.getDiseaseCode()));
            t.setDesc(d.getDiseaseName());
            peslist.add(t);

        }
        db.setDiseaselist(peslist);
        return db;
    }


    @Override
    public ReportDropDownBean accessDiseaseAndProvince() {
        ReportDropDownBean db=new ReportDropDownBean();
        List<DropDownBean> peslist=getDiseaseList();
        db.setDiseaselist(peslist);
        List<DropDownBean> pelist=getProvinces();
        db.setProvincelist(pelist);
        return db;
    }

    @Cacheable("diseaselist")
    private List<DropDownBean> getDiseaseList() {

        List<DropDownBean> peslist= new ArrayList<>();
        List<Disease>dl=diseaseRepository.findAll();
        for (Disease d:dl
        ) {
            DropDownBean t=new DropDownBean();
            t.setCode(String.valueOf(d.getDiseaseCode()));
            t.setDesc(d.getDiseaseName());
            peslist.add(t);

        }
        return peslist;
    }


    @Cacheable("provinces")
    private List<DropDownBean> getProvinces() {
        List<DropDownBean> pelist=new ArrayList<>();

        List<Province>d1=provinceRepository.findAll();
        for (Province d:d1
        ) {
            DropDownBean t=new DropDownBean();
            t.setCode(String.valueOf(d.getProvinceId()));
            t.setDesc(d.getProvinceName());
            pelist.add(t);

        }return pelist;
    }


    @Override
    public ReportDropDownBean accessCropAndProvince() {
        ReportDropDownBean ret=new ReportDropDownBean();
        ret.setProvincelist(getProvinces());
        ret.setCroplist(getCrops());
        ret.setDiseaselist(getDiseaseList());
return ret;
    }


    @Override
    public PesticideDropDownBean accessandloadfertilizer() {
        PesticideDropDownBean db=new PesticideDropDownBean();
        List<DropDownBean> peslist=new ArrayList<>();
        List<Product>dl=productRepository.findByCategory("fertilizer");
        for (Product d:dl
        ) {
            DropDownBean t=new DropDownBean();
            t.setCode(String.valueOf(d.getProductId()));
            t.setDesc(d.getProductName());
            peslist.add(t);

        }
        db.setPeslist(peslist);
        return db;

    }



    @Override
    public CropDropDownBean accessandloadcrop() {
        CropDropDownBean b=new CropDropDownBean();
        b.setCroplist(getCrops() );
        return b;
    }



    @Override
    public ShopParaDropDownBean accessprovinceandproduct() {
        ShopParaDropDownBean p=new ShopParaDropDownBean();

        p.setDistrictlist(getDistricts());
        p.setProductlist(getProducts());
        return p;

    }


    @Override
    public ShopParaDropDownBean accessdistrictandcrop() {
        ShopParaDropDownBean p=new ShopParaDropDownBean();

        p.setDistrictlist(getDistricts());
        p.setProductlist(getCrops());
        return p;
    }

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ShopProductRepository shopProductRepository;



    @Override
    public ShopParaDropDownBean accessProductinShop(RequestBean requestBean) {
        ShopParaDropDownBean sb=null;
        String shopNo=requestBean.getUsername();
        Optional<Shop>sh=shopRepository.findById(shopNo);
        if(sh.isPresent()){
            sb=new ShopParaDropDownBean();
            List<DropDownBean> productlist=new ArrayList<>();
            String ownerNic=sh.get().getOwnernic().getOwnerNic();

            //other shops own
           // List<Shop>ownShops=shopRepository.findByOwnernic(ownerNic);

           List<Object[]>spr= shopProductRepository.getAllProductsOwn(ownerNic,shopNo);

           spr.forEach(sp->{
               DropDownBean db=new DropDownBean();
               db.setCode(sp[0].toString());
               db.setDesc((String)sp[1]);
               productlist.add(db);
           });


sb.setProductlist(productlist);




        }
        return sb;


    }

    @Autowired
    OfficerspecialtyRepository officerspecialtyRepository;

    @Override
    public DistrictSpecialtyBean accessdistrictandSpeciality() {
        DistrictSpecialtyBean d=new DistrictSpecialtyBean();
        d.setDistrict(getDistricts());
        d.setSpecialty(getSpeciality());

        return d;





    }

    @Override
    public DistrictSpecialtyBean accessAreaandSpeciality() {
        DistrictSpecialtyBean dd=new DistrictSpecialtyBean();
        List<DropDownBean> arealist=new ArrayList<>();
        List<Area>dl=areaRepository.findAll();
        for (Area d:dl
        ) {
            DropDownBean t=new DropDownBean();
            t.setCode(d.getAreacode());
            t.setDesc(d.getAreaName());
            arealist.add(t);

        }
        dd.setDistrict(arealist);
        dd.setSpecialty(getSpeciality());

        return dd;
    }


    private List<DropDownBean> getSpeciality() {
        List<DropDownBean> specialty=new ArrayList<>();

        List<Officerspecialty>ol=officerspecialtyRepository.findAll();
        ol.forEach((o->{
            DropDownBean db=new DropDownBean();
            db.setCode(o.getSpecialtycode());
            db.setDesc(o.getSpecialtydesc());
            specialty.add(db);
        }));
        return specialty;
    }


    @Cacheable("getDistricts")
    private List<DropDownBean> getDistricts() {
        List<DropDownBean> pelist=new ArrayList<>();
        List<District>cl=districtRepository.findAll();
        cl.forEach(c->{
            DropDownBean d=new DropDownBean();
            d.setCode(c.getDistrictId());
            d.setDesc(c.getDistrictName());
            pelist.add(d);
        });
        return pelist;
    }

    @Cacheable("getProducts")
    private List<DropDownBean> getProducts() {
        List<DropDownBean> pelist=new ArrayList<>();
        List<Product>cl=productRepository.findAll();
        cl.forEach(c->{
            DropDownBean d=new DropDownBean();
            d.setCode(String.valueOf(c.getProductId()));
            d.setDesc(c.getProductName());
            pelist.add(d);
        });
        return pelist;

    }

    @Autowired
    CropRepository cropRepository;


    @Cacheable("getCrops")
    private List<DropDownBean> getCrops() {
        List<DropDownBean> pelist=new ArrayList<>();
List<Crop>cl=cropRepository.findAll();
cl.forEach(c->{
    DropDownBean d=new DropDownBean();
    d.setCode(c.getCropCode());
    d.setDesc(c.getCropbreedname());
    pelist.add(d);
});
return pelist;
    }


}

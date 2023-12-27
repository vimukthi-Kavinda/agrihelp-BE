package kln.project.componentservice.service;

import kln.project.componentservice.model.bean.*;

public interface Dropdownservice {
    DistrictDropDownBean accessAndLoadDistrict();

    AreaDropDownBean accessAndLoadArea();

    PesticideDropDownBean accessAndLoadPesticide();

    ProvinceDropDownBean accessAndLoadProvince();

    DiseaseDropDownBean accessAndLoadDisease();

    ReportDropDownBean accessDiseaseAndProvince();

    ReportDropDownBean accessCropAndProvince();

    PesticideDropDownBean accessandloadfertilizer();

    CropDropDownBean accessandloadcrop();

    ShopParaDropDownBean accessprovinceandproduct();

    ShopParaDropDownBean accessdistrictandcrop();

    ShopParaDropDownBean accessProductinShop(RequestBean requestBean);

    DistrictSpecialtyBean accessdistrictandSpeciality();

    DistrictSpecialtyBean accessAreaandSpeciality();
}

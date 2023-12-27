package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.Disease;
import kln.project.shopservice.model.entity.DiseaseReportedProvince;
import kln.project.shopservice.model.entity.DiseaseReportedProvinceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DiseaseReportedProvinceRepository extends JpaRepository<DiseaseReportedProvince, DiseaseReportedProvinceId>, JpaSpecificationExecutor<DiseaseReportedProvince> {
    List<DiseaseReportedProvince> findByDiseasereportedprovinceidDisease(Disease pst);
}
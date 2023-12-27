package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.Disease;
import kln.project.officerservice.model.entity.DiseaseReportedProvince;
import kln.project.officerservice.model.entity.DiseaseReportedProvinceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DiseaseReportedProvinceRepository extends JpaRepository<DiseaseReportedProvince, DiseaseReportedProvinceId>, JpaSpecificationExecutor<DiseaseReportedProvince> {
    List<DiseaseReportedProvince> findByDiseasereportedprovinceidDisease(Disease pst);
}
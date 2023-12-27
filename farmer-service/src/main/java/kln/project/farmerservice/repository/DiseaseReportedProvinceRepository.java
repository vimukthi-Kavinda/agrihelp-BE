package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.DiseaseReportedProvince;
import kln.project.farmerservice.model.entity.DiseaseReportedProvinceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiseaseReportedProvinceRepository extends JpaRepository<DiseaseReportedProvince, DiseaseReportedProvinceId>, JpaSpecificationExecutor<DiseaseReportedProvince> {
}
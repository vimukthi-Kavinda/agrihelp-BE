package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.DiseaseReportedProvince;
import kln.project.customerservice.model.entity.DiseaseReportedProvinceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiseaseReportedProvinceRepository extends JpaRepository<DiseaseReportedProvince, DiseaseReportedProvinceId>, JpaSpecificationExecutor<DiseaseReportedProvince> {
}
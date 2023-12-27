package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.CropDisease;
import kln.project.customerservice.model.entity.CropDiseaseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CropDiseaseRepository extends JpaRepository<CropDisease, CropDiseaseId>, JpaSpecificationExecutor<CropDisease> {
}
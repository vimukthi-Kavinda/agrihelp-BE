package kln.project.componentservice.repository;

import kln.project.componentservice.model.entity.CropDisease;
import kln.project.componentservice.model.entity.CropDiseaseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CropDiseaseRepository extends JpaRepository<CropDisease, CropDiseaseId>, JpaSpecificationExecutor<CropDisease> {
}
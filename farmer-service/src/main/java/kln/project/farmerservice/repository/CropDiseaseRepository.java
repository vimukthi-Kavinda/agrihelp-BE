package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Crop;
import kln.project.farmerservice.model.entity.CropDisease;
import kln.project.farmerservice.model.entity.CropDiseaseId;
import kln.project.farmerservice.model.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CropDiseaseRepository extends JpaRepository<CropDisease, CropDiseaseId>, JpaSpecificationExecutor<CropDisease> {
    List<CropDisease> findByCropdiseaseidCrop(Crop crop);

    List<CropDisease> findByCropdiseaseidDisease(Disease disease);
}
package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.Crop;
import kln.project.shopservice.model.entity.CropDisease;
import kln.project.shopservice.model.entity.CropDiseaseId;
import kln.project.shopservice.model.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CropDiseaseRepository extends JpaRepository<CropDisease, CropDiseaseId>, JpaSpecificationExecutor<CropDisease> {
    List<CropDisease> findByCropdiseaseidDisease(Disease disease);

    List<CropDisease> findByCropdiseaseidCrop(Crop crop);
}
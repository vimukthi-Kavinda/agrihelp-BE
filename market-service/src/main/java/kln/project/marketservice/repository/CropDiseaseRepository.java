package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.Crop;
import kln.project.marketservice.model.entity.CropDisease;
import kln.project.marketservice.model.entity.CropDiseaseId;
import kln.project.marketservice.model.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CropDiseaseRepository extends JpaRepository<CropDisease, CropDiseaseId>, JpaSpecificationExecutor<CropDisease> {
    List<CropDisease> findByCropdiseaseidDisease(Disease disease);

    List<CropDisease> findByCropdiseaseidCrop(Crop crop);
}
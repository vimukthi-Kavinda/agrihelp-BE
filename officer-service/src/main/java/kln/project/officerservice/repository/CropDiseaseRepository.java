package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.Crop;
import kln.project.officerservice.model.entity.CropDisease;
import kln.project.officerservice.model.entity.CropDiseaseId;
import kln.project.officerservice.model.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CropDiseaseRepository extends JpaRepository<CropDisease, CropDiseaseId>, JpaSpecificationExecutor<CropDisease> {
    List<CropDisease> findByCropdiseaseidDisease(Disease disease);

    List<CropDisease> findByCropdiseaseidCrop(Crop crop);
}
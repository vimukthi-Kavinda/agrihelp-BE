package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.FarmerCrop;
import kln.project.officerservice.model.entity.FarmerCropId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FarmerCropRepository extends JpaRepository<FarmerCrop, FarmerCropId>, JpaSpecificationExecutor<FarmerCrop> {
}
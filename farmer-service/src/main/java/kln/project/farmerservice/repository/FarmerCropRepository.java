package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.FarmerCrop;
import kln.project.farmerservice.model.entity.FarmerCropId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FarmerCropRepository extends JpaRepository<FarmerCrop, FarmerCropId>, JpaSpecificationExecutor<FarmerCrop> {
}
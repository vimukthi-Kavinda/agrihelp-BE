package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.FarmerCrop;
import kln.project.marketservice.model.entity.FarmerCropId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FarmerCropRepository extends JpaRepository<FarmerCrop, FarmerCropId>, JpaSpecificationExecutor<FarmerCrop> {
}
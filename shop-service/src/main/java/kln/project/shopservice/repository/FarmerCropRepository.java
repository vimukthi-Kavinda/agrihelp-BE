package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.FarmerCrop;
import kln.project.shopservice.model.entity.FarmerCropId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FarmerCropRepository extends JpaRepository<FarmerCrop, FarmerCropId>, JpaSpecificationExecutor<FarmerCrop> {
}
package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.Crop;
import kln.project.customerservice.model.entity.Farmer;
import kln.project.customerservice.model.entity.FarmerCrop;
import kln.project.customerservice.model.entity.FarmerCropId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FarmerCropRepository extends JpaRepository<FarmerCrop, FarmerCropId>, JpaSpecificationExecutor<FarmerCrop> {
    List<FarmerCrop> findByFarmerCropIdCrop(Crop crop);

    List<FarmerCrop> findByFarmerCropIdFarmer(Farmer farmer);
}
package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CropRepository extends JpaRepository<Crop, String>, JpaSpecificationExecutor<Crop> {
    Crop findByCropbreedname(String productName);
}
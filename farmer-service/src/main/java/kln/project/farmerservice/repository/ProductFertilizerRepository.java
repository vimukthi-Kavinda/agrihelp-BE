package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Crop;
import kln.project.farmerservice.model.entity.ProductFertilizer;
import kln.project.farmerservice.model.entity.ProductFertilizerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductFertilizerRepository extends JpaRepository<ProductFertilizer, ProductFertilizerId>, JpaSpecificationExecutor<ProductFertilizer> {
    List<ProductFertilizer> findByProductfertilizeridRecomandedcrop(Crop crop);
}
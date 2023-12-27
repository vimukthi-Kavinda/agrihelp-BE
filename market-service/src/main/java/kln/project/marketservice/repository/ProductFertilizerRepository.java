package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.ProductFertilizer;
import kln.project.marketservice.model.entity.ProductFertilizerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductFertilizerRepository extends JpaRepository<ProductFertilizer, ProductFertilizerId>, JpaSpecificationExecutor<ProductFertilizer> {
}
package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.ProductFertilizer;
import kln.project.shopservice.model.entity.ProductFertilizerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductFertilizerRepository extends JpaRepository<ProductFertilizer, ProductFertilizerId>, JpaSpecificationExecutor<ProductFertilizer> {
}
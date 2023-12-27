package kln.project.componentservice.repository;

import kln.project.componentservice.model.entity.ProductFertilizer;
import kln.project.componentservice.model.entity.ProductFertilizerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductFertilizerRepository extends JpaRepository<ProductFertilizer, ProductFertilizerId>, JpaSpecificationExecutor<ProductFertilizer> {
}
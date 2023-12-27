package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.ProductFertilizer;
import kln.project.officerservice.model.entity.ProductFertilizerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductFertilizerRepository extends JpaRepository<ProductFertilizer, ProductFertilizerId>, JpaSpecificationExecutor<ProductFertilizer> {
}
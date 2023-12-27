package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.ProductPesticide;
import kln.project.shopservice.model.entity.ProductPesticideId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductPesticideRepository extends JpaRepository<ProductPesticide, ProductPesticideId>, JpaSpecificationExecutor<ProductPesticide> {
}
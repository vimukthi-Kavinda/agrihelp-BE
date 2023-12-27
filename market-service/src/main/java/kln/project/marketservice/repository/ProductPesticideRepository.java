package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.ProductPesticide;
import kln.project.marketservice.model.entity.ProductPesticideId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductPesticideRepository extends JpaRepository<ProductPesticide, ProductPesticideId>, JpaSpecificationExecutor<ProductPesticide> {
}
package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.ProductPesticide;
import kln.project.customerservice.model.entity.ProductPesticideId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductPesticideRepository extends JpaRepository<ProductPesticide, ProductPesticideId>, JpaSpecificationExecutor<ProductPesticide> {
}
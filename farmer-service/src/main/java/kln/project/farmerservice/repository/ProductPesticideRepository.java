package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Disease;
import kln.project.farmerservice.model.entity.ProductPesticide;
import kln.project.farmerservice.model.entity.ProductPesticideId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductPesticideRepository extends JpaRepository<ProductPesticide, ProductPesticideId>, JpaSpecificationExecutor<ProductPesticide> {
    List<ProductPesticide> findByProductpesticideidRecomandeddisease(Disease disease);
}
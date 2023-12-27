package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.Disease;
import kln.project.officerservice.model.entity.ProductPesticide;
import kln.project.officerservice.model.entity.ProductPesticideId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductPesticideRepository extends JpaRepository<ProductPesticide, ProductPesticideId>, JpaSpecificationExecutor<ProductPesticide> {
    List<ProductPesticide> findByProductpesticideidRecomandeddisease(Disease disease);
}
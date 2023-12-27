package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.MarketCropPrice;
import kln.project.officerservice.model.entity.MarketCropPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MarketCropPriceRepository extends JpaRepository<MarketCropPrice, MarketCropPriceId>, JpaSpecificationExecutor<MarketCropPrice> {
}
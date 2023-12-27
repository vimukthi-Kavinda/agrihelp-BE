package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.MarketCropPrice;
import kln.project.shopservice.model.entity.MarketCropPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MarketCropPriceRepository extends JpaRepository<MarketCropPrice, MarketCropPriceId>, JpaSpecificationExecutor<MarketCropPrice> {
}
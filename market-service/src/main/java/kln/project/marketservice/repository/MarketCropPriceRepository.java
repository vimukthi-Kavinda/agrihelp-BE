package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.MarketCropPrice;
import kln.project.marketservice.model.entity.MarketCropPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MarketCropPriceRepository extends JpaRepository<MarketCropPrice, MarketCropPriceId>, JpaSpecificationExecutor<MarketCropPrice> {
}
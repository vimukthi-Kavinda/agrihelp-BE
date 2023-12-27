package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Crop;
import kln.project.farmerservice.model.entity.Market;
import kln.project.farmerservice.model.entity.MarketCropPrice;
import kln.project.farmerservice.model.entity.MarketCropPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MarketCropPriceRepository extends JpaRepository<MarketCropPrice, MarketCropPriceId>, JpaSpecificationExecutor<MarketCropPrice> {
    List<MarketCropPrice> findByMarketcroppriceidCrop(Crop crop);

    List<MarketCropPrice> findByMarketcroppriceidMarket(Market market);
}
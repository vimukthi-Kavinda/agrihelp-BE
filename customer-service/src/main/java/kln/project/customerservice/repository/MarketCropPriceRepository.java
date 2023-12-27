package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.Crop;
import kln.project.customerservice.model.entity.Market;
import kln.project.customerservice.model.entity.MarketCropPrice;
import kln.project.customerservice.model.entity.MarketCropPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MarketCropPriceRepository extends JpaRepository<MarketCropPrice, MarketCropPriceId>, JpaSpecificationExecutor<MarketCropPrice> {
    List<MarketCropPrice> findByMarketcroppriceidCrop(Crop crop);

    List<MarketCropPrice> findByMarketcroppriceidMarket(Market market);
}
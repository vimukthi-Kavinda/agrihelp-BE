package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MarketRepository extends JpaRepository<Market, String>, JpaSpecificationExecutor<Market> {
    Market findByMarketname(String marketName);
}
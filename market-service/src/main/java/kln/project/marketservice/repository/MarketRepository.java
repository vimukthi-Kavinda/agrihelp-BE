package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MarketRepository extends JpaRepository<Market, String>, JpaSpecificationExecutor<Market> {
    Market findByMarketname(String marketName);
}
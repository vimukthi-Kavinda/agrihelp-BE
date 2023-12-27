package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MarketRepository extends JpaRepository<Market, String>, JpaSpecificationExecutor<Market> {
}
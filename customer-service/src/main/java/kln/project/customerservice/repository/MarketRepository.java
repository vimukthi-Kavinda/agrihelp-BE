package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MarketRepository extends JpaRepository<Market, String>, JpaSpecificationExecutor<Market> {
}
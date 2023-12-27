package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.TempFarmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TempFarmerRepository extends JpaRepository<TempFarmer, String>, JpaSpecificationExecutor<TempFarmer> {
}
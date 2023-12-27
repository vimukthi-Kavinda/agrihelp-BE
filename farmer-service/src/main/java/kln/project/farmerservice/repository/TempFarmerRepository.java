package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.TempFarmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TempFarmerRepository extends JpaRepository<TempFarmer, String>, JpaSpecificationExecutor<TempFarmer> {
}
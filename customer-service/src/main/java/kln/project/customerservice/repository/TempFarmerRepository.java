package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.TempFarmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TempFarmerRepository extends JpaRepository<TempFarmer, String>, JpaSpecificationExecutor<TempFarmer> {
}
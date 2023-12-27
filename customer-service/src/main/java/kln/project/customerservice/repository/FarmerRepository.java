package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FarmerRepository extends JpaRepository<Farmer, String>, JpaSpecificationExecutor<Farmer> {
}
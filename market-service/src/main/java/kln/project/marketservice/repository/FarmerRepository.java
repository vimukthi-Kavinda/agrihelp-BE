package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FarmerRepository extends JpaRepository<Farmer, String>, JpaSpecificationExecutor<Farmer> {
}
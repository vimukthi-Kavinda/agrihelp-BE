package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DistrictRepository extends JpaRepository<District, String>, JpaSpecificationExecutor<District> {
}
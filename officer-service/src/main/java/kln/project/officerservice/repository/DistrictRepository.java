package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DistrictRepository extends JpaRepository<District, String>, JpaSpecificationExecutor<District> {
}
package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProvinceRepository extends JpaRepository<Province, String>, JpaSpecificationExecutor<Province> {
}
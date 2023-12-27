package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProvinceRepository extends JpaRepository<Province, String>, JpaSpecificationExecutor<Province> {
}
package kln.project.componentservice.repository;

import kln.project.componentservice.model.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DiseaseRepository extends JpaRepository<Disease, String>, JpaSpecificationExecutor<Disease> {
}
package kln.project.componentservice.repository;

import kln.project.componentservice.model.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OfficerRepository extends JpaRepository<Officer, String>, JpaSpecificationExecutor<Officer> {
}
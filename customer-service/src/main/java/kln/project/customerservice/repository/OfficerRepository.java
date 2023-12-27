package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OfficerRepository extends JpaRepository<Officer, String>, JpaSpecificationExecutor<Officer> {
}
package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OfficerRepository extends JpaRepository<Officer, String>, JpaSpecificationExecutor<Officer> {
    Officer findByUsername(String username);
}
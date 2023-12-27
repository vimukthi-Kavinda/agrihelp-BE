package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.Paths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PathsRepository extends JpaRepository<Paths, String>, JpaSpecificationExecutor<Paths> {
}
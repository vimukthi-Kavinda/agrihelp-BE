package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Paths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PathsRepository extends JpaRepository<Paths, String>, JpaSpecificationExecutor<Paths> {
    Paths findByFilepathname(String postpath);
}
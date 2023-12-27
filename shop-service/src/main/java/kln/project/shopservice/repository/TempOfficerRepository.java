package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.TempOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TempOfficerRepository extends JpaRepository<TempOfficer,String>, JpaSpecificationExecutor<TempOfficer> {
    TempOfficer findByUsername(String username);
}

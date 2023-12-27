package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.TempOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TempOfficerRepository extends JpaRepository<TempOfficer,String>, JpaSpecificationExecutor<TempOfficer> {
    TempOfficer findByUsername(String username);
}

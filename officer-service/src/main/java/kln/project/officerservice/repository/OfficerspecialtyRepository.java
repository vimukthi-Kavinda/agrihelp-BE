package kln.project.officerservice.repository;


import kln.project.officerservice.model.entity.Officerspecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OfficerspecialtyRepository extends JpaRepository<Officerspecialty,String>, JpaSpecificationExecutor<Officerspecialty> {
}

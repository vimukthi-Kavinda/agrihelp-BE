package kln.project.customerservice.repository;


import kln.project.customerservice.model.entity.Officerspecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OfficerspecialtyRepository extends JpaRepository<Officerspecialty,String>, JpaSpecificationExecutor<Officerspecialty> {
}

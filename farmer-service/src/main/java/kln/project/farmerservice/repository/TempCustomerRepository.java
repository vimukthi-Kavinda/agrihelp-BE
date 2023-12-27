package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.TempCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TempCustomerRepository extends JpaRepository<TempCustomer, String>, JpaSpecificationExecutor<TempCustomer> {
}

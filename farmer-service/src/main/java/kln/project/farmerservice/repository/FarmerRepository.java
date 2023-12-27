package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FarmerRepository extends JpaRepository<Farmer, String>, JpaSpecificationExecutor<Farmer> {

    // this query method is equal to the
    //  select * from farmer where username='testusername';
    // sql query
    Farmer findByUsername(String username);

}
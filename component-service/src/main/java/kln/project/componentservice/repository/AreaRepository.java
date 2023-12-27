package kln.project.componentservice.repository;

import kln.project.componentservice.model.entity.Area;
import kln.project.componentservice.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, String>, JpaSpecificationExecutor<Area> {
    List<Area> findByDistrict(District district);
}
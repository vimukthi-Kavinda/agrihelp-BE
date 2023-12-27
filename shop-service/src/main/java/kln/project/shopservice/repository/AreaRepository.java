package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AreaRepository extends JpaRepository<Area, String>, JpaSpecificationExecutor<Area> {
}
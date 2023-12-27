package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.PostTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostTypesRepository extends JpaRepository<PostTypes, String>, JpaSpecificationExecutor<PostTypes> {
}
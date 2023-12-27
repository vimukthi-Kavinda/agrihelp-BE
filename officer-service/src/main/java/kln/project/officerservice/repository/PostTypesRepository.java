package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.PostTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostTypesRepository extends JpaRepository<PostTypes, String>, JpaSpecificationExecutor<PostTypes> {
}
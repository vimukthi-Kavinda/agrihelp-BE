package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.TempPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TempPostRepository extends JpaRepository<TempPost, Integer>, JpaSpecificationExecutor<TempPost> {
}
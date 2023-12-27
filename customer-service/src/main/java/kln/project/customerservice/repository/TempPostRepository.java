package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.TempPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TempPostRepository extends JpaRepository<TempPost, Integer>, JpaSpecificationExecutor<TempPost> {
}
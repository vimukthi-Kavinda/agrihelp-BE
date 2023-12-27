package kln.project.componentservice.repository;

import kln.project.componentservice.model.entity.PostReact;
import kln.project.componentservice.model.entity.PostReactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostReactRepository extends JpaRepository<PostReact, PostReactId>, JpaSpecificationExecutor<PostReact> {
}
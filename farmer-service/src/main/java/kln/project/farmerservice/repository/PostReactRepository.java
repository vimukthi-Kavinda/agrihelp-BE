package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Post;
import kln.project.farmerservice.model.entity.PostReact;
import kln.project.farmerservice.model.entity.PostReactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PostReactRepository extends JpaRepository<PostReact, PostReactId>, JpaSpecificationExecutor<PostReact> {
    long countByPostreactidPost(Post post);

    long countByPostreactidPostAndReact(Post post, String number);

    List<PostReact> findByPostreactidPost(Post pst);
}
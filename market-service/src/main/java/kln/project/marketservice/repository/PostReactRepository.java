package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.Post;
import kln.project.marketservice.model.entity.PostReact;
import kln.project.marketservice.model.entity.PostReactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostReactRepository extends JpaRepository<PostReact, PostReactId>, JpaSpecificationExecutor<PostReact> {
    long countByPostreactidPost(Post post);

    long countByPostreactidPostAndReact(Post post, String number);
}
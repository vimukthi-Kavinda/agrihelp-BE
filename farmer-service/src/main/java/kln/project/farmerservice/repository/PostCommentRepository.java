package kln.project.farmerservice.repository;

import kln.project.farmerservice.model.entity.Post;
import kln.project.farmerservice.model.entity.PostComment;
import kln.project.farmerservice.model.entity.PostCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, PostCommentId>, JpaSpecificationExecutor<PostComment> {
    List<PostComment> findByPostcommentidPost(Post bank);
}
package kln.project.officerservice.repository;

import kln.project.officerservice.model.entity.Post;
import kln.project.officerservice.model.entity.PostComment;
import kln.project.officerservice.model.entity.PostCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, PostCommentId>, JpaSpecificationExecutor<PostComment> {
    List<PostComment> findByPostcommentidPost(Post p);
}
package kln.project.shopservice.repository;

import kln.project.shopservice.model.entity.PostComment;
import kln.project.shopservice.model.entity.PostCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostCommentRepository extends JpaRepository<PostComment, PostCommentId>, JpaSpecificationExecutor<PostComment> {
}
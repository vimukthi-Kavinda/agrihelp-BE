package kln.project.componentservice.repository;

import kln.project.componentservice.model.entity.PostComment;
import kln.project.componentservice.model.entity.PostCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostCommentRepository extends JpaRepository<PostComment, PostCommentId>, JpaSpecificationExecutor<PostComment> {
}
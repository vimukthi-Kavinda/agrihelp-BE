package kln.project.customerservice.repository;

import kln.project.customerservice.model.entity.PostComment;
import kln.project.customerservice.model.entity.PostCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostCommentRepository extends JpaRepository<PostComment, PostCommentId>, JpaSpecificationExecutor<PostComment> {
}
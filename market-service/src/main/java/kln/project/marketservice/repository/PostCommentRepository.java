package kln.project.marketservice.repository;

import kln.project.marketservice.model.entity.PostComment;
import kln.project.marketservice.model.entity.PostCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostCommentRepository extends JpaRepository<PostComment, PostCommentId>, JpaSpecificationExecutor<PostComment> {
}
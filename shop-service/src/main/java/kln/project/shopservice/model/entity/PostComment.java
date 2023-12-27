package kln.project.shopservice.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "post_comment")
public class PostComment implements Serializable {

    @EmbeddedId
    PostCommentId postcommentid;

    @Column(name = "postedtime")
    private Timestamp postedTime;

    // Constructors, getters, and setters
}


package kln.project.customerservice.model.entity;


import jakarta.persistence.*;
import kln.project.customerservice.model.entity.PostCommentId;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "post_comment")
public class PostComment implements Serializable {

    @EmbeddedId
    PostCommentId postCommentId;

    @Column(name = "postedtime")
    private Timestamp postedTime;

    // Constructors, getters, and setters
}


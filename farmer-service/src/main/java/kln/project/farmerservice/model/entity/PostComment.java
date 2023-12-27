package kln.project.farmerservice.model.entity;


import jakarta.persistence.*;
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


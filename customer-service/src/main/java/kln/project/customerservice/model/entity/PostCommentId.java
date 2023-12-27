package kln.project.customerservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "postid", referencedColumnName = "postid")
    private Post post;


    @Column(name = "userid", length = 10)
    private String userId;

    @Column(name = "post_comment", length = 150)
    private String postComment;

}

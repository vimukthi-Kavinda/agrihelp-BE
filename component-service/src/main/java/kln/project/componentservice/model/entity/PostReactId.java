package kln.project.componentservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReactId implements Serializable {


    @ManyToOne
    @JoinColumn(name = "postid", referencedColumnName = "postid")
    private Post post;

    @Column(name = "userid", length = 10)
    private String userId;

}

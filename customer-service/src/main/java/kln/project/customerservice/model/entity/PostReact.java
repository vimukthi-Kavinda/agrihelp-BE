package kln.project.customerservice.model.entity;


import jakarta.persistence.*;
import kln.project.customerservice.model.entity.PostReactId;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "post_react")
public class PostReact implements Serializable {


    @EmbeddedId
    PostReactId postReactId;

    @Column(name = "react",length = 1)
    private String react;

    // Constructors, getters, and setters
}


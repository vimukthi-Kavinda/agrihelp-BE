package kln.project.shopservice.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "post_react")
public class PostReact implements Serializable {


    @EmbeddedId
    PostReactId postreactid;

    @Column(name = "react",length = 1)
    private String react;

    // Constructors, getters, and setters
}


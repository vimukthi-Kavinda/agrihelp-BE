package kln.project.officerservice.model.entity;


import jakarta.persistence.*;
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


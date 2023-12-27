package kln.project.farmerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "posttypes")
public class PostTypes {
    @Id
    @Column(name = "post_type_code", length = 10)
    private String posttypecode;

    @Column(name="description",length = 30)
    private String description;

    // Constructors, getters, and setters
}


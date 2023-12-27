package kln.project.componentservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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


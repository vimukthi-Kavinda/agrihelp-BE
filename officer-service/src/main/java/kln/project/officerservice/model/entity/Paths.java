package kln.project.officerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "paths")
public class Paths {
    @Id
    @Column(name = "file_path_name", length = 10)
    private String filepathname;

    @Column(name="path",length = 30)
    private String path;

    // Constructors, getters, and setters
}


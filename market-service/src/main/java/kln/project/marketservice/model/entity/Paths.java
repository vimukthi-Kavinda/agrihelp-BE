package kln.project.marketservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "paths")
public class Paths {
    @Id
    @Column(name = "file_path_name", length = 10)
    private String filePathName;

    @Column(name="path",length = 30)
    private String path;

    // Constructors, getters, and setters
}


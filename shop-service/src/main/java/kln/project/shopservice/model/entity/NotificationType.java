package kln.project.shopservice.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="")
@Data
public class NotificationType {

    @Id
    @Column(name="typeid")
    private String typeid;

    @Column(name = "description")
    private String description;


}

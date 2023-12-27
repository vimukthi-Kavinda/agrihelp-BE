package kln.project.marketservice.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "officerspecialty")
public class Officerspecialty {

    @Id
    @Column(name = "specialtycode")
    private String specialtycode;

    @Column(name = "specialtydesc")
    private String specialtydesc;
}

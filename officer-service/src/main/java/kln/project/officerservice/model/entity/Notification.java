package kln.project.officerservice.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="notifications")
@Data
public class Notification {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "endusername")
    private String endusername;

    @Column(name = "message")
    private String message;

    @Column(name="originusername")
    private String originusername;

    @Column(name="postedtime")
    private Timestamp postedtime;

    @ManyToOne
    @JoinColumn(name = "notification_type",referencedColumnName = "typeid")
    private NotificationType notificationtype;




}

package kln.project.farmerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "temp_post")
public class TempPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid")
    private Integer postId;

    @Column(name = "post_subject", length = 100)
    private String postSubject;

    @Column(name = "post_desc", length = 500)
    private String postDesc;

    @Column(name = "img_name", length = 100)
    private String imgName;

    @Column(name = "posted_user", length = 10)
    private String postedUser;

    @Column(name = "posted_time")
    private Date postedTime;

    @ManyToOne
    @JoinColumn(name = "post_type", referencedColumnName = "post_type_code")
    private PostTypes postType;

    @ManyToOne
    @JoinColumn(name = "cropcode", referencedColumnName = "cropcode")
    private Crop cropcode;
    // Constructors, getters, and setters
}


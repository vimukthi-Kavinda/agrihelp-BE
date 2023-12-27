package kln.project.officerservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "postid")
    private Integer postId;

    @Column(name = "post_subject", length = 100)
    private String postsubject;

    @Column(name = "post_desc", length = 500)
    private String postdesc;

    @Column(name = "img_name", length = 100)
    private String imgName;

    //user name
    @Column(name = "posted_user", length = 20)
    private String posteduser;

    @Column(name = "posted_time")
    private Date postedtime;

    @ManyToOne
    @JoinColumn(name = "post_accepted_officer", referencedColumnName = "officer_id")
    private Officer postAcceptedOfficer;

    @ManyToOne
    @JoinColumn(name = "post_type", referencedColumnName = "post_type_code")
    private PostTypes posttype;

    @ManyToOne
    @JoinColumn(name = "cropcode", referencedColumnName = "cropcode")
    private Crop cropcode;

    // Constructors, getters, and setters
}


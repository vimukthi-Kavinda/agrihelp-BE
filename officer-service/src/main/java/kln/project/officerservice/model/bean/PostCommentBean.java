package kln.project.officerservice.model.bean;


import lombok.Data;

@Data
public class PostCommentBean {

    private String postid;
    private String comment;
    private String userid;
    private String postedDate;
}

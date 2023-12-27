package kln.project.farmerservice.model.bean;


import lombok.Data;

@Data
public class PostCommentBean {

    private String postid;
    private String userid;
    private String comment;
    private String postedDate;

}

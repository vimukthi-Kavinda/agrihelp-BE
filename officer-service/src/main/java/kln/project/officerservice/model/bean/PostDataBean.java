package kln.project.officerservice.model.bean;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostDataBean {
    private Integer postId;
    private String postSubject;
    private String postDesc;
    private String postedTime;
    private String posteduser;
    private String posttype;

    private String acceptedUser;
    private byte[] image;
    private String cropcode;

    private String imageName;
    private String imageData; // Base64-encoded image data


    //

    private String likCount;
    private String disLikCount;

    private String isReacted;//1liked 0disliked -1 not reacted

    private List<CommentDatabean> cmntlist;
}

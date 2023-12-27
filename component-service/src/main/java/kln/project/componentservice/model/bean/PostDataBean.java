package kln.project.componentservice.model.bean;


import lombok.Data;

@Data
public class PostDataBean {
    private Integer postId;
    private String postSubject;
    private String postDesc;
    private String postedTime;
    private String posteduser;

    private String acceptedUser;
    private byte[] image;
    private String cropcode;
}

package kln.project.customerservice.model.bean;


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

    private String imageName;
    private String imageData;
}

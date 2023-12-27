package kln.project.mainservice.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBean {
    private String client_ip;
    private String token;
    private String userrole;
    private String username;
    private int userlevel;
    private Object requestBody;
    private int page;
    private int size;
    private String[] sort;
    private boolean search;
    private byte[] multipartFile;
    private String fileNm;
    private long fileSize;




}

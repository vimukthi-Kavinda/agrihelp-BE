package kln.project.officerservice.service;

import kln.project.officerservice.model.bean.DataTableBean;
import kln.project.officerservice.model.bean.RequestBean;
import kln.project.officerservice.model.bean.ResponseBean;

public interface OfficerPostService {
    ResponseBean uploadPost(byte[] fileContent, String fileName, long fileSize, RequestBean requestBean,  String un) throws Exception;

    ResponseBean deletepost(String postid,String un, ResponseBean responseBean);


}

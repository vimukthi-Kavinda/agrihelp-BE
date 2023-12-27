package kln.project.farmerservice.service;

import kln.project.farmerservice.model.bean.DataTableBean;
import kln.project.farmerservice.model.bean.RequestBean;
import kln.project.farmerservice.model.bean.ResponseBean;

public interface FarmerPostService {
    ResponseBean uploadPost(byte[] fileContent, String fileName, long fileSize, RequestBean requestBean,  String un) throws Exception;

    ResponseBean deletepost(String postid, String username,ResponseBean responseBean);


}

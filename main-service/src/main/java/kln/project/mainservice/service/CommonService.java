package kln.project.mainservice.service;

import kln.project.mainservice.bean.ResponseBean;

public interface CommonService {
    ResponseBean getResponse(String url, String clientIP, String userrole, String username, Object requestBody, String requestID) throws Exception;
    ResponseBean getResponse(String url, String clientIP, String userrole, String username, Object requestBody, int page, int size, String[] sort, boolean search, String requestID) throws Exception;

    ResponseBean getResponse(String service_url, String clientIp, String userrole, String username,String postType,String postsubject,String postdesc,  String requestID, byte[] fileContent,long fileSize,String fileName,String cropcode);

}

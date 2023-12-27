package kln.project.mainservice.service.Impl;

import com.google.gson.Gson;
import kln.project.mainservice.bean.PostBean;
import kln.project.mainservice.bean.RequestBean;
import kln.project.mainservice.bean.ResponseBean;
import kln.project.mainservice.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @Autowired
    private Environment env;
    @Override
    public ResponseBean getResponse(String url, String clientIP, String userrole, String username, Object requestBody, String requestID) throws Exception {
        ResponseBean responseObj;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("requestid", requestID);

        RequestBean requestBean = new RequestBean();
        requestBean.setClient_ip(clientIP);
        requestBean.setUserrole(userrole);
        requestBean.setUsername(username);
        requestBean.setRequestBody(requestBody);

        String json = new Gson().toJson(requestBean);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        responseObj = restTemplate.postForObject(url, requestEntity, ResponseBean.class);
        return responseObj;
    }

    @Override
    public ResponseBean getResponse(String url, String clientIP, String userrole, String username, Object requestBody, int page, int size, String[] sort, boolean search, String requestID) throws Exception {
        ResponseBean responseObj;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("requestid", requestID);

        RequestBean requestBean = new RequestBean();
        requestBean.setClient_ip(clientIP);
        requestBean.setUserrole(userrole);
        requestBean.setUsername(username);
        requestBean.setRequestBody(requestBody);
        requestBean.setPage(page);
        requestBean.setSize(size);
        requestBean.setSort(sort);
        requestBean.setSearch(search);

        String json = new Gson().toJson(requestBean);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        responseObj = restTemplate.postForObject(url, requestEntity, ResponseBean.class);
        return responseObj;
    }

    @Override
    public ResponseBean getResponse(String service_url, String clientIp, String userrole, String username, String postType, String postsubject, String postdesc, String requestID, byte[] fileContent, long fileSize, String fileName,String cropcode) {
        ResponseBean responseObj;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("requestid", requestID);

        RequestBean requestBean = new RequestBean();
        requestBean.setClient_ip(clientIp);
        requestBean.setUserrole(userrole);
        requestBean.setUsername(username);

        PostBean pb=new PostBean(postType,postsubject,postdesc,cropcode);
        requestBean.setRequestBody(pb);

        requestBean.setMultipartFile(fileContent);
        requestBean.setFileSize(fileSize);
        requestBean.setFileNm(fileName);



        String json = new Gson().toJson(requestBean);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
        responseObj = restTemplate.postForObject(service_url, requestEntity, ResponseBean.class);
        return responseObj;
    }
}

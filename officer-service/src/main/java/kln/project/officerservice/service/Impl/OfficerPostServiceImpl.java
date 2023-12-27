package kln.project.officerservice.service.Impl;

import kln.project.officerservice.model.bean.*;
import kln.project.officerservice.model.entity.*;
import kln.project.officerservice.repository.*;
import kln.project.officerservice.service.OfficerPostService;
import kln.project.officerservice.util.Common;
import kln.project.officerservice.util.MessageVarList;
import kln.project.officerservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfficerPostServiceImpl implements OfficerPostService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostRepository postRepository;

    @Autowired
    TempPostRepository tempPostRepository;

    @Autowired
    PostTypesRepository postTypesRepository;
    @Autowired
    private PathsRepository pathsRepository;
    @Autowired
    private CropRepository cropRepository;
    @Autowired
    private PostCommentRepository postCommentRepository;
    @Autowired
    private PostReactRepository postReactRepository;


    @Override
    public ResponseBean uploadPost(byte[] fileContent, String fileName, long fileSize, RequestBean requestBean,  String un) throws Exception {
        ResponseBean responseBean = new ResponseBean();
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        PostBean pb=modelMapper.map(requestBean.getRequestBody(),PostBean.class);

        TempPost p=new TempPost();

        p.setPostDesc(pb.getPostDesc());
        p.setPostSubject(pb.getPostSubject());

        Optional<PostTypes>pt=postTypesRepository.findById(pb.getPostType());
        p.setPostType(pt.get());

        if(pb.getPostType().equals("tip")&&pb.getCropcode()!=null&&!pb.getCropcode().equals("")){
            Optional<Crop>c=cropRepository.findById(pb.getCropcode());
            if(c.isPresent())
                p.setCropcode(c.get());

        }
        p.setPostedUser(un);
        p.setPostedTime(Common.getCurrentDate());
        //p.setImgName(fileName);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        fileName=formattedTimestamp+fileName;
        if(fileContent!=null) {
            p.setImgName(fileName);
            Optional<Paths>pth=pathsRepository.findById("postpath");
            String uri=pth.get().getPath();

            try {
                File file = new File(uri);
                if (!file.exists()) {
                    file.mkdirs();
                }

                String filePath = uri + "/" + fileName;
                FileOutputStream outputStream = new FileOutputStream(filePath);
                outputStream.write(fileContent);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

        }
        tempPostRepository.saveAndFlush(p);
        message= MessageVarList.SUCCESS_ADD;
        responsCode=ResponseCode.RSP_SUCCESS;

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);
        return responseBean;

    }

    @Override
    public ResponseBean deletepost(String postid, String un,ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<Post>p=postRepository.findById(Integer.valueOf(postid));
        if (p.isPresent()){

            Post pst=p.get();
            if(pst.getPosteduser().equals(un)) {

                List<PostComment>pcl=postCommentRepository.findByPostcommentidPost(pst);
                pcl.forEach(pc->{
                    postCommentRepository.delete(pc);
                });

                List<PostReact>prl=postReactRepository.findByPostreactidPost(pst);
                prl.forEach(pr->{
                    postReactRepository.delete(pr);
                });

                postRepository.delete(pst);

                message = MessageVarList.SUCCESS_DELETE;
                responsCode = ResponseCode.RSP_SUCCESS;
            }
            else{
                message="Unauthorized to delete";
            }
            }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);
        return responseBean;
    }



}

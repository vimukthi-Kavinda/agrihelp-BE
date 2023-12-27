package kln.project.farmerservice.service.Impl;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import kln.project.farmerservice.model.bean.*;
import kln.project.farmerservice.model.entity.*;
import kln.project.farmerservice.repository.*;
import kln.project.farmerservice.service.FarmerCommunityService;
import kln.project.farmerservice.util.Common;
import kln.project.farmerservice.util.MessageVarList;
import kln.project.farmerservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class FarmerCommunityServiceImpl implements FarmerCommunityService {
    @Autowired
    PostCommentRepository postCommentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostReactRepository postReactRepository;

    @Autowired
    Common common;

    @Autowired
    PathsRepository pathsRepository;


    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationTypeRepository notificationTypeRepository;





    @Override
    public DataTableBean getnewPosts(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        Page<Post> posts;
        Specification<Post> specification = null;
        PostDataBean postDataBean = modelMapper.map(requestBean.getRequestBody(), PostDataBean.class);



            specification = postSpecification(postDataBean);


        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        posts = postRepository.findAll(specification, paging);

        if (posts != null) {
            dataTableBean.setCount(posts.getTotalElements());
            dataTableBean.setPagecount(posts.getTotalPages());
        }

        posts.forEach((bank) -> {
            PostDataBean dbean = new PostDataBean();
            dbean.setPostId(bank.getPostId());
            dbean.setPostSubject(bank.getPostsubject());
            dbean.setPostDesc(bank.getPostdesc());

            if(bank.getImgname()!=null&&!bank.getImgname().equals("")) {
                try {
                    Paths path=pathsRepository.findByFilepathname("postpath");
                    if(path!=null) {
                        String imgPath = path.getPath();
                        Resource resource = new FileSystemResource(imgPath + bank.getImgname());
                        String imageData = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
                        dbean.setImageName(bank.getImgname());
                        dbean.setImageData(imageData);


                    }
                    // tempDataBean.setImage(getPostImage(temp.getImgName()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            dbean.setPostedTime(common.formatDatetoString(bank.getPostedtime()));
            dbean.setPosteduser(bank.getPosteduser());
            dbean.setAcceptedUser(bank.getPostAcceptedOfficer().getUsername());

            long reactCount1 = postReactRepository.countByPostreactidPostAndReact(bank,"1");
            long reactCount0 = postReactRepository.countByPostreactidPostAndReact(bank,"0");

            Optional<PostReact>pr=postReactRepository.findById(new PostReactId(bank,requestBean.getUsername()));
            if(pr.isPresent())dbean.setIsReacted(pr.get().getReact());
            else dbean.setIsReacted("-1");


            dbean.setDisLikCount(String.valueOf(reactCount0));
            dbean.setLikCount(String.valueOf(reactCount1));

            List<CommentDatabean> cmntlist=new ArrayList<>();


            List<PostComment>pc= postCommentRepository.findByPostcommentidPost(bank);

            for (PostComment p : pc) {
                //if (!p.getPostcommentid().getPost().getPostId().equals(Integer.parseInt(postDataBean.getPostid())))
                //  continue;
                CommentDatabean dbn = new CommentDatabean();
                dbn.setPost(String.valueOf(p.getPostcommentid().getPost().getPostId()));
                dbn.setUser(p.getPostcommentid().getUserId());
                dbn.setComment(p.getPostcommentid().getPostComment());
                dbn.setTime(p.getPostedTime().toString());

                cmntlist.add(dbn);
            }

            dbean.setCmntlist(cmntlist);




            postDataBeans.add(dbean);
        });

        dataTableBean.setList(postDataBeans);

        return dataTableBean;
    }
    private Specification<Post> postSpecification(PostDataBean pb) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            if (pb.getPosteduser() != null && !pb.getPosteduser().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.<String>get("posteduser"), pb.getPosteduser()));
            }
            if (pb.getPostDesc() != null && !pb.getPostDesc().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postdesc"),"%"+ pb.getPostDesc()+"%"));
            }
            if (pb.getPostSubject() != null && !pb.getPostSubject().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postsubject"), "%"+pb.getPostSubject()+"%"));
            }
            Join<Post, PostTypes> psTypJoin = root.join("posttype");
            predicates.add(criteriaBuilder.equal(psTypJoin.get("posttypecode"), "common"));


            if (pb.getPostedTime() != null && !pb.getPostedTime().isEmpty()) {

                Date d= null;
                try {
                    d = common.getCurrentDate();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    d = common.formatStringDateToDate(pb.getPostedTime());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                predicates.add(criteriaBuilder.equal(root.<String>get("postedtime"), d));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }









    @Override
    public DataTableBean gettipPosts(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        Page<Post> posts;
        Specification<Post> specification = null;
        PostDataBean postDataBean = modelMapper.map(requestBean.getRequestBody(), PostDataBean.class);
        String postCategory="tip";
        if (requestBean.isSearch() ) {

            specification = tipPostSpecification(postCategory,postDataBean);
        }else{
            specification = tipPostDefaultSpecification(postCategory);
        }

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        posts = postRepository.findAll(specification, paging);

        if (posts != null) {
            dataTableBean.setCount(posts.getTotalElements());
            dataTableBean.setPagecount(posts.getTotalPages());
        }

        posts.forEach((bank) -> {
            PostDataBean dbean = new PostDataBean();
            dbean.setPostId(bank.getPostId());
            dbean.setPostSubject(bank.getPostsubject());
            dbean.setPostDesc(bank.getPostdesc());

            if(bank.getImgname()!=null&&!bank.getImgname().equals("")) {
                try {
                    Paths path=pathsRepository.findByFilepathname("postpath");
                    if(path!=null) {
                        String imgPath = path.getPath();
                        Resource resource = new FileSystemResource(imgPath + bank.getImgname());
                        String imageData = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
                        dbean.setImageName(bank.getImgname());
                        dbean.setImageData(imageData);


                    }
                    // tempDataBean.setImage(getPostImage(temp.getImgName()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            dbean.setPostedTime(common.formatDatetoString(bank.getPostedtime()));
            dbean.setPosteduser(bank.getPosteduser());
            dbean.setAcceptedUser(bank.getPostAcceptedOfficer().getUsername());
            postDataBeans.add(dbean);
        });

        dataTableBean.setList(postDataBeans);

        return dataTableBean;
    }
    private Specification<Post> tipPostSpecification(String postCategory, PostDataBean pb) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (postCategory != null && !postCategory.isEmpty()) {
                Join<Post, PostTypes> comTypJoin = root.join("posttype");
                predicates.add(criteriaBuilder.equal(comTypJoin.get("posttypecode"), postCategory));
            }

            if (pb.getCropcode() != null && !pb.getCropcode().isEmpty()) {
                Join<Post, Crop> cpTypJoin = root.join("cropcode");
                predicates.add(criteriaBuilder.equal(cpTypJoin.get("cropCode"), pb.getCropcode()));
            }
            if (pb.getPosteduser() != null && !pb.getPosteduser().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.<String>get("posteduser"), pb.getPosteduser()));
            }
            if (pb.getPostDesc() != null && !pb.getPostDesc().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postdesc"),"%"+ pb.getPostDesc()+"%"));
            }
            if (pb.getPostSubject() != null && !pb.getPostSubject().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postsubject"), "%"+pb.getPostSubject()+"%"));
            }

            if (pb.getPostedTime() != null && !pb.getPostedTime().isEmpty()) {

                Date d= null;
                try {
                    d = common.getCurrentDate();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    d = common.formatStringDateToDate(pb.getPostedTime());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                predicates.add(criteriaBuilder.equal(root.<String>get("postedtime"), d));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Post> tipPostDefaultSpecification(String postCategory) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (postCategory != null && !postCategory.isEmpty()) {
                Join<Post, PostTypes> comTypJoin = root.join("posttype");
                predicates.add(criteriaBuilder.equal(comTypJoin.get("posttypecode"), postCategory));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }





    @Override
    public DataTableBean getNewsPosts(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        Page<Post> posts;
        Specification<Post> specification = null;
        PostDataBean postDataBean = modelMapper.map(requestBean.getRequestBody(), PostDataBean.class);
        String postCategory="news";
        if (requestBean.isSearch() ) {

            specification = newsPostSpecification(postCategory,postDataBean);
        }else{
            specification = newsPostDefaultSpecification(postCategory);
        }

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        posts = postRepository.findAll(specification, paging);

        if (posts != null) {
            dataTableBean.setCount(posts.getTotalElements());
            dataTableBean.setPagecount(posts.getTotalPages());
        }

        posts.forEach((bank) -> {
            PostDataBean dbean = new PostDataBean();
            dbean.setPostId(bank.getPostId());
            dbean.setPostSubject(bank.getPostsubject());
            dbean.setPostDesc(bank.getPostdesc());

            if(bank.getImgname()!=null&&!bank.getImgname().equals("")) {
                try {
                    Paths path=pathsRepository.findByFilepathname("postpath");
                    if(path!=null) {
                        String imgPath = path.getPath();
                        Resource resource = new FileSystemResource(imgPath + bank.getImgname());
                        String imageData = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
                        dbean.setImageName(bank.getImgname());
                        dbean.setImageData(imageData);


                    }
                    // tempDataBean.setImage(getPostImage(temp.getImgName()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            dbean.setPostedTime(common.formatDatetoString(bank.getPostedtime()));
            dbean.setPosteduser(bank.getPosteduser());
            dbean.setAcceptedUser(bank.getPostAcceptedOfficer().getUsername());
            postDataBeans.add(dbean);
        });

        dataTableBean.setList(postDataBeans);

        return dataTableBean;
    }

    private Specification<Post> newsPostSpecification(String postCategory, PostDataBean pb) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (postCategory != null && !postCategory.isEmpty()) {
                Join<Post, PostTypes> comTypJoin = root.join("posttype");
                predicates.add(criteriaBuilder.equal(comTypJoin.get("posttypecode"), postCategory));
            }
            if (pb.getPosteduser() != null && !pb.getPosteduser().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.<String>get("posteduser"), pb.getPosteduser()));
            }
            if (pb.getPostDesc() != null && !pb.getPostDesc().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postdesc"),"%"+ pb.getPostDesc()+"%"));
            }
            if (pb.getPostSubject() != null && !pb.getPostSubject().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postsubject"), "%"+pb.getPostSubject()+"%"));
            }

            if (pb.getPostedTime() != null && !pb.getPostedTime().isEmpty()) {

                Date d= null;
                try {
                    d = common.getCurrentDate();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    d = common.formatStringDateToDate(pb.getPostedTime());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                predicates.add(criteriaBuilder.equal(root.<String>get("postedtime"), d));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Post> newsPostDefaultSpecification(String postCategory) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (postCategory != null && !postCategory.isEmpty()) {
                Join<Post, PostTypes> comTypJoin = root.join("posttype");
                predicates.add(criteriaBuilder.equal(comTypJoin.get("posttypecode"), postCategory));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }







    @Override
    public DataTableBean getMyPosts(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        Page<Post> posts;
        Specification<Post> specification = null;

        if (requestBean.isSearch() && requestBean.getRequestBody() != null) {
            PostDataBean postDataBean = modelMapper.map(requestBean.getRequestBody(), PostDataBean.class);

            specification = myPostSpecification(requestBean.getUsername(),postDataBean);
        }else{
            specification = myPostDefaultSpecification(requestBean.getUsername());
        }

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        posts = postRepository.findAll(specification, paging);

        if (posts != null) {
            dataTableBean.setCount(posts.getTotalElements());
            dataTableBean.setPagecount(posts.getTotalPages());
        }

        posts.forEach((bank) -> {
            PostDataBean dbean = new PostDataBean();
            dbean.setPostId(bank.getPostId());
            dbean.setPostSubject(bank.getPostsubject());
            dbean.setPostDesc(bank.getPostdesc());

            if(bank.getImgname()!=null&&!bank.getImgname().equals("")) {
                try {
                    Paths path=pathsRepository.findByFilepathname("postpath");
                    if(path!=null) {
                        String imgPath = path.getPath();
                        Resource resource = new FileSystemResource(imgPath + bank.getImgname());
                        String imageData = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
                        dbean.setImageName(bank.getImgname());
                        dbean.setImageData(imageData);


                    }
                    // tempDataBean.setImage(getPostImage(temp.getImgName()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            dbean.setPostedTime(common.formatDatetoString(bank.getPostedtime()));
            dbean.setPosteduser(requestBean.getUsername());
            dbean.setAcceptedUser(bank.getPostAcceptedOfficer().getUsername());

            Optional<PostReact>pr=postReactRepository.findById(new PostReactId(bank,requestBean.getUsername()));
            if(pr.isPresent())dbean.setIsReacted(pr.get().getReact());
            else dbean.setIsReacted("-1");

            long reactCount1 = postReactRepository.countByPostreactidPostAndReact(bank,"1");
            long reactCount0 = postReactRepository.countByPostreactidPostAndReact(bank,"0");

            dbean.setDisLikCount(String.valueOf(reactCount0));
            dbean.setLikCount(String.valueOf(reactCount1));

            List<CommentDatabean> cmntlist=new ArrayList<>();


            List<PostComment>pc= postCommentRepository.findByPostcommentidPost(bank);

            for (PostComment p : pc) {
                //if (!p.getPostcommentid().getPost().getPostId().equals(Integer.parseInt(postDataBean.getPostid())))
                //  continue;
                CommentDatabean dbn = new CommentDatabean();
                dbn.setPost(String.valueOf(p.getPostcommentid().getPost().getPostId()));
                dbn.setUser(p.getPostcommentid().getUserId());
                dbn.setComment(p.getPostcommentid().getPostComment());
                dbn.setTime(p.getPostedTime().toString());

                cmntlist.add(dbn);
            }

            dbean.setCmntlist(cmntlist);



            postDataBeans.add(dbean);
        });

        dataTableBean.setList(postDataBeans);

        return dataTableBean;
    }
    private Specification<Post> myPostDefaultSpecification(String username) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            predicates.add(criteriaBuilder.equal(root.<String>get("posteduser"), username));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
    private Specification<Post> myPostSpecification(String username,PostDataBean pb) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            predicates.add(criteriaBuilder.equal(root.<String>get("posteduser"), username));

            if (pb.getPostDesc() != null && !pb.getPostDesc().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postdesc"),"%"+ pb.getPostDesc()+"%"));
            }
            if (pb.getPostSubject() != null && !pb.getPostSubject().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postsubject"), "%"+pb.getPostSubject()+"%"));
            }

            if (pb.getPostedTime() != null && !pb.getPostedTime().isEmpty()) {

                Date d= null;
                try {
                    d = common.getCurrentDate();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    d = common.formatStringDateToDate(pb.getPostedTime());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                predicates.add(criteriaBuilder.equal(root.<String>get("postedtime"), d));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }




    @Override
    public ResponseBean deletePostComment( RequestBean requestBean, String username, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        PostCommentBean pcb=modelMapper.map(requestBean.getRequestBody(), PostCommentBean.class);
String postid=pcb.getPostid();
String comment=pcb.getComment();

        Integer pid = Integer.parseInt(postid);
        Optional<Post> po = postRepository.findById(pid);
        if (po.isPresent()) {

            Optional<PostComment> poc = postCommentRepository.findById(new PostCommentId(po.get(), username, comment));
            if (poc.isPresent()) {
                if(poc.get().getPostcommentid().getUserId().equals(requestBean.getUsername()))
                { postCommentRepository.delete(poc.get());
                responsCode = ResponseCode.RSP_SUCCESS;
                message = MessageVarList.SUCCESS_DELETE;}
                else{
                    message="unauthorized to delete othres";
                }
            } else {
                message="Post"+MessageVarList.COMMON_NOT_FOUND;
            }
        }else{
            message="Post"+MessageVarList.COMMON_NOT_FOUND;

        }

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;

    }



    @Override
    public ResponseBean addPostComment(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        PostCommentBean dataBean = modelMapper.map(requestBean.getRequestBody(), PostCommentBean.class);

        String username=requestBean.getUsername();
        if(dataBean.getPostid()==null||dataBean.getPostid().equals("")){
            message=MessageVarList.POST_ID_NULL;
        }else if(dataBean.getComment()==null){
            message=MessageVarList.COMMENT_NULL;
        }else{
            Integer pid = Integer.parseInt(dataBean.getPostid());
            Optional<Post> po = postRepository.findById(pid);
            if (po.isPresent()) {
                Post pst=po.get();
                Optional<PostComment> poc = postCommentRepository.findById(new PostCommentId(pst, username, dataBean.getComment()));
                if (!poc.isPresent()) {
                   Timestamp t= Timestamp.from(ZonedDateTime.now().toInstant());

                    PostComment p=new PostComment();
                    p.setPostcommentid(new PostCommentId(pst, username, dataBean.getComment()));
                    p.setPostedTime(t);
                    postCommentRepository.saveAndFlush(p);

                    //create notification
                    if(!pst.getPosteduser().equals(username)) {
                        Notification n = new Notification();

                        n.setOriginusername(username);
                        n.setEndusername(pst.getPosteduser());
                        n.setMessage(username + MessageVarList.POST_COMMENTING_NOTIFICATION + pst.getPostsubject());

                        n.setPostedtime(t);
                        Optional<NotificationType> nt = notificationTypeRepository.findById("CMNT");
                        if (nt.isPresent()) n.setNotificationtype(nt.get());
                        notificationRepository.saveAndFlush(n);
                    }
                    responsCode = ResponseCode.RSP_SUCCESS;
                    message = MessageVarList.SUCCESS_ADD;
                } else {
                    message="Post comment"+MessageVarList.COMMON_ALREADY_EXIST;
                }
            }else{
                message="Post"+MessageVarList.COMMON_NOT_FOUND;

            }


        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }

    @Override
    public ResponseBean reactpost(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        PostReactBean dataBean = modelMapper.map(requestBean.getRequestBody(), PostReactBean.class);



        if(dataBean.getPostid()==null||dataBean.getPostid().equals("")){
            message=MessageVarList.POST_ID_NULL;
        }else if(dataBean.getReact()==null){
            message=MessageVarList.REACT_NULL;
        }
       /* else if(!dataBean.getReact().equals("1")&&!dataBean.getReact().equals("0")){
            message=MessageVarList.REACT_INVALID;
        }*/
        else if (!Common.reactList.contains(dataBean.getReact())) {
            message = MessageVarList.REACT_INVALID;
        }
        else{
            Integer pid = Integer.parseInt(dataBean.getPostid());
            Optional<Post> po = postRepository.findById(pid);
            if (po.isPresent()) {
                Optional<PostReact> por = postReactRepository.findById(new PostReactId(po.get(), requestBean.getUsername()));
                PostReact p=null;
                if (!por.isPresent()) {
                    p = new PostReact();
                }else{
                    p=por.get();
                }
                p.setPostreactid(new PostReactId(po.get(), requestBean.getUsername()));
                p.setReact(dataBean.getReact());

                postReactRepository.saveAndFlush(p);

                responsCode = ResponseCode.RSP_SUCCESS;
                message = MessageVarList.SUCCESS_ADD;

            }else{
                message="Post"+MessageVarList.COMMON_NOT_FOUND;

            }


        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }





    private Path foundfile = null;
    private byte[] getPostImage(String imgName) throws IOException {
        byte[] img=null;

        Resource resource = null;
        Optional<Paths> path=pathsRepository.findById("postimgpath");
        if(path.isPresent()){
            String imgPath=path.get().getPath();
            Files.list(java.nio.file.Paths.get(imgPath)).forEach(file -> {
                if (file.getFileName().toString().startsWith(imgName)) {
                    foundfile = file;
                }

            });
            if (foundfile != null) {
                resource = new UrlResource(foundfile.toUri());
            }
            img= Files.readAllBytes(resource.getFile().toPath());

        }
        return img;
    }



    @Override
    public DataTableBean getPostReactsComments(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        Page<PostComment> posts;
        Specification<PostComment> specification = null;

        if (requestBean.getRequestBody() != null) {
            PostCommentBean postDataBean = modelMapper.map(requestBean.getRequestBody(), PostCommentBean.class);

            Optional<Post>p=postRepository.findById(Integer.parseInt(postDataBean.getPostid()));
            if(p.isPresent()) {
                try {

                    //specification = myPostSpecification(po);

                    List<Sort.Order> orders = common.getSort(requestBean.getSort());

                    Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

                    posts = postCommentRepository.findAll(specification, paging);

                    for (PostComment bank : posts) {
                        if (!bank.getPostcommentid().getPost().getPostId().equals(Integer.parseInt(postDataBean.getPostid())))
                            continue;
                        PostCommentBean dbean = new PostCommentBean();
                        dbean.setPostid(String.valueOf(bank.getPostcommentid().getPost().getPostId()));
                        dbean.setUserid(bank.getPostcommentid().getUserId());
                        dbean.setComment(bank.getPostcommentid().getPostComment());


                        dbean.setPostedDate(bank.getPostedTime().toString());


                        postDataBeans.add(dbean);
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                long reactCount1 = postReactRepository.countByPostreactidPostAndReact(p.get(),"1");
                long reactCount0 = postReactRepository.countByPostreactidPostAndReact(p.get(),"0");

                Optional<PostReact>pst=postReactRepository.findById(new PostReactId(p.get(), requestBean.getUsername()));
                dataTableBean.setReacted(pst.isPresent());
                dataTableBean.setPositiveCount(String.valueOf(reactCount1));
                dataTableBean.setNegativeCount(String.valueOf(reactCount0));
                dataTableBean.setUserreact(pst.get().getReact());


            }



        }







        dataTableBean.setList(postDataBeans);

        return dataTableBean;
    }




    @Override
    public DataTableBean getFarmerProductNotification(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Optional<NotificationType> nt = notificationTypeRepository.findById("PROD");

        if (nt.isPresent()) {
            List<Notification>nl=notificationRepository.findTop10ByNotificationtypeOrderByPostedtimeDesc(nt.get());

            nl.forEach((n) -> {
                NotificationDataBean ndb = new NotificationDataBean();
                ndb.setMessage(n.getMessage());
                ndb.setOriginusername(n.getOriginusername());
                ndb.setDate(dateFormat.format(n.getPostedtime()));
                postDataBeans.add(ndb);
            });
        }
        dataTableBean.setList(postDataBeans);
        return dataTableBean;



    }

    @Override
    public DataTableBean getFarmerNotification(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<String> endusers = Arrays.asList("all", requestBean.getUsername());
        Optional<NotificationType> nt = notificationTypeRepository.findById("TIPS");
        Optional<NotificationType> nt1 = notificationTypeRepository.findById("CMNT");

        if (nt.isPresent()&&nt1.isPresent()) {

            List<NotificationType> ntyps = Arrays.asList(nt.get(), nt1.get());


            List<Notification>nl=notificationRepository.findTop15ByNotificationtypeInAndOriginusernameNotAndEndusernameInOrderByPostedtimeDesc(ntyps,requestBean.getUsername(),endusers);
            nl.forEach((n) -> {
                NotificationDataBean ndb = new NotificationDataBean();
                ndb.setMessage(n.getMessage());
                ndb.setOriginusername(n.getOriginusername());
                ndb.setDate(dateFormat.format(n.getPostedtime()));
                postDataBeans.add(ndb);
            });
        }
        dataTableBean.setList(postDataBeans);
        return dataTableBean;



    }

    @Override
    public DataTableBean getFarmerPlagueNotification(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Optional<NotificationType> nt = notificationTypeRepository.findById("PLAG");

        if (nt.isPresent()) {
            List<Notification>nl=notificationRepository.findTop10ByNotificationtypeOrderByPostedtimeDesc(nt.get());

            nl.forEach((n) -> {
                NotificationDataBean ndb = new NotificationDataBean();
                ndb.setMessage(n.getMessage());
                ndb.setOriginusername(n.getOriginusername());
                ndb.setDate(dateFormat.format(n.getPostedtime()));
                postDataBeans.add(ndb);
            });
        }
        dataTableBean.setList(postDataBeans);
        return dataTableBean;



    }
}

package kln.project.officerservice.service.Impl;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import kln.project.officerservice.model.bean.*;
import kln.project.officerservice.model.entity.*;
import kln.project.officerservice.repository.*;
import kln.project.officerservice.service.OfficerCommunityService;
import kln.project.officerservice.util.Common;
import kln.project.officerservice.util.MessageVarList;
import kln.project.officerservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
public class OfficerCommunityServiceImpl implements OfficerCommunityService {

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
    public ResponseBean deletePostComment(RequestBean requestBean, String username, ResponseBean responseBean) {
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


                postCommentRepository.delete(poc.get());
                responsCode = ResponseCode.RSP_SUCCESS;
                message = MessageVarList.SUCCESS_DELETE;
            } else {
                message = "Post" + MessageVarList.COMMON_NOT_FOUND;
            }
        } else {
            message = "Post" + MessageVarList.COMMON_NOT_FOUND;

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

        Timestamp t=Timestamp.from(ZonedDateTime.now().toInstant());
        if (dataBean.getPostid() == null || dataBean.getPostid().equals("")) {
            message = MessageVarList.POST_ID_NULL;
        } else if (dataBean.getComment() == null) {
            message = MessageVarList.COMMENT_NULL;
        } else {
            Integer pid = Integer.parseInt(dataBean.getPostid());
            Optional<Post> po = postRepository.findById(pid);
            if (po.isPresent()) {
                Post pst= po.get();
                String username=requestBean.getUsername();
                Optional<PostComment> poc = postCommentRepository.findById(new PostCommentId(pst, username, dataBean.getComment()));
                if (!poc.isPresent()) {
                    PostComment p = new PostComment();
                    p.setPostcommentid(new PostCommentId(pst, username, dataBean.getComment()));
                    p.setPostedTime(t);
                    postCommentRepository.saveAndFlush(p);

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
                    message = "Post comment" + MessageVarList.COMMON_ALREADY_EXIST;
                }
            } else {
                message = "Post" + MessageVarList.COMMON_NOT_FOUND;

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



        if (dataBean.getPostid() == null && dataBean.getPostid().equals("")) {
            message = MessageVarList.POST_ID_NULL;
        } else if (dataBean.getReact() == null) {
            message = MessageVarList.REACT_NULL;
        }
       /* else if(!dataBean.getReact().equals("1")&&!dataBean.getReact().equals("0")){
            message=MessageVarList.REACT_INVALID;
        }*/
        else if (!Common.reactList.contains(dataBean.getReact())) {
            message = MessageVarList.REACT_INVALID;
        } else {
            Integer pid = Integer.parseInt(dataBean.getPostid());
            Optional<Post> po = postRepository.findById(pid);
            if (po.isPresent()) {
                Optional<PostReact> por = postReactRepository.findById(new PostReactId(po.get(), requestBean.getUsername()));
                PostReact p = null;
                if (!por.isPresent()) {
                    p = new PostReact();
                } else {
                    p = por.get();
                }
                p.setPostreactid(new PostReactId(po.get(), requestBean.getUsername()));
                p.setReact(dataBean.getReact());

                postReactRepository.saveAndFlush(p);

                responsCode = ResponseCode.RSP_SUCCESS;
                message = MessageVarList.SUCCESS_ADD;

            } else {
                message = "Post" + MessageVarList.COMMON_NOT_FOUND;

            }


        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }


    //sort by date
    @Override
    public DataTableBean getMyPosts(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        Page<Post> posts;
        Specification<Post> specification = null;

        if (requestBean.isSearch() && requestBean.getRequestBody() != null) {
            PostDataBean postDataBean = modelMapper.map(requestBean.getRequestBody(), PostDataBean.class);

            specification = myPostSpecification(requestBean.getUsername(), postDataBean);
        } else {
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

            if(bank.getImgName()!=null&&!bank.getImgName().equals("")) {
                try {
                    Paths path=pathsRepository.findByFilepathname("postpath");
                    if(path!=null) {
                        String imgPath = path.getPath();
                        Resource resource = new FileSystemResource(imgPath + bank.getImgName());
                        String imageData = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
                        dbean.setImageName(bank.getImgName());
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

            long reactCount1 = postReactRepository.countByPostreactidPostAndReact(bank,"1");
            long reactCount0 = postReactRepository.countByPostreactidPostAndReact(bank,"0");

            dbean.setDisLikCount(String.valueOf(reactCount0));
            dbean.setLikCount(String.valueOf(reactCount1));

            Optional<PostReact>pr=postReactRepository.findById(new PostReactId(bank,requestBean.getUsername()));
            if(pr.isPresent())dbean.setIsReacted(pr.get().getReact());
            else dbean.setIsReacted("-1");

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

    private Path foundfile = null;


    private byte[] getPostImage(String imgName) throws IOException {
        byte[] img = null;

        Resource resource = null;
        Optional<Paths> path = pathsRepository.findById("postimgpath");
        if (path.isPresent()) {
            String imgPath = path.get().getPath();
            Files.list(java.nio.file.Paths.get(imgPath)).forEach(file -> {
                if (file.getFileName().toString().startsWith(imgName)) {
                    foundfile = file;
                }

            });
            if (foundfile != null) {
                resource = new UrlResource(foundfile.toUri());
            }
            img = Files.readAllBytes(resource.getFile().toPath());

        }
        return img;
    }

    private Specification<Post> myPostDefaultSpecification(String username) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            predicates.add(criteriaBuilder.equal(root.<String>get("posteduser"), username));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private Specification<Post> myPostSpecification(String username, PostDataBean pb) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            predicates.add(criteriaBuilder.equal(root.<String>get("posteduser"), username));

            if (pb.getPostDesc() != null && !pb.getPostDesc().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postdesc"), "%" + pb.getPostDesc() + "%"));
            }
            if (pb.getPostSubject() != null && !pb.getPostSubject().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postsubject"), "%" + pb.getPostSubject() + "%"));
            }

            if (pb.getPostedTime() != null && !pb.getPostedTime().isEmpty()) {

                Date d = null;
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
    public DataTableBean getNewsPosts(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        Page<Post> posts;
        Specification<Post> specification = null;
        PostDataBean postDataBean = modelMapper.map(requestBean.getRequestBody(), PostDataBean.class);
        String postCategory = "news";
        if (requestBean.isSearch()) {

            specification = newsPostSpecification(postCategory, postDataBean);
        } else {
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

            if(bank.getImgName()!=null&&!bank.getImgName().equals("")) {
                try {
                    Paths path=pathsRepository.findByFilepathname("postpath");
                    if(path!=null) {
                        String imgPath = path.getPath();
                        Resource resource = new FileSystemResource(imgPath + bank.getImgName());
                        String imageData = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
                        dbean.setImageName(bank.getImgName());
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
                predicates.add(criteriaBuilder.like(root.<String>get("postdesc"), "%" + pb.getPostDesc() + "%"));
            }
            if (pb.getPostSubject() != null && !pb.getPostSubject().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postsubject"), "%" + pb.getPostSubject() + "%"));
            }

            if (pb.getPostedTime() != null && !pb.getPostedTime().isEmpty()) {

                Date d = null;
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
    public DataTableBean gettipPosts(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        Page<Post> posts;
        Specification<Post> specification = null;
        PostDataBean postDataBean = modelMapper.map(requestBean.getRequestBody(), PostDataBean.class);
        String postCategory = "tip";
        if (requestBean.isSearch()) {

            specification = tipPostSpecification(postCategory, postDataBean);
        } else {
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

            if(bank.getImgName()!=null&&!bank.getImgName().equals("")) {
                try {
                    Paths path=pathsRepository.findByFilepathname("postpath");
                    if(path!=null) {
                        String imgPath = path.getPath();
                        Resource resource = new FileSystemResource(imgPath + bank.getImgName());
                        String imageData = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
                        dbean.setImageName(bank.getImgName());
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
                predicates.add(criteriaBuilder.like(root.<String>get("postdesc"), "%" + pb.getPostDesc() + "%"));
            }
            if (pb.getPostSubject() != null && !pb.getPostSubject().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postsubject"), "%" + pb.getPostSubject() + "%"));
            }

            if (pb.getPostedTime() != null && !pb.getPostedTime().isEmpty()) {

                Date d = null;
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

            if(bank.getImgName()!=null&&!bank.getImgName().equals("")) {
                try {
                    Paths path=pathsRepository.findByFilepathname("postpath");
                    if(path!=null) {
                        String imgPath = path.getPath();
                        Resource resource = new FileSystemResource(imgPath + bank.getImgName());
                        String imageData = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
                        dbean.setImageName(bank.getImgName());
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

            ///
            long reactCount1 = postReactRepository.countByPostreactidPostAndReact(bank,"1");
            long reactCount0 = postReactRepository.countByPostreactidPostAndReact(bank,"0");


            dbean.setDisLikCount(String.valueOf(reactCount0));
            dbean.setLikCount(String.valueOf(reactCount1));


            Optional<PostReact>pr=postReactRepository.findById(new PostReactId(bank,requestBean.getUsername()));
            if(pr.isPresent())
                dbean.setIsReacted(pr.get().getReact());
            else
                dbean.setIsReacted("-1");

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
                predicates.add(criteriaBuilder.like(root.<String>get("postdesc"), "%" + pb.getPostDesc() + "%"));
            }
            if (pb.getPostSubject() != null && !pb.getPostSubject().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.<String>get("postsubject"), "%" + pb.getPostSubject() + "%"));
            }

            Join<Post, PostTypes> psTypJoin = root.join("posttype");
            predicates.add(criteriaBuilder.equal(psTypJoin.get("posttypecode"), "common"));

            if (pb.getPosttype() != null && !pb.getPosttype().isEmpty()) {
                Join<Post, PostTypes> comTypJoin = root.join("posttype");
                predicates.add(criteriaBuilder.equal(comTypJoin.get("posttypecode"), pb.getPosttype()));
            }

            if (pb.getPostedTime() != null && !pb.getPostedTime().isEmpty()) {

                Date d = null;
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


    @Autowired
    DiseaseRepository diseaseRepository;
    @Autowired
    private OfficerRepository officerRepository;

    @Autowired
    ProductPesticideRepository productPesticideRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseBean addDisease(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PlagueDetailBean ob = modelMapper.map(requestBean.getRequestBody(), PlagueDetailBean.class);

        String errMsg = null;
        errMsg = validateDiseaseBean(ob);
        Optional<Disease> d = diseaseRepository.findById(ob.getDiseaseCode());

        if (errMsg != null) {
            message = errMsg;
        } else if (d.isPresent()) {
            message = "Disease " + MessageVarList.COMMON_ALREADY_EXIST;
        } else {
            Disease dc = new Disease();
            dc.setDiseaseCode(ob.getDiseaseCode());
            dc.setDiseaseName(ob.getDiseaseName());
            dc.setRootCause(ob.getRootCause());
            dc.setDiseaseDescription(ob.getDiseaseDescription());
            dc.setRemedies(ob.getRemedies());
            dc.setSpreadingMethod(ob.getSpreadingMethod());
            Officer of = officerRepository.findByUsername(requestBean.getUsername());
            dc.setPostedofficer(of);

            diseaseRepository.saveAndFlush(dc);


            //add crops can be effected from this disewase
            if (ob.getEffectioncrops() != null && ob.getEffectioncrops().length > 0) {
                for (String pid : ob.getEffectioncrops()
                ) {
                    if(pid!=null&&!pid.equals("")){
                        Optional<Crop> p = cropRepository.findById(pid);
                        if (p.isPresent()) {
                            CropDisease pdc = new CropDisease();
                            pdc.setCropdiseaseid(new CropDiseaseId(p.get(), dc));
                            cropDiseaseRepository.saveAndFlush(pdc);
                        }
                    }
                }
            }





            if (ob.getPesticideProducts() != null && ob.getPesticideProducts().length > 0) {
                for (String pid : ob.getPesticideProducts()
                ) {
                    if(pid!=null&&!pid.equals("")){
                    Optional<Product> p = productRepository.findById(Integer.valueOf(pid));
                    if (p.isPresent()) {
                        ProductPesticide pdc = new ProductPesticide();
                        pdc.setProductpesticideid(new ProductPesticideId(p.get(), dc));
                        productPesticideRepository.saveAndFlush(pdc);
                    }
                    }
                }
            }


            //add crops

            message=MessageVarList.SUCCESS_ADD;
            responsCode=ResponseCode.RSP_SUCCESS;

        }

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;
    }

    private String validateDiseaseBean(PlagueDetailBean ob) {
        String errMsg = null;

        if (ob.getDiseaseCode() == null || ob.getDiseaseCode().equals("")) {
            errMsg = MessageVarList.DISEASE_ID_NULL;
        }
        if (ob.getDiseaseName() == null || ob.getDiseaseName().equals("")) {
            errMsg = MessageVarList.DISEASE_NAME_NULL;
        }
        if (ob.getDiseaseDescription() == null || ob.getDiseaseDescription().equals("")) {
            errMsg = MessageVarList.DISEASE_DISCRIPTION_NULL;
        }

        if (ob.getRootCause() == null || ob.getRootCause().equals("")) {
            errMsg = MessageVarList.ROOT_CAUSE_NULL;
        }
        if (ob.getRemedies() == null || ob.getRemedies().equals("")) {
            errMsg = MessageVarList.REMEDIES_NULL;
        }
        if (ob.getSpreadingMethod() == null || ob.getSpreadingMethod().equals("")) {
            errMsg = MessageVarList.SPERADING_METHOD_NULL;
        }

        return errMsg;
    }


    @Autowired
    DiseaseReportedProvinceRepository diseaseReportedProvinceRepository;

    @Override
    public ResponseBean deleteDisease(String id, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<Disease>p=diseaseRepository.findById(id);
        if (p.isPresent()){
            Disease pst=p.get();


            //deleting reported provinces
            List<DiseaseReportedProvince>rep=diseaseReportedProvinceRepository.findByDiseasereportedprovinceidDisease(pst);
            rep.forEach(r->{
                diseaseReportedProvinceRepository.delete(r);
            });



            diseaseRepository.delete(pst);

            message=MessageVarList.SUCCESS_DELETE;
            responsCode=ResponseCode.RSP_SUCCESS;
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);
        return responseBean;
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





    @Autowired
    CropDiseaseRepository cropDiseaseRepository;
    @Override
    public ResponseBean viewPlageDetails(String diseacecode, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        List<DiseaceCropDataBean>cdb=new ArrayList<>();
        if(diseacecode==null||diseacecode.equals("")){
            message="Empty Id";
        }else{

            Optional<Disease>f=diseaseRepository.findById(diseacecode);
            if(f.isPresent()) {
                List<CropDisease> fc = cropDiseaseRepository.findByCropdiseaseidDisease(f.get());
                fc.forEach(fc1->{
                    DiseaceCropDataBean db=new DiseaceCropDataBean();
                    db.setDiseaceCode(fc1.getCropdiseaseid().getDisease().getDiseaseCode());
                    db.setDiseaceName(fc1.getCropdiseaseid().getDisease().getDiseaseName());

                    db.setCropName(fc1.getCropdiseaseid().getCrop().getCropbreedname());
                    db.setCropId(fc1.getCropdiseaseid().getCrop().getCropCode());
                    cdb.add(db);
                });
                responsCode = ResponseCode.RSP_SUCCESS;
                message="Data found";
                responseBean.setContent(cdb);
            }else{
                message="No Shop found";
            }
        }
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        return responseBean;
    }

@Autowired
CropRepository cropRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private ProductFertilizerRepository productFertilizerRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public DataTableBean getPlagueList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> marketDataBeans = new ArrayList<>();
        Page<DiseaseReportedProvince> markets;
        Specification<DiseaseReportedProvince> specification = null;

        PlagueDetailBean marketDataBean =new PlagueDetailBean();
        if(requestBean.isSearch()&&requestBean.getRequestBody()!=null) {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            marketDataBean =  modelMapper.map(requestBean.getRequestBody(), PlagueDetailBean.class);
        if(marketDataBean.getCrop().equals(""))marketDataBean.setCrop(null);
            if(marketDataBean.getDiseaseCode().equals(""))marketDataBean.setDiseaseCode(null);
        if(marketDataBean.getProvince().equals(""))marketDataBean.setProvince(null);

        }
       /* else {
            String name = requestBean.getUsername();
            Officer cus = officerRepository.findByUsername(name);
            if(cus!=null) {
                Province farArea = cus.getAssignedArea().getDistrict().getProvince();

                // marketDataBean.setDistrict(cus.getArea().getDistrict().getDistrictId());
                marketDataBean.setProvince(farArea.getProvinceId());
            }
        }*/



        List<String>diseaceListbyCrop=new ArrayList<>();

        //specification = plagueSpecification(marketDataBean);


        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        markets = diseaseReportedProvinceRepository.findAll(specification, paging);

        if (requestBean.isSearch()) {

            if(marketDataBean.getCropName()!=null&&!marketDataBean.getCropName().equals("")){
                Crop p=cropRepository.findByCropbreedname(marketDataBean.getCropName());
                if(p!=null)
                    marketDataBean.setCrop(String.valueOf(p.getCropCode()));
            }
            //get possible deceases a crop can get
            if(marketDataBean.getCrop()!=null&&!marketDataBean.getCrop().equals("")){
                Optional<Crop>cr=cropRepository.findById(marketDataBean.getCrop());
                if(cr.isPresent()){
                    List<CropDisease>fc=cropDiseaseRepository.findByCropdiseaseidCrop(cr.get());
                    fc.forEach(f->{
                        diseaceListbyCrop.add(f.getCropdiseaseid().getDisease().getDiseaseCode());
                    });
                }
            }

        }



        for(DiseaseReportedProvince f :markets){
            if(requestBean.isSearch()&&marketDataBean.getCropName()!=null&&!marketDataBean.getCropName().equals("")&&diseaceListbyCrop.size()==0)
                break;
            if(requestBean.isSearch()&&marketDataBean.getCrop()!=null&&!marketDataBean.getCrop().equals("")&&diseaceListbyCrop.size()==0)
                break;


            if(diseaceListbyCrop.size()>0&&!diseaceListbyCrop.contains(f.getDiseasereportedprovinceid().getDisease().getDiseaseCode())){
                continue;
            }

            if(marketDataBean.getProvince()!=null){
                if(!f.getDiseasereportedprovinceid().getProvince().getProvinceId().equals(marketDataBean.getProvince())){
                    continue;
                }
            }

            if(marketDataBean.getDiseaseCode()!=null){
                if(!f.getDiseasereportedprovinceid().getDisease().getDiseaseCode().equals(marketDataBean.getDiseaseCode())){
                    continue;
                }
            }

            PlagueDetailBean fdb=new PlagueDetailBean();
            fdb.setDiseaseCode(f.getDiseasereportedprovinceid().getDisease().getDiseaseCode());
            fdb.setDiseaseName(f.getDiseasereportedprovinceid().getDisease().getDiseaseName());
            fdb.setDiseaseDescription(f.getDiseasereportedprovinceid().getDisease().getDiseaseDescription());
            fdb.setSpreadingMethod(f.getDiseasereportedprovinceid().getDisease().getSpreadingMethod());
            fdb.setRootCause(f.getDiseasereportedprovinceid().getDisease().getRootCause());
            fdb.setStartDate(Common.formatDatetoString(f.getFirstReportedDay()));
            fdb.setRemedies(f.getDiseasereportedprovinceid().getDisease().getRemedies());
            fdb.setProvince(f.getDiseasereportedprovinceid().getProvince().getProvinceId());
            fdb.setProvinceName(f.getDiseasereportedprovinceid().getProvince().getProvinceName());

            marketDataBeans.add(fdb);
        }
        dataTableBean.setList(marketDataBeans);
        return dataTableBean;

    }



    @Override
    public ResponseBean reportDisease(RequestBean requestBean, ResponseBean responseBean) throws Exception {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        Timestamp t= Timestamp.from(ZonedDateTime.now().toInstant());
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PlagueDetailBean ob = modelMapper.map(requestBean.getRequestBody(), PlagueDetailBean.class);

        Optional<Disease>d=diseaseRepository.findById(ob.getDiseaseCode());
        if(!d.isPresent()){
            message="Disease"+MessageVarList.COMMON_NOT_FOUND;
        }
        Optional<Province>p=provinceRepository.findById(ob.getProvince());
        if(!p.isPresent()){
            message="Province"+MessageVarList.COMMON_NOT_FOUND;
        }
        if(message.equals("")) {
            Optional<DiseaseReportedProvince> dp = diseaseReportedProvinceRepository.findById(new DiseaseReportedProvinceId(d.get(),p.get()));
            if(dp.isPresent()){
                message="Disease already reported";
            } else {
                DiseaseReportedProvince drp = new DiseaseReportedProvince();
                drp.setDiseasereportedprovinceid(new DiseaseReportedProvinceId(d.get(),p.get()));
                drp.setFirstReportedDay(Common.formatStringDateToDate(ob.getStartDate()));
                diseaseReportedProvinceRepository.saveAndFlush(drp);

//notification
                Notification n=new Notification();

                n.setOriginusername(requestBean.getUsername());
                n.setEndusername("all");
                n.setMessage(MessageVarList.PLAGUE_NOTIFICATION+d.get().getDiseaseName()+" in "+p.get().getProvinceName());

                n.setPostedtime(t);

                Optional<NotificationType> nt=notificationTypeRepository.findById("PLAG");
                if(nt.isPresent())n.setNotificationtype(nt.get());
                notificationRepository.saveAndFlush(n);



                message="Disease reported successfully";
                responsCode=ResponseCode.RSP_SUCCESS;
            }


        }

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;



    }

    @Override
    public ResponseBean addCrop(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CropDataBean ob = modelMapper.map(requestBean.getRequestBody(), CropDataBean.class);

        Optional<Crop>d=cropRepository.findById(ob.getCropid());
        if(d.isPresent()){
                message="Crop"+MessageVarList.COMMON_ALREADY_EXIST;
            } else {
                Crop drp = new Crop();
                drp.setCropCode(ob.getCropid());
                drp.setCropbreedname(ob.getCropname());
                drp.setScientificName(ob.getScientificname());
                drp.setWeedmgt(ob.getWeedmgt());
                drp.setWatermgt(ob.getWatermgt());
                drp.setFertilizerusg(ob.getFertilizerusg());
                cropRepository.saveAndFlush(drp);

            for (String s:ob.getFertilizers()
                 ) {
                if(s!=null&&!s.equals("")) {
                    ProductFertilizer pf = new ProductFertilizer();
                    Optional<Product> pr = productRepository.findById(Integer.valueOf(s));
                    if (!pr.isPresent()) continue;
                    pf.setProductFertilizerId(new ProductFertilizerId(pr.get(), drp));
                    productFertilizerRepository.saveAndFlush(pf);
                }



            }


                message="Crop"+MessageVarList.SUCCESS_ADD;
                responsCode=ResponseCode.RSP_SUCCESS;
            }




        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;


    }

    @Override
    public DataTableBean officerList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> alertDataBeans = new ArrayList<>();
        Page<Officer> alerts;
        Specification<Officer> specification = null;
        List<String>areas=new ArrayList<>();

        if (requestBean.isSearch() && requestBean.getRequestBody() != null) {
            OfficerDetailDataBean alertInputBean = modelMapper.map(requestBean.getRequestBody(), OfficerDetailDataBean.class);

            if(alertInputBean.getDistrict()!=null&&!alertInputBean.getDistrict().equals(""))
            {
                Optional<District>d=districtRepository.findById(alertInputBean.getDistrict());
                if(d.isPresent()){
                List<Area>a=areaRepository.findByDistrict(d.get());
                a.forEach(a1->{
                    areas.add(a1.getAreaCode());
                });

                }
            }
            specification = makeSpecification(alertInputBean);
        }

        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        alerts = officerRepository.findAll(specification, paging);

        if (alerts != null) {
            dataTableBean.setCount(alerts.getTotalElements());
            dataTableBean.setPagecount(alerts.getTotalPages());
        }

       for(Officer alert: alerts){
           Area assignAr=alert.getAssignedArea();
           if(areas.size()>0&&!areas.contains(assignAr.getAreaCode())) continue;

           OfficerDetailDataBean alertDataBean = new OfficerDetailDataBean();

            alertDataBean.setOfficerId(alert.getOfficerId());
                alertDataBean.setOfficerName(alert.getOfficerName());
                alertDataBean.setTelno(alert.getTelno());
                alertDataBean.setEmail(alert.getEmail());
               alertDataBean.setAddress(alert.getAddress());
                alertDataBean.setAssignedAreaDesc(alert.getAssignedArea().getAreaName());
           alertDataBean.setSpecialty(alert.getSpecialty().getSpecialtydesc());

            alertDataBeans.add(alertDataBean);
        }

        dataTableBean.setList(alertDataBeans);

        return dataTableBean;
    }




    @Override
    public DataTableBean getOfficerNotification(RequestBean requestBean) {
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
    public DataTableBean getOfficerPlagueNotification(RequestBean requestBean) {
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

    @Override
    public DataTableBean getOfficerProductNotification(RequestBean requestBean) {
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


    @Autowired
    ShopProductRepository shopProductRepository;


    @Override
    public ResponseBean viewProductDetails(String productid, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        ProductDataBean pb=null;
        Optional<Product>p1=productRepository.findById(Integer.valueOf(productid));
        if(p1.isPresent()){
            Product p=p1.get();
            pb=new ProductDataBean();
            pb.setCategory(p.getCategory());
            pb.setProductid(String.valueOf(p.getProductId()));
            pb.setProductname(p.getProductname());
            pb.setPrice(String.valueOf(p.getPrice()));
            pb.setManufacturedby(p.getManufacturedBy());
            pb.setImportedby(p.getImportedBy());
            pb.setExpdate(p.getExpDate());
            pb.setUsage(p.getUsge());

            if(p.getImgname()!=null&&!p.getImgname().equals("")) {
                try {
                    Paths path=pathsRepository.findByFilepathname("prodimg");
                    if(path!=null) {
                        String imgPath = path.getPath();
                        Resource resource = new FileSystemResource(imgPath + p.getImgname());
                        String imageData = Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
                        pb.setImageName(p.getImgname());
                        pb.setImageData(imageData);


                    }
                    // tempDataBean.setImage(getPostImage(temp.getImgName()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }


            //shops

            List<ShopDataBean>sdb=new ArrayList<>();
            List<ShopProduct>sprod=shopProductRepository.findByShopproductidProduct(p);
            if(sprod!=null){
                sprod.forEach((sp)->{
                    Shop s=sp.getShopproductid().getShop();
                    ShopDataBean sb=new ShopDataBean();
                    sb.setShopName(s.getShopname());
                    sb.setAddress(s.getAddress());
                    sb.setShopContactNo(s.getShopContactNo());
                    sb.setOwnerContactNo(s.getOwnerNic().getOwnerContactNo());
                    sdb.add(sb);
                });

            }

            pb.setShopdetails(sdb);

            responsCode=ResponseCode.RSP_SUCCESS;
            message=MessageVarList.COMMON_FOUND;
        }

        responseBean.setContent(pb);
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        return responseBean;

    }

    @Override
    public ResponseBean getRecomandedpestlist(String diseaseid, ResponseBean responseBean) {

        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        List<ProductDataBean>pdb=new ArrayList<>();
        Optional<Disease>d=diseaseRepository.findById(diseaseid);
        if(d.isPresent()) {
            List<ProductPesticide> ps = productPesticideRepository.findByProductpesticideidRecomandeddisease(d.get());

            ps.forEach((p)->{
                ProductDataBean db=new ProductDataBean();
                db.setProductid(String.valueOf(p.getProductpesticideid().getProduct().getProductId()));
                db.setProductname(p.getProductpesticideid().getProduct().getProductname());
                db.setManufacturedby(p.getProductpesticideid().getProduct().getManufacturedBy());
                pdb.add(db);
            });
            responsCode=ResponseCode.RSP_SUCCESS;
            message=MessageVarList.COMMON_FOUND;

        }
        responseBean.setContent(pdb);
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        return responseBean;

    }

    private Specification<Officer> makeSpecification(OfficerDetailDataBean alertInputBean) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();



            if (alertInputBean.getSpecialty() != null && !alertInputBean.getSpecialty().isEmpty()) {
                Join<Officerspecialty, Officer> statusJoin = root.join("specialty");
                predicates.add(criteriaBuilder.equal(statusJoin.get("specialtycode"), alertInputBean.getSpecialty()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }


}

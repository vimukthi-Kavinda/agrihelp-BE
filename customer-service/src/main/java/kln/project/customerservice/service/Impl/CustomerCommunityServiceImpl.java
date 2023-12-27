package kln.project.customerservice.service.Impl;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import kln.project.customerservice.model.bean.DataTableBean;
import kln.project.customerservice.model.bean.PostDataBean;
import kln.project.customerservice.model.bean.RequestBean;
import kln.project.customerservice.model.entity.*;
import kln.project.customerservice.model.bean.*;
import kln.project.customerservice.repository.*;
import kln.project.customerservice.service.CustomerCommunityService;
import kln.project.customerservice.util.Common;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CustomerCommunityServiceImpl implements CustomerCommunityService {
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
            dbean.setPostId(bank.getPostid());
            dbean.setPostSubject(bank.getPostsubject());
            dbean.setPostDesc(bank.getPostdesc());
            if (bank.getImgname() != null && !bank.getImgname().equals("")) {
                try {
                    Paths path = pathsRepository.findByFilepathname("postpath");
                    if (path != null) {
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
            dbean.setPostId(bank.getPostid());
            dbean.setPostSubject(bank.getPostsubject());
            dbean.setPostDesc(bank.getPostdesc());
            if (bank.getImgname() != null && !bank.getImgname().equals("")) {
                try {
                    Paths path = pathsRepository.findByFilepathname("postpath");
                    if (path != null) {
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

    @Cacheable("cusnotification")
    @Override
    public DataTableBean getCustomerNotification(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Optional<NotificationType> nt = notificationTypeRepository.findById("TIPS");
       // Optional<NotificationType> nt1 = notificationTypeRepository.findById("PROD");

        if (nt.isPresent()) {

            //List<NotificationType> ntyps = Arrays.asList(nt.get(), nt1.get());

            List<Notification> nl = notificationRepository.findTop15ByNotificationtypeOrderByPostedtimeDesc(nt.get());
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

    @Cacheable("cusprodnotification")
    @Override
    public DataTableBean getCustomerProductNotification(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Optional<NotificationType> nt = notificationTypeRepository.findById("PROD");

        if (nt.isPresent()) {

            //List<NotificationType> ntyps = Arrays.asList(nt.get(), nt1.get());

            List<Notification> nl = notificationRepository.findTop15ByNotificationtypeOrderByPostedtimeDesc(nt.get());
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


///////////////////////

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

}

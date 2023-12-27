package kln.project.shopservice.service.impl;

import kln.project.shopservice.model.bean.*;
import kln.project.shopservice.model.entity.*;
import kln.project.shopservice.repository.*;
import kln.project.shopservice.service.ShopHelpService;
import kln.project.shopservice.util.Common;
import kln.project.shopservice.util.MessageVarList;
import kln.project.shopservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class ShopHelpServiceImpl implements ShopHelpService {


    @Autowired
    ProductRepository productRepository;


    @Autowired
    ShopProductRepository shopProductRepository;

    @Autowired
    Common common;


    @Autowired
    ModelMapper modelMapper;


    @Autowired
    PathsRepository pathsRepository;


    @Override
    public ResponseBean addnewproduct(RequestBean requestBean, ResponseBean responseBean) throws Exception {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProductDataBean ob = modelMapper.map(requestBean.getRequestBody(), ProductDataBean.class);


       /* Optional<Product>mp=productRepository.findById(ob.setProductid());
        if(mp.isPresent()){
            message="Crop"+MessageVarList.COMMON_ALREADY_REQUEST_EXIST;

        }
        if(message.equals("")&&!mp.isPresent()) {*/
            Product drp = new Product();
            drp.setProductName(ob.getProductname());
        drp.setCategory(ob.getCategory());
        drp.setManufacturedBy(ob.getManufacturedby());
        drp.setImportedBy(ob.getImportedby());
        drp.setExpDate(ob.getExpdate());
        drp.setUsge(ob.getUsage());
        //if(ob.getPrice()!=null&&!ob.getPrice().equals(""))
            drp.setPrice(Double.parseDouble(ob.getPrice()));



            //add image

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedTimestamp = LocalDateTime.now().format(formatter);
        String fileName=formattedTimestamp+ob.getFileName();
        if(ob.getFileContent()!=null) {
            drp.setImgname(fileName);
            Optional<Paths>pth=pathsRepository.findById("prodimg");
            String uri=pth.get().getPath();

            try {
                File file = new File(uri);
                if (!file.exists()) {
                    file.mkdirs();
                }

                String filePath = uri + "/" + fileName;
                FileOutputStream outputStream = new FileOutputStream(filePath);
                outputStream.write(ob.getFileContent());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }

        }
        productRepository.saveAndFlush(drp);



        Optional<Shop>sp=shopRepository.findById(requestBean.getUsername());
        if(sp.isPresent()){
            Shop s=sp.get();
            ShopProduct spro=new ShopProduct();
            spro.setShopproductid(new ShopProductId(s,drp));
            spro.setPrice(Double.parseDouble(ob.getPrice()));
            spro.setAmount(Integer.valueOf(ob.getAmount()));
            shopProductRepository.saveAndFlush(spro);
        }


        Timestamp t= Timestamp.from(ZonedDateTime.now().toInstant());

        Notification n=new Notification();

        n.setPostedtime(t);
        n.setOriginusername(sp.get().getShopRegNo());
        n.setEndusername("all");
        n.setMessage(sp.get().getShopname()+MessageVarList.SHOP_NOTIFICATION+drp.getProductName());


        Optional<NotificationType> nt=notificationTypeRepository.findById("PROD");
        if(nt.isPresent())n.setNotificationType(nt.get());
        notificationRepository.saveAndFlush(n);



            message="Product"+ MessageVarList.SUCCESS_ADD;
            responsCode=ResponseCode.RSP_SUCCESS;
        //}

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;


    }


    @Autowired
    ShopRepository shopRepository;

@Autowired
    NotificationRepository notificationRepository;

@Autowired
    NotificationTypeRepository notificationTypeRepository;

    @Override
    public ResponseBean addproductstore(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ShopProductBean ob = modelMapper.map(requestBean.getRequestBody(), ShopProductBean.class);

        Optional<Shop> d=shopRepository.findById(ob.getShopid());
        Optional<Product>m=productRepository.findById(Integer.parseInt(ob.getProductid()));




        if(!d.isPresent()){
            message="Shop"+MessageVarList.NOT_FOUND;
        } else if (!m.isPresent()) {

            message="Product"+MessageVarList.NOT_FOUND;
        }

        Optional<ShopProduct>mp=shopProductRepository.findById(new ShopProductId(d.get(),m.get()));
        if(mp.isPresent()){
            message="Product "+MessageVarList.COMMON_ALREADY_REQUEST_EXIST;

        }
        if(message.equals("")&&!mp.isPresent()) {
            ShopProduct drp = new ShopProduct();
            drp.setShopproductid(new ShopProductId(d.get(),m.get()));
            if(ob.getAmount()!=null&&!ob.getAmount().equals(""))
            drp.setAmount(Integer.parseInt(ob.getAmount()));
            if(ob.getPrice()!=null&&!ob.getPrice().equals(""))
            drp.setPrice(Double.parseDouble(ob.getPrice()));
            shopProductRepository.saveAndFlush(drp);

            Timestamp t= Timestamp.from(ZonedDateTime.now().toInstant());

            Notification n=new Notification();

            n.setPostedtime(t);
            n.setOriginusername(d.get().getShopRegNo());
            n.setEndusername("all");
            n.setMessage(d.get().getShopname()+MessageVarList.SHOP_NOTIFICATION+m.get().getProductName());


            Optional<NotificationType> nt=notificationTypeRepository.findById("PROD");
            if(nt.isPresent())n.setNotificationType(nt.get());
            notificationRepository.saveAndFlush(n);


            message="Product"+ MessageVarList.SUCCESS_ADD;
            responsCode=ResponseCode.RSP_SUCCESS;
        }

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;


    }

    @Override
    public ResponseBean editproduct(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ShopProductBean ob = modelMapper.map(requestBean.getRequestBody(), ShopProductBean.class);

        Optional<Shop> d=shopRepository.findById(ob.getShopid());
        Optional<Product>m=productRepository.findById(Integer.parseInt(ob.getProductid()));



        if(!d.isPresent()){
            message="Shop"+MessageVarList.NOT_FOUND;
        } else if (!m.isPresent()) {

            message="Product"+MessageVarList.NOT_FOUND;
        }

        Optional<ShopProduct>mp=shopProductRepository.findById(new ShopProductId(d.get(),m.get()));
        if(!mp.isPresent()){
            message="Product "+MessageVarList.NOT_FOUND+" in shop";

        }
        if(message.equals("")&&mp.isPresent()) {
            ShopProduct drp = mp.get();
            //drp.setShopProductId(new ShopProductId(d.get(),m.get()));
            if(ob.getAmount()!=null&&!ob.getAmount().equals(""))
            drp.setAmount(Integer.parseInt(ob.getAmount()));
            if(ob.getPrice()!=null&&!ob.getPrice().equals(""))
            drp.setPrice(Double.parseDouble(ob.getPrice()));
            shopProductRepository.saveAndFlush(drp);

            message="Product"+ MessageVarList.SUCCESS_ADD;
            responsCode=ResponseCode.RSP_SUCCESS;
        }

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;


    }

    @Override
    public ResponseBean delete(String id,RequestBean requestBean, ResponseBean responseBean) {

        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<Product> d=productRepository.findById(Integer.parseInt(id));
        Optional<Shop>m=shopRepository.findById(requestBean.getUsername());



        if(!d.isPresent()){
            message="Product"+MessageVarList.NOT_FOUND;
        } else if (!m.isPresent()) {

            message="Shop"+MessageVarList.NOT_FOUND;
        }

        Optional<ShopProduct>mp=shopProductRepository.findById(new ShopProductId(m.get(),d.get()));

        if(!mp.isPresent()){
            message="Shop"+MessageVarList.NOT_FOUND;

        }
        if(message.equals("")&&mp.isPresent()) {
            ShopProduct drp = mp.get();
            shopProductRepository.delete(drp);
            message="Product"+ MessageVarList.SUCCESS_DELETE;
            responsCode=ResponseCode.RSP_SUCCESS;
        }

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;




    }

    @Override
    public DataTableBean getList(RequestBean requestBean) {
        DataTableBean dataTableBean = new DataTableBean();
        List<Object> postDataBeans = new ArrayList<>();
        Page<ShopProduct> posts;
        Specification<ShopProduct> specification = null;

        /*if (requestBean.isSearch()) {
            postDataBean = modelMapper.map(requestBean.getRequestBody(), MarketCropDataBean.class);

        }*/
        String shopid=requestBean.getUsername();



        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

       // posts = shopProductRepository.findAll(specification, paging);

        Optional<Shop>sh=shopRepository.findById(shopid);
        if(sh.isPresent()) {
            List<ShopProduct> shppro = shopProductRepository.findByShopproductidShop(sh.get());
       /* if (posts != null) {
            dataTableBean.setCount(posts.getTotalElements());
            dataTableBean.setPagecount(posts.getTotalPages());
        }*/

            for (ShopProduct bank : shppro) {

                Product p = bank.getShopproductid().getProduct();
                String mid = bank.getShopproductid().getShop().getShopRegNo();
                if (!mid.equals(shopid)) continue;

                ShopProductBean dbean = new ShopProductBean();
                dbean.setShopid(mid);
                dbean.setShopname(bank.getShopproductid().getShop().getShopname());
                dbean.setProductid(String.valueOf(p.getProductId()));
                dbean.setProductname(p.getProductName());

                dbean.setAmount(String.valueOf(bank.getAmount()));
                dbean.setPrice(String.valueOf(p.getPrice()));

                postDataBeans.add(dbean);
            }
        }
        dataTableBean.setList(postDataBeans);

        return dataTableBean;
    }

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
            pb.setProductname(p.getProductName());
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

responsCode=ResponseCode.RSP_SUCCESS;
            message=MessageVarList.COMMON_FOUND;
        }

        responseBean.setContent(pb);
        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
return responseBean;

    }
}

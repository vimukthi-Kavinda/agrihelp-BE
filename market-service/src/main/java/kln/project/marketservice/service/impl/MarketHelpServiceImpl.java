package kln.project.marketservice.service.impl;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;


import kln.project.marketservice.model.bean.DataTableBean;
import kln.project.marketservice.model.bean.MarketCropDataBean;
import kln.project.marketservice.model.bean.RequestBean;
import kln.project.marketservice.model.bean.ResponseBean;
import kln.project.marketservice.model.entity.Crop;
import kln.project.marketservice.model.entity.Market;
import kln.project.marketservice.model.entity.MarketCropPrice;
import kln.project.marketservice.model.entity.MarketCropPriceId;
import kln.project.marketservice.repository.CropRepository;
import kln.project.marketservice.repository.MarketCropPriceRepository;
import kln.project.marketservice.repository.MarketRepository;
import kln.project.marketservice.service.MarketHelpService;
import kln.project.marketservice.util.Common;
import kln.project.marketservice.util.MessageVarList;
import kln.project.marketservice.util.ResponseCode;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class MarketHelpServiceImpl implements MarketHelpService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    CropRepository cropRepository;

    @Autowired
    MarketCropPriceRepository marketCropPriceRepository;

    @Override
    public ResponseBean add(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        MarketCropDataBean ob = modelMapper.map(requestBean.getRequestBody(), MarketCropDataBean.class);

        Optional<Crop> d=cropRepository.findById(ob.getCropcode());
        Optional<Market>m=marketRepository.findById(ob.getMarketid());



        if(!d.isPresent()){
            message="Crop"+MessageVarList.NOT_FOUND;
        } else if (!m.isPresent()) {

            message="Market"+MessageVarList.NOT_FOUND;
        }

        Optional<MarketCropPrice>mp=marketCropPriceRepository.findById(new MarketCropPriceId(m.get(),d.get()));
        if(mp.isPresent()){
            message="Crop"+MessageVarList.COMMON_ALREADY_REQUEST_EXIST;

        }
        if(message.equals("")&&!mp.isPresent()) {
            MarketCropPrice drp = new MarketCropPrice();
            drp.setMarketCropPriceId(new MarketCropPriceId(m.get(),d.get()));
            drp.setAmount(Double.parseDouble(ob.getAmount()));
            drp.setUnitPrice(Double.parseDouble(ob.getUnitprice()));
            marketCropPriceRepository.saveAndFlush(drp);

            message="Crop"+ MessageVarList.SUCCESS_ADD;
            responsCode=ResponseCode.RSP_SUCCESS;
        }

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;


    }

    @Override
    public ResponseBean edit(RequestBean requestBean, ResponseBean responseBean) {
        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        MarketCropDataBean ob = modelMapper.map(requestBean.getRequestBody(), MarketCropDataBean.class);

        Optional<Crop> d=cropRepository.findById(ob.getCropcode());
        Optional<Market>m=marketRepository.findById(ob.getMarketid());



        if(!d.isPresent()){
            message="Crop"+MessageVarList.NOT_FOUND;
        } else if (!m.isPresent()) {

            message="Market"+MessageVarList.NOT_FOUND;
        }

        Optional<MarketCropPrice>mp=marketCropPriceRepository.findById(new MarketCropPriceId(m.get(),d.get()));

        if(!mp.isPresent()){
            message="Crop"+MessageVarList.COMMON_ALREADY_REQUEST_EXIST;

        }
        if(message.equals("")&&mp.isPresent()) {
            MarketCropPrice drp = mp.get();

            if(ob.getAmount()!=null&&!ob.getAmount().equals(""))
            drp.setAmount(Double.parseDouble(ob.getAmount()));
            if(ob.getUnitprice()!=null&&!ob.getUnitprice().equals(""))
            drp.setUnitPrice(Double.parseDouble(ob.getUnitprice()));
            marketCropPriceRepository.saveAndFlush(drp);

            message="Crop"+ MessageVarList.SUCCESS_UPDATE;
            responsCode=ResponseCode.RSP_SUCCESS;
        }

        responseBean.setResponseCode(responsCode);
        responseBean.setResponseMsg(message);
        responseBean.setContent(null);

        return responseBean;


    }

    @Autowired
    Common common;

    @Override
    public ResponseBean delete(String cropcode,RequestBean requestBean,  ResponseBean responseBean) {

        String responsCode = ResponseCode.RSP_ERROR;
        String message = "";

        Optional<Crop> d=cropRepository.findById(cropcode);
        Optional<Market>m=marketRepository.findById(requestBean.getUsername());



        if(!d.isPresent()){
            message="Crop"+MessageVarList.NOT_FOUND;
        } else if (!m.isPresent()) {

            message="Market"+MessageVarList.NOT_FOUND;
        }

        Optional<MarketCropPrice>mp=marketCropPriceRepository.findById(new MarketCropPriceId(m.get(),d.get()));

        if(!mp.isPresent()){
            message="Crop"+MessageVarList.NOT_FOUND;

        }
        if(message.equals("")&&mp.isPresent()) {
            MarketCropPrice drp = mp.get();
            marketCropPriceRepository.delete(drp);
            message="Crop"+ MessageVarList.SUCCESS_DELETE;
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
        Page<MarketCropPrice> posts;
        Specification<MarketCropPrice> specification = null;

        /*if (requestBean.isSearch()) {
            postDataBean = modelMapper.map(requestBean.getRequestBody(), MarketCropDataBean.class);

        }*/
        String shopid=requestBean.getUsername();



        List<Sort.Order> orders = common.getSort(requestBean.getSort());

        Pageable paging = PageRequest.of(requestBean.getPage(), requestBean.getSize(), Sort.by(orders));

        posts = marketCropPriceRepository.findAll(specification, paging);

        if (posts != null) {
            dataTableBean.setCount(posts.getTotalElements());
            dataTableBean.setPagecount(posts.getTotalPages());
        }

        for(MarketCropPrice bank:posts){

            String mid=bank.getMarketCropPriceId().getMarket().getMarketId();
            if(!mid.equals(shopid))continue;

            MarketCropDataBean dbean = new MarketCropDataBean();
            dbean.setMarketid(mid);
            dbean.setMarketname(bank.getMarketCropPriceId().getMarket().getMarketname());
            dbean.setCropcode(bank.getMarketCropPriceId().getCrop().getCropCode());
            dbean.setCropname(bank.getMarketCropPriceId().getCrop().getCropbreedname());

            dbean.setAmount(String.valueOf(bank.getAmount()));
            dbean.setUnitprice(String.valueOf(bank.getUnitPrice()));
            postDataBeans.add(dbean);
        }

        dataTableBean.setList(postDataBeans);

        return dataTableBean;
    }


}

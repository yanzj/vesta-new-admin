package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.JsonDTO.SellerPictureDTO;
import com.maxrocky.vesta.application.JsonDTO.SellerSeachDTO;
import com.maxrocky.vesta.application.inf.SellerPictureService;
import com.maxrocky.vesta.domain.model.SellerPictureEntity;
import com.maxrocky.vesta.domain.repository.SellerPictureRepository;
import com.maxrocky.vesta.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/1/17.
 */

@Service
public class SellerPictureServiceImpl implements SellerPictureService{
    @Autowired
    private SellerPictureRepository sellerPictureRepository;

    @Override
    public void AddSellerPicture(SellerSeachDTO sellerSeachDTO) {
        SellerPictureEntity sellerPictureEntity = new SellerPictureEntity();
        sellerPictureEntity.setId("M"+DateUtils.getNow(DateUtils.FORMAT_LONG_Number));
        sellerPictureEntity.setCreateTime(new Date());
        sellerPictureEntity.setPictureStatus(SellerPictureEntity.STATUS_VALID);
        sellerPictureEntity.setPictureUrl(sellerSeachDTO.getPictureURL());
        sellerPictureEntity.setSellerId(sellerSeachDTO.getSellerId());
        sellerPictureRepository.AddSellerPicture(sellerPictureEntity);
    }

    @Override
    public List<SellerPictureDTO> getSellerPictureList() {
        return null;
    }
}

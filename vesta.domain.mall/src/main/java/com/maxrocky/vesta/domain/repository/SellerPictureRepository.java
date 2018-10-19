package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SellerPictureEntity;

import java.util.List;

/**
 * Created by chen on 2016/1/15.
 */
public interface SellerPictureRepository {
    /**新增图片*/
    void AddSellerPicture(SellerPictureEntity sellerPictureEntity);
    /**查询商户图片列表*/
    List<SellerPictureEntity> getSellerPictures(String bussnessId);
}

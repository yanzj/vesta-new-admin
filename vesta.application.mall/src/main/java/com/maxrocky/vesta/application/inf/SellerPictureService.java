package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.JsonDTO.SellerPictureDTO;
import com.maxrocky.vesta.application.JsonDTO.SellerSeachDTO;

import java.util.List;

/**
 * Created by chen on 2016/1/17.
 */
public interface SellerPictureService {
    /**新增商户图片*/
    void AddSellerPicture(SellerSeachDTO sellerSeachDTO);
    /**获取商户图片*/
    List<SellerPictureDTO> getSellerPictureList();
}

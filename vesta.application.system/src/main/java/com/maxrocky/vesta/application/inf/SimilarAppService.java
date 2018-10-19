package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.SimilarAppContentDTO;
import com.maxrocky.vesta.application.DTO.SimilarAppPictureDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author WeiYangDong
 * @Description 类APP管理Service
 * @data 2018/5/28
 */
public interface SimilarAppService {

    List<SimilarAppPictureDTO> getPictureList(SimilarAppPictureDTO similarAppPictureDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException;
    List<SimilarAppContentDTO> getContentList(SimilarAppContentDTO similarAppContentDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException;
    SimilarAppPictureDTO getPictureById(String id) throws InvocationTargetException, IllegalAccessException;
    SimilarAppContentDTO getContentById(String id) throws InvocationTargetException, IllegalAccessException;
    void saveOrUpdatePicture(SimilarAppPictureDTO similarAppPictureDTO);
    void saveOrUpdateContent(SimilarAppContentDTO similarAppContentDTO);
    void deletePicture(String id);
    void deleteContent(String id);
}

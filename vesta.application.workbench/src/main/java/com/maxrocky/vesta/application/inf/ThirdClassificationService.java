package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.jsonDTO.AllClassificationDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.SearchClassificationDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.ThirdClassificationCommDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2016/6/6.
 */
public interface ThirdClassificationService {

    /**
     * 根据二级分类获取三级分类
     * @param secondId
     * @return
     */
    public Map<String, String> getThirdClassification(String secondId);

    public Map<String, String> getThirdClassificationNew(String secondId);

    public Map<String,String> getAllCommonlyUsedManagement();


    /**
     * 更新
     * @param thirdClassificationCommDTO
     * @return
     */
    public ApiResult update(ThirdClassificationCommDTO thirdClassificationCommDTO)throws GeneralException;

    public boolean insert(ThirdClassificationCommDTO thirdClassificationCommDTO);

    public boolean deleteAll();

    public boolean deleteById(String id);
    /**
     * Code:D
     * Type:
     * Describe:查询所有的分类 顺序为一级-二级-三级
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/26
     */
    public List<AllClassificationDTO> getAllClassifyLlist(SearchClassificationDTO searchClassificationDTO,WebPage webPage);
    /**
     * Code:D
     * Type:
     * Describe:更新三级分类别名
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/26
     */
    public void updateThirdAlias(AllClassificationDTO allClassificationDTO);

    /**
     * Code:D
     * Type:
     * Describe:通过上移下一进行排序，operation=1，下移 -1上移
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/19
     */

//    public  void  orderThirdClassification(String value,String operation);

}

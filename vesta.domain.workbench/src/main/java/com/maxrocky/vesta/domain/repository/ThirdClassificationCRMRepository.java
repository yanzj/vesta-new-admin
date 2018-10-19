package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ClassificationTemporaryTimeEntity;
import com.maxrocky.vesta.domain.model.ThirdClassificationCommEntity;
import com.maxrocky.vesta.domain.model.ThirdClassificationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;


import java.util.List;
import java.util.Map;

/**
 * Created by dl on 2016/5/9.
 */
public interface ThirdClassificationCRMRepository {
    /**
     * Describe:根据id获取信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     */
    ThirdClassificationEntity get(String id);
    /**
     * Describe:创建三级分类
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    void create(ThirdClassificationEntity thirdClassificationEntity);
    /**
     * CreatedBy:dl
     * Describe:
     * 修改三级分类
     * ModifyBy:
     */
    void update(ThirdClassificationEntity thirdClassificationEntity);

    /**
     * CreatedBy:dl
     * Describe:
     * 删除三级分类
     * ModifyBy:
     */
    void delete();

    /**
     * 根据二级分类获得三级分类
     * @param firstid
     * @return
     */
    List<ThirdClassificationEntity> getSecondClassification(String firstid);

    List<ThirdClassificationCommEntity> getAllCommonlyUsedManagement();

    ThirdClassificationCommEntity getComm(String value);

    void update(ThirdClassificationCommEntity thirdClassificationCommEntity);
    /**
     * 添加
     * @param thirdClassificationCommEntity
     * @return
     */
    public boolean savethirdClassificationComm(ThirdClassificationCommEntity thirdClassificationCommEntity);

    public boolean deleteAll();
    public boolean deleteById(String id);

    /**
     * Code:D
     * Type:
     * Describe:查询所有的分类 顺序为一级-二级-三级
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/26
     */
    public List<Object[]> getAllClassifyLlist(String oneType,String twoType,String threeType,String alias,String thirdTypeName,WebPage webPage);
    public List<Map<String,String>> getAllClassifyLlist(WebPage webPage);

    /**
     * Code:D
     * Type:
     * Describe:更新临时表，查询ClassificationTemporaryTimeEntity
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/29
     */
    public ClassificationTemporaryTimeEntity getClassificationTemporaryTimeEntity(String thirdClassifyValue);
    /**
     * 更新常用三级分类
     * */
    public ThirdClassificationCommEntity getThirdClassificationCommEntity(String thirdClassifyValue);


    public void updateClassificationTemporaryTimeEntity(ClassificationTemporaryTimeEntity e);

    public void updateThirdClassificationCommEntity(ThirdClassificationCommEntity e);

    /**
     * Code:D
     * Type:
     * Describe:通过上移下一进行排序，operation=1，下移 -1上移
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/19
     */

//    public  void  orderThirdClassification(String value,String operation);

}

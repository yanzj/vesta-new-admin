package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.BusinessBulletinDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 商业公告Service
 * Created by WeiYangDong on 2017/9/18.
 */
public interface BusinessBulletinService {

    /**
     * 通过公告ID获取商业公告详情
     * @param id 公告ID
     * @return BusinessBulletinDTO
     */
    BusinessBulletinDTO getBusinessBulletinById(String id) throws InvocationTargetException, IllegalAccessException;

    /**
     * 获取商业公告列表
     */
    List<BusinessBulletinDTO> getBusinessBulletinList(BusinessBulletinDTO businessBulletinDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException;

    /**
     * 保存或更新商业公告信息
     */
    void saveOrUpdateBusinessBulletinInfo(BusinessBulletinDTO businessBulletinDTO);

    /**
     * 物理删除公告信息
     */
    void deleteBusinessBulletinInfo(String id);
}

package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.BlacklistDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 黑名单功能模块Service
 * Created by WeiYangDong on 2017/11/21.
 */
public interface BlacklistService {

    /**
     * 获取黑名单列表
     */
    List<BlacklistDTO> getBlacklistList(BlacklistDTO blacklistDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException;

    /**
     * 物理删除黑名单信息
     */
    void deleteBlacklist(BlacklistDTO blacklistDTO);

    /**
     * 通过主键ID获取黑名单信息
     */
    BlacklistDTO getBlacklistById(BlacklistDTO blacklistDTO) throws InvocationTargetException, IllegalAccessException;

    /**
     * 保存或更新黑名单信息
     */
    void saveOrUpdateBlacklist(BlacklistDTO blacklistDTO);

    /**
     * 通过黑名单房产集合获取房产范围树形数据结构
     */
    String getScopeTreeByHouseCollection(BlacklistDTO blacklistDTO);

    /**
     * 检验黑名单名称唯一性
     */
    int checkBlacklistName(BlacklistDTO blacklistDTO);
}

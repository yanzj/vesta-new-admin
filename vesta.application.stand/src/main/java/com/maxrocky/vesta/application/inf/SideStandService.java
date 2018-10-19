package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.StandAdminDTO;
import com.maxrocky.vesta.application.JsonDTO.ReceiveStandDTO;
import com.maxrocky.vesta.application.JsonDTO.StandAllDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.text.ParseException;
import java.util.List;

/**
 * Created by chen on 2016/5/17.
 */
public interface SideStandService {
    //APP旁站列表
    List<StandAllDTO> getSideStands();
    //批量新增旁站信息
    void addSideStand(List<ReceiveStandDTO> receiveStandDTOs,UserPropertyStaffEntity userPropertyStaffEntity) throws ParseException;
    //后台旁站列表
    List<StandAdminDTO> getSideStandList(StandAdminDTO standAdminDTO,WebPage webPage);
    //新增旁站信息
    void addStand(StandAdminDTO standAdminDTO);
    //修改旁站信息
    void updateStand(StandAdminDTO standAdminDTO);
    //删除旁站信息
    void deleteStand(String standId);
    //打开关闭旁站
    void altStand(String standId);
    //旁站详情
    StandAdminDTO getSideStand(String standId);
}

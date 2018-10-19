package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.VisitorPassDTO;
import com.maxrocky.vesta.application.inf.VisitorService;
import com.maxrocky.vesta.domain.model.VisitorPassEntity;
import com.maxrocky.vesta.domain.model.VisitorPassLogEntity;
import com.maxrocky.vesta.domain.repository.VisitorRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 访客模块Service实现类
 * Created by WeiYangDong on 2017/12/18.
 */
@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorRepository visitorRepository;

    static BeanCopier beanCopier = BeanCopier.create(VisitorPassEntity.class, VisitorPassDTO.class, false);

    /**
     * 获取访客通行列表
     */
    @Override
    public List<VisitorPassDTO> getVisitorPassList(VisitorPassDTO visitorPassDTO, WebPage webPage){
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("projectCode",visitorPassDTO.getProjectCode());//项目编码
        paramsMap.put("houseAddress",visitorPassDTO.getHouseAddress());//房产地址
        paramsMap.put("visitorName",visitorPassDTO.getVisitorName());//访客姓名
        paramsMap.put("ownerName",visitorPassDTO.getOwnerName());//被访人
        paramsMap.put("createOnStr",visitorPassDTO.getCreateOnStr());//查询开始日期
        paramsMap.put("createOnEnd",visitorPassDTO.getCreateOnEnd());//查询结束日期
        //执行查询
        List<VisitorPassEntity> visitorPassList = visitorRepository.getVisitorPassList(paramsMap, webPage);
        //封装结果
        List<VisitorPassDTO> visitorPassDTOList = new ArrayList<>();
        VisitorPassDTO visitorPass = null;
        for (int i=0,length=visitorPassList.size();i<length;i++){
            visitorPass = new VisitorPassDTO();
            beanCopier.copy(visitorPassList.get(i),visitorPass,null);
            visitorPassDTOList.add(visitorPass);
        }
        return visitorPassDTOList;
    }

    /**
     * 获取访客通行日志列表
     */
    @Override
    public List<VisitorPassDTO> getVisitorPassLogList(VisitorPassDTO visitorPassDTO, WebPage webPage){
        //封装参数
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("projectCode",visitorPassDTO.getProjectCode());//项目编码
        paramsMap.put("houseAddress",visitorPassDTO.getHouseAddress());//房产地址
        paramsMap.put("visitorName",visitorPassDTO.getVisitorName());//访客姓名
        paramsMap.put("ownerName",visitorPassDTO.getOwnerName());//被访人
        paramsMap.put("createOnStr",visitorPassDTO.getCreateOnStr());//查询开始日期(放行时间)
        paramsMap.put("createOnEnd",visitorPassDTO.getCreateOnEnd());//查询结束日期(放行时间)
        //执行查询
        List<Object[]> visitorPassLogList = visitorRepository.getVisitorPassLogList(paramsMap, webPage);
        //封装结果
        List<VisitorPassDTO> visitorPassDTOList = new ArrayList<>();
        VisitorPassDTO visitorPass = null;
        VisitorPassLogEntity passLogEntity = null;
        VisitorPassEntity passEntity = null;
        for (int i = 0,length = visitorPassLogList.size(); i < length; i++){
            visitorPass = new VisitorPassDTO();
            Object[] objects = visitorPassLogList.get(i);
            passLogEntity = (VisitorPassLogEntity) objects[0];
            passEntity = (VisitorPassEntity) objects[1];
            visitorPass.setProjectName(passEntity.getProjectName());//项目名称
            visitorPass.setHouseAddress(passEntity.getHouseAddress());//房产地址
            visitorPass.setOwnerName(passEntity.getOwnerName());//业主姓名
            visitorPass.setOwnerPhone(passEntity.getOwnerPhone());//业主电话
            visitorPass.setVisitorName(passEntity.getVisitorName());//访客姓名
            visitorPass.setVisitorPhone(passEntity.getVisitorPhone());//访客电话
            visitorPass.setGuardName(passLogEntity.getGuardName());//扫码人姓名
            visitorPass.setPassDate(passLogEntity.getPassDate());//放行时间
            visitorPassDTOList.add(visitorPass);
        }
        return visitorPassDTOList;
    }

}

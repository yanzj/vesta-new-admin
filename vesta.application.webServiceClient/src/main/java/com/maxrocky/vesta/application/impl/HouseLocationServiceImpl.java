package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.houseLocation.HouseLocationResponse;
import com.maxrocky.vesta.application.houseLocation.model.HouseLocation;
import com.maxrocky.vesta.application.inf.HouseLocationService;
import com.maxrocky.vesta.application.inf.IBasicService;
import com.maxrocky.vesta.domain.model.HouseLocationEntity;
import com.maxrocky.vesta.domain.model.InterfaceLogEntity;
import com.maxrocky.vesta.domain.repository.HouseLocationCRMRepository;
import com.maxrocky.vesta.domain.repository.InterfaceLogRepository;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by dl on 2016/5/6.
 * 房屋位置查询
 */
@Service
public class HouseLocationServiceImpl implements HouseLocationService {
    @Autowired
    private HouseLocationCRMRepository houseLocationCRMRepository;
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;

    @Override
    public String houseLocation(){
        try {
            BasicServiceLocator basicService = new BasicServiceLocator();
            IBasicService iBasicService = basicService.getBasicHttpBinding_IBasicService();
            HouseLocationResponse response=iBasicService.houseLocationQuery();
            HouseLocation[] HouseLocation= response.getBody().getHouseLocationList();

            if (HouseLocation != null) {
                houseLocationCRMRepository.delete();
                for(HouseLocation house:HouseLocation){
                    HouseLocationEntity houseLocation=houseLocationCRMRepository.get(house.getId());
                    if(houseLocation!=null) {
                        //houseLocation.setId(house.getId());
                        houseLocation.setName(house.getName());
                        houseLocation.setModifyOn(new Date());
                        houseLocation.setType(house.getType());
                        houseLocationCRMRepository.update(houseLocation);
                        //调用日志
                        InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                        interfaceLogEntity.setId(IdGen.uuid());
                        interfaceLogEntity.setInterfaceName("查询房屋位置查询接口:更新数据！");
                        interfaceLogEntity.setCode("200");
                        interfaceLogEntity.setEntityName("room_location");
                        interfaceLogEntity.setErrorDate(new Date());
                        interfaceLogRepository.create(interfaceLogEntity);
                    }else{
                        HouseLocationEntity houseLocationEntity=new HouseLocationEntity();
                        houseLocationEntity.setId(house.getId());
                        houseLocationEntity.setName(house.getName());
                        houseLocationEntity.setCreateOn(new Date());
                        houseLocationEntity.setModifyOn(new Date());
                        houseLocationEntity.setType(house.getType());
                        houseLocationCRMRepository.create(houseLocationEntity);
                        //调用日志
                        InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                        interfaceLogEntity.setId(IdGen.uuid());
                        interfaceLogEntity.setInterfaceName("查询房屋位置查询接口:创建数据！");
                        interfaceLogEntity.setCode("200");
                        interfaceLogEntity.setEntityName("room_location");
                        interfaceLogEntity.setErrorDate(new Date());
                        interfaceLogRepository.create(interfaceLogEntity);
                    }
                }
            }
            //查询成功或失败
            if("1".equals(response.getHeader().getStatus())){//成功
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("房屋位置查询接口");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("room_location");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }else{
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("房屋位置查询接口！");
                interfaceLogEntity.setCode("500");
                interfaceLogEntity.setEntityName("room_location");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            return "200";
        }catch (Exception e) {
            e.printStackTrace();
            //调用日志
            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
            interfaceLogEntity.setId(IdGen.uuid());
            interfaceLogEntity.setInterfaceName("房屋位置查询接口！");
            interfaceLogEntity.setCode("500");
            interfaceLogEntity.setEntityName("room_location");
            interfaceLogEntity.setErrorDate(new Date());
            //interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
            interfaceLogRepository.create(interfaceLogEntity);
            return "500";
        }
    }
}

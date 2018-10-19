package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.InspectionService;
import com.maxrocky.vesta.application.inf.UpdateInspectionAdminService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.CustomerDeliveryEntity;
import com.maxrocky.vesta.domain.repository.HouseTransferRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Magic on 2017/6/30.
 */
@Service
public class UpdateInspectionAdminServiceImpl implements UpdateInspectionAdminService {
    @Autowired
    private HouseTransferRepository houseTransferRepository;
    @Autowired
    InspectionService inspectionService;
    @Override
    public ApiResult updateCustomerDeliveryList(String projectNum) {
        if(!StringUtil.isEmpty(projectNum)){
            try {
                List<CustomerDeliveryEntity> list = houseTransferRepository.getCustomerListById(projectNum);
                if (list != null && list.size() > 0) {
                    for (CustomerDeliveryEntity entity : list) {
                        if (!StringUtil.isEmpty(entity.getProgress())) {
                            if (!entity.getProgress().equals("preservation")) {
                                inspectionService.getInspection(null, null, entity);
                            }
                        }
                    }
                }
                return null;
            }catch (Exception e) {
                e.printStackTrace();
                throw new GeneralException("100", "系统处理错误");
            }
        }else{
            return ErrorResource.getError("tip_00000572");//项目编码不能为空
        }

    }
}

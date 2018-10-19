package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.Json.JsonUtil;
import com.maxrocky.vesta.application.inf.TransLogService;
import com.maxrocky.vesta.domain.model.TransLogEntity;
import com.maxrocky.vesta.domain.repository.TransLogRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by JillChen on 2016/1/26.
 */
@Service
public class TransLogServiceImpl implements TransLogService {


    @Autowired
    TransLogRepository transLogRepository;

    @Override
    public void saveOut(String vestaToken, Object object, TransLogEntity transLogEntity) {

        transLogEntity.setContentOut(JsonUtil.toJson(object));
        transLogEntity.setBackTime(DateUtils.getDate());
        transLogRepository.update(transLogEntity);
    }

    @Override
    public TransLogEntity saveIn(TransLogEntity transLogEntity, Object modelIn) {
        transLogEntity.setId(IdGen.uuid());
        transLogEntity.setCreateTime(DateUtils.getDate());
        transLogEntity.setContentIn(JsonUtil.toJson(modelIn));
        transLogRepository.save(transLogEntity);

        return transLogEntity;
    }
}

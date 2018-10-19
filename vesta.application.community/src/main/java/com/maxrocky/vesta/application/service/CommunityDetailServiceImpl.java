package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.model.CommunityDetailEntity;
import com.maxrocky.vesta.domain.repository.CommunityOverviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/05/13
 * Time: 18：00
 * Describe:
 */
@Service
public class CommunityDetailServiceImpl extends BaseServiceImpl<CommunityDetailEntity> implements CommunityDetailService {

    @Autowired
    CommunityOverviewRepository communityOverviewRepository;
    @Autowired
    ImgService imgService;


}

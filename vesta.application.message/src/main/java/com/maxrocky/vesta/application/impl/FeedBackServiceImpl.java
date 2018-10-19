package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.adminDTO.FeedBackDTO;
import com.maxrocky.vesta.application.inf.FeedBackService;
import com.maxrocky.vesta.domain.repository.AuthAgencyRepository;
import com.maxrocky.vesta.domain.repository.FeedBackRepository;
import com.maxrocky.vesta.domain.repository.PropertyImageRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Itzxs on 2018/5/23.
 */
@Service
public class FeedBackServiceImpl implements FeedBackService{

    @Autowired
    FeedBackRepository feedBackRepository;

    @Autowired
    AuthAgencyRepository authAgencyRepository;

    @Autowired
    PropertyImageRepository propertyImageRepository;

    @Override
    public List<FeedBackDTO> getFeedBackList(FeedBackDTO feedBackDTO,WebPage webPage){
        List<FeedBackDTO> feedBackDTOS = new ArrayList<>();
        List<Object[]> list = feedBackRepository.getFeedBackList(feedBackDTO.getCreateDate(),feedBackDTO.getUserId(),feedBackDTO.getPhone(),webPage);
        if(list != null && !list.isEmpty()){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            for (int i = 0; i < list.size(); i++) {
                FeedBackDTO feedBackDTO_DATA = new FeedBackDTO();
                feedBackDTO_DATA.setUserId(list.get(i)[0] == null ? "" : list.get(i)[0].toString());
                feedBackDTO_DATA.setPhone(list.get(i)[1] == null ? "" : list.get(i)[1].toString());
                feedBackDTO_DATA.setContent(list.get(i)[2] == null ? "" : list.get(i)[2].toString());
                feedBackDTO_DATA.setCreateDate(list.get(i)[3] == null ? "" : list.get(i)[3].toString().substring(0,list.get(i)[3].toString().length()-2));
                feedBackDTO_DATA.setUserName(list.get(i)[4] == null ? "" : list.get(i)[4].toString());
                feedBackDTO_DATA.setId(list.get(i)[5] == null ? "" : list.get(i)[5].toString());
                feedBackDTOS.add(feedBackDTO_DATA);
            }
        }
        return feedBackDTOS;
    }

    @Override
    public boolean checkRoot(String userId){
        return authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000000",userId);
    }

    @Override
    public FeedBackDTO getDetail(FeedBackDTO feedBackDTO){
        feedBackDTO.setImg(propertyImageRepository.getFeedBackImage(feedBackDTO.getId()));
        return feedBackDTO;
    }

}

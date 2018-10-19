package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.adminDTO.FeedBackDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Itzxs on 2018/5/23.
 */
public interface FeedBackService {

    /**
     * 意见反馈列表
     * @param feedBackDTO
     * @return
     */
    List<FeedBackDTO> getFeedBackList(FeedBackDTO feedBackDTO,WebPage webPage);

    /**
     * 判断是否为总部权限
     * @param userId
     * @return
     */
    boolean checkRoot(String userId);

    FeedBackDTO getDetail(FeedBackDTO feedBackDTO);
}

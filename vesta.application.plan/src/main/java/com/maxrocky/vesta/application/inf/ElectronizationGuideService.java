package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.adminDTO.ElectronizationGuideDTO;
import com.maxrocky.vesta.application.adminDTO.GuideDTO;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by hp on 2018/5/23.
 * 电子化指引Service接口
 */
public interface ElectronizationGuideService {

    //根据查询条件获取电子化指引详情
    List<ElectronizationGuideDTO> getElectronizationGuideList(ElectronizationGuideDTO electronizationGuideDTO,WebPage webPage);
    //修改电子化指引
    String updateElectronizationGuide(GuideDTO guideDTO, MultipartFile file);
    //删除电子化指引
    String delElectronizationGuide(GuideDTO guideDTO);
    //新增电子化指引
    String saveElectronizationGuide(GuideDTO guideDTO, MultipartFile file);

}

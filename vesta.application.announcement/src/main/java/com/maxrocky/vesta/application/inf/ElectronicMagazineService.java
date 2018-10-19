package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.ElectronicMagazineDTO;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 电子杂志Service
 * Created by WeiYangDong on 2017/9/25.
 */
public interface ElectronicMagazineService {

    /**
     * 通过杂志ID获取电子杂志详情
     * @param id 杂志ID
     * @return ElectronicMagazineDTO
     */
    ElectronicMagazineDTO getElectronicMagazineById(String id) throws InvocationTargetException, IllegalAccessException;

    /**
     * 获取电子杂志列表
     */
    List<ElectronicMagazineDTO> getElectronicMagazineList(ElectronicMagazineDTO electronicMagazineDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException;

    /**
     * 保存或更新电子杂志信息
     */
    void saveOrUpdateElectronicMagazineInfo(ElectronicMagazineDTO electronicMagazineDTO);

    /**
     * 物理删除电子杂志
     */
    void deleteElectronicMagazineInfo(String id);

    /**
     * 图片上传
     */
    public String imgUpload(MultipartFile multipartFile);
}

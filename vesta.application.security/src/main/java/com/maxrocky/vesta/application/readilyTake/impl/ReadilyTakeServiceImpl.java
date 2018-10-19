package com.maxrocky.vesta.application.readilyTake.impl;

import com.maxrocky.vesta.application.readilyTake.DTO.*;
import com.maxrocky.vesta.application.readilyTake.inf.ReadilyTakeService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.project.repository.SecurityProjectRepository;
import com.maxrocky.vesta.domain.readilyTake.model.ReadilyRecordEntity;
import com.maxrocky.vesta.domain.readilyTake.model.ReadilyTakeEntity;
import com.maxrocky.vesta.domain.readilyTake.model.SecurityImageEntity;
import com.maxrocky.vesta.domain.readilyTake.repository.ReadilyTakeRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportExcel;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Magic on 2017/6/9.
 */
@Service
public class ReadilyTakeServiceImpl implements ReadilyTakeService {

    @Autowired
    ReadilyTakeRepository readilyTakeRepository;

    @Autowired
    SecurityProjectRepository securityProjectRepository;

    /**
     * Describe:安全app 查询随手拍列表
     * CreateBy:Magic
     * CreateOn:2017-06-09
     */
    @Override
    public List<ReadilyTakeListDTO> getReadilyTakeList(ReadilyTakeConditionDTO readilyTakeConditionDTO, WebPage webPage, UserInformationEntity userInformationEntity,List<String> agencyidList) {

        Map map = new HashMap();
        map.put("startDate", readilyTakeConditionDTO.getStartDate());//开始日期
        map.put("endDate", readilyTakeConditionDTO.getEndDate());//结束时间
        map.put("projectId", readilyTakeConditionDTO.getProjectId());//项目id
        map.put("state", readilyTakeConditionDTO.getState());//状态
        map.put("severityLevel", "");//严重等级
        map.put("examinationParts", "");//检查部位
        map.put("createName",readilyTakeConditionDTO.getCreateName());//创建人
        if(!StringUtil.isEmpty(readilyTakeConditionDTO.getSeverityLevel())){
            map.put("severityLevel", "%"+readilyTakeConditionDTO.getSeverityLevel()+"%");//严重等级
        }
        if(!StringUtil.isEmpty(readilyTakeConditionDTO.getExaminationParts())){
            map.put("examinationParts", "%"+readilyTakeConditionDTO.getExaminationParts()+"%");//检查部位
        }
        String agencyListString="";
        for(String agency:agencyidList){
            if(!StringUtil.isEmpty(agency)){
                agencyListString=agencyListString+"'"+agency+"',";
            }
        }
        if(!StringUtil.isEmpty(agencyListString)){
            agencyListString=agencyListString.substring(0,agencyListString.length()-1);
        }
        map.put("agencyListString", agencyListString);//层级idList

        List<ReadilyTakeListDTO> retList=new ArrayList<ReadilyTakeListDTO>();
        List<Object[]> list = readilyTakeRepository.getReadilyTakeList(map,webPage);

        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                ReadilyTakeListDTO readilyTakeList=new ReadilyTakeListDTO();
                readilyTakeList.setPatId(obj[0] == null ? "" : obj[0].toString());//随手拍id
                readilyTakeList.setProjectName(obj[1] == null ? "" : obj[1].toString());//项目公司
                readilyTakeList.setCreatName(obj[2] == null ? "" : obj[2].toString());//创建人
                readilyTakeList.setCreatDate(obj[3] == null ? "" : DateUtils.format((Date)obj[3], DateUtils.FORMAT_LONG));//创建时间
                readilyTakeList.setState(obj[4] == null ? "" : obj[4].toString());//状态
                readilyTakeList.setExaminationParts(obj[5] == null ? "" : obj[5].toString());//检查部位
                readilyTakeList.setSeverityLevel(obj[6] == null ? "" : obj[6].toString());//严重等级
                readilyTakeList.setProjectId(obj[7] == null ? "" : obj[7].toString());// 项目公司ID
                retList.add(readilyTakeList);
            }
        }
        return retList;
    }

    @Override
    public void readilyTakeExcel(String title, String[] headers, ServletOutputStream out, ReadilyTakeConditionDTO readilyTakeConditionDTO, UserInformationEntity userInformationEntity,List<String> angencyIdList) {
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        List<ReadilyTakeListDTO> readilyTakeListDTOS = this.getReadilyTakeList(readilyTakeConditionDTO,webPage,userInformationEntity,angencyIdList);

        // 导出数据  "项目公司", "创建时间", "检查部位", "严重等级", "整改状态"
        ExportExcel<ReadilyTakeExcelDTO> ex = new ExportExcel<ReadilyTakeExcelDTO>();
        List<ReadilyTakeExcelDTO> ReadilyTakeExcelDTOs = new ArrayList<ReadilyTakeExcelDTO>();
        if (readilyTakeListDTOS != null && readilyTakeListDTOS.size() > 0) {
            for (ReadilyTakeListDTO readilyTakeListDTO : readilyTakeListDTOS) {
                ReadilyTakeExcelDTO readilyTakeExcelDTO = new ReadilyTakeExcelDTO();
                readilyTakeExcelDTO.setProjectName(readilyTakeListDTO.getProjectName());
                readilyTakeExcelDTO.setCreatName(readilyTakeListDTO.getCreatName());
                readilyTakeExcelDTO.setCreatDate(readilyTakeListDTO.getCreatDate());
                readilyTakeExcelDTO.setExaminationParts(readilyTakeListDTO.getExaminationParts());
                readilyTakeExcelDTO.setSeverityLevel(readilyTakeListDTO.getSeverityLevel());
//                状态reform与twiceReform整改中finshed已完成waiting待确认
                if("reform".equals(readilyTakeListDTO.getState())){
                    readilyTakeListDTO.setState("整改中");
                }
                if("twiceReform".equals(readilyTakeListDTO.getState())){
                    readilyTakeListDTO.setState("二次整改");
                }
                if("finshed".equals(readilyTakeListDTO.getState())){
                    readilyTakeListDTO.setState("已完成");
                }
                if("waiting".equals(readilyTakeListDTO.getState())){
                    readilyTakeListDTO.setState("待确认");
                }
                readilyTakeExcelDTO.setState(readilyTakeListDTO.getState());

                ReadilyTakeExcelDTOs.add(readilyTakeExcelDTO);
            }
        }
        ex.exportExcel(title, headers, ReadilyTakeExcelDTOs, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    @Override
    public ReadilyTakeDetailDTO getReadilyTake(String patId) {
        Object[] obj = readilyTakeRepository.getReadilyTake(patId);

        //根据id查询随手拍图片信息
        List<String> list = new ArrayList<>();
        list.add(patId);
        List<Object[]> imagelist=readilyTakeRepository.getSecurityImageList(list);

        List<Object[]> imgList= new ArrayList<>();

        //根据id 查询整改记录
        List<Object[]> rectificationDescriptionList=readilyTakeRepository.getRectificationDescriptionList(patId);
        if(rectificationDescriptionList!=null && rectificationDescriptionList.size()>0){
            List<String> rectificationDescriptionIdList=new ArrayList<String>();
            for(Object[] obj1 : rectificationDescriptionList){
                rectificationDescriptionIdList.add(obj1[0] == null ? "" : obj1[0].toString());
            }
            //根据idList查询随手拍整改图片信息
            imgList = readilyTakeRepository.getSecurityImageList(rectificationDescriptionIdList);
        }
        if(obj != null){
            ReadilyTakeDetailDTO readilyTakeDetailDTO=new ReadilyTakeDetailDTO();
            readilyTakeDetailDTO.setPatId(obj[0] == null ? "" : obj[0].toString());//随手拍id
            readilyTakeDetailDTO.setProjectId(obj[1] == null ? "" : obj[1].toString());//项目公司Id
            readilyTakeDetailDTO.setProjectName(obj[2] == null ? "" : obj[2].toString());//项目公司
            readilyTakeDetailDTO.setState(obj[3] == null ? "" : obj[3].toString());//状态
            readilyTakeDetailDTO.setExaminationParts(obj[4] == null ? "" : obj[4].toString());//检查部位
            readilyTakeDetailDTO.setSeverityLevel(obj[5] == null ? "" : obj[5].toString());//严重等级
            readilyTakeDetailDTO.setCreateName(obj[6] == null ? "" : obj[6].toString());//创建人姓名
            readilyTakeDetailDTO.setCreateDate(obj[7] == null ? "" : DateUtils.format((Date) obj[7]));//创建时间
            readilyTakeDetailDTO.setDescription(obj[8] == null ? "" : obj[8].toString());//问题描述
            readilyTakeDetailDTO.setInternalPeople(obj[9] == null ? "" : obj[9].toString());//总包负责人名字
            readilyTakeDetailDTO.setExternalPeople(obj[10] == null ? "" : obj[10].toString());//监理负责人名字
            readilyTakeDetailDTO.setSecurityAdministrator(obj[11] == null ? "" : obj[11].toString());//项目安全员
            readilyTakeDetailDTO.setRectificationDate(obj[12] == null ? "" : DateUtils.format((Date) obj[12]));//整改时间
            readilyTakeDetailDTO.setRectificationPeople(obj[13] == null ? "" : obj[13].toString());//整改负责人
            readilyTakeDetailDTO.setSupplementaryDescription(obj[14] == null ? "" : obj[14].toString());//补充描述
            readilyTakeDetailDTO.setAddress(obj[15] == null ? "" : obj[15].toString());//地址
            readilyTakeDetailDTO.setModifyDate(obj[16] == null ? "" : DateUtils.format((Date) obj[16]));//修改时间
                //图片删除list
                List<Object[]> del=new ArrayList<>();
                //遍历图片
                if (imagelist != null && !imagelist.isEmpty()) {
                    List<ImageDTO> imgJsonList = new ArrayList<ImageDTO>();
                    for (Object[] img : imagelist) {
                        //判断外键是否相等随手拍ID
                        if (img[0].toString().equals(readilyTakeDetailDTO.getPatId())) {
                            //随手拍图片
                            if ("1".equals(img[3].toString())) {
                                ImageDTO imageDTO = new ImageDTO();
                                imageDTO.setId(img[1] == null ? "" : img[1].toString());
                                imageDTO.setImageUrl(img[2] == null ? "" : img[2].toString());
                                imageDTO.setType(img[3] == null ? "" : img[3].toString());
                                imageDTO.setBusinessId(readilyTakeDetailDTO.getPatId());
                                imgJsonList.add(imageDTO);
                            }
                            del.add(img);
                        }
                    }
                    imagelist.removeAll(del);
                    readilyTakeDetailDTO.setImgList(imgJsonList);//随手拍图片
                }
                if(rectificationDescriptionList != null && rectificationDescriptionList.size()>0){
                    List <RectificationDescriptionDTO> rectificationDescriptionDTOS = new ArrayList<RectificationDescriptionDTO>();
                    for(Object[] obj1 : rectificationDescriptionList){
                        if(obj1[1].toString().equals(readilyTakeDetailDTO.getPatId())){
                            RectificationDescriptionDTO rectificationDescriptionDTO = new RectificationDescriptionDTO();
                            if(imgList != null && imgList.size()>0){
                                List<ImageDTO> imageJsonList = new ArrayList<ImageDTO>();
                                for(Object[] image : imgList){
                                    //判断整改Id是否等于整改图片id
                                    if(obj1[0].toString().equals(image[0].toString())){
                                        //整改图片
                                        if("2".equals(image[3].toString())){
                                            ImageDTO imageDTO = new ImageDTO();
                                            imageDTO.setBusinessId(image[0].toString());
                                            imageDTO.setId(image[1] == null ? "" : image[1].toString());
                                            imageDTO.setImageUrl(image[2] == null ? "" : image[2].toString());
                                            imageDTO.setType(image[3] == null ? "" : image[3].toString());
                                            imageJsonList.add(imageDTO);
                                        }
                                        del.add(image);
                                    }
                                }
                                rectificationDescriptionDTO.setImageList(imageJsonList);
                            }
                            rectificationDescriptionDTO.setId(readilyTakeDetailDTO.getPatId());
                            rectificationDescriptionDTO.setRectificationDescription(obj1[2] == null ? "" : obj1[2].toString());
                            rectificationDescriptionDTOS.add(rectificationDescriptionDTO);
                        }
                    }
                    readilyTakeDetailDTO.setRectificationDescriptionList(rectificationDescriptionDTOS);//整改描述
                    imagelist.removeAll(del);
                }
                return readilyTakeDetailDTO;
        }
        return null;
    }

    @Override
    public ReadilyTakeRoleDTO getReadilyTakeRole(String userId, String projectId) {
        ReadilyTakeRoleDTO readilyTakeRoleDTO = new ReadilyTakeRoleDTO();
        //项目安全员 4
        //总包负责人 6
        //监理负责人 7
        List<String> roleList = readilyTakeRepository.queryRoleById(userId,projectId);
        if(roleList != null && roleList.size()>0){
            for(String role:roleList){
                if("4".equals(role)){
                    readilyTakeRoleDTO.setDeleProblem("1");//如果是安全员  则有废弃权限
                    readilyTakeRoleDTO.setDealProblem("1");// 整改权限
                }
                if("6".equals(role)){
                    readilyTakeRoleDTO.setDealProblem("1");//如果是总包负责人  则有整改权限
                }
                if("7".equals(role)){
                    readilyTakeRoleDTO.setDealProblem("1");//如果是监理负责人  则有整改权限
                    readilyTakeRoleDTO.setAffirmProblem("1");//确认整改完成权限
                }
            }
        }
        return readilyTakeRoleDTO;
    }

    @Override
    public boolean updateReadilyTakeRole(String patId, String state, String supplementaryDescription) {
        ReadilyTakeEntity readilyTakeEntity = readilyTakeRepository.getReadilyTakeById(patId);
        if(!"".equals(state) && !StringUtil.isEmpty(state)){
            readilyTakeEntity.setState(state);
        }
        if(!"".equals(supplementaryDescription) && !StringUtil.isEmpty(supplementaryDescription)){
            readilyTakeEntity.setSupplementaryDescription(supplementaryDescription);
            readilyTakeEntity.setState("0");
        }
        readilyTakeEntity.setModifyDate(new Date());
        try {
            readilyTakeRepository.updateReadilyTake(readilyTakeEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateReadilyTake(ReadilyTakeRectifyDTO readilyTakeRectifyDTO , MultipartFile[] multipartFile) {

        ReadilyRecordEntity readilyRecordEntity = new ReadilyRecordEntity();
        readilyRecordEntity.setId(IdGen.uuid());
        readilyRecordEntity.setBusinessId(readilyTakeRectifyDTO.getPatId());
        readilyRecordEntity.setModifyOn(new Date());
        if(readilyTakeRectifyDTO.getDescriptions() != null && !"".equals(readilyTakeRectifyDTO.getDescriptions())) {
            readilyRecordEntity.setRectificationDescription(readilyTakeRectifyDTO.getDescriptions());
        }
        readilyTakeRepository.saveReadilyTakeRectify(readilyRecordEntity);

        if (multipartFile.length>0 && multipartFile != null){
            SecurityImageEntity imageDTO = new SecurityImageEntity();
            for(MultipartFile multipartFile1: multipartFile){
                String reviewImgFileUrl = imgUpload(multipartFile1);
                if (reviewImgFileUrl.lastIndexOf("/") != reviewImgFileUrl.length()-1){
                    imageDTO.setUrl(reviewImgFileUrl);
                    imageDTO.setId(IdGen.uuid());
                    imageDTO.setBusinessId(readilyRecordEntity.getId());
                    imageDTO.setType("2");//随手拍整改图片
                    imageDTO.setState("1");
                    imageDTO.setCreateOn(new Date());
                    imageDTO.setModifyOn(new Date());
                    readilyTakeRepository.saveImage(imageDTO);
                }
            }
        }

        return true;
    }



    public String imgUpload(MultipartFile multipartFile){
        String imgUrl = "";
        try{
            //处理图片上传
            if (null != multipartFile){
                ImgService imgService = new ImgServiceImpl();
                //图片地址特殊处理
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                imgUrl = imgService.uploadAdminImage(multipartFile, ImgType.VOTEIMG);
                imgUrl = urlTitle + imgUrl.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return imgUrl;
    }
}

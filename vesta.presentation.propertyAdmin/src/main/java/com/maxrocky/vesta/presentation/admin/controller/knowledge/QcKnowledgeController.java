package com.maxrocky.vesta.presentation.admin.controller.knowledge;

import com.maxrocky.vesta.application.DTO.adminDTO.QcKnowledgeDTO;
import com.maxrocky.vesta.application.admin.dto.UploadImage;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.QcKnowledgeService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.QCKnowledgeEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Itzxs on 2018/6/8.
 */
@Controller
@RequestMapping("/qcKnowledge")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class QcKnowledgeController {

    @Autowired
    QcKnowledgeService qcKnowledgeService;

    @Autowired
    ImgService imgService;

    @Autowired
    AuthAgencyService authAgencyService;

    @RequestMapping("/getKnowledgeList.html")
    public String getKnowledgeList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid QcKnowledgeDTO qcKnowledgeDTO, Model model, WebPage webPage){
        try{
            CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
            List<QCKnowledgeEntity> qcKnowledgeEntities = qcKnowledgeService.getKnowledgeList(qcKnowledgeDTO,webPage);
            model.addAttribute("list",qcKnowledgeEntities);
            model.addAttribute("qcKnowledgeDTO",qcKnowledgeDTO);
            model.addAttribute("webPage",webPage);
            model.addAttribute("userId",userInformationEntity.getStaffId());
            List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
            //知识库功能点   搜索 QCH40010141   创建、编辑、删除知识库 QCH40010142    详情 QCH40010143
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010141":
                        checkAuthFunctionDTO.setQch40010141("Y");
                        break;
                    case "QCH40010142":
                        checkAuthFunctionDTO.setQch40010142("Y");
                        break;
                    case "QCH40010143":
                        checkAuthFunctionDTO.setQch40010143("Y");
                        break;
                }
            }
            model.addAttribute("function",checkAuthFunctionDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/qcKnowledge/qcKnowledgeManage";
    }

    @RequestMapping("/editKnowledge.html")
    public String editKnowledge(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid QcKnowledgeDTO qcKnowledgeDTO, Model model){
        try{
            QCKnowledgeEntity qcKnowledgeEntity = null;
            if(qcKnowledgeDTO != null && qcKnowledgeDTO.getId() != null && !"".equals(qcKnowledgeDTO.getId())){
                qcKnowledgeEntity = qcKnowledgeService.getKnowledgeById(Integer.parseInt(qcKnowledgeDTO.getId()));
            }
            model.addAttribute("knowledge",qcKnowledgeEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/qcKnowledge/editKnowledge";
    }

    @RequestMapping("/edit.html")
    public String edit(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @RequestParam(value = "file") MultipartFile file,@Valid QcKnowledgeDTO qcKnowledgeDTO, Model model){
        try{
            qcKnowledgeService.editKnowledge(qcKnowledgeDTO,file,userInformationEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:../qcKnowledge/getKnowledgeList.html";
    }

    @RequestMapping("/delKnowledge.html")
    public String delKnowledge(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,@Valid QcKnowledgeDTO qcKnowledgeDTO, Model model){
        try{
            qcKnowledgeService.delKnowledge(qcKnowledgeDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:../qcKnowledge/getKnowledgeList.html";
    }

    @RequestMapping("/detailKnowledge.html")
    public String detailKnowledge(String id, Model model){
        QcKnowledgeDTO qcKnowledgeDTO = qcKnowledgeService.getKnowledgeDTOById(Integer.parseInt(id));
        model.addAttribute("qcKnowledgeDTO",qcKnowledgeDTO);
        return "/qcKnowledge/detailKnowledge";
    }

    /**
     * 上传图片
     *Created by yuanyn on 2017/6/14.
     */
    @RequestMapping(value = "/uploadImage")
    @ResponseBody
    public void createMerPicture(@ModelAttribute("uploadImage") @Valid UploadImage uploadImage, @RequestParam(value = "file", required = false) MultipartFile file,
                                 BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> resultmap = new HashedMap();
        try {
            LinkedList<MultipartFile> upfileMap = (LinkedList<MultipartFile>) ((DefaultMultipartHttpServletRequest) request).getMultiFileMap().get("upfile");
            for (MultipartFile tempfile : upfileMap) {
                //图片上传
                String fileName = imgService.uploadAdminImage(tempfile, ImgType.ACTIVITY);
                //图片地址特殊处理
//                String urlTitle = "http://211.94.93.223/images/";

                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;

//                String fileNameNote = fileName.replace("/opt/image.server/images/images_source/houserental/", "");
//                fileName = urlTitle + fileName.replace("/opt/image.server/images/images_source/", "");
                String fileNameNote = fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL,"");
                fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                resultmap.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
                resultmap.put("url",fileName);
                resultmap.put("size", tempfile.getSize());
                resultmap.put("title", fileNameNote);
                resultmap.put("type", fileName.substring(fileName.lastIndexOf(".")));
                resultmap.put("original", fileNameNote);
                String jsonstr = "";
                jsonstr ="{\"state\":\""+resultmap.get("state")+"\",\"url\":\""+resultmap.get("url")+"\",\"title\":\""+resultmap.get("title")+"\",\"original\":\""+resultmap.get("original")+"\"}";
                System.out.println((new StringBuilder("返回Json信息：")).append(jsonstr).toString());
                response.setContentType("text/html;charset=UTF-8");
                ServletOutputStream os ;
                try {
                    os = response.getOutputStream();
                    os.write(jsonstr.getBytes("UTF-8"));
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
            resultmap.put("state", "文件上传失败!");
            resultmap.put("url", "");
            resultmap.put("title", "");
            resultmap.put("original", "");
            String jsonstr = "";
            jsonstr = "{\"state\":\"" + resultmap.get("state") + "\",\"url\":\"" + resultmap.get("url") + "\",\"title\":\"" + resultmap.get("title") + "\",\"original\":\"" + resultmap.get("original") + "\"}";
            System.out.println((new StringBuilder("返回Json信息：")).append(jsonstr).toString());
            response.setContentType("text/html;charset=UTF-8");
        }
    }

}

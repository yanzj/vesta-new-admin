package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.QuestionQuerry;
import com.maxrocky.vesta.application.DTO.admin.QuestionnaireDTO;
import com.maxrocky.vesta.application.DTO.admin.QuestionnaireQuerry;
import com.maxrocky.vesta.application.inf.QuestionnaireService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.QuestionTitleEntity;
import com.maxrocky.vesta.domain.model.QuestionTitleSelectEntity;
import com.maxrocky.vesta.domain.model.QuestionnaireEntity;
import com.maxrocky.vesta.domain.model.SubjectEntity;
import com.maxrocky.vesta.domain.repository.QuestionTitleRepository;
import com.maxrocky.vesta.domain.repository.QuestionTitleSelectRepository;
import com.maxrocky.vesta.domain.repository.QuestionnaireRepository;
import com.maxrocky.vesta.domain.repository.SubjectRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.collections.map.HashedMap;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 */
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{
    @Autowired
    QuestionnaireRepository questionnaireRepository;
    @Autowired
    QuestionTitleRepository questionTitleRepository;
    @Autowired
    QuestionTitleSelectRepository questionTitleSelectRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public List<QuestionnaireDTO> getAll(QuestionnaireQuerry q, WebPage webPage) {
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("titleName",q.getTitleName());
        paramsMap.put("status",q.getStatus());
        List<Object> list = questionnaireRepository.getAll(paramsMap,webPage);
        List<QuestionnaireDTO> dto = new ArrayList<>();
        for (Object l : list){
            QuestionnaireDTO idto = new QuestionnaireDTO();
            Object[] obj = (Object[]) l;
            idto.setQuestionId((String) obj[0] == null ? "" : (String) obj[0]);
            idto.setQuestName((String)obj[1] == null ? "" : (String) obj[1]);
            idto.setProjectName((String) obj[2] == null ? "" : (String) obj[2]);
            idto.setQuestStatus((String) obj[3] == null ? "" : (String) obj[3]);
            idto.setNum((String) obj[4] == null ? "0" : (String)obj[4]);
            idto.setCreateOn((Date)obj[5] == null ? null : (Date) obj[5]);
            dto.add(idto);
        }
        return dto;
    }

    @Override
    public QuestionnaireEntity getDetail(String id) {
        QuestionnaireEntity q = null;
        if(id != null){
             q = questionnaireRepository.getDetail(id);
        }

        return q;
    }

    @Override
    public void test(QuestionQuerry q) {

        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setQuestId(IdGen.uuid());//ID
        questionnaireEntity.setCityId(q.getCityId());
        questionnaireEntity.setProjectName(q.getProjectNum());
        questionnaireEntity.setQuestName(q.getQuestName());
        questionnaireEntity.setRuleNum(q.getRuleNum());
        questionnaireEntity.setBeginDate(DateUtils.parse3(q.getBeginDate()));
        questionnaireEntity.setEndDate(DateUtils.parse3(q.getEndDate()));
        questionnaireEntity.setQuestStatus("1");
        questionnaireEntity.setDeleteStatus(0);
        questionnaireEntity.setCreateOn(new Date());
        questionnaireEntity.setNum("0");
        questionnaireRepository.create(questionnaireEntity);  //保存主表信息，准备添加标题表信息

        //新增问答标题
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setSubjectId(IdGen.uuid());
        subjectEntity.setQuestId(questionnaireEntity.getQuestId());
        subjectEntity.setSubjectName(q.getWdName());
        subjectRepository.create(subjectEntity);

        String json = q.getTt();
        try {
            JSONArray jsonArray = new JSONArray(json);
            int iSize = jsonArray.length();
            for (int i = 0; i < iSize; i++) {
                //获取标题
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                QuestionTitleEntity questionTitleEntity = new QuestionTitleEntity();
                questionTitleEntity.setQuestionTitleId(IdGen.uuid());
                questionTitleEntity.setQuestId(questionnaireEntity.getQuestId());
                questionTitleEntity.setCreateOn(DateUtils.addOneSecond(new Date()));
                questionTitleEntity.setQuestionTitleName(jsonObj.get("name").toString());
                questionTitleEntity.setQuestionOpt(Integer.parseInt(jsonObj.get("radios").toString()));
                questionTitleEntity.setCountNum(i);
                questionTitleRepository.create(questionTitleEntity); //新增表头

                JSONArray sel = new JSONArray(jsonObj.get("sel").toString());
                for(int j = 0; j < sel.length(); j++){
                    //获取子菜单
                    JSONObject jsonObjs = sel.getJSONObject(j);
                    QuestionTitleSelectEntity questionTitleSelectEntity = new QuestionTitleSelectEntity();
                    questionTitleSelectEntity.setQuestionTitleSelectId(IdGen.uuid());
                    questionTitleSelectEntity.setQuestionTitleId(questionTitleEntity.getQuestionTitleId());
                    questionTitleSelectEntity.setSelectName(jsonObjs.get("m").toString());

                    if (null != jsonObjs.get("url")){
                        questionTitleSelectEntity.setPictureUrl(jsonObjs.get("url").toString());
                    }

                    questionTitleSelectEntity.setNumber(0);
                    questionTitleSelectEntity.setCreateOn(DateUtils.addOneSecond(new Date()));
                    questionTitleSelectEntity.setCountNum(j);
                    questionTitleSelectRepository.create(questionTitleSelectEntity);//新增子菜单
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public String addImage(MultipartFile productPicFile) {
        String imgUpload = imgUpload(productPicFile);
        System.out.println("-------"+imgUpload);
        return imgUpload;
    }

    @Override
    public void updateTpKs() {
        questionnaireRepository.updateTpKs(DateUtils.format(new Date(),"yyyy-MM-dd"));
    }

    @Override
    public void updateTpJs() {
        questionnaireRepository.updateTpJs(DateUtils.format(new Date(),"yyyy-MM-dd"));
    }

    /**
     * 图片上传
     */
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

    /**
     * 逻辑删除调查问卷
     */
    public void deleteQuest(String id){
        QuestionnaireEntity questionnaireEntity = getDetail(id);
        if(null != questionnaireEntity){
            questionnaireEntity.setDeleteStatus(1);
            questionnaireRepository.update(questionnaireEntity);
        }
    }
}

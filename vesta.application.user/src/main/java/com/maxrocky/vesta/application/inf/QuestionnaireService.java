package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.QuestionQuerry;
import com.maxrocky.vesta.application.DTO.admin.QuestionnaireDTO;
import com.maxrocky.vesta.application.DTO.admin.QuestionnaireDetailDTO;
import com.maxrocky.vesta.application.DTO.admin.QuestionnaireQuerry;
import com.maxrocky.vesta.domain.model.QuestionnaireEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 */
public interface QuestionnaireService {
    List<QuestionnaireDTO> getAll(QuestionnaireQuerry q, WebPage webPage);

    QuestionnaireEntity getDetail(String id);

    void test(QuestionQuerry q);

    String addImage(MultipartFile productPicFile);

    void updateTpKs();

    void updateTpJs();

    /**
     * 逻辑删除调查问卷
     */
    void deleteQuest(String id);
}

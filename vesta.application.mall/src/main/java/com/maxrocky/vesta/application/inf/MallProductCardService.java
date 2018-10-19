package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.MallProductCardDTO;
import com.maxrocky.vesta.domain.model.MallProductCardEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/5.
 */
public interface MallProductCardService {

    Map<String,Object> add(String id, InputStream fis);

    void update(MallProductCardDTO c);

    List<MallProductCardDTO> getAll(String mallId, WebPage webPage);
}

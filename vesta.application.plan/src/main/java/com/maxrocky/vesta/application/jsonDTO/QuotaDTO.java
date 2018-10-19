package com.maxrocky.vesta.application.jsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/23.
 */
public class QuotaDTO {
    private String quotaDesc;        //指标说明
    private String qualified;        //是否合格
    private List<MaterialImageDTO> imageList;  //图片列表

    public List<MaterialImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<MaterialImageDTO> imageList) {
        this.imageList = imageList;
    }

    public String getQualified() {
        return qualified;
    }

    public void setQualified(String qualified) {
        this.qualified = qualified;
    }

    public String getQuotaDesc() {
        return quotaDesc;
    }

    public void setQuotaDesc(String quotaDesc) {
        this.quotaDesc = quotaDesc;
    }
}

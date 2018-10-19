package com.maxrocky.vesta.application.contest.DTO;

import com.maxrocky.vesta.application.readilyTake.DTO.ImageDTO;

import java.util.List;

/**
 * 安全隐患比武DTO
 * Created by yuanyn on 2017/9/1.
 */
public class ContestListDTO {
    private String id;//隐患id
    private String projectName;//项目公司
    private String projectId;//项目公司Id
    private String creatName;//创建人
    private String creatDate;//创建时间
    private String endDate;//结束时间
    private String describe; //隐患描述
    private List<ImageDTO> image; //隐患图片链接

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCreatName() {
        return creatName;
    }

    public void setCreatName(String creatName) {
        this.creatName = creatName;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<ImageDTO> getImage() {
        return image;
    }

    public void setImage(List<ImageDTO> image) {
        this.image = image;
    }
}

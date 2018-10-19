package com.maxrocky.vesta.application.DTO.jsonDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhaowen on 2016/8/29.14:56
 * Describe:
 */
public class SearchClassificationDTO {

    private String oneType;//一级分类
    private String twoType;//二级分类
    private String threeType;//三级分类
    private String firstTypeName; // 一级分类名称
    private String secondTyoeName; // 二级分类名称
    private String thirdTypeName; // 三级分类名称
    private String alias;//三级分类别名

    private Map<String,String> firstLevels; // 一级分类列表

    private Map<String,String> secondLevels; // 二级分类列表

    private Map<String,String> thirdLevels; // 二级分类列表

    public String getOneType() {
        return oneType;
    }

    public void setOneType(String oneType) {
        this.oneType = oneType;
    }

    public String getTwoType() {
        return twoType;
    }

    public void setTwoType(String twoType) {
        this.twoType = twoType;
    }

    public String getThreeType() {
        return threeType;
    }

    public void setThreeType(String threeType) {
        this.threeType = threeType;
    }

    public String getFirstTypeName() {
        return firstTypeName;
    }

    public void setFirstTypeName(String firstTypeName) {
        this.firstTypeName = firstTypeName;
    }

    public String getSecondTyoeName() {
        return secondTyoeName;
    }

    public void setSecondTyoeName(String secondTyoeName) {
        this.secondTyoeName = secondTyoeName;
    }

    public String getThirdTypeName() {
        return thirdTypeName;
    }

    public void setThirdTypeName(String thirdTypeName) {
        this.thirdTypeName = thirdTypeName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Map<String, String> getFirstLevels() {
        return firstLevels;
    }

    public void setFirstLevels(Map<String, String> firstLevels) {
        this.firstLevels = firstLevels;
    }

    public Map<String, String> getSecondLevels() {
        return secondLevels;
    }

    public void setSecondLevels(Map<String, String> secondLevels) {
        this.secondLevels = secondLevels;
    }

    public Map<String, String> getThirdLevels() {
        return thirdLevels;
    }

    public void setThirdLevels(Map<String, String> thirdLevels) {
        this.thirdLevels = thirdLevels;
    }
}

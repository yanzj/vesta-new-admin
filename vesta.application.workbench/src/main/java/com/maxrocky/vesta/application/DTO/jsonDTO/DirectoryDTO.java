package com.maxrocky.vesta.application.DTO.jsonDTO;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/22.
 */
public class DirectoryDTO {


    private String section;     //部门

    private List<MobileBookDTO> mobileBook;     //电话本

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<MobileBookDTO> getMobileBook() {
        return mobileBook;
    }

    public void setMobileBook(List<MobileBookDTO> mobileBook) {
        this.mobileBook = mobileBook;
    }
}

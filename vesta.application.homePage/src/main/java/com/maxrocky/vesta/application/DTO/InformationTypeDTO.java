package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Annie on 2016/2/22.
 */
public class InformationTypeDTO {
    private List<InformationDTO> propertyService;
    private List<InformationDTO> publicService;

    public InformationTypeDTO(){
        this.propertyService = new ArrayList<InformationDTO>();
        this.publicService = new ArrayList<InformationDTO>();
    }

    public List<InformationDTO> getPropertyService() {
        return propertyService;
    }

    public void setPropertyService(List<InformationDTO> propertyService) {
        this.propertyService = propertyService;
    }

    public List<InformationDTO> getPublicService() {
        return publicService;
    }

    public void setPublicService(List<InformationDTO> publicService) {this.publicService = publicService;}
}

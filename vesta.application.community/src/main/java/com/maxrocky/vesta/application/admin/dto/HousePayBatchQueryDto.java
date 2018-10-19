package com.maxrocky.vesta.application.admin.dto;

import com.maxrocky.vesta.domain.model.CommunityHouseAppointEntity;

/**
 * Created by Tom on 2016/4/13 23:16.
 * Describe:
 */
public class HousePayBatchQueryDto {

    private String projectIdDto;
    private String batchNoDto;
    private Integer statusDto;
    private String payStaTimeDto;
    private String payEndTimeDto;

    public String getProjectIdDto() {
        return projectIdDto;
    }

    public void setProjectIdDto(String projectIdDto) {
        this.projectIdDto = projectIdDto;
    }

    public String getBatchNoDto() {
        return batchNoDto;
    }

    public void setBatchNoDto(String batchNoDto) {
        this.batchNoDto = batchNoDto;
    }

    public Integer getStatusDto() {
        return statusDto;
    }

    public void setStatusDto(Integer statusDto) {
        this.statusDto = statusDto;
    }

    public String getPayStaTimeDto() {
        return payStaTimeDto;
    }

    public void setPayStaTimeDto(String payStaTimeDto) {
        this.payStaTimeDto = payStaTimeDto;
    }

    public String getPayEndTimeDto() {
        return payEndTimeDto;
    }

    public void setPayEndTimeDto(String payEndTimeDto) {
        this.payEndTimeDto = payEndTimeDto;
    }

    public CommunityHouseAppointEntity toCommunityHouseAppointEntity(){
        CommunityHouseAppointEntity communityHouseAppointEntity = new CommunityHouseAppointEntity();
        communityHouseAppointEntity.setCommunityId(this.projectIdDto);
        communityHouseAppointEntity.setDeliveryBatch(this.batchNoDto);
        communityHouseAppointEntity.setStatus(this.statusDto);
        return communityHouseAppointEntity;
    }

}

package com.maxrocky.vesta.application.DTO.admin;

import org.apache.commons.collections.map.HashedMap;

import java.util.Date;
import java.util.Map;

/**
 * U+会员卡DTO
 * Created by WeiYangDong on 2017/11/1.
 */
public class UplusMembershipCardDTO {

    public static final Map<String,String> PROJECT_NUM_MAP = new HashedMap();
//    广渠金茂府（01）
//    亚奥金茂悦（02）
//    望京金茂府（03）
//    亦庄金茂悦X88地块（05）
//    亦庄金茂悦X85地块（06）
//    亦庄金茂逸墅（07）
//    北京金茂府（08）
//    亦庄金茂府（09）
//    丰台金茂广场（10）
    static{
        PROJECT_NUM_MAP.put("BJ-GQJMF","01");
        PROJECT_NUM_MAP.put("BJ-YAJMY","02");
        PROJECT_NUM_MAP.put("BJ-WJJMF","03");
        PROJECT_NUM_MAP.put("BJ-YZJMY","05");
        PROJECT_NUM_MAP.put("BJ-YZJMY85","06");
        PROJECT_NUM_MAP.put("BJ-JMYJY","07");
        PROJECT_NUM_MAP.put("BJ-BJJMF","08");
        PROJECT_NUM_MAP.put("BJ-YZJMF","09");
        PROJECT_NUM_MAP.put("BJ-FTJMGC","10");
    }

    private Integer id;//主键ID
    private String cardNum;//会员卡编码
    private String userId;//用户ID
    private String memberId;//业主ID

    private Integer cardState;//卡状态(0,已刪除;1,已启用)
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}

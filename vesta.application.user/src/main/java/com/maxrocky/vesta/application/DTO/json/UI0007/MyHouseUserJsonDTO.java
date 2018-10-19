package com.maxrocky.vesta.application.DTO.json.UI0007;

import com.maxrocky.vesta.application.DTO.admin.HouseUserAdminDTO;
import com.maxrocky.vesta.domain.model.UserInfoEntity;

/**
 * Created by Tom on 2016/1/20 19:13.
 * Describe:UI0007返回实体类
 */
public class MyHouseUserJsonDTO {

    private String id;//授权Id
    private String name;//姓名
    private String logo;//头像
    private String userType;//用户类型
    private String address;//地址

    public MyHouseUserJsonDTO(){
        id = "";
        name = "";
        logo = "";
        userType = "";
        address = "";
    }

    public MyHouseUserJsonDTO(HouseUserAdminDTO houseUserAdminDTO,UserInfoEntity userInfoEntity){
        id = houseUserAdminDTO.getId();
        name = userInfoEntity.getRealName();
        logo = userInfoEntity.getLogo();
        userType = houseUserAdminDTO.getUserType();
        address = houseUserAdminDTO.getAddress();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

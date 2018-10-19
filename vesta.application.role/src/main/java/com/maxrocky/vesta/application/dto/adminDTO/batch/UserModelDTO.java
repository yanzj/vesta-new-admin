package com.maxrocky.vesta.application.dto.adminDTO.batch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2018/1/11.
 */
public class UserModelDTO {
    private List<UserProjectRoleAccreditDTO> userProRole;

    public UserModelDTO() {
        this.userProRole = new ArrayList<>();
    }

    public List<UserProjectRoleAccreditDTO> getUserProRole() {
        return userProRole;
    }

    public void setUserProRole(List<UserProjectRoleAccreditDTO> userProRole) {
        this.userProRole = userProRole;
    }
}

package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.UserAnthorityDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;

/**
 * Created by zhanghj on 2016/1/25.
 */
public interface UserAnthorityService {

    /**
     * 通过员工id获取角色信息
     * @param staffId
     * @return
     */
    public UserAnthorityDTO getUserAnthority(String staffId);

    /**
     * 更新员工角色关系
     * @param userAnthorityDTO
     * @return
     */
    public boolean updateUserAnthority(UserAnthorityDTO userAnthorityDTO,UserPropertyStaffEntity userPropertystaffEntity);

    /**
     * 删除员工角色关系
     * @param staffId
     * @return
     */
    public boolean deleteUserAnthority(String staffId);

}

package com.maxrocky.vesta.domain.repository;




import com.maxrocky.vesta.domain.model.AppRolesetMapEntity;
import com.maxrocky.vesta.domain.model.RoleRolesetmapEntity;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/18.
 */
public interface RoleRolesetmapRepository {

    /**
     * 查询角色的所有权限
     * @param setId
     * @return
     */
        public List<RoleRolesetmapEntity> listRolesetMapBySetId(String setId);

    /**
     * 删除权限关系
     * @param roleRolesetmapEntity
     * @return
     */
    public  boolean deleteRoleRolesetMap(RoleRolesetmapEntity roleRolesetmapEntity);


    /**
     * 根据权限角色关系Id获得关系
     * @param rolesetId
     * @return
     */
    public RoleRolesetmapEntity getRoleSetmapById(String rolesetId);

    /**
     * 保存角色权限关系
     * @param roleRolesetmapEntity
     * @return
     */
    public boolean saveRolesetMap(RoleRolesetmapEntity roleRolesetmapEntity);

    /**
     * 判断角色和关系是否存在
     * @param setId
     * @param roleId
     * @return
     */
    public RoleRolesetmapEntity getRolesetMap(String setId,String roleId);

    /**
     * 保存质检APP角色权限关系
     * */
    public void saveAppRolestmap(AppRolesetMapEntity appRolesetMapEntity);
    /**
     * 清除APP角色权限关联关系
     * */
    public void delAppRolesetmap(String roleSetId);
}

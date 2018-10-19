package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.CommunityCenterRespository;
import com.maxrocky.vesta.domain.repository.CommunityHouseBatchRespository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 10:10
 * Describe:
 */
@Service
public class CommunityHouseBatchServiceImpl implements CommunityHouseBatchService {
    static {
        // 对日期进行处理
        DateConverter dc = new DateConverter();
        dc.setPattern("yyyy-MM-dd HH:mm:ss");
        ConvertUtils.register(dc, Date.class);
    }

    @Autowired
    CommunityHouseBatchRespository communityHouseBatchRespository;


    /**
     * 增加预约单信息
     *
     * @param userId          用户id,房屋所属人
     * @param houseInfoEntity 房屋信息id
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveAppointOrder(String userId, HouseInfoEntity houseInfoEntity) throws Exception {

        CommunityHouseAppointEntity communityHouseAppointEntity = new CommunityHouseAppointEntity();        //保存预约单
        //设置id
        communityHouseAppointEntity.setId(IdGen.uuid());
        //设置启用状态
        communityHouseAppointEntity.setStatus(1);
        //设置房产用户id
        communityHouseAppointEntity.setUserId(userId);
        //设置房产信息
        communityHouseAppointEntity.setHouseInfoId(houseInfoEntity.getId());
        //设置交付状态为未交付
        communityHouseAppointEntity.setPayStatus(0);
        //设置预约状态为未预约
        communityHouseAppointEntity.setAppointStatus(0);
        //设置项目id
        List<HouseProjectEntity> houseProjectEntitys = (List<HouseProjectEntity>) this.communityHouseBatchRespository.find("from HouseProjectEntity c where c.name = ?", new String[]{houseInfoEntity.getProjectName()});
        if (houseProjectEntitys.size()>0) {
            communityHouseAppointEntity.setCommunityId(houseProjectEntitys.get(0).getId());
        } else {
            throw new GeneralException("100", "CRM传输项目名称与本地录入项目名称不匹配，IllegalAccessException");
        }
        //设置创建日期
        communityHouseAppointEntity.setCreateDate(new Date());
        //同步修改日期为创建
        communityHouseAppointEntity.setOperatorDate(new Date());

        boolean flag = this.communityHouseBatchRespository.saveAppointOrder(communityHouseAppointEntity);

        return flag;
    }
    /**
     * 修改预约单信息
     *
     * @param userId          用户id,房屋所属人
     * @param houseInfoEntity 房屋信息id
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateAppointOrder(String userId, HouseInfoEntity houseInfoEntity) throws Exception {
        List<CommunityHouseAppointEntity> communityHouseAppointEntitys = (List<CommunityHouseAppointEntity>) this.communityHouseBatchRespository.find("from CommunityHouseAppointEntity c where c.houseInfoId = ? and c.userId = ?", new String[]{houseInfoEntity.getId(), userId});
        if(communityHouseAppointEntitys == null){
            return false;
        }
        CommunityHouseAppointEntity communityHouseAppointEntity = communityHouseAppointEntitys.get(0);
        //设置房产用户id
        communityHouseAppointEntity.setUserId(userId);
        //设置房产信息
        communityHouseAppointEntity.setHouseInfoId(houseInfoEntity.getId());
        //设置修改日期
        communityHouseAppointEntity.setOperatorDate(new Date());
        //更新修改人为CRM
        communityHouseAppointEntity.setOperator("CRM");

        boolean flag = this.communityHouseBatchRespository.saveAppointOrder(communityHouseAppointEntity);

        return flag;
    }


    /**
     * CRM信息删除后，预约单自动删除
     * @param houseInfoId
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult deleteAppointOrder(String houseInfoId) throws Exception {
        if (StringUtil.isEmpty(houseInfoId)) {
            CommunityHouseAppointEntity communityHouseAppointEntity = this.communityHouseBatchRespository.get(CommunityHouseAppointEntity.class, houseInfoId);
            communityHouseAppointEntity.setStatus(0);
            this.communityHouseBatchRespository.save(communityHouseAppointEntity);
        }
        return null;
    }

    /**
     * 获取交付批次信息
     *
     * @param id
     * @return
     */
    @Override
    public CommunityAppointManageDto getBatchManage(String id) throws InvocationTargetException, IllegalAccessException {

        CommunityAppointManageDto communityAppointManageDto = new CommunityAppointManageDto();
        CommunityBatchManageEntity communityBatchManageEntity = this.communityHouseBatchRespository.get(CommunityBatchManageEntity.class, id);
        BeanUtils.copyProperties(communityAppointManageDto, communityBatchManageEntity);
        //设置集中交付时间
        communityAppointManageDto.setPayStaDate(communityBatchManageEntity.getPayStaTime().toString().substring(0, 10));
        communityAppointManageDto.setPayEndDate(communityBatchManageEntity.getPayEndTime().toString().substring(0, 10));
        //根据项目id设置项目名称
        communityAppointManageDto.setCommunityName(this.communityHouseBatchRespository.get(CommunityOverviewEntity.class, communityBatchManageEntity.getCommunityId()).getName());
        communityAppointManageDto.setStatus(communityBatchManageEntity.getBatchStatus().toString());
        //设置批次
        communityAppointManageDto.setDeliveryBatch(communityBatchManageEntity.getDeliveryBatch());
        //设置描述
        communityAppointManageDto.setDescription(communityBatchManageEntity.getDescription());
        //设置最大人数访问
        communityAppointManageDto.setMaxUser(communityBatchManageEntity.getMaxUser());
        return communityAppointManageDto;
    }


    /**
     * 交付批次分页查询，带条件查询
     *
     * @param communityAppointManageDto
     * @param webPage
     * @return
     */
    @Override
    public List<CommunityAppointManageDto> queryAllDeliveryBatch(CommunityAppointManageDto communityAppointManageDto, WebPage webPage) throws Exception {

        List<CommunityAppointManageDto> communityAppointManageDtoList = new ArrayList<CommunityAppointManageDto>();

        //查询条件
        CommunityBatchManageEntity communityBatchManageEntity = new CommunityBatchManageEntity();

        // 初始化查询条件数据
        BeanUtils.copyProperties(communityBatchManageEntity, communityAppointManageDto);

        //设置交付状态,99为未选择状态
        if (!"99".equals(communityAppointManageDto.getStatus()) && !StringUtil.isEmpty(communityAppointManageDto.getStatus())) {
            communityBatchManageEntity.setBatchStatus(Integer.parseInt(communityAppointManageDto.getStatus()));
        }

        //根据项目名称查询项目id，设置项目id
        List<CommunityOverviewEntity> list = (List<CommunityOverviewEntity>) this.communityHouseBatchRespository.find("from CommunityOverviewEntity c where c.status = 1 and c.name = ? ", new String[]{communityAppointManageDto.getCommunityName()});
        if (list.size() > 0) {
            CommunityOverviewEntity communityOverviewEntity = list.get(0);
            communityBatchManageEntity.setCommunityId(communityOverviewEntity.getId());
        }
        //设置交付详情
        communityBatchManageEntity.setDeliveryBatch(communityAppointManageDto.getDeliveryBatch());
        //设置开始结束日期
        if (!StringUtil.isEmpty(communityAppointManageDto.getPayStaDate())) {
            communityBatchManageEntity.setPayStaTime(DateUtils.parse(communityAppointManageDto.getPayStaDate(), "yyyy-MM-dd"));
        }
        if (!StringUtil.isEmpty(communityAppointManageDto.getPayEndDate())) {
            communityBatchManageEntity.setPayEndTime(DateUtils.parse(communityAppointManageDto.getPayEndDate(), "yyyy-MM-dd"));
        }

        // 转换参数为 CommunityHouseAppointEntity进行带条件分页查询
        List<CommunityBatchManageEntity> communityBatchManageEntityList =
                communityHouseBatchRespository.queryAllDeliveryBatch(communityBatchManageEntity, webPage);
        //LIST封装为DTO进行返回
        for (CommunityBatchManageEntity batchManage : communityBatchManageEntityList) {
            CommunityAppointManageDto temp = new CommunityAppointManageDto();//批次管理DTO
            BeanUtils.copyProperties(temp, batchManage);
            //拼接集中交付时间
            temp.setPayStaDate(batchManage.getPayStaTime().toString().substring(0, 10));
            temp.setPayEndDate(batchManage.getPayEndTime().toString().substring(0, 10));
            //根据项目id设置项目名称
            temp.setCommunityName(this.communityHouseBatchRespository.get(CommunityOverviewEntity.class, batchManage.getCommunityId()).getName());
            temp.setStatus(batchManage.getBatchStatus().toString());
            //添加
            communityAppointManageDtoList.add(temp);
        }

        //WANGYIFAN测试数据

       //this.saveAppointOrder("fengfan", this.communityHouseBatchRespository.get(HouseInfoEntity.class, "1b98080db8854aa6bc44fdd64d03da07"));

        return communityAppointManageDtoList;
    }


    /**
     * 添加批次管理信息
     *
     * @param communityAppointManageDto
     * @param userPropertyStaffEntity
     * @return
     */
    @Override
    public boolean saveBatchManage(CommunityAppointManageDto communityAppointManageDto, UserPropertyStaffEntity userPropertyStaffEntity) throws Exception {
        //保存实体类
        CommunityBatchManageEntity communityBatchManageEntity = new CommunityBatchManageEntity();
        boolean flag = false;

        //开启状态
        communityBatchManageEntity.setStatus(1);
        //设置描述
        communityBatchManageEntity.setDescription(communityAppointManageDto.getDescription());
        //批次名称
        communityBatchManageEntity.setDeliveryBatch(communityAppointManageDto.getDeliveryBatch());
        //集中交付开始时间
        communityBatchManageEntity.setPayStaTime(DateUtils.parse(communityAppointManageDto.getPayStaDate(), "yyyy-MM-dd"));
        //集中交付结束时间
        communityBatchManageEntity.setPayEndTime(DateUtils.parse(communityAppointManageDto.getPayEndDate(), "yyyy-MM-dd"));
        //上线接待人次
        communityBatchManageEntity.setMaxUser(communityAppointManageDto.getMaxUser());
        //设置初始批次完成状态
        communityBatchManageEntity.setBatchStatus(0);

        //设置操作人
        if (userPropertyStaffEntity != null) {
            //设置修改时间
            communityBatchManageEntity.setOperatorDate(new Date());
            communityBatchManageEntity.setOperator(userPropertyStaffEntity.getStaffId());
        }

        if (communityAppointManageDto != null) {

            //项目id
            List<CommunityOverviewEntity> list = (List<CommunityOverviewEntity>) this.communityHouseBatchRespository.find("from CommunityOverviewEntity c where c.status = 1 and c.name = ? ", new String[]{communityAppointManageDto.getCommunityName()});
            if (list.size() > 0) {
                communityBatchManageEntity.setCommunityId(list.get(0).getId());
            } else {
                throw new GeneralException("100", "添加时项目id为空，IllegalAccessException");
            }

            //如果id为空，则设置id
            if (StringUtil.isEmpty(communityAppointManageDto.getId())) {
                communityBatchManageEntity.setId(IdGen.uuid());
                //设置创建日期
                communityBatchManageEntity.setCreateDate(new Date());
                //设置创建人
                communityBatchManageEntity.setCreatePerson(userPropertyStaffEntity.getUserName());
                //设置所有未交付房屋状态批次
                this.updateBatchManageIdAndName(communityBatchManageEntity.getDeliveryBatch(), communityBatchManageEntity.getId(), communityBatchManageEntity.getCommunityId());

            }

            //将数据保存，或者更新到数据库
            flag = this.communityHouseBatchRespository.saveBatchManage(communityBatchManageEntity);
        }

        return flag;
    }

    @Override
    public boolean updateBatchManage(CommunityAppointManageDto communityAppointManageDto, UserPropertyStaffEntity userPropertyStaffEntity) throws Exception {
        //获取实体类
        CommunityBatchManageEntity communityBatchManageEntity = this.communityHouseBatchRespository.get(CommunityBatchManageEntity.class, communityAppointManageDto.getId().substring(1));

        boolean flag = false;

        //设置描述
        communityBatchManageEntity.setDescription(communityAppointManageDto.getDescription());
        //批次名称
        communityBatchManageEntity.setDeliveryBatch(communityAppointManageDto.getDeliveryBatch());
        //集中交付开始时间
        communityBatchManageEntity.setPayStaTime(DateUtils.parse(communityAppointManageDto.getPayStaDate(), "yyyy-MM-dd"));
        //集中交付结束时间
        communityBatchManageEntity.setPayEndTime(DateUtils.parse(communityAppointManageDto.getPayEndDate(), "yyyy-MM-dd"));
        //上线接待人次
        communityBatchManageEntity.setMaxUser(communityAppointManageDto.getMaxUser());

        //设置操作人
        if (userPropertyStaffEntity != null) {
            //设置修改时间
            communityBatchManageEntity.setOperatorDate(new Date());
            communityBatchManageEntity.setOperator(userPropertyStaffEntity.getStaffId());
        }

        if (communityAppointManageDto != null) {
            //项目id
            List<CommunityOverviewEntity> list = (List<CommunityOverviewEntity>) this.communityHouseBatchRespository.find("from CommunityOverviewEntity c where c.status = 1 and c.name = ? ", new String[]{communityAppointManageDto.getCommunityName()});
            if (list.size() > 0) {
                communityBatchManageEntity.setCommunityId(list.get(0).getId());
            } else {
                throw new GeneralException("100", "添加时项目id为空，IllegalAccessException");
            }

            //将数据保存，或者更新到数据库
            flag = this.communityHouseBatchRespository.updateBatchManage(communityBatchManageEntity);
        }

        return flag;
    }

    @Override
    public boolean deleteBatchManage(CommunityAppointManageDto communityHouseAppointEntity, UserPropertyStaffEntity userPropertyStaffEntity) throws Exception {
        //获取实体类
        CommunityBatchManageEntity communityBatchManageEntity = this.communityHouseBatchRespository.get(CommunityBatchManageEntity.class, communityHouseAppointEntity.getId());
        communityBatchManageEntity.setStatus(0);
        //设置操作人
        if (userPropertyStaffEntity != null) {
            //设置修改时间
            communityBatchManageEntity.setOperatorDate(new Date());
            communityBatchManageEntity.setOperator(userPropertyStaffEntity.getStaffId());
        }

        this.communityHouseBatchRespository.updateBatchManage(communityBatchManageEntity);
        return true;
    }

    /**
     * 批量设置批次管理id
     *
     * @param batchManageName
     * @param batchManageId
     * @param communityId
     * @return
     */
    @Override
    public boolean updateBatchManageIdAndName(String batchManageName, String batchManageId, String communityId) {
        this.communityHouseBatchRespository.updateBatchManageIdAndName(batchManageName, batchManageId, communityId);
        return true;
    }




    //-----------------------------------通用接口

    /**
     * 查询所有项目名称
     *
     * @return
     */
    @Override
    public Map<String, String> quaryAllCommunity() throws Exception {
        List<String> name = new ArrayList<String>();
        List<CommunityOverviewEntity> communityOverviewEntitys = this.communityHouseBatchRespository.find(CommunityOverviewEntity.class);
        for (CommunityOverviewEntity communityOverviewEntity : communityOverviewEntitys) {
            name.add(communityOverviewEntity.getName());
        }
        HashSet<String> s = new HashSet<String>(name);
        Map m = new HashMap();
        int i = 0;
        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            m.put(((Integer) i).toString(), it.next());
            i++;
        }
        return m;
    }

    /**
     * 查询所有支付批次
     *
     * @return
     */
    @Override
    public Map<String, String> quaryAllPayBatch() throws Exception {
        List<String> name = new ArrayList<String>();
        List<CommunityBatchManageEntity> communityBatchManageEntitys = this.communityHouseBatchRespository.find("from CommunityBatchManageEntity");
        for (CommunityBatchManageEntity communityBatchManageEntity : communityBatchManageEntitys) {
            name.add(communityBatchManageEntity.getDeliveryBatch());
        }
        HashSet<String> s = new HashSet<String>(name);
        Map m = new HashMap();
        int i = 0;
        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            m.put(((Integer) i).toString(), it.next());
            i++;
        }
        return m;
    }

    /**
     * 查询所有支付状态
     *
     * @return
     */
    @Override
    public Map<String, String> quaryAllPayStatus() throws Exception {
        List<String> name = new ArrayList<String>();
        List<CommunityBatchManageEntity> communityBatchManageEntitys = this.communityHouseBatchRespository.find("from CommunityBatchManageEntity ");
        for (CommunityBatchManageEntity communityBatchManageEntity : communityBatchManageEntitys) {
            name.add(communityBatchManageEntity.getBatchStatus().toString());
        }
        HashSet<String> s = new HashSet<String>(name);
        Map m = new HashMap();
        int i = 0;
        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            m.put(((Integer) i).toString(), it.next());
            i++;
        }
        return m;
    }

    /**
     * 取得所有map
     *
     * @return
     */
    @Override
    public CommunityAppointPageMapDto getMap() throws Exception {
        CommunityAppointPageMapDto communityAppointPageMapDto = new CommunityAppointPageMapDto();
        communityAppointPageMapDto.setPayBatchMap(this.quaryAllPayBatch());
        communityAppointPageMapDto.setPayStatusMap(this.quaryAllPayStatus());
        communityAppointPageMapDto.setProjectNameMap(this.quaryAllCommunity());
        return communityAppointPageMapDto;
    }

}

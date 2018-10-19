package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.BasicSupplierService;
import com.maxrocky.vesta.application.inf.IBasicService;
import com.maxrocky.vesta.application.model.AssociateSupplier;
import com.maxrocky.vesta.application.model.Supplier;
import com.maxrocky.vesta.application.ws.SupplierRequest;
import com.maxrocky.vesta.application.ws.SupplierResponse;
import com.maxrocky.vesta.application.ws.SupplierResponseBody;
import com.maxrocky.vesta.application.ws.SupplierType;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liudongxin on 2016/6/7.
 */
@Service
public class BasicSupplierServiceImpl implements BasicSupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierRelationshipRepository supplierRelationshipRepository;
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;
    @Autowired
    private SupplierRelationshipSnapRepository supplierRelationshipSnapRepository;
    @Autowired
    private SupplierSnapRepository supplierSnapRepository;

    /**
     * 供应商查询
     * param:项目编码
     * return
     */
    @Override
    public String queryBasicInfo(String[] projectCodeList) {
        String result="";
        try{
            BasicServiceLocator basicService = new BasicServiceLocator();
            IBasicService iBasicService = basicService.getBasicHttpBinding_IBasicService();
            SupplierRequest request=new SupplierRequest();
            //request.setProjectCodeList(projectCodeList);
            request.setProjectCodeList(null);
            SupplierResponse response=iBasicService.supplierQuery(request);
            SupplierResponseBody responseBody=response.getBody();
            String projectNum=projectCodeList[0];
            if(responseBody!=null){
                //供应商关系
                AssociateSupplier[] associateList=responseBody.getAssociateSupplierList();
                if(associateList.length>0){
                    supplierRelationshipRepository.delete(projectNum);
                    for(AssociateSupplier assciate:associateList){
                        SupplierRelationshipEntity relationship=supplierRelationshipRepository.getById(assciate.getId());
                        if(relationship!=null){
                            relationship.setSupplierId(assciate.getSupplierId());
                            relationship.setProjectId(assciate.getProjectId());
                            relationship.setProjectNum(assciate.getProjectCode());
                            relationship.setThirdId(assciate.getClassificationThree());
                            relationship.setModifyDate(new Date());
                            supplierRelationshipRepository.update(relationship);
                            //调用日志
                            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                            interfaceLogEntity.setId(IdGen.uuid());
                            interfaceLogEntity.setInterfaceName("查询供应商接口:更新数据！");
                            interfaceLogEntity.setCode("200");
                            interfaceLogEntity.setEntityName("supplier_relationship");
                            interfaceLogEntity.setErrorDate(new Date());
                            interfaceLogRepository.create(interfaceLogEntity);
                        }else{
                            SupplierRelationshipEntity relationshipEntity=new SupplierRelationshipEntity();
                            relationshipEntity.setId(assciate.getId());
                            relationshipEntity.setProjectId(assciate.getProjectId());
                            relationshipEntity.setProjectNum(assciate.getProjectCode());
                            relationshipEntity.setThirdId(assciate.getClassificationThree());
                            relationshipEntity.setSupplierId(assciate.getSupplierId());
                            if(assciate.getModifiedOn().getTime()!=null) {
                                relationshipEntity.setCreateDate(assciate.getModifiedOn().getTime());
                            }
                            relationshipEntity.setModifyDate(new Date());
                            supplierRelationshipRepository.create(relationshipEntity);
                            //调用日志
                            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                            interfaceLogEntity.setId(IdGen.uuid());
                            interfaceLogEntity.setInterfaceName("查询供应商接口:创建数据！");
                            interfaceLogEntity.setCode("200");
                            interfaceLogEntity.setEntityName("supplier_relationship");
                            interfaceLogEntity.setErrorDate(new Date());
                            interfaceLogRepository.create(interfaceLogEntity);
                        }

                        //更新供应商关系信息临时表
                        SupplierRelationshipSnapEntity supplierEntity=supplierRelationshipSnapRepository.get(assciate.getId());
                        if(supplierEntity!=null){
                            if(!StringUtil.isEmpty(assciate.getSupplierId())) {
                                supplierEntity.setSupplierId(assciate.getSupplierId());
                            }
                            if(!StringUtil.isEmpty(assciate.getProjectId())) {
                                supplierEntity.setProjectId(assciate.getProjectId());
                            }
                            if(!StringUtil.isEmpty(assciate.getProjectCode())) {
                                supplierEntity.setProjectNum(assciate.getProjectCode());
                            }
                            if(!StringUtil.isEmpty(assciate.getClassificationThree())) {
                                supplierEntity.setThirdId(assciate.getClassificationThree());
                            }
                            supplierEntity.setModifyDate(new Date());
                            supplierEntity.setState("1");
                            supplierRelationshipSnapRepository.update(supplierEntity);
                            //调用日志
                            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                            interfaceLogEntity.setId(IdGen.uuid());
                            interfaceLogEntity.setInterfaceName("查询供应商接口:更新数据！");
                            interfaceLogEntity.setCode("200");
                            interfaceLogEntity.setEntityName("supplier_relationship_snap");
                            interfaceLogEntity.setErrorDate(new Date());
                            interfaceLogRepository.create(interfaceLogEntity);
                        }else{
                            SupplierRelationshipSnapEntity supplier=new SupplierRelationshipSnapEntity();
                            supplier.setBusinessId(assciate.getId());
                            supplier.setSupplierId(assciate.getSupplierId());
                            supplier.setProjectId(assciate.getProjectId());
                            supplier.setProjectNum(assciate.getProjectCode());
                            supplier.setThirdId(assciate.getClassificationThree());
                            supplier.setModifyDate(new Date());
                            supplier.setCreateDate(new Date());
                            supplier.setState("1");
                            supplierRelationshipSnapRepository.create(supplier);
                            //调用日志
                            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                            interfaceLogEntity.setId(IdGen.uuid());
                            interfaceLogEntity.setInterfaceName("查询供应商接口:创建数据！");
                            interfaceLogEntity.setCode("200");
                            interfaceLogEntity.setEntityName("supplier_relationship_snap");
                            interfaceLogEntity.setErrorDate(new Date());
                            interfaceLogRepository.create(interfaceLogEntity);
                        }
                    }
                }
                //供应商
                Supplier[] supplierList=responseBody.getSupplierList();
                if(supplierList.length>0){
                    for(Supplier suppliers:supplierList){
                        SupplierEntity supplierEntity=supplierRepository.getById(suppliers.getSupplierId());
                        if(supplierEntity!=null){
                            supplierEntity.setName(suppliers.getName());
                            if(suppliers.getType()!=null) {
                                if ("civilMainContractor".equals(suppliers.getType().toString())) {
                                    supplierEntity.setType("土建总包");
                                } else if ("installMainContractor".equals(suppliers.getType().toString())) {
                                    supplierEntity.setType("安装总包");
                                } else if (SupplierType._decorationEngineering.equals(suppliers.getType().toString())) {
                                    supplierEntity.setType("装饰工程");
                                } else if ("weakElectricityEngineering".equals(suppliers.getType().toString())) {
                                    supplierEntity.setType("弱电工程");
                                } else if ("coatingEngineering".equals(suppliers.getType().toString())) {
                                    supplierEntity.setType("涂装工程");
                                } else if ("steelStructureEngineering".equals(suppliers.getType().toString())) {
                                    supplierEntity.setType("钢结构工程");
                                }
                            }
                            supplierEntity.setModifyDate(new Date());
                            supplierRepository.update(supplierEntity);
                            //调用日志
                            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                            interfaceLogEntity.setId(IdGen.uuid());
                            interfaceLogEntity.setInterfaceName("查询供应商接口:更新数据！");
                            interfaceLogEntity.setCode("200");
                            interfaceLogEntity.setEntityName("supplier");
                            interfaceLogEntity.setErrorDate(new Date());
                            interfaceLogRepository.create(interfaceLogEntity);
                        }else{
                            SupplierEntity supplier=new SupplierEntity();
                            supplier.setId(suppliers.getSupplierId());
                            supplier.setName(suppliers.getName());
                            if(suppliers.getType()!=null) {
                                if ("civilMainContractor".equals(suppliers.getType().toString())) {
                                    supplier.setType("土建总包");
                                } else if ("installMainContractor".equals(suppliers.getType().toString())) {
                                    supplier.setType("安装总包");
                                } else if ("decorationEngineering".equals(suppliers.getType().toString())) {
                                    supplier.setType("装饰工程");
                                } else if ("weakElectricityEngineering".equals(suppliers.getType().toString())) {
                                    supplier.setType("弱电工程");
                                } else if ("coatingEngineering".equals(suppliers.getType().toString())) {
                                    supplier.setType("涂装工程");
                                } else if ("steelStructureEngineering".equals(suppliers.getType().toString())) {
                                    supplier.setType("钢结构工程");
                                }
                            }
                            if(suppliers.getModifiedOn().getTime()!=null) {
                                supplier.setCreateDate(suppliers.getModifiedOn().getTime());
                            }
                            supplier.setModifyDate(new Date());
                            supplierRepository.create(supplier);
                            //调用日志
                            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                            interfaceLogEntity.setId(IdGen.uuid());
                            interfaceLogEntity.setInterfaceName("查询供应商接口:创建数据！");
                            interfaceLogEntity.setCode("200");
                            interfaceLogEntity.setEntityName("supplier");
                            interfaceLogEntity.setErrorDate(new Date());
                            interfaceLogRepository.create(interfaceLogEntity);
                        }

                        //更新供应商信息临时表
                        SupplierSnapEntity supplierSnapEntity=supplierSnapRepository.get(suppliers.getSupplierId());
                        if(supplierSnapEntity!=null){
                            if(!StringUtil.isEmpty(suppliers.getName())) {
                                supplierSnapEntity.setName(suppliers.getName());
                            }
                            if(suppliers.getType()!=null) {
                                if ("civilMainContractor".equals(suppliers.getType().toString())) {
                                    supplierSnapEntity.setType("土建总包");
                                } else if ("installMainContractor".equals(suppliers.getType().toString())) {
                                    supplierSnapEntity.setType("安装总包");
                                } else if ("decorationEngineering".equals(suppliers.getType().toString())) {
                                    supplierSnapEntity.setType("装饰工程");
                                } else if ("weakElectricityEngineering".equals(suppliers.getType().toString())) {
                                    supplierSnapEntity.setType("弱电工程");
                                } else if ("coatingEngineering".equals(suppliers.getType().toString())) {
                                    supplierSnapEntity.setType("涂装工程");
                                } else if ("steelStructureEngineering".equals(suppliers.getType().toString())) {
                                    supplierSnapEntity.setType("钢结构工程");
                                }
                            }
                            supplierSnapEntity.setModifyDate(new Date());
                            supplierSnapEntity.setState("1");
                            supplierSnapEntity.setGraded("1");
                            supplierSnapRepository.update(supplierSnapEntity);
                            //调用日志
                            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                            interfaceLogEntity.setId(IdGen.uuid());
                            interfaceLogEntity.setInterfaceName("查询供应商接口:更新数据！");
                            interfaceLogEntity.setCode("200");
                            interfaceLogEntity.setEntityName("supplier_snap");
                            interfaceLogEntity.setErrorDate(new Date());
                            interfaceLogRepository.create(interfaceLogEntity);
                        }else{
                            SupplierSnapEntity supplierSnap=new SupplierSnapEntity();
                            supplierSnap.setBusinessId(suppliers.getSupplierId());
                            supplierSnap.setName(suppliers.getName());
                            supplierSnap.setModifyDate(new Date());
                            if(suppliers.getModifiedOn().getTime()!=null) {
                                supplierSnap.setCreateDate(suppliers.getModifiedOn().getTime());
                            }
                            if(suppliers.getType()!=null) {
                                if ("civilMainContractor".equals(suppliers.getType().toString())) {
                                    supplierSnap.setType("土建总包");
                                } else if ("installMainContractor".equals(suppliers.getType().toString())) {
                                    supplierSnap.setType("安装总包");
                                } else if ("decorationEngineering".equals(suppliers.getType().toString())) {
                                    supplierSnap.setType("装饰工程");
                                } else if ("weakElectricityEngineering".equals(suppliers.getType().toString())) {
                                    supplierSnap.setType("弱电工程");
                                } else if ("coatingEngineering".equals(suppliers.getType().toString())) {
                                    supplierSnap.setType("涂装工程");
                                } else if ("steelStructureEngineering".equals(suppliers.getType().toString())) {
                                    supplierSnap.setType("钢结构工程");
                                }
                            }
                            supplierSnap.setState("1");
                            supplierSnap.setGraded("1");
                            supplierSnapRepository.create(supplierSnap);
                            //调用日志
                            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                            interfaceLogEntity.setId(IdGen.uuid());
                            interfaceLogEntity.setInterfaceName("查询供应商接口:创建数据！");
                            interfaceLogEntity.setCode("200");
                            interfaceLogEntity.setEntityName("supplier_snap");
                            interfaceLogEntity.setErrorDate(new Date());
                            interfaceLogRepository.create(interfaceLogEntity);
                        }
                    }
                }
                //获取供应商所有数据进行时间对比，如果不匹配，则改为删除状态
                List<SupplierSnapEntity> supplierSnapList=supplierSnapRepository.getSupplierSnapList();
                String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
                if(supplierSnapList.size()>0){
                    for(SupplierSnapEntity supplier:supplierSnapList){
                        String modifyDate = DateUtils.format(supplier.getModifyDate(), DateUtils.FORMAT_SHORT);
                        if(!dateNow.equals(modifyDate)) {
                            //更新供应商信息临时表
                            SupplierSnapEntity supplierSnapEntity=supplierSnapRepository.get(supplier.getBusinessId());
                            supplierSnapEntity.setState("0");
                            supplierSnapRepository.update(supplierSnapEntity);
                        }
                    }
                }
                //获取供应商所有数据进行时间对比，如果不匹配，则改为删除状态
                List<SupplierRelationshipSnapEntity> supplierShipList=supplierRelationshipSnapRepository.getSupplierList();
                if(supplierShipList.size()>0){
                    for (SupplierRelationshipSnapEntity supplierShip:supplierShipList){
                        String modifyDate = DateUtils.format(supplierShip.getModifyDate(), DateUtils.FORMAT_SHORT);
                        if(!dateNow.equals(modifyDate)){
                            SupplierRelationshipSnapEntity supplier=supplierRelationshipSnapRepository.get(supplierShip.getBusinessId());
                            supplier.setState("0");
                            supplierRelationshipSnapRepository.update(supplier);
                        }
                    }
                }
            }
            //查询成功或失败
            if("1".equals(response.getHeader().getStatus())){
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("查询供应商接口！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("supplier_relationship/supplier");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            }else{
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("查询供应商接口！");
                interfaceLogEntity.setCode("500");
                interfaceLogEntity.setEntityName("supplier_relationship/supplier");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            result="200";
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result="500";
            //调用日志
            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
            interfaceLogEntity.setId(IdGen.uuid());
            interfaceLogEntity.setInterfaceName("查询供应商接口！");
            interfaceLogEntity.setCode("500");
            interfaceLogEntity.setEntityName("supplier_relationship/supplier");
            interfaceLogEntity.setErrorDate(new Date());
            interfaceLogRepository.create(interfaceLogEntity);
            return result;
        }
    }
}

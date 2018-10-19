package com.maxrocky.vesta.application.impl;
import com.maxrocky.vesta.application.inf.ClassificationService;
import com.maxrocky.vesta.application.inf.IBasicService;
import com.maxrocky.vesta.application.model.*;
import com.maxrocky.vesta.application.ws.*;
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
 * Created by dl on 2016/5/6.
 * 三级分类查询接口
 */
@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private FirstClassificationCRMRepository firstClassificationCRMRepository;
    @Autowired
    private SecondClassificationCRMRepository secondClassificationCRMRepository;
    @Autowired
    private ThirdClassificationCRMRepository thirdClassificationCRMRepository;
    @Autowired
    private RepairModeCRMRepository repairModeCRMRepository;
    @Autowired
    private WorkTimeCRMRepository workTimeCRMRepository;
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;
    @Autowired
    private OrganizeRepository organizeRepository;
    @Autowired
    private DescribeRepository describeRepository;
    @Autowired
    private TemporaryTableUpdateRepository temporaryTableUpdateRepository;

    @Override
    public String Classification() {
        try {
            BasicServiceLocator basicService = new BasicServiceLocator();
            IBasicService iBasicService = basicService.getBasicHttpBinding_IBasicService();
            ClassificationRequest classRequest=new ClassificationRequest();
            ClassificationRequestHeader questHeader=new ClassificationRequestHeader();
            classRequest.setHeader(questHeader);
            ClassificationResponse response = iBasicService.classificationQuery(classRequest);
            if(response.getBody()!=null) {
                if (response.getBody().getClassificationBody() != null) {
                    //一级分类
                    if (response.getBody().getClassificationBody().getFirstClassificationList() != null) {
                        //先删除，后添加数据
                        firstClassificationCRMRepository.delete();
                        Classification[] firstClassification = response.getBody().getClassificationBody().getFirstClassificationList();
                        if (firstClassification.length > 0) {
                            for (Classification first : firstClassification) {
                                FirstClassificationEntity firstClass = firstClassificationCRMRepository.get(first.getValue());
                                if (firstClass != null) {
                                    firstClass.setLabel(first.getLabel());
                                    //firstClass.setValue(first.getValue());
                                    firstClass.setModifyDate(new Date());
                                    firstClassificationCRMRepository.update(firstClass);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:更新数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("first_classification");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                } else {
                                    FirstClassificationEntity firstClassEntity = new FirstClassificationEntity();
                                    firstClassEntity.setLabel(first.getLabel());
                                    firstClassEntity.setValue(first.getValue());
                                    firstClassEntity.setModifyDate(new Date());
                                    firstClassificationCRMRepository.create(firstClassEntity);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:创建数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("first_classification");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                }

                                //更新三级分类临时表
                                ClassificationTemporaryTimeEntity classEntity = temporaryTableUpdateRepository.queryClassforgradle(first.getValue(), "1");
                                if (classEntity != null) {
                                    if (!StringUtil.isEmpty(first.getLabel())) {
                                        classEntity.setName(first.getLabel());
                                    }
                                    classEntity.setStart("1");
                                    classEntity.setGraded("1");
                                    classEntity.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.updateClass(classEntity);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:更新数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("classification_temporary_time");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                } else {
                                    ClassificationTemporaryTimeEntity classInfo = new ClassificationTemporaryTimeEntity();
                                    classInfo.setCurrentId(first.getValue());
                                    classInfo.setName(first.getLabel());
                                    classInfo.setStart("1");
                                    classInfo.setGraded("1");
                                    classInfo.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.createClass(classInfo);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:创建数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("classification_temporary_time");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                }
                            }
                        }
                    }
                    //二级分类
                    if (response.getBody().getClassificationBody().getSecondClassificationList() != null) {
                        secondClassificationCRMRepository.delete();
                        Classification[] secondClassification = response.getBody().getClassificationBody().getSecondClassificationList();
                        if (secondClassification.length > 0) {
                            for (Classification second : secondClassification) {
                                SecondClassificationEntity secondClass = secondClassificationCRMRepository.get(second.getValue());
                                if (secondClass != null) {
                                    secondClass.setLabel(second.getLabel());
                                    //secondClass.setValue(second.getValue());
                                    secondClass.setFirstId(second.getParentValue());
                                    secondClass.setModifyDate(new Date());
                                    secondClassificationCRMRepository.update(secondClass);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:更新数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("second_classification");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                } else {
                                    SecondClassificationEntity secondClassEntity = new SecondClassificationEntity();
                                    secondClassEntity.setLabel(second.getLabel());
                                    secondClassEntity.setValue(second.getValue());
                                    secondClassEntity.setFirstId(second.getParentValue());
                                    secondClassEntity.setModifyDate(new Date());
                                    secondClassificationCRMRepository.create(secondClassEntity);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:创建数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("second_classification");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                }

                                //更新三级分类临时表
                                ClassificationTemporaryTimeEntity classEntity = temporaryTableUpdateRepository.queryClassforgradle(second.getValue(), "2");
                                if (classEntity != null && second.getLabel().equals(classEntity.getName())) {
                                    if (!StringUtil.isEmpty(second.getLabel())) {
                                        classEntity.setName(second.getLabel());
                                    }
                                    if (!StringUtil.isEmpty(second.getParentValue())) {
                                        classEntity.setParentId(second.getParentValue());
                                    }
                                    classEntity.setStart("1");
                                    classEntity.setGraded("2");
                                    classEntity.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.updateClass(classEntity);
                                } else {
                                    ClassificationTemporaryTimeEntity classInfo = new ClassificationTemporaryTimeEntity();
                                    classInfo.setCurrentId(second.getValue());
                                    classInfo.setName(second.getLabel());
                                    classInfo.setParentId(second.getParentValue());
                                    classInfo.setStart("1");
                                    classInfo.setGraded("2");
                                    classInfo.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.createClass(classInfo);
                                }
                            }
                        }
                    }
                    //三级分类
                    if (response.getBody().getClassificationBody().getThirdClassificationList() != null) {
//                        thirdClassificationCRMRepository.delete();
                        Classification[] thirdClassification = response.getBody().getClassificationBody().getThirdClassificationList();
                        if (thirdClassification.length > 0) {
                            for (Classification third : thirdClassification) {
                                ThirdClassificationEntity thirdClass = thirdClassificationCRMRepository.get(third.getValue());
                                if (thirdClass != null) {
                                    thirdClass.setLabel(third.getLabel());
                                    //thirdClass.setValue(third.getValue());
                                    thirdClass.setSecondId(third.getParentValue());
                                    thirdClass.setModifyDate(new Date());
                                    thirdClassificationCRMRepository.update(thirdClass);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:更新数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("third_classification");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                } else {
                                    ThirdClassificationEntity thirdClassEntity = new ThirdClassificationEntity();
                                    thirdClassEntity.setLabel(third.getLabel());
                                    thirdClassEntity.setValue(third.getValue());
                                    thirdClassEntity.setSecondId(third.getParentValue());
                                    thirdClassEntity.setModifyDate(new Date());
                                    thirdClassificationCRMRepository.create(thirdClassEntity);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:创建数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("third_classification");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                }

                                //更新三级分类临时表
                                ClassificationTemporaryTimeEntity classEntity = temporaryTableUpdateRepository.queryClassforgradle(third.getValue(), "3");
                                if (classEntity != null && third.getLabel().equals(classEntity.getName())) {
                                    if (!StringUtil.isEmpty(third.getLabel())) {
                                        classEntity.setName(third.getLabel());
                                    }
                                    if (!StringUtil.isEmpty(third.getParentValue())) {
                                        classEntity.setParentId(third.getParentValue());
                                    }
                                    classEntity.setStart("1");
                                    classEntity.setGraded("3");
                                    classEntity.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.updateClass(classEntity);
                                } else {
                                    ClassificationTemporaryTimeEntity classInfo = new ClassificationTemporaryTimeEntity();
                                    classInfo.setCurrentId(third.getValue());
                                    classInfo.setName(third.getLabel());
                                    classInfo.setParentId(third.getParentValue());
                                    classInfo.setStart("1");
                                    classInfo.setGraded("3");
                                    classInfo.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.createClass(classInfo);
                                }
                            }
                        }

                    }
                    //维修方式
                    if (response.getBody().getClassificationBody().getRepairModeList() != null) {
                        repairModeCRMRepository.delete();
                        Classification[] repairModeList = response.getBody().getClassificationBody().getRepairModeList();
                        if (repairModeList.length > 0) {
                            for (Classification repairMode : repairModeList) {
                                RepairModeEntity repair = repairModeCRMRepository.get(repairMode.getValue());
                                if (repair != null) {
                                    repair.setLabel(repairMode.getLabel());
                                    repair.setThirdId(repairMode.getParentValue());
                                    repair.setModifyDate(new Date());
                                    repairModeCRMRepository.update(repair);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:更新数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("repair_mode");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                } else {
                                    RepairModeEntity repairModeEntity = new RepairModeEntity();
                                    repairModeEntity.setLabel(repairMode.getLabel());
                                    repairModeEntity.setValue(repairMode.getValue());
                                    repairModeEntity.setThirdId(repairMode.getParentValue());
                                    repairModeEntity.setModifyDate(new Date());
                                    repairModeCRMRepository.create(repairModeEntity);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:创建数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("repair_mode");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                }

                                //更新三级分类临时表
                                ClassificationTemporaryTimeEntity classEntity = temporaryTableUpdateRepository.queryClassfour(repairMode.getValue(), "0");
                                if (classEntity != null && repairMode.getLabel().equals(classEntity.getName())) {
                                    if (!StringUtil.isEmpty(repairMode.getLabel())) {
                                        classEntity.setName(repairMode.getLabel());
                                    }
                                    if (!StringUtil.isEmpty(repairMode.getParentValue())) {
                                        classEntity.setParentId(repairMode.getParentValue());
                                    }
                                    classEntity.setStart("1");
                                    classEntity.setType("0");
                                    classEntity.setGraded("4");
                                    classEntity.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.updateClass(classEntity);
                                } else {
                                    ClassificationTemporaryTimeEntity classInfo = new ClassificationTemporaryTimeEntity();
                                    classInfo.setCurrentId(repairMode.getValue());
                                    classInfo.setName(repairMode.getLabel());
                                    classInfo.setParentId(repairMode.getParentValue());
                                    classInfo.setStart("1");
                                    classInfo.setType("0");
                                    classInfo.setGraded("4");
                                    classInfo.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.createClass(classInfo);
                                }
                            }
                        }
                    }
                    //维修工时
                    if (response.getBody().getClassificationBody().getWorkTimeList() != null) {
                        workTimeCRMRepository.delete();
                        Classification[] workTimeList = response.getBody().getClassificationBody().getWorkTimeList();
                        if (workTimeList.length > 0) {
                            for (Classification workTimes : workTimeList) {
                                WorkTimeEntity workTime = workTimeCRMRepository.get(workTimes.getValue());
                                if (workTime != null) {
                                    workTime.setLabel(workTimes.getLabel());
                                    workTime.setValue(workTimes.getValue());
                                    workTime.setRepairId(workTimes.getParentValue());
                                    workTime.setModifyDate(new Date());
                                    workTimeCRMRepository.update(workTime);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:更新数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("work_time");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                } else {
                                    WorkTimeEntity workTimeEntity = new WorkTimeEntity();
                                    workTimeEntity.setLabel(workTimes.getLabel());
                                    workTimeEntity.setValue(workTimes.getValue());
                                    workTimeEntity.setRepairId(workTimes.getParentValue());
                                    workTimeEntity.setModifyDate(new Date());
                                    workTimeCRMRepository.create(workTimeEntity);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:创建数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("work_time");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                }

                                //更新三级分类临时表
                                ClassificationTemporaryTimeEntity classEntity = temporaryTableUpdateRepository.queryClassforgradle(workTimes.getValue(),"5");
                                if (classEntity != null && workTimes.getLabel().equals(classEntity.getName())) {
                                    if (!StringUtil.isEmpty(workTimes.getLabel())) {
                                        classEntity.setName(workTimes.getLabel());
                                    }
                                    if (!StringUtil.isEmpty(workTimes.getParentValue())) {
                                        classEntity.setParentId(workTimes.getParentValue());
                                    }
                                    classEntity.setStart("1");
                                    classEntity.setGraded("5");
                                    classEntity.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.updateClass(classEntity);
                                } else {
                                    ClassificationTemporaryTimeEntity classInfo = new ClassificationTemporaryTimeEntity();
                                    classInfo.setCurrentId(workTimes.getValue());
                                    classInfo.setName(workTimes.getLabel());
                                    classInfo.setParentId(workTimes.getParentValue());
                                    classInfo.setStart("1");
                                    classInfo.setGraded("5");
                                    classInfo.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.createClass(classInfo);
                                }
                            }
                        }
                    }
                    //简要描述
                    if (response.getBody().getClassificationBody().getDescribeList() != null) {
                        describeRepository.delete();
                        Classification[] describeList = response.getBody().getClassificationBody().getDescribeList();
                        if (describeList.length > 0) {
                            for (Classification describes : describeList) {
                                DescribeEntity describe = describeRepository.get(describes.getValue());
                                if (describe != null) {
                                    describe.setLabel(describes.getLabel());
                                    describe.setValue(describes.getValue());
                                    describe.setThirdId(describes.getParentValue());
                                    describe.setModifyDate(new Date());
                                    describeRepository.update(describe);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:更新数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("describe");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                } else {
                                    DescribeEntity describeEntity = new DescribeEntity();
                                    describeEntity.setLabel(describes.getLabel());
                                    describeEntity.setValue(describes.getValue());
                                    describeEntity.setThirdId(describes.getParentValue());
                                    describeRepository.create(describeEntity);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:创建数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("describe");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                }

                                //更新三级分类临时表
                                ClassificationTemporaryTimeEntity classEntity = temporaryTableUpdateRepository.queryClassfour(describes.getValue(),"1");
                                if (classEntity != null && describes.getLabel().equals(classEntity.getName())) {
                                    if (!StringUtil.isEmpty(describes.getLabel())) {
                                        classEntity.setName(describes.getLabel());
                                    }
                                    if (!StringUtil.isEmpty(describes.getParentValue())) {
                                        classEntity.setParentId(describes.getParentValue());
                                    }
                                    classEntity.setStart("1");
                                    classEntity.setType("1");
                                    classEntity.setGraded("4");
                                    classEntity.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.updateClass(classEntity);
                                } else {
                                    ClassificationTemporaryTimeEntity classInfo = new ClassificationTemporaryTimeEntity();
                                    classInfo.setCurrentId(describes.getValue());
                                    classInfo.setName(describes.getLabel());
                                    classInfo.setParentId(describes.getParentValue());
                                    classInfo.setStart("1");
                                    classInfo.setType("1");
                                    classInfo.setGraded("4");
                                    classInfo.setTimeStamp(new Date());
                                    temporaryTableUpdateRepository.createClass(classInfo);
                                }
                            }
                        }
                    }
                }
                //部门
                if (response.getBody().getRepairBody() != null) {
                    if (response.getBody().getRepairBody().getSeiviceGroupList() != null) {
                        Classification[] serviceGroupList = response.getBody().getRepairBody().getSeiviceGroupList();
                        if (serviceGroupList.length > 0) {
                            for (Classification department : serviceGroupList) {
                                OrganizeEntity organizeEntity = organizeRepository.getOrganizeInfo(department.getValue());
                                if (organizeEntity != null) {
                                    organizeEntity.setOrganizeName(department.getLabel());
                                    organizeEntity.setCrmName(department.getLabel());
                                    organizeRepository.updateOrganize(organizeEntity);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:更新部门数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("role_organize");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                } else {
                                    OrganizeEntity organize = new OrganizeEntity();
                                    organize.setOrganizeId(IdGen.uuid());
                                    organize.setCrmId(department.getValue());
                                    organize.setOrganizeName(department.getLabel());
                                    organize.setCrmName(department.getLabel());
                                    organize.setOrganizeStatus("1");
                                    organizeRepository.addOrganize(organize);
                                    //调用日志
                                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                    interfaceLogEntity.setId(IdGen.uuid());
                                    interfaceLogEntity.setInterfaceName("查询三级分类接口:创建部门数据！");
                                    interfaceLogEntity.setCode("200");
                                    interfaceLogEntity.setEntityName("role_organize");
                                    interfaceLogEntity.setErrorDate(new Date());
                                    interfaceLogRepository.create(interfaceLogEntity);
                                }
                            }
                        }
                    }
                }
                //获取所有数据进行时间对比，如果不匹配，则改为删除状态
                List<ClassificationTemporaryTimeEntity> ClassificationList = temporaryTableUpdateRepository.getClassList();
                if (ClassificationList.size() > 0) {
                    String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
                    for (ClassificationTemporaryTimeEntity classes : ClassificationList) {
                        String modifyDate = DateUtils.format(classes.getTimeStamp(), DateUtils.FORMAT_SHORT);
                        if (!dateNow.equals(modifyDate)) {
                            //更新三级分类临时表
                            ClassificationTemporaryTimeEntity classEntity = temporaryTableUpdateRepository.queryClass(classes.getCurrentId());
                            classEntity.setStart("0");
                            temporaryTableUpdateRepository.updateClass(classEntity);
                        }
                    }
                }
                ResponseHeader header = response.getHeader();
                //查询成功或失败
                if ("1".equals(header.getStatus())) {//成功
                    //调用日志
                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                    interfaceLogEntity.setId(IdGen.uuid());
                    interfaceLogEntity.setInterfaceName("查询三级分类接口！");
                    interfaceLogEntity.setCode("200");
                    interfaceLogEntity.setEntityName("first_classification/second_classification/third_classification");
                    interfaceLogEntity.setErrorDate(new Date());
                    interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
                    interfaceLogRepository.create(interfaceLogEntity);
                } else if ("0".equals(header.getStatus())) {//失败
                    //调用日志
                    InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                    interfaceLogEntity.setId(IdGen.uuid());
                    interfaceLogEntity.setInterfaceName("查询三级分类接口！");
                    interfaceLogEntity.setCode("500");
                    interfaceLogEntity.setEntityName("first_classification/second_classification/third_classification");
                    interfaceLogEntity.setErrorDate(new Date());
                    interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
                    interfaceLogRepository.create(interfaceLogEntity);
                }
            }
            return "200";
        }catch(Exception e){
            e.printStackTrace();
            //调用日志
            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
            interfaceLogEntity.setId(IdGen.uuid());
            interfaceLogEntity.setInterfaceName("查询三级分类接口！");
            interfaceLogEntity.setCode("500");
            interfaceLogEntity.setEntityName("first_classification/second_classification/third_classification");
            interfaceLogEntity.setErrorDate(new Date());
            //interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
            interfaceLogRepository.create(interfaceLogEntity);
            return "500";
        }
    }
}

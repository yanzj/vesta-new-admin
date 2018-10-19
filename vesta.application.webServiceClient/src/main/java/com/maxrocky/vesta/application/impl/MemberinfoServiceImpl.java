package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.IMembershipService;
import com.maxrocky.vesta.application.inf.MemberinfoService;
import com.maxrocky.vesta.application.model.ReqHeader;
import com.maxrocky.vesta.application.ms.*;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by dinglei on 2016/4/26.
 * 会员更新
 */
@Service
public class MemberinfoServiceImpl implements MemberinfoService {
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;

    @Override
    public String userInfoUpdate(UserCRMEntity userCRMEntity,
                                 ContactCRMEntity contactCRMEntity,
                                 CardCRMEntity cardCRMEntity,
                                 CarCRMEntity carCRMEntity,
                                 FamilyCRMEntity familyCRMEntity,
                                 AccountCRMEntity accountCRMEntity,
                                 GradeCRMEntity gradeCRMEntity){
        try {
            MemberInfoUpdateReqMessageBody request = new MemberInfoUpdateReqMessageBody();
            Calendar calendar = Calendar. getInstance();
            if(userCRMEntity!=null) {
                MemberInfoUpdateReqMessageBodyBaseInfo baseInfo = new MemberInfoUpdateReqMessageBodyBaseInfo();
                baseInfo.setId(userCRMEntity.getId());
                baseInfo.setMemberId(userCRMEntity.getMemberId());
                baseInfo.setName(userCRMEntity.getUserName());
                if(!StringUtil.isEmpty(userCRMEntity.getSex())) {
                    if (userCRMEntity.getSex().equals("Male")) {
                        baseInfo.setGender(Gender.Male);
                    } else if (userCRMEntity.getSex().equals("Female")) {
                        baseInfo.setGender(Gender.Female);
                    }
                    if (userCRMEntity.getBirthday() != null) {
                        calendar.setTime(userCRMEntity.getBirthday());
                        calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                        calendar.getTime();
                        baseInfo.setBirthday(calendar);
                    }
                }
                baseInfo.setIdcardNo(userCRMEntity.getIdCard());
                baseInfo.setNationality(userCRMEntity.getNationality());
                baseInfo.setNativePlace(userCRMEntity.getNativesPlace());
                if(!StringUtil.isEmpty(userCRMEntity.getState())) {
                    if (userCRMEntity.getState().equals("Associatemember")) {//准会员
                        baseInfo.setStatus(Membershipstate.Associatemember);
                    } else if (userCRMEntity.getState().equals("Frozen")) {//冻结
                        baseInfo.setStatus(Membershipstate.Frozen);
                    } else if (userCRMEntity.getState().equals("Active")) {//激活
                        baseInfo.setStatus(Membershipstate.Active);
                    }
                }
                if(!StringUtil.isEmpty(userCRMEntity.getEducation())) {
                    if (userCRMEntity.getEducation().equals("JuniorCollegeAndBelow")) {//专科及以下（含专科）
                        baseInfo.setEduLevel(Edulevel.JuniorCollegeAndBelow);
                    } else if (userCRMEntity.getEducation().equals("Unergraduate")) {//大学本科
                        baseInfo.setEduLevel(Edulevel.Unergraduate);
                    } else if (userCRMEntity.getEducation().equals("Postgraduate")) {//研究生及以上
                        baseInfo.setEduLevel(Edulevel.Postgraduate);
                    } else if (userCRMEntity.getEducation().equals("Overseas")) {//海外留学
                        baseInfo.setEduLevel(Edulevel.Overseas);
                    } else if (userCRMEntity.getEducation().equals("Others")) {//其他
                        baseInfo.setEduLevel(Edulevel.Others);
                    }
                }
                if(!StringUtil.isEmpty(userCRMEntity.getHobby())) {
                    if (userCRMEntity.getHobby().equals("Music")) {
                        baseInfo.setHobby(Hobby.Music);//音乐
                    } else if (userCRMEntity.getHobby().equals("Dancing")) {
                        baseInfo.setHobby(Hobby.Dancing);//跳舞
                    } else if (userCRMEntity.getHobby().equals("Drawing")) {
                        baseInfo.setHobby(Hobby.Drawing);//画画
                    } else if (userCRMEntity.getHobby().equals("Handwriting")) {
                        baseInfo.setHobby(Hobby.Handwriting);//书法
                    } else if (userCRMEntity.getHobby().equals("Movies")) {
                        baseInfo.setHobby(Hobby.Movies);//电影
                    } else if (userCRMEntity.getHobby().equals("Photography")) {
                        baseInfo.setHobby(Hobby.Photography);//摄影
                    } else if (userCRMEntity.getHobby().equals("literature")) {
                        baseInfo.setHobby(Hobby.literature);//文学
                    } else if (userCRMEntity.getHobby().equals("Bodybuilding")) {
                        baseInfo.setHobby(Hobby.Bodybuilding);//健身
                    } else if (userCRMEntity.getHobby().equals("Running")) {
                        baseInfo.setHobby(Hobby.Running);//跑步
                    } else if (userCRMEntity.getHobby().equals("climbing")) {
                        baseInfo.setHobby(Hobby.climbing);//爬山
                    } else if (userCRMEntity.getHobby().equals("RockClimbing")) {
                        baseInfo.setHobby(Hobby.RockClimbing);//攀登
                    } else if (userCRMEntity.getHobby().equals("Yoga")) {
                        baseInfo.setHobby(Hobby.Yoga);//瑜伽
                    } else if (userCRMEntity.getHobby().equals("Fighting")) {
                        baseInfo.setHobby(Hobby.Fighting);//格斗
                    } else if (userCRMEntity.getHobby().equals("Riding")) {
                        baseInfo.setHobby(Hobby.Riding);//马术
                    } else if (userCRMEntity.getHobby().equals("Pingpong")) {
                        baseInfo.setHobby(Hobby.Pingpong);//乒乓球
                    } else if (userCRMEntity.getHobby().equals("Badminton")) {
                        baseInfo.setHobby(Hobby.Badminton);//羽毛球
                    } else if (userCRMEntity.getHobby().equals("Tennis")) {
                        baseInfo.setHobby(Hobby.Tennis);//网球
                    } else if (userCRMEntity.getHobby().equals("Basketball")) {
                        baseInfo.setHobby(Hobby.Basketball);//篮球
                    } else if (userCRMEntity.getHobby().equals("Football")) {
                        baseInfo.setHobby(Hobby.Football);//足球
                    } else if (userCRMEntity.getHobby().equals("Swimming")) {
                        baseInfo.setHobby(Hobby.Swimming);//游泳
                    } else if (userCRMEntity.getHobby().equals("Golf")) {
                        baseInfo.setHobby(Hobby.Golf);//高尔夫
                    } else if (userCRMEntity.getHobby().equals("Bowling")) {
                        baseInfo.setHobby(Hobby.Bowling);//保龄球
                    } else if (userCRMEntity.getHobby().equals("PC")) {
                        baseInfo.setHobby(Hobby.PC);//电脑
                    } else if (userCRMEntity.getHobby().equals("HandiCraftArt")) {
                        baseInfo.setHobby(Hobby.HandiCraftArt);//手工艺
                    } else if (userCRMEntity.getHobby().equals("antique")) {
                        baseInfo.setHobby(Hobby.antique);//古玩
                    } else if (userCRMEntity.getHobby().equals("AlcoholandTobacco")) {
                        baseInfo.setHobby(Hobby.AlcoholandTobacco);//烟酒
                    } else if (userCRMEntity.getHobby().equals("Tea")) {
                        baseInfo.setHobby(Hobby.Tea);//品茶
                    } else if (userCRMEntity.getHobby().equals("Cooking")) {
                        baseInfo.setHobby(Hobby.Cooking);//烹饪
                    } else if (userCRMEntity.getHobby().equals("Travel")) {
                        baseInfo.setHobby(Hobby.Travel);//旅游
                    } else if (userCRMEntity.getHobby().equals("Finance")) {
                        baseInfo.setHobby(Hobby.Finance);//金融
                    } else if (userCRMEntity.getHobby().equals("Digital")) {
                        baseInfo.setHobby(Hobby.Digital);//数码
                    } else if (userCRMEntity.getHobby().equals("Car")) {
                        baseInfo.setHobby(Hobby.Car);//汽车
                    } else if (userCRMEntity.getHobby().equals("Cosmetology")) {
                        baseInfo.setHobby(Hobby.Cosmetology);//美容
                    } else if (userCRMEntity.getHobby().equals("Chess")) {
                        baseInfo.setHobby(Hobby.Chess);//棋类
                    } else if (userCRMEntity.getHobby().equals("Shopping")) {
                        baseInfo.setHobby(Hobby.Shopping);//购物
                    } else if (userCRMEntity.getHobby().equals("Luxury")) {
                        baseInfo.setHobby(Hobby.Luxury);//奢侈品
                    } else if (userCRMEntity.getHobby().equals("Others")) {
                        baseInfo.setHobby(Hobby.Others);//其他
                    }
                }
                baseInfo.setEnglishName(userCRMEntity.getEnglishName());
                if(!StringUtil.isEmpty(userCRMEntity.getOccupation())) {
                    if (userCRMEntity.getOccupation().equals("HLWDZSWWY")) {
                        baseInfo.setOccupation(Occupation.HLWDZSWWY);//互联网/电子商务/网游
                    } else if (userCRMEntity.getOccupation().equals("NYLYMYYY")) {
                        baseInfo.setOccupation(Occupation.NYLYMYYY);//农业/林业/牧业/渔业
                    } else if (userCRMEntity.getOccupation().equals("CKDZ")) {
                        baseInfo.setOccupation(Occupation.CKDZ);//采矿/地质
                    } else if (userCRMEntity.getOccupation().equals("DZGDWDZ")) {
                        baseInfo.setOccupation(Occupation.DZGDWDZ);//电子/光电/微电子
                    } else if (userCRMEntity.getOccupation().equals("SPYLYJ")) {
                        baseInfo.setOccupation(Occupation.SPYLYJ);//食品/饮料/烟酒
                    } else if (userCRMEntity.getOccupation().equals("FSPGFZP")) {
                        baseInfo.setOccupation(Occupation.FSPGFZP);//服饰/皮革/纺织品
                    } else if (userCRMEntity.getOccupation().equals("JJJDJU")) {
                        baseInfo.setOccupation(Occupation.JJJDJU);//家具/家电/家居
                    } else if (userCRMEntity.getOccupation().equals("YCLJJG")) {
                        baseInfo.setOccupation(Occupation.YCLJJG);//原材料及加工
                    } else if (userCRMEntity.getOccupation().equals("YSBZZA")) {
                        baseInfo.setOccupation(Occupation.YSBZZA);//印刷/包装/造纸
                    } else if (userCRMEntity.getOccupation().equals("SYHG")) {
                        baseInfo.setOccupation(Occupation.SYHG);//石油/化工
                    } else if (userCRMEntity.getOccupation().equals("YYYLQXSWGC")) {
                        baseInfo.setOccupation(Occupation.YYYLQXSWGC);//医药/医疗器械/生物工程
                    } else if (userCRMEntity.getOccupation().equals("XJSLFJSZP")) {
                        baseInfo.setOccupation(Occupation.XJSLFJSZP);//橡胶/塑料/非金属制品
                    } else if (userCRMEntity.getOccupation().equals("RHRYBH")) {
                        baseInfo.setOccupation(Occupation.RHRYBH);//日化/日用百货
                    } else if (userCRMEntity.getOccupation().equals("BGSBYPWJ")) {
                        baseInfo.setOccupation(Occupation.BGSBYPWJ);//办公设备/用品/文具
                    } else if (userCRMEntity.getOccupation().equals("JSZP")) {
                        baseInfo.setOccupation(Occupation.JSZP);//金属制品
                    } else if (userCRMEntity.getOccupation().equals("WJZBSPGYP")) {
                        baseInfo.setOccupation(Occupation.WJZBSPGYP);//玩具/珠宝/饰品/工艺品
                    } else if (userCRMEntity.getOccupation().equals("QCHKJLBJ")) {
                        baseInfo.setOccupation(Occupation.QCHKJLBJ);//汽车/航空及零部件
                    } else if (userCRMEntity.getOccupation().equals("DQJXJDZG")) {
                        baseInfo.setOccupation(Occupation.DQJXJDZG);//电气/机械/机电/重工
                    } else if (userCRMEntity.getOccupation().equals("TXDXSBFW")) {
                        baseInfo.setOccupation(Occupation.TXDXSBFW);//通信/电信设备/服务
                    } else if (userCRMEntity.getOccupation().equals("YQYBZDH")) {
                        baseInfo.setOccupation(Occupation.YQYBZDH);//仪器/仪表/自动化
                    } else if (userCRMEntity.getOccupation().equals("DLRQXNY")) {
                        baseInfo.setOccupation(Occupation.DLRQXNY);//电力/燃气/新能源
                    } else if (userCRMEntity.getOccupation().equals("JCZHZM")) {
                        baseInfo.setOccupation(Occupation.JCZHZM);//建材/装潢/照明
                    } else if (userCRMEntity.getOccupation().equals("JTYSWLCC")) {
                        baseInfo.setOccupation(Occupation.JTYSWLCC);//交通/运输/物流/仓储
                    } else if (userCRMEntity.getOccupation().equals("JSJYJ")) {
                        baseInfo.setOccupation(Occupation.JSJYJ);//计算机硬件
                    } else if (userCRMEntity.getOccupation().equals("JSJRJJFW")) {
                        baseInfo.setOccupation(Occupation.JSJRJJFW);//计算机软件及服务
                    } else if (userCRMEntity.getOccupation().equals("PFLS")) {
                        baseInfo.setOccupation(Occupation.PFLS);//批发/零售
                    } else if (userCRMEntity.getOccupation().equals("MYJCK")) {
                        baseInfo.setOccupation(Occupation.MYJCK);//贸易/进出口
                    } else if (userCRMEntity.getOccupation().equals("LYCYJD")) {
                        baseInfo.setOccupation(Occupation.LYCYJD);//旅游/餐饮/酒店
                    } else if (userCRMEntity.getOccupation().equals("JRY")) {
                        baseInfo.setOccupation(Occupation.JRY);//金融业[银行/证券/保险/投资等]
                    } else if (userCRMEntity.getOccupation().equals("FDCKF")) {
                        baseInfo.setOccupation(Occupation.FDCKF);//房地产开发/建筑/工程
                    } else if (userCRMEntity.getOccupation().equals("ZZSYEYGL")) {
                        baseInfo.setOccupation(Occupation.ZZSYEYGL);//住宅/商业物业管理
                    } else if (userCRMEntity.getOccupation().equals("KXYJJSFW")) {
                        baseInfo.setOccupation(Occupation.KXYJJSFW);//科学研究/技术服务
                    } else if (userCRMEntity.getOccupation().equals("JCJYRZ")) {
                        baseInfo.setOccupation(Occupation.JCJYRZ);//检测/检验/认证
                    } else if (userCRMEntity.getOccupation().equals("HBSLGGSS")) {
                        baseInfo.setOccupation(Occupation.HBSLGGSS);//环保/水利/公共设施
                    } else if (userCRMEntity.getOccupation().equals("CSFW")) {
                        baseInfo.setOccupation(Occupation.CSFW);//财税服务[会计/审计/税务等]
                    } else if (userCRMEntity.getOccupation().equals("ZYFW")) {
                        baseInfo.setOccupation(Occupation.ZYFW);//专业服务[法律/中介/招聘等]
                    } else if (userCRMEntity.getOccupation().equals("SHFW")) {
                        baseInfo.setOccupation(Occupation.SHFW);//生活服务[医疗/美容/家政等]
                    } else if (userCRMEntity.getOccupation().equals("JYPXXS")) {
                        baseInfo.setOccupation(Occupation.JYPXXS);//教育/培训/学术
                    } else if (userCRMEntity.getOccupation().equals("WSSHBZSHFL")) {
                        baseInfo.setOccupation(Occupation.WSSHBZSHFL);//卫生/社会保障/社会福利
                    } else if (userCRMEntity.getOccupation().equals("YLKTXX")) {
                        baseInfo.setOccupation(Occupation.YLKTXX);//娱乐/康体/休闲
                    } else if (userCRMEntity.getOccupation().equals("CMWHCB")) {
                        baseInfo.setOccupation(Occupation.CMWHCB);//传媒/文化/出版
                    } else if (userCRMEntity.getOccupation().equals("SHZZZFFYLJG")) {
                        baseInfo.setOccupation(Occupation.SHZZZFFYLJG);//社会组织/政府/非营利机构
                    } else if (userCRMEntity.getOccupation().equals("GGHZZXGG")) {
                        baseInfo.setOccupation(Occupation.GGHZZXGG);//广告/会展/咨询/公关
                    } else if (userCRMEntity.getOccupation().equals("YSSJ")) {
                        baseInfo.setOccupation(Occupation.YSSJ);//艺术设计/工业设计
                    } else if (userCRMEntity.getOccupation().equals("DYHGSQY")) {
                        baseInfo.setOccupation(Occupation.DYHGSQY);//多元化工商企业
                    } else if (userCRMEntity.getOccupation().equals("GJZZ")) {
                        baseInfo.setOccupation(Occupation.GJZZ);//国际组织
                    } else if (userCRMEntity.getOccupation().equals("Others")) {
                        baseInfo.setOccupation(Occupation.Others);//其他
                    }
                }
                baseInfo.setJobUnit(userCRMEntity.getJobUnit());
                if(!StringUtil.isEmpty(userCRMEntity.getIncomeLevel())) {
                    if (userCRMEntity.getIncomeLevel().equals("lt2k")) {
                        baseInfo.setIncomeLevel(Incomelevel.lt2k);//小于RMB2000
                    } else if (userCRMEntity.getIncomeLevel().equals("F2k_5k")) {
                        baseInfo.setIncomeLevel(Incomelevel.F2k_5k);//RMB2000-5000
                    } else if (userCRMEntity.getIncomeLevel().equals("F5k_8k")) {
                        baseInfo.setIncomeLevel(Incomelevel.F5k_8k);//RMB5000-8000
                    } else if (userCRMEntity.getIncomeLevel().equals("F8k_12k")) {
                        baseInfo.setIncomeLevel(Incomelevel.F8k_12k);//RMB8000-12000
                    } else if (userCRMEntity.getIncomeLevel().equals("F12k_20k")) {
                        baseInfo.setIncomeLevel(Incomelevel.F12k_20k);//RMB12000-20000
                    } else if (userCRMEntity.getIncomeLevel().equals("gt20k")) {
                        baseInfo.setIncomeLevel(Incomelevel.gt20k);//大于RMB20000
                    }
                }
                if(!StringUtil.isEmpty(userCRMEntity.getCompanyType())) {
                    if (userCRMEntity.getCompanyType().equals("State_owned")) {
                        baseInfo.setCompanyType(Companystatus.State_owned);
                    } else if (userCRMEntity.getCompanyType().equals("Collective_owned")) {
                        baseInfo.setCompanyType(Companystatus.Collective_owned);
                    } else if (userCRMEntity.getCompanyType().equals("Private")) {
                        baseInfo.setCompanyType(Companystatus.Private);
                    } else if (userCRMEntity.getCompanyType().equals("privately_owned")) {
                        baseInfo.setCompanyType(Companystatus.privately_owned);
                    } else if (userCRMEntity.getCompanyType().equals("foreign_funded")) {
                        baseInfo.setCompanyType(Companystatus.foreign_funded);
                    } else if (userCRMEntity.getCompanyType().equals("JointAdventure")) {
                        baseInfo.setCompanyType(Companystatus.JointAdventure);
                    }
                }
                request.setBaseInfo(baseInfo);
                //调用日志
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员更新接口:更新数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("user_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }

            if(contactCRMEntity!=null) {
                MemberInfoUpdateReqMessageBodyContactInfo contactInfo = new MemberInfoUpdateReqMessageBodyContactInfo();
                contactInfo.setId(contactCRMEntity.getId());
                contactInfo.setMemberId(contactCRMEntity.getMemberId());
                contactInfo.setMailBox(contactCRMEntity.getEMail());
                contactInfo.setWeiboId(contactCRMEntity.getWeiBo());
                contactInfo.setQQid(contactCRMEntity.getQq());
                contactInfo.setCommunicationAddress(contactCRMEntity.getAddress());
                contactInfo.setAlternateContactinfor1(contactCRMEntity.getBackUpPhoneOne());
                contactInfo.setAlternateContactinfor2(contactCRMEntity.getBackUpPhoneTwo());
                contactInfo.setAlternateContactinfor3(contactCRMEntity.getBackUpPhoneThree());
                contactInfo.setAlternateContactinfor4(contactCRMEntity.getBackUpPhoneFour());
                contactInfo.setAlternateContactinfor5(contactCRMEntity.getBackUpPhoneFive());
                contactInfo.setPhoneNumber(contactCRMEntity.getPhoneNumber());
                if(contactCRMEntity.getStateCode()!=null) {
                    if (contactCRMEntity.getStateCode().equals("Delete")) {
                        contactInfo.setState_code(State_code.Delete);
                    } else if (contactCRMEntity.getStateCode().equals("Normal")) {
                        contactInfo.setState_code(State_code.Normal);
                    }
                }
                request.setContactInfo(contactInfo);
                //调用日志
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员更新接口:更新数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("contact_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }

            if(carCRMEntity!=null) {
                MemberInfoUpdateReqMessageBodyCarInfo carInfo = new MemberInfoUpdateReqMessageBodyCarInfo();
                carInfo.setId(carCRMEntity.getId());
                carInfo.setMemberId(carCRMEntity.getMemberId());
                carInfo.setLicenseId(carCRMEntity.getLicenseId());
                if(!StringUtil.isEmpty(carCRMEntity.getCarType())) {
                    if (carCRMEntity.getCarType().equals("Saloon")) {//轿车
                        carInfo.setCarType(Cartype.Saloon);
                    } else if (carCRMEntity.getCarType().equals("SUV")) {//suv
                        carInfo.setCarType(Cartype.SUV);
                    } else if (carCRMEntity.getCarType().equals("MPV")) {//mvp
                        carInfo.setCarType(Cartype.MPV);
                    } else if (carCRMEntity.getCarType().equals("Sportscar")) {//跑车
                        carInfo.setCarType(Cartype.Sportscar);
                    } else if (carCRMEntity.getCarType().equals("Motorbike")) {//跑车
                        carInfo.setCarType(Cartype.Motorbike);
                    } else if (carCRMEntity.getCarType().equals("Others")) {//其他
                        carInfo.setCarType(Cartype.Others);
                    }
                }
                if(!StringUtil.isEmpty(carCRMEntity.getCarPower())) {
                    if (carCRMEntity.getCarPower().equals("Gas")) {//燃油
                        carInfo.setCarPower(Carpower.Gas);
                    } else if (carCRMEntity.getCarPower().equals("Electric")) {//电动
                        carInfo.setCarPower(Carpower.Electric);
                    } else if (carCRMEntity.getCarPower().equals("Mixed")) {//混合
                        carInfo.setCarPower(Carpower.Mixed);
                    }
                    if (carCRMEntity.getStateCode().equals("Delete")) {
                        carInfo.setState_code(State_code.Delete);
                    } else if (carCRMEntity.getStateCode().equals("Normal")) {
                        carInfo.setState_code(State_code.Normal);
                    }
                }
                request.setCarInfo(carInfo);
                //调用日志
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员更新接口:更新数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("car_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }

            if(familyCRMEntity!=null) {
                MemberInfoUpdateReqMessageBodyFamilyInfo familyInfo = new MemberInfoUpdateReqMessageBodyFamilyInfo();
                familyInfo.setId(familyCRMEntity.getId());
                familyInfo.setMemberId(familyCRMEntity.getMemberId());
                if(!StringUtil.isEmpty(familyCRMEntity.getMembership())) {
                    if (familyCRMEntity.getMembership().equals("Father")) {
                        familyInfo.setRelationsWithMember(Relationswithmember.Father);
                    } else if (familyCRMEntity.getMembership().equals("Mother")) {
                        familyInfo.setRelationsWithMember(Relationswithmember.Mother);
                    } else if (familyCRMEntity.getMembership().equals("Son")) {
                        familyInfo.setRelationsWithMember(Relationswithmember.Son);
                    } else if (familyCRMEntity.getMembership().equals("Daughter")) {
                        familyInfo.setRelationsWithMember(Relationswithmember.Daughter);
                    } else if (familyCRMEntity.getMembership().equals("Spouse")) {
                        familyInfo.setRelationsWithMember(Relationswithmember.Spouse);
                    } else if (familyCRMEntity.getMembership().equals("Others")) {
                        familyInfo.setRelationsWithMember(Relationswithmember.Others);
                    } else if (familyCRMEntity.getMembership().equals("Renter")) {
                        familyInfo.setRelationsWithMember(Relationswithmember.Renter);
                    } else if (familyCRMEntity.getMembership().equals("LivingWith")) {
                        familyInfo.setRelationsWithMember(Relationswithmember.LivingWith);
                    }
                }
                if(familyCRMEntity.getBirthDate()!=null) {
                    calendar.setTime(familyCRMEntity.getBirthDate());
                    calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar.getTime();
                    familyInfo.setBirthDate(calendar);
                }
                familyInfo.setPhoneNumber(familyCRMEntity.getPhoneNumber());
                familyInfo.setAssociateMemberId(familyCRMEntity.getAssociateMemberId());
                if(!StringUtil.isEmpty(contactCRMEntity.getStateCode())) {
                    if (contactCRMEntity.getStateCode().equals("Delete")) {
                        familyInfo.setState_code(State_code.Delete);
                    } else if (contactCRMEntity.getStateCode().equals("Normal")) {
                        familyInfo.setState_code(State_code.Normal);
                    }
                }
                request.setFamilyInfo(familyInfo);
                //调用日志
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员更新接口:更新数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("family_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }

            if(cardCRMEntity!=null) {
                MemberInfoUpdateReqMessageBodyCardInfo cardInfo = new MemberInfoUpdateReqMessageBodyCardInfo();
                cardInfo.setId(cardCRMEntity.getId());
                cardInfo.setMemberId(cardCRMEntity.getMemberId());
                cardInfo.setCardnumber(cardCRMEntity.getCardNumber());
                cardInfo.setCardType(cardCRMEntity.getCardType());
                cardInfo.setSendCardShop(cardCRMEntity.getSendCardShop());
                if(!StringUtil.isEmpty(cardCRMEntity.getCardStatus())) {
                    if (cardCRMEntity.getCardStatus().equals("Validate")) {
                        cardInfo.setCardStatus(Cardstatus.Validate);
                    } else if (cardCRMEntity.getCardStatus().equals("Invalidate")) {
                        cardInfo.setCardStatus(Cardstatus.Invalidate);
                    } else if (cardCRMEntity.getCardStatus().equals("Frozen")) {
                        cardInfo.setCardStatus(Cardstatus.Frozen);
                    }
                }
                calendar.setTime(cardCRMEntity.getSendCardDate());
                calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                calendar.getTime();
                cardInfo.setSendCardDate(calendar);
                calendar.setTime(cardCRMEntity.getFailDate());
                calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                calendar.getTime();
                cardInfo.setFailDate(calendar);
                request.setCardInfo(cardInfo);
                //调用日志
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员更新接口:更新数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("card_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }

            if(accountCRMEntity!=null) {
                MemberInfoUpdateReqMessageBodyAccountInfo accountInfo = new MemberInfoUpdateReqMessageBodyAccountInfo();
                accountInfo.setMailBox(accountCRMEntity.getMailBox());
                accountInfo.setMemberid(accountCRMEntity.getMemberId());
                accountInfo.setNickName(accountCRMEntity.getNickName());
                accountInfo.setPassword(accountCRMEntity.getPassword());
                request.setAccountInfo(accountInfo);
                //调用日志
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员更新接口:更新数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("user_account_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }

            if(gradeCRMEntity!=null){
                MemberInfoUpdateReqMessageBodyGradeInfo gradeInfo=new MemberInfoUpdateReqMessageBodyGradeInfo();
                //gradeInfo.getBusinesssource(gradeCRMEntity);
                gradeInfo.setMemberid(gradeCRMEntity.getMemberId());
                gradeInfo.setMemberLevel(gradeCRMEntity.getMemberLevel());
                gradeInfo.setBusinesssource(gradeCRMEntity.getFormats());
                request.setGradeInfo(gradeInfo);
                //调用日志
                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员更新接口:更新数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("grade_crm");
                interfaceLogEntity.setErrorDate(new Date());
            }
            MemberInfoUpdateReqBody memberInfoUpdateReqBody=new MemberInfoUpdateReqBody();
            memberInfoUpdateReqBody.setMessagebody(request);
            MemberInfoUpdateRequest memberInfoUpdateRequest=new MemberInfoUpdateRequest();
            memberInfoUpdateRequest.setReqBody(memberInfoUpdateReqBody);
            ReqHeader reqHeader=new ReqHeader();
            reqHeader.setAuthorizationId("admin");
            reqHeader.setAuthorizationPwd("admin_1100903");
            reqHeader.setSysId("WXHY_1100903");
            memberInfoUpdateRequest.setReqHeader(reqHeader);
            MembershipServiceLocator membershipServiceLocator = new MembershipServiceLocator();
            IMembershipService iMembershipService = membershipServiceLocator.getFXEndPoint();
            MemberInfoUpdateResponse response = iMembershipService.memberInfoUpdate(memberInfoUpdateRequest);
            //调用日志
            if(response.getResHeader().getStatus().equals(0)) {//失败
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员更新接口:更新失败！");
                interfaceLogEntity.setCode("500");
                interfaceLogEntity.setEntityName("user_crm");
                interfaceLogEntity.setErrorDate(new Date());
                //interfaceLogEntity.setMemberId(userCRMEntity.getMemberId());
                interfaceLogEntity.setMessage(response.getResHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            }else if(response.getResHeader().getStatus().equals(1)){//成功
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员更新接口:更新成功！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("user_crm");
                interfaceLogEntity.setErrorDate(new Date());
                //interfaceLogEntity.setMemberId(userCRMEntity.getMemberId());
                interfaceLogEntity.setMessage(response.getResHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "500";
        }
    }



    }


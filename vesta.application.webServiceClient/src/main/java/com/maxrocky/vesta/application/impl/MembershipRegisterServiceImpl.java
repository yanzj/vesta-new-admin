package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.IMembershipService;
import com.maxrocky.vesta.application.inf.MembershipRegisterService;
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


/**
 * Created by langmafeng on 2016/5/5 17:47.
 * 会员注册向CRM传数据
 */


@Service
public class MembershipRegisterServiceImpl implements MembershipRegisterService {
    @Autowired
    AccountCRMRepository accountCRMRepository;
    @Autowired
    UserCRMRepository userCRMRepository;
    @Autowired
    ContactCRMRepository contactCRMRepository;
    @Autowired
    CardCRMRepository cardCRMRepository;
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;

    @Override
    public String userInfoRegister(AccountCRMEntity accountCRMEntity, UserCRMEntity userCRMEntity,
                                   ContactCRMEntity contactCRMEntity, CardCRMEntity cardCRMEntity) {
        try {
            MemberRegisterReqMessageBody requestBody = new MemberRegisterReqMessageBody();
            Calendar calendar = Calendar.getInstance();
            if (accountCRMEntity != null) {
                MemberRegisterReqMessageBodyAccountInfo accountInfo = new MemberRegisterReqMessageBodyAccountInfo();
                if (!StringUtil.isEmpty((accountCRMEntity.getMailBox()))) {
                    accountInfo.setMailBox(accountCRMEntity.getMailBox());
                }
                if (!StringUtil.isEmpty(accountCRMEntity.getMailBox())) {
                    accountInfo.setNickName(accountCRMEntity.getMailBox());
                }
                if (!StringUtil.isEmpty(accountCRMEntity.getPassword())) {
                    accountInfo.setPassword(accountCRMEntity.getPassword());
                }
                if (!StringUtil.isEmpty(accountCRMEntity.getMobile())) {
                    accountInfo.setPhoneNumber(accountCRMEntity.getMobile());
                }
                if (!StringUtil.isEmpty(accountCRMEntity.getRegisterId())) {
                    accountInfo.setRegisterId(accountCRMEntity.getRegisterId());
                }
                if(!StringUtil.isEmpty(accountCRMEntity.getNickName())){
                    accountInfo.setNickName(accountCRMEntity.getNickName());
                }
                requestBody.setAccountInfo(accountInfo);
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员注册接口:注册数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("user_account_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);
            }

            //  List<UserCRMEntity> userCRMEntities = userCRMRepository.getBaseInfo();
            if (userCRMEntity != null) {
                MemberRegisterReqMessageBodyBaseInfo baseInfo = new MemberRegisterReqMessageBodyBaseInfo();
                if (!StringUtil.isEmpty(userCRMEntity.getUserName())) {
                    baseInfo.setName(userCRMEntity.getUserName());
                }
                if (userCRMEntity.getSex() != null) {
                    if ((userCRMEntity.getSex().equals("Male"))) {//男
                        baseInfo.setGender(Gender.Male);
                    } else if (userCRMEntity.getSex().equals("Female")) {//女
                        baseInfo.setGender(Gender.Female);
                    }
                }
                if (userCRMEntity.getBirthday() != null) {
                    calendar.setTime(userCRMEntity.getBirthday());
                    calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar.getTime();
                    baseInfo.setBirthday(calendar);
                }
                if (!StringUtil.isEmpty(userCRMEntity.getIdCard())) {
                    baseInfo.setIdcardNo(userCRMEntity.getIdCard());
                }
                if (!StringUtil.isEmpty(userCRMEntity.getNationality())) {
                    baseInfo.setNationality(userCRMEntity.getNationality());
                }
                if (!StringUtil.isEmpty(userCRMEntity.getNativesPlace())) {
                    baseInfo.setNativePlace(userCRMEntity.getNativesPlace());
                }
                if (!StringUtil.isEmpty(userCRMEntity.getEducation())) {
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
                if (!StringUtil.isEmpty(userCRMEntity.getHobby())) {
                    if (userCRMEntity.getHobby().equals("Music")) {//音乐
                        baseInfo.setHobby(Hobby.Music);
                    } else if (userCRMEntity.getHobby().equals("Dancing")) {//舞蹈
                        baseInfo.setHobby(Hobby.Dancing);
                    } else if (userCRMEntity.getHobby().equals("Drawing")) {//绘画
                        baseInfo.setHobby(Hobby.Drawing);
                    } else if (userCRMEntity.getHobby().equals("Handwriting")) {//书法
                        baseInfo.setHobby(Hobby.Handwriting);
                    } else if (userCRMEntity.getHobby().equals("Movies")) {//电影
                        baseInfo.setHobby(Hobby.Movies);
                    } else if (userCRMEntity.getHobby().equals("Photography")) {//摄影
                        baseInfo.setHobby(Hobby.Photography);
                    } else if (userCRMEntity.getHobby().equals("literature")) {//文学
                        baseInfo.setHobby(Hobby.literature);
                    } else if (userCRMEntity.getHobby().equals("Bodybuilding")) {//健身
                        baseInfo.setHobby(Hobby.Bodybuilding);
                    } else if (userCRMEntity.getHobby().equals("Running")) {//跑步
                        baseInfo.setHobby(Hobby.Running);
                    } else if (userCRMEntity.getHobby().equals("climbing")) {//爬山
                        baseInfo.setHobby(Hobby.climbing);
                    } else if (userCRMEntity.getHobby().equals("RockClimbing")) {//攀岩
                        baseInfo.setHobby(Hobby.RockClimbing);
                    } else if (userCRMEntity.getHobby().equals("Yoga")) {//瑜伽
                        baseInfo.setHobby(Hobby.Yoga);
                    } else if (userCRMEntity.getHobby().equals("Fighting")) {//格斗
                        baseInfo.setHobby(Hobby.Fighting);
                    } else if (userCRMEntity.getHobby().equals("Riding")) {//骑马
                        baseInfo.setHobby(Hobby.Riding);
                    } else if (userCRMEntity.getHobby().equals("Pingpong")) {//乒乓球
                        baseInfo.setHobby(Hobby.Pingpong);
                    } else if (userCRMEntity.getHobby().equals("Badminton")) {//羽毛球
                        baseInfo.setHobby(Hobby.Badminton);
                    } else if (userCRMEntity.getHobby().equals("Tennis")) {//网球
                        baseInfo.setHobby(Hobby.Tennis);
                    } else if (userCRMEntity.getHobby().equals("Basketball")) {//篮球
                        baseInfo.setHobby(Hobby.Basketball);
                    } else if (userCRMEntity.getHobby().equals("Football")) {//足球
                        baseInfo.setHobby(Hobby.Football);
                    } else if (userCRMEntity.getHobby().equals("Swimming")) {//游泳
                        baseInfo.setHobby(Hobby.Swimming);
                    } else if (userCRMEntity.getHobby().equals("Golf")) {//高尔夫球
                        baseInfo.setHobby(Hobby.Golf);
                    } else if (userCRMEntity.getHobby().equals("Bowling")) {//保龄球
                        baseInfo.setHobby(Hobby.Bowling);
                    } else if (userCRMEntity.getHobby().equals("PC")) {//电脑
                        baseInfo.setHobby(Hobby.PC);
                    } else if (userCRMEntity.getHobby().equals("HandiCraftArt")) {//手工艺
                        baseInfo.setHobby(Hobby.HandiCraftArt);
                    } else if (userCRMEntity.getHobby().equals("antique")) {//古玩
                        baseInfo.setHobby(Hobby.antique);
                    } else if (userCRMEntity.getHobby().equals("AlcoholandTobacco")) {//烟酒
                        baseInfo.setHobby(Hobby.AlcoholandTobacco);
                    } else if (userCRMEntity.getHobby().equals("Tea")) {//品茶
                        baseInfo.setHobby(Hobby.Tea);
                    } else if (userCRMEntity.getHobby().equals("Cooking")) {//烹饪
                        baseInfo.setHobby(Hobby.Cooking);
                    } else if (userCRMEntity.getHobby().equals("Travel")) {//旅游
                        baseInfo.setHobby(Hobby.Travel);
                    } else if (userCRMEntity.getHobby().equals("Finance")) {//金融
                        baseInfo.setHobby(Hobby.Finance);
                    } else if (userCRMEntity.getHobby().equals("Digital")) {//数码
                        baseInfo.setHobby(Hobby.Digital);
                    } else if (userCRMEntity.getHobby().equals("Car")) {//汽车
                        baseInfo.setHobby(Hobby.Car);
                    } else if (userCRMEntity.getHobby().equals("Cosmetology")) {//美容
                        baseInfo.setHobby(Hobby.Cosmetology);
                    } else if (userCRMEntity.getHobby().equals("Chess")) {//象棋
                        baseInfo.setHobby(Hobby.Chess);
                    } else if (userCRMEntity.getHobby().equals("Shopping")) {//购物
                        baseInfo.setHobby(Hobby.Shopping);
                    } else if (userCRMEntity.getHobby().equals("Luxury")) {//奢侈品
                        baseInfo.setHobby(Hobby.Luxury);
                    } else if (userCRMEntity.getHobby().equals("Others")) {//其他
                        baseInfo.setHobby(Hobby.Others);
                    }
                }
                if (!StringUtil.isEmpty(userCRMEntity.getEnglishName())) {
                    baseInfo.setEnglishName(userCRMEntity.getEnglishName());
                }
                if (!StringUtil.isEmpty(userCRMEntity.getOccupation())) {
                    if (userCRMEntity.getOccupation().equals("HLWDZSWWY")) {//互联网/电子商务/网游
                        baseInfo.setOccupation(Occupation.HLWDZSWWY);
                    } else if (userCRMEntity.getOccupation().equals("NYLYMYYY")) {//农业/林业/牧业/渔业
                        baseInfo.setOccupation(Occupation.NYLYMYYY);
                    } else if (userCRMEntity.getOccupation().equals("CKDZ")) {//采矿/地质
                        baseInfo.setOccupation(Occupation.CKDZ);
                    } else if (userCRMEntity.getOccupation().equals("DZGDWDZ")) {//电子/光电/微电子
                        baseInfo.setOccupation(Occupation.DZGDWDZ);
                    } else if (userCRMEntity.getOccupation().equals("SPYLYJ")) {//食品/饮料/烟酒
                        baseInfo.setOccupation(Occupation.SPYLYJ);
                    } else if (userCRMEntity.getOccupation().equals("FSPGFZP")) {//服饰/皮革/纺织品
                        baseInfo.setOccupation(Occupation.FSPGFZP);
                    } else if (userCRMEntity.getOccupation().equals("JJJDJU")) {//家具/家电/家居
                        baseInfo.setOccupation(Occupation.JJJDJU);
                    } else if (userCRMEntity.getOccupation().equals("YCLJJG")) {//原材料及加工
                        baseInfo.setOccupation(Occupation.YCLJJG);
                    } else if (userCRMEntity.getOccupation().equals("YSBZZA")) {//印刷/包装/造纸
                        baseInfo.setOccupation(Occupation.YSBZZA);
                    } else if (userCRMEntity.getOccupation().equals("SYHG")) {//石油/化工
                        baseInfo.setOccupation(Occupation.SYHG);
                    } else if (userCRMEntity.getOccupation().equals("YYYLQXSWGC")) {//医药/医疗器械/生物工程
                        baseInfo.setOccupation(Occupation.YYYLQXSWGC);
                    } else if (userCRMEntity.getOccupation().equals("XJSLFJSZP")) {//橡胶/塑料/非金属制品
                        baseInfo.setOccupation(Occupation.XJSLFJSZP);
                    } else if (userCRMEntity.getOccupation().equals("RHRYBH")) {//日化/日用百货
                        baseInfo.setOccupation(Occupation.RHRYBH);
                    } else if (userCRMEntity.getOccupation().equals("BGSBYPWJ")) {//办公设备/用品/文具
                        baseInfo.setOccupation(Occupation.BGSBYPWJ);
                    } else if (userCRMEntity.getOccupation().equals("JSZP")) {//金属制品
                        baseInfo.setOccupation(Occupation.JSZP);
                    } else if (userCRMEntity.getOccupation().equals("WJZBSPGYP")) {//玩具/珠宝/饰品/工艺品
                        baseInfo.setOccupation(Occupation.WJZBSPGYP);
                    } else if (userCRMEntity.getOccupation().equals("QCHKJLBJ")) {//汽车/航空及零部件
                        baseInfo.setOccupation(Occupation.QCHKJLBJ);
                    } else if (userCRMEntity.getOccupation().equals("DQJXJDZG")) {//电气/机械/机电/重工
                        baseInfo.setOccupation(Occupation.DQJXJDZG);
                    } else if (userCRMEntity.getOccupation().equals("TXDXSBFW")) {//通信/电信设备/服务
                        baseInfo.setOccupation(Occupation.TXDXSBFW);
                    } else if (userCRMEntity.getOccupation().equals("YQYBZDH")) {//仪器/仪表/自动化
                        baseInfo.setOccupation(Occupation.YQYBZDH);
                    } else if (userCRMEntity.getOccupation().equals("DLRQXNY")) {//电力/燃气/新能源
                        baseInfo.setOccupation(Occupation.DLRQXNY);
                    } else if (userCRMEntity.getOccupation().equals("JCZHZM")) {//建材/装潢/照明
                        baseInfo.setOccupation(Occupation.JCZHZM);
                    } else if (userCRMEntity.getOccupation().equals("JTYSWLCC")) {//交通/运输/物流/仓储
                        baseInfo.setOccupation(Occupation.JTYSWLCC);
                    } else if (userCRMEntity.getOccupation().equals("JSJYJ")) {//计算机硬件
                        baseInfo.setOccupation(Occupation.JSJYJ);
                    } else if (userCRMEntity.getOccupation().equals("JSJRJJFW")) {//计算机软件及服务
                        baseInfo.setOccupation(Occupation.JSJRJJFW);
                    } else if (userCRMEntity.getOccupation().equals("PFLS")) {//批发/零售
                        baseInfo.setOccupation(Occupation.PFLS);
                    } else if (userCRMEntity.getOccupation().equals("MYJCK")) {//贸易/进出口
                        baseInfo.setOccupation(Occupation.MYJCK);
                    } else if (userCRMEntity.getOccupation().equals("LYCYJD")) {//旅游/餐饮/酒店
                        baseInfo.setOccupation(Occupation.LYCYJD);
                    } else if (userCRMEntity.getOccupation().equals("JRY")) {//金融业[银行/证券/保险/投资等]
                        baseInfo.setOccupation(Occupation.JRY);
                    } else if (userCRMEntity.getOccupation().equals("FDCKF")) {//房地产开发/建筑/工程
                        baseInfo.setOccupation(Occupation.FDCKF);
                    } else if (userCRMEntity.getOccupation().equals("ZZSYEYGL")) {//住宅/商业物业管理
                        baseInfo.setOccupation(Occupation.ZZSYEYGL);
                    } else if (userCRMEntity.getOccupation().equals("KXYJJSFW")) {//科学研究/技术服务
                        baseInfo.setOccupation(Occupation.KXYJJSFW);
                    } else if (userCRMEntity.getOccupation().equals("JCJYRZ")) {//检测/检验/认证
                        baseInfo.setOccupation(Occupation.JCJYRZ);
                    } else if (userCRMEntity.getOccupation().equals("HBSLGGSS")) {//环保/水利/公共设施
                        baseInfo.setOccupation(Occupation.HBSLGGSS);
                    } else if (userCRMEntity.getOccupation().equals("CSFW")) {//财税服务[会计/审计/税务等]
                        baseInfo.setOccupation(Occupation.CSFW);
                    } else if (userCRMEntity.getOccupation().equals("ZYFW")) {//专业服务[法律/中介/招聘等]
                        baseInfo.setOccupation(Occupation.ZYFW);
                    } else if (userCRMEntity.getOccupation().equals("SHFW")) {//生活服务[医疗/美容/家政等]
                        baseInfo.setOccupation(Occupation.SHFW);
                    } else if (userCRMEntity.getOccupation().equals("JYPXXS")) {//教育/培训/学术
                        baseInfo.setOccupation(Occupation.JYPXXS);
                    } else if (userCRMEntity.getOccupation().equals("WSSHBZSHFL")) {//卫生/社会保障/社会福利
                        baseInfo.setOccupation(Occupation.WSSHBZSHFL);
                    } else if (userCRMEntity.getOccupation().equals("YLKTXX")) {//娱乐/康体/休闲
                        baseInfo.setOccupation(Occupation.YLKTXX);
                    } else if (userCRMEntity.getOccupation().equals("CMWHCB")) {//传媒/文化/出版
                        baseInfo.setOccupation(Occupation.CMWHCB);
                    } else if (userCRMEntity.getOccupation().equals("SHZZZFFYLJG")) {//社会组织/政府/非营利机构
                        baseInfo.setOccupation(Occupation.SHZZZFFYLJG);
                    } else if (userCRMEntity.getOccupation().equals("GGHZZXGG")) {//广告/会展/咨询/公关
                        baseInfo.setOccupation(Occupation.GGHZZXGG);
                    } else if (userCRMEntity.getOccupation().equals("YSSJ")) {//艺术设计/工业设计
                        baseInfo.setOccupation(Occupation.YSSJ);
                    } else if (userCRMEntity.getOccupation().equals("DYHGSQY")) {//多元化工商企业
                        baseInfo.setOccupation(Occupation.DYHGSQY);
                    } else if (userCRMEntity.getOccupation().equals("GJZZ")) {//国际组织
                        baseInfo.setOccupation(Occupation.GJZZ);
                    } else if (userCRMEntity.getOccupation().equals("Others")) {//其他
                        baseInfo.setOccupation(Occupation.Others);
                    }
                }
                if (!StringUtil.isEmpty(userCRMEntity.getJobUnit())) {
                    baseInfo.setJobUnit(userCRMEntity.getJobUnit());
                }
                if (!StringUtil.isEmpty(userCRMEntity.getIncomeLevel())) {
                    if (userCRMEntity.getIncomeLevel().equals("lt2k")) {//小于RMB2000
                        baseInfo.setIncomeLevel(Incomelevel.lt2k);
                    } else if (userCRMEntity.getIncomeLevel().equals("F2k_5k")) {//RMB2000-5000
                        baseInfo.setIncomeLevel(Incomelevel.F2k_5k);
                    } else if (userCRMEntity.getIncomeLevel().equals("F5k_8k")) {//RMB5000-8000
                        baseInfo.setIncomeLevel(Incomelevel.F5k_8k);
                    } else if (userCRMEntity.getIncomeLevel().equals("F8k_12k")) {//RMB8000-12000
                        baseInfo.setIncomeLevel(Incomelevel.F8k_12k);
                    } else if (userCRMEntity.getIncomeLevel().equals("F12k_20k")) {//RMB12000-20000
                        baseInfo.setIncomeLevel(Incomelevel.F12k_20k);
                    } else if (userCRMEntity.getIncomeLevel().equals("gt20k")) {//大于RMB20000
                        baseInfo.setIncomeLevel(Incomelevel.gt20k);
                    }
                }
                if (!StringUtil.isEmpty(userCRMEntity.getCompanyType())) {
                    if (userCRMEntity.getCompanyType().equals("State_owned")) {//国有
                        baseInfo.setCompanyType(Companystatus.State_owned);
                    } else if (userCRMEntity.getCompanyType().equals("Collective_owned")) {//集体
                        baseInfo.setCompanyType(Companystatus.Collective_owned);
                    } else if (userCRMEntity.getCompanyType().equals("Private")) {//民营
                        baseInfo.setCompanyType(Companystatus.Private);
                    } else if (userCRMEntity.getCompanyType().equals("privately_owned")) {//私营
                        baseInfo.setCompanyType(Companystatus.privately_owned);
                    } else if (userCRMEntity.getCompanyType().equals("foreign_funded")) {//外资
                        baseInfo.setCompanyType(Companystatus.foreign_funded);
                    } else if (userCRMEntity.getCompanyType().equals("JointAdventure")) {//合资
                        baseInfo.setCompanyType(Companystatus.JointAdventure);
                    }
                }
                // baseInfo.setStatus(base.getState());
                requestBody.setBaseInfo(baseInfo);

                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员注册接口:注册数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("user_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);

            }
            //  List<ContactCRMEntity> contactCRMEntities = contactCRMRepository.getContactInfo();
            if (contactCRMEntity != null) {
                MemberRegisterReqMessageBodyContactInfo contactInfo = new MemberRegisterReqMessageBodyContactInfo();
                if (!StringUtil.isEmpty(contactCRMEntity.getEMail())) {
                    contactInfo.setMailBox(contactCRMEntity.getEMail());
                }
                if (!StringUtil.isEmpty(contactCRMEntity.getWeiBo())) {
                    contactInfo.setWeiboId(contactCRMEntity.getWeiBo());

                }
                if (!StringUtil.isEmpty(contactCRMEntity.getWeiXin())) {
                    contactInfo.setWeixinId(contactCRMEntity.getWeiXin());

                }
                if (!StringUtil.isEmpty(contactCRMEntity.getQq())) {
                    contactInfo.setQQid(contactCRMEntity.getQq());

                }
                if (!StringUtil.isEmpty(contactCRMEntity.getAddress())) {
                    contactInfo.setCommunicationAddress(contactCRMEntity.getAddress());

                }
                if (!StringUtil.isEmpty(contactCRMEntity.getBackUpPhoneOne())) {
                    contactInfo.setAlternateContactinfor1(contactCRMEntity.getBackUpPhoneOne());

                }
                if (!StringUtil.isEmpty(contactCRMEntity.getBackUpPhoneTwo())) {
                    contactInfo.setAlternateContactinfor2(contactCRMEntity.getBackUpPhoneTwo());

                }
                if (!StringUtil.isEmpty(contactCRMEntity.getBackUpPhoneThree())) {
                    contactInfo.setAlternateContactinfor3(contactCRMEntity.getBackUpPhoneThree());

                }
                if (!StringUtil.isEmpty(contactCRMEntity.getBackUpPhoneFour())) {
                    contactInfo.setAlternateContactinfor4(contactCRMEntity.getBackUpPhoneFour());

                }
                if (!StringUtil.isEmpty(contactCRMEntity.getBackUpPhoneFive())) {
                    contactInfo.setAlternateContactinfor5(contactCRMEntity.getBackUpPhoneFive());

                }
                if (!StringUtil.isEmpty(contactCRMEntity.getPhoneNumber())) {
                    contactInfo.setPhoneNumber(contactCRMEntity.getPhoneNumber());


                }

                requestBody.setContactInfo(contactInfo);
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员注册接口:注册数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("contact_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);

            }
            //  List<CardCRMEntity> cardCRMEntities = cardCRMRepository.getCardInfo();

            if (cardCRMEntity != null) {
                MemberRegisterReqMessageBodyCardInfo cardInfo = new MemberRegisterReqMessageBodyCardInfo();
                if (!StringUtil.isEmpty(cardCRMEntity.getCardNumber())) {
                    cardInfo.setCardnumber(cardCRMEntity.getCardNumber());
                }
                if (!StringUtil.isEmpty(cardCRMEntity.getCardType())) {
                    cardInfo.setCardType(cardCRMEntity.getCardType());
                }
                if (!StringUtil.isEmpty(cardCRMEntity.getSendCardShop())) {
                    cardInfo.setSendCardShop(cardCRMEntity.getSendCardShop());
                }
                if (!StringUtil.isEmpty(cardCRMEntity.getCardStatus())) {
                    if (cardCRMEntity.getCardStatus().equals("Validate")) {
                        cardInfo.setCardStatus(Cardstatus.Validate);
                    } else if (cardCRMEntity.getCardStatus().equals("Invalidate")) {
                        cardInfo.setCardStatus(Cardstatus.Invalidate);
                    } else if (cardCRMEntity.getCardStatus().equals("Frozen")) {
                        cardInfo.setCardStatus(Cardstatus.Frozen);
                    }
                }
                if (!StringUtil.isEmpty(cardCRMEntity.getFormerCardNumber())) {
                    cardInfo.setFormerCardNumber(cardCRMEntity.getFormerCardNumber());
                }
                if (cardCRMEntity.getSendCardDate() != null) {
                    calendar.setTime(cardCRMEntity.getSendCardDate());
                    calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar.getTime();
                    cardInfo.setSendCardDate(calendar);
                }
                if (cardCRMEntity.getFailDate() != null) {
                    calendar.setTime(cardCRMEntity.getFailDate());
                    calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar.getTime();
                    cardInfo.setFailDate(calendar);
                }
                requestBody.setCardInfo(cardInfo);
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员注册接口:注册数据！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("card_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogRepository.create(interfaceLogEntity);

            }

            MembershipServiceLocator membershipServiceLocator = new MembershipServiceLocator();
            IMembershipService iMembershipService = membershipServiceLocator.getFXEndPoint();
            MemberRegisterReqBody reqBody = new MemberRegisterReqBody();
            //reqBody.setMessageId();
            reqBody.setMessagebody(requestBody);
            //reqBody.setTimeStamp();
            MemberRegisterRequest registerRequest = new MemberRegisterRequest();
            ReqHeader reqHeader=new ReqHeader();
            reqHeader.setAuthorizationId("admin");
            reqHeader.setAuthorizationPwd("admin_1100903");
            reqHeader.setSysId("WXHY_1100903");
            registerRequest.setReqHeader(reqHeader);
            registerRequest.setReqBody(reqBody);
            MemberRegisterResponse response = iMembershipService.memberRegister(registerRequest);
            if (response.getResHeader().getStatus().equals(0)) {//失败
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员注册接口:注册数据失败！");
                interfaceLogEntity.setCode("500");
                interfaceLogEntity.setEntityName("user_account_crm/user_crm/contact_crm/card_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogEntity.setMemberId(userCRMEntity.getMemberId());
                interfaceLogEntity.setMessage(response.getResHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            } else if (response.getResHeader().getStatus().equals(1)) {//成功
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供会员注册接口:注册数据成功！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("user_account_crm/user_crm/contact_crm/card_crm");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogEntity.setMemberId(userCRMEntity.getMemberId());
                interfaceLogEntity.setMessage(response.getResHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            }

            return "200";

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("注册:会员注册接口失败!");
            return "500";
        }
    }

}

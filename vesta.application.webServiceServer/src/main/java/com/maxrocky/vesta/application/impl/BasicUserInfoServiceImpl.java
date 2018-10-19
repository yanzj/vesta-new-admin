package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.inf.BasicUserInfoService;
import com.maxrocky.vesta.application.inf.HouseInfoService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.presistent.repositoryHbnImpl.UserFeedbackRepositoryImpl;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import javax.xml.ws.Endpoint;

/**
 * Created by liudongxin on 2016/4/10.
 * Description:
 * webService:接收金茂项目CRM传递的业主信息
 * ModifyBy:
 */
@Service("basicUserInfoService")
public class BasicUserInfoServiceImpl implements BasicUserInfoService {
    @Autowired
    private UserCRMRepository userRepository;
    @Autowired
    private ContactCRMRepository contactCRMRepository;
    @Autowired
    private FamilyCRMRepository familyCRMRepository;
    @Autowired
    private CarCRMRepository carCRMRepository;
    @Autowired
    private AccountCRMRepository accountCRMRepository;
    @Autowired
    private CardCRMRepository cardCRMRepository;
    @Autowired
    private HouseCRMRepository houseCRMRepository;
    @Autowired
    private HouseRoomRepository houseRoomRepository;
    @Autowired
    private HouseInfoRepository houseInfoRepository;
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;
    @Autowired
    private HouseUserCRMRepository houseUserCRMRepository;
    @Autowired
    private UserFeedbackRepositoryImpl userFeedbackRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    HouseInfoService houseInfoService;
    @Autowired
    SystemLogRepository systemLogRepository;
    @Autowired
    UserTotalRepository userTotalRepository;

    /**
     * CreateBy:liudongxin
     * Description:接收用户信息
     * param userInfo：用户信息参数
     * ModifyBy:
     */
    @Override
    public String userInfo(UserInfoDTO userInfo){
        try {
            //日期转换
            Calendar cal = Calendar.getInstance();
            if(userInfo!=null && !StringUtil.isEmpty(userInfo.getMemberId())){
                if(userInfo.getUserList().size()>0) {
                    for (UserDTO user : userInfo.getUserList()) {
                        if(user.getId()!=null && !"".equals(user.getId())){
                            /* 修正查询条件,若有memberId根据memberId查询,若没有则根据mobile和registerDate查询_2016-08-04_Wyd */
//                            UserCRMEntity userCRMEntity = userRepository.getMember(user.getId(), userInfo.getMemberId());
//                            if (null == userCRMEntity){
//                                userCRMEntity = userRepository.getMemberByMobile(user.getPhone(), user.getRegisterDate());
//                            }
                            /* ====================================================================================== */
                            UserCRMEntity userCRMEntity = userRepository.getMember(user.getId(), userInfo.getMemberId());
                            //如果有数据，则更新;如果无，则创建
                            if (userCRMEntity != null) {
                                //userCRMEntity.setId(user.getId());
                                if(!StringUtil.isEmpty(user.getUserName())) {
                                    userCRMEntity.setUserName(user.getUserName());
                                }
                                if(!StringUtil.isEmpty(user.getEnglishName())) {
                                    userCRMEntity.setNickName(user.getEnglishName());
                                }
                                if(!StringUtil.isEmpty(user.getRealName())) {
                                    userCRMEntity.setRealName(user.getRealName());
                                }
                                if(!StringUtil.isEmpty(userInfo.getMemberId())) {
                                    userCRMEntity.setMemberId(userInfo.getMemberId());
                                }
                                if(!StringUtil.isEmpty(user.getSex())){
                                    userCRMEntity.setSex(user.getSex());
                                }
                                if(user.getBirthday()!=null) {
                                    cal.setTime(user.getBirthday());
                                    //cal.add(Calendar.HOUR_OF_DAY, 16);
                                    userCRMEntity.setBirthday(cal.getTime());
                                }
                                if(!StringUtil.isEmpty(user.getPhone())) {
                                    userCRMEntity.setMobile(user.getPhone());
                                }
                                if(!StringUtil.isEmpty(user.getIdCard())) {
                                    userCRMEntity.setIdCard(user.getIdCard());
                                }
                                if(!StringUtil.isEmpty(user.getUserType())) {
                                    userCRMEntity.setUserType(user.getUserType());
                                }
                                if(user.getModifyDate()!=null) {
                                    cal.setTime(user.getModifyDate());
                                    //cal.add(Calendar.HOUR_OF_DAY, 16);
                                    userCRMEntity.setModifyOn(cal.getTime());
                                }
                                if(!StringUtil.isEmpty(user.getNationality())) {
                                    userCRMEntity.setNationality(user.getNationality());
                                }
                                if(StringUtil.isEmpty(user.getEduLevel())) {
                                    userCRMEntity.setEducation(user.getEduLevel());
                                }
                                if(!StringUtil.isEmpty(user.getNativePlace())) {
                                    userCRMEntity.setNativesPlace(user.getNativePlace());
                                }
                                if(!StringUtil.isEmpty(user.getRegisterDate())) {
                                    userCRMEntity.setRegisterDate(DateUtils.parse(user.getRegisterDate(),"yyyy/MM/dd HH:mm:ss"));
                                }
                                userCRMEntity.setModifyOn(new Date());
                                if(!StringUtil.isEmpty(user.getState())) {
                                    userCRMEntity.setState(user.getState());
                                }
                                userRepository.update(userCRMEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:更新用户数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("user_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                                //处理
                            } else {
                                //下方代码可优化,无需声明userCRM这个变量,代码进入此处userCRMEntity为Null,可直接用,便于后面申诉处理
                                /*
                                UserCRMEntity userCRM = new UserCRMEntity();
                                userCRM.setUserId(IdGen.uuid());
                                userCRM.setId(user.getId());
                                userCRM.setMemberId(userInfo.getMemberId());
                                userCRM.setUserName(user.getUserName());
                                userCRM.setRealName(user.getRealName());
                                userCRM.setBirthday(user.getBirthday());
                                userCRM.setCreateBy(user.getRealName());
                                userCRM.setCreateOn(new Date());
                                userCRM.setMobile(user.getPhone());
                                userCRM.setNickName(user.getEnglishName());
                                userCRM.setIdCard(user.getIdCard());
                                userCRM.setUserType(user.getUserType());
                                if(StringUtil.isEmpty(user.getSex())){
                                    userCRM.setSex(user.getSex());
                                }
                                //userCRMEntity.setModifyOn(user.getModifyDate());
                                userCRM.setUserState(1);
                                userCRM.setNationality(user.getNationality());
                                userCRM.setEducation(user.getEduLevel());
                                userCRM.setNativesPlace(user.getNativePlace());
                                if(user.getRegisterDate()==null) {
                                    userCRM.setRegisterDate(new Date());
                                }else{
                                    userCRM.setRegisterDate(DateUtils.parse(user.getRegisterDate(),"yyyy/MM/dd HH:mm:ss"));
                                }
                                userCRM.setState(user.getState());
                                userRepository.create(userCRM);
                                */
                                //下方代码是上方代码的优化,便于后面申诉处理
                                userCRMEntity = new UserCRMEntity();
                                userCRMEntity.setUserId(IdGen.uuid());
                                userCRMEntity.setId(user.getId());
                                userCRMEntity.setMemberId(userInfo.getMemberId());
                                userCRMEntity.setUserName(user.getUserName());
                                userCRMEntity.setRealName(user.getRealName());
                                userCRMEntity.setBirthday(user.getBirthday());
                                userCRMEntity.setCreateBy(user.getRealName());
                                userCRMEntity.setCreateOn(new Date());
                                userCRMEntity.setMobile(user.getPhone());
                                userCRMEntity.setNickName(user.getEnglishName());
                                userCRMEntity.setIdCard(user.getIdCard());
                                userCRMEntity.setUserType(user.getUserType());
                                if(StringUtil.isEmpty(user.getSex())){
                                    userCRMEntity.setSex(user.getSex());
                                }
                                //userCRMEntity.setModifyOn(user.getModifyDate());
                                userCRMEntity.setUserState(1);
                                userCRMEntity.setNationality(user.getNationality());
                                userCRMEntity.setEducation(user.getEduLevel());
                                userCRMEntity.setNativesPlace(user.getNativePlace());
                                if(user.getRegisterDate()==null) {
                                    userCRMEntity.setRegisterDate(new Date());
                                }else{
                                    userCRMEntity.setRegisterDate(DateUtils.parse(user.getRegisterDate(),"yyyy/MM/dd HH:mm:ss"));
                                }
                                userCRMEntity.setState(user.getState());
                                userRepository.create(userCRMEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:创建用户数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("user_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }
                        }
                    }
                }
                if(userInfo.getContactList().size()>0){
                    for(ContactDTO contact:userInfo.getContactList()){
                        if(contact.getPhoneNumber() !=null && !"".equals(contact.getPhoneNumber())){
                            ContactCRMEntity contactCRMEntity=contactCRMRepository.get(userInfo.getMemberId());
                            if(contactCRMEntity!=null){
                                if(!StringUtil.isEmpty(contact.getMailbox())) {
                                    contactCRMEntity.setEMail(contact.getMailbox());
                                }
                                if(!StringUtil.isEmpty(contact.getWeiboid())) {
                                    contactCRMEntity.setWeiBo(contact.getWeiboid());
                                }
                                if(!StringUtil.isEmpty(contact.getWeixinid())) {
                                    contactCRMEntity.setWeiXin(contact.getWeixinid());
                                }
                                if(!StringUtil.isEmpty(contact.getQqid())){
                                    contactCRMEntity.setQq(contact.getQqid());
                                }
                                if(!StringUtil.isEmpty(contact.getCommunicationAddress())) {
                                    contactCRMEntity.setAddress(contact.getCommunicationAddress());
                                }
                                if(!StringUtil.isEmpty(contact.getAlternateContactinfor1())) {
                                    contactCRMEntity.setBackUpPhoneOne(contact.getAlternateContactinfor1());
                                }
                                if(!StringUtil.isEmpty(contact.getAlternateContactinfor2())) {
                                    contactCRMEntity.setBackUpPhoneTwo(contact.getAlternateContactinfor2());
                                }
                                if(!StringUtil.isEmpty(contact.getAlternateContactinfor3())) {
                                    contactCRMEntity.setBackUpPhoneThree(contact.getAlternateContactinfor3());
                                }
                                if(!StringUtil.isEmpty(contact.getAlternateContactinfor4())) {
                                    contactCRMEntity.setBackUpPhoneFour(contact.getAlternateContactinfor4());
                                }
                                if(!StringUtil.isEmpty(contact.getAlternateContactinfor5())) {
                                    contactCRMEntity.setBackUpPhoneFive(contact.getAlternateContactinfor5());
                                }
                                if(!StringUtil.isEmpty(contact.getPhoneNumber())) {
                                    contactCRMEntity.setPhoneNumber(contact.getPhoneNumber());
                                }
                                if(!StringUtil.isEmpty(contact.getStateCode())) {
                                    contactCRMEntity.setStateCode(contact.getStateCode());
                                }
                                contactCRMRepository.update(contactCRMEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:更新通讯数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("contact_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }else{
                                contactCRMEntity=new ContactCRMEntity();
                                contactCRMEntity.setId(userInfo.getMemberId());
                                contactCRMEntity.setMemberId(userInfo.getMemberId());
                                contactCRMEntity.setEMail(contact.getMailbox());
                                contactCRMEntity.setWeiBo(contact.getWeiboid());
                                contactCRMEntity.setWeiXin(contact.getWeixinid());
                                contactCRMEntity.setQq(contact.getQqid());
                                contactCRMEntity.setAddress(contact.getCommunicationAddress());
                                contactCRMEntity.setBackUpPhoneOne(contact.getAlternateContactinfor1());
                                contactCRMEntity.setBackUpPhoneTwo(contact.getAlternateContactinfor2());
                                contactCRMEntity.setBackUpPhoneThree(contact.getAlternateContactinfor3());
                                contactCRMEntity.setBackUpPhoneFour(contact.getAlternateContactinfor4());
                                contactCRMEntity.setBackUpPhoneFive(contact.getAlternateContactinfor5());
                                contactCRMEntity.setPhoneNumber(contact.getPhoneNumber());
                                contactCRMEntity.setStateCode(contact.getStateCode());
                                contactCRMRepository.create(contactCRMEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:创建通讯数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("contact_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }
                        }
                    }
                }
                if(userInfo.getFamilyList().size()>0){
                    for(FamilyDTO family:userInfo.getFamilyList()){
                        if(family.getId()!=null && !"".equals(family.getId())){
                            FamilyCRMEntity familyCRMEntity=familyCRMRepository.get(family.getId(),family.getMemberId());
                            if(familyCRMEntity!=null){
                                if(!StringUtil.isEmpty(family.getMemberId())) {
                                    familyCRMEntity.setMemberId(family.getMemberId());
                                }
                                if(!StringUtil.isEmpty(family.getRelationsWithMember())) {
                                    familyCRMEntity.setMembership(family.getRelationsWithMember());
                                }
                                if(family.getBirthDate()!=null) {
                                    cal.setTime(family.getBirthDate());
                                    //cal.add(Calendar.HOUR_OF_DAY, 16);
                                    familyCRMEntity.setBirthDate(cal.getTime());
                                }
                                if(!StringUtil.isEmpty(family.getPhoneNumber())){
                                    familyCRMEntity.setPhoneNumber(family.getPhoneNumber());
                                }
                                if(!StringUtil.isEmpty(family.getName())) {
                                    familyCRMEntity.setName(family.getName());
                                }
                                if(!StringUtil.isEmpty(family.getStateCode())) {
                                    familyCRMEntity.setStateCode(family.getStateCode());
                                }
                                familyCRMRepository.update(familyCRMEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:更新家庭数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("family_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }else{
                                familyCRMEntity=new FamilyCRMEntity();
                                familyCRMEntity.setId(family.getId());
                                familyCRMEntity.setMemberId(family.getMemberId());
                                familyCRMEntity.setMembership(family.getRelationsWithMember());
                                if(family.getBirthDate()!=null){
                                    cal.setTime(family.getBirthDate());
                                    cal.add(Calendar.HOUR_OF_DAY, 16);
                                    familyCRMEntity.setBirthDate(cal.getTime());
                                }else{
                                    familyCRMEntity.setBirthDate(new Date());
                                }
                                familyCRMEntity.setPhoneNumber(family.getPhoneNumber());
                                familyCRMEntity.setName(family.getName());
                                familyCRMEntity.setStateCode(family.getStateCode());
                                familyCRMRepository.create(familyCRMEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:创建家庭数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("family_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }
                        }
                    }
                }
                if(userInfo.getCarList().size()>0){
                    for(CarDTO car:userInfo.getCarList()){
                        if(car.getId()!=null && !"".equals(car.getId())){
                            CarCRMEntity carCRMEntity=carCRMRepository.get(car.getId(), userInfo.getMemberId());
                            if(carCRMEntity!=null){
                                if(!StringUtil.isEmpty(car.getMemberId())) {
                                    carCRMEntity.setMemberId(car.getMemberId());
                                }
                                if(!StringUtil.isEmpty(car.getLicenseId())) {
                                    carCRMEntity.setLicenseId(car.getLicenseId());
                                }
                                if(!StringUtil.isEmpty(car.getCarType())) {
                                    carCRMEntity.setCarType(car.getCarType());
                                }
                                if(!StringUtil.isEmpty(car.getCarPower())) {
                                    carCRMEntity.setCarPower(car.getCarType());
                                }
                                if(!StringUtil.isEmpty(car.getStateCode())) {
                                    carCRMEntity.setStateCode(car.getStateCode());
                                }
                                carCRMRepository.update(carCRMEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:更新车辆数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("car_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }else{
                                carCRMEntity=new CarCRMEntity();
                                carCRMEntity.setId(car.getId());
                                carCRMEntity.setMemberId(car.getMemberId());
                                carCRMEntity.setLicenseId(car.getLicenseId());
                                carCRMEntity.setCarType(car.getCarType());
                                carCRMEntity.setCarPower(car.getCarType());
                                carCRMEntity.setStateCode(car.getStateCode());
                                carCRMRepository.create(carCRMEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:创建车辆数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("car_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }
                        }
                    }
                }
                if(userInfo.getAccountList().size()>0) {
                    for (AccountDTO account : userInfo.getAccountList()) {
                        if(account.getId()!=null && !"".equals(account.getId())){
                            AccountCRMEntity accountCRMEntity = accountCRMRepository.get(account.getId(), userInfo.getMemberId());
                            //如果有数据，则更新;如果无，则创建
                            if (accountCRMEntity != null) {
                                //accountCRMEntity.setId(account.getId());
                                if(!StringUtil.isEmpty(userInfo.getMemberId())) {
                                    accountCRMEntity.setMemberId(userInfo.getMemberId());
                                }
                                if(!StringUtil.isEmpty(account.getNickName())) {
                                    accountCRMEntity.setNickName(account.getNickName());
                                }
                                if(!StringUtil.isEmpty(account.getPhoneNumber())) {
                                    accountCRMEntity.setMobile(account.getPhoneNumber());
                                }
                                if(!StringUtil.isEmpty(account.getRegisterId())) {
                                    accountCRMEntity.setRegisterId(account.getRegisterId());
                                }
                                if(!StringUtil.isEmpty(account.getPassword())) {
                                    accountCRMEntity.setPassword(account.getPassword());
                                }
                                if(!StringUtil.isEmpty(account.getMailBox())) {
                                    accountCRMEntity.setMailBox(account.getMailBox());
                                }
                                accountCRMEntity.setModifyOn(new Date());
                                accountCRMRepository.update(accountCRMEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:更新账户数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("user_account_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            } else {
                                AccountCRMEntity accountCRM = new AccountCRMEntity();
                                accountCRM.setAccountId(IdGen.uuid());
                                accountCRM.setId(account.getId());
                                accountCRM.setMemberId(userInfo.getMemberId());
                                accountCRM.setPassword(account.getPassword());
                                accountCRM.setMailBox(account.getMailBox());
                                accountCRM.setNickName(account.getNickName());
                                accountCRM.setMobile(account.getPhoneNumber());
                                accountCRM.setCreateOn(new Date());
                                accountCRMRepository.create(accountCRM);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:创建账户数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("user_account_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }
                        }
                    }
                }
                if(userInfo.getCardList().size()>0) {
                    for (CardDTO card: userInfo.getCardList()) {
                        if(card.getId()!=null && !"".equals(card.getId())){
                            CardCRMEntity cardCRMEntity = cardCRMRepository.get(card.getId(), userInfo.getMemberId());
                            //如果有数据，则更新;如果无，则创建
                            if (cardCRMEntity != null) {
                                //cardCRMEntity.setId(card.getId());
                                if(!StringUtil.isEmpty(userInfo.getMemberId())) {
                                    cardCRMEntity.setMemberId(userInfo.getMemberId());
                                }
                                if(!StringUtil.isEmpty(card.getBusinessSource())) {
                                    cardCRMEntity.setBusinessSource(card.getBusinessSource());
                                }
                                if(!StringUtil.isEmpty(card.getCardNumber())) {
                                    cardCRMEntity.setCardNumber(card.getCardNumber());
                                }
                                if(!StringUtil.isEmpty(card.getCardType())) {
                                    cardCRMEntity.setCardType(card.getCardType());
                                }
                                if(!StringUtil.isEmpty(card.getCardStatus())) {
                                    cardCRMEntity.setCardStatus(card.getCardStatus());
                                }
                                if(!StringUtil.isEmpty(card.getSendCardShop())) {
                                    cardCRMEntity.setSendCardShop(card.getSendCardShop());
                                }
                                if(card.getFailDate()!=null) {
                                    cardCRMEntity.setFailDate(DateUtils.parse2(card.getFailDate()));
                                }
                                if(!StringUtil.isEmpty(card.getFormerCardNumber())) {
                                    cardCRMEntity.setFormerCardNumber(card.getFormerCardNumber());
                                }
                                if(card.getSendCardDate()!=null) {
                                    cardCRMEntity.setSendCardDate(DateUtils.parse2(card.getSendCardDate()));
                                }
                                cardCRMRepository.update(cardCRMEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:更新会员卡数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("card_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            } else {
                                CardCRMEntity cardCRM = new CardCRMEntity();
                                cardCRM.setCardId(IdGen.uuid());
                                cardCRM.setId(card.getId());
                                cardCRM.setMemberId(userInfo.getMemberId());
                                cardCRM.setCardNumber(card.getCardNumber());
                                cardCRM.setBusinessSource(card.getBusinessSource());
                                cardCRM.setSendCardShop(card.getSendCardShop());
                                cardCRM.setCardStatus(card.getCardStatus());
                                cardCRM.setFormerCardNumber(card.getFormerCardNumber());
                                if(card.getFailDate()==null){
                                    cardCRM.setFailDate(new Date());
                                }else {
                                    cardCRM.setFailDate(DateUtils.parse(card.getFailDate()));
                                }
                                if(card.getSendCardDate()==null) {
                                    cardCRM.setSendCardDate(new Date());
                                }else{
                                    //CRM在此处有可能传递形如:2018/08/20 00:00:00 的日期类型,此处即会报错
                                    //正确日期类型为:2017-08-20 00:00:00 此处应做处理
                                    if ("/".equals(card.getSendCardDate())){
                                        cardCRM.setSendCardDate(null);
                                    }else{
                                        cardCRM.setSendCardDate(DateUtils.parse(card.getSendCardDate()));
                                    }
                                }
                                cardCRM.setCardType(card.getCardType());
                                cardCRMRepository.create(cardCRM);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:创建会员卡数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("card_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }
                        }
                    }
                }
                if(userInfo.getHouseList().size()>0) {
                    //存在大于一条业主房产信息,houseUserCRM同步原则,清空该业主房产对应数据
                    System.out.println("---->>>> 准备删除业主 " + userInfo.getMemberId() + " 的业主房产对应关系数据.");
                    houseUserCRMRepository.delHouseUserByMemberId(userInfo.getMemberId());
                    HouseUserCRMEntity houseUserCRMEntity = null;
                    for(HouseDTO house : userInfo.getHouseList()){
                        if(house.getId()!=null && !"".equals(house.getId())){
                            HouseCRMEntity houseCRMEntity = houseCRMRepository.getByMemberId(house.getId(),userInfo.getMemberId());
                            HouseRoomEntity houseRoomEntity=houseRoomRepository.getByMemberId(house.getId());
                            HouseInfoEntity houseInfoEntity=houseInfoRepository.getHouseByRoomId(house.getHouseNum());
                            //如果有数据，则更新;如果无，则创建
                            if (houseCRMEntity != null) {
                                //houseCRMEntity.setId(house.getId());
                                if(!StringUtil.isEmpty(userInfo.getMemberId())) {
                                    houseCRMEntity.setMemberId(userInfo.getMemberId());
                                }
                                if(!StringUtil.isEmpty(house.getUserId())) {
                                    houseCRMEntity.setUserId(house.getUserId());
                                }
                                if(!StringUtil.isEmpty(house.getCity())) {
                                    houseCRMEntity.setCity(house.getCity());
                                }
                                if(!StringUtil.isEmpty(house.getFormatName())) {
                                    houseCRMEntity.setFormatName(house.getFormatName());
                                }
                                if(!StringUtil.isEmpty(house.getArea())) {
                                    houseCRMEntity.setArea(house.getArea());
                                }
                                if(!StringUtil.isEmpty(house.getCommunity())) {
                                    houseCRMEntity.setProjectName(house.getCommunity());
                                }
                                if(!StringUtil.isEmpty(house.getBuild())) {
                                    houseCRMEntity.setBuildingId(house.getBuild());
                                }
                                if(!StringUtil.isEmpty(house.getBuilding())) {
                                    houseCRMEntity.setBuildingName(house.getBuilding());
                                }
                                if(!StringUtil.isEmpty(house.getUnit())) {
                                    houseCRMEntity.setUnitName(house.getUnit());
                                }
                                if(!StringUtil.isEmpty(house.getRoom())) {
                                    houseCRMEntity.setRoomName(house.getRoom());
                                }
                                if(house.getBuildingArea()!=null) {
                                    houseCRMEntity.setBuildingArea(house.getBuildingArea());
                                }
                                if(house.getCarpetArea()!=null) {
                                    houseCRMEntity.setUsableArea(house.getCarpetArea());
                                }
                                if(!StringUtil.isEmpty(house.getHouseType())) {
                                    houseCRMEntity.setHouseType(house.getHouseType());
                                }
                                if(house.getModifyDate()!=null) {
                                    houseCRMEntity.setModifyOn(house.getModifyDate());
                                }
                                if(house.getBuyDate()!=null) {
                                    houseCRMEntity.setCreateOn(house.getBuyDate());
                                }
                                if(!StringUtil.isEmpty(house.getDeclareStadanard())) {
                                    houseCRMEntity.setDeclareStandard(house.getDeclareStadanard());
                                }
                                if(!StringUtil.isEmpty(house.getStateCode())) {
                                    houseCRMEntity.setStateCode(house.getStateCode());
                                }
                                houseCRMRepository.updateHouseInfo(houseCRMEntity);
//                                houseRoomRepository.updateHouseInfo(houseRoomEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:更新房屋数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("house_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            } else {
                                HouseCRMEntity houseCRM=new HouseCRMEntity();
                                houseCRM.setHouseId(IdGen.uuid());
                                houseCRM.setMemberId(userInfo.getMemberId());
                                houseCRM.setId(house.getId());
                                houseCRM.setUserId(house.getUserId());
                                houseCRM.setCreateBy(house.getUserId());
                                houseCRM.setCity(house.getCity());
                                houseCRM.setFormatName(house.getFormatName());
                                houseCRM.setArea(house.getArea());
                                houseCRM.setProjectName(house.getCommunity());
                                houseCRM.setBuildingId(house.getBuild());
                                houseCRM.setBuildingName(house.getBuilding());
                                houseCRM.setUnitName(house.getUnit());
                                houseCRM.setRoomName(house.getRoom());
                                houseCRM.setBuildingArea(house.getBuildingArea());
                                houseCRM.setUsableArea(house.getCarpetArea());
                                houseCRM.setHouseType(house.getHouseType());
                                houseCRM.setRoomId(house.getId());
                                houseCRM.setRoomNum(house.getHouseNum());
                                if(house.getBuyDate()==null){
                                    houseCRM.setCreateOn(new Date());
                                }else {
                                    houseCRM.setCreateOn(house.getBuyDate());
                                }
                                if(house.getModifyDate()==null) {
                                    houseCRM.setModifyOn(new Date());
                                }else {
                                    houseCRM.setModifyOn(house.getModifyDate());
                                }
                                houseCRM.setDeclareStandard(house.getDeclareStadanard());
                                houseCRM.setStateCode(house.getStateCode());
                                houseCRMRepository.create(houseCRM);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:创建房屋数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("house_crm");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }
                            if(houseRoomEntity!=null){
                                if(!StringUtil.isEmpty(userInfo.getMemberId())) {
                                    houseRoomEntity.setMemberId(userInfo.getMemberId());
                                }
                                if(!StringUtil.isEmpty(house.getUserId())) {
                                    houseRoomEntity.setModifyBy(house.getUserId());
                                    houseRoomEntity.setMemberId(userInfo.getMemberId());
                                }
                                if(!StringUtil.isEmpty(house.getUnit())) {
                                    houseRoomEntity.setUnitId(house.getUnit());
                                }
                                if(!StringUtil.isEmpty(house.getRoom())) {
                                    houseRoomEntity.setName(house.getRoom());
                                }
                                if(house.getModifyDate()!=null) {
                                    houseRoomEntity.setModifyOn(house.getModifyDate());
                                }
                                houseRoomRepository.updateHouseInfo(houseRoomEntity);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:更新房屋数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("house_room");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }else{
                                //插入房间数据
                                HouseRoomEntity houseRoom=new HouseRoomEntity();
                                houseRoom.setId(house.getId());
                                houseRoom.setMemberId(userInfo.getMemberId());
                                houseRoom.setName(house.getRoom());
                                houseRoom.setUnitId(house.getUnit());
                                houseRoom.setCreateBy(house.getUserId());
                                houseRoom.setCreateOn(new Date());
                                if(house.getModifyDate()==null) {
                                    houseRoom.setModifyOn(new Date());
                                }else {
                                    houseRoom.setModifyOn(house.getModifyDate());
                                }
                                houseRoomRepository.create(houseRoom);
                                //调用日志
                                InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
                                interfaceLogEntity.setId(IdGen.uuid());
                                interfaceLogEntity.setInterfaceName("调用会员信息接口:创建房屋数据！");
                                interfaceLogEntity.setCode("200");
                                interfaceLogEntity.setEntityName("house_room");
                                interfaceLogEntity.setErrorDate(new Date());
                                interfaceLogRepository.create(interfaceLogEntity);
                            }
                            /**
                             * 下面if代码块执行后,会造成houseinfo表房产和业主memberId一对一对应。  Wyd_20170301
                             */
                            if(houseInfoEntity!=null){
                                if(!StringUtil.isEmpty(house.getMemberId())){
                                    houseInfoEntity.setMemberId(house.getMemberId());
                                }else {
                                    if(!StringUtil.isEmpty(house.getUserId())){
                                        houseInfoEntity.setMemberId(house.getUserId());
                                    }
                                }
                                houseInfoRepository.updateHouseInfo(houseInfoEntity);
                            }
                            /**
                             * 下面是新增的业主房产同步逻辑,是对上面if代码块造成的业主房产一对一关系的修正,业主房产对应关系为多对多   Wyd_20170301
                             */
                            houseUserCRMEntity = new HouseUserCRMEntity();
                            //房产业主关系表主键切换为自增长ID
//                            houseUserCRMEntity.setId(IdGen.uuid());     //主键
                            houseUserCRMEntity.setMemberId(userInfo.getMemberId());     //业主Id
                            houseUserCRMEntity.setRoomId(house.getId());        //房间Id
                            houseUserCRMEntity.setRoomNum(house.getHouseNum());     //房间编码
                            houseUserCRMEntity.setCreateOn(new Date());
                            //执行保存
                            houseUserCRMRepository.saveOrUpdate(houseUserCRMEntity);
                            //更新HouseUserCrm 地址，项目，业主姓名等信息
                            houseUserCRMRepository.updateHouseUserCrm(userInfo.getMemberId());
                        }
                    }
                    //for循环外
                }else{
                    //CRM用户同步信息中没有房产信息时,中间关系需要清掉
                    System.out.println("---->>>> 业主 " + userInfo.getMemberId() + " 没有房产信息,准备清除业主房产对应关系数据.");
                    houseUserCRMRepository.delHouseUserByMemberId(userInfo.getMemberId());
                }
                System.out.println("调用：用户信息接口成功！");
                //此处开始处理申述业务
                System.out.println("--->>> 开始申诉自动处理.");
                /*
                //自动处理申诉业务暂停
                if(userInfo.getUserList().size()>0) {
                    for (UserDTO user : userInfo.getUserList()) {
                        if (user.getId() != null && !"".equals(user.getId())) {
                            UserCRMEntity userCRMEntity = userRepository.getMember(user.getId(), userInfo.getMemberId());
                            if (null != userCRMEntity){
                                //通过真实姓名和身份证去检索申诉信息
                                String content = "姓名:" + userCRMEntity.getRealName() + "，证件号:" + userCRMEntity.getIdCard();
                                List<UserFeedbackEntity> feedbackList = userFeedbackRepository.getFeedbackListByContent(content);
                                if (null != feedbackList && feedbackList.size() > 0){
                                    UserFeedbackEntity userFeedbackEntity = feedbackList.get(0);
                                    //存在申诉
                                    //认证业主
                                    UserInfoEntity userInfoEntity = userInfoRepository.get(userFeedbackEntity.getUserId());
                                    userInfoEntity.setIdCard(userCRMEntity.getIdCard());
                                    userInfoEntity.setId(userCRMEntity.getMemberId());
                                    userInfoEntity.setRealName(userCRMEntity.getRealName());
                                    userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_OWNER);  //认证成功 将用户类型变更为业主
                                    userInfoEntity.setJump("4");
                                    userInfoRepository.update(userInfoEntity);
                                    //门禁分配
                                    //检索用户房产列表
                                    List<HouseInfoEntity> houseInfoEntityList = houseInfoRepository.getHouseByUserMemberId(userCRMEntity.getMemberId());
                                    //门禁分配
                                    HouseInfoEntity houseInfoEntity = null;
                                    if (!houseInfoEntityList.isEmpty() && houseInfoEntityList.size() > 0) {
                                        for (int i = 0,length = houseInfoEntityList.size(); i<length; i++){
                                            houseInfoEntity = houseInfoEntityList.get(i);
                                            houseInfoService.assignDoorByHouse(houseInfoEntity.getProjectNum(),houseInfoEntity.getArea(),houseInfoEntity.getBuildingNum(),houseInfoEntity.getUnitNum(),userInfoEntity);
                                        }
                                    }
                                    //变更申诉状态
                                    for (int i=0,length=feedbackList.size(); i<length; i++){
                                        UserFeedbackEntity feedbackEntity = feedbackList.get(i);
                                        feedbackEntity.setModifyBy("系统自动处理");
                                        feedbackEntity.setModifyOn(new Date());
                                        feedbackEntity.setState("2");
                                        userFeedbackRepository.update(feedbackEntity);
                                    }
                                    System.out.println("-->> 业主:"+userCRMEntity.getRealName()+",证件号:"+userCRMEntity.getIdCard()+",申诉已被处理.");
                                }
                            }
                        }
                    }
                }
                */
                if(userInfo.getUserList().size()>0) {
                    for (UserDTO user : userInfo.getUserList()) {
                        if (user.getId() != null && !"".equals(user.getId())) {
                            UserCRMEntity userCRMEntity = userRepository.getMember(user.getId(), userInfo.getMemberId());
                            if (null != userCRMEntity){
                                //通过真实姓名和身份证去检索申诉信息
                                List<OwnerAppealEntity> ownerAppealEntityList = userInfoRepository.getOwnerAppealByOwner(userCRMEntity.getRealName(), userCRMEntity.getIdCard());
                                for (OwnerAppealEntity ownerAppealEntity : ownerAppealEntityList){
                                    //存在申诉,认证业主
                                    UserInfoEntity userInfoEntity = userInfoRepository.get(ownerAppealEntity.getUserId());
                                    if(!"3".equals(userInfoEntity.getUserType())){
                                        userInfoEntity.setIdCard(userCRMEntity.getIdCard());
                                        userInfoEntity.setId(userCRMEntity.getMemberId());
                                        userInfoEntity.setRealName(userCRMEntity.getRealName());
                                        userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_OWNER);//用户类型变更为业主
                                        userInfoEntity.setJump("4");
                                        userInfoRepository.update(userInfoEntity);
                                        //门禁分配
                                        //检索用户房产列表
                                        List<HouseInfoEntity> houseInfoEntityList = houseInfoRepository.getHouseByUserMemberId(userCRMEntity.getMemberId());
                                        //门禁分配
                                        HouseInfoEntity houseInfoEntity = null;
                                        if (!houseInfoEntityList.isEmpty() && houseInfoEntityList.size() > 0) {
                                            for (int i = 0,length = houseInfoEntityList.size(); i<length; i++){
                                                houseInfoEntity = houseInfoEntityList.get(i);
                                                houseInfoService.assignDoorByHouse(houseInfoEntity.getProjectNum(),houseInfoEntity.getArea(),houseInfoEntity.getBuildingNum(),houseInfoEntity.getUnitNum(),userInfoEntity);
                                            }
                                        }
                                        //增加用户日志记录
                                        SystemLogEntity systemLogEntity = new SystemLogEntity();
                                        systemLogEntity.setLogId(IdGen.uuid());
                                        systemLogEntity.setLogTime(new Date());//注册日期
                                        systemLogEntity.setUserName(userInfoEntity.getNickName());//用户昵称
                                        systemLogEntity.setUserType(userInfoEntity.getUserType());//用户类型
                                        systemLogEntity.setUserMobile(userInfoEntity.getMobile());//手机号
                                        systemLogEntity.setIdCard(userInfoEntity.getIdCard());//身份证
                                        systemLogEntity.setSourceFrom(userInfoEntity.getSourceType());//来源
                                        systemLogEntity.setSysVersion(userInfoEntity.getOperatingSystem());//系统版本
                                        if (houseInfoEntityList != null && houseInfoEntityList.size() > 0) {
                                            systemLogEntity.setProjectId(houseInfoEntityList.get(0).getProjectName());//项目
                                        } else {
                                            systemLogEntity.setProjectId("");//项目
                                        }
                                        systemLogRepository.addSysLog(systemLogEntity);
                                        //调用用户统计
                                        String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
                                        UserToTalEntity userToTal = userTotalRepository.getTotalInfo(dateNow);
                                        if (userToTal != null) {
                                            if ("3".equals(userInfoEntity.getUserType())) {
                                                userToTal.setOwnerUser(userToTal.getOwnerUser() + 1);//业主用户
                                            }
                                            userTotalRepository.update(userToTal);
                                        } else {
                                            UserToTalEntity userToTalEntity = new UserToTalEntity();
                                            userToTalEntity.setId(IdGen.uuid());
                                            userToTalEntity.setCreateDate(new Date());
                                            userToTalEntity.setCommonUser(0);//普通用户
                                            if ("3".equals(userInfoEntity.getUserType())) {
                                                userToTalEntity.setOwnerUser(1);//业主用户
                                            } else {
                                                userToTalEntity.setOwnerUser(0);//业主用户
                                            }
                                            userToTalEntity.setAndroid(0);      //安卓用户
                                            userToTalEntity.setIos(0);          //IOS用户
                                            userToTalEntity.setWeChat(0);       //微信用户
                                            userToTalEntity.setAPP(0);          //APP用户
                                            userTotalRepository.create(userToTalEntity);
                                        }
                                        //变更申诉状态为101
                                        ownerAppealEntity.setHandleState(OwnerAppealEntity.STATE_PASS);
                                        ownerAppealEntity.setModifyOn(new Date());
                                        ownerAppealEntity.setModifyBy("系统自动处理");
                                        userInfoRepository.saveOrUpdate(ownerAppealEntity);
                                        System.out.println("-->> 业主:"+userCRMEntity.getRealName()+",证件号:"+userCRMEntity.getIdCard()+",申诉已被处理.");
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println("--->>> 申诉自动处理完毕.");
            }
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("调用：用户信息接口失败！");
            //调用日志
            InterfaceLogEntity interfaceLogEntity=new InterfaceLogEntity();
            interfaceLogEntity.setId(IdGen.uuid());
            interfaceLogEntity.setInterfaceName("接收会员信息接口:创建/更新数据！");
            interfaceLogEntity.setCode("500");
            interfaceLogEntity.setEntityName("user_crm/contact_crm/family_crm/car_crm/user_account_crm/card_crm/house_crm");
            interfaceLogEntity.setErrorDate(new Date());
            interfaceLogRepository.create(interfaceLogEntity);
            return "200";
        }
    }

    /**
     * CreateBy:liudongxin
     * Description:发布service类
     * Endpoint(服务绑定的地址,发布的实现类)
     * ModifyBy:
     */
    /*public static void main(String[] args){
        Endpoint.publish("http://localhost:8090/ws/userInfoService",new BasicUserInfoServiceImpl());
        System.out.println("service success!");
    }*/
}

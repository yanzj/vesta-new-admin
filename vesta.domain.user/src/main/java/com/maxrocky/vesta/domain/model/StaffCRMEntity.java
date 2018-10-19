package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by yuanyn on 2018/2/5.
 * 同步OA人员信息
 */
@Entity
@Table(name = "staff_OA")
public class StaffCRMEntity {
    private Integer userid ;               //用户id
    private String subcompanyid1;    //分部
    private String departmentid;   //部门
    private String workcode;       //编号
    private String lastname;       //姓名
    private String loginid;        //系统账号
    private String password;       //密码
    private String seclevel;        //安全级别
    private String sex;            //性别
    private String jobtitle;       //岗位
    private String jobactivityid;  //职务
    private String jobgroupid;     //职务类型
    private String jobcall;        //职称
    private String joblevel;        //职级
    private String jobactivitydesc;//职责描述
    private String managerid;      //直接上级
    private String assistantid;    //助理
    private String status;         //状态  eg:正式、试用等
    private String locationid;     //办公地点
    private String workroom;       //办公室
    private String telephone;      //办公电话
    private String mobile;         //移动电话
    private String mobilecall;     //其他电话
    private String fax;            //传真
    private String email;          //电子邮件
    private String systemlanguage;//系统语言  默认7
    private String birthday;       //生日
    private String folk;           //民族
    private String nativeplace;     //籍贯
    private String regresidentplace; //户口
    private String certificatenum;  //身份证号
    private String maritalstatus;   //婚姻状况
    private String policy;          //政治面貌
    private String bememberdate;    //入团日期
    private String bepartydate;     //入党日期
    private String islabouunion;    //是否是工会会员
    private String educationlevel;  //学历
    private String degree;           //学位
    private String healthinfo;       //健康状况
    private String height;           //身高
    private String weight;          //体重
    private String residentplace;    //居住地
    private String homeaddress;    //家庭住址
    private String tempresidentnumber; //暂住证号码
    private String startdate = "" ;    //合同开始日期
    private String enddate = "" ;      //合同结束日期
    private String createdate="";       //创建日期
    private String lastChangdate="";    //最后修改日期
    private Integer accounttype;            //账号类型
    private Integer dsporder;               //显示顺序
    private String personFields="";         //个人信息自定义
    private String personFieldsValue="";   //个人信息自定义值

    @Id
    @Column(name = "USER_ID", unique = true, nullable = false)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "SUBCOMPANY_ID")
    public String getSubcompanyid1() {
        return subcompanyid1;
    }

    public void setSubcompanyid1(String subcompanyid1) {
        this.subcompanyid1 = subcompanyid1;
    }

    @Basic
    @Column(name = "DEPARTMENT_ID")
    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    @Basic
    @Column(name = "WORK_CODE")
    public String getWorkcode() {
        return workcode;
    }

    public void setWorkcode(String workcode) {
        this.workcode = workcode;
    }

    @Basic
    @Column(name = "LAST_NAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "LOGIN_ID")
    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    @Basic
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "SECLEVEL")
    public String getSeclevel() {
        return seclevel;
    }

    public void setSeclevel(String seclevel) {
        this.seclevel = seclevel;
    }

    @Basic
    @Column(name = "SEX")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "JOB_TITLE")
    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    @Basic
    @Column(name = "JOB_ACTIVITY_ID")
    public String getJobactivityid() {
        return jobactivityid;
    }

    public void setJobactivityid(String jobactivityid) {
        this.jobactivityid = jobactivityid;
    }

    @Basic
    @Column(name = "JOB_GROUP_ID")
    public String getJobgroupid() {
        return jobgroupid;
    }

    public void setJobgroupid(String jobgroupid) {
        this.jobgroupid = jobgroupid;
    }

    @Basic
    @Column(name = "JOB_CALL")
    public String getJobcall() {
        return jobcall;
    }

    public void setJobcall(String jobcall) {
        this.jobcall = jobcall;
    }

    @Basic
    @Column(name = "JOB_LEVEL")
    public String getJoblevel() {
        return joblevel;
    }

    public void setJoblevel(String joblevel) {
        this.joblevel = joblevel;
    }

    @Basic
    @Column(name = "JOB_ACTIVITYDESC")
    public String getJobactivitydesc() {
        return jobactivitydesc;
    }

    public void setJobactivitydesc(String jobactivitydesc) {
        this.jobactivitydesc = jobactivitydesc;
    }

    @Basic
    @Column(name = "MANAGER_ID")
    public String getManagerid() {
        return managerid;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    @Basic
    @Column(name = "ASSISTANT_ID")
    public String getAssistantid() {
        return assistantid;
    }

    public void setAssistantid(String assistantid) {
        this.assistantid = assistantid;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "LOCATION_ID")
    public String getLocationid() {
        return locationid;
    }

    public void setLocationid(String locationid) {
        this.locationid = locationid;
    }

    @Basic
    @Column(name = "WORKROOM")
    public String getWorkroom() {
        return workroom;
    }

    public void setWorkroom(String workroom) {
        this.workroom = workroom;
    }

    @Basic
    @Column(name = "TELEPHONE")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "MOBILE")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "MOBILE_CALL")
    public String getMobilecall() {
        return mobilecall;
    }

    public void setMobilecall(String mobilecall) {
        this.mobilecall = mobilecall;
    }

    @Basic
    @Column(name = "FAX")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "SYSTEM_LANGUAGE")
    public String getSystemlanguage() {
        return systemlanguage;
    }

    public void setSystemlanguage(String systemlanguage) {
        this.systemlanguage = systemlanguage;
    }

    @Basic
    @Column(name = "BIRTHDAY")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "FOLK")
    public String getFolk() {
        return folk;
    }

    public void setFolk(String folk) {
        this.folk = folk;
    }

    @Basic
    @Column(name = "NATIVEPLACE")
    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    @Basic
    @Column(name = "REGRESIDENTPLACE")
    public String getRegresidentplace() {
        return regresidentplace;
    }

    public void setRegresidentplace(String regresidentplace) {
        this.regresidentplace = regresidentplace;
    }

    @Basic
    @Column(name = "CERTIFICATENUM")
    public String getCertificatenum() {
        return certificatenum;
    }

    public void setCertificatenum(String certificatenum) {
        this.certificatenum = certificatenum;
    }

    @Basic
    @Column(name = "MARITAL_STATUS")
    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    @Basic
    @Column(name = "POLICY")
    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    @Basic
    @Column(name = "BEMEMBER_DATE")
    public String getBememberdate() {
        return bememberdate;
    }

    public void setBememberdate(String bememberdate) {
        this.bememberdate = bememberdate;
    }

    @Basic
    @Column(name = "BEPARTY_DATE")
    public String getBepartydate() {
        return bepartydate;
    }

    public void setBepartydate(String bepartydate) {
        this.bepartydate = bepartydate;
    }

    @Basic
    @Column(name = "ISLABOUUNION")
    public String getIslabouunion() {
        return islabouunion;
    }

    public void setIslabouunion(String islabouunion) {
        this.islabouunion = islabouunion;
    }

    @Basic
    @Column(name = "EDUCATIONLEVEL")
    public String getEducationlevel() {
        return educationlevel;
    }

    public void setEducationlevel(String educationlevel) {
        this.educationlevel = educationlevel;
    }

    @Basic
    @Column(name = "DEGREE")
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Basic
    @Column(name = "HEALTH_INFO")
    public String getHealthinfo() {
        return healthinfo;
    }

    public void setHealthinfo(String healthinfo) {
        this.healthinfo = healthinfo;
    }

    @Basic
    @Column(name = "HEIGHT")
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Basic
    @Column(name = "WEIGHT")
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "RESIDENTPLACE")
    public String getResidentplace() {
        return residentplace;
    }

    public void setResidentplace(String residentplace) {
        this.residentplace = residentplace;
    }

    @Basic
    @Column(name = "HOME_ADDRESS")
    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    @Basic
    @Column(name = "TEMPRESIDENT_NUMBER")
    public String getTempresidentnumber() {
        return tempresidentnumber;
    }

    public void setTempresidentnumber(String tempresidentnumber) {
        this.tempresidentnumber = tempresidentnumber;
    }

    @Basic
    @Column(name = "START_DATE")
    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    @Basic
    @Column(name = "END_DATE")
    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    @Basic
    @Column(name = "LAST_CHANG_DATE")
    public String getLastChangdate() {
        return lastChangdate;
    }

    public void setLastChangdate(String lastChangdate) {
        this.lastChangdate = lastChangdate;
    }

    @Basic
    @Column(name = "ACCOUNT_TYPE")
    public Integer getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(Integer accounttype) {
        this.accounttype = accounttype;
    }

    @Basic
    @Column(name = "DSPORDER")
    public Integer getDsporder() {
        return dsporder;
    }

    public void setDsporder(Integer dsporder) {
        this.dsporder = dsporder;
    }

    @Basic
    @Column(name = "PERSON_FIELDS")
    public String getPersonFields() {
        return personFields;
    }

    public void setPersonFields(String personFields) {
        this.personFields = personFields;
    }

    @Basic
    @Column(name = "PERSON_FIELDS_VALUE")
    public String getPersonFieldsValue() {
        return personFieldsValue;
    }

    public void setPersonFieldsValue(String personFieldsValue) {
        this.personFieldsValue = personFieldsValue;
    }
}

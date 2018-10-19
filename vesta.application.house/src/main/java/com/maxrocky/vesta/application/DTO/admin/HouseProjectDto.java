package com.maxrocky.vesta.application.DTO.admin;

/**
 * Created by zhanghj on 2016/2/19.
 */
public class HouseProjectDto {

    private String id;                //主键
    private String name;              //名称
    private String pinyinCode;        //拼音简写
    private String originName;        //原名
    private String companyId;         //公司Id
    private String state;             //状态
    private String memo;              //备注
    private String createBy;          //创建人
    private String createOn;          //创建时间
    private String modifyBy;          //修改人
    private String modifyOn;          //修改时间
    private String viewAgencys;       //数据查看相关机构ID
    private String viewOrganizes;     //数据查看相关群组ID
    private String viewStaffs;        //数据查看相关人员ID
    private String plumbingAgency;    //工程接单相关机构ID
    private String plumbingOrganizes; //工程接单相关群组ID
    private String plumbingStaffs;    //工程接单相关人员ID
    private String propertyAgency;    //物业接单相关机构ID
    private String propertyOrganizes; //物业接单相关群组ID
    private String propertyStaffs;    //物业接单相关人员ID
    private String makesAgencys;      //开发接单相关机构ID
    private String makesOrganizes;    //开发接单相关群组ID
    private String makesStaffs;       //开发接单相关人员ID
    private String dispatchAgencys;   //派单到人相关机构ID
    private String dispatchOrganizes; //派单到人相关群组ID
    private String dispatchStaffs;    //派单到人相关人员ID
    private String closeAgencys;      //关单权限相关机构ID
    private String closeOrganizes;    //关单权限相关群组ID
    private String closeStaffs;       //关单权限相关人员ID
    private String checkAgencys;      //验房工程师相关机构ID
    private String checkOrganizes;    //验房工程师相关群组ID
    private String checkStaffs;       //验房工程师相关人员ID
    private String qualityAgencys;    //质量经理相关机构ID
    private String qualityOrganizes;  //质量经理相关群组ID
    private String qualityStaffs;     //质量经理相关人员ID
    private String secondAgencys;     //乙方工程师相关机构ID
    private String secondOrganizes;   //乙方工程师相关群组ID
    private String secondStaffs;      //乙方工程师相关人员ID
    private String dispatchSheetStaffs;//客观信息员（报事派单）相关人员ID
    private String dispatchSheetAgencys;//客观信息员（报事派单）相关机构ID
    private String dispatchSheetOrganizes;//客观信息员（报事派单）相关群组ID
    private String HQObjectiveStaffs;//总部客观（投诉废弃）相关人员ID
    private String HQObjectiveAgencys;//总部客观（投诉废弃）相关机构ID
    private String HQObjectiveOrganizes;//总部客观（投诉废弃）相关群组ID

    private String Repair1ObjectiveStaffs;//报修 ：数据查看 相关人员ID
    private String Repair1ObjectiveAgencys;//报修 ：数据查看 相关机构ID
    private String Repair1ObjectiveOrganizes;//报修 ：数据查看 相关群组ID

    private String ComplaintObjectiveStaffs;//投诉 ：数据查看 相关人员ID
    private String ComplaintObjectiveAgencys;//投诉  ：数据查看 相关机构ID
    private String ComplaintObjectiveOrganizes;//投诉 ：数据查看 相关群组ID


    private String ReceptionObjectiveStaffs;//投诉 ：项目前台 相关人员ID
    private String ReceptionObjectiveAgencys;//投诉  ：项目前台 相关机构ID
    private String ReceptionObjectiveOrganizes;//投诉 ：项目前台 相关群组ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMakesAgencys() {
        return makesAgencys;
    }

    public void setMakesAgencys(String makesAgencys) {
        this.makesAgencys = makesAgencys;
    }

    public String getMakesStaffs() {
        return makesStaffs;
    }

    public void setMakesStaffs(String makesStaffs) {
        this.makesStaffs = makesStaffs;
    }

    public String getPlumbingAgency() {
        return plumbingAgency;
    }

    public void setPlumbingAgency(String plumbingAgency) {
        this.plumbingAgency = plumbingAgency;
    }

    public String getPlumbingStaffs() {
        return plumbingStaffs;
    }

    public void setPlumbingStaffs(String plumbingStaffs) {
        this.plumbingStaffs = plumbingStaffs;
    }

    public String getPropertyAgency() {
        return propertyAgency;
    }

    public void setPropertyAgency(String propertyAgency) {
        this.propertyAgency = propertyAgency;
    }

    public String getPropertyStaffs() {
        return propertyStaffs;
    }

    public void setPropertyStaffs(String propertyStaffs) {
        this.propertyStaffs = propertyStaffs;
    }

    public String getViewAgencys() {
        return viewAgencys;
    }

    public void setViewAgencys(String viewAgencys) {
        this.viewAgencys = viewAgencys;
    }

    public String getViewStaffs() {
        return viewStaffs;
    }

    public void setViewStaffs(String viewStaffs) {
        this.viewStaffs = viewStaffs;
    }

    public String getDispatchAgencys() {
        return dispatchAgencys;
    }

    public void setDispatchAgencys(String dispatchAgencys) {
        this.dispatchAgencys = dispatchAgencys;
    }

    public String getDispatchStaffs() {
        return dispatchStaffs;
    }

    public void setDispatchStaffs(String dispatchStaffs) {
        this.dispatchStaffs = dispatchStaffs;
    }

    public String getCloseAgencys() {
        return closeAgencys;
    }

    public void setCloseAgencys(String closeAgencys) {
        this.closeAgencys = closeAgencys;
    }

    public String getCloseStaffs() {
        return closeStaffs;
    }

    public void setCloseStaffs(String closeStaffs) {
        this.closeStaffs = closeStaffs;
    }

    public String getViewOrganizes() {
        return viewOrganizes;
    }

    public void setViewOrganizes(String viewOrganizes) {
        this.viewOrganizes = viewOrganizes;
    }

    public String getPlumbingOrganizes() {
        return plumbingOrganizes;
    }

    public void setPlumbingOrganizes(String plumbingOrganizes) {
        this.plumbingOrganizes = plumbingOrganizes;
    }

    public String getPropertyOrganizes() {
        return propertyOrganizes;
    }

    public void setPropertyOrganizes(String propertyOrganizes) {
        this.propertyOrganizes = propertyOrganizes;
    }

    public String getMakesOrganizes() {
        return makesOrganizes;
    }

    public void setMakesOrganizes(String makesOrganizes) {
        this.makesOrganizes = makesOrganizes;
    }

    public String getDispatchOrganizes() {
        return dispatchOrganizes;
    }

    public void setDispatchOrganizes(String dispatchOrganizes) {
        this.dispatchOrganizes = dispatchOrganizes;
    }

    public String getCloseOrganizes() {
        return closeOrganizes;
    }

    public void setCloseOrganizes(String closeOrganizes) {
        this.closeOrganizes = closeOrganizes;
    }

    public String getCheckAgencys() {
        return checkAgencys;
    }

    public void setCheckAgencys(String checkAgencys) {
        this.checkAgencys = checkAgencys;
    }

    public String getCheckOrganizes() {
        return checkOrganizes;
    }

    public void setCheckOrganizes(String checkOrganizes) {
        this.checkOrganizes = checkOrganizes;
    }

    public String getCheckStaffs() {
        return checkStaffs;
    }

    public void setCheckStaffs(String checkStaffs) {
        this.checkStaffs = checkStaffs;
    }

    public String getQualityOrganizes() {
        return qualityOrganizes;
    }

    public void setQualityOrganizes(String qualityOrganizes) {
        this.qualityOrganizes = qualityOrganizes;
    }

    public String getQualityAgencys() {
        return qualityAgencys;
    }

    public void setQualityAgencys(String qualityAgencys) {
        this.qualityAgencys = qualityAgencys;
    }

    public String getQualityStaffs() {
        return qualityStaffs;
    }

    public void setQualityStaffs(String qualityStaffs) {
        this.qualityStaffs = qualityStaffs;
    }

    public String getSecondAgencys() {
        return secondAgencys;
    }

    public void setSecondAgencys(String secondAgencys) {
        this.secondAgencys = secondAgencys;
    }

    public String getSecondOrganizes() {
        return secondOrganizes;
    }

    public void setSecondOrganizes(String secondOrganizes) {
        this.secondOrganizes = secondOrganizes;
    }

    public String getSecondStaffs() {
        return secondStaffs;
    }

    public void setSecondStaffs(String secondStaffs) {
        this.secondStaffs = secondStaffs;
    }

    public String getDispatchSheetStaffs() {
        return dispatchSheetStaffs;
    }

    public void setDispatchSheetStaffs(String dispatchSheetStaffs) {
        this.dispatchSheetStaffs = dispatchSheetStaffs;
    }

    public String getDispatchSheetAgencys() {
        return dispatchSheetAgencys;
    }

    public void setDispatchSheetAgencys(String dispatchSheetAgencys) {
        this.dispatchSheetAgencys = dispatchSheetAgencys;
    }

    public String getDispatchSheetOrganizes() {
        return dispatchSheetOrganizes;
    }

    public void setDispatchSheetOrganizes(String dispatchSheetOrganizes) {
        this.dispatchSheetOrganizes = dispatchSheetOrganizes;
    }

    public String getHQObjectiveStaffs() {
        return HQObjectiveStaffs;
    }

    public void setHQObjectiveStaffs(String HQObjectiveStaffs) {
        this.HQObjectiveStaffs = HQObjectiveStaffs;
    }

    public String getHQObjectiveAgencys() {
        return HQObjectiveAgencys;
    }

    public void setHQObjectiveAgencys(String HQObjectiveAgencys) {
        this.HQObjectiveAgencys = HQObjectiveAgencys;
    }

    public String getHQObjectiveOrganizes() {
        return HQObjectiveOrganizes;
    }

    public void setHQObjectiveOrganizes(String HQObjectiveOrganizes) {
        this.HQObjectiveOrganizes = HQObjectiveOrganizes;
    }

    public String getRepair1ObjectiveStaffs() {
        return Repair1ObjectiveStaffs;
    }

    public void setRepair1ObjectiveStaffs(String repair1ObjectiveStaffs) {
        Repair1ObjectiveStaffs = repair1ObjectiveStaffs;
    }

    public String getRepair1ObjectiveAgencys() {
        return Repair1ObjectiveAgencys;
    }

    public void setRepair1ObjectiveAgencys(String repair1ObjectiveAgencys) {
        Repair1ObjectiveAgencys = repair1ObjectiveAgencys;
    }

    public String getRepair1ObjectiveOrganizes() {
        return Repair1ObjectiveOrganizes;
    }

    public void setRepair1ObjectiveOrganizes(String repair1ObjectiveOrganizes) {
        Repair1ObjectiveOrganizes = repair1ObjectiveOrganizes;
    }

    public String getComplaintObjectiveStaffs() {
        return ComplaintObjectiveStaffs;
    }

    public void setComplaintObjectiveStaffs(String complaintObjectiveStaffs) {
        ComplaintObjectiveStaffs = complaintObjectiveStaffs;
    }

    public String getComplaintObjectiveAgencys() {
        return ComplaintObjectiveAgencys;
    }

    public void setComplaintObjectiveAgencys(String complaintObjectiveAgencys) {
        ComplaintObjectiveAgencys = complaintObjectiveAgencys;
    }

    public String getComplaintObjectiveOrganizes() {
        return ComplaintObjectiveOrganizes;
    }

    public void setComplaintObjectiveOrganizes(String complaintObjectiveOrganizes) {
        ComplaintObjectiveOrganizes = complaintObjectiveOrganizes;
    }

    public String getReceptionObjectiveStaffs() {
        return ReceptionObjectiveStaffs;
    }

    public void setReceptionObjectiveStaffs(String receptionObjectiveStaffs) {
        ReceptionObjectiveStaffs = receptionObjectiveStaffs;
    }

    public String getReceptionObjectiveAgencys() {
        return ReceptionObjectiveAgencys;
    }

    public void setReceptionObjectiveAgencys(String receptionObjectiveAgencys) {
        ReceptionObjectiveAgencys = receptionObjectiveAgencys;
    }

    public String getReceptionObjectiveOrganizes() {
        return ReceptionObjectiveOrganizes;
    }

    public void setReceptionObjectiveOrganizes(String receptionObjectiveOrganizes) {
        ReceptionObjectiveOrganizes = receptionObjectiveOrganizes;
    }
}

package com.maxrocky.vesta.application.impl;


import com.maxrocky.vesta.application.DTO.UserLoginStatisticDTO;
import com.maxrocky.vesta.application.inf.ClickTimesService;
import com.maxrocky.vesta.application.DTO.ClickTimesDTO;
import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmei on 2016/2/15.
 */
@Service
public class ClickTimesServiceImpl implements ClickTimesService {

    @Autowired
    private ClickTimesRepository clickTimesRepository;
    @Autowired
    private PropertyCompanyRepository propertyCompanyRepository;
    @Autowired
    private UserSettingRepository userSettingRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private InvoicesTotalRepository invoicesTotalRepository;
    @Autowired
    private UserTotalRepository userTotalRepository;
    @Autowired
    private MenuTotalRepository menuTotalRepository;
    @Autowired
    private ClickUserRepository clickUserRepository;

    /**
     * 查询各模块点击量
     * @param clickTimesSeachDTO
     * @param webPage
     * @return
     */
   @Override
    public List<ClickTimesDTO> ClickManage(ClickTimesSeachDTO clickTimesSeachDTO,  WebPage webPage) {
       List<ClickTimesDTO> clickTimesDTOList = new ArrayList<>();//DTO集合
        ClickTimesEntity clickTimesEntity=new ClickTimesEntity();
       // 初始化 登陆人所负责范围
        clickTimesEntity.setProjectId(clickTimesSeachDTO.getQueryScope());
        clickTimesEntity.setCompanyId(clickTimesSeachDTO.getCompanyName());
        clickTimesEntity.setRegionId(clickTimesSeachDTO.getPropertyArea());
        List<ClickTimesEntity> clickTimesList= clickTimesRepository.ClickManage(clickTimesEntity, webPage);
        for(ClickTimesEntity clickTimes : clickTimesList){
            ClickTimesDTO clickTimesDTO= new ClickTimesDTO();
            clickTimesDTO.setProject(clickTimes.getProject());
            clickTimesDTO.setProjectId(clickTimes.getProjectId());
                clickTimesDTO.setTotal(this.getClickManageNums(clickTimes));
                clickTimesDTO.setCircle(clickTimes.getCircle());
                clickTimesDTO.setService(clickTimes.getService());
                clickTimesDTO.setPeriphery(clickTimes.getPeriphery());
                clickTimesDTO.setHome(clickTimes.getHome());
            clickTimesDTOList.add(clickTimesDTO);
        }
        return clickTimesDTOList;
    }

    /**
     * 首页点击量合计
     * @param clickTimesEntity
     * @return
     */
    @Override
    public int getClickManageNums (ClickTimesEntity clickTimesEntity){
        ClickTimesPageEntity clickTimes= clickTimesRepository.getClickNums(clickTimesEntity);
        int totalNums=0;
        if(null!=clickTimes){
             totalNums=clickTimes.getProperty()+clickTimes.getConsultation()+clickTimes.getPraise()+clickTimes.getComplaint()+clickTimes.getRepair()+clickTimes.getEvaluate();
        }

     return totalNums;
    }


    /**
     * 查询首页各模块点击量
     * @param clickTimesSeachDTO
     * @param webPage
     * @return
     */
    @Override
    public List<ClickTimesDTO> ClickPageManage(ClickTimesSeachDTO clickTimesSeachDTO, WebPage webPage) {
        List<ClickTimesDTO> clickTimesDTOList = new ArrayList<>();//DTO集合
        ClickTimesEntity clickTimesEntity=new ClickTimesEntity();
        clickTimesEntity.setCompanyId(clickTimesSeachDTO.getCompanyName());
        clickTimesEntity.setProjectId(clickTimesSeachDTO.getPropertyProject());
        clickTimesEntity.setRegionId(clickTimesSeachDTO.getPropertyArea());
        List<ClickTimesPageEntity> clickTimesPageList= clickTimesRepository.ClickPageManage(clickTimesEntity,webPage);
        for(ClickTimesPageEntity clickTimesPage : clickTimesPageList){
            ClickTimesDTO clickTimesDTO= new ClickTimesDTO();
            clickTimesDTO.setProject(clickTimesPage.getProject());
            clickTimesDTO.setProjectId(clickTimesPage.getProjectId());
            clickTimesDTO.setTotal(this.getClickManageNums(clickTimesEntity));
            clickTimesDTO.setProperty(clickTimesPage.getProperty());
            clickTimesDTO.setConsultation(clickTimesPage.getConsultation());
            clickTimesDTO.setPraise(clickTimesPage.getPraise());
            clickTimesDTO.setComplaint(clickTimesPage.getComplaint());
            clickTimesDTO.setRepair(clickTimesPage.getRepair());
            clickTimesDTO.setEvaluate(clickTimesPage.getEvaluate());
            clickTimesDTOList.add(clickTimesDTO);
        }
        return clickTimesDTOList;
    }

    @Override
    public void AddClickManage(String projectId,String companyId,String regionId,String type) {
        //String projectId, String companyId, String regionId,String type
        //UserInfoEntity currentLoginUser = userLoginBookService.getUserInfoByToken(vestaToken);
       // UserSettingEntity userSettingEntity = userSettingRepository.get(currentLoginUser.getUserId());
        //String projectId=userSettingEntity.getProjectId();
        ClickTimesEntity clickTimesEntity=clickTimesRepository.getClickTimes(projectId, companyId, regionId);
        ClickTimesPageEntity clickTimesPageEntity=clickTimesRepository.getClickTimesPage(projectId, companyId, regionId);
        if(type.equals(ClickTimesEntity.CIRCLE)||type.equals(ClickTimesEntity.SERVICE)||type.equals(ClickTimesEntity.PERIPHERY)||type.equals(ClickTimesEntity.HOME)) {
            if (null == clickTimesEntity) {
                ClickTimesEntity clickTimes = new ClickTimesEntity();
                clickTimes.setProjectId(projectId);
                clickTimes.setRegionId(regionId);
                clickTimes.setCompanyId(companyId);
                clickTimes.setId(IdGen.uuid());
                if(projectId!=null&&!"".equals(projectId)){
                    List<HouseProjectEntity>  projectList=  propertyCompanyRepository.queryHouseProjectEntity(projectId);
                    for(HouseProjectEntity project: projectList){
                        clickTimes.setProject(project.getName());
                    }
                }
                switch (type) {
                    case ClickTimesEntity.CIRCLE:
                            clickTimes.setCircle(1);
                            clickTimesRepository.AddClickTimes(clickTimes);
                        break;
                    case ClickTimesEntity.SERVICE:
                            clickTimes.setService(1);
                            clickTimesRepository.AddClickTimes(clickTimes);
                        break;
                    case ClickTimesEntity.PERIPHERY:
                            clickTimes.setPeriphery(1);
                            clickTimesRepository.AddClickTimes(clickTimes);
                        break;
                    case ClickTimesEntity.HOME:
                            clickTimes.setHome(1);
                            clickTimesRepository.AddClickTimes(clickTimes);
                        break;
                }
            } else {

                switch (type) {
                    case ClickTimesEntity.CIRCLE:
                        if (clickTimesEntity.getCircle() == 0) {
                            clickTimesEntity.setCircle(1);
                            clickTimesRepository.updateClickTimes(clickTimesEntity);
                        } else {
                            clickTimesEntity.setCircle(clickTimesEntity.getCircle() + 1);
                            clickTimesRepository.updateClickTimes(clickTimesEntity);
                        }
                        break;
                    case ClickTimesEntity.SERVICE:
                        if (clickTimesEntity.getService() == 0) {
                            clickTimesEntity.setService(1);
                            clickTimesRepository.updateClickTimes(clickTimesEntity);
                        } else {
                            clickTimesEntity.setService(clickTimesEntity.getService() + 1);
                            clickTimesRepository.updateClickTimes(clickTimesEntity);
                        }
                        break;
                    case ClickTimesEntity.PERIPHERY:
                        if (clickTimesEntity.getPeriphery() == 0) {
                            clickTimesEntity.setPeriphery(1);
                            clickTimesRepository.updateClickTimes(clickTimesEntity);
                        } else {
                            clickTimesEntity.setPeriphery(clickTimesEntity.getPeriphery() + 1);
                            clickTimesRepository.updateClickTimes(clickTimesEntity);
                        }
                        break;
                    case ClickTimesEntity.HOME:
                        if (clickTimesEntity.getHome() == 0) {
                            clickTimesEntity.setHome(1);
                            clickTimesRepository.updateClickTimes(clickTimesEntity);
                        } else {
                            clickTimesEntity.setHome(clickTimesEntity.getHome() + 1);
                            clickTimesRepository.updateClickTimes(clickTimesEntity);
                        }
                        break;
                }

            }
        }

        if(type.equals(ClickTimesPageEntity.PROPERTY)||type.equals(ClickTimesPageEntity.CONSULTATION)||type.equals(ClickTimesPageEntity.PRAISE)||type.equals(ClickTimesPageEntity.COMPLAINT)||type.equals(ClickTimesPageEntity.REPAIR)||type.equals(ClickTimesPageEntity.EVALUATE)){
            if(null==clickTimesPageEntity){
                ClickTimesPageEntity clickTimesPage=new ClickTimesPageEntity();
                clickTimesPage.setProjectId(projectId);
                clickTimesPage.setRegionId(regionId);
                clickTimesPage.setCompanyId(companyId);
                clickTimesPage.setId(IdGen.uuid());
                if(projectId!=null&&!"".equals(projectId)){
                    List<HouseProjectEntity>  projectList=  propertyCompanyRepository.queryHouseProjectEntity(projectId);
                    for(HouseProjectEntity project: projectList){
                        clickTimesPage.setProject(project.getName());
                    }
                }
                switch (type){
                    case ClickTimesPageEntity.PROPERTY:
                            clickTimesPage.setProperty(1);
                            clickTimesRepository.AddClickTimesPage(clickTimesPage);
                        break;
                    case ClickTimesPageEntity.CONSULTATION:
                            clickTimesPage.setConsultation(1);
                            clickTimesRepository.AddClickTimesPage(clickTimesPage);
                        break;
                    case ClickTimesPageEntity.PRAISE:
                            clickTimesPage.setPraise(1);
                            clickTimesRepository.AddClickTimesPage(clickTimesPage);
                        break;
                    case ClickTimesPageEntity.COMPLAINT:
                            clickTimesPage.setComplaint(1);
                            clickTimesRepository.AddClickTimesPage(clickTimesPage);
                        break;
                    case ClickTimesPageEntity.REPAIR:
                            clickTimesPage.setRepair(1);
                            clickTimesRepository.AddClickTimesPage(clickTimesPage);
                        break;
                    case ClickTimesPageEntity.EVALUATE:
                            clickTimesPage.setEvaluate(1);
                            clickTimesRepository.AddClickTimesPage(clickTimesPage);
                        break;
                }
            }else{
            switch (type){
                case ClickTimesPageEntity.PROPERTY:
                    if(clickTimesPageEntity.getProperty()==0){
                        clickTimesPageEntity.setProperty(1);
                        clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }else{
                        clickTimesPageEntity.setProperty(clickTimesPageEntity.getProperty()+1);
                        clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }
                    break;
                case ClickTimesPageEntity.CONSULTATION:
                    if(clickTimesPageEntity.getConsultation()==0){
                        clickTimesPageEntity.setConsultation(1);
                        clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }else{
                        clickTimesPageEntity.setConsultation(clickTimesPageEntity.getConsultation() + 1);
                        clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }
                    break;
                case ClickTimesPageEntity.PRAISE:
                    if(clickTimesPageEntity.getPraise()==0){
                        clickTimesPageEntity.setPraise(1);
                        clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }else{
                        clickTimesPageEntity.setPraise(clickTimesPageEntity.getPraise() + 1);
                         clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }
                    break;
                case ClickTimesPageEntity.COMPLAINT:
                    if(clickTimesPageEntity.getComplaint()==0){
                        clickTimesPageEntity.setComplaint(1);
                        clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }else{
                        clickTimesPageEntity.setComplaint(clickTimesPageEntity.getComplaint() + 1);
                        clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }
                    break;
                case ClickTimesPageEntity.REPAIR:
                    if(clickTimesPageEntity.getRepair()==0){
                        clickTimesPageEntity.setRepair(1);
                        clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }else{
                        clickTimesPageEntity.setRepair(clickTimesPageEntity.getRepair() + 1);
                        clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }
                    break;
                case ClickTimesPageEntity.EVALUATE:
                    if(clickTimesPageEntity.getEvaluate()==0){
                        clickTimesPageEntity.setEvaluate(1);
                        clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }else{
                        clickTimesPageEntity.setEvaluate(clickTimesPageEntity.getEvaluate() + 1);
                        clickTimesRepository.updateClickTimesPage(clickTimesPageEntity);
                    }
                    break;
            }
            }
        }

    }


    /**
     * 用户统计
     * param:userTotalDTO
     * param:page
     * return
     */
    @Override
    public List<UserLoginStatisticDTO> getUserTotal(UserLoginStatisticDTO userTotalDTO, WebPage page) {
        //检索条件
        UserToTalEntity userInfo=new UserToTalEntity();
        if(userTotalDTO!=null) {
            if (!StringUtil.isEmpty(userTotalDTO.getStartTime())) {
                userInfo.setStartTime(userTotalDTO.getStartTime());//开始时间
            }
            if (!StringUtil.isEmpty(userTotalDTO.getEndTime())) {
                userInfo.setEndTime(userTotalDTO.getEndTime());//结束时间
            }
        }
        //获取用户信息
        List<UserToTalEntity> userList=userTotalRepository.getTotalList(userInfo, page);
        //页面内容：封装到dto里
        List<UserLoginStatisticDTO> usersDTO=new ArrayList<UserLoginStatisticDTO>();
        if(userList.size()>0){
            for(UserToTalEntity users:userList){
                UserLoginStatisticDTO userDto=new UserLoginStatisticDTO();
                userDto.setStartTime(DateUtils.format(users.getCreateDate(), DateUtils.FORMAT_SHORT));//注册时间
                userDto.setCommonUser(users.getCommonUser().toString());//普通用户人数
                userDto.setOwner(users.getOwnerUser().toString());//业主人数
                userDto.setAndroid(users.getAndroid().toString());//安卓用户人数
                userDto.setIos(users.getIos().toString());//ios用户人数
                userDto.setWeChat(users.getWeChat().toString());//微信用户人数
                //男性用户人数
                Integer male=userInfoRepository.getForMale(userDto.getStartTime());
                userDto.setMale(male.toString());
                //女性用户人数
                Integer female=userInfoRepository.getForFeMale(userDto.getStartTime());
                userDto.setFemale(female.toString());
                //用户合计
                Integer total=users.getCommonUser()+users.getOwnerUser();
                userDto.setTotal(total.toString());
                usersDTO.add(userDto);
            }
        }
        return usersDTO;
    }

    /**
     * 单据统计
     * param:userTotalDTO
     * param:page
     * return
     */
    @Override
    public List<UserLoginStatisticDTO> getInvoicesTotal(UserLoginStatisticDTO invoicesTotalDTO, WebPage page) {
        //检索条件
        InvoicesTotalEntity invoicesEntity=new InvoicesTotalEntity();
        if(invoicesTotalDTO!=null) {
            /*if(!StringUtil.isEmpty(invoicesTotalDTO.getCity())){
                invoicesEntity.setCity(invoicesTotalDTO.getCity());//城市
            }
            if (!StringUtil.isEmpty(invoicesTotalDTO.getProjectName())) {
                invoicesEntity.setProject(invoicesTotalDTO.getProjectName());//项目
            }*/
            if (!StringUtil.isEmpty(invoicesTotalDTO.getStartTime())) {
                invoicesEntity.setStartTime(invoicesTotalDTO.getStartTime());//开始时间
            }
            if (!StringUtil.isEmpty(invoicesTotalDTO.getEndTime())) {
                invoicesEntity.setEndTime(invoicesTotalDTO.getEndTime());//结束时间
            }
        }
        //获取统计列表
        List<InvoicesTotalEntity> totalList=invoicesTotalRepository.getTotalList(invoicesEntity, page);
        //页面内容：封装到dto里
        List<UserLoginStatisticDTO> totalDTOs=new ArrayList<UserLoginStatisticDTO>();
        if(totalList.size()>0) {
            for (InvoicesTotalEntity total : totalList) {
                UserLoginStatisticDTO totalDTO = new UserLoginStatisticDTO();
                totalDTO.setStartTime(DateUtils.format(total.getCreateDate(), DateUtils.FORMAT_SHORT));//时间
                totalDTO.setRepairNum(total.getRepairNum().toString());//报修统计
                //如果项目参数不为0，则显示项目反馈统计;为空则显示所有反馈统计
                if (!"0".equals(invoicesEntity.getProject()) && !StringUtil.isEmpty(invoicesEntity.getProject())) {
                    totalDTO.setFeedbackNum(total.getFeedbackTotal().toString());//反馈统计(含项目)
                } else {
                    Integer feedNum = total.getFeedbackNum() + total.getFeedbackTotal();
                    totalDTO.setFeedbackNum(feedNum.toString());//全部反馈统计
                }
                totalDTO.setVisitorNum("0");//访客统计(此功能还没有)
                totalDTO.setPaymentNum("0");//缴费统计(此功能还没有)
                Integer totalNum = total.getRepairNum() + total.getFeedbackNum()+total.getFeedbackTotal();
                totalDTO.setTotal(totalNum.toString());//合计
                totalDTOs.add(totalDTO);
            }
        }
        return totalDTOs;
    }

    /**
     * 菜单统计
     * param:userTotalDTO
     * param:page
     * return
     */
    @Override
    public List<UserLoginStatisticDTO> getMenuTotal(UserLoginStatisticDTO menuTotalDTO, WebPage page) {
        //检索条件
        ClickUsersEntity clickInfo=new ClickUsersEntity();
        if(menuTotalDTO!=null) {
            if (!StringUtil.isEmpty(menuTotalDTO.getStartTime())) {
                clickInfo.setStartTime(menuTotalDTO.getStartTime());//开始时间
            }
            if (!StringUtil.isEmpty(menuTotalDTO.getEndTime())) {
                clickInfo.setEndTime(menuTotalDTO.getEndTime());//结束时间
            }
        }
        //获取用户信息
        List<MenuTotalEntity> menuList=menuTotalRepository.getTotalList();
        //页面内容：封装到dto里
        List<UserLoginStatisticDTO> menusDTO=new ArrayList<UserLoginStatisticDTO>();
        if(menuList.size()>0){
            for (MenuTotalEntity menus : menuList) {
                UserLoginStatisticDTO menuDto = new UserLoginStatisticDTO();
                menuDto.setName(menus.getModuleName());//模块名称
                //时间不为空,则查出搜索条件下的统计信息；为空则查出所有的统计信息
                ClickUsersEntity clickUser = clickUserRepository.getTotalList(clickInfo, menus.getId());
                if(clickUser!=null) {
                    Long clickCount = clickUserRepository.getClickTotal(clickInfo, menus.getId());
                    Integer clicks=Integer.valueOf(clickCount.toString());
                    menuDto.setClicks(clickCount.toString());//点击次数
                    Integer userCount = clickUserRepository.getTotalNum(clickInfo,menus.getId());
                    menuDto.setClickUserNum(userCount.toString());//业主人数
                    Integer total=0;
                    if(userCount != 0) {
                        total = clicks/userCount;
                    }
                    menuDto.setTotal(total.toString());//人均点击次数
                    menusDTO.add(menuDto);
                }else{
                    menuDto.setClicks("0");//点击次数
                    menuDto.setClickUserNum("0");//业主人数
                    menuDto.setTotal("0");//人均点击次数
                    menusDTO.add(menuDto);
                }
            }
        }
        return menusDTO;
    }

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    @Override
    public String exportExcel(UserPropertyStaffEntity user,UserLoginStatisticDTO userLoginlDTO,
                              HttpServletResponse httpServletResponse,String type,HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        if ("1".equals(type)) {
            List<UserLoginStatisticDTO> userTotalDTO = getUserTotal(userLoginlDTO, webPage);

            XSSFSheet sheet = workBook.createSheet("用户统计");

            ExportUtil exportUtil = new ExportUtil(workBook, sheet);
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();

            // 百分比
            XSSFDataFormat fmt = workBook.createDataFormat();
            XSSFDataFormat fmt1 = workBook.createDataFormat();

            // 四个边框加粗
            XSSFCellStyle style1 = workBook.createCellStyle();
            style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
            XSSFFont font = workBook.createFont();
            // 设置字体加粗
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            style1.setFont(font);

            String[] titles = {"序号", "时间", "普通用户", "业主用户", "安卓用户", "IOS用户", "微信用户", "合计"};
            XSSFRow headRow = sheet.createRow(0);


            if (userTotalDTO.size() > 0) {

                userTotalDTO.forEach(userDTO -> {

                    XSSFCell cell=null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    if (userTotalDTO.size() > 0) {
                        for (int i = 0; i < userTotalDTO.size(); i++) {
                            XSSFRow bodyRow = sheet.createRow(i + 1);
                            UserLoginStatisticDTO userDto = userTotalDTO.get(i);
                            cell = bodyRow.createCell(0);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(i + 1);//序号

                            cell = bodyRow.createCell(1);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getStartTime());//时间

                            cell = bodyRow.createCell(2);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getCommonUser());//普通用户

                            cell = bodyRow.createCell(3);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getOwner());//业主用户

                            cell = bodyRow.createCell(4);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getAndroid());//安卓用户

                            cell = bodyRow.createCell(5);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getIos());//IOS用户

                            cell = bodyRow.createCell(6);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getWeChat());//微信用户

                            cell = bodyRow.createCell(7);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getTotal());//合计


                        }
                    }
                });
            }
            try {
                //String fileName = new String(("用户分析列表").getBytes(), "ISO8859-1");
                String fileName = new String(("用户分析列表").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("用户分析列表", "UTF8");
                }
                httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if("2".equals(type)){
            List<UserLoginStatisticDTO> invoicesDTO = getInvoicesTotal(userLoginlDTO, webPage);

            XSSFSheet sheet = workBook.createSheet("单据统计");
            // 百分比
            XSSFDataFormat fmt = workBook.createDataFormat();
            XSSFDataFormat fmt1 = workBook.createDataFormat();
            ExportUtil exportUtil = new ExportUtil(workBook, sheet);
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
            // 四个边框加粗
            XSSFCellStyle style1 = workBook.createCellStyle();
            style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
            XSSFFont font = workBook.createFont();
            // 设置字体加粗
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            style1.setFont(font);
            String[] titles = {"序号", "时间", "报修单", "意见反馈单", "访客邀请单", "物业缴费单", "合计"};
            XSSFRow headRow = sheet.createRow(0);

            if (invoicesDTO.size() > 0) {

                invoicesDTO.forEach(userDTO -> {
                    XSSFCell cell = null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    if (invoicesDTO.size() > 0) {
                        for (int i = 0; i < invoicesDTO.size(); i++) {
                            XSSFRow bodyRow = sheet.createRow(i + 1);
                            UserLoginStatisticDTO invoicesDto = invoicesDTO.get(i);
                            cell = bodyRow.createCell(0);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(i + 1);//序号

                            cell = bodyRow.createCell(1);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(invoicesDto.getStartTime());//时间

                            cell = bodyRow.createCell(2);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(invoicesDto.getRepairNum());//报修单

                            cell = bodyRow.createCell(3);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(invoicesDto.getFeedbackNum());//意见反馈单

                            cell = bodyRow.createCell(4);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(invoicesDto.getVisitorNum());//访客邀请单

                            cell = bodyRow.createCell(5);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(invoicesDto.getPaymentNum());//物业缴费单

                            cell = bodyRow.createCell(6);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(invoicesDto.getTotal());//合计


                        }
                    }
                });
            }
            try {
                String fileName = new String(("客户单据统计列表").getBytes(), "ISO8859-1");
                httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if("3".equals(type)){
            List<UserLoginStatisticDTO> menuDTO=getMenuTotal(userLoginlDTO,webPage);
            XSSFSheet sheet = workBook.createSheet("菜单统计");
            // 百分比
            XSSFDataFormat fmt = workBook.createDataFormat();
            XSSFDataFormat fmt1 = workBook.createDataFormat();

            ExportUtil exportUtil = new ExportUtil(workBook, sheet);
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
            // 四个边框加粗
            XSSFCellStyle style1 = workBook.createCellStyle();
            style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
            XSSFFont font = workBook.createFont();
            // 设置字体加粗
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            style1.setFont(font);
            String[] titles = {"模块名称", "点击次数", "点击人数", "人均点击次数"};
            XSSFRow headRow = sheet.createRow(0);

            if(menuDTO.size()>0){
                menuDTO.forEach(userDTO -> {
                    XSSFCell cell = null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    if (menuDTO.size() > 0) {
                        for (int i = 0; i < menuDTO.size(); i++) {
                            XSSFRow bodyRow = sheet.createRow(i + 1);
                            UserLoginStatisticDTO menuDto = menuDTO.get(i);

                            cell = bodyRow.createCell(0);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(menuDto.getName());//模块名称

                            cell = bodyRow.createCell(1);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(menuDto.getClicks());//点击次数

                            cell = bodyRow.createCell(2);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(menuDto.getClickUserNum());//点击人数

                            cell = bodyRow.createCell(3);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(menuDto.getTotal());//人均点击次数
                        }
                    }
                });
            }
            try {
                String fileName = new String(("菜单统计列表").getBytes(), "ISO8859-1");
                httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}

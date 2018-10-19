package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.VoteDTO;
import com.maxrocky.vesta.application.inf.VoteService;
import com.maxrocky.vesta.application.service.BaseServiceImpl;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.SystemLogRepository;
import com.maxrocky.vesta.domain.repository.VoteRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 投票管理Service实现类
 * @author Wyd_2016/05/26
 */
@Service
public class VoteServiceImpl extends BaseServiceImpl<VoteEntity> implements VoteService {

    @Autowired
    VoteRepository voteRepository;
    @Autowired
    SystemLogRepository systemLogRepository;

    /**
     * 通过投票模板Id_删除投票信息(投票模板、投票项目)
     */
    public void deleteVoteById(String userId, String voteId){
        //逻辑删除
        VoteEntity voteEntity = this.voteRepository.get(VoteEntity.class, voteId);
        voteEntity.setStatus(1);
        voteEntity.setOperatePerson(userId);
        voteEntity.setOperateDate(new Date());
        this.voteRepository.saveOrUpdate(voteEntity);
        //修改级联该投票模板的公告(是否投票状态)状态
        List<String> list = this.voteRepository.queryAnnouncementIdByVoteId(voteId);
        if (list.size() > 0){
            for (int i = 0; i < list.size(); i++){
                AnnouncementEntity announcementEntity = this.voteRepository.get(AnnouncementEntity.class, list.get(i));
                announcementEntity.setIsVote(0);
                announcementEntity.setOperatPerson(userId);
                announcementEntity.setOperatDate(new Date());
                this.voteRepository.saveOrUpdate(announcementEntity);
            }
        }
    }

    @Override
    public void deleteVoteById(UserPropertyStaffEntity user, String userId, String voteId) {
        //逻辑删除
        VoteEntity voteEntity = this.voteRepository.get(VoteEntity.class, voteId);
        voteEntity.setStatus(1);
        voteEntity.setOperatePerson(userId);
        voteEntity.setOperateDate(new Date());
        this.voteRepository.saveOrUpdate(voteEntity);
        //修改级联该投票模板的公告(是否投票状态)状态
        List<String> list = this.voteRepository.queryAnnouncementIdByVoteId(voteId);
        if (list.size() > 0){
            for (int i = 0; i < list.size(); i++){
                AnnouncementEntity announcementEntity = this.voteRepository.get(AnnouncementEntity.class, list.get(i));
                announcementEntity.setIsVote(0);
                announcementEntity.setOperatPerson(userId);
                announcementEntity.setOperatDate(new Date());
                this.voteRepository.saveOrUpdate(announcementEntity);
            }
        }
        InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
        infoReleaseLog.setLogId(IdGen.uuid());
        infoReleaseLog.setLogTime(new Date());//注册日期
        infoReleaseLog.setUserName(user.getStaffName());//用户昵称
        infoReleaseLog.setUserMobile(user.getMobile());//手机号
        infoReleaseLog.setThisSection("服务");//版块
        infoReleaseLog.setThisFunction("物业管理-投票信息管理");//功能
        infoReleaseLog.setThisType("删除");//操作类型
        infoReleaseLog.setLogContent(voteEntity.getTitle());//发布内容
        infoReleaseLog.setAsscommunity("");//关联社区;
        systemLogRepository.addInfoReleaseLog(infoReleaseLog);
    }

    /**
     * 根据通告id查询通告所有范围信息
     * User: yifan
     * Date: 2016/5/18
     * Time: 11:10
     * @return
     */
    public List<Object[]> queryAllVoteTitle() {
        return  this.voteRepository.queryAllVoteTitle();
    }

    /**
     * 条件检索投票列表
     * @param voteDTO
     * @param webPage
     * @return
     */
    public List<VoteDTO> queryVoteListByPage(VoteDTO voteDTO, WebPage webPage){
        /*
        //设置检索时间段
        Date staDate = null;
        Date endDate = null;
        if (!StringUtil.isEmpty(voteDTO.getQueryStaDate())){
            staDate = DateUtils.parse(voteDTO.getQueryStaDate(), DateUtils.FORMAT_SHORT);
        }
        if (!StringUtil.isEmpty(voteDTO.getQueryEndDate())){
            endDate = DateUtils.parse(voteDTO.getQueryEndDate(), DateUtils.FORMAT_SHORT);
        }*/
        String title="";
        Integer status=5;
        String startDate="";
        String endDate="";
        if(voteDTO!=null) {
            title=voteDTO.getTitle();
            status=voteDTO.getVoteStatus();
            startDate=voteDTO.getQueryStaDate();
            endDate=voteDTO.getQueryEndDate();
        }
        List<Map<String, Object>> list = this.voteRepository.queryVoteListByPage(title, status, startDate, endDate, webPage);
//      List<Map<String,Object>> list = this.voteRepository.queryVoteListByPage(voteDTO.getTitle(), voteDTO.getVoteStatus(), staDate, endDate, webPage);
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //封装结果集
        List<VoteDTO> voteDTOs = new ArrayList<>();
        for (Map<String, Object> map : list) {
                VoteDTO resVoteDTO = new VoteDTO();
                resVoteDTO.setId(map.get("id").toString());
                resVoteDTO.setTitle(map.get("title").toString());
                resVoteDTO.setCreateDate(DateUtils.parse(map.get("createDate").toString(), "yyyy-MM-dd HH:mm:ss"));
                resVoteDTO.setCreatePerson(map.get("createPerson").toString());
                Integer count = this.voteRepository.quereyVotePersonCount("", map.get("id").toString());
            //判断投票状态开始，投票id获取中间表对象
            List<AnnouncementVoteEntity> announVoteEntities=voteRepository.queryAnnouncementList(map.get("id").toString());
            //获取需要参数
            Date nowDate=new Date();//当前时间
            Date endDates= DateUtils.parse(map.get("endDate").toString(), "yyyy-MM-dd HH:mm");//投票结束时间
            //结束：当前的时间大于结束的时间2
//            if(announVoteEntities.size()>=0 && nowDate.getTime()>=endDates.getTime()){
//                resVoteDTO.setVoteStatus(2); //已结束
//            } else if(announVoteEntities.size()>0){   //进行中：中间表有，并且
////                if(createDate.getTime()<endDates.getTime()){
//                Date createDate= announVoteEntities.get(0).getCreateDate();//投票时间
//                if(nowDate.getTime()>=createDate.getTime()&&nowDate.getTime()<=endDates.getTime()){
//                  resVoteDTO.setVoteStatus(1); //进行中
//                }
//            }else if(announVoteEntities.size()<=0) {   //未开始：中间表无数据0
//                resVoteDTO.setVoteStatus(0); //未开始
//            }

            if(nowDate.getTime()>endDates.getTime()){
                resVoteDTO.setVoteStatus(2); //已结束
            }else if(announVoteEntities.size()==0){
                resVoteDTO.setVoteStatus(0); //没开始
            }else if(announVoteEntities.size()>0) {
                resVoteDTO.setVoteStatus(1); //进行中
            }
//            if
//            if(announVoteEntities.size()==0&&nowDate.getTime()>=endDates.getTime()) {
//                resVoteDTO.setVoteStatus(2); //已结束
//            }
            resVoteDTO.setVoteCount(count.longValue());
            resVoteDTO.setEndDate(DateUtils.parse(map.get("endDate").toString(), "yyyy-MM-dd HH:mm:ss"));
            voteDTOs.add(resVoteDTO);
        }
        return voteDTOs;
    }

    /**
     * 新增一条投票信息
     * @param voteDTO
     */
    public void addOrUpdateVote(VoteDTO voteDTO){
        Date nowDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        //--1.保存_投票详情(vote_detail)
        VoteEntity voteEntity = new VoteEntity();
        //主鍵
        String voteId = IdGen.uuid();
        voteEntity.setId(voteId);
        //截止时间
        voteEntity.setEndDate(voteDTO.getEndDate());
        //标题
        voteEntity.setTitle(voteDTO.getTitle());
        //投票类型
        voteEntity.setVoteType(voteDTO.getVoteType());
        //状态
        voteEntity.setStatus(0);
        //創建日期
        voteEntity.setCreateDate(nowDate);
        //创建人
        voteEntity.setCreatePerson(voteDTO.getOperatePerson());
        //操作日期
        voteEntity.setOperateDate(nowDate);
        //操作人
        voteEntity.setOperatePerson(voteDTO.getOperatePerson());

        voteRepository.saveOrUpdate(voteEntity);
        //--2.保存_投票项(vote_option)
        //投票项描述_数组
        String[] descriptions = voteDTO.getDescriptions();
        ArrayList<String> imgUrls = voteDTO.getImgUrls();
        for (int i = 0; i < descriptions.length; i++){
            VoteOptionEntity voteOptionEntity = new VoteOptionEntity();
            //主键
            voteOptionEntity.setId(IdGen.uuid());
            //投票详情ID
            voteOptionEntity.setVoteId(voteId);
            //投票图片
            voteOptionEntity.setUrl(imgUrls.get(i));
            //投票描述
            voteOptionEntity.setDescription(descriptions[i]);
            //状态
            voteOptionEntity.setStatus(0);
            //创建日期
            voteOptionEntity.setCreateDate(nowDate);
            //创建人
            voteOptionEntity.setCreatePerson(voteDTO.getOperatePerson());
            //操作日期
            voteOptionEntity.setOperateDate(nowDate);
            //操作人
            voteOptionEntity.setOperatePerson(voteDTO.getOperatePerson());
            //选项排序
            voteOptionEntity.setOptionOrder(i + 1);

            voteRepository.saveOrUpdate(voteOptionEntity);
        }

    }

    @Override
    public void addOrUpdateVote(UserPropertyStaffEntity user,VoteDTO voteDTO) {
        Date nowDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        //--1.保存_投票详情(vote_detail)
        VoteEntity voteEntity = new VoteEntity();
        //主鍵
        String voteId = IdGen.uuid();
        voteEntity.setId(voteId);
        //截止时间
        voteEntity.setEndDate(voteDTO.getEndDate());
        //标题
        voteEntity.setTitle(voteDTO.getTitle());
        //投票类型
        voteEntity.setVoteType(voteDTO.getVoteType());
        //状态
        voteEntity.setStatus(0);
        //創建日期
        voteEntity.setCreateDate(nowDate);
        //创建人
        voteEntity.setCreatePerson(voteDTO.getOperatePerson());
        //操作日期
        voteEntity.setOperateDate(nowDate);
        //操作人
        voteEntity.setOperatePerson(voteDTO.getOperatePerson());

        voteRepository.saveOrUpdate(voteEntity);
        //--2.保存_投票项(vote_option)
        //投票项描述_数组
        String[] descriptions = voteDTO.getDescriptions();
        ArrayList<String> imgUrls = voteDTO.getImgUrls();
        for (int i = 0; i < descriptions.length; i++){
            VoteOptionEntity voteOptionEntity = new VoteOptionEntity();
            //主键
            voteOptionEntity.setId(IdGen.uuid());
            //投票详情ID
            voteOptionEntity.setVoteId(voteId);
            //投票图片
            voteOptionEntity.setUrl(imgUrls.get(i));
            //投票描述
            voteOptionEntity.setDescription(descriptions[i]);
            //状态
            voteOptionEntity.setStatus(0);
            //创建日期
            voteOptionEntity.setCreateDate(nowDate);
            //创建人
            voteOptionEntity.setCreatePerson(voteDTO.getOperatePerson());
            //操作日期
            voteOptionEntity.setOperateDate(nowDate);
            //操作人
            voteOptionEntity.setOperatePerson(voteDTO.getOperatePerson());
            //选项排序
            voteOptionEntity.setOptionOrder(i + 1);

            voteRepository.saveOrUpdate(voteOptionEntity);

            InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(user.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(user.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("物业管理-投票信息管理");//功能
            infoReleaseLog.setThisType("新增");//操作类型
            infoReleaseLog.setLogContent(voteEntity.getTitle());//发布内容
           /* HouseProjectEntity houseProjectEntity=houseProjectRepository.getProjectByCode(projectCode);
            if(houseProjectEntity!=null){
                infoReleaseLog.setAsscommunity(houseProjectEntity.getName());//关联社区;
            }else{

            }*/
            infoReleaseLog.setAsscommunity("");//关联社区;
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);

        }
    }

    /**
     * 投票动作
     * @param announcementVoteId    _公告-投票数据Id
     */
    public void voteAction(String userId, String announcementVoteId){
        //执行投票
        AnnouncementVoteEntity announcementVoteEntity = this.voteRepository.get(AnnouncementVoteEntity.class, announcementVoteId);
        synchronized (announcementVoteEntity) {
            announcementVoteEntity.setVoteNumber(announcementVoteEntity.getVoteNumber() + 1);
            this.voteRepository.saveOrUpdate(announcementVoteEntity);
        }
        //记录投票日志
        VoteRecordEntity voteRecordEntity = new VoteRecordEntity();
        voteRecordEntity.setId(IdGen.uuid());   //主键
        voteRecordEntity.setUserId(userId);     //投票用户Id
        voteRecordEntity.setProjectId("");  //项目Id
        voteRecordEntity.setProjectName("");    //项目名称
        voteRecordEntity.setAnnouncementId(announcementVoteEntity.getAnnouncementId()); //公告Id
        voteRecordEntity.setVoteId(announcementVoteEntity.getVoteId()); //投票Id
        voteRecordEntity.setVoteOptionId(announcementVoteId);   //投票项Id
        voteRecordEntity.setCreatePerson(userId);   //创建人
        voteRecordEntity.setCreateDate(new Date()); //创建日期
        voteRecordEntity.setOperatePerson(userId);  //操作人
        voteRecordEntity.setOperateDate(new Date());    //操作日期
        this.voteRepository.saveOrUpdate(voteRecordEntity);
    }

    /**
     * 投票详情
     * @param announcementId    _公告Id
     * @return ApiResult
     */
    public ApiResult getVoteDetail(String userId, String announcementId,String projectCode){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object>  resultMap = new HashMap<>();
        // #1,通过_公告Id_获取_公告-投票数据列表
        List<Map<String, Object>> announcementVoteDataList = this.voteRepository.queryAnnouncementVoteDataList(announcementId, projectCode);
        resultMap.put("voteOptions", announcementVoteDataList);
        if (announcementVoteDataList.size() == 0){
            //未找到公告_投票记录
            return null;
        }
        String voteId = (String) announcementVoteDataList.get(0).get("voteId");
        VoteEntity voteEntity = this.voteRepository.get(VoteEntity.class, voteId);
        //封装投票详情
        resultMap.put("voteTitle", voteEntity.getTitle());
        resultMap.put("voteType", voteEntity.getVoteType());
        resultMap.put("endDate", dateFormat.format(voteEntity.getEndDate()));
        // #2,通过_公告Id和项目Code_获取参与投票总人数
        Integer count = this.voteRepository.quereyVotePersonCountByProject(announcementId, projectCode);
        //封装投票总人数
        resultMap.put("countPerson", count);
        // #3,通过_投票Id_获取_投票项信息列表
//        List<VoteOptionEntity> voteOptionEntities = this.voteRepository.queryVoteOptionList(voteId);
        //封装投票项信息
//        resultMap.put("voteOptions", voteOptionEntities);
        // #4,通过_用户Id/公告Id/投票Id_判断用户是否已经投票
        Integer cou = this.voteRepository.queryVoteRecordByUser(userId, announcementId, voteId);
        if (cou > 0){
            resultMap.put("isVote",1);
        }else {
            resultMap.put("isVote",0);
        }

        return new SuccessApiResult(resultMap);
    }

    /**
     * 投票结果
     * @param announcementId    _公告Id
     * @return ApiResult
     */
    public ApiResult getVoteResult(String announcementId,String projectCode){
        Map<String,Object>  resultMap = new HashMap<>();
        //#1,通过_公告Id_获取_公告-投票数据列表
        List<Map<String, Object>> announcementVoteDataList = this.voteRepository.queryAnnouncementVoteDataList(announcementId,projectCode);
        resultMap.put("announcementVoteList",announcementVoteDataList);
        //#2,通过_公告Id_获取_参与投票总人数
        Integer count = this.voteRepository.quereyVotePersonCountByProject(announcementId, projectCode);
        resultMap.put("count",count);
        //#3,通过_投票Id_获取_投票详情
        String voteId = (String) announcementVoteDataList.get(0).get("voteId");
        VoteEntity voteEntity = this.voteRepository.get(VoteEntity.class, voteId);
        resultMap.put("voteTitle", voteEntity.getTitle());
        return new SuccessApiResult(resultMap);
    }

    /**
     * 通过_公告Id或投票Id_查询参与投票总人数
     * @param announcementId
     * @param voteId
     * @return Integer
     */
    public Integer quereyVotePersonCount(String announcementId,String voteId){
        return this.voteRepository.quereyVotePersonCount(announcementId,voteId);
    }

    /**
     * 通过_投票Id_获取投票模板信息
     * @param voteId
     * @return VoteEntity
     */
    public VoteEntity queryVoteById(String voteId){
        return this.voteRepository.get(VoteEntity.class, voteId);
    }

    /**
     * 通过_投票Id_检索投票项统计信息列表
     * @param voteId
     * @return
     */
    public List<Map<String,Object>> queryVoteOptionStatistic(String voteId){
        return this.voteRepository.queryVoteOptionStatistic(voteId);
    }

    /**
     * 通过_投票Id_按公告项目进行投票统计
     */
    public List<Map<String,Object>> queryVoteProjectStatistic(String voteId){
        List<Map<String,Object>> list = new ArrayList<>();
        //通过 voteId 获取公告项目列表
        List<Map<String, Object>> anoProList = this.voteRepository.queryAnnProByVote(voteId);
        for (int i = 0; i < anoProList.size(); i++){
            Map<String, Object> map = anoProList.get(i);
            List<AnnouncementVoteEntity> announcementVoteEntities = this.voteRepository.queryVoteOptionNumByAnoPro(map.get("announcementId").toString(), map.get("projectId").toString());
            List optionList = new ArrayList<>();
            for (int j = 0; j < announcementVoteEntities.size(); j++){
//                optionMap.put("option_"+(j+1),announcementVoteEntities.get(j).getVoteNumber());
                optionList.add(announcementVoteEntities.get(j).getVoteNumber());
            }
            map.put("optionList",optionList);
            list.add(map);
        }
        return list;
    }

    /**
     * 通过_投票Id_检索投票项
     */
    public List<Map<String,Object>> queryVoteOptionByVoteId(String voteId){
        return this.voteRepository.queryVoteOptionByVoteId(voteId);
    }

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    @Override
    public String exportExcel(UserPropertyStaffEntity user,VoteDTO votesDTO,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        List<VoteDTO> voteDTO = queryVoteListByPage(votesDTO, webPage);

        XSSFSheet sheet = workBook.createSheet("投票信息列表");

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

        String[] titles = {"序号", "投票标题", "发布人", "创建时间", "投票人数"};
        XSSFRow headRow = sheet.createRow(0);


        if (voteDTO.size() > 0) {

            voteDTO.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (voteDTO.size() > 0) {
                    for (int i = 0; i < voteDTO.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        VoteDTO voteDto = voteDTO.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(voteDto.getTitle());//投票标题

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(voteDto.getCreatePerson());//发布人

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(voteDto.getCreateDate());//创建时间

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(voteDto.getVoteCount());//投票人数
                    }
                }
            });
        }
        try {
            //String fileName = new String(("投票信息管理").getBytes(), "ISO8859_1");
            String fileName = new String(("投票信息管理").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("投票信息管理", "UTF8");
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
        return null;
    }
}



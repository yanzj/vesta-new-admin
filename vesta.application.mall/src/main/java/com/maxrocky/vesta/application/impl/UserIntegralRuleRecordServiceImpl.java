package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.IntegralntegralDTO;
import com.maxrocky.vesta.application.AdminDTO.UserIntegralRuleRecordDTO;
import com.maxrocky.vesta.application.AdminDTO.UserIntegralRuleRecordEntityDTO;
import com.maxrocky.vesta.application.AdminDTO.UserIntegralRuleRecordQuerry;
import com.maxrocky.vesta.application.inf.UserIntegralRuleRecordService;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserIntegralRuleEntity;
import com.maxrocky.vesta.domain.model.UserIntegralRuleRecordEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.UserInfoRepository;
import com.maxrocky.vesta.domain.repository.UserIntegralRuleRecordRepository;
import com.maxrocky.vesta.domain.repository.UserIntegralRuleRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/15.
 */
@Service
public class UserIntegralRuleRecordServiceImpl implements UserIntegralRuleRecordService {

    @Autowired
    UserIntegralRuleRecordRepository userIntegralRuleRecordRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserIntegralRuleRepository userIntegralRuleRepository;

    @Override
    public List<UserIntegralRuleRecordDTO> getUserIntegralRuleRecordList(UserIntegralRuleRecordQuerry q, WebPage webPage) {
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("realName",q.getRealName());
        paramsMap.put("mobile",q.getMobile());
//        paramsMap.put("name",q.getName());
        paramsMap.put("integralNumber",q.getIntegralNumber());
        paramsMap.put("weChatAppId",q.getWeChatAppId());
        paramsMap.put("cardNum",q.getCardNum());
        List<Object> list = userIntegralRuleRecordRepository.getAll(paramsMap,webPage);
        List<UserIntegralRuleRecordDTO> userIntegralRuleRecordDTOArrayList = new ArrayList<>();
        for (Object l : list){
            UserIntegralRuleRecordDTO idto = new UserIntegralRuleRecordDTO();
            Object[] obj = (Object[]) l;
//            idto.setUserIntegralRuleRecordId((String) obj[0] == null ? "" : (String) obj[0]);
//            idto.setRealName((String)obj[1] == null ? "" : (String) obj[1]);
//            idto.setMobile((String) obj[2] == null ? "" : (String) obj[2]);
//            idto.setNickName((String) obj[3] == null ? "" : (String) obj[3]);
//            idto.setName((String) obj[4] == null ? "" : (String) obj[4]);
//            idto.setUserAddress((String)obj[5] == null ? "" : (String) obj[5]);
//            idto.setIntegralNumber((String) obj[6] == null ? "" : (String) obj[6]);
//            idto.setClientName((String) obj[7] == null ? "" : (String) obj[7]);
//            idto.setUserId((String) obj[8] == null ? "" : (String) obj[8]);
//            idto.setHouseProjectId((String) obj[9] == null ? "" : (String) obj[9]);
//            idto.setCardNum((String) obj[10] == null ? "" : (String) obj[10]);
            idto.setRealName((String)obj[0] == null ? "" : (String) obj[0]);
            idto.setMobile((String) obj[1] == null ? "" : (String) obj[1]);
            idto.setIntegralNumber((String) obj[2] == null ? "" : (String) obj[2]);
            idto.setClientName((String) obj[3] == null ? "" : (String) obj[3]);
            idto.setUserId((String) obj[4] == null ? "" : (String) obj[4]);
            idto.setCardNum((String) obj[5] == null ? "" : (String) obj[5]);
            userIntegralRuleRecordDTOArrayList.add(idto);
        }
        return userIntegralRuleRecordDTOArrayList;
    }

    @Override
    public List<UserIntegralRuleRecordEntityDTO> getUserIntegralRuleRecordEntityList(UserIntegralRuleRecordQuerry q) {
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("userId",q.getUserId());
//        paramsMap.put("houseProjectId",q.getHouseProjectId());
        List<UserIntegralRuleRecordEntityDTO> dto = new ArrayList<>();
        List<Object>  list = userIntegralRuleRecordRepository.getUserIntegralRuleById(paramsMap);

        for (Object i : list){
            UserIntegralRuleRecordEntityDTO d = new UserIntegralRuleRecordEntityDTO();
            Object[] obj = (Object[]) i;
            d.setCreateOn((Date) obj[0] == null ? null : (Date) obj[0]);
            d.setModelName((String)obj[1] == null ? "" : (String) obj[1]);
            d.setNumber((String)obj[2] == null ? "" : (String) obj[2]);
            dto.add(d);
        }
        return dto;
    }

    @Override
    public List<IntegralntegralDTO> getCityRule(UserIntegralRuleRecordQuerry q) {
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("cityId",q.getCityId());
        paramsMap.put("projectNum",q.getProjectNum());
        paramsMap.put("weChatAppId",q.getWeChatAppId());
        List<Object> list = userIntegralRuleRecordRepository.getRule(paramsMap);
        List<IntegralntegralDTO> dto = new ArrayList<>();

        for (Object i : list){
            Object[] obj = (Object[]) i;
            IntegralntegralDTO n = new IntegralntegralDTO();
            n.setModelType((String)obj[0] == null ? "" : (String) obj[0]);
            n.setModelName((String)obj[1] == null ? "" : (String) obj[1]);
            n.setNumber(obj[2]);
            dto.add(n);
        }
        return dto;
    }

    @Override
    public Map<String,Object>  uploadExcel(UserPropertyStaffEntity user, InputStream fis) {
        UserIntegralRuleRecordEntity r = null;
        Map<String,Object> resultMap = new HashMap<>();
        List list = new ArrayList();
        List listd = new ArrayList();
        try {
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;

            for(int i = 0; i < hwb.getNumberOfSheets(); i++) {
                sheet = hwb.getSheetAt(i);
                //遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for(int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    row = sheet.getRow(j);
                    r = new UserIntegralRuleRecordEntity();

                    //手机号检查用户是否存在
                    if (null != row.getCell(2) && !"".equals(row.getCell(2).toString())){
                       UserInfoEntity userInfoEntity = userInfoRepository.getByMobile(getCellValue(row.getCell(2)).trim());
                       if(userInfoEntity == null){
                           list.add(getCellValue(row.getCell(0)).trim());
                           continue;
                       }else if(userInfoEntity.getId() == null ){
                           listd.add(getCellValue(row.getCell(0)).trim());
                           continue;
                       }else{
                           //写入用户ID
                           r.setUserId(userInfoEntity.getUserId());
                       }
                    }
                    //检查项目
                    r.setHouseProjectId(getCellValue(row.getCell(3)).trim());
                    //地址
                    r.setUserAddress(getCellValue(row.getCell(4)).trim());
                    //模块
                    r.setModelType(getCellValue(row.getCell(6)).trim());
                    //公众号
                    r.setWeChatAppId(getCellValue(row.getCell(8)).trim());
                    //第三方名称
                    r.setRname(getCellValue(row.getCell(7)).trim());
                    //积分数量
                    r.setNumber(getCellValue(row.getCell(5)).trim());
                    r.setUserIntegralRuleRecordId(IdGen.uuid());
                    r.setCreateOn(new Date());
                    userIntegralRuleRecordRepository.AddIntegralRuleEntity(r);

                    UserIntegralRuleEntity userIntegralRuleEntity = userIntegralRuleRepository.get(r.getUserId(),getCellValue(row.getCell(8)).trim());
                    if(userIntegralRuleEntity == null){
                        userIntegralRuleEntity = new UserIntegralRuleEntity();
                        userIntegralRuleEntity.setId(IdGen.uuid());
                        userIntegralRuleEntity.setWeChatAppId(getCellValue(row.getCell(8)).trim());
                        userIntegralRuleEntity.setIntegralNumber(getCellValue(row.getCell(5)).trim() == null ? "0" : getCellValue(row.getCell(5)).trim());
                        userIntegralRuleEntity.setUserId(r.getUserId());
                        userIntegralRuleRepository.AddUserIntegral(userIntegralRuleEntity);
                    }else{
                      Integer n =  Integer.parseInt(userIntegralRuleEntity.getIntegralNumber()) + Integer.parseInt(getCellValue(row.getCell(5)).trim());
                      userIntegralRuleEntity.setIntegralNumber(String.valueOf(n));
                      userIntegralRuleRepository.UpdateUserIntegral(userIntegralRuleEntity);
                    }

                }
            }
            resultMap.put("list", list);
            resultMap.put("listd", listd);
            resultMap.put("error", "0");
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "-1");
            return resultMap;
        }
    }




    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(XSSFCell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }
}

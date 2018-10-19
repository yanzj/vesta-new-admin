package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.MallProductCardDTO;
import com.maxrocky.vesta.application.inf.MallProductCardService;
import com.maxrocky.vesta.domain.model.MallProductCardEntity;
import com.maxrocky.vesta.domain.model.MallProductEntity;
import com.maxrocky.vesta.domain.repository.IntegralMallRepository;
import com.maxrocky.vesta.domain.repository.MallProductCardRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
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
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/5.
 */
@Service
public class MallProductCardServiceImpl implements MallProductCardService {

    @Autowired
    MallProductCardRepository mallProductCardRepository;

    @Autowired
    IntegralMallRepository integralMallRepository;

    @Override
    public Map<String,Object> add(String id, InputStream fis) {

        MallProductEntity  productEntity = integralMallRepository.getProductById(id);
        MallProductCardEntity m = null;
        Map<String,Object> resultMap = new HashMap<>();
        List list = new ArrayList();
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
                    m = new MallProductCardEntity();
                    MallProductCardEntity mc = mallProductCardRepository.getCode(productEntity.getProductId(),getCellValue(row.getCell(1)).trim());

                    if (null != mc){
                       list.add(getCellValue(row.getCell(0)).trim());
                       continue;
                    }
                    m.setCardId(IdGen.uuid());
                    m.setCode(getCellValue(row.getCell(1)).trim());
                    m.setMallId(productEntity.getProductId());
                    m.setPwd(getCellValue(row.getCell(2)).trim());
                    m.setCreateOn(new Date());
                    productEntity.setProductStock(productEntity.getProductStock()+1);
                    mallProductCardRepository.addOrupdate(m);
                    integralMallRepository.saveOrUpdate(productEntity);
                }
            }
            resultMap.put("list", list);
            resultMap.put("error", "0");
            return resultMap;
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "-1");
            return resultMap;
        }
    }

    @Override
    public void update(MallProductCardDTO c) {

    }

    @Override
    public List<MallProductCardDTO> getAll(String mallId, WebPage webPage) {
        List<MallProductCardEntity> m = mallProductCardRepository.getAll(mallId,webPage);
        //结果集
        List<MallProductCardDTO> dto = new ArrayList<>();
        for (MallProductCardEntity mp : m){
            MallProductCardDTO mdto = new MallProductCardDTO();
            BeanUtils.copyProperties(mp,mdto);
            dto.add(mdto);
        }
        return dto;
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

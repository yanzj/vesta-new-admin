package com.maxrocky.vesta.application.nursery.impl;

import com.maxrocky.vesta.application.nursery.DTO.NurseryDTO;
import com.maxrocky.vesta.application.nursery.inf.NurseryService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.nursery.model.NurseryEntity;
import com.maxrocky.vesta.domain.nursery.repository.NurseryRepository;
import com.maxrocky.vesta.domain.readilyTake.model.SecurityImageEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 苗木Service实现类
 * Created by yuanyn on 2017/9/29.
 */
@Service
public class NurseryServiceImpl implements NurseryService {

    @Autowired
    private NurseryRepository nurseryRepository;

    @Override
    public List<NurseryDTO> getNurseryList(WebPage webPage) {
        List<NurseryDTO> nurseryDTOS = new ArrayList<>();
        List<NurseryEntity> nurseryEntities = nurseryRepository.getNurseryList(webPage);
        if(nurseryEntities !=null && nurseryEntities.size()>0){
            for(NurseryEntity nurseryEntity : nurseryEntities){
                NurseryDTO nurseryDTO = new NurseryDTO();
                nurseryDTO.setNurseryName(nurseryEntity.getNurseryName());
                nurseryDTO.setHeight(nurseryEntity.getHeight());
                nurseryDTO.setNum(nurseryEntity.getNum());
                nurseryDTO.setImageUrl(nurseryEntity.getImageUrl());
                nurseryDTO.setRemark(nurseryEntity.getRemark());
                nurseryDTOS.add(nurseryDTO);
            }
        }
        return nurseryDTOS;
    }

    @Override
    public String exportNurseryModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        BufferedInputStream buffered = null;
        OutputStream out = null;
        HttpSession session = httpServletRequest.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
        String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "NurseryStockTemplate.xlsx";
        File file = new File(filePath);
        if (!file.exists()) {
            return "File not found!";
        }
        try {
            buffered = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[1024];
            int length = 0;
            httpServletResponse.reset(); //非常重要
            httpServletResponse.setContentType("application/x-msdownload");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
            out = httpServletResponse.getOutputStream();
            while ((length = buffered.read(buffer)) > 0)
                out.write(buffer, 0, length);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                buffered.close();
                out.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return "ok";
    }

    @Override
    public String importNurseryExcel(InputStream fis) {
        try {
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for (int i = 0; i < hwb.getNumberOfSheets(); i++){
                sheet = hwb.getSheetAt(i);
                //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++){
                    NurseryEntity nurseryEntity = new NurseryEntity();
                    row = sheet.getRow(j);
                    //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                    if (row.getCell(0) != null) { //第一列  苗木名称
                        nurseryEntity.setNurseryName(row.getCell(0).getRichStringCellValue().getString().trim());
                    }
                    if(row.getCell(1) != null){ //第二列  苗木数量
                        nurseryEntity.setNum(row.getCell(1).getRichStringCellValue().getString().trim());
                    }
                    if(row.getCell(2) != null){ //第三列  苗木高度
                        nurseryEntity.setHeight(row.getCell(2).getRichStringCellValue().getString().trim());
                    }
                    nurseryEntity.setId(IdGen.uuid());
                    nurseryEntity.setImageUrl("0");
                    nurseryEntity.setRemark(" ");
                    nurseryEntity.setModifyDate(new Date());
                    nurseryRepository.addNurseryEntity(nurseryEntity);
                }
            }
                return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "未知错误！";
        }
    }

    @Override
    public void uploadNurseryImage(MultipartFile[] multipartFile) {
        List<NurseryEntity> nurseryEntities = nurseryRepository.getNurseryList(null);
        if(nurseryEntities != null && nurseryEntities.size()>0){
            if (multipartFile.length>0 && multipartFile != null){
                for(MultipartFile multipartFile1: multipartFile){
                    String reviewImgFileUrl = imgUpload(multipartFile1);
                    if (reviewImgFileUrl.lastIndexOf("/") != reviewImgFileUrl.length()-1){
                        for(NurseryEntity nurseryEntity : nurseryEntities){
                            if(nurseryEntity.getImageUrl().equals("0") && nurseryEntity.getNurseryName().equals(multipartFile1.getOriginalFilename().substring(0,multipartFile1.getOriginalFilename().lastIndexOf(".")))){
                                nurseryEntity.setImageUrl(reviewImgFileUrl);
                                nurseryEntity.setModifyDate(new Date());
                                nurseryRepository.updateNursery(nurseryEntity);
                            }
                        }
                    }
                }
            }
        }
    }

    public String imgUpload(MultipartFile multipartFile){
        String imgUrl = "";
        try{
            //处理图片上传
            if (null != multipartFile){
                ImgService imgService = new ImgServiceImpl();
                //图片地址特殊处理
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                imgUrl = imgService.uploadAdminImage(multipartFile, ImgType.VOTEIMG);
                imgUrl = urlTitle + imgUrl.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return imgUrl;
    }
}

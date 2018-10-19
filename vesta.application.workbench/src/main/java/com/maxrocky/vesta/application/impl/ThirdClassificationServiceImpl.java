package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.jsonDTO.AllClassificationDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.SearchClassificationDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.ThirdClassificationCommDTO;
import com.maxrocky.vesta.application.inf.ThirdClassificationService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.ClassificationTemporaryTimeEntity;
import com.maxrocky.vesta.domain.model.ThirdClassificationCommEntity;
import com.maxrocky.vesta.domain.model.ThirdClassificationEntity;
import com.maxrocky.vesta.domain.repository.ThirdClassificationCRMRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by lpc on 2016/6/6.
 */
@Service
public class ThirdClassificationServiceImpl implements ThirdClassificationService {

    @Autowired
    ThirdClassificationCRMRepository thirdClassificationCRMRepository;
    @Autowired
    MapperFacade mapperFacade;

    @Override
    public Map<String, String> getThirdClassification(String secondId) {
        List<ThirdClassificationEntity> list = thirdClassificationCRMRepository.getSecondClassification(secondId);
        Map<String, String> map = new HashMap<>();
        map.put("0000","请选择三级分类");
//        map.put("secondId",secondId);
        if(secondId==null){
            return map;
        }else{
            if(list != null && !list.isEmpty()){
                for(ThirdClassificationEntity sce : list){
                    //如果有三级分类别名则给别名，如果没有则给lebel
                        if (!StringUtil.isEmpty(sce.getAlias())) {
                            map.put(sce.getValue(),sce.getAlias());
                        }else {
                            map.put(sce.getValue(),sce.getLabel());
                        }
                }
            }

            return map;
        }
    }
    @Override
    public Map<String, String> getThirdClassificationNew(String secondId) {
        List<ThirdClassificationEntity> list = thirdClassificationCRMRepository.getSecondClassification(secondId);
        Map<String, String> map = new HashMap<>();
        map.put("0000","请选择三级分类");
        map.put("secondId",secondId);
        if(secondId==null){
            return map;
        }else{
            if(list != null && !list.isEmpty()){
                for(ThirdClassificationEntity sce : list){
                    //如果有三级分类别名则给别名，如果没有则给lebel
                    if (!StringUtil.isEmpty(sce.getAlias())) {
                        map.put(sce.getValue(),sce.getAlias());
                    }else {
                        map.put(sce.getValue(),sce.getLabel());
                    }
                }
            }

            return map;
        }
    }
    @Override
    public Map<String, String> getAllCommonlyUsedManagement() {
        List<ThirdClassificationCommEntity> list = thirdClassificationCRMRepository.getAllCommonlyUsedManagement();
        Map<String, String> map = new LinkedHashMap<>();

        if(list != null && !list.isEmpty()){
            for(ThirdClassificationCommEntity sce : list){
                map.put(sce.getValue(), sce.getAlias() ==null ? sce.getLabel() : sce.getAlias());
            }
        }
        return map;
    }

    @Override
    public ApiResult update(ThirdClassificationCommDTO thirdClassificationCommDTO) throws GeneralException {

        try {
            ThirdClassificationCommEntity thirdClassificationCommEntity = thirdClassificationCRMRepository.getComm(thirdClassificationCommDTO.getValue());
            //thirdClassificationCommEntity = mapperFacade.map(thirdClassificationCommDTO,ThirdClassificationCommEntity.class);
            thirdClassificationCommEntity.setTimeStamp(new Date());
            thirdClassificationCommEntity.setItemOrder(thirdClassificationCommDTO.getItemOrder());
            thirdClassificationCRMRepository.update(thirdClassificationCommEntity);//更新时间
           /* if (!result){
                return ErrorResource.getError("tip_Workbench_LOGOupdateFaild");//头像更新失败
            }else {
                return new SuccessApiResult(SuccessResource.getResource("tip_Workbench_LOGOupdateSuc"));//头像更新成功
            }*/
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
        return null;
    }

    @Override
    public boolean insert(ThirdClassificationCommDTO thirdClassificationCommDTO) {
        ThirdClassificationCommEntity thirdClassificationCommEntity = new ThirdClassificationCommEntity();
        thirdClassificationCommEntity.setId(thirdClassificationCommDTO.getId());
        thirdClassificationCommEntity.setLabel(thirdClassificationCommDTO.getLabel());
        thirdClassificationCommEntity.setValue(thirdClassificationCommDTO.getValue());
        thirdClassificationCommEntity.setSecondId(thirdClassificationCommDTO.getSecondId());
        thirdClassificationCommEntity.setTimeStamp(thirdClassificationCommDTO.getTimeStamp());
        thirdClassificationCommEntity.setItemOrder(thirdClassificationCommDTO.getItemOrder());
        /*String id = IdGen.uuid();*/
        thirdClassificationCRMRepository.savethirdClassificationComm(thirdClassificationCommEntity);

        return false;
    }

    @Override
    public boolean deleteAll() {
        thirdClassificationCRMRepository.deleteAll();
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        thirdClassificationCRMRepository.deleteById(id);
        return false;
    }

    @Override
    public List<AllClassificationDTO> getAllClassifyLlist(SearchClassificationDTO searchClassificationDTO,WebPage webPage) {

        List<AllClassificationDTO> dtoList = new ArrayList<AllClassificationDTO>();
        String oneType = searchClassificationDTO.getOneType();
        String twoType = searchClassificationDTO.getTwoType();
        String threeType = searchClassificationDTO.getThreeType();
        String alias = searchClassificationDTO.getAlias();
        List<Object[]> list = thirdClassificationCRMRepository.getAllClassifyLlist(oneType,twoType,threeType,alias,searchClassificationDTO.getThirdTypeName(),webPage);
        if(list!=null && list.size()>0){
            for(Object[] obj : list){
                AllClassificationDTO dto = new AllClassificationDTO();
                dto.setFirst_Value((String)obj[0]);
                dto.setFirst_Label((String) obj[1]);

                dto.setSecond_Value((String) obj[2]);
                dto.setSecond_Label((String) obj[3]);

                dto.setThird_Value((String) obj[4]);
                dto.setThird_Label((String) obj[5]);
                dto.setThird_Modify_date(DateUtils.format((Date) obj[6]));
                dto.setThird_Alias((String) obj[7]);

                dtoList.add(dto);
                dto=null;
            }
            return dtoList;
        }

        return null;
    }

    @Override
    public void updateThirdAlias(AllClassificationDTO allClassificationDTO) {
        ThirdClassificationEntity entity = new ThirdClassificationEntity();
        entity = thirdClassificationCRMRepository.get(allClassificationDTO.getThird_Value());
        if (entity!=null) {
            entity.setAlias(allClassificationDTO.getThird_Alias());
            entity.setModifyDate(new Date());
            thirdClassificationCRMRepository.update(entity);
        }
        ClassificationTemporaryTimeEntity obj = thirdClassificationCRMRepository.getClassificationTemporaryTimeEntity(allClassificationDTO.getThird_Value());
//        ClassificationTemporaryTimeEntity
        if(obj!=null){
            obj.setAlias(allClassificationDTO.getThird_Alias());
            obj.setTimeStamp(new Date());
            thirdClassificationCRMRepository.updateClassificationTemporaryTimeEntity(obj);
        }

        ThirdClassificationCommEntity objthird = thirdClassificationCRMRepository.getThirdClassificationCommEntity(allClassificationDTO.getThird_Value());
        if(objthird!=null){
            objthird.setAlias(allClassificationDTO.getThird_Alias());
            objthird.setTimeStamp(new Date());
            thirdClassificationCRMRepository.updateThirdClassificationCommEntity(objthird);
        }

        //te
    }

//    @Override
//    public void orderThirdClassification(String value, String operation) {
//        thirdClassificationCRMRepository.orderThirdClassification(value,operation);
//    }

}

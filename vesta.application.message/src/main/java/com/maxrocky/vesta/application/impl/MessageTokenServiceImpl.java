package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.appDTO.MessageTokenDTO;
import com.maxrocky.vesta.application.inf.MessageTokenService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.MessageTokenEntity;
import com.maxrocky.vesta.domain.repository.MessageTokenRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhanghj on 2016/3/3.
 */
@Service
public class MessageTokenServiceImpl implements MessageTokenService {

@Autowired
private MessageTokenRepository messageTokenRepository;
    /**
     * 保存token
     * @param messageTokenDTO
     * @return
     */
    @Override
    public ApiResult saveToken(MessageTokenDTO messageTokenDTO) throws GeneralException {
        if (messageTokenDTO.getUserId()==null||messageTokenDTO.getUserId().equals("")){
            return ErrorResource.getError("tip_message_UseridNull");
        }
        if (messageTokenDTO.getMessageTokenNum()==null||messageTokenDTO.getMessageTokenNum().equals("")){
            return ErrorResource.getError("tip_message_TokenNull");
        }
        if (messageTokenDTO.getMobileType()!=0&&messageTokenDTO.getMobileType()!=1){
            return ErrorResource.getError("tip_message_MobileTypeNull");
        }
        try {
            MessageTokenEntity messageTokenEntity = messageTokenRepository.getByUserId(messageTokenDTO.getUserId());
            if (messageTokenEntity==null) {
                String tokenId= IdGen.uuid();
                MessageTokenEntity token = new MessageTokenEntity();
                token.setMessageTokenId(tokenId);
                token.setMobileType(messageTokenDTO.getMobileType());
                token.setMessageTokenNum(messageTokenDTO.getMessageTokenNum());
                token.setTokenCreateTime(DateUtils.getDate());
                token.setUserId(messageTokenDTO.getUserId());
                boolean t = messageTokenRepository.saveMessageToken(token);
                if (t) {
                    return new SuccessApiResult(SuccessResource.getResource("tip_token_saveSuccess"), null);//保存成功
                }
                else {
                    return ErrorResource.getError("tip_message_savefaild");
                }
            }
            else {
                messageTokenEntity.setMessageTokenNum(messageTokenDTO.getMessageTokenNum());
                messageTokenEntity.setMobileType(messageTokenDTO.getMobileType());
                messageTokenEntity.setTokenCreateTime(DateUtils.getDate());
                boolean t = messageTokenRepository.updateToken(messageTokenEntity);
                if (t) {
                    return new SuccessApiResult(SuccessResource.getResource("tip_token_saveSuccess"), null);//保存成功
                }
                else {
                    return ErrorResource.getError("tip_message_savefaild");
                }
            }
//            return new SuccessApiResult(SuccessResource.getResource("tip_message_delSuccess"), null);//添加成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 删除token
     * @param userId
     * @return
     */
    @Override
    public void delToken(String userId) {
        if (userId.equals("")){
            return ;
        }
//        if (messageTokenDTO.getMessageTokenNum()==null||messageTokenDTO.getMessageTokenNum().equals("")){
//            return ErrorResource.getError("tip_message_TokenNull");
//        }
//        if (messageTokenDTO.getMobileType()!=1&&messageTokenDTO.getMobileType()!=2){
//            return ErrorResource.getError("tip_message_MobileTypeNull");
//        }
        try{
//                MessageTokenEntity messageTokenEntity = messageTokenRepository.getByUserId(userId);
            List<MessageTokenEntity> messageTokenEntities = messageTokenRepository.listTokenByUser(userId);
            if (messageTokenEntities.size()>0) {
                for (MessageTokenEntity messageTokenEntity:messageTokenEntities) {
                    if (messageTokenEntity != null) {
                        boolean result = messageTokenRepository.deleteMessageToken(messageTokenEntity);
//                        if (result) {
//                            return;//删除成功
//                        }
//                        return ;//删除失败
                    }
                }
            }
            return;//删除成功
        }
        catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }
}

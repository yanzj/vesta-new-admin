package com.maxrocky.vesta.application.Thread;

import com.maxrocky.vesta.domain.model.OSSFilesEntity;
import com.maxrocky.vesta.domain.repository.OSSFilesRepository;
import com.maxrocky.vesta.sso.WeChatImage;
import com.maxrocky.vesta.sso.WeChatUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu on 2015/9/25.
 * 线程
 * 通过查询OSSFiles数据，对其微信上传图片下载到服务器
 * 然后上传到阿里OSS上
 * 最后删除图片
 * 在操作线程过程中如果有错误，或者异常，跳过
 */
@Service
public class OSSRunnable implements Runnable{

    /*OSS*/
    @Autowired
    private OSSFilesRepository ossFilesRepository;
    /*自动启动*/
    private Boolean isAuto = false;
    /*OSS List*/
    private List<OSSFilesEntity> ossFilesList = new ArrayList<OSSFilesEntity>();

    public OSSRunnable(){
        this.isAuto = true;
    }

    public OSSRunnable(List<OSSFilesEntity> ossFilesList, OSSFilesRepository ossFilesRepository){
        this.ossFilesList = ossFilesList;
        this.ossFilesRepository = ossFilesRepository;
    }

    /**
     * 线程调用方法
     */
    @Override
    public void run() {
        //线程锁
//        synchronized(this){

        try {
            if(isAuto){
                ossFilesList = ossFilesRepository.getWaitOSSFiles();
            }
        }
        catch (Exception ex){
//                ex.printStackTrace();
        }

        if(ossFilesList == null){
            return;
        }
        if(ossFilesList.size() == 0){
            return;
        }

        //循环OSS上传图片
        for (OSSFilesEntity ossFiles : ossFilesList){

            try {
                WeChatImage weChatImage = new WeChatImage();
                weChatImage.setImageName(ossFiles.getFileName());
                weChatImage.setMediaId(ossFiles.getMediaId());
                weChatImage.setOssPath(ossFiles.getUploadPath());

                //上传图片
                WeChatUtility.wechatToOSS(weChatImage);

                ossFilesRepository.setIsUpload(ossFiles.getOssId());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }

//    }
}

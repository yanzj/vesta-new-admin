package com.maxrocky.vesta.sso;

/**
 * Created by HaiXiang Yu on 2015/9/25.
 */
public class WeChatImage {

    private String mediaId;
    private String imageName;//图片名称  （adfd123.jpg）
    private String ossPath;//oss路径

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getOssPath() {
        return ossPath;
    }

    public void setOssPath(String ossPath) {
        this.ossPath = ossPath;
    }
}

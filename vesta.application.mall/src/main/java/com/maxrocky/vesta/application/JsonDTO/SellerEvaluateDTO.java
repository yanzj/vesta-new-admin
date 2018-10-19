package com.maxrocky.vesta.application.JsonDTO;

/**
 * Created by chen on 2016/1/18.
 */
public class SellerEvaluateDTO {
    private String sellerName;  //商户名
    private String sellerId;    //商户ID
    private String commentNum;  //鲜花好评数

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}

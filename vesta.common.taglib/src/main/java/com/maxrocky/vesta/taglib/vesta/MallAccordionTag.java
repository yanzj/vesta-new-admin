package com.maxrocky.vesta.taglib.vesta;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by JillChen on 2015/12/17.
 */
public class MallAccordionTag extends SimpleTagSupport {

    private String sellerId;

    @Override
    public void doTag() throws JspException, IOException {
        StringBuilder outWrapStr = new StringBuilder();

        outWrapStr.append("<div class='accordion' id='accordion-551887'>");
        outWrapStr.append("<div class='accordion-group'>");
        outWrapStr.append("<div class='accordion-heading'>");
        outWrapStr.append("<a class='accordion-toggle collapsed'  href='#accordion-element-622296' data-toggle='collapse' data-parent='#accordion-551887'>商户基本信息</a>");
        outWrapStr.append("</div>");
        outWrapStr.append("<div class='accordion-body in' id='accordion-element-622296'>");
        outWrapStr.append("<div class='accordion-inner'>");
        outWrapStr.append("<a href='../mall/AddStore.html?sellerId="+sellerId+"'>商户信息</a>");
        outWrapStr.append("</div>");
        outWrapStr.append("<div class='accordion-inner'>");
        outWrapStr.append("<a href='../mall/PictureStoreManage.html?sellerId="+sellerId+"'>商户图片</a>");
        outWrapStr.append("</div>");
        outWrapStr.append("</div>");
        outWrapStr.append("</div>");
        outWrapStr.append("<div class='accordion-group'>");
        outWrapStr.append("<div class='accordion-heading'>");
        outWrapStr.append("<a class='accordion-toggle' href='#accordion-element-622297' data-toggle='collapse' data-parent='#accordion-551887'>产品分类管理</a>");
        outWrapStr.append("</div>");
        outWrapStr.append("<div class='accordion-body in' id='accordion-element-622297'>");
        outWrapStr.append("<div class='accordion-inner'>");
        outWrapStr.append("<a href='../mall/ProductSetManage.html'>维护产品分类</a>");
        outWrapStr.append("</div>");
        outWrapStr.append("</div>");
        outWrapStr.append("</div>");
        outWrapStr.append("<div class='accordion-group'>");
        outWrapStr.append("<div class='accordion-heading'>");
        outWrapStr.append("<a class='accordion-toggle'  href='#accordion-element-622298'  data-toggle='collapse' data-parent='#accordion-551887'>产品信息管理</a>");
        outWrapStr.append("</div>");
        outWrapStr.append("<div class='accordion-body in' id='accordion-element-622298'>");
        outWrapStr.append("<div class='accordion-inner'>");
        outWrapStr.append("<a href='../mall/ProductManage.html'>维护产品信息</a>");
        outWrapStr.append("</div>");
        outWrapStr.append("</div>");
        outWrapStr.append("</div>");
        outWrapStr.append("</div>");


//        outWrapStr.append("");

        // 输出
        this.getJspContext().getOut().println(outWrapStr.toString());
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}

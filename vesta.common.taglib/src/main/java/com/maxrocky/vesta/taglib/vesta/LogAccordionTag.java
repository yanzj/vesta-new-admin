package com.maxrocky.vesta.taglib.vesta;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by JillChen on 2015/12/17.
 */
public class LogAccordionTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {

        StringBuilder outWrapStr = new StringBuilder();

        outWrapStr.append("<div class='accordion' id='accordion-551887'>");

        outWrapStr.append("<div class='accordion-group'>");
        outWrapStr.append("<div class='accordion-heading'>");

        outWrapStr.append("<a class='accordion-toggle collapsed'  href='#accordion-element-622296' data-toggle='collapse' data-parent='#accordion-551887'>系统日志</a>");
        outWrapStr.append("</div>");
        outWrapStr.append("<div class='accordion-body in' id='accordion-element-622296'>");

        outWrapStr.append("<div class='accordion-inner'>");
        outWrapStr.append("<a href='../log/propertyLog.html'>物业管理日志</a>");
        outWrapStr.append("</div>");

        outWrapStr.append("<div class='accordion-inner'>");
        outWrapStr.append("<a href='../log/tradeLog.html'>交易管理日志</a>");
        outWrapStr.append("</div>");

        outWrapStr.append("<div class='accordion-inner'>");
        outWrapStr.append("<a href='../log/sellerLog.html'>商户管理日志</a>");
        outWrapStr.append("</div>");

        outWrapStr.append("<div class='accordion-inner'>");
        outWrapStr.append("<a href='../log/settleLog.html'>清结算日志</a>");
        outWrapStr.append("</div>");

        outWrapStr.append("<div class='accordion-inner'>");
        outWrapStr.append("<a href='../log/userManagerLog.html'>用户管理日志</a>");
        outWrapStr.append("</div>");

        outWrapStr.append("<div class='accordion-inner'>");
        outWrapStr.append("<a href='../log/payLog.html'>支付接口管理日志</a>");
        outWrapStr.append("</div>");

        outWrapStr.append("<div class='accordion-inner'>");
        outWrapStr.append("<a href='../log/roleLog.html'>平台权限管理日志</a>");
        outWrapStr.append("</div>");

        outWrapStr.append("</div>");
        outWrapStr.append("</div>");
        outWrapStr.append("</div>");

//        outWrapStr.append("");

        // 输出
        this.getJspContext().getOut().println(outWrapStr.toString());
    }
}

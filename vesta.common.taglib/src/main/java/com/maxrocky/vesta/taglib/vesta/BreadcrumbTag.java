package com.maxrocky.vesta.taglib.vesta;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by JillChen on 2015/12/17.
 */
public class BreadcrumbTag  extends SimpleTagSupport {
    private String no;

    @Override
    public void doTag() throws JspException, IOException {

        StringBuilder outWrapStr = new StringBuilder();
        outWrapStr.append("<div class='mainRight fl'>");
        outWrapStr.append("<div class='inner'>");
        outWrapStr.append("<div class='p20 pt5'>");
        outWrapStr.append( "<div class='tit1'>您现在的位置是：");
        switch (no) {
            case "0" :
                outWrapStr.append( "<a href='#'>首頁</a> ");
                break;
        }
        outWrapStr.append("</div>");
        outWrapStr.append("</ol>");
//        outWrapStr.append("");

        // 输出
        this.getJspContext().getOut().println(outWrapStr.toString());
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}

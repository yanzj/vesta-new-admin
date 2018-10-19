package com.maxrocky.vesta.taglib.vesta;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by JillChen on 2016/1/14.
 */
public class VestaHeaderTag extends SimpleTagSupport {

    private String sysTitle;

    private String username;

    private String currRegion;

    @Override
    public void doTag() throws JspException, IOException {
        StringBuilder headerWrapStr = new StringBuilder();

        headerWrapStr.append("<div class='sticky-header header-section '>");
        headerWrapStr.append("<div>");
        headerWrapStr.append("<button id='showLeftPush'><i class='fa fa-bars'></i></button>");
        headerWrapStr.append("<div class='logo'>");
        headerWrapStr.append("<a href='#' class='fl logo block mt20 ml50'>");
        headerWrapStr.append("<img src='../static/images/ui/logo.png' />");
        headerWrapStr.append("</a>");
        headerWrapStr.append("</div>");
        headerWrapStr.append("<div class='clearfix'> </div>");
        headerWrapStr.append("</div>");
        headerWrapStr.append("<div class='clearfix'> </div>");
        headerWrapStr.append("</div>");

        //main ---start
        headerWrapStr.append("<div id='page-wrapper'>");
        headerWrapStr.append("<div class='main-page'>");




        this.getJspContext().getOut().println(headerWrapStr.toString());
    }

//    @Override
//    public void doTag() throws JspException, IOException {
//
//        StringBuilder headerWrapStr = new StringBuilder();
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
////        headerWrapStr.append("<div class='navbar navbar-inverse'>");
////        top  模块开始
////        headerWrapStr.append("<div class='top'>");
////        headerWrapStr.append("<div class='pl75 pr75 pr'>");
////
////        headerWrapStr.append("<a href='#' class='fl logo block mt20 ml50'>");
////        headerWrapStr.append("<img src='../static/wandastyle/images/ui/logo.png' />");
////        headerWrapStr.append("</a>");
////        headerWrapStr.append("<span class='block topRight mt10 fr'> ");
////        headerWrapStr.append("<span> "+df.format(new Date())+" </span>");
////        headerWrapStr.append("<span>您好，"+username+"</span>");
////        headerWrapStr.append("<span class='selS1'>"+currRegion+"</span>");
////        headerWrapStr.append("<a href='#' class='edit'>修改密码</a>");
////        headerWrapStr.append("<a href='#' class='exit'>退出</a>");
////        headerWrapStr.append("</span>");
////        headerWrapStr.append("");
////        headerWrapStr.append("");
////
////        headerWrapStr.append("</div>");
////
////        headerWrapStr.append("</div>");
////        top  模块结束
//
//
//        headerWrapStr.append("<div class='navbar navbar-inverse'>");
//        headerWrapStr.append("<div class='container' style='margin-bottom: 0px'>");
//        headerWrapStr.append("<div class='navbar-header'>");
//        headerWrapStr.append("<a href='#' class='navbar-brand'><img src='../static/wandastyle/images/ui/logo.png'/></a>");
//        headerWrapStr.append("</div>");
//
//        headerWrapStr.append("<div class='statusBar'><span>"+df.format(new Date())+"</span>");
//        headerWrapStr.append("<span> 您好，"+username+"&nbsp;&nbsp;" +"</span>");
//        headerWrapStr.append("<span class='selS1'>"+currRegion+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
//        headerWrapStr.append("<a href='../user/passManage.html' ><span  class='glyphicon glyphicon-edit'></span> 密码修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='../login/logOut.html' ><span  class='glyphicon glyphicon-log-out'></span> 退出</a></div>");
//
//
//        headerWrapStr.append("</div>");
//
//        headerWrapStr.append("</div>");
//        // 输出
//        this.getJspContext().getOut().println(headerWrapStr.toString());
//    }

    public String getSysTitle() {
        return sysTitle;
    }

    public void setSysTitle(String sysTitle) {
        this.sysTitle = sysTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrRegion() {
        return currRegion;
    }

    public void setCurrRegion(String currRegion) {
        this.currRegion = currRegion;
    }
}

package com.maxrocky.vesta.taglib.vesta;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by JillChen on 2015/12/17.
 */
public class VestaTag extends SimpleTagSupport {

    private String sysTitle;

    private List<Viewmodel> menulist;

    private List<Viewmodel> secanViewlist;

    private String username;

    @Override
    public void doTag() throws JspException, IOException {

        StringBuilder headerWrapStr = new StringBuilder();

        headerWrapStr.append("<div class='navbar navbar-inverse'>");
        headerWrapStr.append("<div class='container' style='margin-bottom: 0px'>");
        headerWrapStr.append("<div class='navbar-header'>");
        headerWrapStr.append("<a href='#' class='navbar-brand'><img src='../static/wandastyle/images/ui/logo.png'/></a>");
        headerWrapStr.append("</div>");

        headerWrapStr.append("<div class='collapse navbar-collapse'>");
        headerWrapStr.append("<ul class='nav navbar-nav'>");

        Viewmodel tmpmodel;
        for(int i=0;i<menulist.size();i++){
            //按順序判斷一級菜單是否存在
            tmpmodel = menulist.get(i);
            //爲一級菜單賦值%>

            headerWrapStr.append("<li><a href='#' role='button'' class='dropdown-toggle' data-toggle='dropdown'>"+tmpmodel.getMenuName() +"</a>");

            if("N".equals(tmpmodel.getChildFlag())){//有下級菜單

                headerWrapStr.append("<ul class='dropdown-menu'>");
                for(int j=0;j<secanViewlist.size();j++){
                    Viewmodel secanView = secanViewlist.get(j);
                    if(secanView==null){
                        //初始化錯誤
                    }else if(tmpmodel.getMenuId().equals(secanView.getParantmenuid())&&"Y".equals(secanView.getChildFlag())){ //二級無子集
                        if(!"1".equals(secanView.getMenuorder())){
                            headerWrapStr.append("<li class='divider'></li>");
                            }
                        headerWrapStr.append("<li><a tabindex='-1' href='"+secanView.getRunscript()+"'>"+secanView.getMenuName() +"</a></li>");


                    }else if(tmpmodel.getMenuId().equals(secanView.getParantmenuid())&&"N".equals(secanView.getChildFlag())){//二級有子集%>
                        headerWrapStr.append("<li class='divider'></li>");
                        headerWrapStr.append("<li class='dropdown-submenu'>");
                         if(!"".equals(secanView.getRunscript())){

                             headerWrapStr.append("<a href='"+secanView.getRunscript()+"'>"+secanView.getMenuName() +"</a>");

                            }else {
                             headerWrapStr.append("<a >"+secanView.getMenuName()+" </a>");
                            }
                        headerWrapStr.append("<ul class='dropdown-menu'>");

                        for(int k=0;k<secanViewlist.size();k++){
                            Viewmodel thirdView  = secanViewlist.get(k);
                            if(thirdView == null){

                            }else if(secanView.getMenuId().equals(thirdView.getParantmenuid())){

                                headerWrapStr.append(" <li><a href='"+thirdView.getRunscript()+"'>"+thirdView.getMenuName() +"</a></li>");
                                 }
                        }
                            headerWrapStr.append("</ul>");
                            headerWrapStr.append("</li>");
                         }
                }

                headerWrapStr.append("</ul>");

            }
            headerWrapStr.append("</li>");

        }
        headerWrapStr.append("</div>");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        headerWrapStr.append("<div class='statusBar'>"+df.format(new Date())+" 您好，"+username+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        headerWrapStr.append("<a href='../user/passManage.html' >密码修改</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='../login/logOut.html' ><span  class='glyphicon glyphicon-log-out'></span> 退出</a></div>");

        headerWrapStr.append("</div>");
        headerWrapStr.append("</div>");
        // 输出
        this.getJspContext().getOut().println(headerWrapStr.toString());
    }

    public String getSysTitle() {
        return sysTitle;
    }

    public void setSysTitle(String sysTitle) {
        this.sysTitle = sysTitle;
    }

    public List<Viewmodel> getMenulist() {
        return menulist;
    }

    public void setMenulist(List<Viewmodel> menulist) {
        this.menulist = menulist;
    }

    public List<Viewmodel> getSecanViewlist() {
        return secanViewlist;
    }

    public void setSecanViewlist(List<Viewmodel> secanViewlist) {
        this.secanViewlist = secanViewlist;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

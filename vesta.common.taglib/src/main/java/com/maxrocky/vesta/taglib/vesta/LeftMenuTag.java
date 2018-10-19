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
public class LeftMenuTag extends SimpleTagSupport {

    private String sysTitle;

    private List<Viewmodel> menulist;

    private List<Viewmodel> secanViewlist;

    private String crunMenu;


    private String username;

    private String currRegion;

    private String loginid;

    private String sso_menuinfo;


    @Override
    public void doTag() throws JspException, IOException {

        String myBreadcrumb  = "";

        StringBuilder outWrapStr = new StringBuilder();
        outWrapStr.append("<div class=' sidebar' role='navigation'>");
        outWrapStr.append("<div class='navbar-collapse'>");

        StringBuilder firstStr = new StringBuilder();
        StringBuilder twoStr = new StringBuilder();
        StringBuilder threeStr = new StringBuilder(); //showMenu
        StringBuilder fourStr = new StringBuilder();
        StringBuilder fiveStr = new StringBuilder();
        StringBuilder sixStr = new StringBuilder();
        /* sta */

        /* end */
        firstStr.append("<nav class='cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left isMenu' id='cbp-spmenu-s1'><ul class='nav' id='side-menu1'>");
        twoStr.append("<nav class='cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left isMenu' id='cbp-spmenu-s2'><ul class='nav' id='side-menu2'>");
        threeStr.append("<nav class='cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left isMenu' id='cbp-spmenu-s3'><ul class='nav' id='side-menu3'>");
        fourStr.append("<nav class='cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left isMenu' id='cbp-spmenu-s4'><ul class='nav' id='side-menu4'>");
        fiveStr.append("<nav class='cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left isMenu' id='cbp-spmenu-s5'><ul class='nav' id='side-menu5'>");
        sixStr.append("<nav class='cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left isMenu' id='cbp-spmenu-s6'><ul class='nav' id='side-menu6'>");

        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        StringBuilder str3 = new StringBuilder();
        StringBuilder str4 = new StringBuilder();
        StringBuilder str5 = new StringBuilder();
        StringBuilder str6 = new StringBuilder();

        for(Viewmodel tmpmodel:menulist) {

            StringBuilder str = new StringBuilder();

            if (tmpmodel.getBelong().equals("1")) {
                str = str1;
            } else if (tmpmodel.getBelong().equals("2")) {
                str = str2;
            } else if (tmpmodel.getBelong().equals("3")) {
                str = str3;
            } else if (tmpmodel.getBelong().equals("4")) {
                str = str4;
            } else if (tmpmodel.getBelong().equals("5")){
                str = str5;
            }else if(tmpmodel.getBelong().equals("6")){
                str = str6;
            }
            str.append("<li><a href='#'><i class='fa ");
            //拼接标签样式
            String leftIcon = getLeftIcon(tmpmodel.getMenuId());

            str.append(leftIcon);
            str.append(" nav_icon'></i>" + tmpmodel.getMenuName());
//补充二级
            if ("N".equals(tmpmodel.getChildFlag())) {//有下級菜單
                str.append("<span class='fa arrow'></span></a>");
                str.append("<ul class='nav nav-second-level collapse'>");
                for (int j = 0; j < secanViewlist.size(); j++) {
                    Viewmodel secanView = secanViewlist.get(j);
                    if (secanView == null) {
                        //初始化錯誤
                    } else if (tmpmodel.getMenuId().equals(secanView.getParantmenuid()) && "Y".equals(secanView.getChildFlag())) { //二級無子集

                        str.append("<li><a id='" + secanView.getMenuId() + "' href='" + secanView.getRunscript() + "'");
                        if (crunMenu != null && crunMenu.equals(secanView.getMenuId())) {
//                            outWrapStr.append("class='current2'");
                            myBreadcrumb = "<a href='#'>" + tmpmodel.getMenuName() + "</a> <span> &gt;" + secanView.getMenuName() + "</span>";
                        }
                        str.append(">" + secanView.getMenuName() + "</a></li>");
                    }
                }
                str.append("</ul>");
            } else {
                str.append("</a>");
            }
            str.append("</li>");
        }


        firstStr.append(str1+"</ul></nav>");
        twoStr.append(str2+"</ul></nav>");
        threeStr.append(str3+"</ul></nav>");
        fourStr.append(str4+"</ul></nav>");
        fiveStr.append(str5+"</ul></nav>");
        sixStr.append(str6+"</ul></nav>");

        outWrapStr.append(firstStr).append(twoStr);
        outWrapStr.append(threeStr).append(fourStr);
        outWrapStr.append(fiveStr).append(sixStr);


        //菜单栏-----end
        //header---start
        outWrapStr.append("<div class='sticky-header header-section '>");
        outWrapStr.append("<div>");
        outWrapStr.append("<button id='showLeftPush'><i class='fa fa-bars'></i></button>");
        outWrapStr.append("<div class='header-left'>");
        outWrapStr.append("<div class='logo'>");
        outWrapStr.append("<a href='#' class='fl logo block mt20 ml50'>");
        outWrapStr.append("<img src='../static/images/ui/logo.png' />");
        outWrapStr.append("</a></div>");
        outWrapStr.append("<ul class='navList'>");
        if(str1.length() > 0){
            outWrapStr.append("<li id='vip'>金茂会员</li>");
        }
        if(str2.length() > 0){
            outWrapStr.append("<li id='quality'>工程质量</li>");
        }
        if(str3.length() > 0){
            outWrapStr.append("<li id='inspection'>金茂服务家</li>");
        }
        if(str4.length() > 0){
            outWrapStr.append("<li id='operate'>日常管理</li>");
        }
        if(str5.length() > 0){
            outWrapStr.append("<li id='base'>基础数据</li>");
        }
        if(str6.length()>0){
            outWrapStr.append("<li id='security'>安全管理</li>");
        }
        outWrapStr.append("</ul>");
        outWrapStr.append("<div class='clearfix'> </div>");
        outWrapStr.append("</div>");
        outWrapStr.append("<div class='header-right'>");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        outWrapStr.append("<div class='profile_details'>");
        outWrapStr.append("<ul><li>");
        outWrapStr.append("<span>"+df.format(new Date())+"</span>");
        outWrapStr.append("<span> 您好，"+username+"&nbsp;&nbsp;" +"</span>");
        /* 新增密码修改功能_2016-09-27_Wyd */
        outWrapStr.append("<a href=\"javascript:;\" onClick=\"javascript:window.open('../staffUser/toEditStaffUserPassword','','width=610,height=240,left=673, top=195,toolbar=no, status=no, menubar=no, resizable=no, scrollbars=no');return false;\"><span> 修改密码</span></a>");
        /* ============================ */
//        outWrapStr.append("<span class='selS1'>"+currRegion+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        outWrapStr.append("&nbsp;&nbsp;&nbsp;");
        outWrapStr.append("<a  class='signOut' href='/' ><span ></span> <span>退出</span></a>");
        outWrapStr.append("</li></ul>");
        outWrapStr.append("</div>");
        outWrapStr.append("</div>");
        outWrapStr.append("</div>");
        outWrapStr.append("<div class='clearfix'> </div>");
        outWrapStr.append("</div>");
//header---end
        //        內容詳情頁----------start


        outWrapStr.append("<!-- main content start-->");
        outWrapStr.append("<div id='page-wrapper'>");
        outWrapStr.append("<div class='main-page'>");

        outWrapStr.append("<h3 class='title1'>您现在的位置是：");
        //crunmenu = 對應的 viewmodel 表 中 menuid
        if("0".equals(crunMenu)) {
            outWrapStr.append( "<a href='#'>首页</a> ");
        }else{
            outWrapStr.append(myBreadcrumb);
        }
        outWrapStr.append("</h3>");

        // 输出
        this.getJspContext().getOut().println(outWrapStr.toString());
    }

    private String getLeftIcon(String menuId) {
        String leftIcon = "fa-star-o";
        switch (menuId){
            case "000100000000":
                leftIcon = " fa-wrench ";break;
            case "000300000000":
                leftIcon = " fa-cny ";break;
            case "000400000000":
                leftIcon = " fa-mail-reply  ";break;
            case "000500000000":
                leftIcon = " fa-gift ";break;
            case "000600000000":
                leftIcon = " fa-user ";break;
            case "000800000000":
                leftIcon = " fa-users ";break;
            case "001000000000":
                leftIcon = " fa-wrench ";break;
            case "000900000000":
                leftIcon = "fa-edit";break;
        }


        return leftIcon;
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

    public String getCrunMenu() {
        return crunMenu;
    }

    public void setCrunMenu(String crunMenu) {
        this.crunMenu = crunMenu;
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


    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }


    public String getSso_menuinfo() {
        return sso_menuinfo;
    }

    public void setSso_menuinfo(String sso_menuinfo) {
        this.sso_menuinfo = sso_menuinfo;
    }
}


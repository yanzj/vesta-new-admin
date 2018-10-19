package com.maxrocky.vesta.taglib.vesta;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by JillChen on 2016/1/17.
 */
public class LeftMenuBootStrapTag  extends SimpleTagSupport {

    private String sysTitle;

    private List<Viewmodel> menulist;

    private List<Viewmodel> secanViewlist;

    private String crunMenu;


    @Override
    public void doTag() throws JspException, IOException {

        String myBreadcrumb  = "";

        StringBuilder outWrapStr = new StringBuilder();

        outWrapStr.append("<div class='ml75 mr75'>");
        outWrapStr.append("<div class='indexmain pr ohz'>");

        // 菜单栏----start
        outWrapStr.append("<div class='w200 mainLeft'>");
        outWrapStr.append("<ul class='listUl'>");

        for(Viewmodel tmpmodel:menulist) {

            //爲一級菜單賦值%>
            outWrapStr.append("<li><a href='javascript:void(0)'  class='js1'><span class='icon1'>"+tmpmodel.getMenuName() +"</span><i></i></a>");

//补充二级
            if("N".equals(tmpmodel.getChildFlag())){//有下級菜單

                outWrapStr.append("<ul class='listUlChild'>");
                for(int j=0;j<secanViewlist.size();j++){
                    Viewmodel secanView = secanViewlist.get(j);
                    if(secanView==null){
                        //初始化錯誤
                    }else if(tmpmodel.getMenuId().equals(secanView.getParantmenuid())&&"Y".equals(secanView.getChildFlag())){ //二級無子集

                        outWrapStr.append("<li><a href='"+secanView.getRunscript()+"'");
                        if(crunMenu!=null && crunMenu.equals(tmpmodel.getMenuId())) {
                            outWrapStr.append("class='current2'");
                            myBreadcrumb = "<a href='#'>"+tmpmodel.getMenuName()+"</a> <li class='active'>"+secanView.getMenuName()+"</li>";
                        }
                        outWrapStr.append(">"+secanView.getMenuName() +"</a></li>");
                    }
                }

                outWrapStr.append("</ul>");

            }
            outWrapStr.append("</li>");

        }
        outWrapStr.append("</ul>");
        outWrapStr.append("</div>");

        //菜单栏-----end
//        內容詳情頁----------start
//        <div class="container">
//        <ol class="breadcrumb">
//        <li>您现在的位置是：<a href="#">促销活动管理</a></li>
//        <li class="active">满额赠</li>
//        </ol>
//        <div class="highlight">

        outWrapStr.append("<div class='container'>");
        outWrapStr.append("<div class=breadcrumb'>");

        outWrapStr.append( "<li>您现在的位置是：");
        //crunmenu = 對應的 viewmodel 表 中 menuid
        if("0".equals(crunMenu)) {
            outWrapStr.append( "<a href='#'>首頁</a> ");
        }else{
            outWrapStr.append(myBreadcrumb);
        }
        outWrapStr.append("</div>");
        outWrapStr.append("</ol>");
//        outWrapStr.append("");
//        outWrapStr.append("");
//        outWrapStr.append("");
//        outWrapStr.append("");
//        outWrapStr.append("");
//        outWrapStr.append("");
//        outWrapStr.append("");
//        outWrapStr.append("");
//        outWrapStr.append("");
//        outWrapStr.append("");
//        內容詳情頁----------end

//        outWrapStr.append("</div>");
//        outWrapStr.append("</div>");




        // 输出
        this.getJspContext().getOut().println(outWrapStr.toString());
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
}

package com.maxrocky.vesta.taglib.vesta.input;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by JillChen on 2015/12/18.
 */
public class InputDateFromToTag extends SimpleTagSupport {


    private String labelname;

    //from
    private String id_from;
    private String path_from;

    //to
    private String id_to;
    private String path_to;

    @Override
    public void doTag() throws JspException, IOException {
        StringBuilder outWrapStr = new StringBuilder();
//获取body
//        String str=invokeBody();

        outWrapStr.append("<label class=''>"+labelname+"</label>");
        //from
        outWrapStr.append("<div class='input-group date form_date col-lg-4' data-date='' data-date-format='yyyy-mm-dd' data-link-field='dtp_input2'>");
        outWrapStr.append("<input name="+path_from+" type='text' value='' class='form-control' readonly='true'/>");
        outWrapStr.append("<span class='input-group-addon'><span class='glyphicon glyphicon-remove'></span></span>");
        outWrapStr.append("<span class='input-group-addon'><span class='glyphicon glyphicon-calendar'></span></span>");
        outWrapStr.append("</div>");

        outWrapStr.append("至");
        //to
        outWrapStr.append("<div class='input-group date form_date col-lg-4' data-date='' data-date-format='yyyy-mm-dd' data-link-field='dtp_input2'>");
        outWrapStr.append("<input name="+path_to+" type='text' value='' class='form-control' readonly='true'/>");
        outWrapStr.append("<span class='input-group-addon'><span class='glyphicon glyphicon-remove'></span></span>");
        outWrapStr.append("<span class='input-group-addon'><span class='glyphicon glyphicon-calendar'></span></span>");
        outWrapStr.append("</div>");
        this.getJspContext().getOut().println(outWrapStr);
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getId_from() {
        return id_from;
    }

    public void setId_from(String id_from) {
        this.id_from = id_from;
    }

    public String getPath_from() {
        return path_from;
    }

    public void setPath_from(String path_from) {
        this.path_from = path_from;
    }

    public String getId_to() {
        return id_to;
    }

    public void setId_to(String id_to) {
        this.id_to = id_to;
    }

    public String getPath_to() {
        return path_to;
    }

    public void setPath_to(String path_to) {
        this.path_to = path_to;
    }
}

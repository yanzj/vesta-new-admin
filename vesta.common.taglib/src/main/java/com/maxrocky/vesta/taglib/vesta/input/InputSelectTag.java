package com.maxrocky.vesta.taglib.vesta.input;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JillChen on 2015/12/18.
 */
public class InputSelectTag extends SimpleTagSupport {

    private String labelname;

    private String id;

    private String path;

    private String optionname;

    private List<Object[]> options;

    @Override
    public void doTag() throws JspException, IOException {
        StringBuilder outWrapStr = new StringBuilder();

//        outWrapStr.append("</div>");

        outWrapStr.append("<label class=''>"+labelname+"</label>");
        outWrapStr.append("<select  name=" + path + " style='width:179px;' class='form-control'>");


        for(Object[] option:options){
            outWrapStr.append("<option value="+option[1]+" name="+optionname+" id="+option[0]+">"+option[1]+"</option>");
        }

        outWrapStr.append("</select>");
        this.getJspContext().getOut().println(outWrapStr);
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOptionname() {
        return optionname;
    }

    public void setOptionname(String optionname) {
        this.optionname = optionname;
    }

    public List<Object[]> getOptions() {
        return options;
    }

    public void setOptions(List<Object[]> options) {
        this.options = options;
    }
}

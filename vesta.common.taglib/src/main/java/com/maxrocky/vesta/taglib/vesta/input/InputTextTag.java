package com.maxrocky.vesta.taglib.vesta.input;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by JillChen on 2015/12/18.
 */
public class InputTextTag extends SimpleTagSupport {

    private String labelname;

    private String id;

    private String path;

    private boolean readonly;



    //标签的处理方法，简单标签处理类只需要重写doTag方法
    public void doTag() throws JspException, IOException
    {
        StringBuilder outWrapStr = new StringBuilder();
//获取body
//        String str=invokeBody();

//        outWrapStr.append("<div class='form-group col-lg-4'>");
        outWrapStr.append("<label class=''>" + labelname + "</label>");
        outWrapStr.append("<input type='text' class='form-control'");

        if(path!=null && !path.trim().equals("")) {
            outWrapStr.append(" name=" + path);
        }

        if(id!=null && !id.trim().equals("")) {
            outWrapStr.append(" id=" + id);
        }
        if(readonly) {
            outWrapStr.append(" readonly=" + readonly);
        }

        outWrapStr.append(" />");
//        outWrapStr.append("</div>");

        this.getJspContext().getOut().println(outWrapStr);

    }

    private String invokeBody() {
        // TODO Auto-generated method stub
        JspFragment body=this.getJspBody();
        StringWriter sw=new StringWriter();
        if(body!=null){
            try {
                body.invoke(sw);
            } catch (JspException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return sw.toString();
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

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }
}

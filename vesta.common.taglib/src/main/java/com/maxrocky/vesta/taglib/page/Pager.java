package com.maxrocky.vesta.taglib.page;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by JillChen on 2015/12/14.
 */
public class Pager extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String params;
    private Integer curPage;
    private Integer totalPage;
    private Integer pageSize =10;
    private Integer totalCount = 0;
    private String formId;

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    /***
     * javascript:void(0);是去掉#很有必要
     */
    public int doStartTag() throws JspException {
        StringBuffer buffer=new StringBuffer();
        JspWriter out = pageContext.getOut();

        int pageNumber = 0;
        if (totalPage % pageSize == 0) {
            pageNumber = totalPage / pageSize;
        } else {
            pageNumber = (totalPage / pageSize) + 1;
        }
        if (curPage < 1) {
            curPage = 1;
        }

        try {
            if (pageNumber > 0) {
                buffer.append("<script type='text/javascript'>");//script-Start
                buffer.append("function go(pageNum)");
                buffer.append("{");//{start
                buffer.append("var f = document.getElementById('" + formId + "');");
                buffer.append("f.action = f.action + '?pageNum=' + pageNum + '&pageSize="+pageSize + "';");
                buffer.append("f.submit();" );
                buffer.append("}");//}end
                buffer.append("</script>");//script-end

                out.print(buffer.toString());

                out.append("<div class='page-number-strip' style='height:62px;'> ");//page-number-strip
                out.print("<ul class='pagination'>");//添加Bootstrap分页的样式pagination
                int start = 1;
                int end = totalPage;
                for (int i = 4; i >= 1; i--) {
                    if ((curPage - i) >= 1) {
                        start = curPage - i;
                        break;
                    }
                }
                for (int i = 4; i >= 1; i--) {
                    if ((curPage + i) <= totalPage) {
                        end = curPage + i;
                        break;
                    }
                }
                // 如果小于9则右侧补齐
                if (end - start + 1 <= 9) {
                    Integer padLen = 9 - (end - start + 1);
                    for (int i = padLen; i >= 1; i--) {
                        if ((end + i) <= totalPage) {
                            end = end + i;
                            break;
                        }
                    }
                }

                // 如果还小于9左侧补齐
                if (end - start + 1 <= 9) {
                    Integer padLen = 9 - (end - start + 1);
                    for (int i = padLen; i >= 1; i--) {
                        if ((start - i) >= 1) {
                            start = start - i;
                            break;
                        }
                    }
                }

                if (curPage > 1) {
                    if (start > 1) {
                        out.print("<li><a href='javascript:go(1)'>首页</a></li>");
                    }
                    out.print("<li><a href='javascript:go(" + (curPage - 1) + ")'>上一页</a></li>");
                }

                for (int i = start; i <= end; i++) {
                    if (i == curPage) {
                        out.print("<li class='active'><a href='javascript:void(0);'>" + i + "</a></li>");
                    } else {
                        out.print("<li><a href='javascript:go(" + i + ")'>" + i + "</a></li>");
                    }
                }
                if (curPage < totalPage) {
                    out.print("<li><a href='javascript:go(" + (curPage + 1) + ")'>下一页</a></li>");
                    if (end < totalPage) {
                        out.print("<li><a href='javascript:go(" + totalPage + ")'>尾页</a></li>");
                    }
                }
                out.print("<li><a href='javascript:void(0)'>共" + totalPage + "页" + this.totalCount + "条</a></li>");
                out.print("</ul>");
                out.print("</div>");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.doStartTag();

    }

    /***
     * 获取页面的参数
     * @param 格式(parms="username=${user.username})
     * @return
     */
    public String getParms() {

        StringBuffer buffer=new StringBuffer();
        if(params!=null && params.length()>0){
            //分离参数 A=b
            String[] parmsArr=params.split("&");
            for(int i=0;i<parmsArr.length;i++){
                String parmstemp=parmsArr[i];
                String[] parmsEqArr=parmstemp.split("=");
                //分离参数 键 值
                try {
                    buffer.append(i > 0 ? "&" : "");
                    buffer.append(parmsEqArr[0]).append("=");
                    if(parmsEqArr.length>1){
//                        ps.append(URLDecoder.decode(parmsEqArr[1], "UTF-8"));
//                        buffer.append(CharacterEncode.URLEncoder(parmsEqArr[1]));
                    }
                } catch (Exception e) {
                    return "";
                }
            }
        }

        return buffer.toString();
    }
    public static Integer getStartIndex(Integer pageNum, Integer pageSize) {
        Integer res = 0;
        if (pageNum > 0) {
            res = (pageNum - 1) * pageSize;
        }
        return res;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

}

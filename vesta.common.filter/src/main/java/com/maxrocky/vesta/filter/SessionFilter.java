package com.maxrocky.vesta.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ZhangBailiang on 2016/3/16.
 * 登陆过滤
 */
public class SessionFilter extends OncePerRequestFilter {
    /*
    * 登陆过滤
    * @see
    * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
    * javax.servlet.http.HttpServletRequest,
    * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
    */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 不过滤的uri
        String[] notFilter = new String[] { "index.html","authLoginCheck","loginCheck.html","indexForSSO.html","LoginLight.aspx" ,
                "/static/editer/jsp/controller.jsp","/property/addPropertyAnnouncement","static","/user/ownerManage.html","/errors","/Basic"};
        // 请求的uri
        String uri = request.getRequestURI();
        try {
            // 是否过滤
            boolean doFilter = true;
            for (String s : notFilter) {
                if (uri.indexOf(s) != -1) {
                    // 如果uri中包含不过滤的uri，则不进行过滤
                    doFilter = false;
                    break;
                }
            }
            if (uri.equals("/")){
                doFilter = false;
            }

            if (doFilter) {
                // 执行过滤
                // 从session中获取登录者实体
                Object obj = request.getSession().getAttribute("propertystaff");
                Object authObj = request.getSession().getAttribute("authPropertystaff");
                if (null == obj && authObj == null) {
                    // 如果session中不存在登录者实体，则弹出框提示重新登录
                    // 设置request和response的字符集，防止乱码
                    request.setCharacterEncoding("gbk");
                    response.setContentType("text/html;charset=gbk");
                    PrintWriter out = response.getWriter();
                    String loginPage = request.getContextPath()+"/errors/404.jsp";
                    StringBuilder builder = new StringBuilder();
                    builder.append("<script type=\"text/javascript\">");
                    /*   builder.append("alert('网页过期，请重新登录！');");*/
                    builder.append("window.top.location.href='");
                    builder.append(loginPage);
                    builder.append("';");
                    builder.append("</script>");
                    out.print(builder.toString());
                } else {
                    // 如果session中存在登录者实体，则继续
                    filterChain.doFilter(request, response);
                }
            } else {
                // 如果不执行过滤，则继续
                filterChain.doFilter(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 不过滤的uri
        String[] notFilter = new String[] { "index.html","loginCheck.html","indexForSSO.html","LoginLight.aspx" ,
                "/static/editer/jsp/controller.jsp","/property/addPropertyAnnouncement","static","/user/ownerManage.html"};

        // 请求的uri
        String uri = request.getRequestURI();

        *//**
         * uri中包含以下模块时进行过滤
         * property   物业模块
         * advert     广告模块
         * circle     俱乐部模块
         * community  活动模块
         * activities 分享活动模块
         * seller     商户模块
         * billInfo   发票信息
         * role       角色模块
         * click      各项目点击量
         * statistics 统计模块
         * equip      设备统计
         * system     部门管理
         * user       人员管理
         * userFeed   登录人统计
         * log        登录日志
         * message    通知管理
         * operation  核心日志
         * startPage  启动页
         * userFeed   意见反馈
         * overview   金茂楼盘
         * communityNews    品牌咨询
         * vote       投票管理
         * userAppoint  业主预约
         * deliveryPlan  交房计划
         * staffUser  账号管理
         * memberAuthority   角色管理
         * navigationMenu  导航菜单管理
         * defaultConfig   默认配置
         * smsMessage      短信消息提醒
         * notification    通知管理
         *//*

        try {
            if (uri.indexOf("/property") != -1 ||  uri.indexOf("/advert") != -1 ||  uri.indexOf("/circle") != -1 ||
                    uri.indexOf("/community") != -1 ||  uri.indexOf("/activities") != -1 ||  uri.indexOf("/seller") != -1 ||
                    uri.indexOf("/billInfo") !=-1 || uri.indexOf("/role") != -1 || uri.indexOf("/click") != -1 ||
                    uri.indexOf("/statistics") != -1 || uri.indexOf("/equip") !=-1 || uri.indexOf("/system") != -1 ||
                    uri.indexOf("/user") !=-1 || uri.indexOf("/userFeed") != -1 || uri.indexOf("/log") != -1 ||
                    uri.indexOf("/message") !=-1 || uri.indexOf("/operation") !=-1 || uri.indexOf("/startPage") !=-1 ||
                    uri.indexOf("/userFeed") !=-1 || uri.indexOf("/announcement") !=-1 || uri.indexOf("/overview") !=-1 ||
                    uri.indexOf("/communityNews") !=-1 || uri.indexOf("/vote") !=-1 || uri.indexOf("/userAppoint") !=-1 ||
                    uri.indexOf("/deliveryPlan") !=-1 || uri.indexOf("/staffUser") !=-1 || uri.indexOf("/memberAuthority") !=-1 ||
                    uri.indexOf("/navigationMenu") !=-1 || uri.indexOf("/defaultConfig") !=-1 || uri.indexOf("/smsMessage") !=-1 ||
                    uri.indexOf("/notification") != -1 ) {
                // 是否过滤
                boolean doFilter = true;
                for (String s : notFilter) {
                    if (uri.indexOf(s) != -1) {
                        // 如果uri中包含不过滤的uri，则不进行过滤
                        doFilter = false;
                        break;
                    }
                }
                if (doFilter) {
                    // 执行过滤
                    *//* 暂不需要(白屏原因可能并不在这)
                    if (request.getSession(false) == null){
                        // 如果session中不存在登录者实体，则弹出框提示重新登录
                        // 设置request和response的字符集，防止乱码
                        request.setCharacterEncoding("gbk");
                        response.setContentType("text/html;charset=gbk");
                        PrintWriter out = response.getWriter();
                        String loginPage = request.getContextPath()+"/errors/404.jsp";
                        StringBuilder builder = new StringBuilder();
                        builder.append("<script type=\"text/javascript\">");
                 *//**//*   builder.append("alert('网页过期，请重新登录！');");*//**//*
                        builder.append("window.top.location.href='");
                        builder.append(loginPage);
                        builder.append("';");
                        builder.append("</script>");
                        out.print(builder.toString());
                    }
                    *//*
                    // 从session中获取登录者实体
                    Object obj = request.getSession().getAttribute("propertystaff");
                    if (null == obj) {
                        // 如果session中不存在登录者实体，则弹出框提示重新登录
                        // 设置request和response的字符集，防止乱码
                        request.setCharacterEncoding("gbk");
                        response.setContentType("text/html;charset=gbk");
                        PrintWriter out = response.getWriter();
                        String loginPage = request.getContextPath()+"/errors/404.jsp";
                        StringBuilder builder = new StringBuilder();
                        builder.append("<script type=\"text/javascript\">");
                 *//*   builder.append("alert('网页过期，请重新登录！');");*//*
                        builder.append("window.top.location.href='");
                        builder.append(loginPage);
                        builder.append("';");
                        builder.append("</script>");
                        out.print(builder.toString());
                    } else {
                        // 如果session中存在登录者实体，则继续
                        filterChain.doFilter(request, response);
                    }
                } else {
                    // 如果不执行过滤，则继续
                    filterChain.doFilter(request, response);
                }
            } else {
                // uri中包含property 物业模块，则继续
                filterChain.doFilter(request, response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    */
}

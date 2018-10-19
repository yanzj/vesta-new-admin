package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.SystemLogDTO;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by chen on 2016/7/19.
 */
public interface SystemLogService {

    //根据条件获取系统日志列表
    List<SystemLogDTO> getSystemLogList(SystemLogDTO systemLogDTO,WebPage webPage);
    //根据条件获取用户访问日志列表
    List<SystemLogDTO> getuserVisitlLogList(SystemLogDTO systemLogDTO,WebPage webPage);
    //根据条件获取用户单据日志列表
    List<SystemLogDTO> getuserDocumentsLogList(SystemLogDTO systemLogDTO,WebPage webPage);
    //根据条件获取信息发布日志列表
    List<SystemLogDTO> getInfoReleaseLogList(SystemLogDTO systemLogDTO,WebPage webPage);

    /**
     * 优化新增用户日志列表导出EXCEL
     */
    void addUserLogExportExcel2(String title, String[] headers, ServletOutputStream out,SystemLogDTO systemLogDTO) throws Exception;

    String addUserLogExportExcel(SystemLogDTO systemLogDTO,HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;

    /**
     * 优化用户访问日志列表导出EXCEL
     */
    void userVisitLogExportExcel2(String title, String[] headers, ServletOutputStream out,SystemLogDTO systemLogDTO) throws Exception;

    String userVisitLogExportExcel(SystemLogDTO systemLogDTO,HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;

    String userDocumentsLogExportExcel(SystemLogDTO systemLogDTO,HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;

    /**
     * 优化信息发布日志列表导出EXCEL
     */
    void infoReleaseLogExportExcel2(String title, String[] headers, ServletOutputStream out,SystemLogDTO systemLogDTO) throws Exception;

    String infoReleaseLogExportExcel(SystemLogDTO systemLogDTO,HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;

    /**
     * 清除新增用户日志重复数据
     */
    void cleanSystemLogRepeatData();
}

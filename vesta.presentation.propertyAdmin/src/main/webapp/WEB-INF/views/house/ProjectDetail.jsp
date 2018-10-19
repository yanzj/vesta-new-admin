<%--
  Created by IntelliJ IDEA.
  User: zhanghja
  Date: 2016/1/28
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%
  List<Viewmodel> menulist = (List<Viewmodel>) session.getAttribute("menulist");
  List<Viewmodel> secanViewlist = (List<Viewmodel>) session.getAttribute("secanViewlist");

%>

<!DOCTYPE HTML>
<html>
<head>
  <title><spring:message code="sys.tital"/></title>
  <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta name="keywords" content=""/>
  <script type="application/x-javascript"> addEventListener("load", function () {
    setTimeout(hideURLbar, 0);
  }, false);
  function hideURLbar() {
    window.scrollTo(0, 1);
  } </script>
  <!-- Bootstrap Core CSS -->
  <link href="../static/css/bootstrap.css" rel='stylesheet' type='text/css'/>
  <!-- Custom CSS -->
  <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>
  <style>
    label.error {
      margin-left: 1%;
      color: red;
    }
  </style>

  <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
  <!-- font CSS -->
  <!-- font-awesome icons -->
  <link href="../static/css/font-awesome.css" rel="stylesheet">
  <!-- //font-awesome icons -->
  <link href="../static/css/userOrganization.css" rel="stylesheet" type="text/css">
  <!-- js-->
  <script src="../static/js/jquery-1.11.1.min.js"></script>
  <script src="../static/js/modernizr.custom.js"></script>
  <!--animate-->
  <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
  <script src="../static/js/wow.min.js"></script>
  <script>
    $(function(){
      console.log("sqq")
      $("#001400010000").addClass("active");
      $("#001400010000").parent().parent().addClass("in");
      $("#001400010000").parent().parent().parent().parent().addClass("active");
    })
    new WOW().init();
  </script>
  <!--//end-animate-->
  <!-- Metis Menu -->
  <script src="../static/js/metisMenu.min.js"></script>
  <script src="../static/js/custom.js"></script>
  <link href="../static/css/custom.css" rel="stylesheet">
  <!--//Metis Menu -->
</head>

<body class="cbp-spmenu-push">
<div>

  <vl:leftmenu  sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="001400010000" username="${propertystaff.staffName}" />
  <div class="container1 userStaffManage">
      <div class="row">
        <div class="col-md-10 role_new_submit">
          <div class="newRoleSubmit">
            <a  href="../project/updateProject.html?projectId=${project.id}" class="btn btn-primary" for="" >编辑</a>
            <a onclick="javascript :history.back(-1);" class="btn btn-primary" >关闭</a>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-md-10 role_new_submit2">
          <table class="table table-bordered">
            <caption class="role_table_title">项目详情</caption>
            <input type="hidden" name="id" value="${project.id}">
            <tbody>
            <tr>
              <td class="role_table_roleName">项目别名</td>
              <td class="role_table_fillCont">
                ${project.name}
              </td>
            </tr>
            <tr>
              <td class="role_table_titleable_roleName">CRM项目名</td>
              <td class="role_table_fillCont">
                ${project.originName}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">备注</td>
              <td class="role_table_fillCont">
                ${project.memo}
              </td>
            </tr>
            <tr>
              <td class="role_table_roleName">最后修改时间</td>
              <td class="role_table_fillCont">
                ${project.modifyOn}
              </td>
            </tr>
            <tr style="background-color: #F2F2F2"><td colspan="2">质量管理功能模块相关信息</td></tr>
            <tr>
              <td class="role_table_roleName"><span>交验房:数据查看</span></td>
              <td>

                <div class="existence">
                                  <span style="display:inline-block" class="selectResult" id="role_linkRole">
                                    <c:forEach items="${views}" var="view">
                                      ${view.agencyName};
                                    </c:forEach>
                                  </span>
                </div>
              </td>
            </tr>
            <!-- 仅查看该项目下自己创建的整改单和新增权限-->
            <tr>
              <td class="role_table_roleName"><span>交验房:验房工程师</span></td>
              <td>

                <div class="existence">
                                  <span style="display:inline-block" class="selectResult7" id="role_linkCheck">
                                    <c:forEach items="${checkInspect}" var="inspect">
                                      ${inspect.agencyName};
                                    </c:forEach>
                                  </span>
                </div>
              </td>
            </tr>
            <!-- 查看该项目下所有数据 拥有处理、关单、废弃权限 -->
            <tr>
              <td class="role_table_roleName"><span>交验房:质量经理</span></td>
              <td>
                <div class="existence">
                                  <span style="display:inline-block" class="selectResult8" id="role_linkQuality">
                                    <c:forEach items="${qualityManager}" var="inspect">
                                      ${inspect.agencyName};
                                    </c:forEach>
                                  </span>
                </div>
              </td>
            </tr>

            <!-- 仅查看该项目下报修模块下数据  不能对单据进行操作 -->
            <tr>
              <td class="role_table_roleName"><span>报修:数据查看</span></td>
              <td>
                <!-- <span class="existence" ></span> -->
                <div class="existence">
                                  <span style="display:inline-block" class="selectResultRepair1"
                                        id="role_linkRepair1">
                                    <c:forEach items="${Repair1Objective}" var="objective">
                                      ${objective.agencyName};
                                    </c:forEach>
                                  </span>
                </div>
              </td>
            </tr>
            <!--  可查看本组内及派给自身的所有单据，拥有填报权限 -->
            <tr>
              <td class="role_table_roleName"><span>报修:物业接单</span></td>
              <td>
                <!-- <span class="existence" ></span> -->
                <div class="existence">
                                  <span style="display:inline-block" class="selectResult3" id="role_linkProperty">
                                    <c:forEach items="${properties}" var="property">
                                      ${property.agencyName};
                                    </c:forEach>
                                  </span>
                </div>
              </td>
            </tr>
            <!--  可查看本组内及派给自身的所有单据，拥有填报权限 -->
            <tr>
              <td class="role_table_roleName"><span>报修:开发接单</span></td>
              <td>

                <!-- <span class="existence" ></span> -->
                <div class="existence">
                                  <span style="display:inline-block" class="selectResult4" id="role_linkMake">
                                    <c:forEach items="${makes}" var="make">
                                      ${make.agencyName};
                                    </c:forEach>
                                  </span>
                </div>
              </td>
            </tr>
            <%--查看有权限项目下所有报修单据并可进行派单，填报权限--%>
            <tr>
              <td class="role_table_roleName"><span>报修:项目前台</span></td>
              <td>

                <!-- <span class="existence" ></span> -->
                <div class="existence">
                                  <span style="display:inline-block" class="selectResultReception"
                                        id="role_linkReception">
                                    <c:forEach items="${ReceptionObjective}" var="objective">
                                      ${objective.agencyName};
                                    </c:forEach>
                                  </span>
                </div>
              </td>
            </tr>

            <%--	仅查看有权限项目下工单管理-投诉管理问题进行查看无处理权限--%>
            <tr>
              <td class="role_table_roleName"><span>投诉:数据查看</span></td>
              <td>
                <!-- <span class="existence" ></span> -->
                <div class="existence">
                                  <span style="display:inline-block" class="selectResultComplaint"
                                        id="role_linkComplaint">
                                    <c:forEach items="${ComplaintObjective}" var="objective">
                                      ${objective.agencyName};
                                    </c:forEach>
                                  </span>
                </div>
              </td>
            </tr>


            <%--	仅查看有权限项目下工单管理-投诉管理问题进行查看无处理权限--%>
            <tr>
              <td class="role_table_roleName"><span>投诉:对接人</span></td>
              <td>
                <!-- <span class="existence" ></span> -->
                <div class="existence">
                                  <span style="display:inline-block" class="selectResultComplaintss"
                                        id="role_linkComplaintsButt">
                                    <c:forEach items="${ComplaintsButt}" var="objective">
                                      ${objective.agencyName};
                                    </c:forEach>
                                  </span>
                </div>
              </td>
            </tr>

            <tr>
              <td class="role_table_roleName"><span>投诉:项目前台</span></td>
              <td>

                <!-- <span class="existence" ></span> -->
                <div class="existence">
                                  <span style="display:inline-block" class="selectResultDispatch"
                                        id="role_linkDispatch">
                                    <c:forEach items="${dispatchSheet}" var="dispatch">
                                      ${dispatch.agencyName};
                                    </c:forEach>
                                  </span>
                </div>
              </td>
            </tr>
            <%--总部客观具有废弃投诉单权限--%>
            <tr>
              <td class="role_table_roleName"><span>投诉:总部客关</span></td>
              <td>
                <!-- <span class="existence" ></span> -->
                <div class="existence">
                                  <span style="display:inline-block" class="selectResultHQ"
                                        id="role_linkHQ">
                                    <c:forEach items="${HQObjective}" var="objective">
                                      ${objective.agencyName};
                                    </c:forEach>
                                  </span>
                </div>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
  </div>
</div>
</div>
</div>

<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
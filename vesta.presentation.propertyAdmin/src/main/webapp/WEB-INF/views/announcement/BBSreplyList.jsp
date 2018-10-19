<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/vestalib-tags" prefix="vl" %>
<%@ taglib prefix="m" uri="/morinda-tags" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maxrocky.vesta.taglib.vesta.Viewmodel" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
    <link href="../static/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <!-- Custom CSS -->
    <link href="../static/css/style.css" rel='stylesheet' type='text/css'/>


    <script type="text/javascript" charset="utf-8">
        window.UEDITOR_HOME_URL = "/static/editer/"; //UEDITOR_HOME_URL、config、all这三个顺序不能改变
    </script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/editer/ueditor.all.min.js"></script>

    <link href="../static/css/page/page.css" rel='stylesheet' type='text/css'/>
    <!-- font CSS -->
    <!-- font-awesome icons -->
    <link href="../static/css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <!-- js-->
    <script src="../static/js/jquery-1.11.1.min.js"></script>
    <script src="../static/js/modernizr.custom.js"></script>
    <!--animate-->
    <link href="../static/css/animate.css" rel="stylesheet" type="text/css" media="all">
    <script src="../static/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//end-animate-->
    <!-- Metis Menu -->
    <script src="../static/js/metisMenu.min.js"></script>
    <script src="../static/js/custom.js"></script>
    <link href="../static/css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <script src="../static/property/js/propertyHousPay.js"></script>
</head>

<body class="cbp-spmenu-push">
<div class="main-content">

    <vl:leftmenu sysTitle="Vesta Dashboard" menulist="<%=menulist%>" secanViewlist="<%=secanViewlist%>" crunMenu="005500020000" username="${propertystaff.staffName}"/>


    <div class="forms">
        <div class="widget-shadow " data-example-id="basic-forms">
            <%--搜索条件开始--%>
            <div class="form-body">
                <!-- 标题 -->
                <div class="form-group  col-lg-12">
                    <label for="title" class="col-sm-1 control-label"><spring:message
                            code="announcementDTO.title"/></label>

                    <div class="col-sm-8">
                        <input type="text" class="form-control" placeholder="" id="title"
                               name="title" value="${announcementEntity.title}" readonly>
                    </div>
                    <%-- 新闻编辑--%>
                    <%--
                    <div class="form-group col-lg-12" style="z-index:0">
                        <label class="control-label">
                            &lt;%&ndash;<spring:message code="bbsReply.replyContent"/>&ndash;%&gt;
                        </label>
                        <script id="editor" type="text/plain"
                                style="overflow-y:auto;">
                        </script>
                    </div>
                    --%>
                </div>
                <%--
                <div>
                    <form:form action="../replyDetail/createReply" modelAttribute="BBSReply" id="bsForm" name="addReply">
                        <label>回复内容：</label><br/>
                        <form:textarea rows="5" cols="70" path="content" />
                        <form:input path="topicId" type="hidden" value="${BBStopic.id}" />
                        <form:input path="replyUserId" type="hidden" value="${BBStopic.userId}" />
                        <form:input path="replyId" type="hidden" value="${BBStopic.id}" />
                        <input type="submit" id="add" class="btn btn-danger" value="提交" />
                    </form:form>
                </div>
                --%>
                <a href="../announcement/List.html" class="btn btn-primary" for=""><spring:message code="common_back"/></a>
            </div>
            <div class='clearfix'></div>
            <%--搜索条件结束--%>
        </div>
    </div>
    <div class="table-responsive bs-example widget-shadow">
        <table width="100%" class="tableStyle">
            <thead>
            <tr>
                <%--<td style="text-align:center;" width="100"><spring:message code="propertyAnnouncement.serialNumber"/></td>--%>
                <th style="text-align:center;" width="150">楼层</th>
                <th style="text-align:center;" width="200"><spring:message code="bbsReply.userNickName"/></th>
                <th style="text-align:center;"><spring:message code="bbsReply.replyContent"/></th>
                <th style="text-align:center" width="200">回复内容</th>
                <th style="text-align:center;" width="200"><spring:message code="bbsReply.replyDate"/></th>
                <th style="text-align:center;" width="300 "><spring:message code="billInfo.opera"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${announcementReplyEntitys}" var="announcementReplyEntitys" varStatus="row">
                <tr>
                    <%--<td style="text-align:center;"><b>${row.index + 1}</b></td>--%>
                    <td style="text-align:center;">${announcementReplyEntitys.floor}
                        <spring:message code="bbsReply.level"></spring:message></td>
                    <td style="text-align:center;">${announcementReplyEntitys.userNickName}</td>
                    <td style="text-align:center;">${announcementReplyEntitys.content}</td>
                    <td style="text-align:center;">${announcementReplyEntitys.replyContent}</td>
                    <td style="text-align:center;">
                        <fmt:formatDate value="${announcementReplyEntitys.createOn}" pattern="yyyy-MM-dd hh:mm:ss"/>
                    </td>
                        <%--  <td>
                              <c:if test="${announcementDTOs.releaseStatus eq 0 }"><spring:message
                                      code="activityManage.activityStatus_5"/></c:if>
                              <c:if test="${announcementDTOs.releaseStatus eq 1 }"><spring:message
                                      code="activityManage.activityStatus_6"/></c:if>
                          </td>--%>
                    <td>

                    <span style="float: right">
                            <a onClick="javascript:if (confirm('确定修改吗？')) location.href='../replyDetail/updateReply.html?id=${announcementReplyEntitys.id}&topicId=${announcementReplyEntitys.topicId}&status=1';else return;">
                            <c:if test="${announcementReplyEntitys.status=='1'}">禁用</c:if>
                            <c:if test="${announcementReplyEntitys.status=='2'}">启用</c:if>
                        </a>
                    <a onClick="javascript:if (confirm('确定删除吗？')) location.href='../replyDetail/updateReply?id=${announcementReplyEntitys.id}&topicId=${announcementReplyEntitys.topicId}&status=2';else return;">
                        删除</a>&nbsp;
                    ${announcementReplyEntitys.createOn}&nbsp;
                    <button class="btn" id="${announcementReplyEntitys.id}2" onclick="tog('${announcementReplyEntitys.id}'+2,'${announcementReplyEntitys.id}'+1,'${announcementReplyEntitys.id}');">回复
                    </button><button class="btn" id="${announcementReplyEntitys.id}1"
                                     onclick="tog('${announcementReplyEntitys.id}'+2,'${announcementReplyEntitys.id}'+1,'${announcementReplyEntitys.id}');" style="display: none">
                        收起回复
                    </button>
                       <form:form action="../replyDetail/createReply" modelAttribute="BBSReply" id="${announcementReplyEntitys.id}3"
                                  name="addpReply">
                           <form:input path="topicId1" type="hidden" value="${announcementReplyEntitys.topicId}"/>
                           <form:input path="replyUserId1" type="hidden" value="${announcementReplyEntitys.userId}"/>
                           <form:input path="replyId1" type="hidden" value="${announcementReplyEntitys.id}"/>
                           <div id="${announcementReplyEntitys.id}" style="display: none">
                               <form:textarea rows="2" cols="40" path="content1"/>
                               <input type="submit" value="回复" onclick="vald('${announcementReplyEntitys.id}'+3)"/>
                           </div>
                       </form:form>
                    </span>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <m:pager pageIndex="${webPage.pageIndex}"
                 pageSize="${webPage.pageSize}"
                 recordCount="${webPage.recordCount}"
                 submitUrl="${pageContext.request.contextPath }/replyDetail/BBSreplyList.html?pageIndex={0}&topicId=${announcementEntity.id}"/>
    </div>

</div>
</div>
</div>
<script type="text/javascript">
    var title = "${announcementDTO.title}";
    var content = "${announcementDTO.content}";
    var releasePerson = ${announcementDTO.releasePerson};
    var releaseStatus = "${announcementDTO.releaseStatus}";
    var createDate = "${announcementDTO.createDate}";

    var staDate = "${communityOverviewDto.staDate}";
    var endDate = "${communityOverviewDto.endDate}";

</script>
<script type="text/javascript" src="../static/plus/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="../static/js/bootstrap-datetimepicker.zh-CN.js"
        charset="UTF-8"></script>
<!--富文本控件-->
<script type="text/javascript">

    //实例化编辑器 ------
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor', {
                //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
                toolbars: [[
                    /*"fullscreen", "source", "|", "undo", "redo", "|",
                     "bold", "italic", "underline", "fontborder", "strikethrough", "superscript", "subscript", "removeformat", "formatmatch", "autotypeset", "blockquote", "pasteplain", "|", "forecolor", "backcolor", "insertorderedlist", "insertunorderedlist", "selectall", "cleardoc", "|",
                     "rowspacingtop", "rowspacingbottom", "lineheight", "|",
                     "customstyle", "paragraph", "fontfamily", "fontsize", "|",
                     "directionalityltr", "directionalityrtl", "indent", "|",
                     "justifyleft", "justifycenter", "justifyright", "justifyjustify", "|", "touppercase", "tolowercase", "|",
                     "link", "unlink", "anchor", "|", "imagenone", "imageleft", "imageright", "imagecenter", "|",
                     "simpleupload", "insertimage", "emotion", "scrawl", "insertvideo", "music", "attachment", "map", "gmap", "insertframe", "insertcode", "pagebreak", "template", "background", "|",
                     "horizontal", "date", "time", "spechars", "snapscreen", "wordimage", "|",
                     "inserttable", "deletetable", "insertparagraphbeforetable", "insertrow", "deleterow", "insertcol", "deletecol", "mergecells", "mergeright", "mergedown", "splittocells", "splittorows", "splittocols", "charts", "|",
                     "searchreplace", "help", "drafts"*/
                ]],
                //focus时自动清空初始化时的内容
                autoClearinitialContent: false,
                //关闭字数统计
                wordCount: true,
                //关闭elementPath
                elementPathEnabled: true,
                //默认的编辑区域高度
                initialFrameHeight: 100,
                maximumWords: 1400,
                //更多其他参数，请参考ueditor.config.js中的配置项
                readonly: true,
                initialContent: '${announcementEntity.content}'

            }
    );


    function isFocus(e) {
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e) {
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }

    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        <%--arr.push("${merDesc}123");--%>
        UE.getEditor('editor').setContent('${merDesc}', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData() {
        alert(UE.getEditor('editor').execCommand("getlocaldata"));
    }

    function clearLocalData() {
        UE.getEditor('editor').execCommand("clearlocaldata");
        alert("已清空草稿箱")
    }

    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function (action) {
        if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadimage') {
            return '${baseUrl}../communityNews/uploadImage.html?action=uploadimage';
            // return '${baseUrl}../ueditor/fileupload/upload.html';
        } else if (action == 'uploadvideo') {
            return '${baseUrl}../communityNews/uploadImage.html?action=uploadimage';
            // return '${baseUrl}../ueditor/fileupload/upload.html';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }

</script>
<script type="text/javascript">
    $(function(){
//        console.log(111)
        $("#005500020000").addClass("active");
        $("#005500020000").parent().parent().addClass("in");
        $("#005500020000").parent().parent().parent().parent().addClass("active");

    })
    function js_del(id) {
        myvalue = confirm("确定要删除吗?");
        if (myvalue == true) {
            $.ajax({
                type: "POST",
                url: "../announcement/deleteAnnouncementVote",
                cache: false,
                data: "id=" + id + "&&" + "select=cancelStatus" + "&&" + "flag=" + 0,
                success: function (data) {
                    if (data.code == '0') {
                        alert(data);
                        alert(data.code);
                        $("#form").submit();
                    } else {
                        alert("对不起，操作失败");
                    }
                }
            });
        }
    }

    function tog(idx, idy, idz) {
        $("#" + idx).toggle();
        $("#" + idy).toggle();
        $("#" + idz).slideToggle("slow");
    }
    function vald(idf){
        $("#"+idf).validate({
            rules: {
                content1: {
                    required: true,
                    minlength: 1,
                    maxlength: 300
                }
            },
            messages: {
                content1: {
                    required: "请输入内容！",
                    minlength: "不能少于1个字符！",
                    maxlength: "请勿超过300个字！"
                }
            }
        });
        if($("#"+idf).valid()){
            $("#"+idf).submit();
        }else{
            return false;
        }
    }
</script>
<!-- main content end-->
<%@ include file="../main/foolter.jsp" %>
</div>

</body>
</html>
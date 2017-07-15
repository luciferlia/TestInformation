<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<base href="<%=basePath%>">

<title>与德测试信息管理系统</title>

<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange1.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
  <script src="script/ie.js"></script>
<!--[if lt IE 7 ]>alert("当前浏览器版本过低，请切换成极速模式或升级版本");<![endif]-->
<style type="text/css">
#table td {
	border: 1px solid #EDEDED;
	font-color: black;
	text-align: center;
	vertical-align: middle;
	font-size: 0.9em;
}

#sp {
	float: right;
}
.mydiv  td{font-size:0.9em;}
.mydiv input{width:150px;}


a:hover {
	color: red;
}

li {
	list-style: none;
}
td{font-size:0.9em;}

</style>
<script>
	layui.use('layer', function() {
		var layer = layui.layer;

	});
</script>
<script>
	$(function() {
		var all_checked = false;
		$(":checkbox").click(function() {
			var table = $(this).parents("table");
			if ($(this).attr("id") === "all") {
				table.find(":checkbox").prop("checked", !all_checked);
				all_checked = !all_checked;
			} else {
				table.find(":checkbox[id!=all]").each(function(i) {
					if (!$(this).is(":checked")) {
						table.find("#all").prop("checked", false);
						all_checked = false;
						return false;
					}
					$("#all").prop("checked", true);
					all_checked = true;
				});
			}
		});
	});
</script>
</head>
<body>

	
	<h3 style="margin-left:5%;margin-top:1%"
		class="glyphicon glyphicon-tasks">我负责的项目</h3>
	<hr />
	<c:if test="${not empty pageBean.list }">
		<table id="table"
			style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;"
			class="table table-condensed table-bordered table-hover table-striped">
			<tr style="background-color:#4682B4;height:40px;color: white;">
				<td style="text-align:center;vertical-align:middle;width:80px;">序号</td>
				<td><h4>项目名称</h4></td>
				<td><h4>客户名称</h4></td>
				</tr>
				<% int i=1; %>
			<s:iterator value="pageBean.list">
				<tr style="height:40px;">
				   <td style="text-align:center;vertical-align:middle;"><%=i++ %></td>
					<td>
						<s:if test="%{tag==1}">
						<a href="task_showMasterTask?id=<s:property value="id"/>&page=1"><s:property value="projectName"/></a>
						</s:if>
						<s:if test="%{tag==2}">
						<a href="task_showTakSchedule?id=<s:property value="id"/>&page=1"><s:property value="projectName"/></a>
						</s:if>
						<s:if test="%{tag==3}">
						<a href="task_showHistoryTask?id=<s:property value="id"/>&page=1"><s:property value="projectName"/></a>
						</s:if>
					</td>
					<td><s:property value="customer" /></td>
				</tr>
		</s:iterator>
		</table>
		<table style="width:80%;margin-left:5%;">
			<tr>
				<td><span style="float:left;">共<span><s:property
								value="pageBean.totalPage" /></span>页|第<span><s:property
								value="pageBean.currentPage" /> </span>页
				</span></td>
				<td><span style="float:right;"><form
							action="task_showMyProject" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="task_showMyProject?page=1" class="right-font08">首页</a> | <a
									href="task_showMyProject?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="task_showMyProject?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="task_showMyProject?page=<s:property value="%{pageBean.totalPage}"/> "
									class="right-font08">末页</a>
							</s:if>
							<s:else>下一页 | 末页</s:else>
							] 转至：<select name="page" style="width:50px;">
								<s:iterator value="pageBean.count">

									<option value="<s:property value="count"/>"><s:property
											value="count" />
									</option>
								</s:iterator>
							</select>
							<%-- <input type="text" style="width:50px;" name="page" /> --%>
							&nbsp;&nbsp;<input type="submit" class="btn btn-primary btn-xs"
								style="border:1px solid black;font-size:0.9em"
								value="确定" />
						</form></span></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${empty pageBean.list }">
		<c:if test="${message=='' }">
		<center style="color:red;font-size:10px;">没有项目信息</center>
		</c:if>
		<c:if test="${message!='' }"><center style="color:red;font-size:10px;">${message }</center></c:if>
	</c:if>
	<%-- 隐藏域表单 --%>
	<form action="" method="post" id="formid">
		<input type="hidden" name="id" id="id" /> <input type="hidden"
			name="checkedIds" id="checkedIds" />
	</form>
</body>
</html>

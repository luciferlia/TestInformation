<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<script src="script/js/bootstrap.min.js"></script>
<style>
.color{
background-color:#4682B4;width:100px;
}

.tt td{border:1px solid black;text-align:center;vertical-align:middle;font-size:0.9em;font-color:black;}
tr{height:50px;}
</style>

</head>

<body>
	
	<form> <h3 class="glyphicon glyphicon-tasks" style="margin-left:5%;margin-top:1%;">项目详情
	</h3>
		<hr/>
		<table class="tt"
			style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;color: white;">
			<tr>
				<td class="color">项目名称</td>				
				<td>${sessionScope.project.projectName }</td>
				<td class="color">客户名称</td>
				<td>${sessionScope.project.customer }</td>
				</tr>
				<tr>
				<td class="color">项目等级</td>
				<td>${sessionScope.project.level }</td>
				<td class="color">项目阶段</td>
				<td>${sessionScope.project.testPhase }</td>
				</tr>
				<tr>
				<td class="color">测试经理</td>
				<td>${sessionScope.project.vpmName }</td>
				<td class="color">创建时间</td>				
				<td>${sessionScope.project.buildTime}</td>
				</tr>
				<tr>
				<td class="color">硬件主测</td>
				<td>${sessionScope.project.hmaster }</td>
				<td class="color">软件主测</td>				
				<td>${sessionScope.project.smaster }</td>
				</tr>
				<tr>
				<td class="color">硬测组长</td>
				<td>${sessionScope.project.htester }</td>
				<td class="color">软测组长</td>				
				<td>${sessionScope.project.stester }</td>
				</tr>
				<tr>		
				<td class="color">备注</td><td colspan="3">${sessionScope.project.remark }</td>
			</tr>

		</table>
	</form>
	
  </body>
</html>

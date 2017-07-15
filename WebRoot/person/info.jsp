<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script src="script/js/bootstrap.min.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>

<style>
#wai {
	border: 1px solid #4682B4;
}
td{font-size:1em;font-color:black;}

</style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  
  <body>
           <table id="wai" style="width:90%;min-width:1000px;height:80%;margin-left:1%;margin-top:1%;background-color:white;min-height:600px;">
		<tr style="background-color:#4682B4;height:40px;">
			<td></td>
		</tr>
		
		<tr>
			<td style="height:50px;">
				
    <h3 style="margin-left:5%; margin-top:1%;" class="glyphicon glyphicon-tasks">${user.name }的信息</h3>
    <hr/></td></tr>
<tr><td>			
				<table style="width:90%;height:90%;;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;margin-left:5%;" class="table table-condensed table-bordered table-hover table-striped">
				<tr>
				<td style="width:30%; text-align:center;vertical-align:middle;" rowspan="4"><img src="images/ren1.gif"></td>
				<td style="width:100px;">所属组</td>
				<td>${user.groupName }</td>
				</tr>
				<tr> <td>职称</td><td>${user.position }</td></tr>
				<tr> <td>状态</td><td><c:if test="${user.state=='0' }">空闲</c:if><c:if test="${user.state=='1' }">忙碌</c:if> </td></tr>
				<tr> <td>性别</td><td>${user.sex }</td></tr>
				<tr>
				 <td rowspan="2" >
								<span style="margin-left:20%">姓名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${user.name }</span><br/>
		     
				<span style="margin-left:20%">电话&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${user.phone }</span>
			</td>
				
				<td>直接上级</td><td>段举</td></tr>
				<tr>
				 
				
				<td>邮箱</td><td>${user.email }</td></tr>
				</table>				
				
				</td></tr></table>
				
					  </body>
</html>

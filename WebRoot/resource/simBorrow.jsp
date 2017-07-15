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
<link rel="stylesheet" href="script/exchange.css">
<jsp:include page="/wind/top.jsp"></jsp:include>
<script src="script/exchange.js"></script>
<style>
body{background-color:#FDF5E6;}
#wai {
	border: 1px solid #4682B4;
}

td {
	font-size: 0.9em;
	font-color: black;
	
}

.box {
	position: relative;
	border: 2px solid #009ACD;
	width: 80%;
	height: 95%;
	margin-left: 60px;
	margin-top: 5px;
}

.box::before {
	content: attr(title);
	position: absolute;
	left: 10%;
	transform: translateX(-50%);
	-webkit-transform: translate(-50%, -50%);
	padding: 0 10px;
	background-color: #fff;
}
</style>
</head>
<body>
   <tr>
	<DIV ><span><A href="../../project/all">资源管理</A>  &gt; <A href="../../resource/deviceApply">SIM卡申请</A></DIV></DIV>   
	</tr>
 	<table id="wai" style="width:90%;height:70%;margin-left:2%;margin-top:2%;background-color:white;">
		<tr style="background-color:#4682B4;height:40px;">
			<td></td>
		</tr>
	<tr>
	<td>
	<div class="box" title="SIM卡借用信息">
      <table style="margin-left:10%;margin-top:3%;width:70%;height:70%;TABLE-LAYOUT:fixed;WORD-WRAP:break_word;"id="table"> 
			 <tr><td ><input type="hidden" name="sims.id" value="${sims.id }" disabled="false" style="width:180px;heighe:50px"></td></tr></tr>
          	<tr><td >SIM卡号码：</td>
          		<td ><input type="text" name="sims.phone" value="${sims.phone }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
			<tr><td >运营商：</td>
          		<td ><input type="text" name="sims.operator" value="${sims.operator }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
          	<tr><td >借用者：</td>
          		<td ><input type="text" name="sims.manager" value="${sims.borrower }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
          	<tr><td >借用时间：</td>
          		<td ><input type="text" name="sims.borrowTime" value="${sims.borrowTime }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
			<tr><td >归还时间：</td>
          		<td ><input type="text" name="sims.returnTime" value="${sims.returnTime }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
          	<tr><td >借用状态：</td>
          		<td ><select name="sims.state" style="width:100px;">
     			<option value="已借" selected="selected"><font color="black">已借</font></option>
     			<option value="可借" ><font color="black">可借</font></option>
		     	<option value="已还"><font color="black">已还</font></option>
		     	<option value="丢失"><font color="black">丢失</font></option>
		     	<option value="损坏"><font color="black">损坏</font></option>
     			</select></td>
     		</tr>
     		</table>
     		</div>	
     			<tr style="height:40px;">
			<td><button type="submit" style="margin-left:70%;" ><a href="Resource_updateDeviceApply?id=${devices.deviceId }">【保存】</a></button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button  type="reset"> <a href="javascript:void(0);" onClick="window.history.back()">【返回】</a></button></td>
		</table>
</body>
</html>

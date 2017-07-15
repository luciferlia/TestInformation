<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script src="script/js/bootstrap.min.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
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
	<DIV ><span><A href="../../project/all">资源管理</A>  &gt; <A href="../../resource/deviceApply">设备申请</A> &gt; <A href="../../resource/deviceDetail">设备信息详情</A></DIV></DIV>   
	</tr>
 	<table id="wai" style="width:90%;height:70%;margin-left:2%;margin-top:2%;background-color:white;">
		<tr style="background-color:#4682B4;height:40px;">
			<td></td>
		</tr>
	<tr>
	<td>
	<div class="box" title="设备详情信息">
      <table style="margin-left:10%;margin-top:3%;width:70%;height:70%;TABLE-LAYOUT:fixed;WORD-WRAP:break_word;"id="table"> 
          	 <tr><td ><input type="hidden" name="devices.deviceId" value="${devices.deviceId }" disabled="false" style="width:180px;heighe:50px"></td></tr></tr>
          	<tr><td >设备名称：</td>
          		<td ><input type="text" name="devices.deviceName" value="${devices.deviceName }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
     		<tr><td >设备型号：</td>
     			<td ><input type="text" name="devices.deviceType" value="${devices.deviceType }" disabled="false" style="width:180px;heighe:50px"></td></tr>
     		<tr><td>设备版本：</td>
     			<td ><input type="text" name="devices.version" value="${devices.version }" disabled="false" style="width:180px;heighe:50px"></td></tr>
     		<tr><td>设备协议：</td>
     			<td ><input type="text" name="devices.protocol" value="${devices.protocol }" disabled="false" style="width:180px;heighe:50px"></td></tr>
     		<tr><td>是否耗材：</td>
     			<td ><input type="text" name="devices.deviceType" value="${devices.isconsum }" disabled="false" style="width:180px;heighe:50px"></td></tr>
     		<tr><td>设备数量：</td>
     			<td ><input type="text" name="devices.deviceCount" value="${devices.deviceCount }" disabled="false" style="width:180px;heighe:50px"></td></tr>
     		<tr><td>剩余数量：</td>
     			<td ><input type="text" name="devices.borrowCount" value="${devices.remainingCount }" disabled="false" style="width:180px;heighe:50px"></td></tr>
     			<tr><td>负责人：</td>
     			<td ><input type="text" name="devices.manager" value="${devices.manager }" disabled="false" style="width:180px;heighe:50px"></td></tr>
     		<tr><td>备注：</td>
     			<td ><textarea type="text" class="input" name="devices.deviceType" disabled="false" style="width:300px;height:100px;">${devices.remark }</textarea></td></tr>
        </table>
    </div>
	</td>
	</tr>
	<tr style="height:40px;">
			<td><button  type="reset" style="margin-left:70%;"> <a href="javascript:void(0);" onClick="window.history.back()">【返回】</a></button></td>
		</tr>
	</table>
			
  </body>
</html>

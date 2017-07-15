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
	<DIV ><span><A href="../../project/all">资源管理</A>  &gt; <A href="../../resource/deviceApply">测试卡申请</A> &gt; <A href="../../resource/deviceDetail">测试卡详情</A></DIV></DIV>   
	</tr>
 	<table id="wai" style="width:90%;height:70%;margin-left:2%;margin-top:2%;background-color:white;">
		<tr style="background-color:#4682B4;height:40px;">
			<td></td>
		</tr>
	<tr>
	<td>
	<div class="box" title="SIM卡详情信息">
      <table style="margin-left:10%;margin-top:3%;width:70%;height:70%;TABLE-LAYOUT:fixed;WORD-WRAP:break_word;"id="table"> 
          	 <tr><td ><input type="hidden" name="devices.deviceId" value="${sims.id}" disabled="false" style="width:180px;heighe:50px"></td></tr></tr>
          	<tr><td >SIM卡号码：</td>
          		<td ><input type="text" name="sims.phone" value="${sims.phone }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
          	<tr><td >ICCID：</td>
          		<td ><input type="text" name="sims.ICCID" value="${sims.ICCID}" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
			<tr><td >运营商：</td>
          		<td ><input type="text" name="sims.operator" value="${sims.operator }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
          	<tr><td >PUK码：</td>
          		<td ><input type="text" name="sims.puk" value="${sims.puk }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
          	<tr><td >服务密码：</td>
          		<td ><input type="text" name="sims.servicePassword" value="${sims.servicePassword }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
          	<tr><td >套餐说明：</td>
          		<td ><input type="text" name="sims.description" value="${sims.description }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
          	<tr><td >流量：</td>
          		<td ><input type="text" name="sims.gprs" value="${sims.gprs }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
          	<tr><td >短信：</td>
          		<td ><input type="text" name="sims.message" value="${sims.message }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
          	<tr><td >通话：</td>
          		<td ><input type="text" name="sims.callPhone" value="${sims.callPhone }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
          	<tr><td >负责人：</td>
          		<td ><input type="text" name="sims.manager" value="${sims.manager }" disabled="false" style="width:180px;heighe:50px"></td></tr>
          		<tr><td colspan="6" style="height:6px;"></td></tr>
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

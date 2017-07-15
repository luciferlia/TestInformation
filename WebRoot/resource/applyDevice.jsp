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
  .spinner{zoom:1;overflow:auto;border:solid 1px #ccc;float:left;}
    .spinner input{border:none;float:left;margin-top:5px}
    .spinner .arrow{float:right;font-size:12px;}
    .spinner .arrow a{display:block;width:20px;background:#eee;text-align:center;cursor:pointer;-moz-user-select:none;-webit--user-select:none;user-select:none}


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
<script>
    function addminus(id, add) {
        var t = document.getElementById(id), v = parseInt(t.value) || 0;
        add ? v++ : v--;
        t.value = v;
    }
</script>
  </head>
  <body>
   <tr>
	<DIV ><span><A href="../../project/all.jsp">申请设备资源</A>  &gt; <A href="../../resource/deviceApply.jsp">设备申请</A> &gt; <A href="../../resource/applyDevice.jsp">申请设备资源</A></DIV></DIV>   
	</tr>
 	<table id="wai" style="width:90%;height:70%;margin-left:2%;margin-top:2%;background-color:white;">
		<tr style="background-color:#4682B4;height:40px;">
			<td></td>
		</tr>
	<tr>
	<td>
	<div class="box" title="申请设备资源">
      <table style="margin-left:10%;margin-top:3%;width:70%;height:70%;TABLE-LAYOUT:fixed;WORD-WRAP:break_word;"id="table"> 
     		<tr><td ><input type="hidden" name="devices.deviceId" value="${devices.deviceId }" disabled="false"  style="width:180px;heighe:50px"></td></tr></tr>
          	<tr><td >设备名称:</td>
          		<td ><input type="text" name="devices.deviceName" value="${devices.deviceName }" disabled="false"  style="width:180px;heighe:50px"></td></tr>
     			<tr><td colspan="6" style="height:6px;"></td></tr>
     		<tr><td >设备型号:</td>
     			<td ><input type="text" name="devices.deviceType" value="${devices.deviceType }" disabled="false"  style="width:180px;heighe:50px"></td></tr>
     		<tr><td>设备版本：</td>
     			<td ><input type="text" name="devices.version" value="${devices.version }" disabled="false"  style="width:180px;heighe:50px"></td></tr>
     		<tr><td>设备协议：</td>
     			<td ><input type="text" name="devices.protocol" value="${devices.protocol }" disabled="false"  style="width:180px;heighe:50px"></td></tr>
     		<tr><td>设备数量：</td>
     			<td ><input type="text" name="devices.deviceCount" value="${devices.deviceCount }" disabled="false" style="width:180px;heighe:50px"></td></tr>
     		<tr><td>是否耗材：</td>
     			<td ><input type="text" name="devices.deviceType" value="${devices.isconsum }" disabled="false"  style="width:180px;heighe:50px"></td></tr>
     		<tr><td>申请数量：</td>
     		<td>
     			<div class="spinner">
				    <input type="text" readonly="readonly" id="t1" name="devices.borrowCount "  value="${devices.borrowCount }"/>
				    <div class="arrow"><a onclick="addminus('t1',true)">↑</a></div>
				</div></td></tr>     			
     		<tr><td>备注：</td>
     			<td ><textarea type="text" class="input" name="devices.deviceType" disabled="false"  style="width:300px;height:100px;">${devices.remark }</textarea></td></tr>
        </table>
    </div>
	</td>
	</tr>
	<tr style="height:40px;">
			<td><button type="submit" style="margin-left:70%;" ><a href="Resource_updateDeviceApply?id=${devices.deviceId }">【申请】</a></button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button  type="reset"> <a href="javascript:void(0);" onClick="window.history.back()">【返回】</a></button></td>
		</tr>
	</table>
   </body>
</html>
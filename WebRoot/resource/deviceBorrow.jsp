<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<script src="script/exchange3.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<style type="text/css">
li {
	list-style: none;
}


#table td {
	border: 1px solid #EDEDED;
}



a:hover {
	color: red;
}

td {
	font-size: 0.9em;
}
</style>
</head>
<body>
	<h3 class="glyphicon glyphicon-tasks" style="margin-left:5%;"><font color="black">设备借用信息</font></h3>
	<hr/>
	<table id="table" class="table table-condensed table-bordered table-hover table-striped"
		style="margin-left:30px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;">
		<tr style="background-color:#4682B4;height:40px;">
			<td style="text-align:center;vertical-align:middle;width:50px;"><h4><font color="black"><input type="checkbox" name="all" id="all" /></font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">设备名称</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">设备型号</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">借出数量</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">借用者</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">借用时间</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">借用状态</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">归还时间</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">操作</font></h4></td>
			</tr>
			<tbody id="cdTb">
		<s:iterator value="pageBean.list">	
		<%-- <c:forEach items="${ds }" var="ds"> --%>
		<tr style="height:30px;">
				<td style="text-align:center;vertical-align:middle;"><input type="checkbox" /><span style="font-size:0;overflow:hidden;"><s:property value="deviceId"/></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="deviceName"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="deviceType"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="borrowCount"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="borrower"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="borrowTime"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="state"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="returnTime"/></font></td>
				<td style="text-align:center;vertical-align:middle;"><a onclick="updateRow(this);"> 修改借用状态 </a>
			</tr>
			<%-- </c:forEach> --%>
		</s:iterator>
		</tbody>
		</table>
		<table style="width:80%;margin-left:5%;">
				<tr>
					<td><span style="float:left;">共<span><s:property
								value="pageBean.totalPage" /></span>页|第<span><s:property
								value="pageBean.currentPage" /> </span>页</span>
					</td>
					<td><span style="float:right;"><form action="Resource_showDeviceBorrow" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="Resource_showDeviceBorrow?page=1" class="right-font08">首页</a> | <a
									href="Resource_showDeviceBorrow?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="Resource_showDeviceBorrow?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="Resource_showDeviceBorrow?page=<s:property value="%{pageBean.totalPage}"/> "
									class="right-font08">末页</a>
							</s:if>
							<s:else>下一页 | 末页</s:else>
							] 转至：<select name="page" style="width:50px;">
								<s:iterator value="pageBean.count">
									<option value="<s:property value="count"/>"><s:property value="count" />
									</option>
								</s:iterator>
							</select>
							<%-- <input type="text" style="width:50px;" name="page" /> --%>
							<input type="submit" style="border:1px solid black;height:25px;text-align:center;" value="确定"class="btn btn-primary" />
						</form></span></td>
				</tr>
			</table>
		
<!-----------------------------------------------修改借用状态----------------------------------------------------------------------->
		<div id="hid"></div>
		<div id="update" class="popDiv3" style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span><font color="black">X</font></span>
		</div>
		<form action="Resource_updateDeviceState" method="post">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;">
		<td colspan="2" style="height:40px;">
		<h4 style="margin-left:30%"><font color="black" >状态修改</font></h4></td>
		</tr>
		<tr style="display:none;">
					<td><span style="margin-left:20%;"></span></td>
					<td><input type="hidden" name="device.deviceId" value="" id="numb" ></td>
				</tr>	
				<tr>

					<td><span style="margin-left:20%;">仪表名称：</span></td>
					<td><input type="text" name="device.deviceName" value="" id="numbs"  class="form-control" style="width:150px;"></td>
				</tr>
				<tr>

					<td><span style="margin-left:20%;">仪表型号：</span></td>
					<td><input type="text" name="device.deviceType" value="" id="num"  class="form-control" style="width:150px;"></td>
				</tr>
				<tr>

					<td><span style="margin-left:20%;">借出数量：</span></td>
					<td><input type="text" name="device.borrowCount" value="" id="name"  class="form-control" style="width:150px;"></td>
				</tr>
				<tr>

					<td><span style="margin-left:20%;">借用者：</span></td>
					<td><input type="text" name="device.borrower" value="" id="test"  class="form-control" style="width:150px;"></td>
				</tr>
				<tr>

					<td><span style="margin-left:20%;">借出时间：</span></td>
					<td><input type="text" name="device.borrowTime" value="" id="states"  class="form-control" style="width:150px;"></td>
				</tr>
				<tr>
					<td><span style="margin-left:20%;">状态：</span></td>
					<td><select name="device.state" class="form-control" style="width:150px;" id="allnum">
		     			<option value="可借" ><font color="black">可借</font></option>
		     			<option value="已借" ><font color="black">已借</font></option>
		     			<option value="已还"><font color="black">已还</font></option>
		     			<option value="丢失"><font color="black">丢失</font></option>
		     			<option value="损坏"><font color="black">损坏</font></option>
		     			</select></td>
				</tr>
				<tr style="background-color:#4682B4;height:40px;">
					<td colspan="2"><div style="margin-left:30%">
			        <input type="submit" value="提交" id="btn_update"  onclick="updateInfo()" class="btn btn-primary" style="border:1px solid black;">
					&nbsp;&nbsp;&nbsp;&nbsp;	
					<input type="button" value="取消" onclick="hideAddInput()" class="btn btn-primary" style="border:1px solid black;"></div></td>
				</tr>
		</table>
		</form>
	</div>
</body>
</html>

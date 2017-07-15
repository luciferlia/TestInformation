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
<script src="script/exchange.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<style type="text/css">
li {
	list-style: none;
}
#showcheck td {
	border: 1px solid #EDEDED;
}

a {
	font-size: 0.9em;
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
	<h3 class="glyphicon glyphicon-tasks" style="margin-left:5%;"><font color="black">设备资源申请</font></h3>
	<table style="width:80%;margin-left:5%;margin-top:1%;"><tr><td>
		<a href="resource/applyd.jsp" type="button" class="btn btn-primary">申请</a>
	
	
		<form action="Resource_selectDevice2" method="post" style="float:right;">
			<input type="hidden" name="deviceId" value="${deviceId }">
			<input type="hidden" name="page" value="1">
			<font color="black" size="4">设备选择：</font><select name="isconsum" style="width:100px;height:35px;" >
     			<option value="" selected="selected"><font color="black">全部</font></option>
     			<option value="是"><font color="black">耗材</font></option>
     			<option value="否"><font color="black">不耗材</font></option>
     			</select>
     			<button type="submit" class="btn btn-primary">确定</button>
     		
			</form>
			</td></tr></table>
	<hr/>
	<table id="showcheck"
		style="margin-left:30px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;"  class="table  table-bordered table-hover table-striped">
				<tr style="background-color:#4682B4;height:40px;">
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">设备名称</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">设备型号</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">总数量</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">剩余数量</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">是否耗材</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">借用状态</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">负责人</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">操作</font></h4></td>
				</tr>
			<tbody id="cdTb">
			<s:iterator value="pageBean.list">
			<tr style="height:30px;">
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="deviceName"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="deviceType"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="deviceCount"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="remainingCount"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="isconsum"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="state"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="manager"/></font></td>
				<td style="text-align:center;vertical-align:middle;"><a href="Resource_showDetailDevice?id=<s:property value="deviceId"/>"> 详情  </a><%-- |<a href="Resource_deviceApply?id=<s:property value="deviceId"/>"> 申请 </a> --%></td>
				<!-- <a onclick="showDivFun()" > 申请 </a></td> -->
			</tr>
			</s:iterator>
		</tbody>
		</table>
		<table style="width:80%;margin-left:5%;">
				<tr>
					<td><span style="float:left;">共<span><s:property
								value="pageBean.totalPage" /></span>页|第<span><s:property
								value="pageBean.currentPage" /> </span>页</span>
					</td>
					<td><span style="float:right;"><form action="Resource_selectDevice2" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="Resource_selectDevice2?page=1" class="right-font08">首页</a> | <a
									href="Resource_selectDevice2?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="Resource_selectDevice2?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="Resource_selectDevice2?page=<s:property value="%{pageBean.totalPage}"/> "
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
							<input type="submit" style="border:1px solid black;" value="确定" class="btn btn-primary btn-xs">
						</form></span></td>
				</tr>
			</table>
		
<!------------------------------------------------------------资源申请-------------------------------------------------------->
		<%-- <div id="hid"></div>
		<div id="popDiv" class="mydiv" style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="closeDivFun()">
			<span><font color="black"><img alt="" src="/images/del.gif"></font></span>
		</div>
		<table style="width:100%;">
		<form action="user_update" method="post">
			
				<tr style="background-color:#4682B4;">
		<td colspan="2" style="height:40px;background-color:#4682B4;">
		<h4 style="margin-left:30%"><font color="black" >资源申请</font></h4></td>
		</tr>	
		<tr style="height:30px;"><td></td><td></td></tr>
				<tr>

					<td><span style="margin-left:20%;">仪表名称：</span></td>
					<td><input type="text" name="" value=""></td>
				</tr>
				<tr style="height:30px;"><td></td><td></td></tr>
				<tr>

					<td><span style="margin-left:20%;">仪表型号：</span></td>
					<td><input type="text" name="" value=""></td>
				</tr>
				<tr style="height:30px;"><td></td><td></td></tr>
				<tr>

					<td><span style="margin-left:20%;">申请个数：</span></td>
					<td><input type="text" name="" value=""></td>
				</tr>
				<tr style="height:20px;"><td></td><td></td></tr>
                <!-- 点击确定后，所借时间和所借人根据ID号直接生成 -->
				<tr style="height:40px;background-color:#4682B4;">
					<td align="center" colspan="2"><button 
							type="submit">取消</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button 
							type="submit">保存</button></td>
				</tr>
			
		</form>
		</table>
	</div> --%>
</body>
</html>

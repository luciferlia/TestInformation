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
<script src="script/exchange4.js"></script>
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
<body>
	<h3 class="glyphicon glyphicon-tasks" style="margin-left:5%;"><font color="black">样机资源借用信息</font></h3>
	<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
		width="100%" color=#987cb9 SIZE=10>
	<table id="table" class="table table-condensed table-bordered table-hover table-striped"
		style="margin-left:30px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;">
		<tr style="background-color:#4682B4;height:40px;">
			<td style="text-align:center;vertical-align:middle;width:50px;"><h4><font color="black"><input type="checkbox" name="all" id="all" /></font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">样机名称</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">样机型号</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">借出数量</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">借用者</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">借用时间</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">归还时间</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">借用状态</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">操作</font></h4></td>
				</tr>
		<tbody id="cdTb">
		<s:iterator value="pageBean.list">	
		<tr style="height:30px;">
				<td style="text-align:center;vertical-align:middle;"><input type="checkbox" /><span style="font-size:0;overflow:hidden;"><s:property value="deviceId"/></span></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="prototypeName"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="prototypeType"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="borrowCount"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="borrower"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="borrowTime"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="returnTime"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="state"/></font></td>
				<td style="text-align:center;vertical-align:middle;"><a onclick="updateRow(this);"> 修改借用状态 </a>
			</tr>
			<%-- </c:forEach> --%>
		</s:iterator>
		</tbody>
		</table>
		<!--#########################################修改样机借用状态#####################################################-->
		<div id="hid"></div>
		<div style="display:none;" id="update" class="mydiv" >
	   
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span
				style="border:0px solid #000;width:10px;height:10px;line-height:10px;text-align:center;display:block;left:-20px;">X</span>
		</div>
		<form action="Resource_updatePrototype" method="post" yle="height:100%;width:100%;">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#ADD8E6;">
		<td colspan="2" style="height:30px;">
		<h4 style="margin-left:30%"><font color="black" >修改样机</font></h4></td>
		</tr>		
		
				<tr style="display:none;">
					<td><h4></h4></td>
					<td><input type="hidden" id="l1"/></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">样机名称：</span></td>
					<td><input type="text" id="l2" disabled="true" class="form-control" style="width:150px;"/></td>
				</tr>
				<tr style="display:none;">
					<td><h4></h4></td>
					<td><input type="hidden" id="l3"/></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">负责人：</span></td>
					<td><input type="text" id="l4" disabled="true" class="form-control" style="width:150px;"/></td>
				</tr>
				<tr style="display:none;">
					<td><h4></h4></td>
					<td><input type="hidden" id="l5"/></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">借用数量：</span></td>
					<td><input type="text" id="l6"  disabled="true" class="form-control" style="width:150px;"/></td>
				</tr>
				<tr>
					<td ><span style="margin-left:10%;">借用人：</span></td>
					<td><input type="text" id="l7"  name="content" class="form-control" style="width:150px;"/></td>
				</tr>
				
				<tr>

					<td><span style="margin-left:20%;">状态：</span></td>
					<td><select name="cases.level" class="form-control" style="width:150px;" id="l8" >
     			<option value="1" selected="selected"><font color="black">已还</font></option>
     			<option value="2"><font color="black">丢失</font></option>
     			<option value="3"><font color="black">顺坏</font></option>
     		</select></td>
				</tr>
				<tr style="display:none;">
					<td><h4></h4></td>
					<td><input type="hidden" id="l9" /></td>
				</tr>
				<tr style="display:none;">
					<td><h4></h4></td>
					<td><input type="hidden" id="l10"  /></td>
				</tr>
              <tr style="background-color:#ADD8E6;height:30px;">
				<td colspan="2" >
				<div style="margin-left:30%">
			<input type="submit" value="提交" 
				class="btn btn-primary" style="border:1px solid black;"id="btn_update" >
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button"  class="btn btn-primary" style="border:1px solid black;"
				value="取消" onclick="hideAddInput()" ></div></td>
				
				</tr>
		
	 </table>
	 </form>
	</div>
	
</body>
</html>

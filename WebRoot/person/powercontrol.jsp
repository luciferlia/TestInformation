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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<jsp:include page="/admin/adminTop.jsp"></jsp:include>
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange.js"></script>
<style type="text/css">
li{list-style:none;}
#table td{border:1px solid #EDEDED; font-size:0.9em;font-color:black;text-align:center;vertical-align:middle;}
td{font-size:0.9em;font-color:black;}
</style>

</head>

<body>
	
	 <div style="mardin-left:100px;">
			<h3 style="margin-left:100px;" class="glyphicon glyphicon-tasks">权限详细</h3>
			
		</div>
		    <HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
									width="100%" color=#987cb9 SIZE=10>
			<table id="table" style="margin-left:100px;width:1200px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all">
				<tr style="background-color:#4682B4;height:40px;">
					<td><h4><font color="white">权限名称</font></h4></td>
					<td><h4><font color="white">URL</font></h4></td>
					<td><h4><font color="white">所在页面</font></h4></td>
					<td><h4><font color="white">备注名称</font></h4></td>
					<td><h4><font color="white">操作</font></h4></td>
				</tr>
				<tbody id="cdTb">
				<tr style="height:30px;">
					
					<td><font color="black">删除</font></td>
					<td ><font color="black">wind/detaiuse.jsp</font></td>
					<td><font color="black">用例分类</font></td>
					<td ><font color="black">分类删除</font></td>
					<td><a onclick="updateRow(this);">编辑</a>|<a onclick="" >删除</a></td>
				</tr>
				
				 </tbody>
						<tr style="height:30px;">
					
					<td><font color="black">删除</font></td>
					<td ><font color="black">wind/usecase.jsp</font></td>
					<td><font color="black">详细用例</font></td>
					<td ><font color="black">用例删除</font></td>
					<td><a onclick="updateRow(this);">编辑</a>|<a onclick="" >删除</a></td>
				</tr>
					<tr style="height:30px;">
					
					<td><font color="black">删除</font></td>
					<td ><font color="black">project/Myproject.jsp</font></td>
					<td><font color="black">项目基本信息</font></td>
					<td ><font color="black">项目删除</font></td>
					<td><a onclick="updateRow(this);">编辑</a>|<a onclick="" >删除</a></td>
				</tr>
						<tr style="height:30px;">
					
					<td><font color="black">增加</font></td>
					<td ><font color="black">project/Myproject.jsp</font></td>
					<td><font color="black">项目基本信息</font></td>
					<td ><font color="black">项目增加</font></td>
					<td><a onclick="updateRow(this);">编辑</a>|<a onclick="" >删除</a></td>
				</tr>
						<tr style="height:30px;">
					
					<td><font color="black">导入</font></td>
					<td ><font color="black">project/usecase.jsp</font></td>
					<td><font color="black">系统用例</font></td>
					<td ><font color="black">用例导入</font></td>
					<td><a onclick="updateRow(this);">编辑</a>|<a onclick="" >删除</a></td>
				</tr>
						<tr style="height:30px;">
					
					<td><font color="black">编辑</font></td>
					<td ><font color="black">project/Myproject.jsp</font></td>
					<td><font color="black">项目基本信息</font></td>
					<td ><font color="black">项目编辑</font></td>
					<td><a onclick="updateRow(this);">编辑</a>|<a onclick="" >删除</a></td>
				</tr>
			</table>
		<%-- 隐藏域表单 --%>
    
    <div id="hid"></div>
		<div id="update" class="min" style="display:none;">
		<div align="right" style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;" onclick="hideAddInput()">
		<span><font color="black">X</font></span>
		</div>
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#ADD8E6;">
					<td colspan="2" style="height:30px;">
						<h4 style="margin-left:30%"><font color="black" >修改权限</font></h4>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">权限名称：</font></td>
					<td><input type="text" name="" value="" id="l1"></td>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">URL：</font></td>
					<td><input type="text" name="" value="" id="l2"></td>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">所在页面：</font></td>
					<td><input type="text" name="" value="" id="l3"></td>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">备注名称：</font></td>
					<td><input type="text" name="" value="" id="l4"></td>
				</tr>
				<tr style="background-color:#ADD8E6;">
                 <td colspan="2" style="height:30px;">
				<div style="margin-left:30%;height:30px;">
			      <input type="submit" value="提交" onclick="updateInfo()"
				 id="btn_update" >&nbsp;&nbsp;&nbsp;&nbsp;	
			      <input type="reset" value="取消" onclick="hideAddInput()" ></div></td>				
				</tr>
			</table>
		</div>
</body>
</html>

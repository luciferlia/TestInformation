<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange.js"></script>
<style>
li{list-style:none;}
td{font-size:0.9em;font-color:black;}
#table td{border:1px solid black;text-align:center;vertical-align:middle;font-size:0.9em;}
</style>
</head>

<body>
		 	<div style="margin-left:1%;margin-top:1%;">
		<table style="width:80%;margin-left:5%;"><tr><td>
			<ul>
			<li style="float:left;"><a onclick="showDivFun1();" type="button"
				class="btn btn-primary 	glyphicon glyphicon-plus">增加</a></li>
			<span style="float:right;">
			<li style="float:left;"><input type="text" class="form-control" placeholder="搜索"
				style="width:200px;"></li>
			<li style="float:left;">
				<button type="submit" class="btn btn-info">确定</button>
			</li>
			</span>
		</ul></td></tr></table>
<hr/>
			<table   id="table" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;">
				<tr style="background-color:#4682B4;height:40px;">
					<td style="text-align:center;vertical-align:middle;"><h4><font color="black">序号</font></h4></td>
				    <td style="text-align:center;vertical-align:middle;"><h4><font color="black">分类</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="black">创建人</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="black">维护人</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="black">创建时间</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="black">最新维护时间</font></h4></td>
					<td><h4>操作</h4></td>
				</tr>
				
					<tr style="height:40px;">
						<td style="text-align:center;vertical-align:middle;">1</td>
						<td style="text-align:center;vertical-align:middle;"><a href="htest/hdetailuse.jsp">机器测试</a></td>
						<td style="text-align:center;vertical-align:middle;">黄明亮</td>
						<td style="text-align:center;vertical-align:middle;">黄明亮</td>
						<td style="text-align:center;vertical-align:middle;">2016/1/2</td>
						<td style="text-align:center;vertical-align:middle;">2017/2/6</td>
						<td style=""><a onclick="updateRow(this);">修改</a>|<a  onclick='return(confirm("确定删除该分类及其包括的模块和用例?"))'>删除</a></td>
					</tr>
			</table>
		</div>
		 <div id="hid"></div>
		<div style="display:none;" id="update" class="mydiv" >
	   
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span><font color="black">X</font></span>
		</div>
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#ADD8E6;height:30px;">
		<td colspan="2" style="height:30px;">
		<h4 style="margin-left:30%"><font color="black" >修改分类</font></h4></td>
		</tr>		
		<form action="Case_updateCaseType" method="post">
				<tr>
					<td></td>
					<td><input type="hidden" id="l1"  name="id"/></td>
				</tr>
				<tr>
					<td ><span style="margin-left:10%;">分类内容：</span></td>
					<td><input type="text" id="l2"  name="content"/></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">创&nbsp;&nbsp;建&nbsp;&nbsp;人：</span></td>
					<td><input type="text" id="l3"  disabled="true"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="hidden" id="l4" /></td>
				</tr>
              <tr style="background-color:#ADD8E6;height:30px;">
				<td colspan="2" >
				<div style="margin-left:30%;">
			<input type="submit" value="提交" onclick="updateInfo()"
				 id="btn_update" class="btn btn-primary">
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button" 
				value="取消" onclick="hideAddInput()" class="btn btn-primary"></div></td>
				
				</tr>
		</form>
	 </table>
	</div>
	
	<div id="popDiv1"  style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="closeDivFun1()">
			<span><font color="black">X</font></span>
		</div>
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#ADD8E6;">
					<td colspan="2" style="height:30px;">
						<h4 style="margin-left:30%"><font color="black" >增加分类</font></h4>
				</tr>
		      <form action="Case_addType" method="post">			
				<tr>
					<td><span style="margin-left:20%;">分类：</span></td>
					<td><input type="text" name="casetype" value=""></td>
				</tr>
				<tr style="background-color:#ADD8E6;height:30px;">
                 <td colspan="2" >
				<div style="margin-left:30%;">
			      <input type="submit" value="提交" class="btn btn-primary">
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button" 
				value="取消" onclick="closeDivFun1()" class="btn btn-primary"></div></td>				
				</tr>
			
		</form>
		</table>
	</div>
</body>
</html>

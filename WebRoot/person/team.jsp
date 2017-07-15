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
<jsp:include page="/admin/adminTop.jsp"></jsp:include>
<style>
body{background-color:#FDF5E6;}
#mianp{background-color:white;width:80%;height:60%;margin-top:1%;margin-left:1%;border:2px solid #ADD8E6;}
li{list-style:none;}
td{font-size:0.9em;font-color:black;}
</style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div id="mianp">
    <h3 style="margin-left:200px;" class="glyphicon glyphicon-tasks">查询人员</h3>
      <ul>
			<div style="margin-left:800px;">
				<li style="float:left;"><button type="submit" class="btn btn-primary">确定</button></li>
				<li style="float:left;"><button type="reset" class="btn btn-primary" >重置</button></li>
			</div>
		</ul>
    <HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
				width="100%" color=#987cb9 SIZE=10>
				<form action="" method="post">
   <table style="width:70%;height:200px;margin-left:200px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all"
				class="table table-condensed table-bordered table-hover table-striped">
				
   <tr>
   <td style="background-color:#4682B4;width:100px;"><font color="black" style="margin-left:20%;">姓名</font></td>
     <td><input style="200px;" value=""></td>
       <td style="background-color:#4682B4;width:100px;"><font color="black" style="margin-left:20%;">所属组</font></td>
         <td><input style="200px;" value=""></td>
   </tr>
      <tr>
   <td style="background-color:#4682B4;"><font color="black" style="margin-left:20%;">电话</font></td>
     <td><input style="200px;" value=""></td>
       <td style="background-color:#4682B4;"><font color="black" style="margin-left:20%;">邮箱</font></td>
         <td><input style="200px;" value=""></td>
   </tr>   <tr>
   <td style="background-color:#4682B4;"><font color="black" style="margin-left:20%;">职称</font></td>
     <td><input style="200px;" value=""></td>
       <td style="background-color:#4682B4;"><font color="black" style="margin-left:20%;">状态</font></td>
         <td><select name="cases.level" style="width:100px;" type="select" >
     			<option value="1" selected="selected"><font color="black">正式</font></option>
     			<option value="2"><font color="black">试用</font></option>
     			<option value="3"><font color="black">实习</font></option>
     			<option value="3"><font color="black">临时</font></option>
     		</select></td>
   </tr>
   <tr>
   <td style="background-color:#4682B4;"><font color="black" style="margin-left:20%;">性别</font></td>
     <td><input style="200px;" value=""></td>
       <td style="background-color:#4682B4;"><font color="black" style="margin-left:20%;">直接上级</font></td>
         <td><input style="200px;" value=""></td>
   </tr>
   </table>
		</form>		
		</div>
  </body>
</html>

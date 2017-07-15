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
<link rel="StyleSheet" href="script/dtree.css" type="text/css" />
<script type="text/javascript" src="script/dtree.js"></script>
<jsp:include page="/admin/adminTop.jsp"></jsp:include>
<style>
li{list-style:none;}
</style>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
 <h2 style="margin-left:200px;">修改角色权限</h2>
  <HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
				width="100%" color=#987cb9 SIZE=10>
<table style="margin-left:100px;width:1000px;min-height:500px;">
<tr>
<td style="width:400px;">
<ul>
				<li><input type="checkbox" value="" id="checkedId" name="checkedId">经理</li>
				<li><input type="checkbox" value="" id="checkedId" name="checkedId">主管</li>
				<li><input type="checkbox" value="" id="checkedId" name="checkedId">主测</li>
				<li><input type="checkbox" value="" id="checkedId" name="checkedId">用例开发</li>
				<li><input type="checkbox" value="" id="checkedId" name="checkedId">测试员</li>
				<li><input type="checkbox" value="" id="checkedId" name="checkedId">设备管理员</li>
				</ul>
</td>
<td>
<div>
<p><a href="javascript:  d.closeAll();">open all</a> | <a href="javascript: d.openAll();">close all</a></p>
	<script>
  d=new dTree('d');
  d.add(0,-1,'权限管路'); 
  d.add(1,0,'authority','0001','项目管理');
  d.add(2,1,'authority','0002','项目基本信息');
  d.add(3,1,'authority','0003','项目计划信息');
  d.add(4,1,'authority','0004','项目人员信息');
  d.add(5,1,'authority','0005','项目委外信息');
  d.add(6,1,'authority','0006','项目成本信息');
  d.add(7,0,'authority','0007','用例');
  d.add(8,7,'authority','0008','硬测用例');
  d.add(9,7,'authority','0009','软测用例');
  d.add(10,0,'authority','0010','资源管理');
  d.add(11,10,'authority','0011','我的资源');
  d.add(12,10,'authority','0012','设备资源');
  d.add(13,10,'authority','0013','设备管路');
  d.add(14,10,'authority','0014','预定实验室');
  d.add(15,10,'authority','0015','测试卡资源');
  d.add(16,10,'authority','0016','测试卡管理');
  d.add(17,10,'authority','0017','样机申请');
  d.add(18,10,'authority','0018','样机管理');
  d.add(19,0,'authority','0019','个人事务');
  d.add(20,19,'authority','0020','查询人员');
  d.add(21,19,'authority','0021','我的信息');
  d.add(22,19,'authority','0022','权限修改');
  d.add(23,0,'authority','0023','工作台');
  d.add(24,23,'authority','0024','我的任务');
  d.add(25,23,'authority','0025','任务查询');
  d.add(26,23,'authority','0026','任务提醒');
  d.add(27,23,'authority','0027','历史任务');
  document.write(d);
		
  d.openAll();                              
   </script>
   </div>
   
   </td>
   </tr>
   </table>
</body>
</html>

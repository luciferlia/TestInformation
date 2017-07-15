<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<jsp:include page="/wind/top.jsp"></jsp:include>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
	
	body{
	font-color:black;
	}
</style>

  </head>
  
  <body>
   <center>
     <form action="user_update" method="post">
     <table>
     	<tr>
       		<td colspan="2" align="center">
         	<h1><s:text name="修改用户信息"/></h1><br/>
       		</td>
     	</tr>
     	<tr>
     		<td><h3>用 户 &nbsp;名：</h3></td>
     		<td><input type="text" name="user.uname" value="${user.uname }"></td>
     	</tr>
     	<tr>
     		<td><h3>密&nbsp;&nbsp;码：</h3></td>
     		<td><input type="password" name="user.upwd" value="${user.upwd }"></td>
     	</tr>
     	<tr>
     		<td><h3>性 &nbsp;&nbsp;别：</h3></td>
     		<td><input type="radio" name="user.sex" value="男" checked="checked">男<input type="radio" name="user.sex" value="女">女</td>
     	</tr>
     	<tr>
     		<td><h3>权限级别：</h3></td>
     		<td><select name="user.level">
     			<option value="1" selected="selected">读、写</option>
     			<option value="2">只读</option>
     		</select></td>
     	</tr>
     	<tr>
     		<td><h3>真实姓名：</h3></td>
     		<td><input type="text" name="user.realName" value="${user.realName }"></td>
     	</tr>
     	<tr>
     		<td><h3>邮&nbsp;&nbsp;箱：</h3></td>
     		<td><input type="text" name="user.email" value="${user.email }"></td>
     	</tr>
     	<tr>
     		<td><h3>组&nbsp;&nbsp;      别：</h3></td>
     		<td><input type="text" name="user.organize" value="${user.organize }"></td>
     	</tr>
     	<tr>
     		<td align="center" colspan="2"><button class="btn btn-primary" type="submit" >保存</button></td>
     	</tr>
     </table>
     </form>
   </center>
  </body>
</html>

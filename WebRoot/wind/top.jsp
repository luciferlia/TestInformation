<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta name="renderer" content="webkit">
<base href="<%=basePath%>">

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>与德测试信息管理系统</title>


<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<script src="script/logout.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
/*body{background:url(images/bg.jpg) #f8f6e9 0 0 repeat;
      background-size: cover;}*/
#sp li {
	float: left;
	list-style: none;
}
#sp{float: right;} 
.upfilebox {
                position: relative;
            }
            .upfilebox input {
                width: 250px;
                height: 40px;
                position: relative;
                z-index: 9;
                opacity: 0;
            }
            .upfilebox label {
                position: absolute;
                border:1px solid #87CEFA;
                background: white;
                display: inline-block;
                color: black;
                width:250px;
                height: 40px;
                line-height: 35px;
                text-align: center;
                top: 0px;
                left: 0px;
            }
            
</style>
<script type="text/javascript">
layui.use('layer', function(){
  var layer = layui.layer;
 
}); 
</script>
</head>

<body>
<form>
	<!-- <table width="100%" border="0" cellspacing="0" cellpadding="0"
		style="background:url(images/bg.jpg);height:70px;">
		<tr>
			<td height="59" >
			<table width="99%" borfer="0" cellspacing="0" cellpadding="0">
		    <tr>
			<td style="width:1%;"><img
					src="images/logo2.jpg" width="557" height="59" border="0" /></td>
			<td width="64%" align="right" style="font-size:12px;vertical-align:bottom;"> -->
			<table width="100%" style="margin-top:1%"><tr><td>
			<span id="sp" style="float:rigth;">
				<ul>
					<li><h5>
							<span class="layui-icon">&#xe612;<font
								color="black">当前在线人数:<font size="3px" color="red">${onLineCount }</font></font></span>
						</h5></li>
					<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><h5>
							<span class="layui-icon">&#xe612;<font
								color="black">${role.roleName }:<font size="3px" color="blue">${user.name }</font></font></span>
						</h5></li>
					<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><h5>
							<a href="wind/revisepassword.jsp" target="mainFrame"
								class="layui-icon">&#xe642;<font color="black">修改密码</font></a>
						</h5></li>
					<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><h5>
							<!-- <a href="Login_logout" onclick="return loginOut()"
								target="_top" class="glyphicon glyphicon-off "><font
								color="black">退出登录</font></a> -->
								<a onclick="loginOut()"
								target="_top" class="layui-icon">&#xe60f;<font
								color="black">退出登录</font></a>
						</h5></li><li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
						
				</ul>
				</span>
			
	</td>
	</tr>
</table> 
<!-- <HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
				width="100%" color=#987cb9 SIZE=10> -->
				<hr />
</form>
</body>
</html>

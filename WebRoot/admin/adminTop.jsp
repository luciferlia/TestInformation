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
<base href="<%=basePath%>">

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>与德测试信息管理系统</title>

<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
/*body{background:url(images/bg.jpg) #f8f6e9 0 0 repeat;
      background-size: cover;}*/
li {
	float: left;
	list-style: none;
}
#sp{float: right;} 
</style>
<script type="text/javascript">
	function loginOut() {
		var f = confirm("确认退出吗？");
		if (f) {
			return true;
		} else {
			return false;
		}
	}
	layui.use('layer', function(){
  var layer = layui.layer;
 
}); 
</script>
</head>

<body>
	<table width="100%" style="margin-top:1%"><tr><td>

			<span id="sp">
				<ul>
					<li><h5>
							<span class="layui-icon">&#xe612;<font
								color="black">${admin.permission }</font></span>
						</h5></li>
					<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><h5>
							<a href="wind/revisepassword.jsp" target="mainFrame"
								class="layui-icon">&#xe642;<font color="black">修改密码</font></a>
						</h5></li>
					<li>&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li><h5>
							<a href="Admin_loginOut" onclick="return loginOut()"
								target="_top" class="layui-icon">&#xe60f;<font
								color="black">退出登录</font></a>
						</h5></li>
				</ul>
				</span>
			</td>
	</tr>
</table> 
<hr/>
</body>
</html>

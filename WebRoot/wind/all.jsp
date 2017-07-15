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
    <link rel="shortcut icon" href="images/logio.ico" />
    <title>测试信息化管理系统</title>
    
</head>

<frameset cols="170px,*" rows="*" frameborder="0" border="0" framespacing="2" >
		<frame src="wind/left.jsp" name="leftFrame" scrolling="no" noresize="noresize" border="2" 
		id="leftFrame" />
		<frame src="wind/home.jsp" name="mainFrame" scrolling="auto" noresize="noresize"
		id="mainFrame" />
		</frameset>
</html>

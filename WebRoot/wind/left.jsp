<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>与德测试信息管理系统</title>
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
            <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
            <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<style>
li {
	color: black;
}

body {
	background-color: #FFFAF0;
}
.disabled{
pointer-events:none; 
}
</style>

<script>
layui.use('layer', function(){
  var layer = layui.layer;
 
});

layui.use('element', function(){
  var element = layui.element();
  
  //一些事件监听
 element.on('nav(filter)', function(elem){
  console.log(elem); //得到当前点击的DOM对象
});
});
/* $("#logo1").click(function (event) {
 event.preventDefault();        
 }); */
 </script>

</head>
<body>
	
	<ul class="layui-nav layui-nav-tree layui-nav-side" lay-filter="demo" style="width:170px;">
	<li class="layui-nav-item disabled"><img src="images/view.png" style="margin-left:25%;margin-top:3%;" ></li>
		<li class="layui-nav-item"><a href="wind/home.jsp" target="mainFrame" style="font-size:1.2em">首页</a></li>
		
		<c:forEach items="${menu  }" var="m">		
			<c:if test="${not empty m.ms }">
		<li class="layui-nav-item"><a href="javascript:;" style="font-size:1.2em"><i class="layui-icon">&#xe63c;</i> ${m.module }</a>
			<dl class="layui-nav-child">
				<c:forEach items="${m.ms }" var="ms">
				<dd>
					<a href="${ms.url }" target="mainFrame" style="font-size:1em">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ms.name }</a>			
				</dd>
				</c:forEach>
			</dl></li>
			</c:if>
			</c:forEach>
		<li class="layui-nav-item"><a href="log_showAllLog" target="mainFrame" style="font-size:1.2em">日志</a></li>
	</ul>
	

</body>
</html>
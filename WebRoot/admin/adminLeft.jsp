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
 </script>


</head>
<body>
<%-- 	<div class="row" style="margin-top:1%;">
		<div class="col-md-2" style="width:95%;margin-left:5px;min-width:150px;">
			<div class="panel-group table-responsive" role="tablist">
				
				<div id="dao">
					 <div class="panel panel-primary leftMenu">
                        <div class="panel-heading" id="collapseListGroupHeading4" data-toggle="collapse" data-target="#collapseListGroup" role="tab" style="background:url(images/bg.jpg);">
                            <h4 class="panel-title"><a href="wind/home.jsp" rel="drevil"  target="mainFrame"
							><font size="2" color="black" class=" glyphicon glyphicon-th">  首页</font>
						</a></h4>
							</div>
					</div>
			<c:forEach items="${menu  }" var="m">
			
			<c:if test="${not empty m.ams }">
			<div class="panel panel-primary leftMenu" >
					<div class="panel-heading" id="${m.description }" data-toggle="collapse" data-target="#${m.url }" role="tab" style="background:url(images/bg.jpg);">
                            <h4 class="panel-title">
                              <font color="black" size="2" class="glyphicon glyphicon-th-large">  ${m.module }</font>
                                <span class="glyphicon glyphicon-chevron-up right" style="color:black;"></span>
                            </h4>
                        </div>
                         <div id="${m.url }" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapseListGroupHeading2">
							<ul class="list-group">
							
								<c:forEach items="${m.ams }" var="ms">
								 <li class="list-group-item"><a href="${ms.url }" target="mainFrame"
									class="menu-item-left"><font color="black"  size="2" >${ms.name }</font></a></li>
								</c:forEach>
							</ul>
					
					</div>
					</div>
			</c:if>
			</c:forEach>
					
					 <div class="panel panel-primary leftMenu">
                        <div class="panel-heading" id="collapseListGroupHeading4" data-toggle="collapse" role="tab" style="background:url(images/bg.jpg);" target="mainFrame">
                            <h4 class="panel-title"> <font color="black" size="2" class="glyphicon glyphicon-search">  日志</font> </h4>
					</div>

				</div>

				</div>
			</div>
		</div> --%>
		<ul class="layui-nav layui-nav-tree layui-nav-side" lay-filter="demo">
	<li class="layui-nav-item"><img src="images/view.png" style="margin-left:25%;margin-top:3%;"></li>
		<li class="layui-nav-item"><a href="wind/home.jsp" target="mainFrame">首页</a></li>
		
		<c:forEach items="${menu  }" var="m">
			
			<c:if test="${not empty m.ams }">
		<li class="layui-nav-item"><a href="javascript:;"><i class="layui-icon">&#xe63c;</i> ${m.module }</a>
			<dl class="layui-nav-child">
			  <c:forEach items="${m.ams }" var="ms">
				<dd>
					<a href="${ms.url }" target="mainFrame">${ms.name }</a>			
				</dd>
				</c:forEach>
			</dl></li>
			</c:if>
			
			
			
			</c:forEach>
		
		<li class="layui-nav-item"><a href="error.jsp" target="mainFrame">日志</a></li>
	</ul>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<base href="<%=basePath%>">

<title>与德测试信息管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="script/js/jquery.min.js"></script>
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/bootstrap.min.js"></script>
  <script src="script/ie.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<style>
li {
	list-style: none;
}
.mytable td{border:1px solid #B0C4DE;}
.mytd{background-color:#4682B4;text-align: center;
	vertical-align: middle;}
</style>

<script>
	layui.use('element', function() {
		var element = layui.element();
	});
</script>
<script>
	layui.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

					});
					
</script>
</head>
<body>
<span  style="margin-left:45%;font-size:1.5em;">XX项目计划审批</span>
				<hr/>
				<form class="layui-form" action="" style="margin-left:2%;" onSubmit="return check();">
					
		<table style="width:70%;margin-left:10%" class="mytable">
		<tr><td class="mytd">项目名称</td><td><input type="text" name="" class="layui-input"></td>
		<td class="mytd">客户名称</td><td><input type="text" name="" class="layui-input"></td></tr>
		<tr><td class="mytd">项目等级</td><td><input type="text" name="" class="layui-input"></td>
		<td class="mytd">项目经理</td><td><input type="text" name="" class="layui-input"></td></tr>
		<tr><td class="mytd">测试计划</td><td><input type="text" name="" class="layui-input"></td>
		<td class="mytd">建立时间</td><td><input type="text" name="" class="layui-input"></td></tr>
		<tr><td class="mytd">版本名称</td><td>第         X  版本</td>
		<td class="mytd">审批状态</td><td><input type="text" name="" class="layui-input"></td></tr>
		<tr style="height:80px;"><td class="mytd">备注</td><td colspan="3"><textarea placeholder="请输入内容" name="" class="layui-textarea"></textarea></td></tr>
		<tr style="height:80px;"><td class="mytd" >审批意见</td><td colspan="3"><textarea placeholder="请输入内容" name="" class="layui-textarea"></textarea></td></tr>
		</table>
	
	</form>
	<div class="layui-tab layui-tab-card" 
		style="width:90%;min-width:600px;margin-left:5%;">
		<ul class="layui-tab-title">
			<li class="layui-this">审批意见</li>
			<li>流程图</li>
		</ul>
		<div class="layui-tab-content" >
			<div class="layui-tab-item layui-show" >				
	<!-- 项目相关测试经理   主测  组长    以及上级部门经理  段举  卞刘卫 -->
	<ol><li style="float:left;">测试经理</li><li style="float:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
	<li style="float:left;">接收人:卞刘卫</li><li style="float:right;"><input type="submit" class="btn btn-primary"
								style="border:1px solid black;font-size:0.9em"
								value="通过" /> | <input type="submit" class="btn btn-primary"
								style="border:1px solid black;font-size:0.9em"
								value="不通过" /></li></ol><hr/>
	
	</div>
	<div class="layui-tab-item"><img src="images/liucheng.png"></div>
	</div>
	</div>
	
</body>
</html>

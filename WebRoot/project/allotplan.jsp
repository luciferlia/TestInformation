<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
 <link rel="stylesheet" href="script/dist/css/bootstrap-select.css">
<script src="script/dist/js/bootstrap-select.js"></script>
  <script src="script/ie.js"></script>
 <script src="script/allotplan.js"></script>
  <jsp:include page="/wind/top.jsp"></jsp:include>
<style type="text/css">
.tableplan td{border:1px solid 	#EDEDED;font-size:0.9em;}
.tableplan tr{height:50px;}

 .layui-form-label{width:100px;}
</style>
<script>
layui.use('layer', function(){
  var layer = layui.layer;
 
});  
layui.use([ 'form', 'layedit', 'laydate' ],function() {
						var form = layui.form(), 
						layer = layui.layer, 
						layedit = layui.layedit, 
						laydate = layui.laydate;


					});
</script>
   
</head>

<body onload="showplan();">

	<div style="display:none; position:fixed; right:150px; bottom:400px;background-color:#87CEEB;opacity:.7;border-left:1px solid #A9A9A9;border-top:1px solid #A9A9A9;border-bottom:3px solid #A9A9A9;border-right:3px solid #A9A9A9;border-radius:5px;width:100px;height:50px;" id="tips">
	 </div>
		
<div class="layui-tab layui-tab-card" style="border:1px solid #4682B4;width:95%;heigth:auto;margin-left:2%;">
<ul class="layui-tab-title" style="background-color:#4682B4;">
    <li class="layui-this" style="border:1px solid #4682B4;">任务分配</li></ul>
<div class="layui-tab-content" style="height:auto;">
 <div class="layui-tab-item layui-show"  style="height:auto;">
<form style="margin-left:2%;margin-top:1%;" >
	<input type="hidden" name="id" value="${planForm.plan.project.id}" id="id">
				
			<input type="hidden" name="plan.planId"
					value="${planForm.plan.planId }">
					<table style="width:95%;height:auto;" class="tableplan" id="fen1">
					<tr style="display:none;"><td style="width:100px;">计划ID</td>
					<td ><input type="text" id="pid" name="plan.planId" value="${planForm.plan.planId }"></td>
					
				</tr>
					<tr><td style="width:100px;">项目名称:</td>
						<td>${planForm.plan.project.projectName }</td>
					</tr>
					<tr><td style="width:100px;">项目阶段:</td>
						<td>${planForm.plan.phase }</td>
					</tr>
					<tr><td style="width:100px;">项目版本:</td>
						<td>${planForm.plan.version }</td>
					</tr>

				<tr>
					<td style="width:100px;">测试类型:</td>
					<td>${planForm.plan.casetype.casetypeName}</td>
				</tr>
				<tr>
					<td style="width:100px;">测试内容:</td>

					<td ><input type="text" id="l5" class="layui-input" value="<c:forEach items="${planForm.planCasestores }" var="pc">${pc.casestore.testModule },</c:forEach>" readonly></td>

				</tr>
				<tr>
					<td style="width:100px;">用例条数:</td>
					<td style="white-space: nowrap;"><input type="text" name="plantail.caseCount" style="width:150px;display:inline;" id="l6" class="layui-input" value="${planForm.plan.caseCount }" readonly ></td>
				</tr>
				
				<tr><td style="width:100px;">开始时间:</td>
					<td><input type="text" name="plantail.startTime" class="layui-input"  id="l2"
						onclick="layui.laydate({elem: this, festival: true})" value="${planForm.plan.startTime }"
						readonly></td>
				</tr>
				<tr>
					<td style="width:100px;">结束时间:</td>
					<td><input type="text" name="planForm.plan.endTime"  id="l3"
						class="layui-input"
						onclick="layui.laydate({elem: this, festival: true})" value="${planForm.plan.endTime }"
						readonly></td>
				</tr>
				<tr>
					<td style="width:100px;">备注：</td>
					<td><input type="text" name="plantail.remark" id="l7" class="layui-input" value="${planForm.plan.remark }"></td>
				</tr>
				
					</table>
</form>
<!-- <button type="submit" class="btn btn-primary" style="margin-left:80%;" onclick="submitt()">提交</button> -->
<button type="submit" class="btn btn-primary" style="margin-left:80%;" onclick="checkdata()">提交</button>
</div>
</div>
</div>
<div id="choose1" style="display:none;"></div>

 </body>
</html>

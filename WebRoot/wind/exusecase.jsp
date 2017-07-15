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
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<link rel="stylesheet" href="script/exchange.css">
<style>
td {
	font-size: 0.9em;
	font-color: black;
}
</style>
<script>
	layui
			.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate
					});
	function BodyOnLoad() {
		var textarea = document.getElementById("textarea");
		textarea.style.posHeight = textarea.scrollHeight;
	}
	function check(){
	var a=document.getElementsByName('cases.caseName')[0].value;
	var b=document.getElementsByName('cases.testItem')[0].value;
	var c=document.getElementsByName('cases.description')[0].value;
	var d=document.getElementsByName('cases.environment')[0].value;
	var e=document.getElementsByName('cases.step')[0].value;
	var f=document.getElementsByName('cases.expectResult')[0].value;
	var g=document.getElementsByName('cases.advidceTime')[0].value;
	if(a==""||a==null)
	{
	layer.msg("测试模块不能为空");
	return false;
	}else if(b==""||b==null){
	layer.msg("测试项不能为空");
	return false;
	}else if(c==""||c==null){
	layer.msg("用例描述不能为空");
	return false;
	}
	else if(d==""||d==null){
	layer.msg("预置条件不能为空");
	return false;
	}
	else if(e==""||e==null){
	layer.msg("测试步骤不能为空");
	return false;
	}else if(f==""||f==null){
	layer.msg("预期结果不能为空");
	return false;
	}else if(g==""||g==null){
	layer.msg("耗时不能为空");
	return false;
	}else{
	
	return true;}
	}
</script>

</head>
<body onload="BodyOnLoad()">
	<form action="Case_updateCase" method="post" class="layui-form" onSubmit="return check();">
		<input type="hidden" name="cases.caseId" value="${cases.caseId }">
		<table
			style="width:97%;height:80%;margin-left:1%;margin-top:1%;border: 1px solid #4682B4;">
			<tr style="background-color:#4682B4;height:40px;">
				<td></td>
			</tr>

			<tr>
				<td><input type="hidden" name="typeId" value="${typeId }">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">分类</label>
							<div class="layui-input-inline">
								<input type="text" name="cases.classification"
									value="${cases.classification }" 
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">子模块</label>
							<div class="layui-input-inline">
								<input type="text" name="cases.caseName"
									value="${cases.caseName }"  class="layui-input">
							</div>
						</div>
						</div>
						<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">测试项</label>
							<div class="layui-input-inline">
								<input type="text" name="cases.testItem"
									value="${cases.testItem }"  class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">用例编号</label>
							<div class="layui-input-inline">
								<input type="text" name="cases.num" value="${cases.num }"
									disabled="true" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">重要等级</label>
							<div class="layui-input-inline">
								<%-- <input type="text" name="cases.auxiliaryTool"
									value="${cases.auxiliaryTool }" 
									class="layui-input"> --%>
									<select name="cases.level" value="${cases.level }">
									<option value="p1">p1</option>
									<option value="p2">p2</option>
									<option value="p3">p3</option>
									<option value="p3">p4</option>
									</select>
							</div>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">测试内容</label>
						<div class="layui-input-inline">
							<textarea name="cases.description" class="layui-textarea"
								style="width: 500px;overflow-y:visible;">${cases.description }</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">预置条件</label>
						<div class="layui-input-inline">
							<textarea name="cases.environment" value="${cases.environment }"
								class="layui-textarea"
								style="width: 500px;overflow-y:visible;">${cases.environment }</textarea>
						</div>
					</div>

					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">测试步骤</label>
						<div class="layui-input-inline">
							<textarea name="cases.step" value="${cases.step }"
								class="layui-textarea"  
								style="width: 500px;overflow-y:visible;">${cases.step }</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">预期结果</label>
						<div class="layui-input-inline">
							<textarea name="cases.expectResult"
								value="${cases.expectResult }" class="layui-textarea"
								style="width: 500px;overflow-y:visible;">${cases.expectResult }</textarea>
						</div>
					</div>
					
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">耗时</label>
						<div class="layui-input-inline">
							<ul><li style="float:left;"><input type="text" name="cases.advidceTime"
								value="${cases.advidceTime }" 
								class="layui-input">&nbsp;&nbsp;</li><li style="float:left;margin-top:10px;">&nbsp;&nbsp;min</li></ul>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">备注</label>
						<div class="layui-input-inline">
							<textarea name="cases.remark"
								value="${cases.remark }" class="layui-textarea"
								style="width: 500px;overflow-y:visible;">${cases.remark }</textarea>
						</div>
					</div>
					</td>
			</tr>
			<tr style="height:40px;">
				<td><button type="submit" style="margin-left:70%;"
						class="layui-btn layui-btn-normal layui-btn-radius">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="reset" href="wind/usecase.jsp" target="mainFrame"
						class="layui-btn layui-btn-normal layui-btn-radius">返回</button></td>
			</tr>
		</table>
	</form>
	<script src="script/js/autosize.js"></script>
	<script>

		autosize(document.querySelectorAll('textarea'));

	</script>
</body>
</html>

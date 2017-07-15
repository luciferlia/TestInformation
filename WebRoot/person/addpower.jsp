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

<title>与德测试信息管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<jsp:include page="/admin/adminTop.jsp"></jsp:include>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<script src="script/page.js"></script>
<style>
#wai {
	border: 1px solid #4682B4;
}

#table td {
	font-size: 0.9em;
	font-color: black;
	text-align: center;
	vertical-align: middle;
}
#table2 td {
	font-size: 0.9em;
	font-color: black;
	text-align: center;
	vertical-align: middle;
}
li {
	list-style: none;
}

</style>
<script type="text/javascript">
	function console(consoleTag, caseId, stateTag) {
		//alert("sdkl");
		var checkedSubject = $('#table input[name=checkedId]:checkbox:checked');
		var checkedSubject2 = $('#table2 input[name=checkedId1]:checkbox:checked');
		var checkedIds = "";
		var checkedIds2 = "";
		//循环获取选中的复选框的value，这个value是数据表中每条记录的主键${cd.caseId }，传给后台，后台就能根据主键查找到数据表的相应记录
		//将其value用逗号隔开拼接成一个字符串
		checkedSubject.each(function() {
			checkedIds = checkedIds + "," + $(this).val();
		});
		//alert(checkedIds);
		
		checkedSubject2.each(function() {
			checkedIds2 = checkedIds2 + "," + $(this).val();
		});
		//alert(checkedIds2);
		layer.confirm('确定授权?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                //table表中选中的复选框赋值给checkedSubject


			//上面的字符串赋值给隐藏域表单
			$('#checkedIds').val(checkedIds);
			$('#checkedIds2').val(checkedIds2);

			//
			if ("save" == consoleTag) {
				$('#formid').prop("action", "role_rolePower?id=${id}");
			}
			//提交隐藏域表单，后台才能获取隐藏域表单的值
			$('#formid').submit();
                                
                                },function(index){ layer.close(index);
								return false;
								});
		
	}
	
</script>
<script>
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form()
  ,layer = layui.layer
});
</script>
<script>
	layui.use('element', function() {
		var element = layui.element();
	});
</script>

</head>
<body>

	<div class="layui-tab layui-tab-card"
		style="width:99%;height:60%;min-width:600px;min-height:600px;margin-left:1%;">
		<ul class="layui-tab-title">
			<li class="layui-this">菜单权限</li>
			<li>界面权限</li>
		</ul>
		<div class="layui-tab-content" >
			<div class="layui-tab-item layui-show" style="height:400px;">
				
				<form class="layui-form">
					<table id="table"
						style="margin-left:1%;width:95%;min-width:300px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all"
						class="layui-table">

						<tr style="background-color:#4682B4;height:40px;" id="show_tab_one">
							<td style="width:50px;">ID</td>
							<td>一级菜单</td>
							<td>名称</td>
							<td>URL</td>
							<td>状态</td>
						</tr>
						<tbody id="show_tab_tr">
							<c:forEach items="${roleMenus }" var="rm">
								<tr>
									<td>${rm.id }</td>
									<td>${rm.module.moduleName }</td>
									<td>${rm.name }</td>
									<td>${rm.url }</td>
									<td><c:if test="${rm.check==true }">
									  <div class="layui-input-block">
									  <input type="checkbox" name="checkedId" id="checkedId" value="${rm.id }" class=" id-checkbox" checked="checked" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF"/>
									  </div>
										</c:if> <c:if test="${rm.check==false }">
										 <div class="layui-input-block">
										<input type="checkbox" name="checkedId" id="checkedId" value="${rm.id }" class=" id-checkbox" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF"/>
										 </div>										
										</c:if></td>
				
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</form>
					 <table style="margin-left:1%;width:95%;min-width:300px;font-size:0.9em;"><tr><td>
                    共&nbsp;&nbsp;<span id="page" ></span>&nbsp;&nbsp;页&nbsp;&nbsp;|&nbsp;&nbsp;第&nbsp;&nbsp;<span id="curPage1"></span>&nbsp;&nbsp;页
                     <span style="float:right;">
                    <input id="lastpage" type="button" value="上一页" >
   <input id="nextpage" type="button" value="下一页">
   <select id="curPage"></select>
    <input id="npage" type="button" value="确定" class="btn btn-primary btn-xs" style="border:1px solid black;"></span>
   
    </td></tr></table>
		
			</div>
			<div class="layui-tab-item" style="height:400px;">
			
				<form class="layui-form">
					<table id="table2"
						style="margin-left:1%;width:95%;min-width:300px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all"
						class="layui-table">

						<tr style="background-color:#4682B4;height:40px;" id="show_tab_one2">
							<td style="width:50px;">ID</td>
							<td>名称</td>
							<td>URL</td>
							<td>所在页面</td>
							<td>备注名称</td>
							<td>状态</td>
						</tr>
						<tbody id="show_tab_tr2">
							<c:forEach items="${roleFunctions }" var="rfs">
								<tr>
									<td>${rfs.functionId }</td>
									<td>${rfs.name }</td>
									<td>${rfs.url }</td>
									<td>${rfs.jsp }</td>
									<td>${rfs.description }</td>
									<td><c:if test="${rfs.check==true }">
										  <div class="layui-input-block">
											<input type="checkbox" name="checkedId1" id="checkedId1" value="${rfs.functionId }" class=" id-checkbox" checked="checked" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF"/>
											</div>
										</c:if> <c:if test="${rfs.check==false }">
									 <div class="layui-input-block">
											<input type="checkbox" name="checkedId1" id="checkedId1" value="${rfs.functionId }" class=" id-checkbox" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF"/>
									</div>	
										</c:if></td>
								</tr>

							</c:forEach>
						</tbody>


					</table>
					</form>
					 <table style="margin-left:1%;width:95%;min-width:300px;font-size:0.9em;"><tr><td>
                    共&nbsp;&nbsp;<span id="page2" ></span>&nbsp;&nbsp;页&nbsp;&nbsp;|&nbsp;&nbsp;第&nbsp;&nbsp;<span id="curPage12"></span>&nbsp;&nbsp;页
                     <span style="float:right;">
                    <input id="lastpage2" type="button" value="上一页" >
   <input id="nextpage2" type="button" value="下一页">
    <!-- <input id="curPage" type="text" size="5"> --><select id="curPage2"></select>
    <input id="npage2" type="button" value="确定" class="btn btn-primary btn-xs" style="border:1px solid black;"></span>
   
    </td></tr></table>
				
				
			</div>
		</div>
		
	</div>
	<button class="layui-btn layui-btn-normal layui-btn-radius"
					style="float:right;" onclick="console('save','')">确定</button>
	<%-- 隐藏域表单 --%>
	<form action="" method="post" id="formid">
	<input type="hidden" name="checkedIds" id="checkedIds" />
	<input type="hidden" name="checkedIds2" id="checkedIds2" />
	</form>
</body>
</html>

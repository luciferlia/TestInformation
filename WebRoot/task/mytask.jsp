<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

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

<link rel="stylesheet" href="script/css/bootstrap.min.css">
<link rel="stylesheet" href="script/exchange.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
 <link rel="stylesheet" href="script/dist/css/bootstrap-select.css">
<script src="script/dist/js/bootstrap-select.js"></script>

<script src="script/ie.js"></script>
<style>
li {
	list-style: none;
}

td {
	font-size: 0.9em;
	font-color: black;
	text-align: center;
	vertical-align: middle;
}

.table input {
	heigth: 30px;
}

.table td {
	heigth: 40px;
}

.mytest {
	display: -moz-inline-box;
	display: inline-block;
	width: 125px;
}
</style>
<script>
	layui.use('element', function() {
		var element = layui.element();
	});
</script>
<script src="script/mytask.js"></script>
<script>
	layui.use([ 'form', 'layedit', 'laydate' ], function() {
		var form = layui.form(),
			layer = layui.layer,
			layedit = layui.layedit,
			laydate = layui.laydate;


	});

	function checkTestCase(planId, casestoreName) {
		//alert(casestoreName);
		$.ajax({
			type : "POST",
			url : "checkTestCase?planId=" + planId+"&name="+casestoreName,
			success : function(data) {
				if (data == 'success') {
					window.location.href = "task_testCase?planId=" + planId + "&name=" + casestoreName;
				}else if(data=='no'){
					layer.msg("你没有操作该测试内容的权限",{time:1000});
				} else {
					layer.confirm('该测试内容不在自己任务里，是否进行分配给自己测试？', {
						icon : 3,
						title : '提示'
					}, function(index) {
						layer.close(index);
						$.ajax({
							type : "POST",
							url : "deliverMy?planId=" + planId + "&name=" + casestoreName,
							success : function(data) {
								if (data == 'success') {
									window.location.href = "task_testCase?planId=" + planId + "&name=" + casestoreName;
								}else{
									layer.msg("你没有操作该测试内容的权限",{time:1000});
								}

							},
							error : function(xhx, e, errMsg) {
								alert(errMsg);
							}
						});
					}, function(index) {
						layer.close(index);
						return false;
					});
				}

			},
			error : function(xhx, e, errMsg) {
				alert(errMsg);
			}
		});
	}

	$(document).ready(function() {
		var row = document.getElementById('table').rows.length;

		for (var i = 1; i < row; i++) {

			if ($("#table tr:eq('" + i + "') td:eq(11)").text() == '不可分配') {
				$("#table tr:eq('" + i + "') td:eq(15) a:eq(1)").removeAttr('onclick');
				$("#table tr:eq('" + i + "') td:eq(11)").css("color", "red");
			}

		}
	});
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<body>
	<h3 style="margin-left:1%;"
		class="glyphicon glyphicon-tasks">任务安排</h3>
		<input type="hidden" id="pid"  value="${planId }">
	<hr />
	<div class="layui-tab layui-tab-card"
		style="width:95%;height:auto;min-width:600px;min-height:400px;margin-left:1%;">
		<ul class="layui-tab-title">

			<li class="layui-this ">我的任务</li>

		</ul>
		<!-- ###########################################主测和测试经理的工作台####################################### -->
		<div class="layui-tab-content" style="height:auto;">


			<div class="layui-tab-item layui-show" style="height:auto;">
				<table id="table"
					style="width:95%;TABLE-LAYOUT:fixed;margin-left:1%"
					class="layui-table">
					<tr style="background-color:#4682B4;height:40px;color: white;">
						<td style="width:50px;"><h4>序号</h4></td>
						<td style="display:none"></td>
						<td  style="width:70px;"><h4>项目名称</h4></td>
						<td  style="width:70px;"><h4>项目阶段</h4></td>
						<td  style="width:70px;"><h4>软件版本</h4></td>
						<td><h4>版本类型</h4></td>
						<td><h4>测试类型</h4></td>

						<td style="width:20%;"><h4>测试内容</h4></td>

						<td  style="width:70px;"><h4>用例条数</h4></td>
						<td><h4>开始时间</h4></td>
						<td><h4>结束时间</h4></td>
						<td><h4>分配状态</h4></td>
						<td><h4>已执行用例条数</h4></td>
						<!-- <td><h4>版本路径</h4></td>						
						<td><h4>BUG库路径</h4></td> -->

						<td  style="width:70px;"><h4>完成进度</h4></td>
						<td style="width:8%;min-width:100px;"><h4>备注</h4></td>
						<td><h4>操作</h4></td>
					</tr>
					<tbody id="cdTb">
						<%
							int i = 1;
						%>
						<s:iterator value="pageBean.list">
							<tr style="height:30px;">
						<td><%=i++ %></td>
						<td style="display:none;"><s:property value="plantail.plan.planId"/></td>
						<td><s:property value="plantail.plan.project.projectName"/></td>
						<td><s:property value="plantail.plan.phase"/></td>
						<td><s:property value="plantail.plan.version"/></td>
						<td><s:property value="plantail.plan.versionType"/></td>
						<td><s:property value="plantail.plan.casetype.casetypeName"/></td>
						<td><s:iterator value="plancasestores">
						<div  name="testmo" style="float:left;"><s:if test='%{tester!="my"&&tester!="other"}'><a onclick="checkTestCase(<s:property value='plantail.plan.planId'/>,'<s:property value='casestore.testModule'/>')"><s:property value="casestore.testModule" />,</a></s:if></div>
							<s:elseif test='%{tester=="other"}'>
											<font color="red"><s:property value="casestore.testModule" />,</font>
											</s:elseif>
											<s:else>
											<a onclick="checkTestCase(<s:property value='plantail.plan.planId'/>,'<s:property value='casestore.testModule'/>')"><font color="green"><s:property value="casestore.testModule" />,</font></a>
											</s:else>
											</s:iterator></td>	
						<td><s:property value="plantail.caseCount"/></td>
						
						<td><s:property value="plantail.startTime"/></td>
						<td><s:property value="plantail.endTime"/> </td>
						<td><s:property value="plantail.state"/></td>
						  <td>  <s:property value="plantail.finishCase" /></td>
						<td><s:property value="plantail.finishDegree"/></td>
						<td style="overflow:hidden;text-overflow: ellipsis;white-space: nowrap;" onmouseover="showmark(this,event);"><s:property value="plantail.plan.remark"/></td>
						
						<td><a href="task_showmTaskDetail?id=<s:property value="plantail.id"/>"> 详情  </a><c:if test="${not empty permissionForm.add }">|<a onclick="updateshow(this);" > 分配 </a></c:if>

					</tr>
					</s:iterator>
					</tbody>
				</table>
				<table style="width:95%;margin-left:1%;">
					<tr>
						<td><span style="float:left;">共<span><s:property
										value="pageBean.totalPage" /></span>页|第<span><s:property
										value="pageBean.currentPage" /> </span>页
						</span></td>
						<td><span style="float:right;">
									<form action="Plantail_showMasterTask" class="right-font08">
									[
									<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
									<s:else>
										<a href="Plantail_showMasterTask?page=1" class="right-font08">首页</a> | <a
											href="Plantail_showMasterTask?page=<s:property value="%{pageBean.currentPage-1}"/> "
											class="right-font08">上一页</a>
									</s:else>
									|
									<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
										<a
											href="Plantail_showMasterTask?page=<s:property value="%{pageBean.currentPage+1}"/> "
											class="right-font08">下一页</a> | <a
											href="Plantail_showMasterTask?page=<s:property value="%{pageBean.totalPage}"/> "
											class="right-font08">末页</a>
									</s:if>
									<s:else>下一页 | 末页</s:else>
									] 转至：<select name="page" style="width:50px;">
										<s:iterator value="pageBean.count">
											<option value="<s:property value="count"/>"><s:property
													value="count" />
											</option>
										</s:iterator>
									</select> <input type="submit" class="btn btn-primary btn-xs" value="确定"
										style="border:1px solid black">
								</form></span></td>
					</tr>
				</table>
			</div>

		</div>
	</div>
	<!-- #################################################显示弹框 分配任务####################################### -->
	<div id="hid"></div>
	<div style="display:none" id="showee">

		<!-- <form action="task_deliverTask" method="post" id="formid" style="width:100%;height:100%;">
			 -->
		<table
			style="TABLE-LAYOUT:fixed;height:auto;width:100%;"
			class="table" id="fen1">

			<tr style="display:none">
				<td style="width:100px;"><input type="text" id="l1"
					name="plantail.plan.planId"></td>
				<td></td>
			</tr>
	        <tr style="display:none">
				<td style="width:100px;"></td>
				<td></td>
			</tr>
            <tr style="display:none">
				<td style="width:100px;"></td>
				<td></td>
			</tr>
			<tr style="display:none">
				<td style="width:100px;"></td>
				<td></td>
			</tr>
			<tr>
				<td style="width:100px;">测试类型:</td>
				<td><input type="text" id="l4" class="layui-input" readonly></td>
			</tr>
			<tr>
				<td style="width:100px;">测试内容:</td>
				<td><input type="text" id="l5" class="layui-input"
					name="module" readonly></td>
			</tr>
			<tr>
				<td style="width:100px;">用例条数:</td>
				<td><input type="text" name="plantail.caseCount" value=""  style="width:150px;"
					id="l6" class="layui-input" readonly></td>
			</tr>

			<tr>
				<td style="width:100px;">开始时间:</td>
				<td ><input type="text"
					name="plantail.startTime" class="layui-input" id="l2" readonly></td>
			</tr>
			<tr>
				<td style="width:100px;">结束时间:</td>
				<td><input type="text" name="plantail.endTime" lay-verify=""
					class="layui-input" id="l3" readonly></td>
			</tr>
			<tr style=" background-color:white;">
				<td style="width:100px;">备注：</td>
				<td><input type="text" name="plantail.remark" id="l7" value=""
					class="layui-input"></td>
			</tr>
			<!-- <tr>
				<td style="width:16%;">隐藏域</td>
				<td><input type="text" value="" id="trdata" name="arrsum"
					class="layui-input" readonly></td>

			</tr> -->
		</table>
		<!-- </form> -->
	</div>




	<!-- #################################################显示弹框 导入测试结果文件####################################### -->
	<div id="exportf" style="dispaly:none;" class="mydiv">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hidd()">
			<span><font color="black"><img alt=""
					src="images/del.gif"></font></span>
		</div>
		<form style="width:100%;height:100%;" method="post"
			action="task_importCaseResult" onSubmit="return checkfile();"
			enctype="multipart/form-data">
			<table
				style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;font-size:0.9em;">
				<tr
					style="background-color:#4682B4;height:40px;text-align:center;vertical-align:middle;">
					<td colspan="2">导入测试结果</td>
				</tr>
				<tr>
					<td></td>
					<td><input id="hhh" value="" type="hidden" name="planId"></td>
				</tr>
				<tr>
					<td><input type="file" name="file" accept=".xls,.xlsx"
						id="file" onChange="validateFile(this)"></td>
				</tr>
				<tr>
					<td colspan="2" style="color:red;font-size: xx-small;">(注：文件类型必须选择.xls格式或.xlsx格式)</td>
				</tr>
				<tr>
					<td colspan="2">导入的文件的第<input style="width:40px"
						onkeyup="sounts(this)" name="num" value="1" />页&nbsp;&nbsp; 第<input
						style="width:40px;" onkeyup="sounts(this)" name="rowNum" value="2" />行
					</td>
				</tr>
				<tr>
					<td colspan="2" style="color: red;font-size: xx-small;">(注：页数必须大于0，行必须大于1)</td>
				</tr>
				<tr
					style="background-color:#4682B4;height:40px;text-align:center;vertical-align:middle;">
					<td colspan="2"><input type="submit" value="导入"
						class="btn btn-primary" style="border:1px solid black;width:60px;">&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="button" class="btn btn-primary"
						style="border:1px solid black;width:60px;" value="取消"
						onclick="hidd()"></td>
				</tr>
			</table>
		</form>

	</div>

	<div id="choose1" style="display:none;"></div>
     <div id="remark" style="display:none;font-size:0.9em;"> </div>
	<script src="script/allotplan.js"></script>
	<script>
 
 
 $(window).resize(function (){
    $("#hid").css({
    width:$(window).width(),
    height:$(document).height()
    });
});

 function show(obj) {//上传弹出框
		
		var a = document.getElementById("exportf");
		var b = document.getElementById("hid");
		a.style.display = "block";
		b.style.display = "block";
    
      var jj=document.getElementById('hhh');
      jj.value=obj;
	}
	function hidd(){
		var a = document.getElementById("exportf");
		var b = document.getElementById("hid");
		a.style.display = "none";
		b.style.display = "none";
	}
 function validateFile(target){//校验上传的文件的合法性
var fileSize=0;
if(!target.files)
{
var filePath=target.value;
var fileSystem=new ActiveXObject("Scripting.FileSystemObject");
var file=fileSystem.GetFile(filePath);
fileSize=file.size;
}else{
fileSize=target.files[0].size;
}
var size=fileSize/1024;
if(size>9000)
{
alert("附件不能大于9M");
target.value="";
return
}
var name=target.value;
var fileName=name.substring(name.lastIndexOf(".")+1).toLowerCase();
if(fileName !="xls" && fileName !="xlsx")
{
alert("只能上传excel格式的文件");
target.value="";
return
}
}
	function checkfile(){//校验是否上传了文件
	
	var file=document.getElementById('file').value;
	if(file==""||file==null)
	{
	layer.msg("请先选择文件");
	return false;
	}
	else{
	//alert("success");
	return true;
	
	}
	}
layui.use('laydate', function(){
  var laydate = layui.laydate;
  
  var start = {
    min: laydate.now()
    ,max: '2099-06-16 23:59:59'
    ,istoday: false
    ,choose: function(datas){
      end.min = datas; //开始日选好后，重置结束日的最小日期
      end.start = datas //将结束日的初始值设定为开始日
    }
  };
  
  var end = {
    min: laydate.now()
    ,max: '2099-06-16 23:59:59'
    ,istoday: false
    ,choose: function(datas){
      start.max = datas; //结束日选好后，重置开始日的最大日期
    }
  };
  
  document.getElementById('l2').onclick = function(){
    start.elem = this;
    laydate(start);
  }
  document.getElementById('l3').onclick = function(){
    end.elem = this;
    laydate(end);
  }
 
});

</script>

</body>
</html>
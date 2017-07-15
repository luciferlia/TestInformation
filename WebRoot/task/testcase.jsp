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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<script src="script/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/exchange.css">
<style media=print type="text/css">  
.noprint{visibility:hidden}  
</style>  
<style type="text/css">
li {
	list-style: none;
	float:left;
}

#bord {
	border: 1px solid #4682B4;
	width: 1600px;
	height: 600px;
	margin-left: 10px;
	margin-top: 10px;
	background-color: #FDF5E6;
}

#showcheck td {
	border: 1px solid #EDEDED;
}

a {
	font-size: 0.9em;
}

a:hover {
	color: red;
}

td {
	font-size: 0.9em;
}
</style>
<script type="text/javascript">

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
	function console(consoleTag, caseId, stateTag) {
		var checkedSubject = $('#showcheck input[name=checkedId]:checkbox:checked');
		var checkedIds = "";
		//循环获取选中的复选框的value，这个value是数据表中每条记录的主键${cd.caseId }，传给后台，后台就能根据主键查找到数据表的相应记录
		//将其value用逗号隔开拼接成一个字符串
		checkedSubject.each(function() {
			checkedIds = checkedIds + "," + $(this).val();
		});
		//alert(checkedIds);
		if ((checkedIds == "" || checkedIds == null || checkedIds == 0)&&consoleTag!='export') {
			alert("请勾选要操作的用例");
		} else {
			var f=false;
			if ("delete" == consoleTag) {
			
			 layer.confirm('确定删除选中用例?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               //上面的字符串赋值给隐藏域表单
				$('#checkedIds').val(checkedIds);
				
				//删除
				if ("delete" == consoleTag) {
					$('#formid').prop("action", "Case_deleteCase?typeId=${id}");
				}
				
				//提交隐藏域表单，后台才能获取隐藏域表单的值
				$('#formid').submit();
                                
                                },function(index){ layer.close(index);
								return false;
								});
			
			}
		
			
			
		}
	}
	
	
function export1(vall){
layer.confirm('确定导出此模块用例结果?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                alert(vall);
                                window.location.href="task_exportCaseResult?planId="+vall;
								},function(index){ layer.close(index);
								return false;
								});
}

</script>
<script>
	$(function() {
		var all_checked = false;
		$(":checkbox").click(function() {
			var table = $(this).parents("table");
			if ($(this).attr("id") === "all") {
				table.find(":checkbox").prop("checked", !all_checked);
				all_checked = !all_checked;
			} else {
				table.find(":checkbox[id!=all]").each(function(i) {
					if (!$(this).is(":checked")) {
						table.find("#all").prop("checked", false);
						all_checked = false;
						return false;
					}
					$("#all").prop("checked", true);
					all_checked = true;
				});
			}
		});
	});
</script>
</head>

<body>


<table style="width:80%;margin-left:5%;"><tr><td>
	<ul style="margin-top:2%;" class="noprint">
		<li style="float:left;"><c:if test="${not empty permissionForm.delete }"><button onclick="console('delete','')"
				class="btn btn-danger">${permissionForm.delete }</button></c:if></li>
			<li style="float:left;">
					<button type="submit" class="btn btn-info" onclick="export1(${planId})">导出</button>
					<button  onclick="show()" class="btn btn-info">导入</button>			
			</li>
	</ul></td></tr></table>
	<hr/>
	<table id="showcheck"
		style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;">
		<tr style="background-color:#4682B4;height:40px;">
			<td style="text-align:center;vertical-align:middle;width:50px;" class="noprint"><h4>
					<font color="white"><input type="checkbox" name="all"
						id="all" /></font>
				</h4></td>
				<td style="text-align:center;vertical-align:middle;width:100px;"><h4>
					<font color="white">用例编号</font>
				</h4></td>
				<td style="text-align:center;vertical-align:middle;width:100px;"><h4>
					<font color="white">分类</font>
				</h4></td>
				<td style="text-align:center;vertical-align:middle;width:100px;"><h4>
					<font color="white">子模块</font>
				</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>
					<font color="white">测试项</font>
				</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>
					<font color="white">测试内容</font>
				</h4></td>
			<td style="text-align:center;vertical-align:middle;width:100px;"><h4>
					<font color="white">重要等级</font>
				</h4></td>
			<td style="text-align:center;vertical-align:middle;width:100px;"><h4>
					<font color="white">建议耗时</font>
				</h4></td>
			<td style="text-align:center;vertical-align:middle;width:100px;"><h4>
					<font color="white">测试结果</font>
				</h4></td>
			<td style="text-align:center;vertical-align:middle;" class="noprint"><h4>
					<font color="white">操作</font>
				</h4></td>
		</tr>
		<tbody id="cdTb">
			<s:iterator value="pageBean.list">
				<tr style="height:40px;">
					<td style="text-align:center;vertical-align:middle;" class="noprint"><input
						type="checkbox" name="checkedId" id="checkedId"
						value=<s:property value="caseId"/> class=" id-checkbox" />
			</td>
		 	<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.num" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.classification" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.caseName" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.testItem" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.description " /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.level" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.advidceTime" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="blue"><s:if test="%{result=='pass'||result=='Pass'}"><s:property value="result"/></s:if></font><s:else><font
				color="red"><s:property value="result"/></font></s:else></td>
			<td style="text-align:center;vertical-align:middle;" class="noprint"><a
				href="Case_showTestCase?caseId=<s:property value="cases.caseId"/>&tag=1&planId=<s:property value="plan.planId"/>">查看</a></td>
			</tr>
			</s:iterator>
			</tbody>
			</table>
			<table style="width:80%;margin-left:5%;" class="noprint">
				<tr>
					<td ><span style="float:left;">共<span><s:property
								value="pageBean.totalPage" /></span>页|第<span><s:property
								value="pageBean.currentPage" /> </span>页</span>
					</td>
					<td><span style="float:right;"><form action="task_testCase?page=1" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="task_testCase?page=1" class="right-font08">首页</a> | <a
									href="task_testCase?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="task_testCase?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="task_testCase?page=<s:property value="%{pageBean.totalPage}"/>"
									class="right-font08">末页</a>
							</s:if>
							<s:else>下一页 | 末页</s:else>
							] 转至：<select name="page" style="width:50px;">
								<s:iterator value="pageBean.count">

									<option value="<s:property value="count"/>"><s:property
											value="count" />
									</option>
								</s:iterator>
							</select>
							<%-- <input type="text" style="width:50px;" name="page" /> --%>
							<button type="submit">确定</button>
						</form></span></td>
				</tr>
			</table>
			<%-- 隐藏域表单 --%>
			<form action="" method="post" id="formid">
				<input type="hidden" name="caseId" id="caseId" /> <input
					type="hidden" name="checkedIds" id="checkedIds" />
			</form>
			<div id="hid"></div>
<!-- #################################################显示弹框 导入测试结果文件####################################### -->
	<div id="exportf" style="dispaly:none;" class="mydiv">
	<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hidd()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
	<form style="width:100%;height:100%;" method="post" action="task_importCaseResult" onSubmit="return checkfile();" enctype="multipart/form-data">
		<input type="hidden" value="${planId }" name="planId">
	<table
			style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
			<tr
				style="background-color:#4682B4;height:40px;text-align:center;vertical-align:middle;color: white;">
				<td colspan="2">导入测试结果</td>
			</tr>
			<%-- <tr><td></td><td><input id="hhh" value="${planId}" type="text" name="planId"></td></tr> --%>
			<tr>
				<td><input type="file" name="file" accept=".xls,.xlsx"  id="file" onChange="validateFile(this)"></td>
			</tr>
			<tr>
					<td colspan="2" style="color:red;font-size: xx-small;">(注：文件类型必须选择.xls格式或.xlsx格式)</td>
			</tr>
			<tr>
				<td colspan="2">导入的文件的第<input style="width:40px" 
					onkeyup="sounts(this)" name="num" value="1"/>页&nbsp;&nbsp; 第<input style="width:40px;"
					onkeyup="sounts(this)" name="rowNum" value="2"/>行
				</td>
			</tr>
			<tr><td colspan="2" style="color: red;font-size: xx-small;">(注：页数必须大于0，行必须大于1)</td></tr>
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
	<script>
	function show(obj) {//上传弹出框   //href="task_importCaseResult?planId=<s:property value="plan.planId"/>" 
		
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
	</script>
</body>
</html>

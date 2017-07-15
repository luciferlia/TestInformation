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
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange1.js"></script>
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<style media=print type="text/css">  
.noprint{visibility:hidden}  
</style>  
<style type="text/css">
li {
	list-style: none;
	float:left;
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
	function console(consoleTag, id, stateTag) {
		var checkedSubject = $('#showcheck input[name=checkedId]:checkbox:checked');
		var checkedIds = "";
		//循环获取选中的复选框的value，这个value是数据表中每条记录的主键${cd.caseId }，传给后台，后台就能根据主键查找到数据表的相应记录
		//将其value用逗号隔开拼接成一个字符串
		checkedSubject.each(function() {
			checkedIds = checkedIds + "," + $(this).val();
		});
		if (checkedIds == "" || checkedIds == null || checkedIds == 0) {
			layer.msg("请勾选要操作的用例");
		} else {
			var f=false;
			if ("delete" == consoleTag) {
			}
			layer.confirm('确定删除选中用例?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                $('#checkedIds').val(checkedIds);

				//删除
				if ("delete" == consoleTag) {
					$('#formid').prop("action", "Case_deletecusCase");
				}
				//提交隐藏域表单，后台才能获取隐藏域表单的值
				$('#formid').submit();
                                
                },function(index){ layer.close(index);
								return false;
								});
			
		}
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
	
	function updatefile(){
	var a=document.getElementById('updatefile');
	var b=document.getElementById('hid');
	a.style.display='block';
	b.style.display='block';
	}
	function hidefile(){
	var a=document.getElementById('updatefile');
	var b=document.getElementById('hid');
	a.style.display='none';
	b.style.display='none';
	}
	function checkfile(){
	
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
</head>

<body>
<table style="width:80%;margin-left:5%;margin-top:1%;"><tr><td>
	<ul>
		<li style="float:left;"><c:if test="${not empty permissionForm.upload }"><button
			class="btn btn-primary" onclick="updatefile()">${permissionForm.upload }</button></c:if></li>
		<li style="float:left;margin-left:5%;"><c:if test="${not empty permissionForm.add }"><a
			href="Case_pram?id=${id }" type="button" class="btn btn-primary">${permissionForm.add }</a></c:if></li>
		<li style="float:left;"><c:if test="${not empty permissionForm.delete }"><button onclick="console('delete','')"
			class="btn btn-danger">${permissionForm.delete }</button></c:if></li>
	</ul></td></tr></table>
	<hr/>
	<table id="showcheck"
		style="margin-left:5%;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;" class="table table-condensed table-bordered table-hover table-striped">
		<tr style="background-color:#4682B4;height:40px;color:white;">
		<td style="text-align:center;vertical-align:middle;width:50px;" class="noprint"><h4><input type="checkbox" name="all" id="all" /></h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>序号</h4></td>
			<td style="text-align:center;vertical-align:middle;display:none"><h4>ID</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>用例名称</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>URL</h4></td>
			<td style="text-align:center;vertical-align:middle;" class="noprint"><h4>操作</h4></td>
		</tr>
			<% int i=1; %>
		<tbody id="cdTb">
			<s:iterator value="pageBean.list">
			<tr style="height:40px;">
			<td style="text-align:center;vertical-align:middle;" class="noprint"><input
				type="checkbox" name="checkedId" id="checkedId"
				value=<s:property value="id"/> class=" id-checkbox" />
			</td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><%=i++ %></font></td>
			<td style="text-align:center;vertical-align:middle;display:none;"><font
				color="black"><s:property value="id" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cuscaseName" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="fileUrl" /></font></td>
			<td style="text-align:center;vertical-align:middle;" class="noprint"><a
				href="">上传</a> |
				<a href="Case_downloadCase?id=${id }&cuscaseId=<s:property value="id"/>">下载</a></td>
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
					<td><span style="float:right;"><form action="Case_showcusCaseDetail" class="right-font">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="Case_showcusCaseDetail?page=1" class="right-font08">首页</a> | <a
									href="Case_showcusCaseDetail?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="Case_showcusCaseDetail?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="Case_showcusCaseDetail?page=<s:property value="%{pageBean.totalPage}"/>"
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
							<input class="btn btn-primary btn-xs" style="border:1px solid black;" value="确定"  type="submit">
						</form></span></td>
				</tr>
			</table>
			
			<div id="hid"></div>
			<!-- ################################上传#################################################### -->
			
			<div id="updatefile" class="mydiv" style="display:none">
			<div align="right"
				style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hidefile()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span></div>
			<form style="width:100%;height:100%;" action="Case_uploadCase" method="post" enctype="multipart/form-data" onSubmit="return checkfile();">
				<input type="hidden" value="${id }" name="id">
			<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
			<tr
				style="background-color:#4682B4;height:40px;text-align:center;vertical-align:middle;color: white;">
				<td colspan="2">上传文件</td>
			</tr>
		<tr>
				<td ><input type="file" name="file" id="file"></td>
			</tr>
		<tr
				style="background-color:#4682B4;height:40px;text-align:center;vertical-align:middle;">
				<td colspan="2"><input type="submit" value="上传" name="btnUpload"
					class="btn btn-primary" style="border:1px solid black;width:60px;">&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="button" class="btn btn-primary"
					style="border:1px solid black;width:60px;" value="取消"
					onclick="hidd()"></td>
			</tr>
			</table>
			</form>
		</div>
		<%-- 隐藏域表单 --%>
			<form action="" method="post" id="formid">
				<input type="hidden" name="id" id="id" /> <input
					type="hidden" name="checkedIds" id="checkedIds" />
			</form>
			<div id="hid"></div>
			
</body>
</html>

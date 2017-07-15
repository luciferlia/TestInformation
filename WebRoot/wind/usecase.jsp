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
.noprint {
	visibility: hidden
}
</style>
<style type="text/css">
li {
	list-style: none;
	float: left;
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
	function console(consoleTag, caseId, stateTag) {
		var checkedSubject = $('#showcheck input[name=checkedId]:checkbox:checked');
		var checkedIds = "";
		//循环获取选中的复选框的value，这个value是数据表中每条记录的主键${cd.caseId }，传给后台，后台就能根据主键查找到数据表的相应记录
		//将其value用逗号隔开拼接成一个字符串
		checkedSubject.each(function() {
			checkedIds = checkedIds + "," + $(this).val();
		});
		//alert(checkedIds);
		if ((checkedIds == "" || checkedIds == null || checkedIds == 0) && consoleTag != 'export') {
			layer.msg("请勾选要操作的用例");
		} else {
			var f = false;
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


	function export1(vall) {
		layer.confirm('确定导出此模块用例?', {
			icon : 3,
			title : '提示'
		}, function(index) {
			layer.close(index);

			window.location.href = "Case_exportCase?typeId=" + vall;
		}, function(index) {
			layer.close(index);
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


	<table style="width:80%;margin-left:5%;margin-top:1%;">
		<tr>
			<td>
				<ul class="noprint">
					<li style="float:left;"><c:if
							test="${not empty permissionForm.add }">
							<a href="Case_pram?id=${id }" type="button"
								class="btn btn-primary">${permissionForm.add }</a>
						</c:if></li>
					<li style="float:left;"><c:if
							test="${not empty permissionForm.delete }">
							<button onclick="console('delete','')" class="btn btn-danger">${permissionForm.delete }</button>
						</c:if></li>

					<span style="float:right;">
						<form action="Case_selectCase2" role="form">
							<li style="float:left;"><input type="hidden" name="id"
								value="${id }"></li>
							<li style="float:left;"><input type="hidden" name="page"
								value="1"></li>
							<li style="float:left;"><font size="5">重要级别：</font><select
								name="level" style="width:100px;height:30px;">
								
								<option value="">所有用例</option>
								<c:forEach items="${list }" var="l" varStatus="id">
									<c:if test="${level==l }">
									<option value="${l }" selected="selected">${l }</option>
									</c:if>
									<c:if test="${level!=l }">
									<option value="${l }">${l }</option>
									</c:if>
								</c:forEach>
									<%-- <c:if test="${level='p' }"></c:if>
									<option  selected="selected">${level}</option>
									<option value="p1">p1</option>
									<option value="p2">p2</option>
									<option value="p3">p3</option>
									<option value="p4">p4</option> --%>
							</select></li>
							<li style="float:left;">
								<button type="submit" class="btn btn-primary">确定</button>
							</li>


							<li style="float:left;"><c:if
									test="${not empty permissionForm.impor }">
									<input type="button" class="btn btn-info"
										value="${permissionForm.impor }" onclick="showDivFun()" />
								</c:if></li>

							<li style="float:left;"><c:if
									test="${not empty permissionForm.expor }">
									<button type="button" class="btn btn-info"
										onclick="export1(${id})">${permissionForm.expor }</button>
								</c:if></li>
							<li style="float:left;">
								<button onclick="self.print();" target="_self"
									class="btn btn-info">打印</button>
							</li>
						</form>
					</span>
				</ul>
			</td>
		</tr>
	</table>
	<hr />
	<table id="showcheck"
		style="margin-left:30px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;"
		class="table table-condensed table-bordered table-hover table-striped">
		<tr style="background-color:#4682B4;height:40px;">
			<td style="text-align:center;vertical-align:middle;width:50px;"
				class="noprint"><h4>
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
					<font color="white">重要级别</font>
				</h4></td>
			<td style="text-align:center;vertical-align:middle;width:100px;"><h4>
					<font color="white">耗时(min)</font>
				</h4></td>
			<td style="text-align:center;vertical-align:middle;width:100px;" class="noprint"><h4>
					<font color="white">操作</font>
				</h4></td>
		</tr>
		<tbody id="cdTb">
			<s:iterator value="pageBean.list">
				<tr style="height:40px;">
					<td style="text-align:center;vertical-align:middle;"
						class="noprint"><input type="checkbox" name="checkedId"
						id="checkedId" value=<s:property value="caseId"/>
						class=" id-checkbox" /></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black"><a href="Case_showCase?caseId=<s:property value="caseId"/>&typeId=${id}&tag=2"><s:property value="num" /></a></font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black"><s:property value="classification" /></font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black"><s:property value="caseName" /></font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black"><s:property value="testItem" /></font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black"><s:property value="description " /></font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black"><s:property value="level" /></font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black"><s:property value="advidceTime" /></font></td>
					<td style="text-align:center;vertical-align:middle;"
						class="noprint"><c:if
							test="${not empty permissionForm.edit }">
							<a
								href="Case_updateCasePre?caseId=<s:property value="caseId"/>&typeId=${id}">${permissionForm.edit }</a> | </c:if><a
						href="Case_showCase?caseId=<s:property value="caseId"/>&typeId=${id}&tag=2">查看</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<table style="width:80%;margin-left:5%;" class="noprint">
		<tr>
			<td><span style="float:left;">共<span><s:property
							value="pageBean.totalPage" /></span>页|第<span><s:property
							value="pageBean.currentPage" /> </span>页
			</span></td>
			<td><span style="float:right;"><form
						action="Case_selectCase2?id=${id }" class="right-font08">
						[
						<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
						<s:else>
							<a href="Case_selectCase2?page=1&id=${id }" class="right-font08">首页</a> | <a
								href="Case_selectCase2?page=<s:property value="%{pageBean.currentPage-1}"/>&id=${id } "
								class="right-font08">上一页</a>
						</s:else>
						|
						<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
							<a
								href="Case_selectCase2?page=<s:property value="%{pageBean.currentPage+1}"/>&id=${id } "
								class="right-font08">下一页</a> | <a
								href="Case_selectCase2?page=<s:property value="%{pageBean.totalPage}"/>&id=${id}"
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
						<input class="btn btn-primary btn-xs"
							style="border:1px solid black;" value="确定" type="submit">
					</form></span></td>
		</tr>
	</table>
	<%-- 隐藏域表单 --%>
	<form action="" method="post" id="formid">
		<input type="hidden" name="caseId" id="caseId" /> <input
			type="hidden" name="checkedIds" id="checkedIds" />
	</form>
	<div id="hid"></div>



	<div id="popDiv" class="mydiv" style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="closeDivFun()">
			<span><font color="black"><img alt=""
					src="images/del.gif"></font></span>
		</div>
		<form action="Case_importCase" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="typeId" value="${id }" />
			<table
				style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
				<tr style="background-color:#4682B4;">
					<td style="height:40px;">
						<h3 style="margin-left:35%">
							<font color="white">导入用例</font>
						</h3>
				</tr>
				<tr>
				
				<td  colspan="2"><div class="upfilebox" style="margin-left:20%;"><input type="file" name="file"  id="file" onChange="validateFile(this)"><label><i class="layui-icon" style="color:#4682B4;font-size:1.5em;">&#xe61f;</i> 上传xls文件</label></div></td>
			</tr>
				
				<tr>
					<td colspan="2">导入文件的第<input style="width:40px"
						onkeyup="sounts(this)" name="line" value="1" />页&nbsp;&nbsp; 第<input
						style="width:40px;" onkeyup="sounts(this)" name="rowNum" value="2" />行
					</td>
				</tr>
				<tr>
					<td colspan="2" style="color: red;font-size: xx-small;">(注：页数必须大于0，行必须大于1)</td>
				</tr>
				<tr style="background-color:#4682B4;">
					<td style="height:40px;">
						<div style="margin-left:25%;height:40px;">
							<input type="submit" value="导入" class="btn btn-primary"
								style="border:1px solid black;width:60px;">&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="重置" class="btn btn-primary"
								style="border:1px solid black;width:60px;">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		function validateFile(target) {
			var fileSize = 0;
			if (!target.files) {
				var filePath = target.value;
				var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
				var file = fileSystem.GetFile(filePath);
				fileSize = file.size;
			} else {
				fileSize = target.files[0].size;
			}
			var size = fileSize / 1024;
			if (size > 9000) {
				layer.msg("附件不能大于9M");
				target.value = "";
				return
			}
			var name = target.value;
			var fileName = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
			if (fileName != "xls" && fileName != "xlsx") {
				layer.msg("只能上传excel格式的文件");
				target.value = "";
				return
			}
		}
		function num(obj) {
			obj.value = obj.value.replace(/\D/g, '');
		}
	</script>
</body>
</html>

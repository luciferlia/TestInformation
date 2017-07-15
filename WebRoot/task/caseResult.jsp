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
.noprint td{font-size:0.8em;}
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
		if (checkedIds == "" || checkedIds == null || checkedIds == 0) {
			layer.msg("请勾选要操作的用例");
		} else {
			var f=false;
			if ("delete" == consoleTag) {
			 f= confirm("确定删除选中用例？");
			}
			if ("export" == consoleTag) {
			 f= confirm("确定导出选中用例？");
			}
			
			if (f) {
				//table表中选中的复选框赋值给checkedSubject


				//上面的字符串赋值给隐藏域表单
				$('#checkedIds').val(checkedIds);

				//删除
				if ("delete" == consoleTag) {
					$('#formid').prop("action", "Case_deleteCase?typeId=${id}");
				}
				if("export" ==consoleTag){
					$('#formid').prop("action","Case_exportCase?typeId=${id}")
				}
				//提交隐藏域表单，后台才能获取隐藏域表单的值
				$('#formid').submit();

			} else {
				return false;
			}
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
</script>
</head>

<body>
<h4 class="glyphicon glyphicon-tasks" style="margin-left:5%;margin-top:1%;">
		问题用例
	</h4>
	<hr/>
	<c:if test="${not empty pageBean.list }">
	
	<table id="showcheck"
		style="margin-left:1%;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;">
		<tr style="background-color:#4682B4;height:40px;color: white;">
			<td style="text-align:center;vertical-align:middle;width:50px;" class="noprint"><h4>序号</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>分类</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>子模块</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>测试项</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>用例编号</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>重要等级</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>建议耗时</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>测试结果</h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4>操作</h4></td>
		</tr>
		<tbody id="cdTb">
			<s:iterator value="pageBean.list" status="status" >
				<tr style="height:40px;">
					<td style="text-align:center;vertical-align:middle;" class="noprint"><s:property value="#status.count"/>
				<%-- <td style="text-align:center;vertical-align:middle;" class="noprint"><font>
						<s:property value="cases.caseId"/></font> --%>
			</td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.classification" /></font></td>
				<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.caseName" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.testItem" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.num" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.level" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="black"><s:property value="cases.advidceTime" /></font></td>
			<td style="text-align:center;vertical-align:middle;"><font
				color="blue"><s:if test="%{result=='pass'||result=='Pass'}"><s:property value="result"/></s:if></font><s:else><font
				color="red"><s:property value="result"/></font></s:else></td>
			<td style="text-align:center;vertical-align:middle;"><a
				href="task_showDetailCase?caseId=<s:property value="cases.caseId"/>&planId=<s:property value="plan.planId"/>"><font color="#8B4513">查看</font></a></td>
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
					<td><span style="float:right;"><form action="task_showCaseResult?page=1" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="task_showCaseResult?page=1" class="right-font08">首页</a> | <a
									href="task_showCaseResult?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="task_showCaseResult?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="task_showCaseResult?page=<s:property value="%{pageBean.totalPage}"/>"
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
							<input type="submit" class="btn btn-primary btn-xs"
								style="border:1px solid black;font-size:0.9em"
								value="确定" />
						</form></span></td>
				</tr>
			</table>
		</c:if>
		<c:if test="${empty pageBean.list }">
			<c:if test="${message=='' }">
			<center style="color:red;font-size:10px;">已经测完的用例中没有出现问题用例</center>
			</c:if>
			<c:if test="${message!='' }"><center style="color:red;font-size:10px;">${message }</center></c:if>
		</c:if>
			<%-- 隐藏域表单 --%>
			<form action="" method="post" id="formid">
				<input type="hidden" name="caseId" id="caseId" /> <input
					type="hidden" name="checkedIds" id="checkedIds" />
			</form>

</body>
</html>

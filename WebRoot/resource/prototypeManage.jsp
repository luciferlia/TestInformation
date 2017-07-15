<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<base href="<%=basePath%>">

<title>与德测试信息管理系统</title>

<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange1.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<style type="text/css">
li {
	list-style: none;
}

#table td {
	border: 1px solid #EDEDED;
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
		var checkedSubject = $('#table input[name=checkedId]:checkbox:checked');
		var checkedIds = "";
		//循环获取选中的复选框的value，这个value是数据表中每条记录的主键，传给后台，后台就能根据主键查找到数据表的相应记录
		//将其value用逗号隔开拼接成一个字符串
		checkedSubject.each(function() {
			checkedIds = checkedIds + "," + $(this).val();
		});
		if (checkedIds == "" || checkedIds == null || checkedIds == 0) {
			alert("请勾选要操作的SIM卡");
		} else {
			var f=false;
			if ("delete" == consoleTag) {
			 f= confirm("确定删除选中SIM卡？");
			}
			if (f) {
				//table表中选中的复选框赋值给checkedSubject
				//上面的字符串赋值给隐藏域表单
				$('#checkedIds').val(checkedIds);
				//删除
				if ("delete" == consoleTag) {
					$('#formid').prop("action", "Resource_deleteSelectedPrototype");
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
	<h3 class="glyphicon glyphicon-tasks" style="margin-left:5%;"><font color="black">样机资源信息</font></h3>
	<ul>
		<li style="float:left;margin-left:3%;"><a onclick="showDivFun()"
			type="button" class="btn btn-primary">增加</a></li>
		<li style="float:left;"><button onclick="console('delete','')" class="btn btn-danger">删除</button></li></ul>
	</ul>
	<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
		width="100%" color=#987cb9 SIZE=10>
			<table id="table" class="table table-condensed table-bordered table-hover table-striped"
				style="margin-left:30px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;">
			<tr style="background-color:#4682B4;height:40px;">
				<td style="text-align:center;vertical-align:middle;width:40px;"><h4><font color="black"><input type="checkbox" name="all" id="all" /></font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">样机名称</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">样机型号</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">样机数量</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">负责人</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">操作</font></h4></td>
			</tr>
			<tbody id="cdTb">
			<s:iterator value="pageBean.list">
			<tr style="height:30px;">
				<td style="text-align:center;vertical-align:middle;"><input type="checkbox" name="checkedId" id="checkedId" class=" id-checkbox" value="<s:property value="id"/>"/><span style="font-size:0;overflow:hidden;"><s:property value="id"/></span></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="prototypeName"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="prototypeType"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="prototypeCount"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="manager"/></font></td>
				<td style="text-align:center;vertical-align:middle;"><a onclick="updateRow(this);"> 修改 </a>|<a href="Resource_deletePrototype?id=<s:property value="id"/>"> 删除 </a>|<a href="Resource_showPrototypeBorrow?id=<s:property value="id"/>"> 借用情况 </a></td>
			</tr>
			</s:iterator>
		</tbody>
		</table>
		<table style="width:80%;margin-left:5%;">
				<tr>
					<td><span style="float:left;">共<span><s:property
								value="pageBean.totalPage" /></span>页|第<span><s:property
								value="pageBean.currentPage" /> </span>页</span>
					</td>
					<td><span style="float:right;"><form action="Resource_showAllPrototype" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="Resource_showAllPrototype?page=1" class="right-font08">首页</a> | <a
									href="Resource_showAllPrototype?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="Resource_showAllPrototype?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="Resource_showAllPrototype?page=<s:property value="%{pageBean.totalPage}"/> "
									class="right-font08">末页</a>
							</s:if>
							<s:else>下一页 | 末页</s:else>
							] 转至：<select name="page" style="width:50px;">
								<s:iterator value="pageBean.count">
									<option value="<s:property value="count"/>"><s:property value="count" />
									</option>
								</s:iterator>
							</select>
							<%-- <input type="text" style="width:50px;" name="page" /> --%>
							<input type="submit" style="border:1px solid black;height:25px;text-align:center;" value="确定" class="btn btn-primary">
						</form></span></td>
				</tr>
			</table>
	<%-- 隐藏域表单 --%>
			<form action="" method="post" id="formid">
				<input type="hidden" name="id" id="id" /> <input
					type="hidden" name="checkedIds" id="checkedIds" />
			</form>
<!------------------------------------------------修改样机  ------------------------------------------------------------------->
			<div id="hid"></div>
		<div style="display:none;" class="mydiv" id="update" >
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span><font color="black">X</font></span>
		</div>
		<form action="Resource_updatePrototype" method="post">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#ADD8E6;">
		<td colspan="2" style="height:40px;">
		<h4 style="margin-left:30%"><font color="black" >修改样机</font></h4></td>
		</tr>		
				
				<tr>
					<td><h4></h4></td>
					<td><input type="hidden" value="" name="prototype.id"id="l1"/></td>
				</tr>
				<tr>
					<td ><span style="margin-left:10%;">样机名称：</span></td>
					<td><input type="text" value="" name="prototype.prototypeName" id="l2"/></td>
				</tr>
				<tr>
					<td><sapn style="margin-left:10%;">样机型号：</span></td>
					<td><input type="text"   value="" name="prototype.prototypeType" id="l3"/></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">样机总数：</span></td>
					<td><input type="text"  value="" name="prototype.prototypeCount"  id="l4"/></td>
				</tr>
				<tr>
					<td><input type="hidden"  value=""  id="l5"/></td>
				</tr>
				<tr>
					<td><input type="hidden" value="" id="l6"/></td>
				</tr>
             	<tr style="background-color:#ADD8E6;height:40px;">
				<td colspan="2" >
				<div style="margin-left:30%">
				<input type="submit" value="提交" id="btn_update">
				&nbsp;&nbsp;&nbsp;&nbsp;	
				<input type="button" 
				value="取消" onclick="hideAddInput()" ></div></td>
				</tr>
		 </table>
	 </form>
	</div>
	
<!----------------------------------------添加样机------------------------------------------------------------------>	
	<div id="popDiv" class="mydiv" style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="closeDivFun()">
			<span><font color="black">X</font></span>
		</div>
		<form action="Resource_addPrototype" method="post">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr  style="background-color:#ADD8E6;">
					<td colspan="2" style="height:40px;">
						<h4 style="margin-left:30%"><font color="black" >增加样机</font></h4>
					</tr>
					<tr>
					<td><input type="hidden" id=""  name="prototype.id"/></td>
					</tr>
					<tr>
					<td ><span style="margin-left:10%;">样机名称：</span></td>
					<td><input type="text" id=""  name="prototype.prototypeName"/></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">样机型号：</span></td>
					<td><input type="text" id="" name="prototype.prototypeType"/></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">样机总数：</span></td>
					<td><input type="text" id="" name="prototype.prototypeCount" /></td>
				</tr>
				<tr  style="background-color:#ADD8E6;height:40px;">
                 <td colspan="2" >
				<div style="margin-left:30%">
			      <input type="submit" value="提交" >
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button" 
				value="取消" onclick="closeDivFun()" ></div></td>				
				</tr>
		</table>
		</form>
	</div>
</body>
</html>

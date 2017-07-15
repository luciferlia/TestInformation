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
<script src="script/exchange4.js"></script>
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
					$('#formid').prop("action", "Resource_deleteSelectedSim");
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
	<h3 class="glyphicon glyphicon-tasks" style="margin-left:5%;"><font color="black">SIM卡基本信息</font></h3>
	<ul>
		<li style="float:left;margin-left:3%;"><a onclick="javascript:shield5()"
			type="button" class="btn btn-primary">增加</a></li>
		<li style="float:left;"><button onclick="console('delete','')" class="btn btn-danger">删除</button></li></ul>
	<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
		width="100%" color=#987cb9 SIZE=10>
	<table id="table" class="table table-condensed table-bordered table-hover table-striped"
				style="margin-left:30px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;">
			<tr style="background-color:#4682B4;height:40px;">
				<td style="text-align:center;vertical-align:middle;width:30px;"><h4><font color="black"><input type="checkbox" name="all" id="all" /></font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">MSISDN(号码)</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">ICCID</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">运营商</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">PUK码</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">服务密码</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">套餐说明</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">流量</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">短信</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">通话</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">负责人</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">操作</font></h4></td>
			</tr>
			<tbody id="cdTb">
			<s:iterator value="pageBean.list">
			<tr style="height:30px;">
				<td style="text-align:center;vertical-align:middle;"><input type="checkbox" name="checkedId" id="checkedId" class=" id-checkbox" value="<s:property value="id"/>"/><span style="font-size:0;overflow:hidden;"><s:property value="id"/></span></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="phone"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="ICCID"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="operator"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="puk"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="servicePassword"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="description"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="gprs"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="message"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="callPhone"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="manager"/></font></td>
				<td style="text-align:center;vertical-align:middle;"><a onclick="updateRow(this);"> 编辑 </a>|<a href="Resource_deleteSim?id=<s:property value="id"/>"> 删除 </a>|<a href="Resource_showSimBorrow?id=<s:property value="id"/>"> 借用情况 </a></td>
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
					<td><span style="float:right;"><form action="Resource_showAllSim" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="Resource_showAllSim?page=1" class="right-font08">首页</a> | <a
									href="Resource_showAllSim?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="Resource_showAllSim?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="Resource_showAllSim?page=<s:property value="%{pageBean.totalPage}"/> "
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
<!----------------------------------------------------借用情况------------------------------------------------------------->
		<div id="hid"></div>
		<div id="log_window"  style="display:none;" >
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="cancel_shield()">
			<span><font color="black">X</font></span>
		</div>
		<table   style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;height:390px;width:100%;">
				<tr>
				<td style="background-color:#ADD8E6;width:150px;"></td>
					<td style="background-color:#ADD8E6;"><h4 style="margin-left:20%;">
						<font color="black">借用情况</font>
					</h4></td>
				</tr>
				<tr>
					<td ><font style="margin-left:20%;" color="black">SIM卡ID：</font></td>
					<td><input type="text" name="" value="" ></td>
				</tr>
				<tr>
					<td ><font  style="margin-left:20%;" color="black">运营商：</font></td>
					<td><input type="text" name="" value=""></td>
				</tr>
                <tr>
					<td ><font style="margin-left:20%;" color="black">电话号码：</font></td>
					<td><input type="text" name="" value=""></td>
				</tr>
				<tr>
					<td ><font style="margin-left:20%;" color="black">借用人：</font></td>
					<td><input type="text" name="" value=""></td>
				</tr>
				<tr>
					<td ><font style="margin-left:20%;" color="black">借用时间：</font></td>
					<td><input type="text" name="" value=""></td>
				</tr>
				<tr>
					<td ><font style="margin-left:20%;" color="black">归还时间：</font></td>
					<td><input type="text" name="" value=""></td>
				</tr>
				<tr >
					<td ><font style="margin-left:20%;" color="black">借用状态：</font></td>
					<td><select name="cases.level" style="width:100px;">
     			<option value="已还" selected="selected"><font color="black">已还</font></option>
     			<option value="丢失"><font color="black">丢失</font></option>
     			<option value="损坏"><font color="black">损坏</font></option>
     			</select></td>
				</tr>
				<tr style="background-color:#ADD8E6;">
                 <td colspan="2" >
				<div style="margin-left:30%">
			     <input type="submit" value="提交" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;
			     <input type="button" value="取消" onclick="cancel_shield()" class="btn btn-primary"></div></td>				
				</tr>
	 </table>
<!---------------------------------------------------- 新增SIM卡信息------------------------------------------------------------>
	</div>
		<div id="log_window5"  style="display:none;" >
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="cancel_shield5()">
			<span><font color="black">X</font></span>
		</div>
		<form action="Resource_addSim" method="post">
		<table   style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;height:500px;width:100%;">
			<tr>
				<td style="background-color:#ADD8E6;width:150px;"></td>
					<td style="background-color:#ADD8E6;"><h4 style="margin-left:30%;">
						<font color="black">新增SIM卡信息</font>
					</h4></td>
			</tr>
			<tr>
				<td style="width:100px;"><font style="margin-left:30%;" color="black">运营商：</font></td>
				<td><select name="sim.operator" style="width:100px;" type="select" >
     			<option value="移动" selected="selected">移动</option>
     			<option value="联通">联通</option>
     			<option value="电信">电信</option>
     			<option value="其他">其他</option>
     		</select></td>
     		</tr>
     			<!-- <tr>
					<td><input type="hidden" name="sim.id" ></td>
				</tr> -->
     			<tr>
					<td ><font style="margin-left:30%;" color="black">MSISDN(号码)：</font></td>
					<td><input type="text" name="sim.phone" ></td>
				</tr>
				<tr>
					<td ><font style="margin-left:30%;" color="black">ICCID：</font></td>
					<td><input type="text" name="sim.ICCID" ></td>
				</tr>
                <tr>
					<td ><font style="margin-left:30%;" color="black">PUK码：</font></td>
					<td><input type="text" name="sim.puk" ></td>
					</tr>
                <tr>
					<td ><font style="margin-left:30%;" color="black">服务码：</font></td>
					<td><input type="text" name="sim.servicePassword" ></td>
				</tr>
							<tr>
					<td ><font style="margin-left:30%;" color="black">套餐说明：</font></td>
					<td><input type="text" name="sim.description" style="width:200px;height:100px;"></td>
					</tr>
                <tr>
					<td ><font style="margin-left:30%;" color="black">流量：</font></td>
					<td><input type="text" name="sim.gprs" style="width:50px;">MB</td>
				</tr>
				<tr>
					<td ><font style="margin-left:30%;" color="black">短信：</font></td>
					<td><input type="text" name="sim.message" style="width:50px;">条</td>
					</tr>
                <tr>
					<td ><font style="margin-left:30%;" color="black">通话：</font></td>
					<td><input type="text" name="sim.callPhone" style="width:50px;">分钟</td>
				</tr>
				<tr style="background-color:#ADD8E6;">                 <td colspan="2" >
				<div style="margin-left:35%">
			      <input type="submit" value="提交" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;	
			      <input type="button" value="取消" onclick="cancel_shield5()" class="btn btn-primary"></div></td>				
				</tr>
	 </table>
	 </form>
	</div>
	
<!------------------------------------------------------修改SIM卡信息--------------------------------------------------------------->
		<div  id="update" class="log_window2" style="display:none;" >
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span><font color="black">X</font></span>
		</div>
		<form action="Resource_updateSim" method="post">
		<table   style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;height:500px;width:100%;">
			<tr>
				<td style="background-color:#ADD8E6;width:150px;"></td>
					<td style="background-color:#ADD8E6;"><h4 style="margin-left:20%;">
						<font color="black">修改SIM卡信息</font>
					</h4></td>
			</tr>
				<tr style="height:0px">
					<td><input type="hidden" value="" name="sim.id" id="l1"/></td>
				</tr>
     			<tr>
					<td ><font color="black" style="margin-left:30%;">MSISDN(号码)：</font></td>
					<td><input type="text" name="sim.phone" value="" id="l2"></td>
				</tr>
				<tr>
					<td ><font color="black" style="margin-left:30%;">ICCID：</font></td>
					<td><input type="text" name="sim.ICCID" value="" id="l3"></td>
				</tr>
					<tr>
					<td style="width:100px;"><font color="black" style="margin-left:30%;">运营商：</font></td>
					<td><select name="sim.operator" style="width:100px;" type="select" id="l4">
		     			<option value="移动" >移动</option>
		     			<option value="联通">联通</option>
		     			<option value="电信">电信</option>
		     			<option value="其他">其他</option>
		     		</select></td>
     		</tr>
                <tr>
					<td ><font style="margin-left:30%;" color="black">PUK码：</font></td>
					<td><input type="text" name="sim.puk" value="" id="l5"></td>
					</tr>
                <tr>
					<td ><font style="margin-left:30%;" color="black">服务码：</font></td>
					<td><input type="text" name="sim.servicePassword" value="" id="l6"></td>
				</tr>
					<tr>
					<td ><font style="margin-left:30%;" color="black">套餐说明：</font></td>
					<td><input type="text" name="sim.description" value="" style="width:200px;height:100px;" id="l7"></td>
					</tr>
                <tr>
					<td ><font style="margin-left:30%;" color="black">流量：</font></td>
					<td><input type="text" name="sim.gprs" value="" style="width:50px;" id="l8">MB</td>
				</tr>
				<tr>
					<td ><font style="margin-left:30%;" color="black">短信：</font></td>
					<td><input type="text" name="sim.message" value="" style="width:50px;" id="l9">条</td>
					</tr>
                <tr>
					<td ><font style="margin-left:30%;" color="black">通话：</font></td>
					<td><input type="text" name="sim.callPhone" value="" style="width:50px;" id="l10">分钟</td>
				</tr>
				 <tr>
					<td><input type="hidden" name="" value="" style="width:50px;" id="l11"></td>
				</tr>
				<tr style="background-color:#ADD8E6;">
                <td colspan="2" >
				<div style="margin-left:35%">
			    <input type="submit" value="提交" id="btn_update" onclick="updateInfo()" class="btn btn-primary">
				&nbsp;&nbsp;&nbsp;&nbsp;	
				<input type="button" value="取消" onclick="hideAddInput()" class="btn btn-primary"></div></td>				
				</tr>
	 </table>
	 </form>
	</div>
</body>
</html>

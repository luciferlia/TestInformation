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
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/exchange.css">
<jsp:include page="/wind/top.jsp"></jsp:include>
<script src="script/exchange4.js"></script>

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
	function console(consoleTag, deviceId, stateTag) {
		var checkedSubject = $('#table input[name=checkedId]:checkbox:checked');
		var checkedIds = "";
		//循环获取选中的复选框的value，这个value是数据表中每条记录的主键，传给后台，后台就能根据主键查找到数据表的相应记录
		//将其value用逗号隔开拼接成一个字符串
		checkedSubject.each(function() {
			checkedIds = checkedIds + "," + $(this).val();
		});
		if (checkedIds == "" || checkedIds == null || checkedIds == 0) {
			layer.msg("请勾选要操作的设备");
		} else {
			var f=false;
			if ("delete" == consoleTag) {
			 
			 layer.confirm('确定删除选中设备?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                //table表中选中的复选框赋值给checkedSubject
				//上面的字符串赋值给隐藏域表单
				$('#checkedIds').val(checkedIds);
				//删除
				if ("delete" == consoleTag) {
					$('#formid').prop("action", "Resource_deleteSelectedDevice");
				}
				//提交隐藏域表单，后台才能获取隐藏域表单的值
				$('#formid').submit();
                                
                                },function(index){ layer.close(index);
								return false;
								});
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
<h3 class="glyphicon glyphicon-tasks" style="margin-left:5%;"><font color="black">设备基本信息</font></h3>
	<ul>
		<li style="float:left;margin-left:3%;"><a onclick="showDivFun()" type="button" class="btn btn-primary">增加</a></li>
		<li style="float:left;"><button onclick="console('delete','')" class="btn btn-danger">删除</button></li>
	</ul>
	<%-- 	<div style="margin-left:60%;">
		<input type="hidden" name="id" value="${device.deviceId }">
			<input type="hidden" name="page" value="1">
			<li><font size="5">设备选择：</font>
				<select name="isconsum" style="width:100px;height:30px;">
				<option value="全部" selected="selected">全部设备</option>
     			<option value="是">耗材</option>
     			<option value="否">不耗材</option>
     			</select>
     			<button type="submit" class="btn btn-primary">确定</button></li>
     	</div> --%>
	<hr/>
	<table id="table" class="table table-condensed table-bordered table-hover table-striped"
		style="margin-left:30px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;">
		<tr style="background-color:#4682B4;height:40px;">
			<td style="text-align:center;vertical-align:middle;width: 5%"><h4><font color="black"><input type="checkbox" name="all" id="all" /></font></h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4><font color="black">设备名称</font></h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4><font color="black">设备型号</font></h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4><font color="black">版本</font></h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4><font color="black">协议</font></h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4><font color="black">数量</font></h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4><font color="black">是否耗材</font></h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4><font color="black">备注</font></h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4><font color="black">负责人</font></h4></td>
			<td style="text-align:center;vertical-align:middle;"><h4><font color="black">操作</font></h4></td>
		</tr>
		<tbody id="cdTb">
		<s:iterator value="pageBean.list">
		<tr style="height:30px;">
			<td style="text-align:center;vertical-align:middle;"><input type="checkbox" name="checkedId" id="checkedId" class=" id-checkbox" value="<s:property value="deviceId"/>"/><span style="font-size:0;overflow:hidden;"><s:property value="deviceId"/></span></td>
			<td style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="deviceName"/></font></td>
			<td style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="deviceType"/></font></td>
			<td style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="version"/></font></td>
			<td style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="protocol"/></font></td>
			<td style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="deviceCount"/></font></td>
			<td style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="isconsum"/></font></td>
			<td style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="remark"/></font></td>
			<td style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="manager"/></font></td>
			<td style="text-align:center;vertical-align:middle;"><a onclick="updateRow(this);"> 编辑 </a>|<a href="Resource_deleteDevice?id=<s:property value="deviceId"/>"> 删除 </a>|<a href="Resource_showDeviceBorrow?id=<s:property value="deviceId"/>"> 借用情况 </a></td>
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
					<td><span style="float:right;"><form action="Resource_showAllDevice" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="Resource_showAllDevice?page=1" class="right-font08">首页</a> | <a
									href="Resource_showAllDevice?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="Resource_showAllDevice?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="Resource_showAllDevice?page=<s:property value="%{pageBean.totalPage}"/> "
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
				<input type="hidden" name="deviceId" id="deviceId" /> <input
					type="hidden" name="checkedIds" id="checkedIds" />
			</form>
<!---------------------------------------------- 添加设备信息 --------------------------------------------------------------->
		<div id="hid"></div>
				<div id="popDiv" class="popDiv3" style="display:none;">
					<div align="right"
						style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
						onclick="closeDivFun()">
						<span><font color="black"><img alt="" src="images/del.gif"></font></span>
					</div>
					<form action="Resource_addDevice" method="post" onSubmit="return check1();">
						<table
							style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
							<tr style="background-color:#4682B4;height:30px;">
								<td colspan="2">
									<h4 style="margin-left:35%">
										<font color="black">添加设备信息</font>
									</h4>
							</tr>
							<tr style="display:none;">
								<td><input type="hidden" name="device.deviceId" value=""></td>
							</tr>
							<tr>
								<td><font style="margin-left:20%;">名称：</font></td>
								<td><input type="text" name="device.deviceName" value="" class="form-control" style="width:150px;"></td>
							</tr>
							<tr>
								<td><font style="margin-left:20%;">型号：</font></td>
								<td><input type="text" name="device.deviceType" value="" class="form-control" style="width:150px;"></td>
							</tr>
							<tr>
								<td><font style="margin-left:20%;">版本：</font></td>
								<td><input type="text" name="device.version" value="" class="form-control" style="width:150px;"></td>
							</tr>
							<tr>
								<td><font style="margin-left:20%;">协议：</font></td>
								<td><input type="text" name="device.protocol" value="" class="form-control" style="width:150px;"></td>
							</tr>
							<tr>
								<td><font style="margin-left:20%;">数量：</font></td>
								<td><input type="text" name="device.deviceCount" value="" class="form-control" style="width:150px;"></td>
							</tr>
							<tr>
								<td><font style="margin-left:20%;">状态：</font></td>
								<td><select name="device.state" type="select" class="form-control" style="width:150px;">
					     			<option value="可借" selected="selected"><font color="black">可借</font></option>
					     			<option value="已借"><font color="black">已借</font></option>
					     		</select></td>
							</tr>
							<tr>
								<td><font style="margin-left:20%;">是否耗材：</font></td>
								<td><select name="device.isconsum"  type="select" class="form-control" style="width:150px;">
						     			<option value="是">是</option>
						     			<option value="否">否</option>
					     			</select></td>
							</tr>
							<tr>
								<td><font style="margin-left:20%;">备注：</font></td>
								<td><input type="text" name="device.remark" value="" class="form-control" style="width:150px;"></td>
							</tr>
							<tr style="background-color:#4682B4;height:40px;">
								<td colspan="2">
									<div style="margin-left:30%">
										<input type="submit" value="提交" class="btn btn-primary" style="border:1px solid black;">&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" value="取消"  class="btn btn-primary" style="border:1px solid black;" onclick="closeDivFun()" >
									</div>
								</td>
							</tr>
						</table>
					</form>
				</div>
<!-------------------------------------------------修改设备信息--------------------------------------------------------->
				<div style="display:none" id="update" class="popDiv3">
					<div align="right"
						style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
						onclick="hideAddInput()">
						<span><font color="black"><img alt="" src="images/del.gif"></font></span>
					</div>
					<form action="Resource_updateDevice" method="post" onSubmit="return check();">
						<table
							style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
							<tr style="background-color:#4682B4;">
								<td colspan="2" style="height:30px;">
									<h4 style="margin-left:30%">
										<font color="black">修改设备信息</font>
									</h4>
							</tr>
							<tr style="display:none;">
								<td><input type="hidden" name="device.deviceId" value="" id="l1"></td>
							</tr>
							<tr>
								<td><font style="margin-left:10%;">名称：</font></td>
								<td><input type="text" name="device.deviceName" value="" id="l2" class="form-control" style="width:150px;"></td>
							</tr>
							<tr>
								<td><font style="margin-left:10%;">型号：</font></td>
								<td><input type="text" name="device.deviceType" value="" id="l3" class="form-control" style="width:150px;"></td>
							</tr>
							<tr>
								<td><font style="margin-left:10%;">版本：</font></td>
								<td><input type="text" name="device.version" value="" id="l4" class="form-control" style="width:150px;"></td>
							</tr>
							<tr>
								<td><font style="margin-left:10%;">协议：</font></td>
								<td><input type="text" name="device.protocol" value="" id="l5" class="form-control" style="width:150px;"></td>
							</tr>
							<tr>
								<td><font style="margin-left:10%;">数量：</font></td>
								<td><input type="text" name="device.deviceCount" value="" id="l6" class="form-control" style="width:150px;"></td>
							</tr>
							<tr>
								<td><font style="margin-left:10%;">是否耗材：</font></td>
								<td><select name="device.isconsum"  type="select" id="l7" class="form-control" style="width:150px;">
						     			<option value="是">是</option>
						     			<option value="否">否</option>
					     			</select></td>
							</tr>
							<tr>
								<td><font style="margin-left:10%;">备注：</font></td>
								<td><input type="text" name="device.remark" value="" id="l8" class="form-control" style="width:150px;"></td>
							</tr>
							<tr style="display:none;">
								<td><input type="hidden"  value="" id="l9"></td>
							</tr>
							<tr style="display:none;">
								<td><input type="hidden" value="" id="l10"></td>
							</tr>
							<tr>
								<td colspan="2" style="background-color:#4682B4;">
									<div style="margin-left:30%">
										<input type="submit" value="提交" id="btn_update" class="btn btn-primary" style="border:1px solid black;"
											 >&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="btn btn-primary" style="border:1px solid black;" value="取消" onclick="hideAddInput()">
									</div>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<script>
				function check(){
				var a1=document.getElementById('l2').value;
				var a2=document.getElementById('l3').value;
				var a3=document.getElementById('l4').value;
				var a4=document.getElementById('l5').value;
				var a5=document.getElementById('l6').value;
				var a6=document.getElementById('l7').value;
			/* 	var a7=document.getElementById('l8').value; */
				   if(a1==""||a1==null)
					{
					layer.msg("名称不能为空");
					return false;
					}
				   else if(a2==""||a2==null)
					{
					
					
					layer.msg("型号不能为空");
					return false;
					}
					else if(a3==""||a3==null)
					{
					
					layer.msg("版本不能为空");
					return false;
					}
					else if(a4==""||a4==null)
					{
					
					layer.msg("协议不能为空");
					return false;
					}
					else if(a5==""||a5==null)
					{
					
					layer.msg("数量不能为空");
					return false;
					}
					else if(a6==""||a6==null)
					{
					
					layer.msg("是否耗材不能为空");
					return false;
					}
					
					/* else if(a8==""||a8==null)
					{
					layer.msg("备注不能为空");
					return false;
					} */
					else{
					return true;
					}
				}
				
				function check1(){
				var a1=document.getElementsByName('device.deviceName')[0].value;
				var a2=document.getElementsByName('device.deviceType')[0].value;
				var a3=document.getElementsByName('device.version')[0].value;
				var a4=document.getElementsByName('device.protocol')[0].value;
				var a5=document.getElementsByName('device.deviceCount')[0].value;
				var a6=document.getElementsByName('device.isconsum')[0].value;
				var a7=document.getElementsByName('device.state')[0].value;
				   if(a1==""||a1==null)
					{
					layer.msg("名称不能为空");
					return false;
					}
				   else if(a2==""||a2==null)
					{
					
					
					layer.msg("型号不能为空");
					return false;
					}
					else if(a3==""||a3==null)
					{
					
					layer.msg("版本不能为空");
					return false;
					}
					else if(a4==""||a4==null)
					{
					
					layer.msg("协议不能为空");
					return false;
					}
					else if(a5==""||a5==null)
					{
					
					layer.msg("数量不能为空");
					return false;
					}
					else if(a6==""||a6==null)
					{
					
					layer.msg("是否耗材不能为空");
					return false;
					}
					
					else if(a8==""||a8==null)
					{
					layer.msg("状态不能为空");
					return false;
					}
					else{
					return true;
					}
				}
				</script>
</body>
</html>

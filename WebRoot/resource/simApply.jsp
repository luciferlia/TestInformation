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
<jsp:include page="/wind/top.jsp"></jsp:include>
<script src="script/exchange.js"></script>
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
</head>
<body>
	<h3 class="glyphicon glyphicon-tasks" style="margin-left:5%;"><font color="black">SIM卡资源申请</font></h3>
	<div style="margin-left:60%;">
		<form action="Resource_selectSim2" method="post">
			<input type="hidden" name="id" value="${id }">
			<input type="hidden" name="page" value="1">
			<li ><font color="black" size="4">运营商：</font><select name="operator" style="width:100px;height:30px;" >
     			<option value="" selected="selected"><font color="black">全部</font></option>
     			<option value="移动" ><font color="black">移动</font></option>
     			<option value="联通"><font color="black">联通</font></option>
     			<option value="电信"><font color="black">电信</font></option>
     			<option value="其他"><font color="black">其他</font></option>
     			</select>
     			<button type="submit" class="btn btn-primary">确定</button>
     		</li>
			</form>
			</div>
			<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)" 
			width="100%" color=#987cb9 SIZE=10>
		<table id="table" class="table table-condensed table-bordered table-hover table-striped"
			style="margin-left:30px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;">
			<tr style="background-color:#4682B4;height:40px;">
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">MSISDN(号码)</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">ICCID</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">运营商</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">PUK码</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">服务密码</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">套餐说明</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">负责人</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">借用状态</font></h4></td>
				<td style="text-align:center;vertical-align:middle;"><h4><font color="black">操作</font></h4></td>
			</tr>
			<tbody id="cdTb">
			<s:iterator value="pageBean.list">
			<tr style="height:30px;">
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="phone"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="ICCID"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="operator"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="puk"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="servicePassword"/></font></td>
				<td  style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="description"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="manager"/></font></td>
				<td  style="text-align:center;vertical-align:middle;" ><font color="black"><s:property value="state"/></font></td>
				<td style="text-align:center;vertical-align:middle;"><a href="Resource_showDetailSim?id=<s:property value="id"/>"> 详情  </a>|<a onclick="showDivFun();"> 申请 </a></td>
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
					<td><span style="float:right;"><form action="Resource_selectSim2" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="Resource_selectSim2?page=1" class="right-font08">首页</a> | <a
									href="Resource_selectSim2?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="Resource_selectSim2?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="Resource_selectSim2?page=<s:property value="%{pageBean.totalPage}"/> "
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
<!-----------------------------------------------------------SIM卡申请------------------------------------------------->
		<div id="popDiv" class="mydiv" style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="closeDivFun()">
			<span>X</span>
		</div>
		<form action="user_update" method="post">
			<table style=" width:100%;">
				<tr style="background-color:#87CEFA;">
					<td colspan="2" >
						<h1 style="margin-left:30%"><font color="black" >SIM卡申请</font></h1>
				</tr>
				<tr>

					<td><h4>运营商：</h4></td>
					<td><input type="text" name="" value=""></td>
				</tr>
				<tr>

					<td><h4>PUK码：</h4></td>
					<td><input type="text" name="" value=""></td>
				</tr>
				<tr>

					<td><h4>套餐说明：</h4></td>
					<td><input type="text" name="" value=""></td>
				</tr>
                <!-- 点击确定后，所借时间和所借人根据ID号直接生成 -->
				<tr>
					<td align="center" colspan="2"><button class="btn btn-primary"
							type="submit">保存</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

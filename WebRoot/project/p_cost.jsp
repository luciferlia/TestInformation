<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="script/exchange2.js"></script>
  <script src="script/ie.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<style type="text/css">
table{color:black;}

#table td{text-align:center;vertical-align:middle;font-size:0.9em;font-color:black;border:1px solid #EDEDED;}
td{font-size:0.9em;font-color:black;}
 #sp{float: right;} 
li{list-style:none;}
a:hover{color:red;}
li{list-style:none;}
.popDiv4 input{width:150px;}
</style>
<script type="text/javascript">
$(function() {
    var all_checked = false;
    $(":checkbox").click(function() {
        var table = $(this).parents("table");
        if($(this).attr("id") === "all") {
            table.find(":checkbox").prop("checked", !all_checked);
            all_checked = !all_checked;
        }
        else {
            table.find(":checkbox[id!=all]").each(function (i) {
                if(!$(this).is(":checked")) {
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
	<table style="width:90%;margin-left:5%;"><tr><td>
				<ul>
			<li style="float:left;"><a onclick="showDivFun2();"
				type="button" class="btn btn-primary 	glyphicon glyphicon-plus"
				target="_blank">增加</a></li>
			<li style="float:left;"><button
					onclick='return(confirm("确定删除该委外信息?"))'
					class="btn btn-danger 	glyphicon glyphicon-trash">删除</button></li>
			<span style="float:right;">
				<li style="float:left;"><input type="text"
						class="form-control" placeholder="搜索" style="width:200px;"></li>
				<li style="float:left;">
						<button type="submit" class="btn btn-primary">确定</button>
				</li>
			</span>
		</ul></td></tr></table>
		<hr />
		<table id="table" class="table table-condensed table-bordered table-hover table-striped"
			style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:90%;margin-left:5%" >
			<tr style="background-color:#4682B4;height:40px;">
				<td style="width:40px;"><input type="checkbox" name="all" id="all" /></td>
				
				<td><h4>项目名称</h4></td>
				<td><h4>客户名称</h4></td>
				<td><h4>测试总金额</h4></td>
				<td><h4>测试负责人</h4></td>
				<td><h4>测试人员数量</h4></td>
				<td><h4>成本支出</h4></td>		
				<td><h4>开始时间</h4></td>
				<td><h4>计划完成时间</h4></td>
				<td><h4>实际时间</h4></td>
				<td><h4>完成状态</h4></td>
				<td><h4>操作</h4></td>
			</tr>
			
			<c:forEach items="${projectcosts}" var="pc">
			<tr style="height:40px;">
			
				<td><input type="checkbox" /> </td>
	
				<td><a
					href="Project_projectDetail?name=${pc.projectName}">${pc.projectName }</a></td>
				<td>${pc.customer }</td>
				<td>${pc.total }</td>
				<td>${pc.testManager }</td>
				<td>${pc.testerCount }</td>
				<td>${pc.cost }</td>
				<td>${pc.startTime }</td>
				<td>${pc.planTime }</td>
				<td>${pc.actualTime }</td>
				<td>${pc.state }</td>
				<td>
				<a onclick="updateRow(this);">编辑</a>|<a href="project/project.jsp" >查看</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<table style="width:90%;margin-left:5%;">
		<tr><td style="width:20%;">共<span>&nbsp;&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;</span>页&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;第<span>&nbsp;&nbsp;&nbsp;&nbsp;10&nbsp;&nbsp;&nbsp;&nbsp;</span>页</td>
		<td><span id="sp">[<a href="#" class="right-font08">首页</a> | <a href="#" class="right-font08">上一页</a> | <a href="#" class="right-font08">下一页</a> | <a href="#" class="right-font08">末页</a>] 转至：<input type="text" style="width:50px;"/>&nbsp;&nbsp;<input type="submit"  class="btn btn-primary btn-xs" style="border:1px solid black;" value="确定"/></span></td></tr>
		</table>
		 <div id="hid"></div>
	<div class="popDiv4" id="update" style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span><font color="black">X</font></span>
		</div>
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;">
		<td colspan="2" style="height:30px;">
		<h4 style="margin-left:30%"><font color="black" >编辑项目成本信息</font></h4></td>
		</tr>	
		<form action="user_update" method="post">
			<tr style="height:2px;">
					<td></td>
					<td><input type="hidden"   value="" id="l1"/></td>
				</tr>
				<tr style="height:2px;">
					<td><font color="black" style="margin-left:20%;">项目名称：</font></td>
					<td><input type="text" name="" value="" id="l2" class="form-control"></td>

				</tr>
								<tr>
					<td><font color="black" style="margin-left:20%;">客户名称：</font></td>
					<td><input type="text" name="" value="" id="l3" class="form-control"></td>
				</tr>
				<tr>
					<td><font color="black" style="margin-left:20%;">测试总金额：</font></td>
					<td><input type="text" name="" value="" id="l4" class="form-control"></td>
				</tr>
                <tr>
					<td><font color="black" style="margin-left:20%;">测试负责人：</font></td>
					<td><input type="text" name="" value="" id="l5" class="form-control"></td>
				</tr><tr>
					<td><font color="black" style="margin-left:20%;">测试人员数量：</font></td>
					<td><input type="text" name="" value="" id="l6" class="form-control"></td>
				</tr><tr>
					<td><font color="black" style="margin-left:20%;">成本支出：</font></td>
					<td><input type="text" name="" value="" id="l7" class="form-control"></td>
				</tr>
				<tr>
					<td><font color="black" style="margin-left:20%;">开始时间：</font></td>
					<td><input type="text" name="" value="" id="l8"   class="form-control"></td>
				</tr>
				<tr>
					<td><font color="black" style="margin-left:20%;">计划完成时间：</font></td>
					<td><input type="text" name="" value="" id="l9"  class="form-control"></td>
				</tr>
					<tr>
					<td><font color="black" style="margin-left:20%;" >实际时间：</font></td>
					<td><input type="text" name="" value="" id="l10" class="form-control"></td>
				</tr>
				<tr>
					<td><font color="black" style="margin-left:20%;">完成状态：</font></td>
					<td><input type="text" name="" value="" id="l11" class="form-control"></td>
				</tr>
				<tr>

					<td></td>
					<td><input type="hidden"   value="" id="l12"/></td></td>
				</tr>
				
				<tr style="background-color:#4682B4;height:30px;">
				<td colspan="2" >
				<div style="margin-left:30%">
				<input type="submit" value="提交" class="btn btn-primary" onclick="updateInfo()"  id="btn_update"  style="border:1px solid black" >
				&nbsp;&nbsp;&nbsp;&nbsp;	
				<input type="button" value="取消" onclick="hideAddInput()"  class="btn btn-primary" style="border:1px solid black"></div></td>				
			</tr>
			
		</form>
		</table>
	</div>
	<div id="popDiv2"  class="popDiv4" style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="closeDivFun2()">
			<span><font color="black">X</font></span>
		</div>
			<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;">
		<td colspan="2" style="height:30px;">
		<h4 style="margin-left:30%"><font color="black" >增加项目成本信息</font></h4></td>
		</tr>	
		<form action="user_update" method="post">
			
				<tr>

					<td><font color="black" style="margin-left:20%;">项目名称：</font></td>
					<td><input type="text" name="" value=""class="form-control"></td>
				</tr>
				<tr>
					<td><font color="black"style="margin-left:20%;" >客户名称：</font></td>
					<td><input type="text" name="" value="" class="form-control" ></td>
				</tr>
				<tr>
					<td><font color="black"style="margin-left:20%;" >测试总金额：</font></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr>
                <tr>
					<td><font color="black" style="margin-left:20%;">测试负责人：</font></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr><tr>
					<td><font color="black" style="margin-left:20%;">测试人员数量：</font></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr><tr>
					<td><font color="black" style="margin-left:20%;">成本支出：</font></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr>
				<tr>
					<td><font color="black" style="margin-left:20%;">开始时间：</font></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr>
				<tr>
					<td><font color="black" style="margin-left:20%;">计划完成时间：</font></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr>
					<tr>
					<td><font color="black" style="margin-left:20%;">实际时间</font></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr>
				<tr>
					<td><font color="black" style="margin-left:20%;">完成状态：</font></td>
					<td><input type="text" name="" value="" class="form-control" ></td>
				</tr>
				<tr style="background-color:#4682B4;height:30px;">
					<td align="center" colspan="2"><input type="button" style="border:1px solid black" class="btn btn-primary"
						value="保存"	type="submit"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" style="border:1px solid black" class="btn btn-primary"
						value="取消"	onclick="closeDivFun2()" /></td>
				</tr>
			
		</form>
		</table>
	</div>

       
</body>
</html>

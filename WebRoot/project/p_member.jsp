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
<script src="script/exchange.js"></script>
  <script src="script/ie.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<style type="text/css">
table{color:black;}
#table td{text-align:center;vertical-align:middle;font-size:0.9em;font-color:black;border:1px solid black;}
td{font-size:0.9em;font-color:black;} 
li{list-style:none;}
 #sp{float: right;} 
a:hover{color:red;}
.li1 {
	list-style: none;
	width: 100px;
	white-space: nowrap;
	text-overflow: ellipsis;
	-o-text-overflow: ellipsis;
	overflow: hidden;
	margin-top: 5px;
}

.li2 {
	list-style: none;
	margin-top: 5px;
}
.mydiv1 input{width:150px;}
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
<table style="width:80%;margin-left:5%;"><tr><td>
		<ul>
			<li style="float:left;"><button
					onclick='return(confirm("确定删除全部人员信息?"))'
					class="btn btn-danger 	glyphicon glyphicon-trash">删除</button></li>
			<span style="float:right;">
				<li style="float:left;"><td><input type="text"
						class="form-control" placeholder="搜索" style="width:200px;"></td></li>
				<li style="float:left;"><td>
						<button type="submit" class="btn btn-primary">确定</button>
				</td></li>
			</span>
		</ul></td></tr></table>
		<hr />
		<table style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;" id="table" class="table table-condensed table-bordered table-hover table-striped">
			<tr  style="background-color:#4682B4;height:40px;">
				<td style="width:40px;"><input type="checkbox" name="all" id="all" /></td>
				<td><h4>序号</h4></td>
				<td><h4>项目名称</h4></td>
				<td><h4>项目经理</h4></td>
				<td><h4>硬件主测</h4></td>
				<td><h4>软件主测</h4></td>	
				<td><h4>硬测人员</h4></td>
				<td><h4>软测人员</h4></td>
				<td><h4>操作</h4></td>
			</tr>
			
			<c:forEach items="${projecthumans }" var="ph">
			  <tr style="height:40px;">
				<td><input type="checkbox" /></td>
				<td>${ph.id}</td>
			  	<td class="li1" onmouseover="this.className='li2'"
			  		onmouseout="this.className='li1'"><a
				  	href="Project_projectDetail?name=${ph.projectName}">${ph.projectName }</a></td>
				   <td data-toggle="modal" data-target="#myModal" id="${ph.VPM }">${ph.VPM }</td>
			  	<td data-toggle="modal" data-target="#myModal">${ph.hMaster }</td>
		   	    <td data-toggle="modal" data-target="#myModal">${ph.sMaster }</td>
				<td><c:forEach items="${ph.htesterName }" var="hn">
					${hn }
				</c:forEach></td>
				<td><c:forEach items="${ph.stesterName }" var="sn">
					${sn }
					</c:forEach>
					</td>
				<td><a  href="Projecthuman_addHumanPre?id=${ph.id }">编辑</a>|<a href="#" >查看</a>
	</td>
			</tr>
			</c:forEach>
		</table>
		<table style="width:80%;margin-left:5%;">
		<tr><td><span style="float:left;">共<span>&nbsp;&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;&nbsp;</span>页&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;第<span>&nbsp;&nbsp;&nbsp;&nbsp;10&nbsp;&nbsp;&nbsp;&nbsp;</span>页</span></td>
		<td><span id="sp">[<a href="#" class="right-font08">首页</a> | <a href="#" class="right-font08">上一页</a> | <a href="#" class="right-font08">下一页</a> | <a href="#" class="right-font08">末页</a>] 转至：<input type="text" style="width:50px;"/><button type="submit">确定</button></span></td></tr>
		</table>
		    <div id="hid"></div>
		<%-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
		<div class="modal-content">
		<div class="modal-body">
		<form>
		<jsp:include page="user.jsp"></jsp:include>
		</form>
		</div>
		</div>
		</div>
		</div> --%>
	<div id="popDiv" class="mydiv1" style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="closeDivFun()">
			<span
				style="width:10px;height:10px;line-height:10px;text-align:center;display:block;left:-20px;"><img src="images/check_error.gif"></span>
		</div>
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#87CEFA;">
		<td colspan="2" style="height:70px;">
		<h2 style="margin-left:30%"><font color="black" >编辑项目成员</font></h2></td>
		</tr>	
		<form action="user_update" method="post">
		
				<tr>

					<td><h4 style="margin-left:10%;">项目名称：</h4></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr>
				<tr>
					<td><h4 style="margin-left:10%;">项目经理：</h4></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr>
				<tr>
					<td><h4 style="margin-left:10%;">测试阶段：</h4></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr>
                <tr>
					<td><h4 style="margin-left:10%;">测试项：</h4></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr><tr>
					<td><h4 style="margin-left:10%;">硬件主测：</h4></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr><tr>
					<td><h4 style="margin-left:10%;">软件主测：</h4></td>
					<td><input type="text" name="" value="" class="form-control"></td>
				</tr>
				<tr>
					<td align="center"><button class="btn btn-primary"
							type="submit">保存</button></td>
					<td align="center"><button class="btn btn-primary"
							onclick="closeDivFun()">取消</button></td>
				</tr>
			
		</form>
		</table>
	</div>

</body>
</html>

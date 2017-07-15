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
<script src="script/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/exchange.css">

<jsp:include page="/admin/adminTop.jsp"></jsp:include>
<style type="text/css">
li {
	list-style: none;
}

#table td {
	border: 1px solid #EDEDED;
	font-size: 0.9em;
	font-color: black;
	text-align: center;
	vertical-align: middle;
}

td {
	font-size: 0.9em;
	font-color: black;
}
</style>


<script>
		function add(){
	
	var a=document.getElementById("add");
	var b=document.getElementById("hid");
    a.style.display="block";
     b.style.display="block";
	}
	function addhid(){
	
	var a=document.getElementById("add");
	var b=document.getElementById("hid");
    a.style.display="none";
     b.style.display="none";
	}
	function delet(obj)
	{
	layer.confirm('确定删除此组别?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                
                                window.location.href="user_deleteSelectType?id="+obj+"&selectType.type=1";//href="user_deleteSelectType?id=${st.id }&selectType.type=1" 
								},function(index){ layer.close(index);
								return false;
								});
	
	}
</script>
</head>

<body>

	
		<h3 style="margin-left:5%;" class="glyphicon glyphicon-tasks">组别列表</h3><br/>
	
	<button class="btn btn-primary" style="margin-left:5%;margin-top:1%;" onclick="add()">增加</button>
	<hr/>
	<table id="table"
		style="margin-left:5%;width:80%;TABLE-LAYOUT:fixed;WORD-BREAK:break-all">
		<tr style="background-color:#4682B4;height:40px;color: white;">
			<td><h4>编号</h4></td>
			<td><h4>组名</h4></td>
			<td><h4>操作</h4></td>
		</tr>
		<c:forEach items="${selectTypes }" var="st">
			<tr style="height:30px;">
				<td><font color="black">${st.id}
				</font></td>
				<td><font color="black">${st.name}
				</font></td>
				<td><a onclick="edit(this);">编辑</a>|<a onclick="delet(${st.id })">删除</a></td>
			</tr>
		</c:forEach>
		</table>
		<div id="hid"></div>
			<div class="mydiv" id="update" style="display:none;">
				<div align="right"
					style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
					onclick="edithid()">
					<span><font color="black">X</font></span>
				</div>
				<form action="user_updateSelectType" method="post" onSubmit="return check1();">
					<input type="hidden" name="selectType.type" value="1">
				<table style="width:100%;height:100%;">
					

						<tr style="background-color:#4682B4;height:40px;">
							<td colspan="2">
								<h4 style="margin-left:30%">
									<font color="black">分组修改</font>
								</h4>
							</td>
						</tr>
						<tr style="display:none;">

							<td><span style="margin-left:30%;"></span></td>
							<td><input type="hidden" name="selectType.id" value="" id="r1" class="form-control" style="width:150px;"></td>
						</tr>
						<tr>

							<td><span style="margin-left:30%;">组名：</span></td>
							<td><input type="text" name="selectType.name" value="" id="r2" class="form-control" style="width:150px;"></td>
						</tr>
						<!-- 点击确定后，所借时间和所借人根据ID号直接生成 -->
					<tr style="background-color:#4682B4;height:40px">
						<td align="center" colspan="2"><input type="submit"
							value="提交"  style="border:1px solid black;"
							class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="btn btn-primary" onclick="edithid()" style="border:1px solid black;" value="取消"
								type="button"></td>
					</tr>

					
				</table>
				</form>
			</div>
			<div class="mydiv" id="add" style="display:none;">
				<div align="right"
					style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
					onclick="addhid()">
					<span><font color="black">X</font></span>
				</div>
					<form action="user_addSelectType" method="post" onSubmit="return check();">
					<input type="hidden" name="selectType.type" value="1">
				<table style="width:100%;height:100%;">
				

							<tr style="background-color:#4682B4;height:40px;">
							<td colspan="2">
								<h4 style="margin-left:30%">
									<font color="black">分组增加</font>
								</h4>
							</td>
						</tr>
						
						<tr>

							<td><span style="margin-left:30%;">组名：</span></td>
							<td><input type="text" name="selectType.name" value="" class="form-control" style="width:150px;"></td>
						</tr>
						
						<!-- 点击确定后，所借时间和所借人根据ID号直接生成 -->
					<tr style="background-color:#4682B4;height:40px;">
						<td align="center" colspan="2"><input type="submit"
							value="提交" style="border:1px solid black;"
							class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input class="btn btn-primary" onclick="addhid()" value="取消" style="border:1px solid black;"
								type="button"></td>
					</tr>

					
				</table>
				</form>
			</div>
				<script>
			function edit(obj){
			var tr=obj.parentNode.parentNode;
			var ids=$(tr).children('td').eq(0)[0].children[0].innerText;
			var ss=$(tr).children('td').eq(1)[0].children[0].innerText;
			var a=document.getElementById("r1");
			var b=document.getElementById("r2");
			a.value=ids;
			b.value=ss;
			var c=document.getElementById("hid");
			var d=document.getElementById("update");
			c.style.display="block";
			d.style.display="block";
			}
			function edithid(){
			
			var c=document.getElementById("hid");
			var d=document.getElementById("update");
			c.style.display="none";
			d.style.display="none";
			}
			function check(){
     var a=document.getElementsByName('selectType.name')[0].value;
     if(a==""||a==null)
     {
     layer.msg("组名不能为空");
     return false;
     }else{return true;}
     }
     function check1(){
     var a=document.getElementById('r2').value;
     if(a==""||a==null)
     {
     layer.msg("组名不能为空");
     return false;
     }else{return true;}
     }
			</script>
</body>
</html>

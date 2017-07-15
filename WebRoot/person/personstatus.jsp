<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>与德测试信息管理系统</title>
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css">
<script src="script/layui/layui.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
	<link rel="stylesheet" href="script/kuang.css" media="all">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
li{list-style:none;}
#table{
 border-right: 6px inset 	#87CEFA; 
    border-bottom: 6px inset 	#87CEFA; 

}
 #table td{border:1px solid #4682B4;font-size:0.9em;font-color:black;

 }
 table{font-color:black;


 }
 #box{
 display:none;
 width:300px;
 height:200px;
 border:1px  solid #4682B4;
 background-color:white;
  margin: auto;
    position: absolute;
    z-index:3;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
   
	
 }
</style>
    <script>
    function display(obj){
    var ss=$(obj).text();
    var ll=document.getElementById("shoiw");
    ll.innerHTML=ss;
    layer.open({
				type: 1,
				title:'请选择需要的测试模块',
				skin: 'layui-layer-rim', //加上边框
				area: ['600px', '310px'], //宽高
				content:$("#shoiw"),
					btn: ['确定', '取消']
               ,yes: function(){
               layer.msg("haha"); 
		       layer.closeAll();
               }
               ,btn2: function(){
               layer.closeAll();
              }					
			  });
    }
    function disappear(){
    document.getElementById("box").style.display="none";
    }
    
    $(document).ready(function(){
    var row=document.getElementById("tabledd").rows.length;
    var cell = document.getElementById("tabledd").rows.item(0).cells.length;
    for(var i=0;i<row+1;i++)
	{ for(var j=0;j<cell;j++)
	{
	var tex=$("#tabledd tr:eq('"+i+"') td:eq('"+j+"')").text();//获取状态
    var strs;
    strs=tex.split(",");
    for(k=0;k<strs.length;k++){
   // alert(strs[jk]);
    if(strs[5]==1)
    {
    $("#tabledd tr:eq('"+i+"') td:eq('"+j+"')").css("background-color","#808080");//改变td的背景颜色为灰色
    }
    }
	}
	
	}
	
    });
    
    </script>
</head>

<body>
	<div style="mardin-left:60px;margin-top:1%;">
			<h3  style="margin-left:50px;" class="glyphicon glyphicon-tasks">系统测试人员工作状态表</h3>
			<!-- <ul >
			<div style="margin-left:67%;">
				<li style="float:left;"><select name="cases.level" style="width:150px;height:35px;" type="select" >
     			<option value="1" selected="selected"><font color="black">忙碌</font></option>
     			<option value="2"><font color="black">空闲</font></option>
     		 </select></li>
				<li style="float:left;">
						<button type="submit" style="height:35px;" class="btn  btn-primary">确定</button>
				</li>
					<li style="float:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
					<li style="float:left;"><select name="cases.level" style="width:150px;height:35px;" type="select" >
     			<option value="1" selected="selected"><font color="black">一组</font></option>
     			<option value="2"><font color="black">二组</font></option>
     			<option value="2"><font color="black">三组</font></option>
     			<option value="2"><font color="black">四组</font></option>
     			<option value="2"><font color="black">专项组</font></option>
     		 </select></li>
				<li style="float:left;">
						<button type="submit" class="btn btn-primary" style="height:35px;">确定</button>
				</li>
			</div>
		</ul> -->
	</div>
	<hr/>
	<table id="tabledd" style="margin-left:40px;width:90%;" class="layui-table">
	
	<% int i=0; %>
	<c:forEach items="${userStatus }" var="user" step="15">
	<tr>
	<c:forEach items="${userStatus }" var="user" begin="<%=i %>" end="<%=i+14 %>" step="1" varStatus="status">
	<td class="theme-login" onclick="display(this)" ><dl>
	<dt style="display:none">${status1.count }11,</dt>
	<dt>${user.name },</dt>
	<dd>${user.groupName }33,</dd>
	<dd style="display:none">${user.projectName }44,</dd>
	<dd style="display:none">${user.testModule }55,</dd>
	<dd style="display:none">${user.finsh }66</dd>
	</dl></td>
	<% i++; %>
	</c:forEach>
	</tr>
	</c:forEach>
	</table>
	<div id="shoiw">
	
	</div>
  </body>
</html>

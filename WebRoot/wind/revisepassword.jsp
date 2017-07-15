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

<title>修改密码</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<script src="script/js/jquery.min.js"></script>
  <script src="script/ie.js"></script>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style>
.code {
	
	font-family: Arial;
	font-style: italic;
	color: black;
	font-size: 25px;
	border: 0;
	padding: 1px 1px;
	letter-spacing: 3px;
	font-weight: bolder;
	float: left;
	cursor: pointer;
	width: 150px;
	height: 60px;
	

}

a {
	text-decoration: none;
	font-size: 12px;
	color: #288bc4;
}

a:hover {
	text-decoration: underline;
}

#Button1 {  
	margin-left: 2%;
}
body{-moz-user-select:none;}
</style>
<script type="text/javascript">
	var code;
	function creatCode() {
		code = "";
		var codeLength = 6; //验证码长度为6
		var checkCode = document.getElementById("checkCode");
		var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，也可以用中文的
		for (var i = 0; i < codeLength; i++) {
			var charNum = Math.floor(Math.random() * 52);
			code += codeChars[charNum];
		}
		if (checkCode) {
			checkCode.className = "code";
			checkCode.innerHTML = code;
		}
	}

	function validateCode() {
		var inputCode = document.getElementById("inputCode").value;
		if (inputCode.length <= 0) {
			layer.msg("请输入验证码");
		} else if (inputCode.toUpperCase() != code.toUpperCase()) {
			layer.msg("验证码输入有误");
			creatCode();
		} else {
			var pwd=document.getElementById("pwd").value;
			var newPwd=document.getElementById("newPwd").value;
			var rePwd=document.getElementById("rePwd").value;
			var array=[{'pwd':pwd,'newPwd':newPwd,'rePwd':rePwd}];
			if(pwd==newPwd){
				//layer.msg("新密码和原始密码不能相同",{time:2000});
				alert("新密码和原始密码不能相同");
			}else if(newPwd!=rePwd){
				//layer.msg("两次输入的密码不一致",{time:2000});
				alert("两次输入的密码不一致");
			}else{
				$.ajax({
					type:'post',
					url:'updatePwd',
					data:{"params":JSON.stringify(array)},
					dataType:'json',
					success:function(data){
					//alert(data);
						if(data=='success'){
						//layer.msg("密码修改成功",{time:1000});
						alert("密码修改成功");
						setTimeout(function(){parent.location='wind/all.jsp'},2000);
						
						}else if(data=='fail'){
							window.location.href="Login_logout?message='原密码输入不正确，该账户已被锁定，请联系管理员解锁'";
						}else{
						//alert("dsaf"+data);
						layer.msg(data,{time:1000});
						}
					},
					fail:function(xhx,e,errMsg){
						alert(errMsg);
					}
				});
			}
			
			
		}
	}
	document.onselectstart=new Function("event.returnValue=false;"); 
</script>

</head>
<body onload="creatCode()">
<%-- <c:if test="${not empty message }">
	<jsp:include page="/wind/top.jsp"></jsp:include>
</c:if> --%>
<jsp:include page="/wind/top.jsp"></jsp:include>
	<div class="layui-tab layui-tab-card" style="margin-left:3%;margin-top:2%;width:80%;min-width:600px;border:1px solid #4682B4;">
  <ul class="layui-tab-title" style="background-color:#4682B4;border:1px solid #4682B4;">
    <li class="layui-this">修改密码</li>
  </ul>
  <div class="layui-tab-content" style="height: 80%;">
    <div class="layui-tab-item layui-show">
    
	<blockquote class="layui-elem-quote">	 
	<font size="5px" color="red">${message }</font>
<br><label>旧密码：</label><input type="password" class="layui-input" style="width:300px;"
					id="pwd" placeholder="请输入旧密码" width="200">
</br>
<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
					width="100%" color=#987cb9 SIZE=10>
<br><label>新密码：</label><input type="password" class="layui-input" style="width:300px;"
					id="newPwd" placeholder="请输入新密码" width="200">
</br>
<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
					width="100%" color=#987cb9 SIZE=10>
<br><label>确认密码：</label><input type="password"
					class="layui-input" style="width:300px;" id="rePwd" placeholder="请再次输入新密码"
					width="200">
</br>
<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
					width="100%" color=#987cb9 SIZE=10>
<br><label>验 证 码：</label><input type="text" id="inputCode" class="layui-input" style="width:300px;"
					placeholder="请输入验证码" width="200">
</br>
<br>
				<div class="code" id="checkCode"></div>
				<a href="javascript:creatCode();">看不清，换一张</a></br>
</blockquote>			   
          <button class="layui-btn layui-btn-normal layui-btn-radius" id="Button1"
				onclick="validateCode();" type="button">确定</button>
				</div>
    </div>
    
  </div>

</body>
</html>

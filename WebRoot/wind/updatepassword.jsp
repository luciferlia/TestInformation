<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link rel="shortcut icon" href="images/logio.ico" />
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

body {
	-moz-user-select: none;
}
</style>
<script type="text/javascript">
layui.use('layer', function(){
  var layer = layui.layer;
 
}); 
	function sendMsg(){
		var username=document.getElementById("username").value;
		$.ajax({
			type:"post",
			url:"sendMsg1?username="+username,
			success : function(data) {
					layer.msg(data, {
				time : 2000
			});
				},
				fail : function(xhx, e, errMsg) {
					alert(errMsg);
				}
		
		});
	}

	function validateCode() {

		var username=document.getElementById("username").value;
		var newPwd = document.getElementById("newPwd").value;
		var rePwd = document.getElementById("rePwd").value;
		var code=document.getElementById("inputCode").value;
		//var array=[{'pwd':pwd,'newPwd':newPwd,'rePwd':rePwd}];
		if (newPwd != rePwd) {
			layer.msg("两次输入的密码不一致", {
				time : 2000
			});
		} else {
			$.ajax({
				type : 'post',
				url : 'SettingPwd?newPwd='+newPwd+"&username="+username+"&code="+code,
				success : function(data) {
					//alert(data);
					if (data == 'success') {
						layer.msg("密码设置成功,返回登录界面", {
							time : 2000
						});
						setTimeout(function() {
							parent.location = 'index.jsp'
						}, 2000);

					} else if (data == 'fail') {
						layer.msg("校验码输入有误", {
							time : 2000
						});
						//window.location.href = "Login_logout?message='原密码输入不正确，该账户已被锁定，请联系管理员解锁'";
					} else {
						//alert("dsaf"+data);
						layer.msg(data, {
							time : 1000
						});
					}
				},
				fail : function(xhx, e, errMsg) {
					alert(errMsg);
				}
			});
		}



	}
	document.onselectstart = new Function("event.returnValue=false;");
	
</script>

</head>
<body>
	<div class="layui-tab layui-tab-card"
		style="margin-left:3%;margin-top:2%;width:80%;min-width:600px;border:1px solid #4682B4;">
		<ul class="layui-tab-title"
			style="background-color:#4682B4;border:1px solid #4682B4;">
			<li class="layui-this">修改密码</li>
		</ul>
		<div class="layui-tab-content" style="height: 80%;">
			<div class="layui-tab-item layui-show">

				<blockquote class="layui-elem-quote">
					<p style="color: red;">${message }</p>
					<br><label>用户名：</label><input type="text" class="layui-input" style="width:300px;"
					id="username" value="${username }" disabled="disabled" width="200">
					</br>
					<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
					width="100%" color=#987cb9 SIZE=10>
					<br>
					<label>新密码：</label><input type="password" class="layui-input"
						style="width:300px;" id="newPwd" placeholder="请输入新密码" width="200">
					</br>
					<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
						width="100%" color=#987cb9 SIZE=10>
					<br>
					<label>确认密码：</label><input type="password" class="layui-input"
						style="width:300px;" id="rePwd" placeholder="请再次输入新密码" width="200">
					</br>
					<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
						width="100%" color=#987cb9 SIZE=10>
					<br>
					<label>校验码：</label><input type="text" id="inputCode"
						class="layui-input" style="width:300px;" placeholder="请输入校验码"
						width="200"> <a onclick="sendMsg()">重新获取</a><br>

				</blockquote>
				<button class="layui-btn layui-btn-normal layui-btn-radius"
					id="Button1" onclick="validateCode();" type="button">确定</button>
			</div>
		</div>

	</div>

</body>
</html>

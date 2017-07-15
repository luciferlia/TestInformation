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

<title>忘记密码</title>
<link rel="shortcut icon" href="images/logio.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<script src="script/js/jquery.min.js"></script>
  <script src="script/ie.js"></script>
  <jsp:include page="/wind/top.jsp"></jsp:include>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style>
.code {
	
	font-family: Arial;
	font-style: italic;
	color: black;
	font-size: 0.9em;
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
	font-size: 0.9em;
	color: #288bc4;
}

a:hover {
	text-decoration: underline;
}

#Button1 {
	margin-left: 2%;
}

</style>
<script type="text/javascript">
layui.use('layer', function(){
  var layer = layui.layer;
 
}); 
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
		var username=document.getElementById("username").value;
		if (inputCode.length <= 0) {
			layer.msg("请输入验证码");
		} else if (inputCode.toUpperCase() != code.toUpperCase()) {
			layer.msg("验证码输入有误");
			creatCode();
		} else {
			$.ajax({
				type:"post",
				url:"validateName1?username="+username,
				success:function(data){
    			//alert(data);
				if(data=="success")
				{
				alert("您输入的用户名不存在，请确认输入的用户名是否正确");
				
				//obj.parentNode.nextElementSibling.innerHTML="工号可以使用"; 
				}else{
					alert("用户名输入正确");
					window.location.href="sendMsg?username="+username;
				}
				},
				fail:function(xhx,e,errMsg){
					alert(errMsg);
				}
			});
			//layer.msg("我们将会发送一封邮件到你的邮箱，请注意查收");
		}
	}
	document.onselectstart=new Function("event.returnValue=false;"); 
</script>

</head>
<body onload="creatCode()">
	
	<div class="layui-tab layui-tab-card" style="margin-left:3%;margin-top:2%;width:80%;min-width:600px;border:1px solid #4682B4;">
  <ul class="layui-tab-title" style="background-color:#4682B4;border:1px solid #4682B4;">
    <li class="layui-this">找回密码</li>
  </ul>
  <div class="layui-tab-content" style="height: 80%;">
    <div class="layui-tab-item layui-show">
    
	<blockquote class="layui-elem-quote">	 
<label>用户名：</label><input type="text" class="layui-input" style="width:300px;"
					id="username" placeholder="请输入您的用户名" width="200" value="${username }">

<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
					width="100%" color=#987cb9 SIZE=10>


<label>验 证 码：</label><input type="text" id="inputCode" class="layui-input" style="width:300px;"
					placeholder="请输入验证码" width="200">

	<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
					width="100%" color=#987cb9 SIZE=10>	
		  
		 
	 <div class="code" id="checkCode" unselectable="on" onselectstart="return false;" style="-moz-user-select:none;"></div>
				   <a href="javascript:creatCode();">看不清，换一张</a>
	</blockquote>			   
          <button class="layui-btn layui-btn-normal layui-btn-radius" id="Button1"
				onclick="validateCode();" type="button">下一步</button>
				</div>
    </div>
    
  </div>

	
	
</body>
</html>

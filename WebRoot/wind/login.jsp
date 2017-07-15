<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta charset="utf-8">
  <link rel="shortcut icon" href="images/logio.ico" />
	<title>测试管理系统</title>
	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
<script src="script/js/jquery.min.js"></script>
  <script src="script/ie.js"></script>
        <!-- CSS -->
        <link rel="stylesheet" href="script/css/reset.css">
        <link rel="stylesheet" href="script/css/supersized.css">
        <link rel="stylesheet" href="script/css/style2.css">
        <script src="script/js/jquery.min.js"></script>
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<style>
a{
text-decoration:none;
}
</style>
   <script>
      $.fn.onlyNumAlpha=function (){
      $(this).keypress=(function (event){
      var eventObj=event||e;
      var keyCode=evetObj.keyCode||eventObj.which;
      if((keyCode >= 48 && keyCode <= 57)||(keyCode >= 65 && keyCode <= 90)||(keyCode >= 97 && keyCode <= 122))
      return true;
      else
      return false;
      }).focus(function (){
      this.style.imeMode='disabled';
      }).bind("paste",function (){
      var clipboard = window.clipboardData.getData("Text");
        if (/^(\d|[a-zA-Z])+$/.test(clipboard))
            return true;
        else
            return false;
      });
      };
$(function () {
    
    // 限制使用了onlyNumAlpha类样式的控件只能输入数字和字母
    $(".username").onlyNumAlpha();
   });
   
   function valiCheck(){
   		$.ajax({
   			type:"post",
			url:"logLogin",
   		
   		});
   	
   }
</script>
    </head>

    <body oncontextmenu="return false">

        <div class="page-container">
            <h1>用户登录</h1>
            <form action="Login_login" method="post" id="commentForm">
            	<div style="color: red;">
                	<s:property value="#attr.message"/>
                </div>
				<div>
					<input type="text" name="username" onkeyup="this.value=this.value.replace(/[^\w]/g,'');" class="username" placeholder="用户名" autocomplete="off"  minlength="2" maxlength="18" value="${username }"/>
				<span></span>
				</div>
                <div>
		<input type="password" name="password" class="password" placeholder="密码" oncontextmenu="return false" onpaste="return false"  minlength="2" maxlength="16"/>
                </div>
                
                <button id="submit" type="submit">登录</button>
                 <a href="wind/forget.jsp" style="float:right"><font color="white">忘记密码</font></a>
            </form>
            <div class="connect">
                <p>WIND Test Management System</p>
				<p style="margin-top:20px;" >与德测试信息管理系统</p>
            </div>
        </div>
		<div class="alert" style="display:none">
			<h2>消息</h2>
			<div class="alert_con">
				<p id="ts"></p>
				<p style="line-height:70px"><a class="btn">确定</a></p>
			</div>
		</div>
 <div id="hid"></div>
        <!-- Javascript -->
		
        <script src="script/js/supersized.3.2.7.min.js"></script>
        <script src="script/js/supersized-init.js"></script>
		<script>
		$(".btn").click(function(){
			is_hide();
		})
		var u = $("input[name=username]");
		var p = $("input[name=password]");
		$("#submit").live('click',function(){
			if(u.val() == '' || p.val() =='')
			{
				$("#ts").html("用户名或密码不能为空~");
				is_show();
				return false;
			}
			else{
				var reg = /^[0-9A-Za-z]+$/;
				if(!reg.exec(u.val()))
				{
					$("#ts").html("用户名错误");
					is_show();
					return false;
				}
			}
		});
		window.onload = function()
		{
			$(".connect p").eq(0).animate({"left":"0%"}, 600);
			$(".connect p").eq(1).animate({"left":"0%"}, 400);
		}
		function is_hide(){ $(".alert").animate({"top":"-40%"}, 300) }
		function is_show(){ $(".alert").show().animate({"top":"45%"}, 300) }
		</script>
    </body>
   
</html>



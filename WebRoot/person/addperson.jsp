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

<title>与德测试信息管理系统</title>
<script src="script/js/jquery.min.js"></script>


<script src="script/js/jquery.validate.min.js"></script>
<jsp:include page="/admin/adminTop.jsp"></jsp:include>
<script src="script/checkmsg.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<style>

#wai {
	border: 1px solid #4682B4;
}

td {
	font-size: 1em;
	font-color: black;
	
}
#table td{border:1px solid #EDEDED;}
#box1 {float: left;width:40%;height:80%;border: 1px solid black;}
#box2 {float: left;}
.box {
	position: relative;
	border: 2px solid #009ACD;
	width: 90%;
	height: 95%;
	margin-left: 60px;
	margin-top: 5px;
}

.box::before {
	content: attr(title);
	position: absolute;
	left: 10%;
	transform: translateX(-50%);
	-webkit-transform: translate(-50%, -50%);
	padding: 0 10px;
	background-color: #fff;
}
.layui-input{width:150px;height:35px;border:1px solid #B8B8B8;}
.mblack
{
  padding-top: 4px;
  padding-left: 8px;
  vertical-align: top;
  white-space: nowrap;
}
.error {
  background:url("images/check_error.gif") no-repeat 0px;
  font-weight: bold;
  padding-left:16px;
  color: #EA5200;
}
.checked {
  background:url("images/check_right.gif") no-repeat center center;
}
</style>
<style>
.error{
	color:red;
}
</style>
<script>

 $(function(){
            var validate = $("#commentForm").validate({
                debug: true, //调试模式取消submit的默认提交功能    
                
                errorElement: 'span',
                submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form   
                    //alert("提交表单");   
                    form.submit();   //提交表单   
                },   
                
                rules:{
                    "user.name":{
                        isChinese:true,
                        required:true,
                        minlength:true
                    },  
                    "user.num":{
                        isNum:true
                        
                    },
                    "user.username":{
                       isRightfulString:true
                    },
                    "user.password":{
                       isRightfulString:true
                    }, 
                    "user.phone":{
                        isTel:true
                    }, 
                    "user.email":{
                        isMail:true                    
                    }               
                },
                //如果验证控件没有message，将调用默认的信息
                messages:{
                    "user.name":{
                        isChinese:"只能输入汉字"
                    },
                     "user.num":{
                        isNum:"只能输入0-9数字"
                    },
                    "user.username":{
                       isRightfulString:"只能是字母和数字组成"
                    },
                    "user.password":{
                       isRightfulString:"只能为字母和数字，长度在6-16之间"
                    },
                     "user.phone":{
                       isTel:"请输入正确的手机号码"
                    },
                    "user.email":{
                       isMail:"请输入正确的邮箱格式"
                    }
                                                     
                },
             
              success:function(label){
             label.text("").addClass("checked"); //正确的时候输出的样式为checked
                  },
             errorPlacement: function(error, element) {
            if (element.is(":radio"))
                error.appendTo(element.parent().next());  //如果元素色type为radio将错误的信息输出到该元素的父类元素的下一个元素
            else if (element.is(":checkbox")) //如果元素色type为checkbox将错误的信息输出到该元素的父类元素的下一个元素中
                error.appendTo(element.next());
            else
                error.appendTo(element.parent().next());
                 },
             highlight: function(element, errorClass) {
            $(element).parent().next().find("." + errorClass).removeClass("checked");//表单用户(获取到焦点)操作时如果正确就移除错误的css属性添加正确的css属性
              }        
            });    
        
        });

</script>
<script>
	function send(obj)
	{
	
	var name=obj.value;
	//alert(name);
	 if(name=="")
	{obj.parentNode.nextElementSibling.innerHTML="<font color=red>用户名不能为空</font>"; 
	return} 
	$.ajax({
	type:"post",
	url:"validateName?username="+name,
	success:function(data){
	//alert(data);
	if(data=="success")
	{
	obj.parentNode.nextElementSibling.innerHTML="用户名可以使用"; 
	}else{
	obj.parentNode.nextElementSibling.innerHTML="<font color=red>用户名已存在</font>"; 
	obj.value="";
	}
	}
	}); 
	}
	function send1(obj)
	{
	
	var name=obj.value;
	if(name=="")
	{obj.parentNode.nextElementSibling.innerHTML="<font color=red>工号不能为空</font>"; 
	return}
	$.ajax({
	type:"post",
	url:"validateNum?num="+name,
	success:function(data){
    //alert(data);
	if(data=="success")
	{
	//alert("工号可以使用");
	obj.parentNode.nextElementSibling.innerHTML="工号可以使用"; 
	}else{
	obj.parentNode.nextElementSibling.innerHTML="<font color=red>工号已存在</font>"; 
	obj.value="";
	}
	},
	fail:function(xhx,e,errMsg){
		alert(errMsg);
	}
	});
	}
	function checkp(){
	var a=document.getElementById("name").value;
	var b=document.getElementById("num").value;
	var c=document.getElementById("username").value;
	var d=document.getElementById("psd").value;
    var e=document.getElementById("mail").value;
	if(a==""||a==null || b==""||b==null || c==""||c==null|| d==""||d==null || e==""||e==null){
	layer.msg("提交失败，除手机号外的值不能为空");
	return false;
	}
	else {
	return true;
	}
	}
	</script>
</head>
<body>
	 <form action="user_addPerson" method="post" id="commentForm" onSubmit="return checkp();">
 	<table id="wai" style="width:90%;min-width:1000px;height:80%;margin-left:1%;margin-top:1%;background-color:white;min-height:600px;">
		<tr style="background-color:#4682B4;height:40px;">
			<td></td>
		</tr>
		
		<tr style="text-align: center;vertical-align: middle;">
			<td>
				
     
   
  
     <table style="margin-left:1%;margin-top:2%;width:90%;min-width:900px;height:80%;min-height:450px;TABLE-LAYOUT:fixed;WORD-WRAP:break_word;"id="table" class="table table-condensed table-bordered table-hover table-striped">
     	<tr><td colspan="2" rowspan="5" style="width:25%;min-width:200px;text-align:center;vertical-align:middle;"><img src="images/ren.gif"></td>
     	<td style="width:5%"><span style="float:left;">姓名</span></td><td style="width:200px;"><input type="text" value="" name="user.name" id="name"  class="layui-input"  minlength="2" maxlength="5" /></td style="min-width:100px;"><td><label for="user.name" generated="true" class="mblack"></label></td>
     		<td style="width:5%"><span style="float:left;">性别</span></td><td style="width:200px;">
     		<select name="user.sex"  style="width:150px;height:35px;"><option value="男" selected="selected">男</option><option value="女">女</option></select><td></td>
     		</td></tr>
   
     	
     	<tr>
     		<td ><span style="float:left;">工号</span></td><td><input type="text" value="" onblur="send1(this)" name="user.num" id="num" required data-rule-number="true" class="layui-input" data-msg-required="不能为空" data-msg-msg="只能输入0-9数字" data-msg-minlength="请输入最小3位" minlength="3" maxlength="5"/></td><td></td>
     		<td ><span style="float:left;">部门</span></td><td>
     		<select name="user.department" style="width:150px;height:35px;" ><option value="软件测试部" selected="selected">软件测试部</option><option value="硬件测试部">硬件测试部</option></select><td></td>
     		</td>
     	</tr>
     		
     	<tr>
     		
     		<td ><span style="float:left;">用户名</span></td><td><input type="text" value="" onblur="send(this)" name="user.username" id="username" class="layui-input" required data-msg-required="不能为空" minlength="2" maxlength="18" data-msg-required="请输入用户姓名" data-msg-msg="用户名为字母"data-msg-minlength="请输入最小2位" /></td><td></td>
     		<td ><span style="float:left;">密码</td><td><input type="text" value=""  class="layui-input" name="user.password" id="psd" required data-msg-required="不能为空" minlength="6" maxlength="18" data-msg-required="请输入密码" data-msg-minlength="请输入最小6位"/></td><td></td>
     	</tr>
     		
     	<tr>
     	<td><span style="float:left;">所属组</span></td><td>
       
     	<select name="user.groupName"  style="width:150px;height:35px;" >
     	<c:forEach items="${selectTypes }" var="st">
     		<c:if test="${st.type=='1' }">
     		<option value="${st.name }" >${st.name }</option>
     		</c:if>
     	</c:forEach>
     	</select>
     	
     	</td><td></td>
     		<td><span style="float:left;">职位</span></td><td>
     	<select name="user.position"  style="width:150px;height:35px;" >
     	<c:forEach items="${selectTypes }" var="st">
     		<c:if test="${st.type=='2' }">
     		<option value="${st.name }" selected="selected">${st.name }</option>
     		</c:if>
     	</c:forEach>
     	</select>
     		</td><td></td>
     		
     	</tr>
     		
     	<tr>
     	<td ><span style="float:left;">手机</span></td><td><input type="text" value="" class="layui-input" name="user.phone" id="phone"  required data-rule-mobile="true" data-msg-required="请输入手机号" maxlength="11" data-msg-mobile="请输入正确格式"/></td><td></td>
     	
     		<td ><span style="float:left;">邮箱</span></td><td><input type="text" value=""  class="layui-input" name="user.email" id="mail"required data-rule-email="true" data-msg-required="请输入email地址" data-msg-email="请输入正确的email地址"/></td><td></td>
     		
     	</tr>
     	
     </table>
    
     <p style="color: red;">${message }</p>
    
			</td>
		</tr>
		<tr style="height:40px;">
			<td><button type="submit" style="margin-left:70%;" class="layui-btn layui-btn-primary layui-btn-radius">【保存】</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button  type="reset" href="wind/usecase.jsp" target="mainFrame" class="layui-btn layui-btn-primary layui-btn-radius" style="display: none;">【返回】</button></td>
		</tr>
	</table>
	</form>
	
</body>
</html>

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
<script src="script/js/bootstrap.min.js"></script>
<script src="date/laydate.js"></script>
 <script src="script/jquery.min.js"></script>
 <jsp:include page="/wind/top.jsp"></jsp:include>
	<link rel="stylesheet" href="script/kuang.css" media="all">

 <style>
     #we li{list-style:none;
	     float:left;} 
	 #table td{border:1px solid #4682B4;font-size:0.9em;}	
	
    </style>
    <script type="text/javascript">
  jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
	});
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	});

}

);
    </script>
</head>
<body>
  <h4 style="margin-left:1%;margin-top:1%;"><ul id="we">
  <li><a class="glyphicon glyphicon-th-large">使用情况</a></li>
  <li><a class="glyphicon glyphicon-list-alt  theme-login" onclick="javascript:;">申请资源</a></li>
  <li><span class="glyphicon glyphicon-list-alt" onclick="laydate()" readonly>按时间查找</span></li>
  <li></li>
  </ul></h4>

  <HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
			width="1700px" color=#987cb9 SIZE=10>
			
<table style="width:95%;height:90%;margin-left:20px;min-width:600px;min-height:600px;" id="table">

<tr style="height:30px;background-color:#4682B4;border:1px solid black;">
<td style="width:30px;border:1px solid black;"><font color="black">ID</font></td>
<td style="width:70px;border:1px solid black;"><font color="black">实验室</font></td>
<td style="width:150px;border:1px solid black;"><font color="black">仪器设备</font></td>
<td style="border:1px solid black;"><font color="black">00</font></td>
<td style="border:1px solid black;"><font color="black">01</font></td>
<td style="border:1px solid black;"><font color="black">02</font></td>
<td style="border:1px solid black;"><font color="black">03</font></td>
<td style="border:1px solid black;"><font color="black">04</font></td>
<td style="border:1px solid black;"><font color="black">05</font></td>
<td style="border:1px solid black;"><font color="black">06</font></td>
<td style="border:1px solid black;"><font color="black">07</font></td>
<td style="border:1px solid black;"><font color="black">08</font></td>
<td style="border:1px solid black;"><font color="black">09</font></td>
<td style="border:1px solid black;"><font color="black">10</font></td>
<td style="border:1px solid black;"><font color="black">11</font></td>
<td style="border:1px solid black;"><font color="black">12</font></td>
<td style="border:1px solid black;"><font color="black">13</font></td>
<td style="border:1px solid black;"><font color="black">14</font></td>
<td style="border:1px solid black;"><font color="black">15</font></td>
<td style="border:1px solid black;"><font color="black">16</font></td>
<td style="border:1px solid black;"><font color="black">17</font></td>
<td style="border:1px solid black;"><font color="black">18</font></td>
<td style="border:1px solid black;"><font color="black">19</font></td>
<td style="border:1px solid black;"><font color="black">20</font></td>
<td style="border:1px solid black;"><font color="black">21</font></td>
<td style="border:1px solid black;"><font color="black">22</font></td>
<td style="border:1px solid black;"><font color="black">23</font></td>
</tr>
<tr>
<td>1</td>
<td>实验室1</td>
<td><ul>
<li>恒温恒湿箱#9</li>
<li>紫外光照箱</li>
<li>快速温变箱
</li>
</ul></td>
<td class="theme-login" onclick="javascript:;"></td>
<td style="background-color:blue;" id="bg" class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td  class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
</tr>
<tr>
<td>2</td>
<td>实验室2</td>
<td><ul>
<li>淋雨试验机</li>
<li>按键寿命试验机#1</li>
<li>软压试验机#2</li>
</ul></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
</tr>
<tr>
<td>3</td>
<td>实验室3</td>
<td><ul>
<li>扭曲试验机#1</li>
<li>接口插拔试验机#1</li>
<li>摩擦寿命试验机#1</li>
</ul></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
</tr>
<tr>
<td>4</td>
<td>实验室4</td>
<td><ul>
<li>纸带耐磨试验机#1
</li>
<li>弹簧锤
</li>
<li>全自动综合测力试验机#1
</li>
</ul></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
</tr>
<tr>
<td>5</td>
<td>实验室5</td>
<td><ul>
<li>三轴全自动荷重试验机
</li>
<li>指针式推拉力计#1
</li>
<li>电子式推拉力计#2
</li>
</ul></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
</tr>
<tr>
<td>6</td>
<td>实验室6</td>
<td><ul>
<li>砂尘试验箱
</li>
<li>定向跌落仪#1
</li>
<li>电动振动试验系统
</li>
</ul></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
<td class="theme-login" onclick="javascript:;"></td>
</tr>
<tr style="height:25%;">
<td colspan="27"> 
<table style="width:100%;height:100%;">
<tr style="height:30px;background-color:#4682B4;">
<td><font color="black">实验室使用状态</font></td>
</tr>
<tr>
<td>实验室1 叶宇斌  3/1 9:00--11:00</td>
</tr>
</table>
</td>
</tr>
</table>


<div class="theme-popover" id="ll">
<div class="theme-poptit" style="background-color:#4682B4;">
<a href="javascript:;" title="关闭" class="close"><font color="black">×</font></a>
          <h4><font color="black">申请实验室资源</font></h4>
</div>
<div class="theme-popbod dform">
<form method="post">
<table style="width:100%;height:310px;">
<tr><td colspan="2"><font size="3" color="red" style="margin-left:60px;">实验室资源提前预约≤2天以内，超过2天以上不接收预约</font></td></tr>
<tr><td colspan="2"><font size="3" color="red" style="margin-left:60px;">若1个月内超出3次预约但未使用，将以黄榜公布并惩罚</font></td></tr>
<tr><td style="width:30%;"><font size="2" style="margin-left:90px;">实验室信息</font></td>
<td><input style="width:200px;"></td></tr>
<tr><td><font size="2" style="margin-left:90px;">申&nbsp;&nbsp;请&nbsp;&nbsp;人</font></font></td>
<td><input style="width:200px;margin-left:0px;"></td></tr>
<tr><td><font size="2" style="margin-left:90px;">申请内容</font></td>
<td><input style="width:200px;"></td></tr>
<tr><td><font size="2" style="margin-left:90px;">开始日期</font></td>
<td><input style="width:200px;" onclick="laydate()" readonly></td></tr>
<tr><td><font size="2" style="margin-left:90px;" >开始时间</font></td>
<td>
<select name="h" id="h" onchange="show();">
<script>
for(var i=0;i<=23;i++){
 document.write("<option value='"+i+"'>"+i+"</option>");
}
</script>
</select>
时
<select name="min" id="min" onchange="show();">
<script>
for(var i=0;i<=59;i++){
 document.write("<option value='"+i+"'>"+i+"</option>");
}
</script>
</select>
分<input style="width:70px;" name="time" id="time" readonly="readonly"></td></tr>
<tr><td><font size="2" style="margin-left:90px;">结束日期</font></td>
<td><input style="width:200px;" onclick="laydate()" readonly></td></tr>
<tr><td><font size="2" style="margin-left:90px;" >结束时间</font></td>
<td>
<select name="h1" id="h1" onchange="show1();">
<script>
for(var i=0;i<=23;i++){
 document.write("<option value='"+i+"'>"+i+"</option>");
}
</script>
</select>
时
<select name="min1" id="min1" onchange="show1();">
<script>
for(var i=0;i<=59;i++){
 document.write("<option value='"+i+"'>"+i+"</option>");
}
</script>
</select>
分<input style="width:70px;" name="time1" id="time1" readonly="readonly"></td></tr>
<tr><td colspan="2" ><div style="margin-left:35%"><input class="btn btn-primary" style="width:60px;" onclick="apply()" name="submit" value=" 提交" />
<input class="btn btn-primary"  name="submit" style="width:60px;" value=" 取消 " onclick="hide()"/></div></td></tr>
</table>
</div>
</div>
<div class="theme-popover-mask" id="lg">
</div>

<script>
function show(){
 document.getElementById("time").value=document.getElementById("h").value+"时"+document.getElementById("min").value+"分"
}
</script>
<script>
function show1(){
 document.getElementById("time1").value=document.getElementById("h1").value+"时"+document.getElementById("min1").value+"分"
}
</script>
<script>
function apply(){
document.getElementById("ll").style.display ="none";
document.getElementById("lg").style.display = "none";
document.getElementById("bg").style.background="red";
}
</script>
<script>
function hide(){
document.getElementById("ll").style.display = "none";
document.getElementById("lg").style.display = "none";
}
</script>
</body>
</html>
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
<meta charset="utf-8">
<title>与德测试信息管理系统</title>

<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<jsp:include page="/admin/adminTop.jsp"></jsp:include>
<style>
li{list-style:none;}
table{border:1px solid #EDEDED;}
#ll{border:1px solid black;}
td{border:1px solid #EDEDED; font-size:0.9em;font-color:black;
padding:10px;}
#table1 {background-color:#FFF8DC;}
</style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script>
function pageScroll(){
    //把内容滚动指定的像素数（第一个参数是向右滚动的像素数，第二个参数是向下滚动的像素数）
    window.scrollBy(0,-100);
    //延时递归调用，模拟滚动向上效果
    scrolldelay = setTimeout('pageScroll()',100);
    //获取scrollTop值，声明了DTD的标准网页取document.documentElement.scrollTop，否则取document.body.scrollTop；因为二者只有一个会生效，另一个就恒为0，所以取和值可以得到网页的真正的scrollTop值
    var sTop=document.documentElement.scrollTop+document.body.scrollTop;
    //判断当页面到达顶部，取消延时代码（否则页面滚动到顶部会无法再向下正常浏览页面）
    if(sTop==0) clearTimeout(scrolldelay);
}
</script>
  </head>
  
  <body>
  <a name="top"></a>
	  <a onclick="pageScroll()" style="display:block; position:fixed; right:20px; bottom:20px;"><img src="images/top.jpg"></a>
    <table style="width:90%;height:100%;">
    <tr>
    <td style="width:20%;height:100%;">
    <table style="width:200px;height:100%;" id="table1">
    <tr><td><font color="black" style="margin-left:50px;" ><input type="checkbox" value="" id="checkedId" name="checkedId">VPM</font></td></tr>
    <tr><td><font color="black" style="margin-left:50px;"><input type="checkbox" value="" id="checkedId" name="checkedId">软件主测</font></td></tr>
    <tr><td><font color="black" style="margin-left:50px;"><input type="checkbox" value="" id="checkedId" name="checkedId">硬件主测</font></td></tr>
    <tr><td><font color="black" style="margin-left:50px;"><input type="checkbox" value="" id="checkedId" name="checkedId">组长</font></td></tr>
    <tr><td><font color="black" style="margin-left:50px;"><input type="checkbox" value="" id="checkedId" name="checkedId">自动化主管</font></td></tr>
    <tr><td><font color="black" style="margin-left:50px;"><input type="checkbox" value="" id="checkedId" name="checkedId">CTS主管</font></td></tr>
    <tr><td><font color="black" style="margin-left:50px;"><input type="checkbox" value="" id="checkedId" name="checkedId">专项主管</font></td></tr>
    <tr><td><font color="black" style="margin-left:50px;"><input type="checkbox" value="" id="checkedId" name="checkedId">用例开发</font></td></tr>
    <tr><td><font color="black" style="margin-left:50px;"><input type="checkbox" value="" id="checkedId" name="checkedId">软件测试人员</font></td></tr>
    <tr><td><font color="black" style="margin-left:50px;"><input type="checkbox" value="" id="checkedId" name="checkedId">硬件测试人员</font></td></tr>
    <tr><td><font color="black" style="margin-left:50px;"><input type="checkbox" value="" id="checkedId" name="checkedId">资源管理员</font></td></tr>
             
    </table>
    </td>
    <td style="width:80%;height:100%;">
     <div id="l2">
    <form action="" method="get"> 
    <div>
   <table style="width:100%;height:100%;">
<tr style="height:40%;"><td>
<span id="box1">
<ul id="tree">
<li><input type=checkbox>测试用例
<ul>
<li><input type=checkbox>软件测试用例
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
<li><input type=checkbox>硬件测试用例
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
</ul>
</li>
</ul></span>
</td><td>
<span id="box2">
<ul id="tree1">
<li><input type=checkbox>项目管理
<ul>
<li><input type=checkbox>项目基本信息
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
<li><input type=checkbox>项目人员信息
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
<li><input type=checkbox>项目委外信息
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
<li><input type=checkbox>项目成本信息
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
</ul>
</li>
</ul></span>
</td><td>
<ul id="tree2">
<li><input type=checkbox>资源管理
<ul>
<li><input type=checkbox>我的资源</li>
<li><input type=checkbox>设备资源申请</li>
<li><input type=checkbox>设备资源管理
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
<li><input type=checkbox>SIM卡资源申请</li>
<li><input type=checkbox>SIM卡资源管理
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
<li><input type=checkbox>样机资源申请</li>
<li><input type=checkbox>样机资源管理
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
<li><input type=checkbox>实验室申请</li>
<li><input type=checkbox>耗材申请</li>
</ul>
</li>
</ul>
</td><td>
<ul id="tree3">
<li><input type=checkbox>个人事务
<ul>
<li><input type=checkbox>查询人员</li>
<li><input type=checkbox>我的信息</li>
<li><input type=checkbox>人员状态
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
<li><input type=checkbox>权限修改
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
<li><input type=checkbox>角色权限修改
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
<li><input type=checkbox>角色增加</li>
</ul>
</li>
</ul>
</td><td>
<ul id="tree4">
<li><input type=checkbox>工作台
<ul>
<li><input type=checkbox>我的任务</li>
<li><input type=checkbox>任务分配
<ul>
<li><input type=checkbox>增加</li>
<li><input type=checkbox>修改</li>
<li><input type=checkbox>删除</li>
<li><input type=checkbox>查询</li>
</ul>
</li>
<li><input type=checkbox>任务提醒</li>
<li><input type=checkbox>历史任务
</ul>
</li>
</ul>
</td></tr>
<table>
    </div>
    
    </td>
    </tr>
    </table> 
      <script language="javascript">
var arr = tree.getElementsByTagName('input')
for(var i=0; i < arr.length; i++){
var input = arr[i]
if(input.type = 'checkbox'){
input.onclick = ClickInput
}
}
function ClickInput(){
var li = this.parentElement;
var arr = li.getElementsByTagName('input')
for(var i=0; i < arr.length; i++){
var input = arr[i]
if(input.type == 'checkbox'){
input.checked = this.checked
}
}
var li = li.parentElement.parentElement
while(li.tagName.toLowerCase() == 'li'){
var input = li.childNodes[0]
if(input.tagName.toLowerCase() == 'input'){
input.checked = this.checked
}
li = li.parentElement.parentElement
}
}
</script>
<script language="javascript">
var arr = tree1.getElementsByTagName('input')
for(var i=0; i < arr.length; i++){
var input = arr[i]
if(input.type = 'checkbox'){
input.onclick = ClickInput
}
}
function ClickInput(){
var li = this.parentElement;
var arr = li.getElementsByTagName('input')
for(var i=0; i < arr.length; i++){
var input = arr[i]
if(input.type == 'checkbox'){
input.checked = this.checked
}
}
var li = li.parentElement.parentElement
while(li.tagName.toLowerCase() == 'li'){
var input = li.childNodes[0]
if(input.tagName.toLowerCase() == 'input'){
input.checked = this.checked
}
li = li.parentElement.parentElement
}
}
</script>
<script language="javascript">
var arr = tree2.getElementsByTagName('input')
for(var i=0; i < arr.length; i++){
var input = arr[i]
if(input.type = 'checkbox'){
input.onclick = ClickInput
}
}
function ClickInput(){
var li = this.parentElement;
var arr = li.getElementsByTagName('input')
for(var i=0; i < arr.length; i++){
var input = arr[i]
if(input.type == 'checkbox'){
input.checked = this.checked
}
}
var li = li.parentElement.parentElement
while(li.tagName.toLowerCase() == 'li'){
var input = li.childNodes[0]
if(input.tagName.toLowerCase() == 'input'){
input.checked = this.checked
}
li = li.parentElement.parentElement
}
}
</script>
<script language="javascript">
var arr = tree3.getElementsByTagName('input')
for(var i=0; i < arr.length; i++){
var input = arr[i]
if(input.type = 'checkbox'){
input.onclick = ClickInput
}
}
function ClickInput(){
var li = this.parentElement;
var arr = li.getElementsByTagName('input')
for(var i=0; i < arr.length; i++){
var input = arr[i]
if(input.type == 'checkbox'){
input.checked = this.checked
}
}
var li = li.parentElement.parentElement
while(li.tagName.toLowerCase() == 'li'){
var input = li.childNodes[0]
if(input.tagName.toLowerCase() == 'input'){
input.checked = this.checked
}
li = li.parentElement.parentElement
}
}
</script>
<script language="javascript">
var arr = tree4.getElementsByTagName('input')
for(var i=0; i < arr.length; i++){
var input = arr[i]
if(input.type = 'checkbox'){
input.onclick = ClickInput
}
}
function ClickInput(){
var li = this.parentElement;
var arr = li.getElementsByTagName('input')
for(var i=0; i < arr.length; i++){
var input = arr[i]
if(input.type == 'checkbox'){
input.checked = this.checked
}
}
var li = li.parentElement.parentElement
while(li.tagName.toLowerCase() == 'li'){
var input = li.childNodes[0]
if(input.tagName.toLowerCase() == 'input'){
input.checked = this.checked
}
li = li.parentElement.parentElement
}
}
</script>
  </body>
</html>

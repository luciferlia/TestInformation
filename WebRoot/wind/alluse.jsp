<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange.js"></script>

<style>
li{list-style:none;}

#table td{border:1px solid #EDEDED;}
td{font-size:0.9em;}

a{font-size:0.9em;}
a:hover{color:red;}
</style>
<script>
function delet(vall){
layer.confirm('确定删除此模块?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               
                             $.ajax( {

       async : false,

       cache : false,

       type : 'POST',

       url :"Case_deleteCaseType?id="+vall+"&tag=0",// 请求的action路径      href="Case_deleteCaseType?id=${cas.id }&tag=0"

       success : function() { // 请求成功后处理函数。
      
      layer.msg("删除成功");
       window.location.reload();
       },
        error : function(e) {
           layer.msg('删除失败!',{time:1000});
         }
       });
								},function(index){ layer.close(index);
								return false;
								});

}


	
	
</script>

</head>

<body>
	  <jsp:include page="/wind/top.jsp"></jsp:include>
	  <table style="width:80%;margin-left:5%;margin-top:1%;"><tr><td>
			<ul >
			<li style="float:left;"><c:if test="${not empty permissionForm.add }"><a onclick="showDivFun1();" type="button"
				class="btn btn-primary 	glyphicon glyphicon-plus">${permissionForm.add }</a></c:if> </li>
			<span style="float:right;">
			<li style="float:left;"><input type="text" class="form-control" placeholder="搜索" id="search"
				style="width:200px;"></li>
			<!-- <li style="float:left;">
				<button type="submit" class="btn btn-info"  >确定</button>
			</li> -->
			<li  style="float:left;"><c:if test="${not empty permissionForm.expor }">
					<button type="submit" class="btn btn-info" onclick="console('export','')">${permissionForm.expor }</button></c:if>
			</li>
			</span>
			
		</ul></td></tr></table>
           <hr/>			
           <table  id="table" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;" class="table table-condensed table-bordered table-hover table-striped">
				<tr style="background-color:#4682B4;height:40px;">
					<td style="text-align:center;vertical-align:middle;width:50px;"><h4><font color="white" >序号</font></h4></td>
					<td style="text-align:center;vertical-align:middle;width:50px;display:none;"></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">分类</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">创建人</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">维护人</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">创建时间</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">最新维护时间</font></h4></td>
					<c:if test="${not empty permissionForm.edit||not empty permissionForm.delete }">
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">操作</font></h4></td>
					</c:if>
				</tr>
				<tbody id="tb">
				<% int i=1; %>
				<c:forEach items="${caseType}" var="cas">
					<tr style="height:40px;" username="${cas.casetypeName }" >
						<td style="text-align:center;vertical-align:middle;"><font color="black"  size="2"><%=i++ %></font></td>
						<td style="text-align:center;vertical-align:middle;display:none;"><font color="black"  size="2">${cas.id }</font></td>
						<td style="text-align:center;vertical-align:middle;"><font color="black"  size="2"><c:if test="${cas.casetypeName=='回归测试'||cas.casetypeName=='CTS测试' }">${cas.casetypeName }</c:if><c:if test="${cas.casetypeName!='回归测试'&&cas.casetypeName!='CTS测试' }"> <a href="Case_showCasestore?id=${cas.id }">${cas.casetypeName }</a></c:if></font></td>
						<td style="text-align:center;vertical-align:middle;"><font color="black"  size="2">${cas.createName }</font></td>
						<td style="text-align:center;vertical-align:middle;"><font color="black"  size="2">${cas.updateName }</font></td>
						<td style="text-align:center;vertical-align:middle;"><font color="black"  size="2">${cas.createTime }</font></td>
						<td style="text-align:center;vertical-align:middle;"><font color="black"  size="2">${cas.updateTime }</font></td>
						<c:if test="${not empty permissionForm.edit||not empty permissionForm.delete }">
						<td style="text-align:center;vertical-align:middle;"><c:if test="${not empty permissionForm.edit }"><a onclick="updateRow(this);">${permissionForm.edit }</a><c:if test="${not empty permissionForm.edit&&not empty permissionForm.delete }"> | 
						</c:if></c:if><c:if test="${not empty permissionForm.delete }"><a  onclick='delet(${cas.id });' >${permissionForm.delete }</a></c:if>
							</td>
							</c:if>
								
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		 <div id="hid"></div>
		<div style="display:none;" id="update" class="mydiv" >
	   
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
		<form action="Case_updateCaseType" method="post" onSubmit="return check1();">
		<input type="hidden" name="tag" value="0">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;height:30px;">
		<td colspan="2" style="height:30px;">
		<h4 style="margin-left:30%"><font color="white" >修改分类</font></h4></td>
		</tr>		
		<tr style="display:none;">
					<td></td>
					<td><input type="hidden" id="l1" /></td>
				</tr>
				<tr style="display:none;">
					<td></td>
					<td><input type="hidden" id="l2"  name="id"/></td>
				</tr>
				<tr>
					<td ><span style="margin-left:10%;">分类内容：</span></td>
					<td><input type="text" id="l3"  name="content" class="form-control" style="width:150px;"/></td>
				</tr>
				<tr style="display:none;">
					<td><span style="margin-left:10%;">创&nbsp;&nbsp;建&nbsp;&nbsp;人：</span></td>
					<td><input type="text" id="l4"  disabled="true" class="form-control" style="width:150px;"/></td>
				</tr>
				
              <tr style="background-color:#4682B4;height:30px;">
				<td colspan="2" >
				<div style="margin-left:30%;">
			<input type="submit" value="提交" 
				 id="btn_update" class="btn btn-primary" style="border:1px solid black;">
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button" 
				value="取消" onclick="hideAddInput()" class="btn btn-primary" style="border:1px solid black;"></div></td>
				
				</tr>
		
	 </table>
	 </form>
	</div>
	
	<div id="popDiv1"  style="display:none;" class="mydiv">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="closeDivFun1()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
		 <form action="Case_addType" method="post" onSubmit="return check();">
		 <input type="hidden" name="tag" value="0">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;">
					<td colspan="2" style="height:30px;">
						<h4 style="margin-left:30%"><font color="white" >增加分类</font></h4>
				</tr>
		     			
				<tr>
					<td><span style="margin-left:20%;">分类：</span></td>
					<td><input type="text" name="casetype" value=""  class="form-control" style="width:150px;"></td>
				</tr>
				<tr style="background-color:#4682B4;height:30px;">
                 <td colspan="2" >
				<div style="margin-left:30%;">
			      <input type="submit" value="提交" class="btn btn-primary" style="border:1px solid black;">
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button" 
				value="取消" onclick="closeDivFun1()" class="btn btn-primary" style="border:1px solid black;"></div></td>				
				</tr>
			
		
		</table>
		</form>
	</div>
	 <script>
     function searchname(){//搜索对应分类
     var name=$('#search').val();
     if(name=='')
     {
     $('#tb  tr').show();
     }
     else{
     $('#tb tr').each(
     function(){
     var names=$(this).attr('username');
    
     if(names.indexOf(name) != -1 ){
     $(this).show();
     }else{
     $(this).hide();
     }
     }
     );
     }
     }
     $('#search').bind('input propertychange',function(){
     searchname();
     });
     function check(){
     var a=document.getElementsByName('casetype')[0].value;
     if(a==""||a==null)
     {
     layer.msg("分类不能为空");
     return false;
     }else{return true;}
     }
     function check1(){
     var a=document.getElementById('l3').value;
     if(a==""||a==null)
     {
     layer.msg("分类不能为空");
     return false;
     }else{return true;}
     }
     </script>
</body>
</html>

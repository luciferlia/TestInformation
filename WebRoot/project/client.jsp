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
<jsp:include page="/wind/top.jsp"></jsp:include>
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange.js"></script>
  <script src="script/ie.js"></script>
<style>
li{list-style:none; float:left;}

#table td{border:1px solid #EDEDED;}
td{font-size:0.9em;text-align:center;vertical-align:middle;}
a:hover{color:red;}
</style>
<script>
 $("button").click(function(){ $('#btn1').attr("disabled","disabled"); });
 function delet(vall){
layer.confirm('确定删除此测试模板?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               
                             $.ajax( {

       async : false,

       cache : false,

       type : 'POST',

       url :"policy_delPolicyPool?id="+vall,// 请求的action路径      href="policy_delPolicyPool?id=${ppf.id }"

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
		<table style="width:80%;margin-left:5%;"><tr><td>
			<ul>
			<li style="float:left;"><c:if test="${not empty permissionForm.add }"><a onclick="showDivFun1()" type="button"
				class="btn btn-primary 	glyphicon glyphicon-plus">${permissionForm.add }</a></c:if> </li>
			<span style="float:right;">
			<li><input type="text" class="form-control" placeholder="搜索" id="search"
				style="width:200px;"/></li>
			
			
			</span>
		</ul></td></tr></table>
			<hr />
			<table  id="table" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;"class="table table-condensed table-bordered table-hover table-striped">
				<tr style="background-color:#4682B4;height:40px;">
					<td style="width:50px;"><h4><font color="white" >序号</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">模板名称</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">创建人</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">维护人</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">创建时间</font></h4></td>
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">最新维护时间</font></h4></td>
					<c:if test="${not empty permissionForm.edit||not empty permissionForm.delete }">
					<td style="text-align:center;vertical-align:middle;"><h4><font color="white">操作</font></h4></td>
					</c:if>
				</tr>
				<tbody id="tb">
			<c:forEach items="${policypools }" var="ppf">
					<tr style="height:40px;"  username="${ppf.policyName }">
						<td >${ppf.id }</td>
						<td><a href="policy_showPolicy?id=${ppf.id }">${ppf.policyName }</a></td>
						<td>${ppf.createUser.name }</td>
						<td>${ppf.updateUser.name }</td>
						<td>${ppf.createTime }</td>
						<td>${ppf.updateTime }</td>
						<c:if test="${not empty permissionForm.edit||not empty permissionForm.delete }">
						<td style="text-align:center;vertical-align:middle;">
						<c:if test="${not empty permissionForm.edit }"><a onclick="updateRow(this);">${permissionForm.edit }</a>
						<c:if test="${not empty permissionForm.edit&&not empty permissionForm.delete }"> | 
						</c:if></c:if><c:if test="${not empty permissionForm.delete }"><a  onclick='delet(${ppf.id });' >${permissionForm.delete }</a></c:if>
						</td></c:if>
					</tr>
				</c:forEach>
				</tbody>
			</table>
	
		 <div id="hid"></div>
		<div style="display:none;" id="update" class="mydiv" >
	   
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
		<form action="policy_updatePolicyPool" method="post">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;height:30px;">
		<td colspan="2" style="height:30px;">
		<h4 style="margin-left:10%"><font color="black" >修改模板</font></h4></td>
		</tr>		
		
				<tr>
					<td></td>
					<td><input type="hidden" id="l1"  name="id"/></td>
				</tr>
				<tr>
					<td ><span style="margin-left:10%;">模板名称：</span></td>
					<td><input type="text" id="l2"  name="customerName"  class="form-control" style="width:150px;"/></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">负&nbsp;&nbsp;责&nbsp;&nbsp;人：</span></td>
					<td><input type="text" id="l3" class="form-control" disabled="true" style="width:150px;"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="hidden" id="l4" /></td>
				</tr>
              <tr style="background-color:#4682B4;height:30px;">
				<td colspan="2" >
				<div style="margin-left:10%;">
			<input type="submit" value="提交" onclick="updateInfo()" style="border:1px solid black;"
				 id="btn_update" class="btn btn-primary">
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button" style="border:1px solid black;"
				value="取消" onclick="hideAddInput()" class="btn btn-primary"></div></td>
				
				</tr>
		
	 </table>
	 </form>
	</div>
	
	<div id="popDiv1"  style="display:none;" class="mydiv" >
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="closeDivFun1()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
		<form action="policy_addPolicyPool" method="post">	
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;">
					<td colspan="2" style="height:30px;">
						<h4 style="margin-left:10%"><font color="black" >增加模板</font></h4>
				</tr>
		      		
				<tr>
					<td><span style="margin-left:20%;">模板名称：</span></td>
					<td><input type="text" name="customerName" value="" class="form-control" style="width:150px;"></td>
				</tr>
				<tr style="background-color:#4682B4;height:30px;">
                 <td colspan="2" >
				<div style="margin-left:10%;">
			      <input type="submit" value="提交" class="btn btn-primary" style="border:1px solid black;">
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button" 
				value="取消" onclick="closeDivFun1()" class="btn btn-primary" style="border:1px solid black;"></div></td>				
				</tr>
			
		
		</table>
		</form>
	</div>
	<script>
     function searchname(){
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
     </script>
</body>
</html>

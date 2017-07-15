<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
li{list-style:none;}

#table td{border:1px solid #EDEDED;}

a:hover{color:red;}
td{font-size:0.9em;}
</style>
<script>
function delet(id1,id2){
 layer.confirm('确定删除该模块用例?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                 window.location.href='Case_deleteCasestore?id='+id1+'&typeId='+id2+'&tag=1';//href="Case_deleteCasestore?id=${cs.casestoreId }&typeId=${id}&tag=1"
                                },function(index){ layer.close(index);
								return false;
								});

}

</script>
</head>

<body>
	
	
	<table style="width:80%;margin-left:5%;margin-top:1%;"><tr><td>
		<ul>
			<li style="float:left;"><c:if test="${not empty permissionForm.add }"><a onclick="showDivFun1();"
				type="button" class="btn btn-primary 	glyphicon glyphicon-plus"
				target="_blank">${permissionForm.add }</a></c:if></li>
			<span style="float:right;">
				<li style="float:left;"><input type="text"
						class="form-control" placeholder="搜索" style="width:200px;" id="search"></li>
				
			</span>
		</ul></td></tr></table>
		<hr/>
		<table id="table" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;" class="table table-condensed table-bordered table-hover table-striped">
			<tr style="background-color:#4682B4;height:40px;color: white;">
				<td style="text-align:center;vertical-align:middle;width:50px;">序号</td>
				<td style="text-align:center;vertical-align:middle;display:none"></td>
				<td style="text-align:center;vertical-align:middle;">测试内容</td>
				<td style="text-align:center;vertical-align:middle;">创建人</td>
				<td style="text-align:center;vertical-align:middle;">维护人</td>
				<td style="text-align:center;vertical-align:middle;">创建时间</td>
				<td style="text-align:center;vertical-align:middle;">最新维护时间</td>
				<c:if test="${not empty permissionForm.edit||not empty permissionForm.delete }">
				<td style="text-align:center;vertical-align:middle;">操作</td></c:if>
			</tr>
			<tbody id="tb">
			<% int i=1; %>
			<c:forEach items="${CaseStore }" var="cs">
				<tr style="height:40px;" username="${cs.testModule }">
					<td style="text-align:center;vertical-align:middle;"><font
						color="black"><%=i++ %></font></td>
					<td style="text-align:center;vertical-align:middle;display:none"><font color="black">${cs.casestoreId }</font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black"><a href="Case_showcusCaseDetail?id=${cs.casestoreId }&page=1">${cs.testModule }</a></font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.createName }</font></td>
						<td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.updateName }</font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.createTime }</font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.updateTime }</font></td>
					<c:if test="${not empty permissionForm.edit||not empty permissionForm.delete }">
					<td style="text-align:center;vertical-align:middle;"><c:if test="${not empty permissionForm.edit }"><a  onclick="updateRow(this);" >${permissionForm.edit }</a></c:if>
					<c:if test="${not empty permissionForm.edit&&not empty permissionForm.delete }"> | </c:if>
					<c:if test="${not empty permissionForm.delete }"><a  onclick='delet(${cs.casestoreId },${id});'>${permissionForm.delete }</a></c:if>
			   		</td>
			   </c:if>
				</tr>
			</c:forEach>
			</tbody>
		</table>

    <div id="hid"></div>

	<div style="display:none" id="update" class="mydiv">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
		<form action="Case_updateCasestore?typeId=${id }" method="post" onSubmit="return check1();">
		<input type="hidden" name="tag" value="1">
		<table
			style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
			<tr style="background-color:#4682B4;">
				<td colspan="2" style="height:30px;">
					<h4 style="margin-left:30%">
						<font color="white">修改模块</font>
					</h4>
				</td>
			</tr>
			
                <tr style="display:none;">
					<td></td>
					<td><input type="hidden" id="l1" /></td>
				</tr>
				<tr style="display:none;">
					<td></td>
					<td><input type="hidden" id="l2" name="id" /></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">测试内容：</span></td>
					<td><input type="text" id="l3" name="content" class="form-control"  style="width:150px;"/></td>
				</tr>
				<tr style="display:none;">
					<td><span style="margin-left:10%;">创建人：</span></td>
					<td><input type="text" id="l4" disabled="true" class="form-control" style="width:150px;"/></td>
				</tr>
				
				<tr style="background-color:#4682B4;height:30px;">
					<td colspan="2">
						<div style="margin-left:30%">
							<input type="submit" value="提交" style="border:1px solid black;"
								style="display:" id="btn_update" class="btn btn-primary">
							&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="取消" style="border:1px solid black;"
								onclick="hideAddInput()" class="btn btn-primary">
						</div>
					</td>
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
		<form action="Case_addStore" method="post" onSubmit="return check();">
		<input type="hidden" name="tag" value="1">
		<table
			style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">

			<tr style="background-color:#4682B4;">
				<td colspan="2" style="height:30px;">
					<h4 style="margin-left:30%">
						<font color="white">增加分类</font>
					</h4>
			</tr>
			
				<tr>

					<td><span style="margin-left:20%;">测试内容：</span></td>
					<td><input type="text" name="testModule" value="" class="form-control"  style="width:150px;"></td>
				</tr>

				<tr style="background-color:#4682B4;height:30px;">
					<td colspan="2">
						<div style="margin-left:30%">
							<input type="submit" value="提交" class="btn btn-primary" style="border:1px solid black;">
							&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="取消"
								onclick="closeDivFun1()" class="btn btn-primary" style="border:1px solid black;">
						</div>
					</td>
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
     function check(){
     var a=document.getElementsByName('testModule')[0].value;
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

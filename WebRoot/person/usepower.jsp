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
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange1.js"></script>
<script src="script/page.js"></script>
<jsp:include page="/admin/adminTop.jsp"></jsp:include>
<link rel="stylesheet" href="script/layui/css/layui.css">
<script src="script/layui/layui.js"></script>

<style type="text/css">
li{list-style:none;}
#table td{ font-size:0.9em;font-color:black;text-align:center;vertical-align:middle;}
td{font-size:0.9em;font-color:black;}
</style><%-- 
<script>
layui.use('layer', function(){
  var layer = layui.layer;
  
  layer.msg('hello');
}); 
</script> --%>
<script type="text/javascript">



	function checkDelete(id){
	/* 	if(config=='role'){
			return confirm("删除该角色将会同时删除其所有关联数据。确认删除该角色吗？");
		}else if(config=='menu'){
			return confirm("确认删除该菜单吗？");
		}else if(config=='function'){
			return confirm("确认删除该权限吗？");		
		} */
		 layer.confirm('删除该角色将会同时删除其所有关联数据。确认删除该角色吗?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               window.location.href='role_deleteRole?id='+id;//href="role_deleteRole?id=${role.roleId }"
                                },function(index){ layer.close(index);
								return false;
								});
		
		
		
	}
	function checkDelete1(id){
		 layer.confirm('确认删除该菜单吗?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               window.location.href='function_deleteFunction?id='+id;//href="function_deleteFunction?id=${f.functionId }" 
                                },function(index){ layer.close(index);
								return false;
								});
		
		
		
	}
	function checkDelete2(id){
		 layer.confirm('确认删除该菜单吗?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               window.location.href='menu_deleteMenu?id='+id; 
                                },function(index){ layer.close(index);
								return false;
								});
		
	} 
</script>
</head>

<body >

	<table style="width:95%;margin-left:1%;min-width:600px;"><tr><td>
	<ul >
			<li style="float:left;"><a onclick="showDivFun()"
				type="button" class="btn btn-primary">新增角色</a></li>
				<span style="float:right;">
				<li style="float:left;"><a onclick="showDivFun1()" type="button" class="btn btn-primary">新增界面权限</a></li>
				<li style="float:left;"><a onclick="showDivFun2()" class="btn btn-primary">新增权限</a></li>				
			</span>
		</ul>	
	</td></tr></table>
		<div class="layui-tab layui-tab-card" style="width:95%;height:80%;min-width:600px;min-height:500px;margin-left:1%;">
     <ul class="layui-tab-title">
      <li class="layui-this">角色管理</li>
      <li>页面权限</li>
     <li>菜单权限</li>
    
  </ul>
  <div class="layui-tab-content" style="height: 100px;">
    <div class="layui-tab-item layui-show">
    <table id="table1" style="margin-left:1%;width:95%;min-width:700px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all" class="layui-table">
				<tr style="background-color:#4682B4;height:40px;">
					
					<td style="width:50px;"><h4><font color="white">ID</font></h4></td>
					<td><h4><font color="white">角色名</font></h4></td>
					<td><h4><font color="white">创建时间</font></h4></td>
					<td><h4><font color="white">备注</font></h4></td>
					<td><h4><font color="white">操作</font></h4></td>
				</tr>
				<c:forEach items="${roles }" var="role">
				<tr style="height:30px;">
					<td ><font color="black">${role.roleId }</font></td>
					<td><font color="black">${role.roleName }</font></td>
					<td><font color="black">${role.buildTime }</font></td>
					<td><font color="black">${role.description }</font></td>
					<td><a onclick="updateRow1(this);">编辑</a>|<a  onclick="checkDelete(${role.roleId })" >删除</a>|<a href="role_rolePowerPre?id=${role.roleId }">授权</a></td>
				</tr>
				</c:forEach>
			</table>
    </div>
    <div class="layui-tab-item">
    <table id="table" style="margin-left:1%;width:95%;min-width:700px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all" class="layui-table">
				<tr style="background-color:#4682B4;height:40px;" id="show_tab_one">
				<td style="width:50px;"><h4><font color="white">ID</font></h4></td>
					<td><h4><font color="white">权限名称</font></h4></td>
					<td><h4><font color="white">URL</font></h4></td>
					<td><h4><font color="white">所在页面</font></h4></td>
					<td><h4><font color="white">备注名称</font></h4></td>
					<td><h4><font color="white">操作</font></h4></td>
				</tr>
				 <tbody id="show_tab_tr">  
				<c:forEach items="${functions }" var="f">
			
						<tr style="height:30px;">
					<td><h4><font color="black">${f.functionId }</font></h4></td>
					<td><font color="black">${f.name }</font></td>
					<td ><font color="black">${f.url }</font></td>
					<td><font color="black">${f.jsp }</font></td>
					<td ><font color="black">${f.description }</font></td>
					<td><a onclick="updateRow(this);">编辑</a>|<a  onclick="checkDelete1(${f.functionId })" >删除</a></td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			 <table style="margin-left:1%;width:95%;min-width:700px;font-size:0.9em;"><tr><td>
                    共&nbsp;&nbsp;<span id="page" ></span>&nbsp;&nbsp;页&nbsp;&nbsp;|&nbsp;&nbsp;第&nbsp;&nbsp;<span id="curPage1"></span>&nbsp;&nbsp;页
                     <span style="float:right;">
                    <input id="lastpage" type="button" value="上一页" >
   <input id="nextpage" type="button" value="下一页">
    <!-- <input id="curPage" type="text" size="5"> --><select id="curPage"></select>
    <input id="npage" type="button" value="确定" class="btn btn-primary btn-xs" style="border:1px solid black;"></span>
   
    </td></tr></table>
    </div>
    <div class="layui-tab-item">   
    <table id="table2" style="margin-left:1%;width:95%;min-width:700px;TABLE-LAYOUT:fixed;WORD-BREAK:break-all" class="layui-table">
				<tr style="background-color:#4682B4;height:40px;" id="show_tab_one2">
					<td style="width:50px;"><h4><font color="black">ID</font></h4></td>
					<td><h4><font color="white">菜单名</font></h4></td>
					<td><h4><font color="white">URL</font></h4></td>
					<td><h4><font color="white">操作</font></h4></td>
				</tr>
				 <tbody  id="show_tab_tr2"> 
			<c:forEach items="${menus }" var="menu">
						<tr style="height:30px;">
					
					<td><font color="black">${menu.id }</font></td>
					<td ><font color="black">${menu.name }</font></td>
					<td><font color="black">${menu.url }</font></td>
					<td><a onclick="updateRow2(this);">编辑</a>|<a  onclick="checkDelete2(${menu.id })" >删除</a></td>
				</tr>
			</c:forEach>
			</tbody>
			</table>
			 <table style="margin-left:1%;width:95%;min-width:700px;font-size:0.9em;"><tr><td>
                    共&nbsp;&nbsp;<span id="page2" ></span>&nbsp;&nbsp;页&nbsp;&nbsp;|&nbsp;&nbsp;第&nbsp;&nbsp;<span id="curPage12"></span>&nbsp;&nbsp;页
                     <span style="float:right;">
                    <input id="lastpage2" type="button" value="上一页" >
   <input id="nextpage2" type="button" value="下一页">
    <!-- <input id="curPage" type="text" size="5"> --><select id="curPage2"></select>
    <input id="npage2" type="button" value="确定" class="btn btn-primary btn-xs" style="border:1px solid black;"></span>
   
    </td></tr></table>
			</div>
   </div>
  </div>
  <div id="hid"></div>
  	<!--   *************************************************************   增加        *************************************************************       -->
	<div id="popDiv" class="mydiv" style="display:none;">
		<div align="right" style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;" onclick="closeDivFun()">
		<span><font color="black">X</font></span>
		</div>
		<form action="role_addRole" method="post">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;">
					<td colspan="2" style="height:30px;">
						<h4 style="margin-left:30%"><font color="black" >增加角色</font></h4>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">角色名称：</font></td>
					<td><input type="text" name="role.roleName" value="" class="form-control" style="width:150px;"></td>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">备注：</font></td>
					<td><input type="text" name="role.description" value="" class="form-control" style="width:150px;"></td>
				</tr>
				<tr style="background-color:#4682B4;">
                 <td colspan="2" style="height:40px;">
				<div style="margin-left:30%;height:40px;">
			      <input type="submit" value="提交" style="border:1px solid black;" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;	
			      <input type="reset" value="取消" onclick="closeDivFun()" style="border:1px solid black;" class="btn btn-primary"></div></td>				
				</tr>
			</table>
			</form>
		</div>
		<div id="popDiv1" class="mydiv" style="display:none;">
		<div align="right" style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;" onclick="closeDivFun1()">
		<span><font color="black">X</font></span>
		</div>
		<form action="function_addFunction" method="post" style="height:100%;width:100%;">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;">
					<td colspan="2" style="height:30px;">
						<h4 style="margin-left:30%"><font color="black" >增加界面权限</font></h4>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">权限名称：</font></td>
					<td><input type="text" name="function.name" value="" class="form-control" style="width:150px;"></td>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">URL：</font></td>
					<td><input type="text" name="function.url" value="" class="form-control" style="width:150px;"></td>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">所在页面：</font></td>
					<td><input type="text" name="function.jsp" value="" class="form-control" style="width:150px;"></td>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">备注名称：</font></td>
					<td><input type="text" name="function.description" value="" class="form-control" style="width:150px;"></td>
				</tr>
				<tr style="background-color:#4682B4;">
                 <td colspan="2" style="height:40px;">
				<div style="margin-left:30%;height:40px;">
			      <input type="submit" value="提交"  style="border:1px solid black;" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;	
			      <input type="reset" value="取消" onclick="closeDivFun1()" style="border:1px solid black;" class="btn btn-primary"></div></td>				
				</tr>
			</table>
			</form>
		</div>

       <div id="popDiv2" class="mydiv" style="display:none;">
		<div align="right" style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;" onclick="closeDivFun2()">
		<span><font color="black">X</font></span>
		</div>
		
		<form action="menu_addMenu" method="post">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;">
					<td colspan="2" style="height:30px;">
						<h4 style="margin-left:30%"><font color="black" >增加菜单权限</font></h4>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">菜单名称：</font></td>
					<td><input type="text" name="menu.name" value="" class="form-control" style="width:150px;"></td>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">URL：</font></td>
					<td><input type="text" name="menu.url" value="" class="form-control" style="width:150px;"></td>
				</tr>
				<tr>
					<td><font style="margin-left:20%;">一级菜单：</font></td>
					<td><select name="moduleId" class="form-control" style="width:150px;"><c:forEach items="${modules }" var="module"><option value="${module. moduleId}">${module.moduleName }</option></c:forEach> </select> </td>
				</tr>
				<tr style="background-color:#4682B4;">
                 <td colspan="2" style="height:40px;">
				<div style="margin-left:30%;height:40px;">
			      <input type="submit" value="提交"  style="border:1px solid black;" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;	
			      <input type="reset" value="取消" onclick="closeDivFun2()"style="border:1px solid black;" class="btn btn-primary" ></div></td>				
				</tr>
			</table>
		</form>
		</div>
		<!--   *************************************************************    编辑        *************************************************************       -->
		<div style="display:none;" id="update1" class="mydiv" >
	   
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput1()">
			<span><font color="black">X</font></span>
		</div>
		<form action="role_updateRole" method="post">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;height:30px;">
		<td colspan="2" style="height:30px;">
		<h4 style="margin-left:30%"><font color="black" >编辑角色</font></h4></td>
		</tr>		
		
				<tr style="dispaly:none;">
					<td></td>
					<td><input type="hidden" id="p1"  name="role.roleId" class="form-control" style="width:150px;"/></td>
				</tr>
				<tr>
					<td ><span style="margin-left:10%;">角色名：</span></td>
					<td><input type="text" id="p2"  name="role.roleName" class="form-control" style="width:150px;"/></td>
				</tr>
			<tr style="display:none;">
					<td ><span style="margin-left:10%;"></span></td>
					<td><input type="hidden" id="p3"  name="" class="form-control" style="width:150px;"/></td>
				</tr>
			<tr>
					<td ><span style="margin-left:10%;">备注：</span></td>
					<td><input type="text" id="p4"  name="role.description" class="form-control" style="width:150px;"/></td>
				</tr>
			
              <tr style="background-color:#4682B4;height:40px;">
				<td colspan="2" >
				<div style="margin-left:30%;">
			<input type="submit" value="提交" onclick="updateInfo1()" style="border:1px solid black;" class="btn btn-primary"
				 id="btn_update1" >
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button" style="border:1px solid black;"
				value="取消" onclick="hideAddInput1()" class="btn btn-primary"></div></td>
				
				</tr>
		
	 </table>
	 </form>
	</div>
	<div style="display:none;" id="update" class="mydiv" >
	   
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span><font color="black">X</font></span>
		</div>
		<form action="function_updateFunction" method="post">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;height:30px;">
		<td colspan="2" style="height:30px;">
		<h4 style="margin-left:30%"><font color="black" >编辑页面权限</font></h4></td>
		</tr>		
		        <tr style="dispaly:none;">
					<td></td>
					<td><input type="hidden" id="l1"  name="id" /></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">权限名称：</span></td>
					<td><input type="text" id="l2"  name="function.name" class="form-control" style="width:150px;"/></td>
				</tr>
				<tr style="dispaly:none;">
					<td ></td>
					<td><input type="hidden" id="l3"  name="function.url"/></td>
				</tr>
				<tr style="dispaly:none;">
					<td></td>
					<td><input type="hidden" id="l4" name="function.jsp" /></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">备注名称：</span></td>
					<td><input type="text" id="l5" name="function.description" class="form-control" style="width:150px;"/></td>
				</tr>
				<tr style="dispaly:none;">
					<td><span style="margin-left:10%;"></span></td>
					<td><input type="hidden" id="l6" /></td>
				</tr>
              <tr style="background-color:#4682B4;height:40px;">
				<td colspan="2" >
				<div style="margin-left:30%;">
			<input type="submit" value="提交" onclick="updateInfo()" style="border:1px solid black;"
				 id="btn_update" class="btn btn-primary">
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button" style="border:1px solid black;"
				value="取消" onclick="hideAddInput()" class="btn btn-primary"></div></td>
				
				</tr>
		
	 </table>
	 </form>
	</div>
	<div style="display:none;" id="update2" class="mydiv" >
	   
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput2()">
			<span><font color="black">X</font></span>
		</div>
		<form action="menu_editMenu" method="post">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
		<tr style="background-color:#4682B4;height:30px;">
		<td colspan="2" style="height:30px;">
		<h4 style="margin-left:30%"><font color="black" >编辑菜单</font></h4></td>
		</tr>		
		
				<tr style="dispaly:none;">
					<td></td>
					<td><input type="hidden" id="r1"  name="menu.id"/></td>
				</tr>
				<tr>
					<td ><span style="margin-left:10%;">菜单名：</span></td>
					<td><input type="text" id="r2"  name="menu.name" class="form-control" style="width:150px;"/></td>
				</tr>
				<tr style="dispaly:none;">
					<td></td>
					<td><input type="hidden" id="r3" /></td>
				</tr>
				
              <tr style="background-color:#4682B4;height:40px;">
				<td colspan="2" >
				<div style="margin-left:30%;">
			<input type="submit" value="提交" onclick="updateInfo2()"
				 id="btn_update2" class="btn btn-primary" style="border:1px solid black;">
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button" style="border:1px solid black;"
				value="取消" onclick="hideAddInput2()" class="btn btn-primary"></div></td>
				
				</tr>
		
	 </table>
	 </form>
	</div>
		<script>
		layui.use('element', function(){
       var element = layui.element();
       });
		</script>
</body>
</html>

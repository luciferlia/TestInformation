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
<jsp:include page="/admin/adminTop.jsp"></jsp:include>
<style type="text/css">
li{list-style:none;}
#table td{border:1px solid #EDEDED; font-size:0.9em;font-color:black;text-align:center;vertical-align:middle;}
td{font-size:0.9em;font-color:black;}
</style>
 <script type="text/javascript">
 layui.use('layer', function() {
		var layer = layui.layer;

	});
 
 function console(consoleTag, caseId, stateTag) {

  var checkedSubject = $('#table input[name=checkedId]:checkbox:checked');
   var checkedSubject1 = $('#table1 input[name=checkedId1]:checkbox:checked');
      var checkedIds="";
      var checkedIds1="";
        //循环获取选中的复选框的value，这个value是数据表中每条记录的主键${cd.caseId }，传给后台，后台就能根据主键查找到数据表的相应记录

        checkedSubject.each(function() {
           checkedIds=checkedIds+","+$(this).val();
         });
         checkedSubject1.each(function() {
           checkedIds1=checkedIds1+","+$(this).val();
         });
       /*   var f=false;
         if('delete'==consoleTag){
         	if( checkedIds==""|| checkedIds==null|| checkedIds==0){
         		layer.msg("请勾选要删除的人员");
         
         	}else{
         		f= confirm("确定删除这些人员吗？");
         	}
         
         }else  */if('power'==consoleTag){
         	if( checkedIds==""|| checkedIds==null|| checkedIds==0){
     			layer.msg("请勾选要授权的人员");
     		}else if(checkedIds1==""|| checkedIds1==null|| checkedIds1==0){
     			layer.msg("请勾选权限名");
     		}else{
      			
      			layer.confirm('确定对这些人员进行授权吗?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                //table表中选中的复选框赋值给checkedSubject
      
    

        $('#checkedIds').val(checkedIds);
        $('#checkedIds1').val(checkedIds1);
        //删除
        if("power" == consoleTag) {
            $('#formid').prop("action", "user_addPower");
        }else if('delete'==consoleTag){
        	$('#formid').prop("action", "user_deleteUser");
        }

        $('#formid').submit();
                                
                                },function(index){ layer.close(index);
								return false;
								});
         	}
         }
     
	
}
        
                
</script>
<script>
	$(function() {
		var all_checked = false;
		$(":checkbox").click(function() {
			var table = $(this).parents("table");
			if ($(this).attr("id") === "all") {
				table.find(":checkbox").prop("checked", !all_checked);
				all_checked = !all_checked;
			} else {
				table.find(":checkbox[id!=all]").each(function(i) {
					if (!$(this).is(":checked")) {
						table.find("#all").prop("checked", false);
						all_checked = false;
						return false;
					}
					$("#all").prop("checked", true);
					all_checked = true;
				});
			}
		});
	});
</script>
<script>
function shield(){
	    var s = document.getElementById("hid");
	    s.style.display = "block";
	    
	    var l = document.getElementById("log_window");
	    l.style.display = "block";
	}

	function cancel_shield(){
	    var s = document.getElementById("hid");
	    s.style.display = "none";
	    
	    var l = document.getElementById("log_window");
	    l.style.display = "none";
	}
</script>
</head>

<body>
	
	 
			<h3 style="margin-left:5%;" class="glyphicon glyphicon-tasks">人员角色赋予</h3>
			<table style="margin-top:1%;margin-left:5%;width:80%;"><tr><td>
			<ul >
			<li style="float:left;"><a onclick="showDivFun()"
			type="button" class="btn btn-primary">授权</a></li>
			
			
				<form action="user_query?page=1" method="post" style="float:right;">
				<li style="float:left;"><select name="type" style="height:32px;"><option value="num">工号</option><option value="name">姓名</option><option value="position">职位</option></select></li>
				<li style="float:left;"><input type="text"
						class="form-control" placeholder="搜索" style="width:200px;" name="content"></li>
				<li style="float:left;">
						<button type="submit" class="btn btn-primary">确定</button>
				</li>
				</form>
				
			
		</ul></td></tr></table>
	
		    <hr/>
			<table id="table" style="margin-left:5%;width:80%;TABLE-LAYOUT:fixed;WORD-BREAK:break-all">
				<tr style="background-color:#4682B4;height:40px;">
					<td style="width:40px;"><h4><font color="black"><input type="checkbox"  name="all" id="all" /></font></h4></td>
					<td ><h4><font color="white">工号</font></h4></td>
					<td><h4><font color="white">姓名</font></h4></td>
					<td><h4><font color="white">登录时间</font></h4></td>
					<td><h4><font color="white">职位</font></h4></td>
					<td><h4><font color="white">角色</font></h4></td>
					<td><h4><font color="white">操作</font></h4></td>
				</tr>
			<s:iterator value="pageBean.list">
				<tr style="height:30px;">
					<td><input type="checkbox" name="checkedId" id="checkedId" value="<s:property value="user.userId" />"/><font style="font-size:0;overflow:hidden;"><s:property value="user.userId" /></font></td>
					<td><font color="black"><s:property value="user.num"/> </font></td>
					<td><font color="black"><s:property value="user.name"/> </font></td>
					<td><font color="black"><s:property value="user.loginTime"/></font></td>
					<td><font color="black"></font><s:property value="user.position"/></td>
					<td style="text-align:center;vertical-align:middle;"><font color="black"><s:property value="roleName"/></font></td>
					<td><a  type="button" onclick="updateRow(this);"
					class="btn btn-primary">编辑</a></td>
				</tr>
				
			</s:iterator>	 
			<table>
			<table style="width:80%;margin-left:5%;">
				<tr>
					<td ><span style="float:left;">共<span><s:property
								value="pageBean.totalPage" /></span>页|第<span><s:property
								value="pageBean.currentPage" /> </span>页</span>
					</td>
					<td><span style="float:right;"><form action="user_query" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="user_query?page=1" class="right-font08">首页</a> | <a
									href="user_query?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="user_query?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="user_query?page=<s:property value="%{pageBean.totalPage}"/>"
									class="right-font08">末页</a>
							</s:if>
							<s:else>下一页 | 末页</s:else>
							] 转至：<select name="page" style="width:50px;">
								<s:iterator value="pageBean.count">

									<option value="<s:property value="count"/>"><s:property
											value="count" />
									</option>
								</s:iterator>
							</select>
							<%-- <input type="text" style="width:50px;" name="page" /> --%>
							<input type="submit" value="确定" class="btn btn-primary btn-xs" style="border:1px solid black;">
						</form></span></td>
				</tr>
			</table>

    <div id="hid"></div>
		<div id="popDiv"  style="display:none;" class="popDiv3">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="closeDivFun()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
		<table id="table1" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;height:100%;width:100%;"
			class="table table-condensed table-bordered table-hover table-striped">
			<tr>
					<td style="background-color:#4682B4;height:40px;" colspan="2"><h4 style="margin-left:35%;"><font color="black">角色赋予</font></h4></td>
				</tr>
			<tr>
				<td colspan="2">
				
				<c:forEach items="${roles }" var="role">
				<input type="checkbox" value="${role.roleId }" id="checkedId1" name="checkedId1">${role.roleName }<br/>
				</c:forEach>
			
				</td>	</tr>
				<tr>
                 <td colspan="2" style="background-color:#4682B4;height:40px;" >
				<div style="margin-left:35%">
			      <input type="submit" class="btn btn-primary" onclick="console('power','')" style="border:1px solid black;" value="提交">
				&nbsp;&nbsp;&nbsp;&nbsp;	<input type="button"  style="border:1px solid black;"
				value="取消" onclick="closeDivFun()" class="btn btn-primary"></div></td>				
				</tr>
		
	 </table>
	</div>
	<div class="popDiv3" id="update" style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
		<form action="user_updateUserMsg" method="post" onSubmit="return checkpower();" style="width:100%;height:100%;">
		<table style="width:100%;height:100%;">
		<tr style="background-color:#4682B4;height:40px;">
		<td colspan="2" >
		<h4 style="margin-left:30%"><font color="black" >角色修改</font></h4></td>
		</tr>	
		<tr style="display:none;">
					<td ><span style="margin-left:30%;"></span></td>
					<td><input type="hidden" name="user.userId" value="" id="l1"></td>
				</tr>
				<tr >

					<td ><span style="margin-left:30%;">工号：</span></td>
					<td><input type="text" name="user.num" value="" id="l2" class="form-control" style="width:150px;"></td>
				</tr>
				<tr >

					<td ><span style="margin-left:30%;">姓名：</span></td>
					<td><input type="text" name="user.name" value="" id="l3" class="form-control" style="width:150px;"></td>
				</tr>
				<tr >

					<td ><span style="margin-left:30%;">登录时间：</span></td>
					<td><input type="text" name="" value="" id="l4" disabled="disabled" class="form-control" style="width:150px;"></td>
				</tr>
				<tr >

					<td ><span style="margin-left:30%;">职位：</span></td>
					<td><input type="text" name="user.position" value="" id="l5" class="form-control" style="width:150px;"></td>
				</tr>
				<tr >

					<td ><span style="margin-left:30%;">权限：</span></td>
					<td><select name="roleId" style="width:100px;" id="l6" class="form-control" style="width:150px;">
						<c:forEach items="${roles }" var="role">
		     			<option value="${role.roleId }" selected="selected">${role.roleName }</option>
		     			</c:forEach>
     				</select></td>
				</tr>
                <!-- 点击确定后，所借时间和所借人根据ID号直接生成 -->
				<tr style="background-color:#4682B4;height:40px;">
					<td align="center" colspan="2"><input type="submit" value="提交" onclick="updateInfo()" style="border:1px solid black;"
				 id="btn_update" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-primary" onclick="hideAddInput()"
						style="border:1px solid black;"	type="button">取消</button></td>
				</tr>
			
		
		</table>
		</form>
	</div>
	<script>
	function checkpower(){
	var a=document.getElementById("l6").value;
	if(a==""||a==null)
     {
     layer.msg("权限不能为空");
     return false;
     }	
     else{
     
     return true;}
	}
	</script>
    		<%-- 隐藏域表单 --%>
   <form action="" method="post" id="formid">
        <input type="hidden" name="checkedIds" id="checkedIds" />
        <input type="hidden" name="checkedIds1" id="checkedIds1" />
    </form>
</body>
</html>

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

<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange1.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
  <script src="script/ie.js"></script>
<!--[if lt IE 7 ]>alert("当前浏览器版本过低，请切换成极速模式或升级版本");<![endif]-->
<style type="text/css">
#table td {
	border: 1px solid #EDEDED;
	font-color: black;
	text-align: center;
	vertical-align: middle;
	font-size: 0.9em;
}

#sp {
	float: right;
}
.mydiv  td{font-size:0.9em;}
.mydiv input{width:150px;}


a:hover {
	color: red;
}

li {
	list-style: none;
}
td{font-size:0.9em;}

</style>
<script>
	layui.use('layer', function() {
		var layer = layui.layer;

	});
</script>
<script type="text/javascript">
	function validation(parm) {
		var bflag = true;
		$.ajax({
			type : "POST",
			url : "validation?id=" + parm, //这里你根据后台地址改,id为项目编号
			success : function(data) {
				//console.log(data)
				//alert(data);
				if (data == 'success') {
					bflag = true;
					window.location.href = "Plan_showPlan?id=" + parm;
				} else {
					layer.msg('该项目没有制定计划，您没有制定计划的权限!', {
						time : 3000
					});
					bflag = false;

				}
			},
			error : function(e) {
				layer.msg('验证权限失败!', {
					time : 1000
				});
			}
		});

		return bflag;

	}

	function exporPlan(projectId) {
		$.ajax({
			type : 'post',
			url : 'exportPlan?projectId=' + projectId,
			success : function(data) {
				//alert(data);
				if (data == 'success') {
					/* layer.msg('导出项目计划成功', {
						time : 2000
					}); */
					layer.confirm('确定导出策略模板?', {icon: 3, title:'提示'}, function(index){
 					layer.close(index);
					window.location.href = "Plan_downloadPlan";
					},function(index){ layer.close(index);});
				} else {
					layer.msg('导出项目计划失败,该项目中没有计划', {
						time : 2000
					});
				}
			}
		});
	}

	function console(consoleTag, id, stateTag) {
		var checkedSubject = $('#table input[name=checkedId]:checkbox:checked');
		var checkedIds = "";
		//循环获取选中的复选框的value，这个value是数据表中每条记录的主键，传给后台，后台就能根据主键查找到数据表的相应记录
		//将其value用逗号隔开拼接成一个字符串
		checkedSubject.each(function() {
			checkedIds = checkedIds + "," + $(this).val();
		});
		if (checkedIds == "" || checkedIds == null || checkedIds == 0) {
			layer.msg("请勾选要操作的项目");
		} else {
			var f = false;
			if ("delete" == consoleTag) {
					 
			}
			  layer.confirm('确定删除选中项目？', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                //table表中选中的复选框赋值给checkedSubject
				//上面的字符串赋值给隐藏域表单
				$('#checkedIds').val(checkedIds);
				//删除
				if ("delete" == consoleTag) {
					$('#formid').prop("action", "Project_deleteSelectedProject");
				}
				//提交隐藏域表单，后台才能获取隐藏域表单的值
				$('#formid').submit();
			},function(index){ layer.close(index);
								return false;
								});
			
		}
	}
	function sounts(obj) {
		obj.value = obj.value.replace(/\D/g, '');
	}
</script>
<script>
	function show(obj) {
		//alert("sdf");
		var a = document.getElementById("importDiv");
		var b = document.getElementById("hid");
		a.style.display = "block";
		b.style.display = "block";
		/* 	var showname=document.getElementById('showname');
         showname.innerHTML='请选择文件'; */
      //var ids = $(obj).parentNode.children('td').eq(1)[0];
     var tr = obj.parentNode.parentNode;
      var ids=$(tr).children('td').eq(0)[0].children[1].innerText;
      var jj=document.getElementById('hhh');
      jj.value=ids;
	}
	function hidd(){
		var a = document.getElementById("importDiv");
		var b = document.getElementById("hid");
		a.style.display = "none";
		b.style.display = "none";
	
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
	
	$(document).ready(function() {
	 var row=document.getElementById('table').rows.length;
 
				for(var i=1;i<row;i++){	
				 $("#table tr:eq('"+i+"') td:eq(6)").css('font-weight','bold');		
	        if($("#table tr:eq('"+i+"') td:eq(6)").text()=='已完成'){
	        
			$("#table tr:eq('"+i+"')").css('background-color','#A9A9A9');
			 $("#table tr:eq('"+i+"') td:eq(7) a:eq(0)").removeAttr('onclick');
          
             $("#table tr:eq('"+i+"') td:eq(7) a:eq(2)").removeAttr('onclick');
 
			}
			if($("#table tr:eq('"+i+"') td:eq(6)").text()=='新建立'){
			
			$("#table tr:eq('"+i+"') td:eq(6)").css('color','#FF0000');
           
			}
			if($("#table tr:eq('"+i+"') td:eq(6)").text()=='进行中'){
			$("#table tr:eq('"+i+"') td:eq(6)").css('color','#228B22');		
			}
			}
	});
	
</script>
</head>
<body>
<table style="width:90%;margin-left:5%;"><tr><td>
	<ul>
		<li style="float:left;"><c:if test="${not empty pf.add}">
				<a onclick="showDivFun()" type="button" 
					class="btn btn-primary	glyphicon glyphicon-plus">${pf.add }</a>
			</c:if></li>
		<li style="float:left;"><c:if test="${not empty pf.delete}">
				<button onclick="console('delete','')"  class="btn btn-danger glyphicon glyphicon-trash">${pf.delete }</button>

			</c:if></li>
		<span style="float:right;">
		<form action="Project_Search" method="post">
				<li style="float:left;"><select name="type" style="height:32px;"><option value="project_Name">项目名称</option><option value="customer">客户</option></select></li>
				<li style="float:left;"><input type="text"
						class="form-control" placeholder="搜索" style="width:200px;" name="content"></li>
				<li style="float:left;">
						<button type="submit" class="btn btn-primary">确定</button>
				</li>
				</form>
			<!-- <li style="float:left;"><input type="text"
					class="form-control" placeholder="搜索" style="width:200px;"></li>
			<li style="float:left;">
					<button type="submit" class="btn btn-primary">确定</button>
			</li> -->
		</span>
	</ul></td></tr></table>
	<hr />
	<c:if test="${not empty pageBean.list }">
		<table id="table"
			style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:90%;margin-left:5%;"
			class="table table-condensed table-bordered table-hover table-striped">
			<tr style="background-color:#4682B4;height:40px;color: white;">
				<td style="text-align:center;vertical-align:middle;width:40px;"><input
					type="checkbox" name="all" id="all" /></td>
				<td><h4>项目名称</h4></td>
				<td><h4>客户名称</h4></td>
				<td><h4>项目等级</h4></td>
				<td><h4>创建人</h4></td>
				<td><h4>测试阶段</h4></td>
				<td><h4>项目状态</h4></td>
				<td><h4>操作</h4></td>
			</tr>
			<s:iterator value="pageBean.list">
				<tr style="height:40px;">
				   <td style="text-align:center;vertical-align:middle;"><input type="checkbox" name="checkedId" id="checkedId" class=" id-checkbox" value="<s:property value="id"/>"/><span style="font-size:0;overflow:hidden;"><s:property value="id"/></span></td>
					<td><a onclick="validation(<s:property value='id'/>);"><s:property
								value="projectName" /></a></td>
					<td><s:property value="customer" /></td>
					<td><s:property value="level" /></td>
					<td><s:property value="vpmName" /></td>
					<td><s:property value="testPhase"/></td>
					<td><s:property value="state"/></td>
					<td><c:if test="${not empty pf.edit }">
							<a onclick="updateRow(this);" class="upd">${pf.edit }</a> | </c:if><a class="pla"
						onclick="validation(<s:property value='id'/>);" disabled="disabled">计划</a> |  <a class="palex"
						onclick="show(this);">导入计划</a> | <a
						onclick="exporPlan(<s:property value='id'/>);" class="palou">导出计划</a></td>

				</tr>
			</s:iterator>
			
		</table>
		<table style="width:90%;margin-left:5%;">
			<tr>
				<td><span style="float:left;">共<span><s:property
								value="pageBean.totalPage" /></span>页|第<span><s:property
								value="pageBean.currentPage" /> </span>页
				</span></td>
				<td><span style="float:right;"><form
							action="Project_showProject" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="Project_showProject?page=1" class="right-font08">首页</a> | <a
									href="Project_showProject?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="Project_showProject?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="Project_showProject?page=<s:property value="%{pageBean.totalPage}"/> "
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
							&nbsp;&nbsp;<input type="submit" class="btn btn-primary btn-xs"
								style="border:1px solid black;font-size:0.9em"
								value="确定" />
						</form></span></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${empty pageBean.list}">
		<c:if test="${message=='' }">
		<center style="color:red;font-size:10px;">没有项目信息</center>
		</c:if>
		<c:if test="${message!='' }">
		<center style="color:red;font-size:10px;">${message }</center></c:if>
	</c:if>
	<%-- 隐藏域表单 --%>
	<form action="" method="post" id="formid">
		<input type="hidden" name="id" id="id" /> <input type="hidden"
			name="checkedIds" id="checkedIds" />
	</form>
	<div id="hid"></div>
	<!--##############################################编辑项目##############################################  -->
	<div style="display:none" id="update" class="mydiv">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hideAddInput()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
		<form action="Project_updateProject" method="post" onSubmit="return check();">
			<table
				style="height:250px;width:100%;margin-top:0px; margin-bottom:0px;">
				<tr style="background-color:#4682B4;">
					<td colspan="2" style="height:40px;">
						<h3 style="margin-left:35%">
							<font color="white">编辑项目</font>
						</h3>
					</td>
				</tr>
					<tr style="display:none">
					<td><input type="hidden" ></td>
				</tr>
				<tr style="display:none">
					<td><input type="hidden" name="project.id" id="l1"></td>
				</tr>
				<tr>
					<td><font style="margin-left:10%;">项目名称：</font></td>
					<td><input type="text" name="project.projectName" id="l2" class="form-control"></td>
				</tr>
				<tr>
					<td><font style="margin-left:10%;">客户名称：</font></td>
					<td><!-- <input type="text" name="project.customer" id="l3" class="form-control"> -->
					<div style="width:150px;height:35px;position:relative">
					<select  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" class="form-control" onChange="set_modaul(this);">
					<option value="请选择客户">请选择客户</option>
					<option value="魅族">魅族</option>
					<option value="华硕">华硕</option>
					<option value="联想">联想</option>
					<option value="阿尔卡特">阿尔卡特</option>
					<option value="BLU">BLU</option>
					<option value="富士康">富士康</option>
					</select>
					<input type="text" name="project.customer" id="l3" class="form-control" style="width:80%; height:100%; position:absolute; left:0px; top:0px; z-index:100;" value=""/>
					</div></td>
				</tr>
				<tr>
					<td><font style="margin-left:10%;">项目等级：</font></td>
					<td><!-- <input type="text" name="project.level" id="l4" class="form-control"> -->
					<div style="width:150px;height:35px;position:relative">
					<select style="width:150px;"  class="form-control" onChange="set_modaul(this);">
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
					
					</select>
					<input type="text" name="project.level" id="l4" class="form-control" style="width:80%; height:100%; position:absolute; left:0px; top:0px; z-index:100;" value="" readonly/>
					</div></td>
				</tr>
				<tr style="display:none">
					<td><input type="hidden" name="project.vpmName" id="l5"></td>
				</tr>
				<tr style="display:none">
					<td><font style="margin-left:10%;"></font></td>
					<td><input name="project.testPhase" style="width:100px;"
						type="hidden" id="l6"></td>
				</tr>
				<tr style="background-color:#4682B4">
					<td colspan="2" style="height:40px;">
						<div style="margin-left:25%;">
							<input type="submit" value="提交" 
								id="btn_update" class="btn btn-primary"
								style="border:1px solid black;width:60px;">
							&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset" value="取消"
								onclick="hideAddInput()" class="btn btn-primary"
								style="border:1px solid black;width:60px;">
						</div>
					</td>
				</tr>
			</table>
		</form>
		<!--################################################新建项目#####################################################  -->
	</div>
	<div id="popDiv" class="mydiv" style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="closeDivFun()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
		<table
			style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;border-radius:30px;  ">
			<tr style="background-color:#4682B4;">
				<td colspan="2" style="height:40px;">
					<h3 style="margin-left:35%">
						<font color="white">增加项目</font>
					</h3>
			</tr>
			<form action="Project_addProject" method="post" onSubmit="return check1();">
				<tr style="display:none">
					<td></td>
					<td><input type="hidden" />&nbsp;</td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">项目名称：</span></td>
					<td><input type="text" name="project.projectName" value="" class="form-control" id="e1"></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">客户名称：</span></td>
					<td><!-- <input type="text" name="project.customer" value="" class="form-control" id="e2"> -->
					<div style="width:150px;height:35px;position:relative">
					<select  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" class="form-control" onChange="set_modaul(this);">
					<option value="请选择客户">请选择客户</option>
					<option value="魅族">魅族</option>
					<option value="华硕">华硕</option>
					<option value="联想">联想</option>
					<option value="阿尔卡特">阿尔卡特</option>
					<option value="BLU">BLU</option>
					<option value="富士康">富士康</option>
					</select>
					<input type="text" name="project.customer" id="e2"  class="form-control" style="width:80%; height:100%; position:absolute; left:0px; top:0px; z-index:100;" value=""/>
					</div>
					</td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">策略模板：</span></td>
					<td><select name="project.policyName" class="form-control" style="width:150px;">
							<c:forEach items="${policypools }" var="pps">
								<option>${pps.policyName }</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><span style="margin-left:10%;">项目等级：</span></td>
					<td><!-- <input type="text" name="project.level" value="" class="form-control" id="e3"> -->
					<select style="width:150px;" name="project.level"  class="form-control" id="e3">
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
					
					</select>
					</td>
				</tr>

				<tr style="background-color:#4682B4;">
					<td colspan="2" style="height:40px;">
						<div style="margin-left:25%;height:40px;">
							<input type="submit" value="提交" class="btn btn-primary"
								style="border:1px solid black;width:60px;">&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="取消" onclick="closeDivFun()"
								class="btn btn-primary"
								style="border:1px solid black;width:60px;">
						</div>
					</td>
				</tr>
		</table>
		</form>
	</div>
	<div id="importDiv" style="display:none;" class="mydiv">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="hidd()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
		<form action="Plan_importPlan" method="post" enctype="multipart/form-data" style="width:100%;height:100%;"  onSubmit="return checkfile();">
		<table
			style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
			<tr style="background-color:#4682B4;height:40px;text-align:center;vertical-align:middle;color: white">
				<td colspan="2">导入项目计划</td>
			</tr>
			<tr><td></td><td><input id="hhh" value="" type="hidden" name="projectId"></td></tr>
			<tr>				
				<td  colspan="2"><div class="upfilebox" style="margin-left:5%;"><input type="file" name="file" accept=".xls,.xlsx" id="file" onChange="validateFile(this)"><label ><i class="layui-icon" style="color:#4682B4;font-size:1.5em;">&#xe61f;</i> <span id="showname">上传xls文件</span></label></div><span id="showname1" style="display:none;"></span></td>
			</tr>
			
			<tr>
				<td colspan="2">上传本文件的第<input style="width:40px" 
					onkeyup="sounts(this)" name="num" value="1"/>页&nbsp;&nbsp; 第<input style="width:40px;"
					onkeyup="sounts(this)" name="rowNum" value="2"/>行
				</td>
			</tr>
			<tr><td colspan="2" style="color: red;">文本框中必须填大于0的整数，行必须填大于1的整数</td></tr>
			<tr
				style="background-color:#4682B4;height:40px;text-align:center;vertical-align:middle;">
				<td colspan="2"><input type="submit" value="导入" 
					class="btn btn-primary" style="border:1px solid black;width:60px;">&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="button" class="btn btn-primary"
					style="border:1px solid black;width:60px;" value="取消"
					onclick="hidd()"></td>
			</tr>
		</table>
		</form>
	</div>
	<script>
	function set_modaul(obj){//将select的值写入到input框里 ，若测试内容选择为入口测试，则版本开始时间为测试开始时间
	var ne=$(obj).next('input')[0];
	var pv=obj.value;
	ne.value=pv;
	
	}
	function validateFile(target){
var fileSize=0;
var name=target.value;

var showname=document.getElementById('showname');

showname.innerHTML=name;

if(!target.files)
{
var filePath=target.value;
var fileSystem=new ActiveXObject("Scripting.FileSystemObject");
var file=fileSystem.GetFile(filePath);
fileSize=file.size;
}else{
fileSize=target.files[0].size;
}
var size=fileSize/1024;
if(size>9000)
{
layer.msg("附件不能大于9M");
target.value="";
return
}

var fileName=name.substring(name.lastIndexOf(".")+1).toLowerCase();
if(fileName !="xls" && fileName !="xlsx")
{
layer.msg("只能上传excel格式的文件");
target.value="";
return
}
}
	function checkfile(){
	
	var file=document.getElementById('file').value;
	if(file==""||file==null)
	{
	layer.msg("请先选择文件");
	return false;
	}
	else{
	//alert("success");
	layer.msg('加载中', {
				icon : 16,
				shade : 0.5,
				time : 5000000
			});
	
	return true;
	
	}
	}
	function check1(){
    var a=document.getElementById('e1').value;
      var b=document.getElementById('e2').value;
       var c=document.getElementById('e3').value;     
       if(a==""||a==null)
     {
     layer.msg("项目名称不能为空");
     return false;
     }
     else if(b==""||b==null){
          layer.msg("客户名称不能为空");
      return false;
     }
      else if(c==""||c==null){
          layer.msg("项目等级不能为空");
      return false;
     }else{return true;}
     }
     function check(){
     var a=document.getElementById('l2').value;
      var b=document.getElementById('l3').value;
       var c=document.getElementById('l4').value;
     if(a==""||a==null)
     {
     layer.msg("项目名称不能为空");
     return false;
     }
     else if(b==""||b==null){
          layer.msg("客户名称不能为空");
      return false;
     }
      else if(c==""||c==null){
          layer.msg("项目等级不能为空");
      return false;
     }else{return true;}
     }
	
	/* $("button").click(function(){
    $(this).attr("disabled","disabled");    
}); */
	</script>
</body>
</html>

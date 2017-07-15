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
<style>
li{list-style:none;}

#table td{border:1px solid #EDEDED;}
a{font-size:0.9em;}
a:hover{color:red;}
td{font-size:0.9em;}
</style>
<script>
function delet(param){
layer.confirm('确定删除此模块?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                              // alert(param);
                               
                             $.ajax( {

       async : false,

       cache : false,

       type : 'POST',

       url :"Case_deleteCasestore?id="+param+"&typeId=${id}&tag=0",// 请求的action路径      href="Case_deleteCaseType?id=${cas.id }&tag=0"

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

<script type="text/javascript">
	function console() {
		var checkedSubject = $('#table input[name=checkedId]:checkbox:checked');
		var checkedIds = "";
		//循环获取选中的复选框的value，这个value是数据表中每条记录的主键${cd.caseId }，传给后台，后台就能根据主键查找到数据表的相应记录
		//将其value用逗号隔开拼接成一个字符串
		checkedSubject.each(function() {
			checkedIds = checkedIds + "," + $(this).val();
		});
		//alert(checkedIds);
		if ((checkedIds == "" || checkedIds == null || checkedIds == 0)) {
			layer.msg("请勾选要导出的模块");
		} else {
			
layer.confirm('确定导出选中模块?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                $('#checkedIds').val(checkedIds);
				
				$('#formid').prop("action", "Case_exportModule?typeId=${id}");
				//提交隐藏域表单，后台才能获取隐藏域表单的值
				$('#formid').submit();
                                
                                },function(index){ layer.close(index);
								return false;
								});
			
				

			
		}
	}


	function export1(vall) {
		layer.confirm('确定导出此模块用例?', {
			icon : 3,
			title : '提示'
		}, function(index) {
			layer.close(index);

			window.location.href = "Case_exportCase?typeId=" + vall;
		}, function(index) {
			layer.close(index);
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
				<li style="float:left;"><input type="text" id="search"
						class="form-control" placeholder="搜索" style="width:200px;"></li>
				<!-- <li style="float:left;">
						<button type="submit" class="btn btn-info">确定</button>
				</li> -->
				<li  style="float:left;"><c:if test="${not empty permissionForm.expor }">
					<button type="submit" class="btn btn-info" onclick="console()">${permissionForm.expor }</button></c:if>
			</li>
			</span>
		</ul></td></tr></table>
		<hr/>
		<table id="table" style="TABLE-LAYOUT:automatic;WORD-BREAK:break-all;width:80%;margin-left:5%;" class="table table-condensed table-bordered table-hover table-striped">
			<tr style="background-color:#4682B4;height:40px;">
				<td style="text-align:center;vertical-align:middle;">
						<font color="white"><input type="checkbox" name="all"
						id="all" />序号</font></td>
						<td style="text-align:center;vertical-align:middle;display:none;"></td>
				<td style="text-align:center;vertical-align:middle;">
						<font color="white">测试内容</font></td>
				<td style="text-align:center;vertical-align:middle;">
						<font color="white">总耗时（h）</font></td>
				<td style="text-align:center;vertical-align:middle;">
						<font color="white">P1用例（条）--耗时（h）</font></td>
				<td style="text-align:center;vertical-align:middle;">
						<font color="white">P2用例（条）--耗时（h）</font></td>
				<td style="text-align:center;vertical-align:middle;">
						<font color="white">P3用例（条）--耗时（h）</font></td>
				<td style="text-align:center;vertical-align:middle;">
						<font color="white">P4用例（条）--耗时（h）</font></td>
				<!-- <td style="text-align:center;vertical-align:middle;">
						<font color="white">创建人</font></td> -->
				<td style="text-align:center;vertical-align:middle;">
						<font color="white">维护人</font></td>
				<!-- <td style="text-align:center;vertical-align:middle;">
						<font color="white">创建时间</font></td> -->
				<td style="text-align:center;vertical-align:middle;">
						<font color="white">最新维护时间</font></td>
						<c:if test="${not empty permissionForm.edit||not empty permissionForm.delete }">
				<td style="text-align:center;vertical-align:middle;"><h4>
						<font color="white">操作</font></h4></td>
						</c:if>
			</tr>
			<tbody id="tb">			<% int i=1; %>
			<c:forEach items="${CaseStore }" var="cs">
				<tr style="height:40px;"  username="${cs.testModule }">
				<td style="text-align:center;vertical-align:middle;"><font
						color="black"><input type="checkbox" name="checkedId"
						id="checkedId" value=${cs.casestoreId }
						class=" id-checkbox" /><%=i++ %></font></td>
				
							<td style="text-align:center;vertical-align:middle;display:none;"><font
						color="black">${cs.casestoreId }</font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black"><a
							href="Case_caseDetail?id=${cs.casestoreId }&page=1">${cs.testModule }(<font color="red">${cs.caseNum}</font>)</a></font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.totalTime }</font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.p1TotalTime }</font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.p2TotalTime }</font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.p3TotalTime }</font></td>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.p4TotalTime }</font></td>
					<%-- <td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.createName }</font></td> --%>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.updateName }</font></td>
					<%-- <td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.createTime }</font></td> --%>
					<td style="text-align:center;vertical-align:middle;"><font
						color="black">${cs.updateTime }</font></td>
						<c:if test="${not empty permissionForm.edit||not empty permissionForm.delete }">

					<td style="text-align:center;vertical-align:middle;"><c:if test="${not empty permissionForm.edit }"><a  onclick="updateRow(this);" >${permissionForm.edit }</a></c:if><c:if test="${not empty permissionForm.edit&&not empty permissionForm.delete }">|
					</c:if><c:if test="${not empty permissionForm.delete }"><a onclick="delet(${cs.casestoreId})">${permissionForm.delete }</a></c:if>
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
		<input type="hidden" name="tag" value="0">
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
					<td><input type="text" id="l3" name="content"  class="form-control" style="width:150px;"/></td>
				</tr>
				<tr style="display:none;">
					<td><span style="margin-left:10%;">创建人：</span></td>
					<td><input type="text" id="l4" disabled="true"  class="form-control" style="width:150px;"/></td>
				</tr>
				
				<tr style="background-color:#4682B4;height:30px;">
					<td colspan="2">
						<div style="margin-left:30%">
							<input type="submit" value="提交" 
								style="border:1px solid black;" id="btn_update" class="btn btn-primary"  >
							 &nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="取消"
								onclick="hideAddInput()" class="btn btn-primary" style="border:1px solid black;">
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
		<input type="hidden" name="tag" value="0">
		<input type="hidden" name="id" value="${id }">
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
					<td><input type="text" name="testModule" value=""  class="form-control" style="width:150px;"></td>
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
     <%-- 隐藏域表单 --%>
	<form action="" method="post" id="formid"> <input
			type="hidden" name="checkedIds" id="checkedIds" />
	</form>
</body>
</html>

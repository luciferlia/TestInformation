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
<script src="script/ie.js"></script>
<style type="text/css">
a{font-size:0.9em;color:red;}
a:hover{color:blue;}
.table input{heigth:40px;}
.myui td{border: 1px solid #C0C0C0; font-size:0.9em;text-align:center;vertical-align:middle;}
</style>


<script>
   layui.use('layer', function(){
  var layer = layui.layer;
 
}); 
	layui.use([ 'form', 'layedit', 'laydate' ],function() {
						var form = layui.form(), 
						layer = layui.layer, 
						layedit = layui.layedit, 
						laydate = layui.laydate;


					});
					//Plan_updateWeekPlan
					function shohid(){
					var a=document.getElementById('showi');
					var b=document.getElementById('showii');
					$(a).hide();
					$(b).show();
					}
					 function addProjectPlan(){
    var sum=0;
      var data = savePlan(); //获取要传到后台的数据 json 格式
      var x=document.getElementsByName('road')[0].value;
      
      if(x==null|x=="")
      
      {
      layer.msg("版本路径不能为空");
     
      }
      else
      {
      layer.msg('加载中', {
  icon: 16
  ,shade:0.5,time:5000000
});
      console.log(data);
    
            $.ajax({
             type: "POST",
            url: "addWeekPlan", //这里你根据后台地址改
         data:{"params":JSON.stringify(data)},
		dataType:"json",
         success: function(data){
           
            layer.msg('添加周计划成功!',{time:1000});
            window.location.reload();
            //setTimeout(function(){ window.location.reload()},2000);
           
          
         },
         error : function(xhx,e,errMsg) {
         	alert(errMsg);
           layer.msg('添加周计划失败!',{time:3000});
         }
      });
     
      }
    
      
   }

   function savePlan(){
      var array=[];
      var versionType=document.getElementsByName("versionType")[0].value;
        var road=document.getElementsByName("road")[0].value;
          var bug=document.getElementsByName("bug")[0].value;
        
          var fr=document.getElementsByName("remark");
          var fg=document.getElementsByName("planId");
          var remark,planId;
          //alert(fr.length);
          for(i=0;i<fr.length;i++)
          {
          
          remark=fr[i].value;
          planId=fg[i].value;
          json={remark,planId,versionType,road,bug};
          array.push(json);
          }
    
      return array;
   }

   function isEmptyObject(obj) {
      for (var key in obj) {
         return false;
      }
      return true;
   }
   
   function checkPlan(planId){
 	$.ajax({
             type: "POST",
            url: "checkPlanContent?planId="+planId, //这里你根据后台地址改
         success: function(data){
            if(data=='success'){
            	bflag=true;
            	window.location.href="Plan_showDeliverPre?id="+planId;
            }else{
            	layer.msg('该计划不能进行分配!',{time:3000});
            	bflag=false;
            
            }
         },
         error : function(e) {
           layer.msg('验证权限失败!',{time:1000});
         }
      });
 	}
</script>
</head>

<body>
     <h4 class="glyphicon glyphicon-tasks" style="margin-left:4%;margin-top:1%;">项目周计划</h4>
	<hr/>
	<button class="btn btn-primary" type="button" onclick="shohid()" style="margin-left:4%;">编辑</button>
		<span style="font-size:1.2em;margin-left:30%;">项目名称：
		<% int i=0; %>
		   <c:forEach items="${planForms }" var="pf">
			<% if(i==0){ %>
			${pf.plan.project.projectName }
			<%} i=1; %>
		</c:forEach>
		&nbsp&nbsp&nbsp阶段：
		<% int j=0; %>
		<c:forEach items="${planForms }" var="pf">
			<% if(j==0){ %>
			${pf.plan.phase } 
			<%} j=1; %>
		</c:forEach>
		&nbsp&nbsp&nbsp版本：
		<% int m=0; %>
		<c:forEach items="${planForms }" var="pf">
			<% if(m==0){ %>
			${pf.plan.version } 
			<%} m=1; %>
		</c:forEach>
		</span>
		<span  id="showi">
		<table style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;margin-left:4%;width:90%;min-width: 1000px;" class="myui">
		<tr style="background-color:#4682B4;height:40px;color: white;">
			<td style="width:50px;"><h4>序号 </h4></td> 
			<!-- <td><h4>版本用途 </h4></td>  -->
			
			<td><h4>测试模块</h4></td>
			<td style="min-width:200px;width:15%;"><h4>测试内容</h4></td>
			<td><h4>总人力</h4> </td>      <!-- <td><h4>需缺人力</h4></td> -->
			<td><h4>开始时间</h4></td>
			<td><h4>结束时间</h4></td>
			<td><h4>备注</h4></td>
			<td style="display:none;"><h4></h4></td>
			<td><h4>操作</h4></td>
		</tr>
			<tbody id="cdTb">
			<% int y=1; %>
		 <c:forEach items="${planForms }" var="pf">
		<tr style="height:40px;">
				<td><font color="black"  size="2"><%=y++ %></font></td>
				<%-- <td><textarea placeholder="请输入内容" class="layui-textarea" name="versionType" style="min-height:50px;" value="${pf.plan.versionType }">${pf.plan.versionType }</textarea></td> --%>
				<td>${pf.plan.casetype.casetypeName }</td>
				<td><c:forEach items="${pf.planCasestores }" var="pc">${pc.casestore.testModule },</c:forEach> </td>
				<td>${pf.plan.humanInput }</td>
				<%-- <td>${pf.plan.humanCount }</td> --%>
				<td>${pf.plan.startTime }</td>
				<td>${pf.plan.endTime }</td>
			    <td style="overflow:hidden;text-overflow: ellipsis;white-space: nowrap;" onmouseover="showmark(this,event);">${pf.plan.remark }</td>
			    <td style="display:none">${pf.plan.planId }</td>
				<td><a onclick="checkPlan(${pf.plan.planId })">分配</a></td>
			</tr>
			</c:forEach>
		</tbody>
		</table>
		<hr/>
		<c:forEach items="${planForms }" var="pf" begin="0" end="0">
		<div style="width:90%;min-height:200px;margin-left:4%;border-left:5px solid #87CEFA;border-top:1px solid #87CEEB;border-bottom:1px solid #87CEEB;border-right:1px solid #87CEEB;border-radius:10px;">
		<ul><li style="height:60px;"><p style="margin-left:5%;margin-top:2%;">版&nbsp;本&nbsp;类&nbsp;&nbsp;型：${pf.plan.versionType }</p></li>
		<li  style="height:60px;"><span style="margin-left:5%;">版&nbsp;本&nbsp;路&nbsp;&nbsp;径：<a href="file://${pf.plan.versionPath }">${pf.plan.versionPath }</a></span></li>
		<li  style="height:60px;"><span style="margin-left:5%;">BUG库路径：<a href="${pf.plan.bugPath }">${pf.plan.bugPath }</a></span><li></ul>		
		</div>		
		</c:forEach>
		</span>
		<span id="showii" style="display:none;">
		<table  id="table" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;margin-left:4%;width:90%;min-width: 1000px;" class="myui">
		<tr style="background-color:#4682B4;height:40px;color: white;">
			<td style="width:50px;"><h4>序号 </h4></td> 
			<!-- <td><h4>版本用途 </h4></td>  -->
			
			<td><h4>测试模块</h4></td>
			<td style="min-width:200px;width:15%;"><h4>测试内容</h4></td>
			<td><h4>总人力</h4> </td>
			<!-- <td><h4>需缺人力</h4></td> -->
			<td><h4>开始时间</h4></td>
			<td><h4>结束时间</h4></td>
			<td ><h4>备注</h4></td>
			<td style="display:none;"><h4></h4></td>
		</tr>
			<tbody id="cdTb">
			<% int s=1; %>
		 <c:forEach items="${planForms }" var="pf">
		<tr style="height:40px;">
				<td><font color="black"  size="2"><%=s++ %></font></td>
				<td>${pf.plan.casetype.casetypeName }</td>
				<td><c:forEach items="${pf.planCasestores }" var="pc">${pc.casestore.testModule },</c:forEach> </td>
				<td>${pf.plan.humanInput }</td>
				<%-- <td>${pf.plan.humanCount }</td> --%>
				<td>${pf.plan.startTime }</td>
				<td>${pf.plan.endTime }</td>
			    <td><textarea placeholder="请输入内容" class="layui-textarea" name="remark" style="min-height:50px;" value="${pf.plan.remark }">${pf.plan.remark }</textarea></td>
			    <td style="display:none"><textarea type="hidden" name="planId" value="${pf.plan.planId }">${pf.plan.planId }</textarea></td>
			</tr>
			</c:forEach>
		</tbody>
		</table>
		<hr/>
		<c:forEach items="${planForms }" var="pf" begin="0" end="0">
		
		<div style="width:90%;min-height:200px;min-width:500px;margin-left:4%;border-left:5px solid #87CEFA;border-top:1px solid #87CEEB;border-bottom:1px solid #87CEEB;border-right:1px solid #87CEEB;border-radius:10px;">
		<ul><li style="height:60px;"><p style="margin-left:5%;margin-top:1%;"><ul><li style="float:left;margin-left:5%;">版&nbsp;本&nbsp;类&nbsp;&nbsp;型：</li><li style="float:left;"><textarea placeholder="请输入内容" class="layui-textarea" name="versionType" style="min-height:40px;width:400px;"value="${pf.plan.versionType }">${pf.plan.versionType }</textarea></li></ul></p></li>
		<li  style="height:60px;"><p style="margin-left:5%;"><ul><li style="float:left;margin-left:5%;">版&nbsp;本&nbsp;路&nbsp;&nbsp;径：</li><li style="float:left;"><textarea placeholder="请输入内容" class="layui-textarea" name="road" style="min-height:40px;width:400px;"value="${pf.plan.versionPath }">${pf.plan.versionPath }</textarea></li></ul></p></li>
		<li  style="height:60px;"><p style="margin-left:5%;"><ul><li style="float:left;margin-left:5%;">BUG库路径：</li><li style="float:left;"><textarea placeholder="请输入内容" class="layui-textarea" name="bug" style="min-height:40px;width:400px;"value="${pf.plan.bugPath }">${pf.plan.bugPath }</textarea></li></ul></p><li></ul>		
		</div>
		</c:forEach>
		<button type="submit" style="margin-left:80%;"onclick="addProjectPlan()" class="layui-btn btn-primary layui-btn-radius">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		 <div id="remark" style="display:none;font-size:0.9em;"> </div>
		
</html>

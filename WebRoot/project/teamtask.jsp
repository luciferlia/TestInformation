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
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange.js"></script>
  <script src="script/ie.js"></script>
<style type="text/css">

 #table td{border:1px solid #EDEDED;font-size:0.9em;}

td{font-size:0.9em;text-align:center;vertical-align:middle;}
a{font-size:0.9em;}
a:hover{color:red;}
table{color:black;}

.table input{heigth:40px;}
.myui td{border: 1px solid #EDEDED;}
</style>

<script>
	function hide(tableId, startRow, endRow, col){
	var a=document.getElementById('table');
	a.style.display='none';
	var b=document.getElementById('table1');
	b.style.display='block';
	var c=document.getElementById('buto');
	c.style.display='none';
	var e=document.getElementById('buto2');
	e.style.display='block';
	var tb = document.getElementById(tableId);  
         var s=tb.rows[1].cells[1].innerHTML;
       
        if (col >= tb.rows[0].cells.length) {  
            return;  
        }  
        //当检查第0列时检查所有行  
        if (col == 0) {  
            endRow = tb.rows.length - 1;  
        }  
        for (var i = startRow; i < endRow; i++) {  
            //subCol:已经合并了多少列  
            var subCol = tb.rows[0].cells.length - tb.rows[startRow].cells.length;  
            //程序是自左向右合并，所以下一行一直取第0列  
            if (tb.rows[startRow].cells[col - subCol].innerHTML == tb.rows[i + 1].cells[0].innerHTML) {  
                //如果相同则删除下一行的第0列单元格  
                tb.rows[i + 1].removeChild(tb.rows[i + 1].cells[0]);  
                //更新rowSpan属性  
                tb.rows[startRow].cells[col - subCol].rowSpan = (tb.rows[startRow].cells[col - subCol].rowSpan | 0) + 1;  
                //当循环到终止行前一行并且起始行和终止行不相同时递归(因为上面的代码已经检查了i+1行，所以此处只到endRow-1)  
                if (i == endRow - 1 && startRow != endRow) {  
                    hide(tableId, startRow, endRow, col + 1);  
                }  
            } else {  
                //起始行，终止行不变，检查下一列  
               hide(tableId, startRow, i, col + 1);  
                //增加起始行  
                startRow = i + 1;  
            }  
        }  
	}

	function hidd2(){
	var a=document.getElementById('table');
	a.style.display='block';
	var b=document.getElementById('table1');
	b.style.display='none';
	var c=document.getElementById('buto');
	c.style.display='block';
	var d=document.getElementById('buto1');
	d.style.display='block';
	var e=document.getElementById('buto2');
	e.style.display='none';
	}
	</script>
<script>
   function delet1(obj){
    var tr=obj.parentNode.parentNode;
    var tbody=tr.parentNode;
    tbody.removeChild(tr);
      
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
    	//alert(data);
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
   </script>
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
</script>
</head>

<body>
<h4 class="glyphicon glyphicon-tasks" style="margin-left:4%;margin-top:1%;">项目周计划</h4>
	<hr/>
		<span style="font-size:1.2em;margin-left:35%;">项目名称：
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
		<form class="layui-form">
		<table  id="table" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;margin-left:4%;width:90%;min-width: 1000px;">
		<tr style="background-color:#4682B4;height:40px;color: white;">
			<td style="width:50px;"><h4>序号 </h4></td> 
			<!-- <td><h4>版本用途 </h4></td>  -->
			
			<td><h4>测试模块</h4></td>
			<td style="min-width:200px;width:15%;"><h4>测试内容</h4></td>
			<td><h4>总人力</h4> </td>
			<!-- <td><h4>需缺人力</h4></td> -->
			<td><h4>开始时间</h4></td>
			<td><h4>结束时间</h4></td>
			<td><h4>备注</h4></td>
			<td style="display:none;"><h4></h4></td>
		</tr>
			<tbody id="cdTb">
			<% int y=1; %>
		 <c:forEach items="${planForms }" var="pf">
		<tr style="height:40px;">
				<td><font color="black"  size="2"><%=y++ %></font></td>
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
		<div style="width:90%;min-height:200px;font-size:0.9em;min-width:500px;margin-left:4%;border-left:5px solid #87CEFA;border-top:1px solid #87CEEB;border-bottom:1px solid #87CEEB;border-right:1px solid #87CEEB;border-radius:10px;">
		<ul><li style="height:60px;"><p style="margin-left:5%;margin-top:1%;"><ul><li style="float:left;margin-left:5%;">版&nbsp;本&nbsp;类&nbsp;&nbsp;型：</li><li style="float:left;"><textarea placeholder="请输入内容" class="layui-textarea" name="versionType" style="min-height:40px;width:400px;"value=""></textarea></li></ul></p></li>
		<li  style="height:60px;"><p style="margin-left:5%;"><ul><li style="float:left;margin-left:5%;">版&nbsp;本&nbsp;路&nbsp;&nbsp;径：</li><li style="float:left;"><textarea placeholder="请输入内容" class="layui-textarea" name="road" style="min-height:40px;width:400px;"value=""></textarea></li></ul></p></li>
		<li  style="height:60px;"><p style="margin-left:5%;"><ul><li style="float:left;margin-left:5%;">BUG库路径：</li><li style="float:left;"><textarea placeholder="请输入内容" class="layui-textarea" name="bug" style="min-height:40px;width:400px;"value=""></textarea></li></ul></p><li></ul>		
		</div>
		
		</c:forEach>
		</form>
	<button type="submit" onclick="addProjectPlan()" id="buto" class="layui-btn btn-primary layui-btn-radius" style="margin-left:80%;">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</html>

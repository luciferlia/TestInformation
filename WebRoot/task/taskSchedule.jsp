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
<script src="script/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange4.js"></script>
<style>
li {
	list-style: none;
}

td {
	font-size: 0.9em;
	font-color: black;
	text-align: center;
	vertical-align: middle;
}
</style>
<script>
	layui.use('element', function() {
		var element = layui.element();
	});
</script>
<script>
	layui.use([ 'form', 'layedit', 'laydate' ], function() {
		var form = layui.form(),
			layer = layui.layer,
			layedit = layui.layedit,
			laydate = layui.laydate;


	});
	$(document).ready(function(){
	
	var row=document.getElementById("table").rows.length;
    var cell = document.getElementById("table").rows.item(0).cells.length;
	var average=0;
	 var tb = document.getElementById("table");  
	
	
	for(var i=2;i<row+1;i++)
	{
	  
	var tex1=$("tr:eq('"+i+"') td:eq(9)").text();
	$("tr:eq('"+i+"') td:eq(9)").css("color","#FF0000");
	var tex=$("tr:eq('"+i+"') td:eq(11)").text();
	
	if(tex=="已完成"){
		
		$("tr:eq('"+i+"') td:eq(11)").css("color","blue");
	}
	if(tex=="未进行")
	{
	$("tr:eq('"+i+"') td:eq(11)").css("color","#FF8C00");
	}
	if(tex=="未分配"){
	$("tr:eq('"+i+"') td:eq(11)").css("color","green");
	}
	if(tex=="进行中"){
	$("tr:eq('"+i+"') td:eq(11)").css("color","red");
	}
	
	}
     for(var j=1;j<row;j++)
     {
   
     var sum=parseFloat(tb.rows[j].cells[7].innerHTML);//分配的用例条数
      var sum1=parseFloat(tb.rows[j].cells[8].innerHTML);//执行的用例条数
      for(var k=j+1;k<row;k++){
       	if (tb.rows[j].cells[0].innerHTML == tb.rows[k].cells[0].innerHTML&&tb.rows[j].cells[1].innerHTML == tb.rows[k].cells[1].innerHTML)
		{
			sum+=parseFloat(tb.rows[k].cells[7].innerHTML);
			sum1+=parseFloat(tb.rows[k].cells[8].innerHTML);
     
		if(k==row-1){
			j=k;
		}
		}else{
		j=k-1;break;
		}
	 }
	 if(sum==0)
	 {
	 average=0;
	  tb.rows[j].cells[12].innerHTML=average+".00"+"%";
	 }
	 else{
	 var resl=parseFloat(sum1*100/sum);
    average=changeTwoDecimal(resl);
     //tb.rows[j].cells[13].innerHTML=yu+"("+average+"%"+")";
             tb.rows[j].cells[12].innerHTML=average+"%";
      }

     }
	//hebingRows(13);
	/* for(var h=0;h<cell;h++)
	{
	hebingRows(h);
	} */
	}
	
	);
	
	function changeTwoDecimal(x){
	var f_x = parseFloat(x);  
		if (isNaN(f_x))  
		{  
		alert('function:changeTwoDecimal->parameter error');  
		return false;  
		}  
		var f_x = Math.round(x*100)/100;  
		var s_x = f_x.toString();  
		var pos_decimal = s_x.indexOf('.');  
		if (pos_decimal < 0)  
		{  
		pos_decimal = s_x.length;  
		s_x += '.';  
		}  
		while (s_x.length <= pos_decimal + 2)  
		{  
		s_x += '0';  
		}  
		return s_x;  
	}
	 
</script>
<script type="text/javascript">  
    ///合并表格相同行的内容  
    ///tableId：表格ID（最好是tbody，避免把表尾给合并了)  
    ///startRow：起始行，没有标题就从0开始  
    ///endRow：终止行，此参数是递归时检查的范围，一开始时会自动赋值为最后一行  
    ///col：当前处理的列  
    function MergeCell(tableId, startRow, endRow, col) {  
      
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
                    MergeCell(tableId, startRow, endRow, col + 1);  
                }  
            } else {  
                //起始行，终止行不变，检查下一列  
                MergeCell(tableId, startRow, i, col + 1);  
                //增加起始行  
                startRow = i + 1;  
            }  
        }  
        
        
        
    }  
 
</script>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<body onload="MergeCell('cdTb', 0, 0, 0)">
	<h3 style="margin-left:1%;margin-top:1%"
		class="glyphicon glyphicon-tasks">任务进度</h3>
	<hr/>
	<c:if test="${not empty pageBean.list }">
	<!-- ###########################################显示任务进度####################################### -->
	
	<span style="font-size:1.2em;margin-left:45%;">项目名称：
		<% int i=0; %>
		<s:iterator value="pageBean.list">
			<% if(i==0){ %>
			<s:property value="plantail.plan.project.projectName" />
			<%} i=1; %>
		</s:iterator>
	</span>
	<table id="table"
		style="width:90%;TABLE-LAYOUT:fixed;WORD-BREAK:break-all;margin-left:1%;min-width:800px;"
		class="layui-table">
		<tr style="background-color:#4682B4;height:40px;color: white;">
			<!-- <td><h4>序号</h4></td> -->
			<!-- <td><h4>项目名称</h4></td> -->
			<td><h4>项目阶段</h4></td>
			<td><h4>软件版本</h4></td>
			<td><h4>测试类型</h4></td>
			<td style="width:20%;"><h4>测试内容</h4></td>
			<td><h4>开始时间</h4></td>
			<td><h4>结束时间</h4></td>
			<td><h4>责任人</h4></td>
			<td><h4>分配用例总条数</h4></td>
			<td><h4>已执行用例条数</h4></td>
			<td><h4>问题用例条数</h4></td>
			<td><h4>完成度</h4></td>
			<td><h4>任务状态</h4></td>
			<td><h4>总体进度</h4></td>
			<!-- <td><h4>完成状态</h4></td> -->
		</tr>
		<tbody id="cdTb">
			<s:iterator value="pageBean.list">
				<tr style="height:30px;">
					<%-- <td><s:property value="id"/></td> --%>
					<%-- <td><s:property value="plantail.plan.project.projectName" /></td> --%>
					<td><s:property value="plantail.plan.phase" /></td>
					<td><a href="task_versionParam?projectName=<s:property value="plantail.plan.project.projectName"/>&version=<s:property value="plantail.plan.version"/>"><font color="#8B4513"><s:property value="plantail.plan.version" /></font></a></td>
					<td><s:property value="plantail.plan.casetype.casetypeName" /></td>
					<td ><a href="task_showTestResult?id=<s:property value="plantail.plan.planId"/>& userId=<s:property value="plantail.user.userId"/> &page=1"><font color="#8B4513"><s:property value="casestoreName"/></font></a></td>
					<td><s:property value="plantail.plan.startTime" /></td>
					<td><s:property value="plantail.plan.endTime" /></td>
					<td><s:property value="plantail.user.name" /></td>
					<td><s:property value="plantail.caseCount" /></td>
					<td><s:property value="plantail.finishCase" /></td>
					<td><s:property value="issueCount" /></td>
					<td><s:property value="plantail.finishDegree" /></td>
					<td><s:property value="plantail.state" /></td>
					<%-- <td><s:property value="plantail.finishState" /></td> --%>
					<td><s:property value="" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<table style="width:90%;margin-left:1%;">
		<tr>
			<td><span style="float:left;">共<span><s:property
							value="pageBean.totalPage" /></span>页|第<span><s:property
							value="pageBean.currentPage" /> </span>页
			</span></td>
			<td><span style="float:right;"><form action="task_showTakSchedule?page=1" class="right-font08">
						[
						<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
						<s:else>
							<a href="task_showTakSchedule?page=1" class="right-font08">首页</a> | <a
								href="task_showTakSchedule?page=<s:property value="%{pageBean.currentPage-1}"/> "
								class="right-font08">上一页</a>
						</s:else>
						|
						<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
							<a
								href="task_showTakSchedule?page=<s:property value="%{pageBean.currentPage+1}"/> "
								class="right-font08">下一页</a> | <a
								href="task_showTakSchedule?page=<s:property value="%{pageBean.totalPage}"/> "
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
						<input type="submit" class="btn btn-primary btn-xs"
								style="border:1px solid black;font-size:0.9em"
								value="确定" />
					</form></span></td>
		</tr>
	</table>
</c:if>
	<c:if test="${empty pageBean.list }">
		<c:if test="${message=='' }">
		<center style="color:red;font-size:10px;">没有任务进度信息</center>
		</c:if>
		<c:if test="${message!='' }"><center style="color:red;font-size:10px;">${message }</center></c:if>
	</c:if>
	</body>
</html>
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
	});
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
    $(document).ready(function(){
	
	var row=document.getElementById("table").rows.length;
    var cell = document.getElementById("table").rows.item(0).cells.length;
	 var tb = document.getElementById("table");  
	
      for(var i=2;i<row+1;i++)
	{
	var tex=$("tr:eq('"+i+"') td:eq(7)").text();
	$("tr:eq('"+i+"') td:eq(7)").css("color","#228B22");
	var tex2=$("tr:eq('"+i+"') td:eq(11)").text();
	$("tr:eq('"+i+"') td:eq(11)").css("color","#FF0000");
    } 
    });
     
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<body onload="MergeCell('cdTb', 0, 0, 0)">
	<h3 style="margin-left:1%;margin-top:1%"
		class="glyphicon glyphicon-tasks">用例执行结果</h3>
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
				<span>
				<table id="table" style="width:90%;TABLE-LAYOUT:automatic;WORD-BREAK:break-all;margin-left:1%" class="layui-table">
					<tr style="background-color:#4682B4;height:40px;color: white;">
						<!-- <td><h4>序号</h4></td> -->
						<!-- <td><h4>项目名称</h4></td> -->
						<td><h4>项目阶段</h4></td>
						<td><h4>软件版本</h4></td>
						<td><h4>测试类型</h4></td>
						<td><h4>测试内容</h4></td>
						<td><h4>测试者</h4></td>
						<td><h4>分配用例总条数</h4></td>
						<td><h4>已执行用例条数</h4></td>
						<td><h4>Pass条数</h4></td>
						<td><h4>NA条数</h4></td>
						<td><h4>NT条数</h4></td>
						<td><h4>TC Issue条数</h4></td>
						<td><h4>Fail条数</h4></td>
						<td><h4>操作</h4></td>
						</tr>
					<tbody id="cdTb">
					<s:iterator value="pageBean.list">
					<tr style="height:30px;">
						<%-- <td><s:property value="id"/></td> --%>
						<%-- <td><s:property value="plan.project.projectName"/></td> --%>
						<td><s:property value="plan.phase"/></td>
						<td><s:property value="plan.version"/></td>
						<%-- <td><a href="task_versionParam?projectName=<s:property value="plan.project.projectName"/>&version=<s:property value="plan.version"/>"><s:property value="plan.version"/></a></td> --%>
						<td><s:property value="plan.casetype.casetypeName"/></td>
						<td><s:property value="casestoreName"/></td>
						<%-- <td><a href="task_moduleParam?projectName=<s:property value="plan.project.projectName"/>&version=<s:property value="plan.version"/>&casetypeName=<s:property value="plan.casetype.casetypeName"/>&tester=<s:property value="plantail.user.name"/>"><s:property value="casestoreName"/></a></td> --%>
						<td><s:property value="plantail.user.name"/></td>
						<td><s:property value="totalCount"/></td>
						<td><s:property value="finishCase"/></td>
						<td><s:property value="pass"/></td>
						<td><s:property value="naCount"/></td>
						<td><s:property value="ntCount"/></td>
						<td><s:property value="tcIssueCount"/></td>
						<td><s:property value="fail"/></td>
						<td><a href="task_showCaseResult?planId=<s:property value="plan.planId"/>&userId=<s:property value="plantail.user.userId"/>"><font color="#8B4513">问题用例</font></a></td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
				<table style="width:90%;margin-left:1%;">
				<tr>
					<td><span style="float:left;">共<span><s:property
								value="pageBean.totalPage" /></span>页|第<span><s:property
								value="pageBean.currentPage" /> </span>页</span>
					</td>
					<td><span style="float:right;"><form action="task_showTestResult" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="task_showTestResult?page=1" class="right-font08">首页</a> | <a
									href="task_showTestResult?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="task_showTestResult?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="Plantail_showTestResult?page=<s:property value="%{pageBean.totalPage}"/> "
									class="right-font08">末页</a>
							</s:if>
							<s:else>下一页 | 末页</s:else>
							] 转至：<select name="page" style="width:50px;">
								<s:iterator value="pageBean.count">
									<option value="<s:property value="count"/>"><s:property value="count" />
									</option>
								</s:iterator>
							</select>
							<%-- <input type="text" style="width:50px;" name="page" /> --%>
							<input type="submit" value="确定" class="btn btn-primary btn-xs" style="border:1px solid black;">
						</form></span></td>
				</tr>
			</table>
			</c:if>
		<c:if test="${empty pageBean.list }">
			<c:if test="${message=='' }">
			<center style="color:red;font-size:10px;">该测试内容还未分配出去，暂时没有测试结果信息</center>
			</c:if>
			<c:if test="${message!='' }"><center style="color:red;font-size:10px;">${message }</center></c:if>
		</c:if>
			<div class="layui-tab-item">
			<div id="main" style="height:400px"></div>
			  <script src="script/js/echarts.js"></script>
			    <script type="text/javascript">
			               // 基于准备好的dom，初始化echarts图表
			        var myChart = echarts.init(document.getElementById('main')); 
			        var option = {
					    color: ['#3398DB'],
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    grid: {
					        left: '3%',
					        right: '4%',
					        bottom: '3%',
					        containLabel: true
					    },
					    xAxis : [
					        {
					            type : 'category',
					            data : ['PASS', 'Fail', 'NA', 'NT'],
					            axisTick: {
					                alignWithLabel: true
					            }
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:'直接访问',
					            type:'bar',
					            barWidth: '60%',
					            data:[100, 5, 2, 3]
					        }
					    ]
					};
			        // 为echarts对象加载数据 
			        myChart.setOption(option); 
			    </script>
				</div>
				</body>
</html>
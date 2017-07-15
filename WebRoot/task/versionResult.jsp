<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<base href="<%=basePath%>">

<title>与德测试信息管理系统</title>


<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<script src="script/js/echarts.js"></script>

<style>
li {
	list-style: none;
}

td {
	font-size: 2px;
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
	layui
			.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

					});
					
					
	function showResult(){
	$.ajax({
             type: "POST",
         	url:"showVersionResult",
         success: function(data){
     //  alert(data);
 		console(data);
         var ss=data.substr(1, data.length-2).split(",");
         for(var i=0;i<ss.length;i++)
	       {
	       
	       var name=ss[i].split("-")[0];
	       var nums=ss[i].split("-")[1];
	      
	       }  
      	 
      	 	//myChart.hideLoading();//隐藏加载动画
      	 	//alert("jjj");
								myChart.setOption({//加载数据图表
									xAxis : {
										data : names
									},
									series : [ {
										name : '数量',
										data : nums
									} ]
								});
							
						},
         error : function(xhx,e,errMsg) {
         //请求失败时执行该函数
        // alert("jgjkjkj");
							console.log(errMsg);
							layer.msg("图表请求数据失败");
							myChart.hideLoading();
						}
      });
      
      }
</script>
	
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<body >

	<!-- <button onclick="showResult()">获取</button> -->
	<h3 style="margin-left:1%;margin-top:1%"
		class="glyphicon glyphicon-tasks">版本测试结果统计</h3>
	<hr/>
	<div class="layui-tab layui-tab-card"
		style="width:95%;height:90%;min-width:600px;min-height:400px;margin-left:1%;">
		<ul class="layui-tab-title">
			<li class="layui-this">版本结果显示</li>
			</ul>
		<!-- ###########################################显示任务进度####################################### -->
		<div class="layui-tab-content" style="height: 100px;">
			<div class="layui-tab-item layui-show">
				<div id="main" style="height:400px" ></div>
				<script type="text/javascript">
	//	function versionResult(){
		 var myChart = echarts.init(document.getElementById('main'));
					// 初始 option
					myChart.setOption({
						title : {
							text : ''
						},
						tooltip : {},
						legend : {
							data : [ '数量' ]
						},
						xAxis : {
							data : []
						},
						yAxis : {},
						series : [ {
							name : '数量',
							type : 'bar',
							data : []
						} ]
					});
					myChart.showLoading();//数据加载完之前先显示一段简单的loading动画
					var names = [];//名称数组，放在X轴
					var nums = [];//数量数组，放在Y轴
					 $.ajax({
             type: "POST",
         	url:"showVersionResult",
         success: function(data){
       //alert(data);
 		console.log(data);
         var ss=data.substr(1, data.length-2).split(",");
         for(var i=0;i<ss.length;i++)
	       {
	       
	        var name=ss[i].split("_")[0];
	        var num=ss[i].split("_")[1];
	      names.push(name);
	       nums.push(num);
	       }  
      	 console.log(names);
      	  console.log(nums);
      	 	myChart.hideLoading();//隐藏加载动画
      	 	//alert("jjj");
								myChart.setOption({//加载数据图表
									xAxis : {
										data : names
									},
									series : [ {
										name : '数量',
										data : nums
									} ]
								});
							
						},
         error : function(xhx,e,errMsg) {
         //请求失败时执行该函数
         //alert("jgjkjkj");
							console.log(errMsg);
							layer.msg("图表请求数据失败");
							myChart.hideLoading();
						}
      });
			
		//}
		</script>
			</div>
			<div class="layui-tab-item" id="showre">
				<div id="main1" style="height:400px"></div>

			    </div>
		</div>
	</div>
</body>
</html>
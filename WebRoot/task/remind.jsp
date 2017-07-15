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
<jsp:include page="/wind/top.jsp"></jsp:include>
<script src="script/js/bootstrap.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
        <div  id="main"     style="height:400px"></div>
       <!-- ECharts单文件引入 -->
  <%--  <script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script> --%>
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
      toolbox: {
        show : true,
        feature : {
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
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

        // 为echarts对象加载数据 
       /*  myChart.setOption({
        title:{
        text:'异步数据加载'
        },
        tooltip:{},
        legend:{
        data:['人力']
        },
        xAxis:{
        data:[]
        },
        yAxis:{},
        series:[{
        name:'人力',
        type:'bar',
        data:[]
        }]
        
        }); 
        //异步数据加载
        $.get('data.json').done(function (data){
        //填入数据
        myChart.setOption({
        xAixs:{
        data:data.categories
        },
        series:[{
        //根据名字对应到响应的系列
        name:'人力',
        data:data.data
        }]
        });
        });
        myChart.showLoading();
    $.get('data.json').done(function (data) {
    myChart.hideLoading();
    myChart.setOption();
       }); */
    </script>
  </body>
</html>

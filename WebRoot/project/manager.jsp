<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<base href="<%=basePath%>">

<title>与德测试信息管理系统</title>

<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<link rel="stylesheet" href="script/exchange.css">
<script src="script/exchange.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
 <script src="script/js/jquery.min.js"></script>
<style>
li{list-style:none;}
td{font-size:0.9em;font-color:black;border:1px solid black;
text-align:center;vertical-align:middle;}
</style>
<script>
	layui.use('element', function() {
		var element = layui.element();
	});
</script>
   <script>
	layui.use([ 'form', 'layedit', 'laydate' ],function() {
						var form = layui.form(), 
						layer = layui.layer, 
						layedit = layui.layedit, 
						laydate = layui.laydate;


					});
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
$(function() {
    var all_checked = false;
    $(":checkbox").click(function() {
        var table = $(this).parents("table");
        if($(this).attr("id") === "all") {
            table.find(":checkbox").prop("checked", !all_checked);
            all_checked = !all_checked;
        }
        else {
            table.find(":checkbox[id!=all]").each(function (i) {
                if(!$(this).is(":checked")) {
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
 
</head>


<body>
	<h3  style="margin-left:5%;margin-top:1%" class="glyphicon glyphicon-tasks">任务安排</h3>
	
	<hr />
		
		
		<div class="layui-tab layui-tab-card" style="width:95%;height:90%;min-width:600px;min-height:400px;margin-left:1%;">
  <ul class="layui-tab-title">
  <li  class="layui-this">人力情况</li>
    <li>所有项目</li> 
    
    
  </ul>
  <div class="layui-tab-content" style="height: 100px;">
      <div class="layui-tab-item layui-show">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
      <div  id="main"     style="height:400px"></div>
       <!-- ECharts单文件引入 -->
      <script src="script/js/echarts.js"></script>
        <script type="text/javascript">
               // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main')); 
       
        var option = {
    title : {
        text: '项目人力支出',
       
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['预期支出','实际支出']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'预期支出',
            type:'bar',
            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'实际支出',
            type:'bar',
            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
            markPoint : {
                data : [
                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]
};

        // 为echarts对象加载数据 
        myChart.setOption(option); 
       /*  myChart.setOption({
    title : {
        text: '项目人力支出',
       
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['预期支出','实际支出']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'预期支出',
            type:'bar',
            data:[],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'实际支出',
            type:'bar',
            data:[],
            markPoint : {
                data : [
                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]
});
myChart.showLoading();//数据加载完之前先显示一段简单的loading动画
					
					var nums = [];//数量数组，放在Y轴
					var nums1 = [];//数量数组，放在Y轴
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
      	 
								myChart.setOption({//加载数据图表
									
									series : [ {
										name : '预期支出',
										data : nums
									},{
										name : '实际支出',
										data : nums1
									} ]
								});
							
						},
         error : function(xhx,e,errMsg) {
         //请求失败时执行该函数
        
							console.log(errMsg);
							layer.msg("图表请求数据失败");
							myChart.hideLoading();
						}
      }); */
    </script>
    </div>
    <div class="layui-tab-item ">
 <table style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:80%;margin-left:5%;" class="table table-condensed table-bordered table-hover table-striped">
			<tr style="background-color:#4682B4;height:40px;">
			<td style="text-align:center;vertical-align:middle;width:40px;"><input type="checkbox" name="all" id="all" /></font></td>				
				<td><h4>项目名称</h4></td>
				<td><h4>客户名称</h4></td>
				<td><h4>项目等级</h4></td>
				<td><h4>测试经理</h4></td>
				<td><h4>测试阶段</h4></td>
				<td><h4>操作</h4></td>
			</tr>
		<s:iterator value="pageBean.list">
			<tr style="height:40px;">
			<td type="display:none;"><input type="checkbox" /><span style="font-size:0;overflow:hidden;"><s:property value="id"/></span></td>				
				
								
				<td><font color="black" size="2"><a href="Project_projectDetail?id=<s:property value="id"/>"><s:property value="projectName"/></a></font></td>
				<td><font color="black" size="2"><s:property value="customer"/> </font></td>
				<td><font color="black" size="2"><s:property value="level"/></font></td>
				<td><font color="black" size="2"><s:property value="vpmName"/></font></td>
				<td><font color="black" size="2"><a href="Project_plan?name=<s:property value="projectName"/>&phase=<s:property value="testPhase"/>"><s:property value="testPhase"/></a></font></td>
				<td><a 	onclick="updateRow(this);">编辑</a>|<a  onclick="javascript:window.location.href='Plan_showPlan?id=<s:property value='id'/>'" >项目计划</a></td>
	    		
			</tr>
			</s:iterator>
			<tr style="height:40px;">
			<td type="display:none;"><input type="checkbox" /><span style="font-size:0;overflow:hidden;"><s:property value="id"/></span></td>				
				
								
				<td><font color="black" size="2">1</font></td>
				<td><font color="black" size="2">2 </font></td>
				<td><font color="black" size="2">3</font></td>
				<td><font color="black" size="2">4</font></td>
				<td><font color="black" size="2">5</font></td>
				<td><a 	onclick="updateRow(this);">编辑</a>|<a  onclick="javascript:window.location.href='Plan_showPlan?id=<s:property value='id'/>'" >项目计划</a></td>
	    		
			</tr>
		</table>
		<table style="width:80%;margin-left:5%;">
				<tr>
					<td><span style="float:left;">共<span><s:property
								value="pageBean.totalPage" /></span>页|第<span><s:property
								value="pageBean.currentPage" /> </span>页</span>
					</td>
					<td><span style="float:right;"><form action="Project_showAllProject2" class="right-font08">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="Project_showAllProject2?page=1" class="right-font08">首页</a> | <a
									href="Project_showAllProject2?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="Project_showAllProject2?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="Project_showAllProject2?page=<s:property value="%{pageBean.totalPage}"/> "
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
							<button type="submit" class="layui-btn layui-btn-primary layui-btn-radius" style="height:30px;">确定</button>
						</form></span></td>
				</tr>
		      	</table>
  
    </div>
   
  </div>
</div>
	<div id="hid"></div>
		<div id="log_window" style="display:none;">
		<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			onclick="cancel_shield()">
			<span><font color="black">X</font></span>
		</div>
		  <form class="layui-form">
		<table style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;height:100%;width:100%;" class="table">
			<tr  style="background-color:#4682B4;">
				<td style="width:25%;"></td>
				<td><font color="black" size="3" style="float:left;">分配测试任务</font></td>
			</tr>
			
			<tr>
				<td>接收者：</td>
				<td><div class="layui-input-inline" style="float:left;">
								<select name="modules" lay-verify="required" lay-search=""style="width:150px;margin-left:1%;">
									<option value="">直接选择或搜索选择</option>
									<option value="1">罗绿梅</option>
									<option value="2">叶宇斌</option>
									<option value="3">何兵兵</option>
									<option value="4">王猛</option>
									<option value="5">李艳芳</option>
									<option value="6">任娟</option>
									<option value="7">曹粉粉</option>
									<option value="8">孙勇</option>
									<option value="9">杜思</option>
									
								</select>
							</div></td>
			</tr>
			<tr>
				<td>软件版本：</td>
				<td><input type="text" name=""  value="" class="layui-input"></td>
			</tr>
			<tr>
				<td>测试用例：</td>
				<td><div class="layui-input-inline" style="float:left;">
								<select name="modules" lay-verify="required"  style="width:150px;margin-left:1%;">
									<option value="">直接选择或搜索选择</option>
									<option value="1">相机</option>
									<option value="2">图库</option>
									<option value="3">设置</option>
								</select>
							</div></td>
			</tr>
			<tr>
				<td>用例条数：</td>
				<td><input type="text" name="" value=""class="layui-input"></td>
			</tr>
			
			<!-- 根据策略和计划从数据库里筛选显示模块 -->
			
			<tr>
				<td>开始时间:</td>
				<td><input type="text" name=""  class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly></td>
			</tr>
			<tr>
				<td>结束时间:</td>
				<td><input type="text" name="" lay-verify="" class="layui-input" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" readonly></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><input type="text" name=""  value=""class="layui-input"></td>
			</tr>
		<tr>
			<td style="background-color:#4682B4;" colspan="2">
				<div style="margin-left:5%">
					<input type="submit" value="提交" class="btn btn-primary" onclick="skip()" style="border:1px solid black;width:60px;">
					&nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="取消"
						onclick="cancel_shield()" class="btn btn-primary" style="border:1px solid black;width:60px;">
				</div>
			</td>
		</tr>
		
		</table>
		</form>
	</div>	
	
</body>
</html>

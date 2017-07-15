<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
  <script src="script/ie.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<style>
li {
	list-style: none;
}
</style>

<script>
	layui.use('element', function() {
		var element = layui.element();
	});
</script>
<script>
	layui.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

					});
					
					function check(){
					var a1=document.getElementsByName('')[0].value;
					var a2=document.getElementsByName('')[0].value;
					var a3=document.getElementsByName('')[0].value;
					var a4=document.getElementsByName('')[0].value;
					var a5=document.getElementsByName('')[0].value;
					var a6=document.getElementsByName('')[0].value;
					var a7=document.getElementsByName('')[0].value;
					var a8=document.getElementsByName('')[0].value;
					var a9=document.getElementsByName('')[0].value;
					var a10=document.getElementsByName('')[0].value;
					var a11=document.getElementsByName('')[0].value;
					var a12=document.getElementsByName('')[0].value;
					var a13=document.getElementsByName('')[0].value;
					if(a1==""||a1==null)
					{
					lay.msg("标题不能为空");
					return false;
					}else if(a2==""||a2==null)
					{
					lay.msg("紧急程度不能为空");
					return false;
					}
					else if(a3==""||a3==null)
					{
					lay.msg("申请人不能为空");
					return false;
					}
					else if(a4==""||a4==null)
					{
					lay.msg("申请小组不能为空");
					return false;
					}
					else if(a5==""||a5==null)
					{
					lay.msg("项目名称不能为空");
					return false;
					}
					else if(a6==""||a6==null)
					{
					lay.msg("测试阶段不能为空");
					return false;
					}
					else if(a7==""||a7==null)
					{
					lay.msg("设备名称不能为空");
					return false;
					}
					else if(a8==""||a8==null)
					{
					lay.msg("设备版本不能为空");
					return false;
					}
					else if(a9==""||a9==null)
					{
					lay.msg("设备协议不能为空");
					return false;
					}
					else if(a10==""||a10==null)
					{
					lay.msg("借用数量不能为空");
					return false;
					}
					else if(a11==""||a11==null)
					{
					lay.msg("起始日期不能为空");
					return false;
					}
					else if(a12==""||a12==null)
					{
					lay.msg("截止日期不能为空");
					return false;
					}
					else if(a13==""||a13==null)
					{
					lay.msg("借用事由不能为空");
					return false;
					}
					else{
					return true;
					}
					}
</script>
</head>
<body>

	<div class="layui-tab layui-tab-card" 
		style="width:80%;height:92%;min-width:600px;min-height:830px;margin-left:1%;border:1px solid #4682B4;">
		<ul class="layui-tab-title" style="background-color:#4682B4;border-left:1px solid #4682B4;">
			<li class="layui-this">设备申请</li>
			<li>流程图</li>
		</ul>
		<div class="layui-tab-content" >
			<div class="layui-tab-item layui-show" >
			
				<h4 class="glyphicon glyphicon-tasks" style="margin-left:45%;">设备资源预约申请</h4>
				<hr/>
				<form class="layui-form" action="" style="margin-left:2%;" onSubmit="return check();">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">标题</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">紧急程度</label>
							<div class="layui-input-block">
								<select name="">
									<option value=""></option>
									<option value="0">紧急</option>
									<option value="1">重要</option>
									<option value="2">一般</option>

								</select>
							</div>
						</div>
					</div>
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">申请人</label>
								<div class="layui-input-inline">
									<input type="text" name="" class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">申请小组</label>
								<div class="layui-input-inline">
									<input type="text" name="" class="layui-input">
								</div>
							</div>
						</div>
                        <div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">项目名称</label>
							<div class="layui-input-inline">
								<select name="" lay-verify="required" lay-search="">
									<option value="">直接选择或搜索选择</option>
									<option value="1">layer</option>
									<option value="2">form</option>
									<option value="3">layim</option>
									<option value="4">element</option>
									<option value="5">laytpl</option>
									<option value="6">upload</option>
									<option value="7">laydate</option>
									<option value="8">laypage</option>
									<option value="9">flow</option>
									<option value="10">util</option>
									<option value="11">code</option>
									<option value="12">tree</option>
									<option value="13">layedit</option>
									<option value="14">nav</option>
									<option value="15">tab</option>
									<option value="16">table</option>
									<option value="17">select</option>
									<option value="18">checkbox</option>
									<option value="19">switch</option>
									<option value="20">radio</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">测试阶段</label>
							<div class="layui-input-block">
								<select name="" lay-verify="required">
									<option value=""></option>
									<option value="0">EVT</option>
									<option value="1">DVT</option>
									<option value="2">PVT</option>
									<option value="3">MVT</option>
									<option value="4">MP</option>
								</select>
							</div>
						</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-inline">
								<label class="layui-form-label">设备负责人</label>
								<div class="layui-input-inline">
									<input type="text" name="" class="layui-input">
								</div>
							</div>
							<div class="layui-inline">
								<label class="layui-form-label">申请时间</label>
								<div class="layui-input-inline">
									<input type="text" name="" class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">设备名称</label>
							<div class="layui-input-inline">
								<select name="" lay-verify="required" lay-search="">
									<option value="">直接选择或搜索选择</option>
									<option value="1">layer</option>
									<option value="2">form</option>
									<option value="3">layim</option>
									<option value="4">element</option>
									<option value="5">laytpl</option>
									<option value="6">upload</option>
									<option value="7">laydate</option>
									<option value="8">laypage</option>
									<option value="9">flow</option>
									<option value="10">util</option>
									<option value="11">code</option>
									<option value="12">tree</option>
									<option value="13">layedit</option>
									<option value="14">nav</option>
									<option value="15">tab</option>
									<option value="16">table</option>
									<option value="17">select</option>
									<option value="18">checkbox</option>
									<option value="19">switch</option>
									<option value="20">radio</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">设备版本</label>
							<div class="layui-input-block">
								<select name="" lay-verify="required">
									<option value=""></option>
									<option value="0">V1.0</option>
									<option value="1">V2.0</option>
									<option value="2">V3.0</option>
									<option value="3">V4.0</option>

								</select>
							</div>
						</div>
					</div>
					<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">设备协议</label>
						<div class="layui-input-inline">
							<select name="" lay-verify="required" lay-search="">
								<option value="">直接选择或搜索选择</option>
								<option value="1">layer</option>
								<option value="2">form</option>
								<option value="3">layim</option>
								<option value="4">element</option>
								<option value="5">laytpl</option>
								<option value="6">upload</option>
								<option value="7">laydate</option>
								<option value="8">laypage</option>
								<option value="9">flow</option>
								<option value="10">util</option>
								<option value="11">code</option>
								<option value="12">tree</option>
								<option value="13">layedit</option>
								<option value="14">nav</option>
								<option value="15">tab</option>
								<option value="16">table</option>
								<option value="17">select</option>
								<option value="18">checkbox</option>
								<option value="19">switch</option>
								<option value="20">radio</option>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">借用数量</label>
						<div class="layui-input-inline">
							<input type="text" name="" class="layui-input">
						</div>
					</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">起始日期</label>
					<div class="layui-input-inline">
						<input type="text" name="" class="layui-input" id="LAY_demorange_s" readonly>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">截止日期</label>
					<div class="layui-input-inline">
						<input type="text" name="" class="layui-input" id="LAY_demorange_e" readonly>
					</div>
				</div>
			</div>

			
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">借用事由</label>
				<div class="layui-input-block">
					<textarea placeholder="请输入内容" name="" class="layui-textarea" style="width:600px;"></textarea>
				</div>
			</div>
	
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">备注</label>
			<div class="layui-input-block">
				<textarea placeholder="请输入内容" name="" class="layui-textarea" style="width:600px;"></textarea>
			</div>
		</div>
	
	</form>
	<button class="layui-btn layui-btn-normal layui-btn-radius" type="submit" style="float:right;">确定</button>
	</div>
	
	<div class="layui-tab-item"><img src="images/liucheng.png"></div>
	</div>
	
	<script>
	layui.use('laydate', function(){
  var laydate = layui.laydate;
  
  var start = {
    min: laydate.now()
    ,max: '2099-06-16 23:59:59'
    ,istoday: false
    ,istime: true
    ,choose: function(datas){
      end.min = datas; //开始日选好后，重置结束日的最小日期
      end.start = datas //将结束日的初始值设定为开始日
    }
  };
  
  var end = {
    min: laydate.now()
    ,max: '2099-06-16 23:59:59'
    ,istoday: false
    ,istime: true
    ,choose: function(datas){
      start.max = datas; //结束日选好后，重置开始日的最大日期
    }
  };
  
  document.getElementById('LAY_demorange_s').onclick = function(){
    start.elem = this;
    laydate(start);
  }
  document.getElementById('LAY_demorange_e').onclick = function(){
    end.elem = this
    laydate(end);
  }
 
});
	
		function show() {
			document.getElementById("time").value = document
					.getElementById("h").value
					+ "时" + document.getElementById("min").value + "分"
		}
	</script>
	<script>
		function show1() {
			document.getElementById("time1").value = document
					.getElementById("h1").value
					+ "时" + document.getElementById("min1").value + "分"
		}
	</script>
</body>
</html>

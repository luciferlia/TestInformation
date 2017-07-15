<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.wind.util.*"%>
<%@ page import="com.wind.entity.Plan"%>
<%@ page import="com.wind.action.PlanAction"%>
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

<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.js"></script>
<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="script/JSON/json2.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<script src="script/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<style>
.layui-form-label {
	width: 120px;
}

td {
	font-size: 0.9em;
}
</style>
<script>
	layui.use('element', function() {
		var element = layui.element();
	});
</script>
<script>
	layui.use([ 'form', 'layedit', 'laydate' ],
		function() {
			var form = layui.form(),
				layer = layui.layer,
				layedit = layui.layedit,
				laydate = layui.laydate;
		});
</script>
  
<script>
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

function show(){
var sel=document.getElementById('schoolId');
var inp=document.getElementById('input1');
var school=sel.value;
inp.value=school;
}

function check(){
var a=document.getElementsByName('plan.phase')[0].value;
var b=document.getElementsByName('plan.version')[0].value;
var c=document.getElementsByName('plan.versionReleasetime')[0].value;
var d=document.getElementsByName('plan.caseCount')[0].value;
var e=document.getElementsByName('plan.humanInput')[0].value;
var f=document.getElementsByName('plan.humanCount')[0].value;
var la=document.getElementsByName('plan.prototypeCount')[0].value;
var h=document.getElementsByName('plan.prototypeReuse')[0].value;
var i=document.getElementsByName('plan.versionPath')[0].value;
var j=document.getElementsByName('plan.startTime')[0].value;
var k=document.getElementsByName('plan.endTime')[0].value;
//alert(a);
if(a==""||a==null)
{
layer.msg("阶段不能为空");
return false;
}
else if(b==""||b==null)
{
layer.msg("版本不能为空");
return false;
}
else if(c==""||c==null)
{
layer.msg("版本发布时间不能为空");
return false;
}
else if(d==""||d==null)
{
layer.msg("用例条数不能为空");
return false;
}
else if(e==""||e==null)
{
layer.msg("人力投入不能为空");
return false;
}
else if(f==""||f==null)
{
layer.msg("人数安排不能为空");
return false;
}
else if(la==""||la==null)
{
layer.msg("测试机需求不能为空");
return false;
}
else if(h==""||h==null)
{
layer.msg("测试机复用不能为空");
return false;
}
else if(i==""||i==null)
{
layer.msg("版本路径不能为空");
return false;
}
else if(j==""||j==null)
{
layer.msg("开始时间不能为空");
return false;
}
else if(k==""||k==null)
{
layer.msg("结束时间不能为空");
return false;
}
else
{
return true;
}
} 
</script> 
</head>
<body>
 <table id="table1" style="display:none;">
		<tr>
			<td>分类</td>
			<td>用例模块</td>
		</tr>
		<c:forEach items="${showCaseForms }" var="scf">
		<tr>
			<td>${scf.caseType }</td>
			<td><c:forEach items="${scf.caseNum }" var="cn">${cn.caseStore },${cn.num },${cn.caseTime }|<br></c:forEach></td>
			
		</tr>
		</c:forEach>
	</table>
    <div style="width:70%;heigth:100%;margin-top:3%;margin-left:5%;border:2px solid #4682B4;min-width:700px;">
    <table style="width:80%;"><tr style="heigth:40px;background-color:#4682B4;"><td></td></tr></table>
	<form action="Plan_updatePlan" method="post" class="layui-form" onSubmit="return check();"
		style="margin-left:5%;margin-top:5%;width:80%;">
		<input type="hidden" name="id" value="${projectId }">
				
			<input type="hidden" name="plan.planId"
					value="${planForm.plan.planId }">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">项目名称：</label>
							<div class="layui-input-inline">
								<input type="text" name="" value="${planForm.plan.project.projectName }"
									class="layui-input" disabled="false">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">项目阶段：</label>
							<div class="layui-input-inline">
								<input type="text" name="plan.phase" value="${planForm.plan.phase }"
									class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">软件版本：</label>
							<div class="layui-input-inline">
								<input type="text" name="plan.version" value="${planForm.plan.version }"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">版本发布时间：</label>
							<div class="layui-input-inline">
								<input type="text" name="plan.versionReleasetime"
									value="${planForm.plan.versionReleasetime }" class="layui-input"
									onclick="layui.laydate({elem: this, festival: true})" readonly>
							</div>
						</div>

					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">测试类型：</label>
							<div class="layui-input-inline" >
								<select  name="plan.casetype.casetypeName" id="schoolId" style="width:190px;height:37px;border:1px solid #DCDCDC" lay-ignore>
									<%-- <option value="${planForm.plan.casetype.id}" >${planForm.plan.casetype.casetypeName}</option> --%>
										<c:forEach items="${showCaseForms }" var="scf">
											<c:if test="${scf.caseType==planForm.plan.casetype.casetypeName }">
											<option value="${scf.caseType}" selected="selected">${scf.caseType}</option>
											</c:if>
											<c:if test="${scf.caseType!=planForm.plan.casetype.casetypeName }">
											<option value="${scf.caseType}">${scf.caseType}</option>
											</c:if>
									</c:forEach>
								</select>
			                
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">测试内容：</label>
							<div class="layui-input-inline">
						       
								<input type="text" name="testContent" style="width:190px;height:37px;border:1px solid #DCDCDC" value="<c:forEach items='${planForm.planCasestores}' var='pc'>${pc.casestore.testModule },</c:forEach>" id="classId" onclick="showwe(this)">
                        </div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">用例条数：</label>
							<div class="layui-input-inline">
									<input type="text" id="studentId" name="plan.caseCount" value="${planForm.plan.caseCount }" class="layui-input">
							</div>
						</div>
						<%-- <div class="layui-inline">
							<label class="layui-form-label">版本类型：</label>
							<div class="layui-input-inline">
								<input type="text" name="plan.versionType"
									value="${planForm.plan.versionType }" class="layui-input">
							</div>
						</div> --%> 
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">人力投入(人.天)：</label>
							<div class="layui-input-inline">
								<input type="text" name="plan.humanInput" id="time"
									value="${planForm.plan.humanInput }" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">人数安排：</label>
							<div class="layui-input-inline">
								<input type="text" name="plan.humanCount"
									value="${planForm.plan.humanCount }" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">测试机需求(台)：</label>
							<div class="layui-input-inline">
								<input type="text" name="plan.prototypeCount"
									value="${planForm.plan.prototypeCount }" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">测试机复用(台)：</label>
							<div class="layui-input-inline">
								<input type="text" name="plan.prototypeReuse"
									value="${planForm.plan.prototypeReuse }" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">开始时间：</label>
							<div class="layui-input-inline">
								<input type="text" name="plan.startTime"
									value="${planForm.plan.startTime }" class="layui-input"
									onclick="layui.laydate({elem: this, festival: true})" readonly>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">结束时间：</label>
							<div class="layui-input-inline">
								<input type="text" name="plan.endTime" value="${planForm.plan.endTime }"
									class="layui-input"
									onclick="layui.laydate({elem: this, festival: true})" readonly>
							</div>
						</div>
					</div>
					<%-- <div class="layui-form-item layui-form-text">
						<label class="layui-form-label">版本路径：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width: 700px;" name="plan.versionPath" value="${planForm.plan.versionPath }">${planForm.plan.versionPath }</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">版本计划：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width:700px;" name="plan.versionPlan" value="${planForm.plan.versionPlan }">${planForm.plan.versionPlan }</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">下载工具：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width:700px;" name="plan.loadTool" value="${planForm.plan.loadTool}">${planForm.plan.loadTool}</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">归档路径：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width:700px;" name="plan.filePath" value="${planForm.plan.filePath }">${planForm.plan.filePath }</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">BUG库路径：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width:700px;" name="plan.bugPath" value="${planForm.plan.bugPath }">${planForm.plan.bugPath }</textarea>
						</div>
					</div>--%>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">备注：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width:525px;" name="plan.remark" value="${planForm.plan.remark }">${planForm.plan.remark }</textarea>
						</div>
					</div> 
		
				<button type="submit" style="margin-left:70%;"
						class="layui-btn layui-btn-normal layui-btn-radius">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<!-- <button type="reset" href="wind/usecase.jsp" target="mainFrame"
						class="layui-btn layui-btn-normal layui-btn-radius">返回</button> -->
			
	</form>
	
	</div>
	<div id="choose" style="display:none;"></div>
	<script>
	function show1(){
		  cities = new Object();
		 	//cities['入口测试'] = new Array('接收测试', '可生产测试');
		 var rows = document.getElementById("table1").rows.length; 

		 for(var i=2;i<rows+1;i++)
		 {

		 var caseType=$("tr:eq('"+i+"') td:eq(0)").text();
		 var caseStore=$("tr:eq('"+i+"') td:eq(1)").text();
		 var city =[];
		 var strs= new Array(); //定义一数组 
		 cities[caseType] = new Array()
		  strs=caseStore.split("|"); //字符分割 
		  for(var j=0;j<strs.length-1;j++)
		  {
		  var data=strs[j];
		  var strs1= new Array();
		  strs1=data.split(","); 
		   cities[caseType].push(strs1[0]);
		  }

		 }
        return cities;
		}
	function showwe(obj){
	   var cities=show1();
	   //console.log(cities);
		var pv;
		var i;
        var first;
		
		first=document.getElementById('schoolId');
		
		
	    pv = first.value;
		//alert(pv);

		//ne.value=pv;
		if (pv == '0')
			return;
		if (typeof (cities[pv]) == 'undefined')
			return;
		var chkhtml=[];
		for (i = 0; i < cities[pv].length; i++) {
			if((i+1)%5==0)
			{
               chkhtml.push('<span class="mytest"><input type="checkbox" name="checkedId" value="'+cities[pv][i]+'"/>'+cities[pv][i]+'</span><br/>');
			}
			else{
			chkhtml.push('<span class="mytest"><input type="checkbox" name="checkedId" value="'+cities[pv][i]+'"/>'+cities[pv][i]+'</span>');
			}
		    $("#choose").html(chkhtml.join(''));			
		}
		var ss=obj.value;
		var va=ss.split(",");
		var box=document.getElementsByName("checkedId");
		for(var i=0;i<va.length;i++)
		{
		for(j=0;j<box.length;j++){

		if(box[j].value==va[i])
		{
		box[j].checked=true;
		break;
		}
		}
		}
		layer.open({
			type: 1,
			title:'请选择需要的测试模块',
			skin: 'layui-layer-rim', //加上边框
			area: ['650px', '310px'], //宽高
			content:$("#choose"),
				btn: ['确定', '取消']
           ,yes: function(){
		    var checkedSubject = $('input[name=checkedId]:checkbox:checked');
	var checkedIds = "";
	//循环获取选中的复选框的value，这个value是数据表中每条记录的主键，传给后台，后台就能根据主键查找到数据表的相应记录
	//将其value用逗号隔开拼接成一个字符串
	checkedSubject.each(function() {
		checkedIds = checkedIds + $(this).val()+ "," ;
	});
	 var rows = document.getElementById("table1").rows.length; 
	 var sum=0;
	 var time=0;
	 var tt=obj.parentNode;
	 var tr=tt.parentNode;
		 var va=checkedIds.split(",");
	 for(var i=1;i<rows;i++)
	 {
	 var caseStore=$("tr:eq('"+i+"') td:eq(1)").text();
	 var city =[];
	 var strs= new Array(); //定义一数组 
	  strs=caseStore.split("|"); //字符分割 
	  for(k=0;k<va.length;k++){
	  for(var j=0;j<strs.length-1;j++)
	  {
	 /* var data=strs[j];
	  var strs1= new Array();
	  strs1=data.split(","); */		
		  var strs1=strs[j].split(",");
		  
	
		  if(va[k]==strs1[0])
		  {		
			 // alert(va[k]);
    	/*$(tr).children('td').eq(6).children('input').val(strs1[1]);*/
			  sum +=parseInt(strs1[1]);
			   time +=parseFloat(strs1[2]);
			  break;
	}}}
	  }//alert(sum);
	 var dk=changeTwoDecimal(time);
	  $('#studentId').val(sum);
	  	  var x=parseInt(dk);
	  var y=dk-x;
	  var m;
	  if(dk>0&&dk<=0.7)
		  {
		  m=0.5;
		  
		  }else if(dk==0)
			  {
			  m=0;
			 
			  }else if(dk>0.7&&y>0.7){
				  m=x+1;
			  }else if(dk>0.7&&y>0.2&&y<=0.7)
				  {
				  m=x+0.5;
				  }
			  else{
				  m=x;
			  }
	   $('#time').val(m);
                         layer.closeAll();
				obj.value=checkedIds;		 
           }
           ,btn2: function(){
           layer.closeAll();
          }					
		  });
		
	}
	</script>
</body>
</html>

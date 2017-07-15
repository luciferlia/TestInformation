<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
  <script src="script/ie.js"></script>

    <jsp:include page="/wind/top.jsp"></jsp:include>

<style type="text/css">
#tablel td {
	font-size: 0.9em;
	font-color: black;
	border: 1px solid #EDEDED;
	text-align: center;
	vertical-align: middle;
	WORD-WRAP: break-word
}

 #table td{border:1px solid #EDEDED;font-size:0.9em;}
td{font-size:0.9em;text-align: center;vertical-align:middle;font-color: black;   vertical-align: middle !important;}
a{font-size:0.9em;}
a:hover{color:red;}
li {
	list-style: none;
	float:left;
}
#tablel input {
	border: 1px solid white;
}

.table input {
	width: 150px;
	height:40px;
	
}
.mytest{


display:-moz-inline-box;

display:inline-block;

width:125px;
font-size:0.8em;
 }
</style>
<script>
layui.use('layer', function(){
  var layer = layui.layer;
 
});  
function edit(){
var a=document.getElementById('table');
var b=document.getElementById('tablel');
var c=document.getElementById('table3');
var d=document.getElementById('button1');
var e=document.getElementById('button2');
var f=document.getElementById('button3');

$(a).hide();
$(b).show();
$(c).hide();
$(d).hide();
$(e).show();
$(f).show();
}
function hidee(){
var a=document.getElementById('table');
var b=document.getElementById('tablel');
var c=document.getElementById('table3');
var d=document.getElementById('button1');
var e=document.getElementById('button2');
var f=document.getElementById('button3');

$(a).show();
$(b).hide();
$(c).show();
$(d).show();
$(e).hide();
$(f).hide();
}

</script>
<script type="text/javascript">  
    
    function MergeCell(tableId, startRow, endRow, col) {  
      
        var tb = document.getElementById(tableId);  
         var ch = document.getElementById('ch');
         ch.style.display='block'; 
          var he = document.getElementById('he');
         he.style.display='none';  
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
 
 function validation(planId,projectId){
 
		var bflag=true;
		$.ajax({
             type: "POST",
            url: "valiPlan?planId="+planId, //这里你根据后台地址改
         success: function(data){
           
            if(data=='success'){
            	bflag=true;
            	window.location.href="Plan_updatePlanPre?id="+planId+"&projectId="+projectId;
            }else{
            	layer.msg('该计划已被分配出去，不能进行编辑!',{time:3000});
            	bflag=false;
            
            }
         },
         error : function(e) {
           layer.msg('验证权限失败!',{time:1000});
         }
      });
	
	return bflag;
	
	}
</script> 
<script>
 
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
 
	
	   function addEditPlan(){
	  
      var data = savePlan(); //获取要传到后台的数据 json 格式
     
      var sum=0;
      
       var a=document.getElementsByName('status');
        var b=document.getElementsByName('version');
         var c=document.getElementsByName('pushtime');
          var d=document.getElementsByName('modaul');
           var e=document.getElementsByName('test');
            var f=document.getElementsByName('count');
             var g=document.getElementsByName('startime');
              var h=document.getElementsByName('endtime');
      for(var i=0;i<a.length;i++)
      {
      var ver=a[i].value;
      var ver1=b[i].value;
      var ver2=c[i].value;
      var ver3=d[i].value;
      var ver4=e[i].value;
      var ver5=f[i].value;
      var ver6=g[i].value;
     var ver7=h[i].value;
      
     if(ver==""||ver==null || ver1==""||ver1==null || ver2==""||ver2==null || ver3==""||ver3==null|| ver4==""||ver4==null || ver5==""||ver5==null|| ver6==""||ver6==null || ver7==""||ver7==null)
    {
    var check=1;
    }else{
    
    check=0;}
    sum +=check;
      }
      if(sum!=0)
      
      {
      layer.msg("值不能为空");
      }
      else
      { layer.msg('加载中', {
  icon: 16
  ,shade:0.5,time:5000000
});
            $.ajax({
             type: "POST",
            url: "editPlan?id="+${id}, //这里你根据后台地址改,id为项目编号
         data:{"params":JSON.stringify(data)},
		dataType:"json",
         success: function(data){
            //console.log(data)
            window.location.reload();
            layer.msg('编辑项目计划成功!',{time:1000});
         },
         error : function(e) {
           layer.msg('编辑项目计划失败!',{time:1000});
           console.log(e);
         }
      });
  }
          
   
   }

   function savePlan(){
      var array=[];
      $("#tablel tr").each(function(){
         var json = {};
         $(this).find("input").each(function (i){
            json[""+$(this)[0].name+""]=$(this)[0].value;
         });
         if(!isEmptyObject(json)){
           
            array.push(json);
         }
      });
    
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
	layui.use([ 'form', 'layedit', 'laydate' ],function() {
						var form = layui.form(), 
						layer = layui.layer, 
						layedit = layui.layedit, 
						laydate = layui.laydate;


					});
					function delete1(obj) {
			
			var tr = obj.parentNode.parentNode;
			var id=$(tr).children('td').eq(13)[0].children[0].value;
		     layer.confirm('确定删除此条计划?', {
			icon : 3,
			title : '提示'
		}, function(index) {
			layer.close(index);
		     if(id==""||id==null)
		    	 {
		    	 var tbody = tr.parentNode;
		    	 tbody.removeChild(tr);
		    	 layer.msg('删除成功',{time:1000});
		    	 }
		     else{
		    	 //进入数据库删
		    	 
		    	 $.ajax({
		 			type:"post",
		 			url:"delPlan?planId="+id,
		 			
		 			success:function(data){
		 				
		 				if(data=='success'){
		 				var tbody = tr.parentNode;
		 		    	 tbody.removeChild(tr);
		 				layer.msg('删除成功',{time:1000});
		 				}else if(data=='fail'){
		 				layer.msg('该计划已分配，不能被删除',{time:1000});
		 				}else{
		 					layer.msg('删除失败,',{time:1000});
		 				}
		 				 
		 			},
		 			error:function(xhr,status,errMsg){
		 			alert(errMsg);
		 				layer.msg('删除失败',{time:1000});
		 			}
		 		});
		 	}
			}, function(index) {
			layer.close(index);
			return false;
		});
	}
			
		
		function delet(vall,param){
layer.confirm('确定删除此条计划?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               
                             $.ajax( {

       async : false,

       cache : false,

       type : 'POST',

       url :"Plan_deletePlan?id="+vall+"&projectId="+param,// 请求的action路径      href="Plan_deletePlan?id=${planForm.plan.planId }&projectId=${id}"

       success : function(data) { // 请求成功后处理函数。
      if(data=="success"){
      layer.msg("删除成功");
       window.location.reload();
      }else if(data=="fail"){
      	layer.msg("该计划已被分配，不能被删除");
      }
      
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
$(document).ready(function() { 
var sum1=0;
var sum2=0;
var sum3=0;
var sum4=0;
var sum5=0;
var tb=document.getElementById('table');
 var row=document.getElementById('table').rows.length;
 var cell= document.getElementById('table').rows[0].cells.length;
 
				for(var i=1;i<row-1;i++){	
				if($("#table tr:eq('"+i+"') td:eq(5)").text()==null||$("#table tr:eq('"+i+"') td:eq(5)").text()==''){
continue;
}			
				sum1 += parseFloat($("#table tr:eq('"+i+"') td:eq(5)").text());
				if($("#table tr:eq('"+i+"') td:eq(6)").text()==null||$("#table tr:eq('"+i+"') td:eq(6)").text()==''){
continue;
}			
				sum2 += parseFloat($("#table tr:eq('"+i+"') td:eq(6)").text());	
				
				if($("#table tr:eq('"+i+"') td:eq(7)").text()==null||$("#table tr:eq('"+i+"') td:eq(7)").text()==''){
continue;
}			
				sum3 += parseFloat($("#table tr:eq('"+i+"') td:eq(7)").text());	
				if($("#table tr:eq('"+i+"') td:eq(8)").text()==null||$("#table tr:eq('"+i+"') td:eq(8)").text()==''){
continue;
}			
				sum4 += parseFloat($("#table tr:eq('"+i+"') td:eq(8)").text());
					if($("#table tr:eq('"+i+"') td:eq(9)").text()==null||$("#table tr:eq('"+i+"') td:eq(9)").text()==''){
continue;
}			
				sum5 += parseFloat($("#table tr:eq('"+i+"') td:eq(9)").text());	
			
			if($("#table tr:eq('"+i+"') td:eq(14)").text()=='已完成'){
			$("#table tr:eq('"+i+"')").css('background-color','#A9A9A9');
			
	        $("#table tr:eq('"+i+"') td:eq(15) a:eq(0)").removeAttr('onclick');
	          $("#table tr:eq('"+i+"') td:eq(15) a:eq(1)").removeAttr('onclick');
	          $("#table tr:eq('"+i+"') td:eq(1) a:eq(0)").removeAttr('href');//去掉a标签中的href属性
		
			}
					
}
if(cell==15)
{
$('#totalRow').append('<td>合计</td><td>/</td><td>/</td><td>/</td><td>/</td><td>'+sum1+'</td><td>'+changeTwoDecimal(sum2)+'</td><td>'+sum3+'</td><td>'+sum4+'</td><td>'+sum5+'</td><td>/</td><td>/</td><td>/</td><td>/</td><td>/</td>');
}
else{
$('#totalRow').append('<td>合计</td><td>/</td><td>/</td><td>/</td><td>/</td><td>'+sum1+'</td><td>'+changeTwoDecimal(sum2)+'</td><td>'+sum3+'</td><td>'+sum4+'</td><td>'+sum5+'</td><td>/</td><td>/</td><td>/</td><td>/</td>');} 
});

		
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
			<td>${scf.caseType}</td>
			<td><c:forEach items="${scf.caseNum }" var="cn">${cn.caseStore },${cn.num },${cn.caseTime }|<br></c:forEach></td>
		</tr>
		</c:forEach>
	</table>
<a onclick="pageScroll()"
		style="display:block; position:fixed; right:5px; bottom:20px;"><img
		src="images/top.jpg"></a>
	
   <h4 class="glyphicon glyphicon-tasks" style="margin-left:1%;">
		项目计划信息
	</h4>
	<table style="width:98%;margin-left:1%;margin-top:1%;"><tr><td>
	<ul>
		<li style="float:left;">
				
				<li style="float:left;"><c:if test="${not empty pf.allEdit }"><button
				onclick="edit()" id="button1"
				class="btn btn-primary">${pf.allEdit }</button></c:if></li>
				<li style="float:left;"><button
				onclick="hidee()"  id="button2" style="display:none"
				class="btn btn-primary">取消</button></li>
				
				<li style="float:left;"><c:if test="${not empty pf.add }"><button
				onclick="javascript:window.location.href='projectUser_showUser?id=${id}'"
				class="btn btn-primary">${pf.add }</button></c:if></li> 
				<li style="float:left;">
					<button type="button" class="btn btn-primary" id="he" onclick="MergeCell('cdTb', 0, 0, 0)">合并</button>
			</li>
			<li style="float:left;">
					<button type="button" id="ch" class="btn btn-primary" style="display:none;" onclick="javascript:window.location.reload();">取消合并</button>
			</li>
			<span style="float:right;">
			<li style="float:left;"><input type="text" id="search"
					class="form-control" placeholder="输入任意字搜索" style="width:200px;"></li>
			
		</span>
	</ul></td></tr></table>
	<hr />
		<span style="font-size:1.2em;margin-left:45%;">
		<% int i=0; %>
		<c:forEach items="${pageBean.list }" var="planForm">
			<% if(i==0){ %>
			${planForm.plan.project.projectName }
			<%} i=1; %>
		</c:forEach>项目测试计划
		<span>
	<table  id="table"
		style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;margin-left:1%;width:98%;min-width: 1000px;"class="table  table-bordered table-hover table-striped">
		<tr style="background-color:#4682B4;height:40px;color: white;">
			
			<td style="width:5%;min-width:50px;"><h4>项目阶段</h4> </td>
			<td style="width:5%;min-width:50px;">软件版本</td>
			<td style="width:6%;min-width:70px;">版本发布时间</td>
			
			<td style="width:6%;min-width:50px;">测试类型</td>
			<td style="width:15%;min-width:400px;">测试内容</td>
			<td style="width:5%;">用例条数</td>
			<td style="width:6%;">人力投入(人.天)</td>
			<td style="width:5%;">人数安排</td>
			<td style="width:6%;">测试机需求(机器/台)</td>
			<td style="width:6%;">测试机复用(机器/台)</td>
			<!-- <td style="width:5%;">复用类型</td> -->
			<td style="width:6%;min-width:100px;">开始时间</td>
			<td style="width:6%;min-width:100px;">结束时间</td>
			<td><h4>备注 </h4></td>
			<td>状态</td>
			<c:if test="${not empty pf.edit||not empty pf.delete }">
			<td style="width:6%;min-width:100px;"><h4>操作</h4></td>
			</c:if>
			
		</tr>
		<tbody id="cdTb">

		<c:forEach items="${pageBean.list }" var="planForm">
			<tr>

				<td>${planForm.plan.phase }</td>
				<td><c:if test="${not empty pf.update }"><a href="Plan_teamtask?versionName=${planForm.plan.version }&id=${id}">${planForm.plan.version }</a></c:if><c:if test="${empty pf.update }">${planForm.plan.version }</c:if></td>
				<td>${planForm.plan.versionReleasetime }</td>
				
				<td>${planForm.plan.casetype.casetypeName }</td>
				<td><a onclick="checkPlan(${planForm.plan.planId})"><c:forEach items="${planForm.planCasestores }" var="pc"><c:if test="${pc.tester=='ather' }"><font color="red">${pc.casestore.testModule },</font> </c:if><c:if test="${pc.tester=='my' }"><font color="green"> ${pc.casestore.testModule },</font></c:if> <c:if test="${pc.tester!='my'&&pc.tester!='ather' }"> ${pc.casestore.testModule },</c:if></c:forEach></a></td>
				<td>${planForm.plan.caseCount }</td>
				<td>${planForm.plan.humanInput }</td>
				<td>${planForm.plan.humanCount }</td>
				<td>${planForm.plan.prototypeCount }</td>
				<td>${planForm.plan.prototypeReuse } </td>
			<%-- 	<td>${planForm.plan.prototypeType } </td> --%>
				<td>${planForm.plan.startTime }</td>
				<td>${planForm.plan.endTime }</td>
				<td style="overflow:hidden;text-overflow: ellipsis;white-space: nowrap;" onmouseover="showmark(this,event);">${planForm.plan.remark }</td>
				<td>${planForm.status }</td>
				<c:if test="${not empty pf.edit||not empty pf.delete }"><td><c:if test="${not empty pf.edit }"><a onclick="validation(${planForm.plan.planId},${id });">${pf.edit }</a></c:if><c:if test="${not empty pf.delete }">|<a onclick="delet(${planForm.plan.planId },${id })">${pf.delete }</a></c:if></td></c:if>
		
		</tr>
		</c:forEach>
		</tbody>
	   <tr id="totalRow"></tr></table>
	   <form action="Plan_showPlan" class="right-font08">
	 		<table style="width:98%;margin-left:20px;" id="table3">
				<tr>
					<td><span style="float:left;">共<s:property
								value="pageBean.totalPage" />页|第<s:property
								value="pageBean.currentPage" /> 页</span>
					</td>
					<td><span style="float:right;">
							[
							<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
							<s:else>
								<a href="Plan_showPlan?page=1" class="right-font08">首页</a> | <a
									href="Plan_showPlan?page=<s:property value="%{pageBean.currentPage-1}"/> "
									class="right-font08">上一页</a>
							</s:else>
							|
							<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
								<a
									href="Plan_showPlan?page=<s:property value="%{pageBean.currentPage+1}"/> "
									class="right-font08">下一页</a> | <a
									href="Plan_showPlan?page=<s:property value="%{pageBean.totalPage}"/> "
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
							<!-- <input type="text" style="width:50px;" name="page" /> -->
							<input type="submit" class="btn btn-primary btn-xs"
								style="border:1px solid black;"
								value="确定">
						</span></td>
				</tr>
			</table> 
			</form>
			
       <table id="tablel" style="margin-left:1%;width:98%;display:none;" >
		<tr style="background-color:#4682B4;height:40px;color: white;">
			<td style="width:4%;min-width:50px;"><h4>项目阶段</h4> </td>
			<td style="width:4%;min-width:50px;">软件版本</td>
			<td style="width:5.5%;min-width:70px;">版本发布时间</td>
			
			<td style="width:6%;min-width:50px;">测试类型</td>
			<td style="min-width:150px;">测试内容</td>
			<td style="width:4%;">用例条数</td>
			<td style="width:5%;">人力投入(人.天)</td>
			<td style="width:4%;">人数安排</td>
			<td style="width:6%;">测试机需求(机器/台)</td>
			<td style="width:6%;">测试机复用(机器/台)</td>
			
			<td style="width:5.5%;min-width:100px;">开始时间</td>
			<td style="width:5.5%;min-width:100px;">结束时间</td>
			<td><h4>备注 </h4></td>
			<td style="display:none;"></td>
			<td style="width:6%;min-width:100px;"><h4>操作</h4></td>
			<td style="display:none;">标识</td>
		</tr>
			<c:forEach items="${pageBean.list }" var="planForm">
				<tr>
				<td >
				<div style="width:60px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				<select name="first"  onChange="set_modaul(this)" style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" >
				<option selected="selected" value="请选阶段">请选阶段</option>
				<option  value="EVT">EVT</option>
				<option value="DVT">DVT</option>
				<option value="PVT">PVT</option>
				<option value="MVT">MVT</option>
				<option value="MP">MP</option>
				</select>


				<input type="text" name="status" style="width:80%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${planForm.plan.phase }"/>

                 </div></td>
				<td><input type="text" name="version" value="${planForm.plan.version }" class="layui-input" onclick="changeFlag(this);"></td>
				<td><input type="text" name="pushtime"  value="${planForm.plan.versionReleasetime }"
					class="layui-input" onclick="layui.laydate({elem: this, festival: true});changeFlag(this);" readonly></td>
				
				<td>	
				<div style="width:150px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				<select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px"
					onChange="set_modaul(this);" >
						 <option value="选择模块" selected="selected">选择模块</option>
						<c:forEach items="${showCaseForms }" var="scf">						
						<option value="${scf.caseType}">${scf.caseType}</option>
						</c:forEach>
				</select>
				<input type="text" name="modaul" style="width:90%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${planForm.plan.casetype.casetypeName }"/>
                 </div></td>
                 <td>
				
				<input type="text" name="test" style="width:100%;height:50px;" onclick="showt(this)" value="<c:forEach items='${planForm.planCasestores }' var='pc'>${pc.casestore.testModule },</c:forEach>"/>
				</td>
				<td><input type="text" name="count"  value="${planForm.plan.caseCount }" class="layui-input" onkeyup="sounts(this)" onclick="changeFlag(this);"></td>
				<td><input type="text" name="humannum" value="${planForm.plan.humanInput }" class="layui-input" onkeyup="sount(this)" onclick="changeFlag(this);"></td>
				<td><input type="text" name="humancount" value="${planForm.plan.humanCount }" class="layui-input" onkeyup="sounts(this)" onclick="changeFlag(this);"></td>
				<td><input type="text" name="pcount" value="${planForm.plan.prototypeCount }" class="layui-input" onkeyup="sounts(this)" onclick="changeFlag(this);"></td>
				<td><input type="text" name="preuse" value="${planForm.plan.prototypeReuse }" class="layui-input" onkeyup="sounts(this)" onclick="changeFlag(this);"></td>
				
				<td><input type="text" name="startime" value="${planForm.plan.startTime }"
					class="layui-input" onmouseover="auto(this)"
					onclick="layui.laydate({elem: this, festival: true});changeFlag(this);" readonly></td>
				<td><input type="text" name="endtime" value="${planForm.plan.endTime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true});changeFlag(this);" readonly></td>
				<td>
						<input placeholder="请输入内容" name="remark" class="layui-input" value="${planForm.plan.remark }"  onclick="changeFlag(this);"
							style="min-height:50px;">
					</td>
					<td style="display:none;"><input type="hidden" name="planId" value="${planForm.plan.planId }"/></td>
				<td><a onclick="delete1(this)">删除</a>  |
						<a onclick="add(this)">增加</a></td>
							<td style="display:none;"><input type="hidden" name="Flag" value="0"/></td>
			</tr>
			</c:forEach>
	</table>
 <a type="button" style="margin-left:90%;display:none;width:70px;" onclick="addEditPlan()" id="button3" 
		class="layui-btn layui-btn-normal layui-btn-radius">保存</a>
 <div id="choose" style="display:none;">
				
				
				</div>
 <div id="remark" style="display:none;font-size:0.9em;"> </div>

<script type="text/javascript">


function add(el)
						{
						layer.confirm('确定增加一行?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               
								 var tt=$(el.parentNode.parentNode);
								tt.after('<tr><td><div style="width:60px; height:28px; margin:auto;  border:1px #ccc solid; position:relative"><select name="first"  onChange="set_modaul(this)" style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" ><option selected="selected" value="请选阶段">请选阶段</option><option   value="EVT">EVT</option><option value="DVT">DVT</option><option value="PVT">PVT</option><option value="MVT">MVT</option><option value="MP">MP</option></select><input type="text" name="status" style="width:80%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.phase }"/></div></td><td><input type="text" name="version" value="${p.version }" class="layui-input"></td><td><input type="text" name="pushtime" value="${p.versionReleasetime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" readonly></td><td><div style="width:150px; height:28px; margin:auto;  border:1px #ccc solid; position:relative"><select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px"onChange="set_modaul(this);" ><option value="选择模块" selected="selected">选择模块</option><c:forEach items="${showCaseForms }" var="scf"><option value="${scf.caseType}">${scf.caseType}</option></c:forEach></select><input type="text" name="modaul" style="width:90%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.casetype.casetypeName }"/></div></td><td><input type="text" name="test"  style="width:100%;height:50px;"  onclick="showt(this)" value="${p.casestore.testModule }"/></div></td><td><input type="text" name="count" value="${p.caseCount }" class="layui-input"></td><td><input type="text" name="humannum"  value="${p.humanInput }" onkeyup="sount(this)" class="layui-input"></td><td><input type="text" name="humancount" value="${p.humanCount }" onkeyup="sounts(this)" class="layui-input"></td><td><input type="text" name="pcount" value="${p.prototypeCount }" onkeyup="sounts(this)" class="layui-input"></td><td><input type="text" name="preuse" onkeyup="sounts(this)"value="${p.prototypeReuse }"  class="layui-input"></td><td><input type="text" name="startime" value="${p.startTime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" onmouseover="auto(this)" readonly></td><td><input type="text" name="endtime" value="${p.endTime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" readonly></td><td><input placeholder="请输入内容" name="remark" class="layui-input" value="${p.remark }" style="min-height:50px;"></td><td style="display:none"><input type="hidden" name="planId" value="${p.id }"/></td><td><a onclick="delete1(this)">删除</a> | <a onclick="add(this)">增加</a></td>	<td style="display:none;"><input type="hidden" name="Flag" value="1"/></td></tr>');
                                 },function(index){ layer.close(index);});
						
						
						}
						
</script>

<script>
/* $("button").click(function(){
    $(this).attr("disabled","disabled");    
}); */
 function search(){
    $("#table tr:gt(0)").hide();
     var $d = $("#table tr:gt(0)").filter(":contains('"+$.trim($("#search").val())+"')");
          $d.show();
    
     }
     $('#search').bind('input propertychange',function(){
     search();
     });
     
</script>
 <script src="script/policy.js"></script>
		<%-- 隐藏域表单 --%>
	<form action="" method="post" id="formid">
		<input type="hidden" name="planId" id="planId" />
		 <input	type="hidden" name="checkedIds" id="checkedIds" />
	</form>
</html>

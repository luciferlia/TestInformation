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

<script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<script type="text/script" src="script/exchange.js"></script>

<style type="text/css">
.table td {
	font-size: 0.9em;
	font-color: black;
	border: 1px solid black;
	text-align: center;
	vertical-align: middle;
	WORD-WRAP: break-word
}


.table select{width:100%;height:100%;}
.table input{border:1px solid white;}
li {
	float: left;
	list-style: none;
}
.colortd{
background-color:#ffff33;
}
.mytest{

display:-moz-inline-box;

display:inline-block;

width:125px;
 }
</style>

<script>

	function pageScroll() {
		//把内容滚动指定的像素数（第一个参数是向右滚动的像素数，第二个参数是向下滚动的像素数）
		window.scrollBy(0, -100);
		//延时递归调用，模拟滚动向上效果
		scrolldelay = setTimeout('pageScroll()', 100);
		//获取scrollTop值，声明了DTD的标准网页取document.documentElement.scrollTop，否则取document.body.scrollTop；因为二者只有一个会生效，另一个就恒为0，所以取和值可以得到网页的真正的scrollTop值
		var sTop = document.documentElement.scrollTop + document.body.scrollTop;
		//判断当页面到达顶部，取消延时代码（否则页面滚动到顶部会无法再向下正常浏览页面）
		if (sTop == 0)
			clearTimeout(scrolldelay);
	}
    layui.use('layer', function(){
  var layer = layui.layer;
 
});  
	layui
			.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

					});
					function delete1(obj) {
		 layer.confirm('确定删除此行?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
	    	var tr = obj.parentNode.parentNode;
	
	    	 var tbody = tr.parentNode;
	    	 tbody.removeChild(tr);
	    	 layer.msg('删除成功',{time:1000});
	    	 },function(index){ layer.close(index);});		
	}
</script>
     <script type="text/javascript">

$('#save').click(function(){this.disabled=true;});
   function addProjectPlan(){
   
      var data = savePlan(); //获取要传到后台的数据 json 格式
      console.log(data);
      //alert(data);
          var sum=0;
       var a=document.getElementsByName('status');
        var b=document.getElementsByName('version');
         var c=document.getElementsByName('pushtime');
        
          var e=document.getElementsByName('modaul');
           var f=document.getElementsByName('test');
            var g=document.getElementsByName('count');
             var h=document.getElementsByName('startime');
              var j=document.getElementsByName('endtime');
      for(var i=0;i<a.length;i++)
      {
      var ver=a[i].value;
      var ver1=b[i].value;
      var ver2=c[i].value;
     
      var ver4=e[i].value;
      var ver5=f[i].value;
      var ver6=g[i].value;
      var ver7=h[i].value;
       var ver8=j[i].value;
      if(ver==""||ver==null || ver1==""||ver1==null || ver2==""||ver2==null|| ver4==""||ver4==null || ver5==""||ver5==null|| ver6==""||ver6==null || ver7==""||ver7==null|| ver8==""||ver8==null)
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
      {
       layer.msg('加载中', {
  icon: 16
  ,shade:0.5,time:5000000
});
            $.ajax({
             type: "POST",
            url: "addPlan?id="+${id}, //这里你根据后台地址改
         data:{"params":JSON.stringify(data)},
		dataType:"json",
		
         success: function(data){
            //console.log(data)
           
           
            layer.msg('新增项目计划成功!',{time:1000});
              window.location.reload();
         },
         complete:function (){
         
         },
         error : function(e) {
           layer.msg('新增项目计划失败!',{time:1000});
         }
      });
  }
          
   
   }

   function savePlan(){
      
      var array=[];
      $("tr").each(function(){
         var json = {};
         $(this).find("input").each(function (i){
            json[""+$(this)[0].name+""]=$(this)[0].value;
         });
         if(!isEmptyObject(json)){
            //console.log(json);
            array.push(json);
         }
      });
    
       // alert(json);
      //console.log(array);
      return array;
   }

   function isEmptyObject(obj) {
      for (var key in obj) {
         return false;
      }
      return true;
   }

</script>

</head>

<body >
 <table id="table1" style="display:none">
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

 
	<a onclick="pageScroll()"
		style="display:block; position:fixed; right:5px; bottom:20px;"><img
		src="images/top.jpg"></a>
	<span style="font-size:1.2em;margin-left:45%;">
		<%-- <% int i=0; %>
		<c:forEach items="${policy }" var="p">
			<% if(i==0){ %>
			${p.project.projectName }
			<%} i=1; %>
		</c:forEach> --%>${projectName }项目测试计划
		</span>
	<hr />
	<table style="width:100%;min-width:1100px;" class="table"
		id="table">
		<tr style="background-color:#4682B4;height:40px;color: white;"onclick="adddd(this)">
			
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
			<!-- <td style="width:4%;">复用类型</td> -->
			<td style="width:5.5%;min-width:100px;">开始时间</td>
			<td style="width:5.5%;min-width:100px;">结束时间</td>
			<td><h4>备注 </h4></td>
			<td style="display:none;"></td>
			<td style="width:6%;min-width:100px;"><h4>操作</h4></td>

		</tr>
		
		<tbody id="cdTb">
		<c:forEach items="${policy }" var="p">
		
			<c:if test="${p.phase=='EVT' }">
			<tr>
				<td>
				<div style="width:50px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				
				<select   style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this);">
				<option value="选择模块">选择模块</option>
				<option value="EVT">EVT</option>
				<option value="DVT">DVT</option>
				<option value="PVT">PVT</option>
				<option value="MVT">MVT</option>
				<option value="MP">MP</option></select>
				<input type="text" name="status" style="width:73%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.phase }" readonly/>
				</div>
				</td>
				<td><input type="text" name="version" value="${p.version }" class="layui-input"></td>
				<td><input type="text" name="pushtime" value="${p.versionReleasetime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" readonly></td>
								<td>
				<div style="width:110px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				<select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px"
					onChange="set_modaul(this);" >
						 <option value="选择模块" selected="selected">选择模块</option>
						<c:forEach items="${showCaseForms }" var="scf">
						
						<option value="${scf.caseType}">${scf.caseType}</option>
						</c:forEach>
				</select>
				<input type="text" name="modaul" style="width:87%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.casetype.casetypeName }"/>
                 </div>
				</td>
				<td>
				
				<input type="text" name="test" onclick="showt(this)" value="${p.testContent }" style="width:100%;height:50px;"/>
				</td>
				<td><input type="text" name="count" value="${p.caseCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="humannum" value="${p.humanInput }" class="layui-input" onkeyup="sount(this)"></td>
				<td><input type="text" name="humancount" value="${p.humanCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="pcount" value="${p.prototypeCount }" class="layui-input" onkeyup="sounts1(this)"></td>
				<td><input type="text" name="preuse" value="${p.prototypeReuse }" class="layui-input" onkeyup="sounts1(this)"></td>
				<%-- <td><input type="text" name="ptype" value="${p.prototypeType }" class="layui-input"></td> --%>
				<td><input type="text" name="startime" value="${p.startTime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" onmouseover="auto(this)" readonly></td>
				<td><input type="text" name="endtime" value="${p.endTime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" readonly></td>
				<td><div class="layui-input-block" style="margin-left:0px;">
						<input placeholder="请输入内容" name="remark" class="layui-input" value="${p.remark }" 
							style="min-height:50px;">
					</div></td>
					<td style="display:none;"><input type="hidden" name="policyId" value="${p.id }"/></td>
		             <td><a onclick="delete1(this)">删除</a> | <a onclick="add(this)" >增加</a></td>
			</tr>
			</c:if>
			</c:forEach>
			<tr onclick="adddd(this)">
				<td colspan="17" class="colortd"><span
					style="float:left;">TR4评审</span></td>
			</tr>
			
		<c:forEach items="${policy }" var="p">
		
			<c:if test="${p.phase=='DVT' }">
			<tr>
				<td>
				<div style="width:50px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				
				<select   style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this);">
				<option value="选择模块">选择模块</option>
				<option value="EVT">EVT</option>
				<option value="DVT">DVT</option>
				<option value="PVT">PVT</option>
				<option value="MVT">MVT</option>
				<option value="MP">MP</option></select>
				<input type="text" name="status" style="width:73%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.phase }" readonly/>
				</div>
				</td>
				<td><input type="text" name="version" value="${p.version }" class="layui-input"></td>
				<td><input type="text" name="pushtime" value="${p.versionReleasetime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" readonly></td>
			
				<td>
				<div style="width:110px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				<select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px"
					onChange="set_modaul(this);" >
						 <option value="选择模块" selected="selected">选择模块</option>
						<c:forEach items="${showCaseForms }" var="scf">
						
						<option value="${scf.caseType}">${scf.caseType}</option>
						</c:forEach>
				</select>
				<input type="text" name="modaul" style="width:87%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.casetype.casetypeName }"/>
                 </div>
				</td>
				<td>
				
				
				<input type="text" name="test" onclick="showt(this)" value="${p.testContent }" style="width:100%;height:50px;"/>
				</td>
				<td><input type="text" name="count" value="${p.caseCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="humannum" value="${p.humanInput }" class="layui-input" onkeyup="sount(this)"></td>
				<td><input type="text" name="humancount" value="${p.humanCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="pcount" value="${p.prototypeCount }" class="layui-input" onkeyup="sounts1(this)"></td>
				<td><input type="text" name="preuse" value="${p.prototypeReuse }" class="layui-input" onkeyup="sounts1(this)"></td>
				<%-- <td><input type="text" name="ptype" value="${p.prototypeType }" class="layui-input"></td> --%>
				<td><input type="text" name="startime" value="${p.startTime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" onmouseover="auto(this)" readonly></td>
				<td><input type="text" name="endtime" value="${p.endTime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" readonly></td>
				<td><div class="layui-input-block" style="margin-left:0px;">
						<input placeholder="请输入内容" name="remark" class="layui-input" value="${p.remark }" 
							style="min-height:50px;">
					</div></td>
					<td style="display:none;"><input type="hidden" name="policyId" value="${p.id }"/></td>
				<td><a onclick="delete1(this)">删除</a> | <a onclick="add(this)" >增加</a></td>
			</tr>
			
			</c:if>
			</c:forEach>
			<tr onclick="adddd(this)">
				<td colspan="17" class="colortd"><span
					style="float:left;">TR4A评审</span></td>
			</tr>
		<c:forEach items="${policy }" var="p">
		
			<c:if test="${p.phase=='PVT' }">
			<tr>
			
				<td>
				<div style="width:50px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				
				<select   style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this);">
				<option value="选择模块">选择模块</option>
				<option value="EVT">EVT</option>
				<option value="DVT">DVT</option>
				<option value="PVT">PVT</option>
				<option value="MVT">MVT</option>
				<option value="MP">MP</option></select>
				<input type="text" name="status" style="width:73%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.phase }" readonly/>
				</div>
				</td>
				<td><input type="text" name="version" value="${p.version }" class="layui-input"></td>
				<td><input type="text" name="pushtime" value="${p.versionReleasetime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" readonly></td>
				
				<td>
				<div style="width:110px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				<select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px"
					onChange="set_modaul(this);" >
						 <option value="选择模块" selected="selected">选择模块</option>
						<c:forEach items="${showCaseForms }" var="scf">
						
						<option value="${scf.caseType}">${scf.caseType}</option>
						</c:forEach>
				</select>
				<input type="text" name="modaul" style="width:87%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.casetype.casetypeName }"/>
                 </div>
				</td>
				<td>
								
				<input type="text" name="test" onclick="showt(this)" value="${p.testContent }" style="width:100%;height:50px;"/>
				</td>
		
				
		           <td><input type="text" name="count" value="${p.caseCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="humannum" value="${p.humanInput }" class="layui-input" onkeyup="sount(this)"></td>
				<td><input type="text" name="humancount" value="${p.humanCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="pcount" value="${p.prototypeCount }" class="layui-input" onkeyup="sounts1(this)"></td>
				<td><input type="text" name="preuse" value="${p.prototypeReuse }" class="layui-input" onkeyup="sounts1(this)"></td>

			<%-- 	<td><input type="text" name="ptype" value="${p.prototypeType }" class="layui-input"></td> --%>

				<td><input type="text" name="startime" value="${p.startTime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" onmouseover="auto(this)" readonly></td>
				<td><input type="text" name="endtime" value="${p.endTime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" readonly></td>
				<td><div class="layui-input-block" style="margin-left:0px;">
						<input placeholder="请输入内容" name="remark" class="layui-input" value="${p.remark }" 
							style="min-height:50px;">
					</div></td>
					<td style="display:none;"><input type="hidden" name="policyId" value="${p.id }"/></td>
				<td><a onclick="delete1(this)">删除</a> | <a onclick="add(this)" >增加</a></td>
			</tr>
			</c:if>
			</c:forEach>
			<tr onclick="adddd(this)">
				<td colspan="17" class="colortd"><span
					style="float:left;">TR5评审</span></td>
			</tr>
			
		  <c:forEach items="${policy }" var="p">
		
			<c:if test="${p.phase=='MVT' }">
			<tr>
				<td>
				<div style="width:50px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				
				<select   style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this);">
				<option value="选择模块">选择模块</option>
				<option value="EVT">EVT</option>
				<option value="DVT">DVT</option>
				<option value="PVT">PVT</option>
				<option value="MVT">MVT</option>
				<option value="MP">MP</option></select>
				<input type="text" name="status" style="width:73%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.phase }" readonly/>
				</div>
				</td>
				<td><input type="text" name="version" value="${p.version }" class="layui-input"></td>
				<td><input type="text" name="pushtime" value="${p.versionReleasetime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" readonly></td>
				
				<td>
				<div style="width:110px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				<select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px"
					onChange="set_modaul(this);" >
						 <option value="选择模块" selected="selected">选择模块</option>
						<c:forEach items="${showCaseForms }" var="scf">
						
						<option value="${scf.caseType}">${scf.caseType}</option>
						</c:forEach>
				</select>
				<input type="text" name="modaul" style="width:87%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.casetype.casetypeName }"/>
                 </div>
				</td>
				<td>
				
				<input type="text" name="test" onclick="showt(this)" value="${p.testContent }" style="width:100%;height:50px;"/>
				</td>
				<td><input type="text" name="count" value="${p.caseCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="humannum" value="${p.humanInput }" class="layui-input" onkeyup="sount(this)"></td>
				<td><input type="text" name="humancount" value="${p.humanCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="pcount" value="${p.prototypeCount }" class="layui-input" onkeyup="sounts1(this)"></td>
				<td><input type="text" name="preuse" value="${p.prototypeReuse }" class="layui-input" onkeyup="sounts1(this)"></td>
				<%-- <td><input type="text" name="ptype" value="${p.prototypeType }" class="layui-input"></td> --%>
				<td><input type="text" name="startime" value="${p.startTime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" onmouseover="auto(this)" readonly></td>
				<td><input type="text" name="endtime" value="${p.endTime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" readonly></td>
				<td><div class="layui-input-block" style="margin-left:0px;">
						<input placeholder="请输入内容" name="remark" class="layui-input" value="${p.remark }" 
							style="min-height:50px;">
					</div></td>
					<td style="display:none;"><input type="hidden" name="policyId" value="${p.id }"/></td>
				<td><a onclick="delete1(this)">删除</a> | <a onclick="add(this)" >增加</a></td>
			</tr>
			
			</c:if>
			</c:forEach>
			<tr onclick="adddd(this)">
				<td colspan="17" class="colortd"><span
					style="float:left;">TR6评审</span></td>
			</tr>
		<c:forEach items="${policy }" var="p">
		
			<c:if test="${p.phase=='MP' }">
			<tr>
				<td>
				<div style="width:50px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				
				<select   style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this);">
				<option value="选择模块">选择模块</option>
				<option value="EVT">EVT</option>
				<option value="DVT">DVT</option>
				<option value="PVT">PVT</option>
				<option value="MVT">MVT</option>
				<option value="MP">MP</option></select>
				<input type="text" name="status" style="width:73%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.phase }" readonly/>
				</div>
				</td>
				<td><input type="text" name="version" value="${p.version }" class="layui-input"></td>
				<td><input type="text" name="pushtime" value="${p.versionReleasetime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" readonly></td>
				
				<td>
				<div style="width:110px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				<select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px"
					onChange="set_modaul(this);" >
						 <option value="选择模块" selected="selected">选择模块</option>
						<c:forEach items="${showCaseForms }" var="scf">
						
						<option value="${scf.caseType}">${scf.caseType}</option>
						</c:forEach>
				</select>
				<input type="text" name="modaul" style="width:87%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.casetype.casetypeName }"/>
                 </div>
				</td>
				<td>
				
				<input type="text" name="test" onclick="showt(this)"value="${p.testContent }" style="width:100%;height:50px;"/>
				</td>
				<td><input type="text" name="count" value="${p.caseCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="humannum" value="${p.humanInput }" class="layui-input" onkeyup="sount(this)"></td>
				<td><input type="text" name="humancount" value="${p.humanCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="pcount" value="${p.prototypeCount }" class="layui-input" onkeyup="sounts1(this)"></td>
				<td><input type="text" name="preuse" value="${p.prototypeReuse }" class="layui-input" onkeyup="sounts1(this)"></td>
				<%-- <td><input type="text" name="ptype" value="${p.prototypeType }" class="layui-input"></td> --%>
				<td><input type="text" name="startime" value="${p.startTime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" onmouseover="auto(this)" readonly></td>
				<td><input type="text" name="endtime" value="${p.endTime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" readonly></td>
				<td><div class="layui-input-block" style="margin-left:0px;">
						<input placeholder="请输入内容" name="remark" class="layui-input" value="${p.remark }" 
							style="min-height:50px;">
					</div></td>
					<td style="display:none;"><input type="hidden" name="policyId" value="${p.id }"/></td>
				<td><a onclick="delete1(this)">删除</a> | <a onclick="add(this)" >增加</a></td>
			</tr>
			</c:if>
			</c:forEach>

		</tbody>
	</table>
	
	<a type="button" style="margin-left:90%;" onclick="addProjectPlan()"
		class="layui-btn layui-btn-normal layui-btn-radius" id="save">保存</a>

<div id="choose" style="display:none;">
				
				
				</div>
	<script>
	
						function add(el)
						{
						layer.confirm('确定增加一行?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               
								 var tt=$(el.parentNode.parentNode);

								 tt.after('<tr><td><div style="width:50px; height:28px; margin:auto;  border:1px #ccc solid; position:relative"><select   style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this);"><option value="选择模块">选择模块</option><option value="EVT">EVT</option><option value="DVT">DVT</option><option value="PVT">PVT</option><option value="MVT">MVT</option><option value="MP">MP</option></select><input type="text" name="status" style="width:73%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.phase }" readonly/></div></td><td><input type="text" name="version" value="${p.version }" class="layui-input"></td><td><input type="text" name="pushtime" value="${p.versionReleasetime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" readonly></td><td><div style="width:110px; height:28px; margin:auto;  border:1px #ccc solid; position:relative"><select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this)" ><option value="选择模块" selected="selected">选择模块</option><c:forEach items="${showCaseForms }" var="scf"><option value="${scf.caseType}">${scf.caseType}</option></c:forEach></select><input type="text" name="modaul" style="width:87%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.casetype.casetypeName }"/></div></td><td><input type="text" name="test"onclick="showt(this)" value="${p.testContent }" style="width:100%;height:50px;"/></div></td><td><input type="text" name="count" value="${p.caseCount }" class="layui-input"></td><td><input type="text" name="humannum"  value="${p.humanInput }" onkeyup="sount(this)" class="layui-input"></td><td><input type="text" name="humancount" value="${p.humanCount }" onkeyup="sounts(this)" class="layui-input"></td><td><input type="text" name="pcount" value="${p.prototypeCount }" onkeyup="sounts1(this)" class="layui-input"></td><td><input type="text" name="preuse" onkeyup="sounts1(this)"value="${p.prototypeReuse }"  class="layui-input"></td><td><input type="text" name="startime" value="${p.startTime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" onmouseover="auto(this)" readonly></td><td><input type="text" name="endtime" value="${p.endTime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" readonly></td><td><div class="layui-input-block" style="margin-left:0px;"><input placeholder="请输入内容" name="remark" class="layui-input" value="${p.remark }" style="min-height:50px;"></div></td><td style="display:none;"><input type="hidden" name="policyId" value="${p.id }"/></td><td><a onclick="delete1(this)">删除</a> | <a onclick="add(this)" >增加</a></td></tr>');

                               },function(index){ layer.close(index);});
						
						
						}
						function adddd(el)
						{
						layer.confirm('确定增加一行?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               
								 var tt=$(el);

								 tt.after('<tr><td><div style="width:50px; height:28px; margin:auto;  border:1px #ccc solid; position:relative"><select   style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this);"><option value="选择模块">选择模块</option><option value="EVT">EVT</option><option value="DVT">DVT</option><option value="PVT">PVT</option><option value="MVT">MVT</option><option value="MP">MP</option></select><input type="text" name="status" style="width:73%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.phase }" readonly/></div></td><td><input type="text" name="version" value="${p.version }" class="layui-input"></td><td><input type="text" name="pushtime" value="${p.versionReleasetime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" readonly></td><td><div style="width:110px; height:28px; margin:auto;  border:1px #ccc solid; position:relative"><select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this)" ><option value="选择模块" selected="selected">选择模块</option><c:forEach items="${showCaseForms }" var="scf"><option value="${scf.caseType}">${scf.caseType}</option></c:forEach></select><input type="text" name="modaul" style="width:87%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.casetype.casetypeName }"/></div></td><td><input type="text" name="test"onclick="showt(this)" value="${p.testContent }" style="width:100%;height:50px;"/></div></td><td><input type="text" name="count" value="${p.caseCount }" class="layui-input"></td><td><input type="text" name="humannum"  value="${p.humanInput }" onkeyup="sount(this)" class="layui-input"></td><td><input type="text" name="humancount" value="${p.humanCount }" onkeyup="sounts(this)" class="layui-input"></td><td><input type="text" name="pcount" value="${p.prototypeCount }" onkeyup="sounts1(this)" class="layui-input"></td><td><input type="text" name="preuse" onkeyup="sounts1(this)"value="${p.prototypeReuse }"  class="layui-input"></td><td><input type="text" name="startime" value="${p.startTime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" onmouseover="auto(this)" readonly></td><td><input type="text" name="endtime" value="${p.endTime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" readonly></td><td><div class="layui-input-block" style="margin-left:0px;"><input placeholder="请输入内容" name="remark" class="layui-input" value="${p.remark }" style="min-height:50px;"></div></td><td style="display:none;"><input type="hidden" name="policyId" value="${p.id }"/></td><td><a onclick="delete1(this)">删除</a> | <a onclick="add(this)" >增加</a></td></tr>');

                               },function(index){ layer.close(index);});
						
						
						}
	</script>
		<script src="script/js/autosize.js"></script>
	<script>

		autosize(document.querySelectorAll('textarea'));

	</script>
 <script src="script/policy.js"></script>
	
</body>
</html>

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

<script src="script/js/jquery.min.js"></script>
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<link rel="stylesheet" href="script/exchange.css">

<style type="text/css">
#table td {
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
		obj.disable=true;
		var tr = obj.parentNode.parentNode;
		var id=$(tr).children('td').eq(13)[0].children[0].value;
	     if(id==""||id==null)
	    	 {
	    	 layer.confirm('确定删除此行?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
	    	 var tbody = tr.parentNode;
	    	 tbody.removeChild(tr); 
	    	 layer.msg('删除成功',{time:1000});
	    	 },function(index){ layer.close(index);});
	    	 
	    	 }
	     else{
	    	 //进入数据库删
	    	 layer.confirm('确定删除此行包括数据?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
	    	// alert(id);
	    	 $.ajax({
	 			type:"post",
	 			url:"deletePolicy?policyId="+id,
	 			success:function(textStatus){
	 				 var tbody = tr.parentNode;
	 		    	 tbody.removeChild(tr);
	 				layer.msg('删除成功',{time:1000});
	 			},
	 			error:function(xhr,status,errMsg){
	 				alert(errMsg);
	 				layer.msg('删除失败',{time:1000});
	 			}
	 		});},function(index){ layer.close(index);});
	 	}
		
		
	}
</script>
     <script type="text/javascript">
   function addProjectPlan(){
      var data = savePlan(); //获取要传到后台的数据 json 格式
      console.log(data);
      var sum=0;
      //alert(data);
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
    if(ver==""||ver==null || ver1==""||ver1==null || ver2==""||ver2==null || ver4==""||ver4==null || ver5==""||ver5==null || ver6==""||ver6==null || ver7==""||ver7==null||ver8==""||ver8==null)
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
            url: "addPolicy?id="+${id}, //这里你根据后台地址改
         data:{"params":JSON.stringify(data)},
		dataType:"json",
		beforeSend:function (){
		document.getElementById("btn1").disabled=true;
		},
         success: function(data){
            //console.log(data)
            window.location.reload();
            layer.msg('新增项目策略成功!',{time:1000});
           /*  var l=document.getElementById("btn1");
            alert(l); */
         },
         error : function(e) {
           layer.msg('新增项目策略失败!',{time:1000});
         },
        /*  complete:function (){
         document.getElementById("btn1").disabled=true;
         } */
      });
      }
          
   
   }

	function exportPolicy(policypoolId){
	 layer.confirm('确定导出策略模板?', {icon: 3, title:'提示'}, function(index){
 layer.close(index);
$.ajax({
			type:'post',
			url:'exportPolicy?policypoolId='+policypoolId,
			success:function(data){
			alert(data);
				if(data=='success'){
					//layer.msg('导出策略成功',{time:2000});
					window.location.href = "policy_downloadPolicy";
				}else{
					layer.msg('导出策略失败,该模板中沒有策略',{time:2000});
				}
			}
		});

},function(index){ layer.close(index);});
		
	}
	function importpolicy(policypoolId){
		
	}
	
   function savePlan(){
      var array=[];
      $("#table tr").each(function(){
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
   function vall(){
       var tbody=document.getElementById('cdTb');
      var row=tbody.children[0];
      var luk=$(row).children('td').eq(4)[0].children[0].children[0];
      var first=luk.value;
      var ne=$(luk).next('input')[0];
       ne.value=first;
   }
   $("#btn1").click(function(){ $(this).attr("disabled","disabled"); });
</script>



<script>
	
	function input(){
	 var a=document.getElementById("importDiv");
      var b=document.getElementById("hid");
      a.style.display="block";
      b.style.display="block";
      var c=document.getElementById("ids");
      var d=document.getElementById("kkk");
      d.value=c.value;
	}
	
	function hidd(){
       var a=document.getElementById("importDiv");
      var b=document.getElementById("hid");
      a.style.display="none";
      b.style.display="none";
      }
    
</script>
</head>

<body >
<%-- <font color="red">${result }</font> --%>
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

	<a onclick="pageScroll()"
		style="display:block; position:fixed; right:5px; bottom:20px;"><img
		src="images/top.jpg"></a>
		<input value="${id }" type="hidden" id="ids">
		
	<ul style="float:right;">
	<li style="float:left;"><c:if test="${not empty permissionForm.impor }"><a onclick="input()" type="button"
				class="layui-btn layui-btn-normal layui-btn-radius">${permissionForm.impor }</a></c:if> </li>
	<li style="float:right;"><c:if test="${not empty permissionForm.expor }"><a onclick="exportPolicy(${id});" type="button"
				class="layui-btn layui-btn-normal layui-btn-radius">${permissionForm.expor }</a></c:if> </li>
	</ul>
		<hr />
		<span style="font-size:1.2em;margin-left:45%;">
		<% int i=0; %>
		<c:forEach items="${policys }" var="p">
			<% if(i==0){ %>
			${p.policypool.policyName }
			<%} i=1; %>
		</c:forEach>
		</span>
	<table style="width:100%;min-width:1110px;" class="table" id="table">
		<tr style="background-color:#4682B4;height:40px;color: white;" onclick="adddd(this)">
			<td style="width:6%;min-width:50px;"><h4>项目阶段</h4> </td>
			<td style="width:6%;min-width:50px;">软件版本</td>
			<td style="width:7%;min-width:70px;">版本发布时间</td>
			<td style="width:7%;min-width:50px;">测试类型</td>
			<td style="width:15%;">测试内容</td>
			<td style="width:5%;">用例条数</td>
			<td style="width:6%;">人力投入(人.天)</td>
			<td style="width:5%;">人数安排</td>
			<td style="width:5%;">测试机需求(机器/台)</td>
			<td style="width:5%;">测试机复用(机器/台)</td>
			<!-- <td style="width:5%;">复用类型</td> -->
			<td style="width:7%;min-width:100px;">开始时间</td>
			<td style="width:7%;min-width:100px;">结束时间</td>
			<td><h4>备注 </h4></td>
			<td style="display:none;"></td>
			<td style="width:6%;min-width:100px;"><h4>操作</h4></td>
		</tr>
		<tbody id="cdTb">
		<c:forEach items="${policys }" var="p">
			
			<tr>
			    <td>
				<div style="width:50px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				
				<select   style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this);">
				<option value="选择模块">选择模块</option>
				<option value="TR1">TR1</option>
				<option value="TR2">TR2</option>
				<option value="TR3">TR3</option>
				<option value="TR4">TR4</option>
				<option value="TR5">TR5</option>
				<option value="TR6">TR6</option>
				<option value="TR7">TR7</option></select>
				<input type="text" name="status" style="width:73%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.phase }" readonly/>
				</div>
				</td>
				<td><input type="text" name="version" value="${p.version }" class="layui-input"></td>
				<td><input type="text" name="pushtime" value="${p.versionReleasetime }"
					class="layui-input"
					onclick="layui.laydate({elem: this, festival: true})" readonly></td>
				
				<td>
				<div style="width:110px; height:28px; margin:auto;  border:1px #ccc solid; position:relative">
				<select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this)">
						 <option value="选择模块" selected="selected">选择模块</option>
						<c:forEach items="${showCaseForms }" var="scf">
						<option value="${scf.caseType}">${scf.caseType}</option>
						</c:forEach>
				</select>
				<input type="text" name="modaul" style="width:87%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.casetype.casetypeName }"/>
                 </div>
				</td>
				<td>
				
				<%-- <select name="second" style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px"
					onChange="sum(this);">
				</select> --%>
				<input type="text" name="test" onclick="showt(this)" value="${p.testContent }" style="height:50px;"/>
				</td>
				<td><input type="text" name="count" value="${p.caseCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="humannum" value="${p.humanInput }" class="layui-input"  onkeyup="sount(this)"></td>
				<td><input type="text" name="humancount" value="${p.humanCount }" class="layui-input" onkeyup="sounts(this)"></td>
				<td><input type="text" name="pcount" value="${p.prototypeCount }" class="layui-input" onkeyup="sounts1(this)"></td>
				<td><input type="text" name="preuse" value="${p.prototypeReuse }" class="layui-input" onkeyup="sounts1(this)"></td>
				<%-- <td><input type="text" name="ptype" value="${p.prototypeType }" class="layui-input" ></td> --%>
				<td><input type="text" name="startime" value="${p.startTime }" 
					class="layui-input" onclick="layui.laydate({elem: this, festival: true})" onmouseover="auto(this)" readonly ></td>
				<td><input type="text" name="endtime" value="${p.endTime }"
					class="layui-input" onclick="layui.laydate({elem: this, festival: true})"  readonly></td>
				<td><div class="layui-input-block" style="margin-left:0px;">
					<input placeholder="请输入内容" name="remark" class="layui-input" value="${p.remark }" 
							style="min-height:50px;">
					</div></td>
					<td style="display:none;"><input type="hidden" name="policyId" value="${p.id }"/></td>
				<td><a onclick="delete1(this)">删除</a> | <a onclick="add(this)" >增加</a></td>
			</tr>
		
			</c:forEach>
	
		</tbody>
	</table>
	
	<ul style="margin-left:80%;"><li style="float:left;"><button type="submit"  onclick="addProjectPlan()" id="save"
		class="layui-btn layui-btn-normal layui-btn-radius">保存</button></li>
		<li style="float:left;"><button type="reset" onclick="javascript:window.location.reload();" class="layui-btn layui-btn-normal layui-btn-radius">取消</button>
		</li></ul>
		<div id="hid"></div>
<div id="importDiv" style="display:none;" class="mydiv" >
	<div align="right"
			style="padding:2px;z-index:2000;font-size:12px;cursor:pointer;position:absolute;right:0;"
			 onclick="hidd()">
			<span><font color="black"><img alt="" src="images/del.gif"></font></span>
		</div>
		<form action="policy_importPolicy" method="post" enctype="multipart/form-data" style="width:100%;height:100%;"  onSubmit="return checkfile();">
		<table style="height:100%;width:100%;margin-top:0px; margin-bottom:0px;">
			<tr style="background-color:#4682B4;height:40px; text-align: center;vertical-align: middle;"><td >导入项目计划</td></tr>
			<tr><td><input id="kkk" value="" type="hidden" name="policypoolId"></td></tr>
			<tr>				
				<td  colspan="2"><div class="upfilebox" style="margin-left:20%;"><input type="file" name="file" accept=".xls,.xlsx"  onChange="validateFile(this)"><label><i class="layui-icon" style="color:#4682B4;font-size:1.5em;">&#xe61f;</i> 上传xls文件</label></div></td>
			</tr>
			<tr>
				<td style="font-size:0.7em;">上传本文件的第<input style="width:60px"
					onkeyup="sounts(this)" name="num" value="1"/>页&nbsp;&nbsp; 第<input style="width:60px;"
					onkeyup="sounts(this)" name="rowNum" value="2"/>行
				</td>
			</tr>
			<tr><td style="color: red;font-size:0.7em;">文本框中必须填大于0的整数，行必须填大于1的整数</td></tr>
			<tr  style="background-color:#4682B4;height:40px;text-align:center;vertical-align:middle;"><td colspan="2">
			<input type="submit" value="导入" class="layui-btn layui-btn-normal"  style="border:1px solid black;width:60px;">&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="layui-btn layui-btn-normal"  style="border:1px solid black;width:60px;" value="取消"  onclick="hidd()"></td></tr>
		</table>
		</form>
	</div>
<div id="choose" style="display:none;">
				
				
				</div>
	<script>
	
	
	
						function add(el)
						{
						layer.confirm('确定增加一行?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               
								 var tt=$(el.parentNode.parentNode);

								 tt.after('<tr><td><div style="width:50px; height:28px; margin:auto;  border:1px #ccc solid; position:relative"><select   style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this);"><option value="选择模块">选择模块</option><option value="TR1">TR1</option><option value="TR2">TR2</option><option value="TR3">TR3</option><option value="TR4">TR4</option><option value="TR5">TR5</option><option value="TR6">TR6</option><option value="TR7">TR7</option></select><input type="text" name="status" style="width:73%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.phase }" readonly/></div></td><td><input type="text" name="version" value="${p.version }" class="layui-input"></td><td><input type="text" name="pushtime" value="${p.versionReleasetime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" readonly></td><td><div style="width:110px; height:28px; margin:auto;  border:1px #ccc solid; position:relative"><select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this)" ><option value="选择模块" selected="selected">选择模块</option><c:forEach items="${showCaseForms }" var="scf"><option value="${scf.caseType}">${scf.caseType}</option></c:forEach></select><input type="text" name="modaul" style="width:87%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.casetype.casetypeName }"/></div></td><td><input type="text" name="test"onclick="showt(this)" value="${p.caseCount }" style="height:50px;"/></div></td><td><input type="text" name="count" value="${p.caseCount }" class="layui-input"></td><td><input type="text" name="humannum"  value="${p.humanInput }" onkeyup="sount(this)" class="layui-input"></td><td><input type="text" name="humancount" value="${p.humanCount }" onkeyup="sounts(this)" class="layui-input"></td><td><input type="text" name="pcount" value="${p.prototypeCount }" onkeyup="sounts1(this)" class="layui-input"></td><td><input type="text" name="preuse" onkeyup="sounts1(this)"value="${p.prototypeReuse }"  class="layui-input"></td><td><input type="text" name="startime" value="${p.startTime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" onmouseover="auto(this)" readonly></td><td><input type="text" name="endtime" value="${p.endTime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" readonly></td><td><div class="layui-input-block" style="margin-left:0px;"><input placeholder="请输入内容" name="remark" class="layui-input" value="${p.remark }" style="min-height:50px;"></div></td><td style="display:none;"><input type="hidden" name="policyId" value="${p.id }"/></td><td><a onclick="delete1(this)">删除</a> | <a onclick="add(this)" >增加</a></td></tr>');

                                },function(index){ layer.close(index);});
						
						
						}
						function adddd(el)
						{
						layer.confirm('确定增加一行?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               
								 var tt=$(el);

								 tt.after('<tr><td><div style="width:50px; height:28px; margin:auto;  border:1px #ccc solid; position:relative"><select   style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this);"><option value="选择模块">选择模块</option><option value="TR1">TR1</option><option value="TR2">TR2</option><option value="TR3">TR3</option><option value="TR4">TR4</option><option value="TR5">TR5</option><option value="TR6">TR6</option><option value="TR7">TR7</option></select><input type="text" name="status" style="width:73%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.phase }" readonly/></div></td><td><input type="text" name="version" value="${p.version }" class="layui-input"></td><td><input type="text" name="pushtime" value="${p.versionReleasetime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" readonly></td><td><div style="width:110px; height:28px; margin:auto;  border:1px #ccc solid; position:relative"><select name="first"  style="width:100%; height:100%; line-height:28px; position:absolute; left:0px; top:0px" onChange="set_modaul(this)" ><option value="选择模块" selected="selected">选择模块</option><c:forEach items="${showCaseForms }" var="scf"><option value="${scf.caseType}">${scf.caseType}</option></c:forEach></select><input type="text" name="modaul" style="width:87%; height:100%; position:absolute; left:0px; top:0px; z-index:2;" value="${p.casetype.casetypeName }"/></div></td><td><input type="text" name="test"onclick="showt(this)" value="${p.caseCount }" style="height:50px;"/></div></td><td><input type="text" name="count" value="${p.caseCount }" class="layui-input"></td><td><input type="text" name="humannum"  value="${p.humanInput }" onkeyup="sount(this)" class="layui-input"></td><td><input type="text" name="humancount" value="${p.humanCount }" onkeyup="sounts(this)" class="layui-input"></td><td><input type="text" name="pcount" value="${p.prototypeCount }" onkeyup="sounts1(this)" class="layui-input"></td><td><input type="text" name="preuse" onkeyup="sounts1(this)"value="${p.prototypeReuse }"  class="layui-input"></td><td><input type="text" name="startime" value="${p.startTime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" onmouseover="auto(this)" readonly></td><td><input type="text" name="endtime" value="${p.endTime }"class="layui-input"onclick="layui.laydate({elem: this, festival: true})" readonly></td><td><div class="layui-input-block" style="margin-left:0px;"><input placeholder="请输入内容" name="remark" class="layui-input" value="${p.remark }" style="min-height:50px;"></div></td><td style="display:none;"><input type="hidden" name="policyId" value="${p.id }"/></td><td><a onclick="delete1(this)">删除</a> | <a onclick="add(this)" >增加</a></td></tr>');

                              },function(index){ layer.close(index);});
						
						
						}
						function checkDate(obj){
						//alert(obj);
						
						var date1=obj.value;
						
							var tr=obj.parentNode.parentNode;
	
		
		var strat=$(tr).children('td').eq(11)[0].children[0];
		  var date2=strat.value;
		 var day1=date1.replace(/\-/gi,"/");
		 var day2=date2.replace(/\-/gi,"/");
		 var time1=new Date(day1).getTime();
		  var time2=new Date(day2).getTime();
				if(time1<time2)
				{
				obj.value="";
				layer.msg("结束时间不能小于起始时间");
				
				}else{
				return true;
				}		   
					
						}
						
				f
	</script>
		<script src="script/js/autosize.js"></script>
	<script>

		autosize(document.querySelectorAll('textarea'));

	</script>
	<script src="script/policy.js"></script>
<script>
function validateFile(target){
var fileSize=0;
if(!target.files)
{
var filePath=target.value;
var fileSystem=new ActiveXObject("Scripting.FileSystemObject");
var file=fileSystem.GetFile(filePath);
fileSize=file.size;
}else{
fileSize=target.files[0].size;
}
var size=fileSize/1024;
if(size>9000)
{
layer.msg("附件不能大于9M");
target.value="";

}
var name=target.value;
var fileName=name.substring(name.lastIndexOf(".")+1).toLowerCase();
if(fileName !="xls" && fileName !="xlsx")
{
layer.msg("只能上传excel格式的文件");
target.value="";

}
}

	
		function checkfile(){
	
	var file=document.getElementById('file').value;
	if(file==""||file==null)
	{
	layer.msg("请先选择文件");
	return false;
	}
	else{
	//alert("success");
	return true;
	
	}
	}
	
	
	
</script> 
	
</body>
</html>

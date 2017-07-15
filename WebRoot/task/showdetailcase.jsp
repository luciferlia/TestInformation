
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="script/layui/css/layui.css" media="all">
<script src="script/layui/layui.js"></script>
<script src="script/js/jquery.min.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<style>
td {
	font-size: 0.9em;
	font-color: black;
}
.haha li{float:left;}
body select{height:35px;}
</style>

<script>
layui.use('layer', function(){
  var layer = layui.layer;
 
}); 
	layui
			.use(
					[ 'form', 'layedit', 'laydate' ],
					function() {
						var form = layui.form(), layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate
					});
					
					
	function check(){
	var res=document.getElementById("y2").value;
	//alert(res);
	var r=document.getElementById("r2").value;
	//alert(r);
	if(r==res){
		
	}else{
		var f=confirm("结果未被保存，是否舍弃？");
		if(f)
		{
			return true;
		}else{
			return false;
		}
	}
		
	}
					
</script>

</head>
<body onload="data()">

	<form action="Case_updateCase" method="post" >
		<table
			style="width:97%;height:80%;margin-left:1%;margin-top:1%;border: 1px solid #4682B4;">
			<tr style="background-color:#4682B4;height:40px;">
				<td><input type="hidden" value="${caseresult.plan.planId }" id="y1"></td>
			</tr>
			<tr>
				<td><input type="hidden" name="typeId" value="${typeId }">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">分类</label>
							<div class="layui-input-inline">
								<input type="text" name="cases.classification"
									value="${cases.classification }" disabled="true"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">子模块</label>
							<div class="layui-input-inline">
								<input type="text" name="cases.caseName"
									value="${cases.caseName }" disabled="true" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">测试项</label>
							<div class="layui-input-inline">
								<input type="text" name="cases.testItem"
									value="${cases.testItem }" disabled="true" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">用例编号</label>
							<div class="layui-input-inline">
								<input type="text" name="cases.num"  value="${cases.num }"
									disabled="true" class="layui-input">
									<input type="hidden" name="cases.caseId" id="y4" value="${cases.caseId }"
									disabled="true" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">重要等级</label>
							<div class="layui-input-block">
							<input type="text" name="cases.level"
									value="${cases.level }" disabled="true"
									class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">测试内容</label>
						<div class="layui-input-block">
							<textarea name="cases.description" class="layui-textarea"  disabled="true" 
								style="max-width: 700px;overflow-y:visible;">${cases.description }</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">预置条件</label>
						<div class="layui-input-block">
							<textarea name="cases.environment" value="${cases.environment }"
								class="layui-textarea"  disabled="true" 
								style="max-width: 700px;heigth:50px;resize: none;">${cases.environment }</textarea>
						</div>
					</div>
					
					<div class="layui-form-item layui-form-text" style="display:none">
						<label class="layui-form-label"></label>
						<div class="layui-input-block" id="div2">
							<textarea name="cases.step" value="${cases.step }"
								class="layui-textarea"  disabled="true"   id="tex1"
								style="max-width: 700px;overflow-y:visible;">${cases.step }</textarea>
						</div>
					</div>
						<div id="div1">
							<textarea name="cases.expectResult" style="display:none" id="tex"
								value="${cases.expectResult }" class="layui-textarea"
								   style="max-width:700px;overflow-y:visible;">${cases.expectResult }</textarea>
						</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">建议耗时</label>
							<div class="layui-input-inline">
								<ul><li style="float:left;"><input type="text" name="cases.advidceTime"
									value="${cases.advidceTime }" disabled="true" style="width:70px;"
									class="layui-input"></li><li style="float:left;margin-top:10px;">&nbsp;&nbsp;min</li></ul>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">实际结果</label>
							<div class="layui-input-inline"  id="results">
								
								<input type="text" name="" id="y2"value="${caseresult.result }" class="layui-input"  disabled="true" style="color:blue;" >
							</div>
						</div>
						</div>
					
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">备注</label>
						<div class="layui-input-block">
							<textarea name="cases.description" class="layui-textarea"  disabled="true" 
								style="max-width: 700px;overflow-y:visible;">${cases.description }</textarea>
						</div>
					</div>
					<c:if test="${tag==1 }">
					<div class="layui-form-item layui-form-text"> 
						<label class="layui-form-label">结果备注</label>
							<textarea name="" value="" class="layui-textarea" id="y3"
								style="max-width: 700px;overflow-y:visible;">${caseresult.remark }</textarea>
					</div></c:if>
					<input type="hidden" name="" id="r2"value="${caseresult.result }" class="layui-input"  disabled="true" style="color:blue;" >
					</td>
					
			</tr>

		</table>
	</form>
	<script>
	function data(){
	
	
		var text=document.getElementById('tex').innerHTML;
		
     	var strs= new Array(); //定义一数组 
      	strs=text.split(". "); //字符分割 
   	 	var text1=document.getElementById('tex1').innerHTML;
     	var strs1= new Array(); //定义一数组 
      	strs1=text1.split(". "); //字符分割 
     	var a=document.getElementById('div1');
     	var html,sec;
       	html='<div style="width:810px;">'
       	var tag=${tag};
      if(tag==1){
    
     
      	for (i=1;i<strs.length ;i++ ) 
       { 
       	if(i!=strs.length-1){
       	strs[i]=strs[i].substr(0, strs[i].length-1);
       	strs1[i]=strs1[i].substr(0, strs1[i].length-1);
       }
      html += '<label class="layui-form-label" >操作步骤'+i+'</label>'+'<div class="layui-input-block"><textarea class="layui-textarea" style="width:700px;"  disabled="true" >'+strs1[i]+'</textarea></div>';
      html +='<label class="layui-form-label" style="color:red;">预期结果'+i+'</label>'+'<div class="layui-input-block" ><textarea class="layui-textarea" style="width:700px;color:red"  disabled="true" >'+strs[i]+'</textarea></div>';
      html +='<div class="layui-form-item layui-form-text"><ul class="haha" style="float:right;"><li><input type="radio" name="choose'+i+'" value="Pass" onclick="show(this)">Pass&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li><li><input type="radio" name="choose'+i+'" value="Fail" onclick="show(this)">Fail&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li><li><input type="radio" name="choose'+i+'" value="NA" onclick="show(this)">NA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li><li><input type="radio" name="choose'+i+'" value="NT" onclick="show(this)">NT&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li><li><input type="radio" name="choose'+i+'" value="TC Issue" onclick="show(this)">TC Issue</li></ul></div>';
       } 
       	html +='</div>';
     
        a.innerHTML=html;
        
	}
	else{
	
	for (j=1;j<strs.length ;j++ ) 
       { 
       	if(j!=strs.length-1){
       	strs[j]=strs[j].substr(0, strs[j].length-1);
       	strs1[j]=strs1[j].substr(0, strs1[j].length-1);
       }
      html += '<label class="layui-form-label">操作步骤'+j+'</label>'+'<div class="layui-input-block"><textarea class="layui-textarea" style="width:700px;"  disabled="true" >'+strs1[j]+'</textarea></div>'+'<label class="layui-form-label" style="color:red;">预期结果'+j+'</label>'+'<div class="layui-input-block"><textarea class="layui-textarea" style="width:700px;color:red"  disabled="true" >'+strs[j]+'</textarea></div>';
       } 
       	html +='</div>';
     
        a.innerHTML=html;
	}

	}

	</script>
	<script>
		function show(obj){
 
	 var ss=obj.value;
	
	if(ss=="Fail" || ss=="NA" || ss=="NT" || ss=="TC Issue")	{
	
	   ht='<input type="text" name="" id="y2" value="'+ss+'"class="layui-input"  disabled="true" style="color:red;">';   
	
	 }
	else{
	 ht='<input type="text" name="" id="y2" value="Pass"class="layui-input"  disabled="true" style="color:blue;">';   
	}
	   var b=document.getElementById('results');
	   b.innerHTML=ht;
	
	}
	
	</script>
	<script>
	function save(){
	var ids=document.getElementById("y1").value;
	var res=document.getElementById("y2").value;
	var mark=document.getElementById("y3").value;
	var caseid=document.getElementById("y4").value;
	
	var array=[{"ids":ids,"res":res,"mark":mark,"caseid":caseid}];
	
	console.log(array);
	
	$.ajax({
             type: "POST",
            url: "SaveCaseResult", //这里你根据后台地址改
         data:{"params":JSON.stringify(array)},
		dataType:"json",
         success: function(data){
            //console.log(data)
            //window.location.reload();
            layer.msg('结果保存成功!',{time:1000});
            if(${endCaseId}==${cases.caseId}){
            	
            	//alert("已经为最后一条");
            	layer.msg("已经为最后一条用例",{time:1000});
            	setTimeout(function() {
            		window.location.reload();
            	}, 1500)
            }else{
            	//alert("进入下一条");
            	layer.confirm('是否进入下一条？', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                               window.location.href="Case_nextTestCase?planId=${caseresult.plan.planId }&caseId=${cases.caseId }&tag=1";
			},function(index){ layer.close(index);
								window.location.reload();return false;
								});
            	
            }
           // 
            
         },
         error : function(xhx,e,errMsg) {
         alert(errMsg);
           layer.msg('结果保存失败!',{time:1000});
         }
      });
	
	}
	 
	</script>
	<script src="script/js/autosize.js"></script>
	<script>

		autosize(document.querySelectorAll('textarea'));

	</script>
</body>
</html>

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
<script src="script/js/bootstrap.min.js"></script>
  <script src="script/ie.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
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
   <script type="text/javascript">  
   
         
    //创建XMLHttpRequest对象     
    function createXMLHttpRequest() {  
        if (window.XMLHttpRequest)  
          {// code for all new browsers  
            XMLHttpReq=new XMLHttpRequest();  
           
          }  
        else if (window.ActiveXObject)  
          {// code for IE5 and IE6  
            XMLHttpReq=new ActiveXObject("Microsoft.XMLHTTP");  
           
          }  
        else  
          {  
          alert("Your browser does not support XMLHttpReq.");  
          }  
          
    }  
    //发送请求  
    function sendRequest(url, params, method) {  
        if (method) {  
            if (method.toLowerCase("post")) {  
                //alert("post方法");  
                sendRequestPost(url, params);  
            } else {  
                if (method.toLowerCase("get")) {  
                    sendRequestGet(url + "?" + params);  
                } else {  
                    //  
                }  
            }  
        } else {  
            alert("\u63d0\u4ea4\u65b9\u5f0f\u4e0d\u80fd\u4e3a\u7a7a\uff01");  
        }  
    }  
    //post发送请求函数  
    function sendRequestPost(url, params) {  
        createXMLHttpRequest();  
        XMLHttpReq.open("POST", url, true);  
        XMLHttpReq.onreadystatechange = processResponse;//指定响应函数  
        XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");  
        XMLHttpReq.send(params); // 发送请求  
    }  
    //get发送请求函数  
    function sendRequestGet(url) {  
        createXMLHttpRequest();  
        XMLHttpReq.open("GET", url, true);  
        XMLHttpReq.onreadystatechange = processResponse;//指定响应函数  
        XMLHttpReq.send(null); // 发送请求  
    }  
    //post发送请求函数(无需传递参数时)  
    function sendRequestPostwihtnoArgs(url)   
    {  
        createXMLHttpRequest();  
       
        XMLHttpReq.onreadystatechange = processResponse;//指定回调函数  
        XMLHttpReq.open("POST", url, true);  
       
        XMLHttpReq.send(null); // 发送请求  
    }  
    // 更新列表框  
    function update() { 
        var res = eval('('+XMLHttpReq.responseText+')');  
        var option;  
        var id_name_code = res.split("|");//拆分字符串，得到id与name(包括后缀字符串)的组合值  
        var id_result = id_name_code[0];//得到id的组合值(所有的id,以_隔开)  
        var name_result = id_name_code[1];//得到name的组合值(所有的name,以_隔开) 
        var result = id_name_code[2]; //得到后缀名字符串  
        //拆分以_隔开的字符串  
       
        var id = id_result.split("_");  
        var name = name_result.split("_"); 
        
        if ("case" == result) {  
            clearStudent();  
            //var studentId = $(tr).children('td').eq(5)[0].children[0];
           var studentId = document.getElementById("studentId"); 
            studentId.value=id[0];
          
        }  
        if ("caseStore" == result) {  
            
            clearClass();  
           
		  // var classesId=$(tr).children('td').eq(4)[0].children[0];
              var classesId = document.getElementById("classId");
            //option=new Option("${plan.casestore.testModule}","${plan.casestore.casestoreId}");
            //classesId.options.add(option); 
            for ( var i = 0; i < id.length-1; i++) { 
             	//if(${plan.casestore.casestoreId}==id[i]){
             	
             	//}else{
             		 option = new Option(name[i], id[i]);  
                	classesId.options.add(option);  
             	//}
               
            }  
        }  
        if ("caseType" == result) {  
          
            clearSchool();    
             // var schoolId=$(sc).children().eq(3)[0].children[0];
              var schoolId = document.getElementById("schoolId");  
            option=new Option("${plan.casetype.casetypeName}","${plan.casetype.id}");
            schoolId.options.add(option); 
            for ( var i = 0; i < id.length-1; i++) {  
            	//if(${plan.casetype.id}==id[i]){
            	
            	//}else{
            	option = new Option(name[i], id[i]);  
                schoolId.options.add(option);  
            	//}
            }  
        }  
    
    }  
    // 清除列表框  
    function clearSchool() {  
     
        var schoolId = document.getElementById("schoolId");  
        while (schoolId.childNodes.length > 0) {  
            schoolId.removeChild(schoolId.childNodes[0]);  
        }  
    }  
    function clearClass() { 
     
       var classesId = document.getElementById("classId");  
        while (classesId.childNodes.length > 0) {  
            classesId.removeChild(classesId.childNodes[0]);  
        }  
    }  
    function clearStudent() {  
     
      var studentId = document.getElementById("studentId");  
        while (studentId.childNodes.length > 0) {  
            studentId.removeChild(studentId.childNodes[0]);  
        }  
    }  
    // 处理返回信息函数  
    function processResponse() {  
        if (XMLHttpReq.readyState == 4) { // 判断对象状态  
            if (XMLHttpReq.status == 200) { //信息已经成功返回，开始处理信息  
                update();  
            } else { //页面不正常  
                window.alert("未找到页面");  
            }  
        }  
    }  
    function findStubyClasslId() { 
   
   
       var classId = document.getElementById('classId').value; 
        var sURL = "allCaseNum";  
        var method = "post";  
        var sParams = "casestoreId=" + classId;  
        sendRequest(sURL, sParams, method);  
    }  
    function findClassbySchoolId() { 
     
      var typeId = document.getElementById('schoolId').value; 
        var sURL = "allCaseStore";  
        var method = "POST";  
        var sParams = "typeId=" + typeId;  
       
        sendRequest(sURL, sParams, method);  
    }  
  
    function findAllSchool() {  
        var sURL = "allCaseType";  
        sendRequestPostwihtnoArgs(sURL);  
    }  
</script> 
<script>
function show(){
var sel=document.getElementById('schoolId');
var inp=document.getElementById('input1');
var school=sel.value;
inp.value=school;
}
</script> 
</head>
<body onload="findAllSchool()">
    <div style="width:90%;heigth:100%;margin-top:1%;margin-left:2%;border:1px solid #4682B4;min-width:900px;">
    <table style="width:100%;"><tr style="heigth:40px;background-color:#4682B4;"><td></td></tr></table>
	<form action="Plan_updatePlan" method="post" class="layui-form"
		style="margin-left:5%;margin-top:1%;width:90%;">
		<input type="hidden" name="id" value="${planId }">
			<input type="hidden" name="plantail.id"
					value="${plantailForm.plantail.id }">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">项目名称：</label>
							<div class="layui-input-inline">
								<input type="text" name="" value="${plantailForm.plantail.plan.project.projectName }"
									class="layui-input" disabled="false">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">项目阶段：</label>
							<div class="layui-input-inline">
								<input type="text" name="plan.phase" value="${plantailForm.plantail.plan.phase }"
									class="layui-input" disabled="false">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">软件版本：</label>
							<div class="layui-input-inline">
								<input type="text" name="plantail.plan.version" value="${plantailForm.plantail.plan.version }"
									class="layui-input" disabled="false">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">发布时间：</label>
							<div class="layui-input-inline">
								<input type="text" name="plantail.plan.versionReleasetime"
									value="${plantailForm.plantail.plan.versionReleasetime }" class="layui-input" disabled="false"
									onclick="layui.laydate({elem: this, festival: true})" readonly>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">版本类型：</label>
							<div class="layui-input-inline">
								<input type="text" name="plantail.plan.versionType"
									value="${plantailForm.plantail.plan.versionType }" class="layui-input" disabled="false">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">测试类型：</label>
							<div class="layui-input-inline" >
							<input type="text" name="plantail.plan.casetype.id" value="${plantailForm.plantail.plan.casetype.casetypeName}"
									class="layui-input" disabled="false">
								</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">测试内容：</label>
							<div class="layui-input-inline">
							<input type="text"  value="<c:forEach items='${plantailForm.plancasestores }' var='pc'>${pc.casestore.testModule },</c:forEach>"
									class="layui-input" disabled="false">
                        	</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">用例条数：</label>
							<div class="layui-input-inline">
							<input type="text" name="plantail.plan.caseCount" value="${plantailForm.plantail.plan.caseCount }"
									class="layui-input" disabled="false">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">人力投入(人.天)：</label>
							<div class="layui-input-inline">
								<input type="text" name="plantail.plan.humanInput"
									value="${plantailForm.plantail.plan.humanInput }" class="layui-input" disabled="false">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">总人力：</label>
							<div class="layui-input-inline">
								<input type="text" name="plantail.plan.humanCount"
									value="${plantailForm.plantail.plan.humanCount }" class="layui-input" disabled="false">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">需缺人力：</label>
							<div class="layui-input-inline">
								<input type="text" name="plantail.plan.humanNeed"
									value="${plantailForm.plantail.plan.humanNeed }" class="layui-input" disabled="false">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">测试机需求(台)：</label>
							<div class="layui-input-inline">
								<input type="text" name="plantail.plan.prototypeCount "
									value="${plantailForm.plantail.plan.prototypeCount }" class="layui-input" disabled="false">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">测试机复用(台)：</label>
							<div class="layui-input-inline">
								<input type="text" name="plantail.plan.prototypeReuse"
									value="${plantailForm.plantail.plan.prototypeReuse }" class="layui-input" disabled="false">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">开始时间：</label>
							<div class="layui-input-inline">
								<input type="text" name="plantail.plan.startTime"
									value="${plantailForm.plantail.plan.startTime }" class="layui-input" disabled="false"
									onclick="layui.laydate({elem: this, festival: true})" readonly>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">结束时间：</label>
							<div class="layui-input-inline">
								<input type="text" name="plantail.plan.endTime" value="${plantailForm.plantail.plan.endTime }"
									class="layui-input" disabled="false"
									onclick="layui.laydate({elem: this, festival: true})" readonly>
							</div>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">版本路径：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width: 700px;" name="plantail.plan.versionPath" disabled="false">${plantailForm.plantail.plan.versionPath }</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">版本计划：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width:700px;" name="plantail.plan.versionPlan" disabled="false">${plantailForm.plantail.plan.versionPlan }</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">下载工具：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width:700px;" name="plantail.plan.loadTool" disabled="false">${plantailForm.plantail.plan.loadTool}</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">归档路径：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width:700px;" name="plantail.plan.filePath" disabled="false">${plantailForm.plantail.plan.filePath }</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">BUG库路径：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width:700px;" name="plantail.plan.bugPath" disabled="false">${plantailForm.plantail.plan.bugPath }</textarea>
						</div>
					</div>
					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">备注：</label>
						<div class="layui-input-block">
							<textarea placeholder="请输入内容" class="layui-textarea"
								style="width:700px;" name="plantail.plan.remark" disabled="false">${plantailForm.plantail.plan.remark }</textarea>
						</div>
					</div>
					<td><button  type="reset" style="margin-left:70%;"> <a href="javascript:void(0);" onClick="window.history.back()">【返回】</a></button></td>
	</form>
	</div>
</body>
</html>
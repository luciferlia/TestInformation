<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>与德测试信息管理系统</title>

<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<jsp:include page="/admin/adminTop.jsp"></jsp:include>
<style>
td {
	font-size: 0.9em;
	font-color: black;
}

li {
	list-style: none;
}

.box {
	position: relative;
	border: 2px solid #B8B8B8;
	width: 80%;
	height: 700px;
	margin-left: 5%;
	margin-top: 30px;
}

.box::before {
	content: attr(title);
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
	-webkit-transform: translate(-50%, -50%);
	padding: 0 10px;
	background-color: #fff;
}

div.scroll {
	width: 70%;
	height: 90%;
	overflow: scroll;
}

.box1 {
	position: relative;
	border: 2px solid #B8B8B8;
	width: 95%;
	height: 700px;
	margin-left: 10px;
	margin-top: 30px;
}

.box1::before {
	content: attr(title);
	position: absolute;
	left: 50%;
	transform: translateX(-50%);
	-webkit-transform: translate(-50%, -50%);
	padding: 0 10px;
	background-color: #fff;
}
</style>
<script type="text/javascript">
 
      function checkdata(){
     
    var ids = '';
    $('input[type="checkbox"][name="checkid"]:checked').each(function(){
	ids += $(this).val() + ',';
    });
    
     }
   
    </script>

<script type="text/javascript">
 function console(consoleTag, stateTag) {
      var ids = '';
    $('input[type="checkbox"][name="checkedId"]:checked').each(function(){
	ids += $(this).val() + ',';
    });
    alert(ids);
    
        //循环获取选中的复选框的value，这个value是数据表中每条记录的主键${cd.caseId }，传给后台，后台就能根据主键查找到数据表的相应记录

       
     if( ids==""|| ids==null|| ids==0){
    layer.msg("请勾选要增加的人员");
     }
     else{
      
      layer.confirm('确定添加勾选的人员?', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                
                                 $('#checkedIds').val(ids);
           
        //删除
        if("add" == consoleTag) {
            $('#formid').prop("action", "Projecthuman_addHuman?id=${project.id}");
        }

        $('#formid').submit();
                                
                                },function(index){ layer.close(index);
								return false;
								});
	
}
  }      
                
</script>
<!-- 从数据库获取checkbox的value值并传到前台 -->
<script>
//获取到后台传来的值
var chk=value;//获取的值
$('.checkedId').each(function(){//循环遍历checkbox
var self=$(this);
var selfId=self.attr('id');
if(selfId!=null)//判断value值是否存在
{
$(this).prop("checked", true);//设置checkbox为勾选状态
}
});

</script>
</head>

<body>
	<table border="0" style="width:90%;margin-left:20px;" id="kk">
		<tr style="height:30px;">
			<td colspan="2">
				<ul style="margin-left:5%;">
					<li style="float:left;margin-top:1px;"><h4>角色名称：</h4></li>
					<li style="float:left;margin-top:7px;"><input type="text"
						class="form-control" placeholder="获取的项目名称" style="width:200px;"
						value="${project.projectName }" disabled="true"></li>
				</ul>
			</td>
		</tr>
		<tr>
			<td style="width:40%;">
				<div class="box" title="未有权限">
					<ul>
						<li style="float:left; margin-top:20px;"><h4>搜索权限</h4></li>
						<li style="float:left; margin-top:15px;"><input type="text"
							class="form-control" placeholder="请输入权限名"
							style="width:250px; margin-left:10px;"></li>
					</ul>
					<!--即时搜索显示结果-->

					<div class="scroll" style="margin-left:10%;margin-top:13%;">
						<ul id="cdTb">
						<%-- 	<c:forEach items="${users}" var="user">
								<li><input type="checkbox" value="${user.userId }"
									id="checkedId" name="checkedId">${user.name }</li>
							</c:forEach> --%>
							<li><input type="checkbox" value="1" id="checkedId" name="checkedId">1</li>
							<li><input type="checkbox" value="2" id="checkedId" name="checkedId">2</li>
							<li><input type="checkbox" value="3" id="checkedId" name="checkedId">3</li>
							<li><input type="checkbox" value="4" id="checkedId" name="checkedId">4</li>
							<li><input type="checkbox" value="5" id="checkedId" name="checkedId">5</li>

						</ul>
					</div>
					<button class="btn btn-primary" type="submit"
						style="margin-left:85%;" onclick="console('add','')">确定</button>
				</div>

			</td>
			<td>
				<div class="box1" title="已有权限" id="add">
					<div style="margin-left:2%;margin-top:1%;height:93%;">
                      <ul id="cdTb">
                      	<c:forEach items="${users}" var="user">
                            <li><input type="checkbox" value="${user.userId }" id="checkedId" name="checkedId">${user.name }</li>
                    	 </c:forEach>
                        	<li><input type="checkbox" value="1" id="checkedId" name="checkedId" checked="checked">1</li>
							<li><input type="checkbox" value="2" id="checkedId" name="checkedId" checked="checked">2</li>
							<li><input type="checkbox" value="3" id="checkedId" name="checkedId" checked="checked">3</li>
							<li><input type="checkbox" value="4" id="checkedId" name="checkedId" checked="checked">4</li>
							<li><input type="checkbox" value="5" id="checkedId" name="checkedId" checked="checked">5</li>
                    </ul>
                    </div>
                   <button class="btn btn-primary" type="submit" style="margin-left:85%;" onclick="console('delete','')">确定</button>
                 </div>
			</td>
		</tr>
	</table>

	<%-- 隐藏域表单 --%>
	<form action="" method="post" id="formid">
		<input type="hidden" name="checkedIds" id="checkedIds" />
	</form>
</body>
</html>

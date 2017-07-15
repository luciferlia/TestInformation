<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
<script src="script/ie.js"></script>

<jsp:include page="/wind/top.jsp"></jsp:include>

<style>

td{font-size:1em;font-color:black;}
.table  td{text-align:center;vertical-align:middle;}
li{list-style:none;}
   
        
        div.scroll {
            width: 200px;
            height: 500px;
            overflow: scroll;
        }
        
        .box1 {
            position: relative;
            border: 2px solid #B8B8B8;
            width: 85%;
            height:500px;
            margin-left: 10px;
            
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
       
       .scroll{  display:inline-block;
                         _zoom:1;
                         _display:inline;}
    </style>
    <script type="text/javascript">
 
 
 	function check(param1,param2){
 	
 		$.ajax({
 			type:'post',
 			url:'checkRole?uid='+param2,
 			success:function(data){
 				if(data=='success'){
 					window.location.href="projectUser_deleteUser?id="+param1+"&uid="+param2;
 				}else{
 				layer.msg('测试经理不能被删除',{time:3000});
 				}
	 				
	 			},
 		
 		});
 	}
 
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
     if( ids==""|| ids==null|| ids==0){
     layer.msg("请勾选要添加的人员");
     }
     else{
     layer.confirm('确定添加勾选的人员？', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                //table表中选中的复选框赋值给checkedSubject
        $('#checkedIds').val(ids);
           
        //删除
        if("add" == consoleTag) {
            $('#formid').prop("action", "projectUser_addUser?id=${project.id}");
        }

        $('#formid').submit();                                                             
                                },function(index){ layer.close(index);
								return false;
								});
     
}
  }  
       var pageSize=5;  //每页显示的记录条数   
        var curPage=0;   //显示第curPage页
        var len;         //总行数
        var page;        //总页数    
    $(function(){    
len =$("#table tr").length-1;   //去掉表头     
 page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
// console.log("len:"+len+"page:"+page);
 document.getElementById("page").innerHTML=page;
  for(var j=1;j<page+1;j++)
		{
		$('#curPage').append('<option value='+j+'>'+j+'</option>');
		}
 curPage=1;
 displayPage();//显示第一页
$("#nextpage").click(function(){//下一页
   if(curPage<page){
       curPage+=1;
   }
   else{
       layer.msg("已经是最后一页");
   }
  displayPage();
  });
$("#lastpage").click(function(){//上一页
   if(curPage>1){
       curPage-=1;
   }
   else{
     layer.msg("已经是第一页");
   }
   displayPage();
   });
$("#npage").click(function(){//跳到固定某一页
   var npage=parseInt(document.getElementById("curPage").value);
   if(npage>page||npage<1){
      layer.msg("该页不存在");
   }
   else{
       curPage=npage;
   }
   displayPage();
   });
});

function displayPage(){  
var begin=(curPage-1)*pageSize;//起始记录号
var end = begin + pageSize;

if(end > len ) end=len;
$("#table tr").hide();
$("#table tr").each(function(i){
  if(i-1>=begin && i-1<end)//显示第page页的记录
      {
      $("#show_tab_one").show();
      $(this).show();      
      document.getElementById("curPage1").innerHTML=curPage;

      }         
});

}        
function pageSize(){
curPage=0;   //显示第curPage页   
pageSize=parseInt(document.getElementById("pageSize").value);
len =$("#table tr").length-1;   //去掉表头     
page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
curPage=1;
displayPage();//显示第一页   
}
            
</script>

</head>

 <body>
    <h4 class="glyphicon glyphicon-tasks" style="margin-top:1%;margin-left:2%;">项目成员添加</h4>
    <hr />
     <table border="0" style="width:90%;margin-left:2%;margin-top:1%;">
<tr style="height:30px; font-size:1em"><td colspan="2">项目名称：${project.projectName }</td><td colspan="2">项目阶段：${project.testPhase }</td>
    </tr>
    <tr><td colspan="4"><ul style="margin-top:1%;"> <li  style="float:left; margin-top:7px;"><h3>搜索人员:</h3></li>
       <li  style="float:left;"><input class="form-control" value="" id="search" placeholder="输入名字或拼音"></li></ul> </td></tr></table>

            <table style="width:90%;margin-left:2%;margin-top:1%;"><tr><td>
                   <div class="scroll" id="t1"style="margin-left:1%;margin-top:13%;border:1px solid #B8B8B8;float:left;">
                  <c:if test="${role.roleName=='软测测试经理' }"> 主测<hr/></c:if><c:if test="${role.roleName=='软测主测' }">系统测试<hr/></c:if>
                                                                      
                     <ul id="searchname" style="margin-left:5%;">
                      	<c:forEach items="${urfs}" var="urf">
                      	<c:if test="${urf.roleName=='软测主测'||urf.roleName=='软测系统测试' }">
                           <li username="${urf.user.name }" pinyin="${urf.user.username }"> <input type="checkbox" value="${urf.user.userId }" id="checkedId" name="checkedId" >${urf.user.name }</li>
                    	</c:if>
                    	 </c:forEach>
                        </ul>
                    
                 
                    </div>
                    </td><td>
                    <c:if test="${role.roleName=='软测测试经理' }">
                   
                    <div class="scroll" id="t2" style="margin-top:13%;margin-left:12%;border:1px solid #B8B8B8;float:left;">
                   组长<hr/>
                                                                      
                     <ul id="searchname1" style="margin-left:5%;">
                      	<c:forEach items="${urfs}" var="urf">
                      	<c:if test="${urf.roleName=='软测组长' }">
                           <li username="${urf.user.name }" pinyin="${urf.user.username }"> <input type="checkbox" value="${urf.user.userId }" id="checkedId" name="checkedId" >${urf.user.name }</li>
                    	 </c:if>
                    	 </c:forEach>
                        </ul>
                    
                 
                    </div>      
            </c:if></td><td>
           <button class="btn btn-primary" type="submit" onclick="console('add','')">添加>></button></td>
            <td style="min-width:500px;">
                <div class="box1"   style="margin-left:2%;margin-top:3%;">
              
                    <table class="table table-hover table-striped" id="table" style="margin-top:1%;border:1px solid #B8B8B8">
                        
                            <tr style="background-color:#4682B4;height:40px;"  id="show_tab_one">
                                <td><h4>成员</h4></td>
                                <td><h4>角色</h4></td>
                                <td><h4>操作</h4></td>
                            </tr>
                            <!--从数据库中读取，展现在表格中-->
                        
                           <tbody id="show_tab_tr">  
                        	<c:forEach items="${pufs}" var="pu">
                            <tr style="height:50px;">
                                    <td>${pu.user.name }</td>
                                    <td>${pu.role }  </td>
                                    <div>
                                        <td>   
                                        	<c:if test="${pu.role!='测试经理' }">                                         
                                            <a onclick="check(${project.id },${pu.user.userId});" class="btn btn-danger btn-sm">删除</a>
                                            </c:if>
                                        </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                    </table>
                       <table style="width:100%;font-size:0.9em;"><tr><td>
                    共&nbsp;&nbsp;<span id="page" ></span>&nbsp;&nbsp;页&nbsp;&nbsp;|&nbsp;&nbsp;第&nbsp;&nbsp;<span id="curPage1"></span>&nbsp;&nbsp;页
                     <span style="float:right;">
                    <input id="lastpage" type="button" value="上一页" >
   <input id="nextpage" type="button" value="下一页">
   <select id="curPage"></select>
    <input id="npage" type="button" value="确定" class="btn btn-primary btn-xs" style="border:1px solid black;"></span>
   
    </td></tr></table>
                    </div></td></tr></table>
              
     <%-- 隐藏域表单 --%>
    <form action="" method="post" id="formid">
        <input type="hidden" name="checkedIds" id="checkedIds" />
    </form>
     <script>
     function searchname(){
     var name=$('#search').val();
     
     if(name=='')
     {
     $('#searchname  li').show();
       $('#searchname1  li').show();
     
     }
     else{
     $('#searchname li').each(
     function(){
     var names=$(this).attr('username');
     var pinyin=$(this).attr('pinyin');
     if(names.indexOf(name) != -1 || pinyin.indexOf(name) != -1){
     $(this).show();
     }else{
     $(this).hide();
     }
     }
     );
      $('#searchname1 li').each(
     function(){
     var names1=$(this).attr('username');
     var pinyin1=$(this).attr('pinyin');
     if(names1.indexOf(name) != -1 || pinyin1.indexOf(name) != -1){
     $(this).show();
     }else{
     $(this).hide();
     }
     }
     );
     }
     }
     $('#search').bind('input propertychange',function(){
     searchname();
     });
     </script>
  
</body>
</html>

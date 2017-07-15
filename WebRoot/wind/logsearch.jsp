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
    
      <link rel="shortcut icon" href="images/logio.ico">
    <title>测试信息化系统</title>	
    <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="script/css/bootstrap.min.css">
<script src="script/js/jquery.min.js"></script>
<script src="script/js/bootstrap.min.js"></script>
  <script src="script/ie.js"></script>

    <jsp:include page="/wind/top.jsp"></jsp:include>
	<style type="text/css">
	#table td{border:1px solid #EDEDED;text-align:center;vertical-align:middle;font-size:0.9em;}
	li{float:left;}	
	
	</style>
	<script>
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
//console.log("  begin:"+len+"   end:"+end);
if(end > len ) end=len;
$("#table tr").hide();
$("#table tr").each(function(i){
  if(i-1>=begin && i-1<end)//显示第page页的记录
      {
      $("#show_tab_one").show();
      $(this).show();
      //document.getElementById("curPage").value=curPage;
      document.getElementById("curPage1").innerHTML=curPage;
      
		//alert(curPage);
		
      }         
});

}        
function pageSize(){
curPage=0;   //显示第curPage页   
pageSize=parseInt(document.getElementById("pageSize").value);
len =$("#table tr").length-1;   //去掉表头     
page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
//console.log("len:"+len+"   page:"+page);
// document.getElementById("page").value=page;


curPage=1;
displayPage();//显示第一页   
}
            
	</script>
	<script>
	layui.use('element', function() {
		var element = layui.element();
	});
</script>
<script src="script/mytask.js"></script>
<script>
	layui.use([ 'form', 'layedit', 'laydate' ], function() {
		var form = layui.form(),
			layer = layui.layer,
			layedit = layui.layedit,
			laydate = layui.laydate;


	});
</script>
</head>
<body>
<form class="layui-form" >
    <table style="width:90%;margin-left:2%;font-size:0.9em;" class="dd">
    <tr style="height:60px;"><td ><ul><li>业务名称:</li><li><input type="text" class="form-control"style="width:100px;"></li>
      <li>&nbsp;&nbsp;操&nbsp;&nbsp;作&nbsp;&nbsp;人:</li><li><input type="text" class="form-control"style="width:100px;"></li>
      <li>&nbsp;&nbsp;操作类型:</li><li><select name="" style="width:100px;">
									<option value="不限">不限</option>
									<option value="0">删除</option>
									<option value="1">添加</option>
									<option value="2">更新</option>
								</select></li></ul></td>
    <td></td></tr>
    <tr><td><ul><li>业务键值:</li><li><input type="text" class="form-control"style="width:100px;"></li>
    <li>&nbsp;&nbsp;操作时间:</li><li><input type="text" class="form-control"style="width:100px;"id="LAY_demorange_s"
						readonly></li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li><li><input type="text" class="form-control"style="width:100px;"id="LAY_demorange_e"
						readonly></li></ul></td>
    <td><button type="submit" class="btn btn-info" onclick="" style="float:right;">查找</button></td></tr>
    </table>
    </form>
	<table id="table" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:90%;margin-left:2%;" class="table table-condensed table-bordered table-hover table-striped">
	<tr style="background-color:#4682B4;height:40px;color: white;" id="show_tab_one">
	<td style="width:50px;">序号</td><td>表名</td><td>业务名称</td><td>操作人</td><td>操作类型</td><td>操作时间</td><td>业务主键值</td><td>操作</td></tr>
	<tbody id="show_tab_tr">
	
	<s:iterator value="pageBean.list">
	<tr style="height:40px;"><td ><s:property value="id"/> </td>
	<td><s:property value="tableName"/></td>
	<td><s:property value="name"/></td>
	<td><s:property value="user.name"/></td>
	<td><s:property value="type"/></td>
	<td><s:property value="time"/></td>
	<td><s:property value="primyKey"/></td>
	<td><a href="log_showLogDetail?id=<s:property value="id"/>">查看</a> | <a href="log_deleteLog?id=<s:property value="id"/>">删除</a> <!-- | <a onclick="">恢复</a> --></td>
	</tr>
	</s:iterator>
	</tbody>
	</table>
	<%-- <table style="width:90%;margin-left:2%;font-size:0.9em;"><tr><td>
                    共&nbsp;&nbsp;<span id="page" ></span>&nbsp;&nbsp;页&nbsp;&nbsp;|&nbsp;&nbsp;第&nbsp;&nbsp;<span id="curPage1"></span>&nbsp;&nbsp;页
                     <span style="float:right;">
                    <input id="lastpage" type="button" value="上一页" >
   <input id="nextpage" type="button" value="下一页">
   <select id="curPage"></select>
    <input id="npage" type="button" value="确定" class="btn btn-primary btn-xs" style="border:1px solid black;"></span>
   
    </td></tr></table> --%>
    
    <table style="width:80%;margin-left:5%;" class="noprint">
		<tr>
			<td><span style="float:left;">共<span><s:property
							value="pageBean.totalPage" /></span>页|第<span><s:property
							value="pageBean.currentPage" /> </span>页
			</span></td>
			<td><span style="float:right;"><form
						action="log_showAllLog" class="right-font08">
						[
						<s:if test="%{pageBean.currentPage==1}">首页 | 上一页</s:if>
						<s:else>
							<a href="log_showAllLog?page=1" class="right-font08">首页</a> | <a
								href="log_showAllLog?page=<s:property value="%{pageBean.currentPage-1}"/> "
								class="right-font08">上一页</a>
						</s:else>
						|
						<s:if test="%{pageBean.currentPage!=pageBean.totalPage}">
							<a
								href="log_showAllLog?page=<s:property value="%{pageBean.currentPage+1}"/> "
								class="right-font08">下一页</a> | <a
								href="log_showAllLog?page=<s:property value="%{pageBean.totalPage}"/>"
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
						<input class="btn btn-primary btn-xs"
							style="border:1px solid black;" value="确定" type="submit">
					</form></span></td>
		</tr>
	</table>
    <script>
    layui.use('laydate', function(){
  var laydate = layui.laydate;
  
  var start = {
    min: laydate.now()
    ,max: '2099-06-16 23:59:59'
    ,istoday: false
    ,choose: function(datas){
      end.min = datas; //开始日选好后，重置结束日的最小日期
      end.start = datas //将结束日的初始值设定为开始日
    }
  };
  
  var end = {
    min: laydate.now()
    ,max: '2099-06-16 23:59:59'
    ,istoday: false
    ,choose: function(datas){
      start.max = datas; //结束日选好后，重置开始日的最大日期
    }
  };
  
  document.getElementById('LAY_demorange_s').onclick = function(){
    start.elem = this;
    laydate(start);
  }
  document.getElementById('LAY_demorange_e').onclick = function(){
    end.elem = this;
    laydate(end);
  }
  
});
    </script>
</body>
</html>

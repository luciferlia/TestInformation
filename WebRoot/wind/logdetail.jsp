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
	var pageSize=10;  //每页显示的记录条数   
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
     <span style="margin-left:2%;">查看日志详细内容  业务名称：<font color="red">${name }</font></span>
     <hr/>
	<table id="table" style="TABLE-LAYOUT:fixed;WORD-BREAK:break-all;width:90%;margin-left:2%;" class="table table-condensed table-bordered table-hover table-striped">
	<tr style="background-color:#4682B4;height:40px;" id="show_tab_one">
	<td style="width:50px;">序号</td><td>字段</td><td>名称</td><td>旧值</td><td>新值</td></tr>
	<tbody id="show_tab_tr">
	<c:forEach items="${logsettingdetails }" var="lst">
	<tr style="height:40px;">
	<td>${lst.id }</td>
	<td>${lst.columnName }</td>
	<td>${lst.columnText }</td>
	<td>${lst.oldValue }</td>
	<td>${lst.newValue }</td>
	
	</tr>
	</c:forEach>
	</tbody>
	</table>
	<table style="width:90%;margin-left:2%;font-size:0.9em;"><tr><td>
                    共&nbsp;&nbsp;<span id="page" ></span>&nbsp;&nbsp;页&nbsp;&nbsp;|&nbsp;&nbsp;第&nbsp;&nbsp;<span id="curPage1"></span>&nbsp;&nbsp;页
                     <span style="float:right;">
                    <input id="lastpage" type="button" value="上一页" >
   <input id="nextpage" type="button" value="下一页">
   <select id="curPage"></select>
    <input id="npage" type="button" value="确定" class="btn btn-primary btn-xs" style="border:1px solid black;"></span>
   
    </td></tr></table>
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

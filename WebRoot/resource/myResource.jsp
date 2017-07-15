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
<script src="script/js/jquery.min.js"></script>
<jsp:include page="/wind/top.jsp"></jsp:include>
<script src="script/js/bootstrap.min.js"></script>
<style>
 .box {
            position: relative;
            border: 2px solid #B8B8B8;
            width: 30%;
            min-width:300px;
            height: 500px;
            margin-left: 1%;
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
         .box1 {
            position: relative;
            border: 2px solid #B8B8B8;
            width: 30%;
               min-width:300px;
            height: 500px;
            margin-left: 1%;
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
          .box2 {
            position: relative;
            border: 2px solid #B8B8B8;
            width: 30%;
             min-width:300px;
            height: 500px;
            margin-left: 1%;
            margin-top: 30px;
        }
          .box2::before {
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

</script>
</head>
<body>
		<h3 class="glyphicon glyphicon-tasks" style="margin-left:5%;">我的资源</h3>
		<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
			width="90%" color=#987cb9 SIZE=10>
			<table style="width:100%;min-width:800px;margin-left:2%;">
			<tr>
			<td >
			 <div class="box" title="我的财富">
                <ul>
                    <li style=" margin-top:15px;"><font color="#2F4F4F" size="4"><span>移动SIM卡</span><span>1364534re</span>   </font></li>
                    <li style="margin-top:15px;"><font color="#2F4F4F" size="4"><span>移动SIM卡</span><span>1364534re</span> </li>
                    </ul>                  
                </div>
                </td>
                <td>
                 <div class="box1" title="所借资源">
                <ul>
                    <li style=" margin-top:15px;"><font color="#2F4F4F" size="4"><span>移动SIM卡</span><span>adsfsdfs</span>   </font></li>
                    <li style="margin-top:15px;"><font color="#2F4F4F" size="4"><span>移动SIM卡</span><span>sdgfgsf</span> </li>
                    <li style=" margin-top:15px;"><font color="#2F4F4F" size="4"><span>蓝牙</span><span>是的re</span>   </font></li>
                    <li style="margin-top:15px;"><font color="#2F4F4F" size="4"><span>华硕样机</span><span>1台</span> </li>
                    </ul>                  
                </div>
                </td>
                  <td>
                 <div class="box2" title="损失资源">
                <ul>
                    <li style=" margin-top:15px;"><font color="#2F4F4F" size="4"><span>移动SIM卡</span><span>adsfsdfs</span> <span>损坏</span>  </font></li>
                    <li style="margin-top:15px;"><font color="#2F4F4F" size="4"><span>移动SIM卡</span><span>sdgfgsf</span> <span>丢失</span></li>
                    <li style=" margin-top:15px;"><font color="#2F4F4F" size="4"><span>蓝牙</span><span>是的re</span><span>丢失</span>   </font></li>
                    <li style="margin-top:15px;"><font color="#2F4F4F" size="4"><span>华硕样机</span><span>1台</span><span>损坏</span> </li>
                    </ul>                  
                </div>
                </td>
                </tr>
                </table>
  </body>
</html>

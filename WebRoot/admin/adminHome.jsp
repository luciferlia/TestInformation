<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>与德测试信息管理系统</title>
<link rel="stylesheet" href="script/css/bootstrap.min.css">
 <script src="http://cdn.bootcss.com/jquery/1.12.3/jquery.min.js"></script>
<script src="script/layer.js"></script>
<script src="script/logout.js"></script>
<jsp:include page="/admin/adminTop.jsp"></jsp:include>

	<style type="text/css">
	#table span {font-size:1.5em;}
	
	.panel{
	width:90%;
	}
		a:hover,a:focus{
		    text-decoration: none;
		    outline: none;
		}
		#accordion{
		    padding-right: 24px;
		    padding-left: 24px;
		    z-index: 1;
		}
		#accordion .panel{
		    border: none;
		    box-shadow: none;
		}
		#accordion .panel-heading{
		    padding: 0;
		    border-radius: 0;
		    border: none;
		}
		#accordion .panel-title{
		    padding: 0;
		}
		#accordion .panel-title a{
		    display: block;
		    font-size: 16px;
		    font-weight: bold;
		    background: #4682B4;
		   
		    color: white;
		    padding: 15px 25px;
		    position: relative;
		    margin-left: -24px;
		    transition: all 0.3s ease 0s;
		}
		#accordion .panel-title a.collapsed{
		    background: #87CEFA;
		    color: black;
		  
		    margin-left: 0;
		    transition: all 0.3s ease 0s;
		}
		#accordion .panel-title a:before{
		    content: "";
		    border-left: 24px solid #4682B4;
		    border-top: 24px solid transparent;
		    border-bottom: 24px solid transparent;
		    position: absolute;
		    top: 0;
		    right: -24px;
		    transition: all 0.3s ease 0s;
		}
		#accordion .panel-title a.collapsed:before{
		    border-left-color: #87CEFA;
		   
		}
		#accordion .panel-title a:after{
		    content: "\f106";
		    font-family: 'FontAwesome';
		    position: absolute;
		    top: 30%;
		    right: 15px;
		    font-size: 18px;
		    color: white;
		}
		#accordion .panel-title a.collapsed:after{
		    content: "\f107";
		    color: black;
		}
		#accordion .panel-collapse{
		    position: relative;
		}
		#accordion .panel-collapse.in:before{
		    content: "";
		    border-right: 24px solid #4682B4;
		    border-bottom: 18px solid transparent;
		    position: absolute;
		    top: 0;
		    left: -24px;
		}
		#accordion .panel-body{
		    border:1px solid #4682B4;
		    font-size: 14px;
		    color: black;
		    background: white;
		    border-top: none;
		    z-index: 1;
		}
	</style>
</head>
<body>
	

		
		                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" style="width:70%;height:100%;min-width:800px;margin-left:2%;margin-top:2%;float:left;">
		                  
		                        <div class="panel panel-default">
		                            <div class="panel-heading " role="tab" id="headingOne">
		                                <h4 class="panel-title">
		                                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" >
		                                    <span class="glyphicon glyphicon-tasks"></span>
		                                       我的任务
		                                    </a>
		                                </h4>
		                            </div>
		                            <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
		                              <form>
		                                <div class="panel-body" style="min-height:200px;">
		                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nisl lorem, dictum id pellentesque at, vestibulum ut arcu. Curabitur erat libero, egestas eu tincidunt ac, rutrum ac justo. Vivamus condimentum laoreet lectus, blandit posuere tortor aliquam vitae. Curabitur molestie eros. </p>
		                                </div>
		                                </form>
		                            </div>
		                        </div>
		                        
		                        
		                        <div class="panel panel-default">
		                            <div class="panel-heading" role="tab" id="headingTwo">
		                                <h4 class="panel-title">
		                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
		                                     <span class="glyphicon glyphicon-tasks"></span>
		                                        我的项目
		                                    </a>
		                                </h4>
		                            </div>
		                            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
		                                <div class="panel-body" style="min-height:200px;">
		                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nisl lorem, dictum id pellentesque at, vestibulum ut arcu. Curabitur erat libero, egestas eu tincidunt ac, rutrum ac justo. Vivamus condimentum laoreet lectus, blandit posuere tortor aliquam vitae. Curabitur molestie eros. </p>
		                                </div>
		                            </div>
		                        </div>
		                      
		                        <div class="panel panel-default">
		                            <div class="panel-heading" role="tab" id="headingThree">
		                                <h4 class="panel-title">
		                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
		                                     <span class="glyphicon glyphicon-tasks"></span>
		                                        我的资源
		                                    </a>
		                                </h4>
		                            </div>
		                            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
		                                <div class="panel-body" style="min-height:200px;">
		                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nisl lorem, dictum id pellentesque at, vestibulum ut arcu. Curabitur erat libero, egestas eu tincidunt ac, rutrum ac justo. Vivamus condimentum laoreet lectus, blandit posuere tortor aliquam vitae. Curabitur molestie eros. </p>
		                                </div>
		                            </div>
		                        </div>
								<div class="panel panel-default">
		                            <div class="panel-heading" role="tab" id="headingFour">
		                                <h4 class="panel-title" >
		                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
		                                     <span class="glyphicon glyphicon-tasks"></span>
		                                        我的请求
		                                    </a>
		                                </h4>
		                            </div>
		                            <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
		                                <div class="panel-body" style="min-height:200px;">
		                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nisl lorem, dictum id pellentesque at, vestibulum ut arcu. Curabitur erat libero, egestas eu tincidunt ac, rutrum ac justo. Vivamus condimentum laoreet lectus, blandit posuere tortor aliquam vitae. Curabitur molestie eros. </p>
		                                </div>
		                            </div>
		                        </div>
								 <div class="panel panel-default">
		                            <div class="panel-heading" role="tab" id="headingFive">
		                                <h4 class="panel-title">
		                                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
		                                     <span class="glyphicon glyphicon-tasks"></span>
		                                        我的管理
		                                    </a>
		                                </h4>
		                            </div>
		                            <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
		                                <div class="panel-body" style="min-height:200px;">
		                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nisl lorem, dictum id pellentesque at, vestibulum ut arcu. Curabitur erat libero, egestas eu tincidunt ac, rutrum ac justo. Vivamus condimentum laoreet lectus, blandit posuere tortor aliquam vitae. Curabitur molestie eros. </p>
		                                </div>
		                            </div>
		                        </div>
		                    </div>
		  <div style="float:left;margin-top:2%;">
		  <table style="width:2px;border-right:2px solid #4682B4;height:600px;"><tr><td></td></tr></table></div>
          <div style="float:left;width:25%;height:100%;margin-left:2%;marfin-top:2%;min-height:400px;">
          
          <table style="width:100%;margin-left:2%;margin-top:10%;height:50%;min-height:100px;max-height:200px;min-width:350px;" id="table">
          <tr>
          <td><span class="glyphicon glyphicon-envelope"> <a href="http://mail.wind-mobi.com/" target="_blank">邮箱</a></span></td>
          <td><span class="	glyphicon glyphicon-list-alt"> <a href="http://10.0.80.8/ocm/Api/Auth/Login" target="_blank">EDOC</a></span></td>
          <td><span class="	glyphicon glyphicon-book"> <a href="http://10.0.10.6/windwiki/index.php/%E9%A6%96%E9%A1%B5" target="_blank">WiKi</a></span></td>
          </tr>
          <tr>
          <td><span class="	glyphicon glyphicon-leaf"> <a href="http://10.0.10.18/redmine/login" target="_blank">Redmine</a></span></td>
          <td><span class="	glyphicon glyphicon-folder-open"> <a href="http://10.0.80.5/wui/theme/ecology7/page/login.jsp" target="_blank">OA</a></span></td>
          <td><span class="	glyphicon glyphicon-briefcase"> <a href="http://wu/" target="_blank">ERP</a></span></td>
          </tr>
          </table>
           <HR style="FILTER: alpha(opacity=100,finishopacity=0,style=2)"
									width="100%" color=#987cb9 SIZE=10></div>
	
	<script src="http://cdn.bootcss.com/jquery/1.11.0/jquery.min.js" type="text/javascript"></script>
	<script>window.jQuery || document.write('<script src="script/js/jquery-1.11.0.min.js"><\/script>')</script>
	<script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
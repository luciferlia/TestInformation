/**
 * 
 */
    
	function updateshow(obj){
		
		var tr=obj.parentNode.parentNode;
		
		var l1=$(tr).children('td').eq(1).text();
		var l2=$(tr).children('td').eq(9).text();
		var l3=$(tr).children('td').eq(10).text();
		var l4=$(tr).children('td').eq(6).text();
		var l5=$(tr).children('td').eq(7).children('div').text();
		var l6=$(tr).children('td').eq(8).text();
		
		var n1=document.getElementById('l1');
		var n2=document.getElementById('l2');
		var n3=document.getElementById('l3');
		var n4=document.getElementById('l4');
		var n5=document.getElementById('l5');
		var n6=document.getElementById('l6');
		
		n1.value=l1;
		n2.value=l2;
		n3.value=l3;
		n4.value=l4;
		n5.value=l5;
		n6.value=l6;
		 
		
/*---------------------------------------成员----------------------------------------------------------------------------------------------*/	 			
		 var planId=document.getElementById("l1").value;
		 var name=document.getElementById("l5").value;
		 console.log(name);
		$.ajax({
         type: "POST",
     	url:"showUserMsg?id="+planId,
     success: function(data){        
   if(data=="fail"){
	   layer.msg("您没有获取人员信息的权限",{time:2000});
   }else{
	   var sell=document.getElementsByName('plantail.user.userId');
	     var str="";
	     var ss=data.substr(1, data.length-2).split(",");
	     for(var i=0;i<ss.length;i++)
	       {	       
	      var name=ss[i].split("_")[0];
	      var id=ss[i].split("_")[1];	
	      var time=ss[i].split("_")[2];
	      str +='<option value="'+id+'">'+name+'</option>';
	       }  
	  	 	//sel.innerHTML=str;
	  	 	for(k=0;k<sell.length;k++)
	  	 		{
	  	 		sell[k].innerHTML=str;
	  	 		}
   }
     
     },
     error : function(xhx,e,errMsg) {
     alert(errMsg);
     }
  });
/*---------------------------------------等级和数目----------------------------------------------------------------------------------------------*/	 			
		$.ajax({
	         type: "POST",
	     	url:"showCaseLevel?name="+name,
	     success: function(data){        
	   if(data=="fail"){
		   layer.msg("您没有获取人员信息的权限",{time:2000});
	   }else{
		
		  var strr=data.split(",");
		  var long=strr.length;
		  //将用例编号分解写在框里
		var end=strr[long-2];		
		var start=strr[long-3];		
		var timenum=document.getElementById('timenum');
		/*var startl=document.getElementsByName("start");
		var endl=document.getElementsByName("end");*/
		/*for(j=0;j<startl.length;j++)
			{
			startl[j].value=start;
			endl[j].value=end;
			}
		*/
		timenum.innerHTML=strr[long-1]+"天";
		
		var start=strr[long-3];		
		n6.value=end;
		
		//分解等级
		var html="";
		for(k=0;k<long-3;k++)
		{
			var str=strr[k].split("_");
			html+='<li style="float:left;"><input type="checkbox" name="checkId" value="'+str[0]+'">'+str[0]+'&nbsp;&nbsp;&nbsp;'+str[1]+'天&nbsp;&nbsp;</li>';
			}
	   }
	     $('.first').after(html);
	     },
	     error : function(xhx,e,errMsg) {
	     alert(errMsg);
	     }
	  });
	  
/*-------------------------------------------------------------------------------------------------------------------------------------------*/	 			
		 var str=l5.split(",");
		 var html=[];
		 var fu=document.getElementById('sel');
		 var rr=fu.parentNode.parentNode.parentNode.parentNode.parentNode;
		 var nn6=n6.parentNode.parentNode;
		 if(str.length<=2)
			 {
			 //如果只有一个模块，则显示布局需要改变，涉及到一个模块分配给多个模块的方式
			
			$(rr).hide();
			// n5.disabled=true;
			 var usehtml='<tr><td></td><td ><ul><li style="float:left"  class="first"><button type="button" onclick="clone(this);">复制</button></li>'+
					'<li style="float:left">&nbsp;&nbsp;&nbsp;或者&nbsp;&nbsp;&nbsp;</li>'+
					'<li style="float:left"><input type="text" onkeyup="num(this)" style="width:40px" name="start" value="" >--'+
					'<input type="text" style="width:40px" onkeyup="num(this)" name="end" value=""></li>'+
					'<li style="float:left"><select name="plantail.user.userId" onChange="showtime(this);" style="width:80px;" ><option value="id"></option></select></li>'+
					'<li style="float:left"><button onclick="deletee(this);">删除</button></li></ul></td></tr>';
			 $(nn6).after(usehtml);
			 
			 }
		 else{
			 $(rr).show();
			 			 
		 }	
 /*-------------------------------------------------------------------------------------------------------------------------------------------*/	 			 
		 layer.open({
				type: 1,
				title:'分配测试任务',
				maxmin: true,
				skin: 'layui-layer-rim', //加上边框
				area: ['800px', '610px'], //宽高
				content:$("#showee"),
					btn: ['确定', '取消']
	           ,yes: function(){
	        	   //验证表单  验证通过则可以提交表单
	        	   
	        	   
	        	   var e=0,c=0,d=0,e1=0,c1=0,d1=0,ste=0,sed,pde,te,dsum=0,ssum=0,esum=0;
	        	    var su=0,sun=0;
	        	    var yuu=0;
	        	    var startl=document.getElementsByName('start');
	        	    var endl=document.getElementsByName("end");
	        	    var h=document.getElementById("l5").value;
	        	    var sell=document.getElementsByName('plantail.user.userId');
	        	    if(startl.length==0){
	        	    	e=1,e1=1,sun=0,su=0;
	        	    }
	        	    else{
/*-------------------------------------------------------------------------------------------------------------------------------------------*/	
	        	    	//判断等级或者用例编号是否其中一个写,且只写了一个
	        	    	var checkedSubject = $('#fen1 input[name=checkId]:checkbox:checked');
		        		var checkedIds = "";
		        			checkedSubject.each(function() {
		        				checkedIds = checkedIds + "," + $(this).val();
		        			});
		        			if(checkedIds == "" || checkedIds == null || checkedIds == 0 ) 
		        			{c=0;}else{c=1;}
		        			
		        			for(z=0;z<startl.length;z++)
		        				{
		        				if(startl[z].value==null||startl[z].value==''){
		        					continue;
		        					}			
		        				ssum +=parseFloat(startl[z].value);
		        				if(endl[z].value==null||endl[z].value==''){
		        					continue;
		        					}	
		        				esum +=parseFloat(endl[z].value);
		        				}	
		        			dsum=parseFloat(ssum+esum);
		        			if(dsum==0 || dsum==""||dsum==null)
	    					{
	    					d=0;
	    					
	    					}
	    				else{
	    					d=1;
	    				}	    
		        			 e=c+d;
	        			
/*-------------------------------------------------------------------------------------------------------------------------------------------*/	 	        			
	        			//判断 等级是否重复
		        			 var ssd=checkedIds.split(",");	        		
			        			var objs = {}
			        	        ssd.forEach(function(v,k){
			        	            if(objs[v]){
			        	                objs[v]++;
			        	            }else{
			        	                objs[v] = 1;
			        	            }
			        	        })	        	        
			        	        var prop="";
			        			for(var p in objs)
			        				{
			        				if(typeof(objs[p])=="function"){objs[p]();}
			        				else{
			        					prop +=objs[p]+",";
			        				}
			        				
			        				}	
			        			console.log(objs);
			        			var sty=prop.split(",");
			        			
			        			for(u=1;u<sty.length-1;u++)
			        				{
			        				if(sty[u]>1)
			        					{
			        					su=1;
			        					break;
			        					}
			        			
			        				}
/*-------------------------------------------------------------------------------------------------------------------------------------------*/	        			
	        		
	        		//判断分配的用例编号是否重复
	        			
			        			for(m=0;m<startl.length;m++){
				        			var starts=startl[m].value;
				        			
				        			}
				        			
				        			
				        			
				        			for(n=0;n<endl.length;n++){
				        			var ends=endl[n].value;
				        			}
				        			var sta,star,en;
				        			for(p=0;p<startl.length-1;p++)
				        				{
				        				 sta=startl[p+1].value;
				        				 star=startl[p].value;
				        				 en=endl[p].value;
				        				 enl=endl[p+1].value;
				        				 if(sta==null||sta==""||enl==null||enl=="")
				        					 {
				        					 yuu=0;	        				
				        					 }
				        				 else if((sta<=en) && (sta>=star)||(enl>=star)&&(enl<=en)||(sta<=star)&&(enl>=en))
				        					{
				        					yuu=1;	        				
				        					}	        				 
				        				else{	        					
				        					yuu=0;	        				
				        				}
				        				sun +=yuu;
				        				}
/*-------------------------------------------------------------------------------------------------------------------------------------------*/	 	        	    
	        	//判断同一行内是否等级和编号有一个写了
	        			
				        			for(r=0;r<startl.length;r++)
			        				 {
			        				
			        				 if(startl[r].value==""||startl[r].value==null||endl[r].value==""||endl[r].value==null) {d1=0;}else{d1=1;}
			        				 
			        				 if((startl[r].value==""&&endl[r].value!="")||(startl[r].value!=""&&endl[r].value=="")) 
			        				 {te=1;}else{te=0;}
				        			
			        				 
			        				 var sttt=startl[r].parentNode.parentNode.parentNode.parentNode;
			        				
			        				$(sttt).each(function(){
			        					var checkedbox=$(this).find(":checkbox:checked");
			        					
			        					 if(checkedbox.length > 0){
			        		                c1=1;
			        		             }
			        					 else{c1=0; }
			        				})	
			        	        			e1=c1+d1; 
			        			//	alert(e1+"      ,"+c1+"      ,"+d1);
			        				
			        				ste +=te;
			        				 }	
	        		
/*-------------------------------------------------------------------------------------------------------------------------------------------*/		        			 
	        	//验证等级或者用例数是否分完
	        	/*		  var sdata=temp.split(",");
	        			  var long=sdata.length;
	        			  //将用例编号分解写在框里
	        			var ssend=sdata[long-1];
	        				
	        			 var max=startl[0].value;
	        			 for(x=0;x<startl.length;x++){
	        				 
	        				 if( max < startl[x].value ){
	        					    max = startl[x].value;
	        					  }	        				 
	        			 }
	        			 if(max!=null||max!="")
	        				 {sed=0;}
	        			 else if(max<parseFloat(ssend))
    					 {
    					 sed=1;
    					 }
    				 else{sed=0;}
	        			 
	        			 
	        			 if((ssd.length-1)<(long-2)&&(ssd.length-1)!=0)
	        			 {
	        				 pde=0;
	        			 }
	        			 else{pde=1;}*/
	        			 
	        			 }
	        		      
 /*-------------------------------------------------------------------------------------------------------------------------------------------*/	
	        	    //将每行的数据组成字段
	        	    var arrsum="";
	   			 for(t=0;t<startl.length;t++)
	   			 {
	   			 var st=startl[t].parentNode.parentNode.parentNode.parentNode;
	   			
	   		          var array=[];
	   		         var sta1=startl[t].value;
	   				 var en1=endl[t].value;
	   				 var se1=$(sell[t+1]).val();
	   		         var json = "";
	   		         $(st).find("input[name=checkId]:checkbox:checked").each(function (i){
	   		            json = json +$(this).val()+ "-";
	   		         });
	   		         
	      		        array.push(json,sta1,en1,se1);
	      		      console.log(array);
	      		  var arrs=array[0]+","+array[1]+","+array[2]+","+array[3]+"/";
	      		   
	      		   arrsum += arrs;

	   	    }

	        			
	        			 var ssr=document.getElementById('trdata');
	        			 ssr.value=arrsum;
	        			
	        			 var person=document.getElementById('sel').value;
	        			 var testcon=document.getElementById("l5").value;
	        			 var testnum=document.getElementById("l6").value;
	        			 var starttime=document.getElementById("l2").value;
	        			 var endtime=document.getElementById("l3").value;
	        			 var remark=document.getElementById("l7").value;
	        			 var sumall=document.getElementById("trdata").value;
	        			 var array=[];
	        			 var json = {};
	        			 json={planId,person,testcon,testnum,starttime,endtime,remark,sumall};
	        	         array.push(json);
	        	         if(starttime==""||starttime==null||endtime==""||endtime==null)
	     	        	{
	     	        	layer.msg("起始时间和结束时间不能为空");
	     	        	return false;
	     	        	} else if (e==0) {
	     	        				layer.msg("用例分配不能为空");
	     	        				return false;
	     	        			} 
	     	        	
	     	        	else if (e==2) {
	         				layer.msg("等级和用例分配不能都有");
	         				return false;
	         			} 
	     	        	
	     	        	else if (ste>=1) {
	         				layer.msg("同一行内用例编号不能有一个为空");
	         				return false;
	         			} 
	     	        	else if (e1==0) {
	         				layer.msg("同一行内等级和用例编号不能同时为空");
	         				return false;
	         			} else if (e1>1) {
	         				layer.msg("同一行内等级和用例编号不能同时有值");
	         				return false;
	         			} 
	     	        			else if (h==0) {
	     	        				layer.msg("测试内容不能为空");
	     	        				return false;
	     	        			}
	     	        			else if (sun!=0) {
	     	        				layer.msg("分配的用例编号不能重复");
	     	        				return false;
	     	        			}else if(su==1)
	     	        		{
	     	        		layer.msg("分配等级不能重复");
	         				return false;
	     	        		}else if (sed==1) {
	     	    				layer.msg("用例条数必须分完");
	     	    				return false;
	     	    			} 
	     	        		else if (pde==0) {
	     	    				layer.msg("等级必须分完");
	     	    				return false;
	     	    			} 
	        	else{
	        		 layer.msg('加载中', {
	        			  icon: 16
	        			  ,shade:0.5,time:5000000
	        			});
                     console.log(array);
	        		$.ajax({
	   		         type: "POST",
	   		     	url:"deliverTask",
	   		     	data:{"params":JSON.stringify(array)},
		   			dataType:"json",
	   		     success: function(data){        
	   		    	//layer.closeAll();
	   		    	 if(data=="fail"){
	   		    		 layer.msg("您没有分配计划的权限",{time:3000});
	   		    	 }else{
	   		    		layer.msg("分配任务成功",{time:2000});
	   		   		    setTimeout(function() {
	   		   		  window.location.reload();
	   		   		    }, 2000);
	   		    	 }
	   		    	
	   		     },
	   		     error : function(xhx,e,errMsg) {
	   		     alert(errMsg);
	   		     }
	   		  });
	        	
	        	}

		           },btn2: function(){
		           layer.closeAll();
		           window.location.reload();
		          },end: function () {
		        	  window.location.reload();
		            }					
				  });
		 for(s=0;s<str.length-1;s++)
		 {
		 if((s+1)%4==0)
		 {
		 html.push('<span class="mytest"><input type="checkbox" name="checkedId1" value="'+str[s]+'"/>'+str[s]+'</span><br/>');
		 }
		 else{
		 html.push('<span class="mytest"><input type="checkbox" name="checkedId1" value="'+str[s]+'"/>'+str[s]+'</span>');
		 }
		 $('#choose1').html(html.join(''));
		 } 
	}
	
	function sgo(){//主测的分配任务弹出框的测试内容选择弹出框
		 var ll=document.getElementById('l5');
		 var use=document.getElementById('l5').value;
		 if(use==null||use=="")
			 {
			 layer.msg("无测试内容");
			 
			 }
		 
		 else{
		 //alert(use);
		 if(document.getElementsByName('sho')[0])
			{
			
			var sh=document.getElementsByName('sho');
			 var tbody = sh[0].parentNode;
				
			 var strh=sh.length;
			for(y=strh-1;y>=0;y--)
				{
							
		    	 tbody.removeChild(sh[y]); 
				}
			}
		 
		 

		 var str=use.split(",");
				var box=document.getElementsByName("checkedId1");
				for(var i=0;i<str.length;i++)
				{
				for(j=0;j<box.length;j++){

				if(box[j].value==str[i])
				{
				box[j].checked=true;
				break;
				}
				}
				}
		 
				var fu=document.getElementById('sel');
				 
				 var rr=fu.parentNode.parentNode.parentNode.parentNode.parentNode;
				
			 
		layer.open({
			
					type: 1,
					title:'请选择需要的测试模块',
					shade:false,
					skin: 'layui-layer-rim', //加上边框
					area: ['600px', '310px'], //宽高
					content:$("#choose1"),
						btn: ['确定', '取消']
		           ,yes: function(index){
		            var checkedSubject = $('input[name=checkedId1]:checkbox:checked');
			var checkedIds = "";
			//循环获取选中的复选框的value，这个value是数据表中每条记录的主键，传给后台，后台就能根据主键查找到数据表的相应记录
			//将其value用逗号隔开拼接成一个字符串
			checkedSubject.each(function() {
				checkedIds = checkedIds + $(this).val()+ "," ;
			});
			ll.value=checkedIds;
			var ssd=checkedIds.split(",");
			
			
			if(ssd.length==2){
				var vall=document.getElementById("l1").value;//计划编号
				var name=ssd[0];
				$.ajax({
		         type: "POST",
		     	url:"showUserMsg?id="+vall,
		     success: function(data){   
		    	 if(data=="fail"){
		    		 layer.msg("您没有获取人员信息的权限",{time:2000});
		    	 }else{
		    		
		    		 var sel=document.getElementById("sel");
				     var sell=document.getElementsByName('plantail.user.userId');
				     var str="";
				     var ss=data.substr(1, data.length-2).split(",");
				     for(var i=0;i<ss.length;i++)
				       {	       
				      var name=ss[i].split("_")[0];
				      var id=ss[i].split("_")[1];	      
				      str +='<option value="'+id+'">'+name+'</option>';
				       }  
				  	 	//sel.innerHTML=str;
				  	 	for(k=0;k<sell.length;k++)
				  	 		{
				  	 		sell[k].innerHTML=str;
				  	 		}
		    	 }
		    
		     },
		     error : function(xhx,e,errMsg) {
		     alert(errMsg+"sdjk1");
		     }
		  });
				 $.ajax({
			         type: "POST",
			     	url:"showCaseLevel?name="+name,
			     success: function(data){        
			   if(data=="fail"){
				   layer.msg("您没有获取人员信息的权限",{time:2000});
			   }else{
				   //alert(data);
				  var strr=data.split(",");
				  var long=strr.length;
				  //将用例编号分解写在框里
				var end=strr[long-2];
					
				var start=strr[long-3];
					
				/*var startl=document.getElementsByName("start");
				var endl=document.getElementsByName("end");
				for(j=0;j<startl.length;j++)
					{
					startl[j].value=start;
					endl[j].value=end;
					}*/
				var casenum=document.getElementById("l6");
				casenum.value=end;
				//分解等级
				var html="";
				for(k=0;k<long-3;k++)
				{
					var str=strr[k].split("_");
					html+='<li style="float:left;"><input type="checkbox" name="checkId" value="'+str[0]+'">'+str[0]+'&nbsp;&nbsp;&nbsp;'+str[1]+'天&nbsp;&nbsp;</li>';
			}
			   }
			     $('.first').after(html);
			     },
			     error : function(xhx,e,errMsg) {
			     alert(errMsg);
			     }
			  });
				var n6=document.getElementById('l6');
				var nn6=n6.parentNode.parentNode;
				 var usehtml='<tr name="sho"><td></td><td ><ul><li style="float:left"  class="first"><button type="button" onclick="clone(this);">复制</button></li>'+
					'<li style="float:left">&nbsp;&nbsp;&nbsp;或者&nbsp;&nbsp;&nbsp;</li>'+
					'<li style="float:left"><input type="text" onkeyup="num(this)" style="width:40px" name="start" value="" >--'+
					'<input type="text" style="width:40px" onkeyup="num(this)" name="end" value=""></li>'+
					'<li style="float:left"><select name="plantail.user.userId" onChange="showtime(this);" style="width:80px;" ><option value="id"></option></select></li>'+
					'<li style="float:left"><button onclick="deletee(this);">删除</button></li></ul></td></tr>';
			 $(nn6).after(usehtml);
			 
			 $(rr).hide();
			}
			else{
				
				
				if(document.getElementsByName('sho')[0])
					{
					
					var sh=document.getElementsByName('sho');
					 var tbody = sh[0].parentNode;
						
					 var strh=sh.length;
					for(y=strh-1;y>=0;y--)
						{
									
				    	 tbody.removeChild(sh[y]); 
						}
					}
				 $(rr).show();
				
			}
		           layer.close(index);
		           },btn2: function(index){
		           layer.close(index);
		          }					
				  });
		            
		 }
		 }
	
	
	function clone(obj){
		var ll=obj.parentNode.parentNode.parentNode.parentNode;
		
		var kk=ll.cloneNode(true);
		$(ll).after(kk);
	}
	
	function deletee(obj){
		var ll=obj.parentNode.parentNode.parentNode.parentNode;
		var tbody=ll.parentNode;
		tbody.removeChild(ll); 
	}
function num(obj)
{
	obj.value=obj.value.replace(/\D/g,'');
	 var name=document.getElementById("l5").value;
	$.ajax({
        type: "POST",
    	url:"showCaseLevel?name="+name,
    success: function(data){        
  if(data=="fail"){
	   layer.msg("您没有获取人员信息的权限",{time:2000});
  }else{
	
	  var strr=data.split(",");
	  var long=strr.length;
	  //将用例编号分解写在框里
	var end=strr[long-2];
		
	var num=obj.value;
	
		if(num>parseFloat(end))
			{
			layer.msg("分配的用例编号不能超出实际编号范围");
			num="";
			}
			}
		
	
    },
    error : function(xhx,e,errMsg) {
    alert(errMsg);
    }
 });	
}



function changeTwoDecimal(x){
	var f_x = parseFloat(x);  
		if (isNaN(f_x))  
		{  
		alert('function:changeTwoDecimal->parameter error');  
		return false;  
		}  
		var f_x = Math.round(x*100)/100;  
		var s_x = f_x.toString();  
		var pos_decimal = s_x.indexOf('.');  
		if (pos_decimal < 0)  
		{  
		pos_decimal = s_x.length;  
		s_x += '.';  
		}  
		while (s_x.length <= pos_decimal + 2)  
		{  
		s_x += '0';  
		}  
		return s_x;  
	}

function showtime(obj){
	if(document.getElementsByName('times')[0]){
		var timedom=document.getElementsByName('times');
		for(k=0;k<timedom.length;k++){
			$(timedom[k]).remove();
		}
		
	}
	var start=document.getElementById('l2').value;
	var end=document.getElementById('l3').value;
	 var start1=start.replace(/\-/gi,"/");
	 var end1=end.replace(/\-/gi,"/");
	 var time1=new Date(start1).getTime();
	  var time2=new Date(end1).getTime();
	  var gap=time2-time1;
	
	  var gap1=gap/(24*60*60*1000);
	  var gsp=gap1+1;
	 ///alert(gsp);
	  var planId=document.getElementById("l1").value;
		var selected=obj.value;
		var lii=obj.parentNode;
		
		$.ajax({
      type: "POST",
  	url:"showUserMsg?id="+planId,
  success: function(data){        
if(data=="fail"){
	   layer.msg("您没有获取人员信息的权限",{time:2000});
}else{
	//alert(data);
	     var ss=data.substr(1, data.length-2).split(",");
	     var html="";
	     for(var i=0;i<ss.length;i++)
	       {	       
	      var name=ss[i].split("_")[0];
	      var id=ss[i].split("_")[1];	
	      var time=ss[i].split("_")[2];
	      
	      if(selected==id)
	    	  {
	    	 var times=changeTwoDecimal(time);
	    	 if(times<=gsp){
	    	 var surplus=gsp-times;
	    	  html='<li style="float:left" name="times">人员所用时间:  '+times+'&nbsp;&nbsp;可用时间:  '+surplus+'</li>';
	    	  $(lii).after(html);
	    	 }
	    	 else{
	    		 
	    		 html='<li style="float:left" name="times">人员所用时间:  '+times+'&nbsp;&nbsp;所选人没有可用时间</li>';
		    	  $(lii).after(html); 
	    	 }
	    	  }
	       }  
	    
}
  
  },
  error : function(xhx,e,errMsg) {
  alert(errMsg+"   "+"a-first");
  }
});

	
}

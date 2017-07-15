/**
 * 
 */

function data(){
	var planId=document.getElementById("pid").value;
	var temp;
	 $.ajax({
	      type: "POST",
	      async:false,
	  	url:"showUserMsg?id="+planId,
	  success: function(data){    
	if(data=="fail"){
		   layer.msg("您没有获取人员信息的权限",{time:2000});
	}else{		
		temp=data;
	}
	  
	  },
	  error : function(xhx,e,errMsg) {
	  alert(errMsg+"   "+"a-first");
	  }
	});
	
	return temp;
	
}
/*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/	 


function showplan(){
	
	
	var start=document.getElementById('l2').value;
	var end=document.getElementById('l3').value;
	 var start1=start.replace(/\-/gi,"/");
	 var end1=end.replace(/\-/gi,"/");
	 var time1=new Date(start1).getTime();
	  var time2=new Date(end1).getTime();
	  var gap=time2-time1;
	
	  var gap1=gap/(24*60*60*1000);
	  var gsp=gap1+1;
	var n5=document.getElementById("l5");
	var n6=document.getElementById("l6");
	var planId=document.getElementById("pid").value;
	
	var nn6=n6.parentNode.parentNode;
	// 获取测试内容
	var name=n5.value;
	// 分解测试内容
	var str=name.split(",");
	var state;
	 $.ajax({			
         type: "POST",
         async:false,
     	url:"showCaseLevel?name="+str[0]+",&planId="+planId,
     success: function(data){        
   if(data=="fail"){
	   layer.msg("您没有获取人员信息的权限",{time:2000});
   }else{
	  
	  var strr=data.split(",");
	  var long=strr.length;
	// 将用例编号分解写在框里
	  var status=strr[long-1];
	  state=status;
	  
   }
     },
     error : function(xhx,e,errMsg) {
     alert(errMsg+"   "+"b-second");
     }
  });
	
	if(state=="未分配")// 判断该计划是否被分配过
	{	
		var temp=data();
	var html="";
	// 将测试内容一条一条的追加到table表中
	for(var i=0;i<str.length-1;i++)
		{
		
		html +='<tr name="sho"><td>'+str[i]+
		'</td><td ><ul class="ulplay" style="float:left;width:100%;"><li style="float:left"  class="first'+i+'"><button type="button"  class="btn btn-default btn-xs"onclick="clone(this);"><i class="layui-icon" style="font-size:1.3em; color:black;">&#xe61f;</i> </button>&nbsp;&nbsp;</li>'+
		'<li style="float:left">&nbsp;&nbsp;或者&nbsp;&nbsp;</li>'+
		'<li style="float:left"><input type="text" onkeyup="num(this)" style="width:40px" name="start'+i+'" value="" >--'+
		'<input type="text" style="width:40px" onkeyup="num(this)" name="end'+i+'" value="">&nbsp;&nbsp;&nbsp;</li>'+
		'<li style="float:left">'+'<div  class="col-lg-10" style="width:150px;">'+
		'<select name="plantail.user.userId'+i+'" onChange="showtime(this);" style="width:150px;" class="selectpicker show-tick form-control" data-live-search="true"><option value="id"></option></select>'+
		'</div>'+
		'</li><li style="float:left">&nbsp;&nbsp;&nbsp;<button onclick="deletee(this);" name="shobu'+i+'" class="btn btn-default btn-xs" ><i class="layui-icon" style="font-size:1.4em; color:red;">&#xe640;</i></button></li><li style="float:left;" class="username'+i+'"></li>'+
		'</ul></td></tr>';		
		}
	$(nn6).after(html);	
	
	 
		
	
	// 将等级与用时写入
	for(j=0;j<str.length-1;j++)
		{
		 $.ajax({			
	         type: "POST",
	         async:false,
	     	url:"showCaseLevel?name="+str[j]+",&planId="+planId,
	     success: function(data){        
	   if(data=="fail"){
		   layer.msg("您没有获取人员信息的权限",{time:2000});
	   }else{
		  
		  var strr=data.split(",");
		  var long=strr.length;
		// 将用例编号分解写在框里
		  var end=strr[long-3];
		
		  var start=strr[long-4];	
		 
			var username='<li style="float:left">'+strr[long-1]+'</li>';  
		  		
		// n5.disabled=true;
		// 分解等级
		var html="";
		var total='<li style="float:left;">&nbsp;总用时：&nbsp;&nbsp;'+strr[long-2]+'天</li>' +
		'<li style="float:left;">&nbsp;总数：&nbsp;&nbsp;'+end+'</li>';
		for(k=0;k<long-4;k++)
		{
			var str=strr[k].split("_");
			html+='<li style="float:left;"><input type="checkbox" checked="checked" name="checkId" value="'+str[0]+'">'+str[0]+'&nbsp;&nbsp;'+str[1]+'天&nbsp;&nbsp;</li>';
		}
	   }
	     $('.username'+j).after(username);
	     $('.first'+j).after(html+total);
	     },
	     error : function(xhx,e,errMsg) {
	     alert(errMsg+"   "+"b-second");
	     }
	  });

		 
		 
		 
		   var sell=document.getElementsByName("plantail.user.userId"+j+"");		 
	       
		     var strsh='<option value="选择人员" data-flag="0">选择人员</option>';

		     var ss=temp.substr(1, temp.length-2).split(",");		
		     for(var i=0;i<ss.length;i++)
		       {	       
		      var name=ss[i].split("_")[0];
		      var id=ss[i].split("_")[1];	

		      var time=ss[i].split("_")[2];	// 人员时间
		      var percent=time/gsp;
		      var times=time+1;
		      if(percent<1&&percent>0){// 人员时间处于部分可用状态为蓝色
			      strsh +='<option value="'+id+'"  data-flag="'+times+'"style="color:#00B2EE;" username="'+name+'">'+name+'</option>';
			       }
			     else if (percent==0)// 人员时间处于完全可用状态为绿色
			    	 {
			    	 strsh +='<option value="'+id+'" data-flag="'+times+'" style="color:#32CD32;" username="'+name+'">'+name+'</option>';
			    	 }
			     else{// 人员时间处于不可用状态为红色
			    	 strsh +='<option value="'+id+'" data-flag="'+times+'" style="color:red;" username="'+name+'">'+name+'</option>';
			     }

		       }  
		  	 	
		  	 		sell[0].innerHTML=strsh;
		  	 		
		  	 	for(h=0;h<sell.length;h++)
	  	 		{
		  	 		var ops = sell[h].getElementsByTagName("option");
		  	        var arrOps = Array.prototype.slice.call(ops, 0);
		  	        arrOps.sort(function (a, b) {
		  	            return a.attributes["data-flag"].value - b.attributes["data-flag"].value;
		  	        });
		  	        sell[h].options.length = 0;
		  	        arrOps.map(function (op) {
		  	            sell[h].appendChild(op);
		  	        });
		  	      $(sell[h]).selectpicker('refresh');
	  	 		}
		 
		 
		}
	// 追加成功后。将人员追加到select中


	
	
	  	 	
 	

	
	}
	else{
		
		var htmlf="";
		// 将测试内容一条一条的追加到table表中
		for(var i=0;i<str.length-1;i++)
			{
			
			htmlf +='<tr><td>'+str[i]+
			'</td><td ><ul style="float:left;"><li style="float:left"  class="username'+i+'"></li>'+
			'</ul></td></tr>';		
			}
		$(nn6).after(htmlf);	
		
		
		for(j=0;j<str.length-1;j++)
		{
		 $.ajax({			
	         type: "POST",
	         async:false,
	     	url:"showCaseLevel?name="+str[j]+",&planId="+planId,
	     success: function(data){        
	   if(data=="fail"){
		   layer.msg("您没有获取人员信息的权限",{time:2000});
	   }else{
		
		  var strr=data.split(",");
		  var long=strr.length;
		
		 
			var username='<li style="float:left">'+strr[long-1]+
			'</li><li style="float:left">&nbsp;&nbsp;&nbsp;<button onclick="refen(this);" name="rebu" class="btn btn-default" >重新分配</button></li>';  
		  
			
		
		// n5.disabled=true;
		// 分解等级
	   }
	     $('.username'+j).after(username);
	    
	     },
	     error : function(xhx,e,errMsg) {
	     alert(errMsg+"   "+"b-second");
	     }
	  });
		}
		
	}
	
	
}



/*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/



//重新分配
function refen(obj){

	var start=document.getElementById('l2').value;
	var end=document.getElementById('l3').value;
	 var start1=start.replace(/\-/gi,"/");
	 var end1=end.replace(/\-/gi,"/");
	 var time1=new Date(start1).getTime();
	  var time2=new Date(end1).getTime();
	  var gap=time2-time1;
	
	  var gap1=gap/(24*60*60*1000);
	  var gsp=gap1+1;
var tr=obj.parentNode.parentNode.parentNode.parentNode;
var ul=obj.parentNode.parentNode;
var td=obj.parentNode.parentNode.parentNode;
ul.remove();
tr.setAttribute("name","sho");
var name=$(tr).children('td').eq(0).text();
var planId=document.getElementById("pid").value;
$.ajax({			
 type: "POST",

	url:"showCaseLevel?name="+name+",&planId="+planId,
success: function(data){        
if(data=="fail"){
layer.msg("您没有获取人员信息的权限",{time:2000});
}else{

	
	
		
var strr=data.split(",");
var long=strr.length;
//将用例编号分解写在框里
var end=strr[long-3];	
var start=strr[long-4];	

	var username='<li style="float:left">'+strr[long-1]+'</li>';  
 var rename=document.getElementsByName('sho');
	var lg=rename.length-1;

//分解等级
var html="";
var total='<li style="float:left;">&nbsp;总用时：&nbsp;'+strr[long-2]+'天</li>' +
'<li style="float:left;">&nbsp;总数：&nbsp;'+end+'</li>';
for(k=0;k<long-4;k++)
{
	var str=strr[k].split("_");
	html+='<li style="float:left;"><input type="checkbox" checked="checked" name="checkId" value="'+str[0]+'">'+str[0]+'&nbsp;&nbsp;&nbsp;'+str[1]+'天&nbsp;&nbsp;</li>';
}
}

htmlre='<ul class="ulplay" style="float:left;width:100%;"><li style="float:left"  class="first"><button type="button"  class="btn btn-default btn-xs"onclick="clone(this);"><i class="layui-icon" style="font-size:1.3em; color:black;">&#xe61f;</i> </button>&nbsp;&nbsp;</li>'+
html+total+'<li style="float:left">&nbsp;&nbsp;或者&nbsp;&nbsp;</li>'+
'<li style="float:left"><input type="text" onkeyup="num(this)" style="width:40px" name="start'+lg+'" value="" >--'+
'<input type="text" style="width:40px" onkeyup="num(this)" name="end'+lg+'" value="">&nbsp;&nbsp;&nbsp;</li>'+
'<li style="float:left">'+'<div  class="col-lg-10" style="width:150px;">'+
'<select name="plantail.user.userId'+lg+'" onChange="showtime(this);" style="width:150px;" class="selectpicker show-tick form-control" data-live-search="true"><option value="id"></option></select>'+
'</div>'+
'</li><li style="float:left">&nbsp;&nbsp;&nbsp;<button onclick="deletee(this);" name="shobu" class="btn btn-default btn-xs" ><i class="layui-icon" style="font-size:1.4em; color:red;">&#xe640;</i></button></li><li style="float:left;" class="username"></li>'+
'</ul>';			
td.innerHTML=htmlre;
},
error : function(xhx,e,errMsg) {
alert(errMsg+"   "+"b-second");
}
});
$.ajax({
 type: "POST",
	url:"showUserMsg?id="+planId,
success: function(data){        
if(data=="fail"){
	   layer.msg("您没有获取人员信息的权限",{time:2000});
}else{		
	 var rename=document.getElementsByName('sho');
		var lg=rename.length-1;
	   var sell=document.getElementsByName("plantail.user.userId"+lg+"");		 

	     var str='<option value="选择人员" data-flag="0">选择人员</option>';

	     var ss=data.substr(1, data.length-2).split(",");		
	     for(var i=0;i<ss.length;i++)
	       {	       
	      var name=ss[i].split("_")[0];
	      var id=ss[i].split("_")[1];	

	      var time=ss[i].split("_")[2];	// 人员时间
	      var percent=time/gsp;
	      var times=time+1;
	      if(percent<1&&percent>0){// 人员时间处于部分可用状态为蓝色
		      str +='<option value="'+id+'"  data-flag="'+times+'"style="color:#00B2EE;">'+name+'</option>';
		       }
		     else if (percent==0)// 人员时间处于完全可用状态为绿色
		    	 {
		    	 str +='<option value="'+id+'" data-flag="'+times+'" style="color:#32CD32;">'+name+'</option>';
		    	 }
		     else{// 人员时间处于不可用状态为红色
		    	 str +='<option value="'+id+'" data-flag="'+times+'" style="color:red;">'+name+'</option>';
		     }

	       }  
	  	 	for(k=0;k<sell.length;k++)
	  	 		{
	  	 		sell[k].innerHTML=str;
	  	 		}
	  	 	for(h=0;h<sell.length;h++)
	 		{
	  	 		var ops = sell[h].getElementsByTagName("option");
	  	        var arrOps = Array.prototype.slice.call(ops, 0);
	  	        arrOps.sort(function (a, b) {
	  	            return a.attributes["data-flag"].value - b.attributes["data-flag"].value;
	  	        });
	  	        sell[h].options.length = 0;
	  	        arrOps.map(function (op) {
	  	            sell[h].appendChild(op);
	  	        });
	  	      $(sell[h]).selectpicker('refresh');
	 		}
	  	 	
	  	 	
}

},
error : function(xhx,e,errMsg) {
alert(errMsg+"   "+"a-first");
}
});	
	
}


/*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

// 表单验证
function checkdata(){
	var tips=document.getElementById('tips');
	
	var n5=document.getElementById("l5");
	var tr=document.getElementsByName('sho');
	var planId=document.getElementById("pid").value;
	var name=n5.value;
	var pass=0,pw=0;
	// 分解测试内容
	var str=name.split(",");
	var temp=data();
	// 验证每个模块的表单是否通过
	for(i=0;i<tr.length;i++)		
{     
		
	var a=0,ssum=0,esum=0,allsum=0,b=0,trall=0,su=0,yuu=0,sun=0,e1=0,c1=0,d1=0,ste=0,te=0,sed=0,pde=0,selec=0;
		var startl=document.getElementsByName("start"+i+"");
        var endl=document.getElementsByName("end"+i+"");
        var sell=document.getElementsByName("plantail.user.userId"+i+"");
        
        // 判断人员是否全部填写
        var v=$(tr[i]).find('option:selected');
        
        var selecteds='';
    	v.each(function(){
    	selecteds=selecteds+$(this).val()+',';
    	});
    	
    	if(selecteds.indexOf('选择人员')==-1)
    		{
    		
    		selec=1;
    		}
    	else{ selec=0;}
    	// 判断模块中等级或者用例编号是否其中一个写,且只写了一个
        var jsons="";		
		$(tr[i]).find('input:checkbox[name=checkId]:checked').each(function(i){
			jsons=jsons+$(this).val()+",";			
		});

      // alert(jsons.length);
       // 如果jsons.length==0的话，证明此tr内没有勾选checkbox
       if(jsons.length==0)
	     {
	        a=0;
	     }else{
		   a=1;
	     }
       // 获取模块内的start或者end的值是否填写
       for(j=0;j<startl.length;j++)
    	   {
    	   if(startl[j].value==null||startl[j].value==''){
				continue;
				}			
			ssum +=parseFloat(startl[j].value);
			if(endl[j].value==null||endl[j].value==''){
				continue;
				}	
			esum +=parseFloat(endl[j].value);
    	   
    	   }
       allsum=parseFloat(ssum+esum);
       if(allsum==""||allsum==null)
       {
    	   b=0;
       }
       else{
    	   b=1;
       }
       trall=a+b;// 如果等于2的话说明两个都填了
       // alert(trall+" "+a+" "+b+" "+allsum);
       
       // 判断等级是否重复
       var  ssd=jsons.split(",");
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
       
		// 判断分配的用例编号是否重复
		
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
			 else if((star<=sta && sta<=en && en<=enl)||(sta<=star && star<=enl &&enl<=en)||(sta<=star && star<=en && en<=enl)||(star<=sta &&sta<=enl && enl<=en))
				{
				yuu=1;	
				//alert("yuu"+yuu+"  sta"+sta+"  star"+star+"  en"+en+"  enl"+enl);
				}	        				 
			else{	        					
				yuu=0;	        				
			}
			sun +=yuu;
			}
      // alert(sun);
		// 判断同一ul内是否等级和编号有一个写了
		
		 for(r=0;r<startl.length;r++)
			 {
			
			 if(startl[r].value==""||startl[r].value==null||endl[r].value==""||endl[r].value==null) {d1=0;}else{d1=1;}
			 
			 if((startl[r].value==""&&endl[r].value!="")||(startl[r].value!=""&&endl[r].value=="")) 
			 {te=1;}else{te=0;}
			
			 
			 var sttt=startl[r].parentNode.parentNode;
			 var checkedbox="";
			 $(tr[i]).find('input:checkbox[name=checkId]:checked').each(function(i){
				 checkedbox=checkedbox+$(this).val()+",";			
				});			 							
				 if(checkedbox.length > 0){
	                c1=1;
	             }
				 else{c1=0; }			
       			e1=c1+d1; 
		// alert(e1+" ,"+c1+" ,"+d1);
			
			ste +=te;
			 }	
             
		// 验证等级或者用例数是否分完
		  var sdata=temp.split(",");
		  var long=sdata.length;
		  // 将用例编号分解写在框里
		var ssend=sdata[long-3];
			
		 var max=endl[0].value;
		 for(x=0;x<endl.length;x++){
			// alert(endl[x].value);
			 if( max < parseFloat(endl[x].value)){
				    max = endl[x].value;
				  }	        				 
		 }
		 
		 if(max==null||max=="")
			 {sed=0;}
		 else if(max<parseFloat(ssend))
		 {
		 sed=1;
		 //alert(max+"   "+parseFloat(ssend));
		 }
	 else{sed=0;}
		
		 if((ssd.length-1)<(long-4)&&(ssd.length-1)!=0)
		 {
			 pde=0;
		 }
		 else{pde=1;}
		 
		 // 校验人员是否选择
		 if (sed==1) {
				//layer.msg("用例条数必须分完");
				tips.innerHTML="用例条数没有分配完哦";
				tips.style.display='block';
			} 
			else if (pde==0) {
				//layer.msg("等级必须分完");
				tips.innerHTML="用例等级没有分配完哦";
				tips.style.display='block';
			}
		 
		 if (trall==0) {
				layer.msg("用例分配不能为空");
				pw=0;
				} 
	     else if (trall==2) {
		layer.msg("等级和用例分配不能都有");
		pw=0;
	           } 
	     else if (ste>=1) {
		layer.msg("同一行内用例编号不能有一个为空");
		pw=0;
	           } 
	    else if (e1==0) {
		layer.msg("同一行内等级和用例编号不能同时为空");
		pw=0;
	       } 
	    else if (e1>1) {
		layer.msg("同一行内等级和用例编号不能同时有值");
		pw=0;
	           } 
		else if (h==0) {
				layer.msg("测试内容不能为空");
				pw=0;
			   }
			else if (sun!=0) {
				layer.msg("分配的用例编号不能重复");
				pw=0;
			}
			else if(su==1){
		layer.msg("分配等级不能重复");
		pw=0;
		}
		
		else if(selec==0){
			layer.msg("人员未选择");
			pw=0;
		}
	else{
		
		pw=1;
	}
     pass +=pw;  
       
       
}
// alert(pass);
  if(pass==tr.length)
	{ 
	  layer.msg("验证通过");
	  submitt();
	}
	
}



/*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/



// 保存数据
function submitt(){
	 var array=[];
	 var json={};
	var tr=document.getElementsByName('sho');
	// 获取每个name为sho的tr行的数据
	$('tr[name="sho"]').each(function(i) {
		
		// 获取td里面的每个ul里面的checkbox的值并组成字段
		var arrsum="";
		var startl=document.getElementsByName("start"+i+"");
		var endl=document.getElementsByName("end"+i+"");
		var sell=document.getElementsByName("plantail.user.userId"+i+"");
		for(j=0;j<startl.length;j++)
	{
			var st=startl[j].parentNode.parentNode;
			
			var arr=[];
			var stal=startl[j].value;
			var enl=endl[j].value;
			var sel=$(sell[j]).val();
			var jsons="";
		
						
			$(st).find('input:checkbox[name=checkId]:checked').each(function(i){
				jsons=jsons+$(this).val()+"-";
				
			});
			arr.push(jsons,stal,enl,sel);
			
			var arrs=arr[0]+","+arr[1]+","+arr[2]+","+arr[3]+"/";
			arrsum +=arrs;
			
	}
		console.log(arrsum);
		// 获取planId 测试内容 开始时间 结束时间 备注 projectId
		var planId=document.getElementById('pid').value;
		 var starttime=document.getElementById("l2").value;
		 var endtime=document.getElementById("l3").value;
		 var remark=document.getElementById("l7").value;
		
		 var testcon=$(this).children('td').eq(0).text();
		 json={planId,arrsum,starttime,endtime,remark,testcon};
		 array.push(json);
	})
	
	console.log(array);
	
	 $.ajax({
		 type: "POST", 
		 url:"deliverTask",
	 data:{"params":JSON.stringify(array)},
	 dataType:"json", 
	 success:function(data){
	 
	 layer.msg('分配任务成功!',{time:1000});
	 setTimeout(function() {window.location.reload(); }, 2000); 
	 }, 
	 error : function(e) {
	 layer.msg('分配任务失败!',{time:1000}); 
	 console.log(e); } 
	 });
	 
	
	
}


/*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

// 任务分配弹框的方式
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
	 
	 // 测试 内容
/*---------------------------------------成员----------------------------------------------------------------------------------------------*/	 		
	showplan();
	
	
/*-------------------------------------------------------------------------------------------------------------------------------------------*/	 			 
	 layer.open({
			type: 1,
			title:'分配测试任务',
			maxmin: true,
			skin: 'layui-layer-rim', // 加上边框
			area: ['1200px', '610px'], // 宽高
			content:$("#showee"),
				btn: ['确定', '取消']
           ,yes: function(){
        	  checkdata();
	           },btn2: function(){
	           layer.closeAll();
	           window.location.reload();
	          },end: function () {
	        	  window.location.reload();
	            }					
			  });
	
}


/*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/



function clone(obj){// 复制行
	var tr=obj.parentNode.parentNode.parentNode.parentNode;
	var num=tr.rowIndex;	
	var lg=num-7;
	var shobu=document.getElementsByName("shobu"+lg+"");	
	for(var i=0;i<shobu.length;i++){
		shobu[0].disabled=false;
	}
	var ll=obj.parentNode.parentNode;
	var kk=ll.cloneNode(true);
	$(ll).after(kk);
	//解决bootstrap-select，在clone()方法中无效的问题
	
		$('.selectpicker').data('selectpicker', null);
		$('.bootstrap-select').css("display","none");
		$('.selectpicker').selectpicker();
}


/*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

function deletee(obj){// 删除行
	var tr=obj.parentNode.parentNode.parentNode.parentNode;
	var num=tr.rowIndex;
	
	var lg=num-7;
	
	var ll=obj.parentNode.parentNode;
	

	var shobu=document.getElementsByName("shobu"+lg+"");	
	if(shobu.length>1){
	var ll=obj.parentNode.parentNode;
	ll.remove(); 
    
	}else{
		shobu[0].disabled=true;
		
	}
	
	
}

/*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

function showtime(obj){// 显示人员时间
	
	var start=document.getElementById('l2').value;
	var end=document.getElementById('l3').value;
	 var start1=start.replace(/\-/gi,"/");
	 var end1=end.replace(/\-/gi,"/");
	 var time1=new Date(start1).getTime();
	  var time2=new Date(end1).getTime();
	  var gap=time2-time1;
	
	  var gap1=gap/(24*60*60*1000);
	  var gsp=gap1+1;
	var temp=data();
	if(document.getElementsByName('times')[0]){
		var timedom=document.getElementsByName('times');
		for(k=0;k<timedom.length;k++){
			$(timedom[k]).remove();
		}
		
	}
	var planId=document.getElementById("pid").value;
	var selected=obj.value;
	var lii=obj.parentNode.parentNode;
	$.ajax({
	      type: "POST",
	      async:false,
	  	url:"showUserMsg?id="+planId,
	  success: function(data){        
	if(data=="fail"){
		   layer.msg("您没有获取人员信息的权限",{time:2000});
	}else{
		
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
		    	 var surplus=changeTwoDecimal(gsp-times);
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


/*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

function changeTwoDecimal(x){// 保留2位小数
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


/*------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

function num(obj)// 限制输入数据
{  var n5=document.getElementById("l5");
	var name=n5.value;
	var tr=obj.parentNode.parentNode.parentNode.parentNode;
// 分解测试内容
     name=$(tr).children('td').eq(0).text();
	 $.ajax({			
         type: "POST",
         async:false,
     	url:"showCaseLevel?name="+name+",",
     success: function(data){        
   if(data=="fail"){
	   layer.msg("您没有获取人员信息的权限",{time:2000});
   }else{
	   var strr=data.split(",");
		  var long=strr.length;
		  // 将用例编号分解写在框里
		var end=strr[long-3];
		
		var num=obj.value;
		
			if(num>parseFloat(end))
				{
				layer.msg("分配的用例编号不能超出实际编号范围");
				obj.value="";
				}		
   }
     
   
     },
     error : function(xhx,e,errMsg) {
     alert(errMsg+"   "+"b-second");
     }
  });
   
}

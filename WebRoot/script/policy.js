function show(){//将传来的关于模块和测试内容的数据分解成数组
		  cities = new Object();
		 	
		 var rows = document.getElementById("table1").rows.length; 

		 for(var i=2;i<rows+1;i++)
		 {

		 var caseType=$("tr:eq('"+i+"') td:eq(0)").text();
		 var caseStore=$("tr:eq('"+i+"') td:eq(1)").text();
		 var city =[];
		 var strs= new Array(); //定义一数组 
		 cities[caseType] = new Array()
		  strs=caseStore.split("|"); //字符分割 
		  for(var j=0;j<strs.length-1;j++)
		  {
		  var data=strs[j];
		  var strs1= new Array();
		  strs1=data.split(","); 
		   cities[caseType].push(strs1[0]);
		  }

		 }
        return cities;
		}
function set_modaul(obj){//将select的值写入到input框里 ，若测试内容选择为入口测试，则版本开始时间为测试开始时间
	var ne=$(obj).next('input')[0];
	var pv=obj.value;
	//alert(pv);
	ne.value=pv;
	if(pv=="入口测试"){
		var tr=obj.parentNode.parentNode.parentNode;
		var fir=$(tr).children('td').eq(10)[0].children[0];
		var fir1=$(tr).children('td').eq(2)[0].children[0];
		fir.value=fir1.value;	
	}
	
}
function auto(obj){//计算人力与人数，自动显示结束时间
	var tr=obj.parentNode.parentNode;
	var penum=$(tr).children('td').eq(6)[0].children[0].value;
		var num=$(tr).children('td').eq(7)[0].children[0].value;
		var start=$(tr).children('td').eq(11)[0].children[0];
          var date2=obj.value;   
          if(penum==null||penum==""||num==null||num==""||date2==null||date2=="")
        	  {
        	  start.value="";
        	  }
          else{
        	  
        	  var day2=date2.replace(/\-/gi,"/");
              var day=new Date(day2).getTime();
              if(num==0){
            	  
            	  layer.msg("人力安排不能为空");
              }
              else{
            	  var ll=Math.ceil(penum/num);
            		if(ll==0)
            			{var lu=ll;}
            		else{
            			
            			lu=ll-1;
            		}
            		
            		  var time2=new Date(day+lu*24*60*60*1000);
            		start.value=time2.getFullYear()+"-"+(time2.getMonth()+1)+"-"+time2.getDate();  
            	  
              }
          }
        	 
            	           
	}
function auto1(obj){
	var tr=obj.parentNode.parentNode;
	var penum=$(tr).children('td').eq(6)[0].children[0].value;
		var num=$(tr).children('td').eq(7)[0].children[0].value;
		var start=$(tr).children('td').eq(11)[0].children[0];
          var date2=obj.value;   
          if(penum==null||penum==""||num==null||num==""||date2==null||date2=="")
        	  {
        	  start.value="";
        	  }
          else{
        	  
        	  var day2=date2.replace(/\-/gi,"/");
              var day=new Date(day2).getTime();
              if(num==0){
            	  
            	  layer.msg("人力安排不能为空");
              }
              else{
            	  var ll=Math.ceil(penum/num);
            		if(ll==0)
            			{var lu=ll;}
            		else{
            			
            			lu=ll-1;
            		}
            		//alert(lu);
            		  var time2=new Date(day+lu*24*60*60*1000);
            		start.value=time2.getFullYear()+"-"+(time2.getMonth()+1)+"-"+time2.getDate();  
            	  
              }
          }
        	 
            	           
	}
		 function showt(obj) {//测试内容弹出框
	   var cities=show();
	   //console.log(cities);
		var pv;
		var i;
        var first,tr;
		tr=obj.parentNode.parentNode;
		first=$(tr).children('td').eq(3)[0].children[0].children[1];
		
	    pv = first.value;
		if (pv == '0')
			return;
		if (typeof (cities[pv]) == 'undefined')
			return;
		var chkhtml=[];
		
		var html='<hr/><ul><li style="float:left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="test" value="性能测试">性能测试&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="test" value="功能测试">功能测试&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>';
		var pchoose='<li style="float:left;"><input type="checkbox" value="p1" name="checkId1s" checked="checked">p1&nbsp;&nbsp;&nbsp;'+
			'<input type="checkbox" value="p2" name="checkId1s" checked="checked">p2&nbsp;&nbsp;&nbsp;'+
			'<input type="checkbox" value="p3" name="checkId1s" checked="checked">p3&nbsp;&nbsp;&nbsp;'+
			'<input type="checkbox" value="p4" name="checkId1s" checked="checked">p4&nbsp;&nbsp;&nbsp;</li></ul>';
		for (i = 0; i < cities[pv].length; i++) {
			if((i+1)%5==0)
			{
               chkhtml.push('<span class="mytest"><input type="checkbox" name="checkedId" value="'+cities[pv][i]+'"/>'+cities[pv][i]+'</span><br/>');
			}
			else{
			chkhtml.push('<span class="mytest"><input type="checkbox" name="checkedId" value="'+cities[pv][i]+'"/>'+cities[pv][i]+'</span>');
			}
					
		}
		chkhtml.push(html,pchoose);
		
	    $("#choose").html(chkhtml.join(''));	
		var ss=obj.value;
		var va=ss.split(",");
		var box=document.getElementsByName("checkedId");
		for(var i=0;i<va.length;i++)
		{
		for(j=0;j<box.length;j++){

		if(box[j].value==va[i])
		{
		box[j].checked=true;
		break;
		}
		}
		}
		layer.open({
			type: 1,
			title:'请选择需要的测试模块',
			skin: 'layui-layer-rim', //加上边框
			area: ['650px', '310px'], //宽高
			content:$("#choose"),
				btn: ['确定', '取消']
           ,yes: function(){
		    var checkedSubject = $('input[name=checkedId]:checkbox:checked');
	var checkedIds = "";
	//循环获取选中的复选框的value，这个value是数据表中每条记录的主键，传给后台，后台就能根据主键查找到数据表的相应记录
	//将其value用逗号隔开拼接成一个字符串
	checkedSubject.each(function() {
		checkedIds = checkedIds + $(this).val()+ "," ;
	});
	 var rows = document.getElementById("table1").rows.length; 
	 var sum=0;
	 var time=0;
	 var tt=obj.parentNode;
	 var tr=tt.parentNode;
		 var va=checkedIds.split(",");
	 for(var i=1;i<rows;i++)
	 {
	 var caseStore=$("tr:eq('"+i+"') td:eq(1)").text();
	 var city =[];
	 var strs= new Array(); //定义一数组 
	  strs=caseStore.split("|"); //字符分割 
	  for(k=0;k<va.length;k++){
	  for(var j=0;j<strs.length-1;j++)
	  {
	
		  var strs1=strs[j].split(",");
		  
	
		  if(va[k]==strs1[0])
		  {		
			 
			  sum +=parseInt(strs1[1]);
			  time +=parseFloat(strs1[2]);
			  break;
	}}}
	  }
	  $(tr).children('td').eq(5).children('input').val(sum);
	  var x=parseInt(time);
	  var y=time-x;
	  var m;
	 if(time>0&&time<=0.7)
		  {
		  m=0.5;
		  
		  }else if(time==0)
			  {
			  m=0;
			 
			  }else if(time>0.7&&y>0.7){
				  m=x+1;
			  }else if(time>0.7&&y>0.2&&y<=0.7)
				  {
				  m=x+0.5;
				  }
			  else{
				  m=x;
			  }
		  
	  $(tr).children('td').eq(6).children('input').val(m);
                         layer.closeAll();
				obj.value=checkedIds;		 
           }
           ,btn2: function(){
           layer.closeAll();
          }					
		  });
		
	}
	
	function sount(obj){//input框只能输入2为小数的数字
		 obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符     
		      obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的     
		      obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");    
		      obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入2个小数     
		      if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额    
		       obj.value= parseFloat(obj.value);    
		      }    
		}
		function sounts(obj){//input框只能输入整数
		
			obj.value=obj.value.replace(/\D/g,'');
		}
		function sounts1(obj){//input框只能输入整数
			
			obj.value=obj.value.replace(/\D/g,'');
			var tr=obj.parentNode.parentNode;
			var pnum= $(tr).children('td').eq(7).children('input').val();
			if(obj.value<pnum){
				obj.value="";
				layer.msg("样机数不能小于人数");
			}
			
			
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
		
		function pageScroll() {
			//把内容滚动指定的像素数（第一个参数是向右滚动的像素数，第二个参数是向下滚动的像素数）
			window.scrollBy(0, -100);
			//延时递归调用，模拟滚动向上效果
			scrolldelay = setTimeout('pageScroll()', 100);
			//获取scrollTop值，声明了DTD的标准网页取document.documentElement.scrollTop，否则取document.body.scrollTop；因为二者只有一个会生效，另一个就恒为0，所以取和值可以得到网页的真正的scrollTop值
			var sTop = document.documentElement.scrollTop + document.body.scrollTop;
			//判断当页面到达顶部，取消延时代码（否则页面滚动到顶部会无法再向下正常浏览页面）
			if (sTop == 0)
				clearTimeout(scrolldelay);
		}
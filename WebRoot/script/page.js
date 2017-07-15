/**
 * 
 */
//table分页        
        var pageSize=5;  //每页显示的记录条数   
        var curPage=0;   //显示第curPage页
        var len;         //总行数
        var page;        //总页数
        var pageSize2=5;  //每页显示的记录条数   
        var curPage2=0;   //显示第curPage页
        var len2;         //总行数
        var page2;        //总页数
       $(function(){    
       len =$("#table tr").length-1;   //去掉表头     
       len2 =$("#table2 tr").length-1;   //去掉表头    
        page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
        page2=len2 % pageSize2==0 ? len2/pageSize2 : Math.floor(len2/pageSize2)+1;//根据记录条数，计算页数
       // console.log("len:"+len+"page:"+page);
        document.getElementById("page").innerHTML=page;
        document.getElementById("page2").innerHTML=page2;
         for(var j=1;j<page+1;j++)
			{
			$('#curPage').append('<option value='+j+'>'+j+'</option>');
			}
         for(var k=1;k<page2+1;k++)
			{
			$('#curPage2').append('<option value='+k+'>'+k+'</option>');
			}
        curPage=1;
        curPage2=1;
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
      $("#nextpage2").click(function(){//下一页
          if(curPage2<page2){
              curPage2+=1;
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
      
      
      $("#lastpage2").click(function(){//上一页
          if(curPage2>1){
              curPage2-=1;
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
      
      
      $("#npage2").click(function(){//跳到固定某一页
          var npage2=parseInt(document.getElementById("curPage2").value);
          if(npage2>page2||npage2<1){
              layer.msg("该页不存在");
          }
          else{
              curPage2=npage2;
          }
          displayPage();
          });
 });
 
 function displayPage(){  
     var begin=(curPage-1)*pageSize;//起始记录号
     var end = begin + pageSize;
     
     
     var begin2=(curPage2-1)*pageSize2;//起始记录号
     var end2 = begin2 + pageSize2;
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
     
     if(end2 > len2 ) end2=len2;
     $("#table2 tr").hide();
     $("#table2 tr").each(function(k){
         if(k-1>=begin2 && k-1<end2)//显示第page页的记录
             {
             $("#show_tab_one2").show();
             $(this).show();
             
             document.getElementById("curPage12").innerHTML=curPage2;
            
			
             }         
     });

 }        
 function pageSize(){
     curPage=0;   //显示第curPage页   
     curPage2=0;   //显示第curPage页   
     pageSize=parseInt(document.getElementById("pageSize").value);
     pageSize2=parseInt(document.getElementById("pageSize2").value);
      len =$("#table tr").length-1;   //去掉表头     
      len2 =$("#table2 tr").length-1;   //去掉表头
      page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
      curPage=1;
      page2=len2 % pageSize2==0 ? len2/pageSize2 : Math.floor(len/pageSize2)+1;//根据记录条数，计算页数
      curPage2=1;
      displayPage();//显示第一页   
 }

 

/*$(function(){    
len =$("#table2 tr").length-1;   //去掉表头     
 page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
// console.log("len:"+len+"page:"+page);
 document.getElementById("page2").innerHTML=page;
  for(var j=1;j<page+1;j++)
		{
		$('#curPage2').append('<option value='+j+'>'+j+'</option>');
		}
 curPage=1;
 displayPage2();//显示第一页
$("#nextpage2").click(function(){//下一页
   if(curPage<page){
       curPage+=1;
   }
   else{
       layer.msg("已经是最后一页");
   }
  displayPage2();
  });
$("#lastpage2").click(function(){//上一页
   if(curPage>1){
       curPage-=1;
   }
   else{
     layer.msg("已经是第一页");
   }
   displayPage2();
   });
$("#npage2").click(function(){//跳到固定某一页
   var npage=parseInt(document.getElementById("curPage2").value);
   if(npage>page||npage<1){
      layer.msg("该页不存在");
   }
   else{
       curPage=npage;
   }
   displayPage2();
   });
});

function displayPage2(){  
var begin=(curPage-1)*pageSize;//起始记录号
var end = begin + pageSize;
//console.log("  begin:"+len+"   end:"+end);
if(end > len ) end=len;
$("#table2 tr").hide();
$("#table2 tr").each(function(i){
  if(i-1>=begin && i-1<end)//显示第page页的记录
      {
      $("#show_tab_one2").show();
      $(this).show();
      //document.getElementById("curPage").value=curPage;
      document.getElementById("curPage12").innerHTML=curPage;
       var sel = document.getElementById("numd");//获取select元素
		for(var k=1;k<curPage;k++){
		sel.options[sel.length] = new Option(k, k);
		} 
		//alert(curPage);
		
      }         
});

}        
function pageSize2(){
curPage=0;   //显示第curPage页   
pageSize=parseInt(document.getElementById("pageSize2").value);
len =$("#table2 tr").length-1;   //去掉表头     
page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
//console.log("len:"+len+"   page:"+page);
// document.getElementById("page").value=page;


curPage=1;
displayPage2();//显示第一页   
}
*/
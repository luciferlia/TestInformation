/**
 * 
 */

function showDivFun2() {
	 var s = document.getElementById("hid");
	    s.style.display = "block";
	    
	    var l = document.getElementById("popDiv2");
	    l.style.display = "block";
}
//关闭事件
function closeDivFun2() {
	var s = document.getElementById("hid");
   s.style.display = "none";
   
   var l = document.getElementById("popDiv2");
   l.style.display = "none";
}
	//----获取行号-----
function getRow(r){
 var i=r.parentNode.parentNode.rowIndex; 
 return i ;
}



//----清除添加信息框的内容-----
function cleanAddInput(){
document.getElementById("l1").value="";
document.getElementById("l2").value="";
 document.getElementById("l3").value="";
 document.getElementById("l4").value=""; 
 document.getElementById("l5").value="";
  document.getElementById("l6").value=""; 
  document.getElementById("l7").value=""; 


}
//----清除添加信息框的内容-----

//----显示添加信息框-----
function showAddInput(){
 document.getElementById("update").style.display="block" ;
 document.getElementById("hid").style.display="block" ;
 document.getElementById("btn_update").style.display="none" ;
 cleanAddInput(); 
}
//----显示添加信息框-----

//----隐藏添加信息框-----
function hideAddInput(){
 document.getElementById("update").style.display="none" ;
 document.getElementById("hid").style.display="none" ;
}
//----隐藏添加信息框-----

//----判断输入框的信息是否符合要求-----
function judge(){
 //根据id获取表单信息
	var l1 = document.getElementById("l1").value;
	 var l2 = document.getElementById("l2").value;
	 var l3 = document.getElementById("l3").value; 
	 var l4 = document.getElementById("l4").value;
	  var l5 = document.getElementById("l5").value;
	  var l6 = document.getElementById("l6").value;
	  var l7 = document.getElementById("l7").value;
	  var judge = true ; //用于判断表单信息是否符合
	  if(l1==""||l2=="" ||l3==""||l4==""||l5==""||l6==""||l7==""){
	  judge = false ;
	  alert("请输入项目名称");
	 }
	 
 
 return judge ;
}
//----判断输入框的信息是否符合要求-----



//----根据行号修改信息-----
function updateRow(r){
 row = getRow(r); //把该行号赋值给全局变量
 showAddInput(); //显示修改表单
 //提交按钮替换

 document.getElementById("btn_update").style.display="" ;
 insertInputFromQuery(queryInfoByRow(row));
 
}
//----根据行号修改信息-----


//----根据行号查信息----
function queryInfoByRow(r){
 
 var arr = new Array();
 for(var m=0 ; m<7;m++){
  arr[m] = document.getElementById("table").rows[row].cells[m].innerText;
 }
 return arr ; //返回该行数据
 
}
//----根据行号查信息----

//----把查询到的信息放入修改的表单里----
function insertInputFromQuery(arr){
	document.getElementById("l1").value = arr[0];
	 document.getElementById("l2").value = arr[1];
	 document.getElementById("l3").value = arr[2];
	 document.getElementById("l4").value = arr[3];
	  document.getElementById("l5").value = arr[4];
	  document.getElementById("l6").value = arr[5];
	  document.getElementById("l7").value = arr[6];
 
}
//----把查询到的信息放入修改的表单里----


function updateInfo(){
 if(judge()==true){
  alert("修改成功");
  insertInfo(); //插入修改后的值
  hideAddInput(); //隐藏添加模块
 }else{
  alert("修改失败");
  hideAddInput();
 }
}

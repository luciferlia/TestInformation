/**
 * 
 */


function shield5(){
    var s = document.getElementById("hid");
    s.style.display = "block";
    
    var l = document.getElementById("log_window5");
    l.style.display = "block";
}

function cancel_shield5(){
    var s = document.getElementById("hid");
    s.style.display = "none";
    
    var l = document.getElementById("log_window5");
    l.style.display = "none";
}


function cancel_shield(){
    var s = document.getElementById("hid");
    s.style.display = "none";
    
    var l = document.getElementById("log_window");
    l.style.display = "none";
}
function shield(){
    var s = document.getElementById("hid");
    s.style.display = "block";
    
    var l = document.getElementById("log_window");
    l.style.display = "block";
}

	function showDivFun() {
		
		document.getElementById("hid").style.display="block" ;
		document.getElementById("popDiv").style.display = 'block';
	}
	//关闭事件
	function closeDivFun() {
		document.getElementById("popDiv").style.display = 'none';
		 document.getElementById("hid").style.display="none" ;
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
	  document.getElementById("l8").value=""; 
	  document.getElementById("l9").value=""; 
	  document.getElementById("l10").value=""; 


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
		 var l8 = document.getElementById("l8").value;
		 var l9 = document.getElementById("l9").value; 
		 var l10 = document.getElementById("l10").value;
 var judge = true ; //用于判断表单信息是否符合
 if(l1==""||l2=="" ||l3==""||l4==""||l5==""||l6==""||l7==""||l8=="" ||l9==""||l10==""){
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
 for(var m=0 ; m<10;m++){
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
	  document.getElementById("l8").value = arr[7];
	  document.getElementById("l9").value = arr[8];
	  document.getElementById("l10").value = arr[9];
 
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





//----获取行号-----
function getRow1(r){

var i=r.parentNode.parentNode.rowIndex; 
return i ;
}



//----清除添加信息框的内容-----
function cleanAddInput1(){
	 document.getElementById("p1").value="";
	 document.getElementById("p2").value="";
	 document.getElementById("p3").value=""; 
	 document.getElementById("p4").value="";
	 document.getElementById("p5").value=""; 
	 document.getElementById("p6").value="";
	 document.getElementById("p7").value=""; 
	 document.getElementById("p8").value=""; 
}
//----清除添加信息框的内容-----

//----显示添加信息框-----
function showAddInput1(){
document.getElementById("update1").style.display="block" ;
document.getElementById("hid").style.display="block" ;
document.getElementById("btn_update1").style.display="none" ;
cleanAddInput1(); 
}
//----显示添加信息框-----

//----隐藏添加信息框-----
function hideAddInput1(){
document.getElementById("update1").style.display="none" ;
document.getElementById("hid").style.display="none" ;
}
//----隐藏添加信息框-----

//----判断输入框的信息是否符合要求-----
function judge1(){
//根据id获取表单信息
	var p1 = document.getElementById("p1").value;
	 var p2 = document.getElementById("p2").value;
	 var p3 = document.getElementById("p3").value; 
	 var p4 = document.getElementById("p4").value;
	  var p5 = document.getElementById("p5").value;
	  var p6 = document.getElementById("p6").value;
		var p7 = document.getElementById("p7").value;
		var p8 = document.getElementById("p8").value;
		
var judge = true ; //用于判断表单信息是否符合
if(p1==""||p2=="" ||p3==""||p4==""||p5==""||p6==""||p7==""){
	  judge = false ;
	  alert("请输入项目名称");
	 }
	 

return judge ;
}
//----判断输入框的信息是否符合要求-----



//----根据行号修改信息-----
function updateRow1(r){
row = getRow1(r); //把该行号赋值给全局变量
showAddInput1(); //显示修改表单
//提交按钮替换

document.getElementById("btn_update1").style.display="" ;
insertInputFromQuery1(queryInfoByRow1(row));

}
//----根据行号修改信息-----


//----根据行号查信息----
function queryInfoByRow1(r){

var arr = new Array();
for(var m=0 ; m<8;m++){
arr[m] = document.getElementById("table1").rows[row].cells[m].innerText;
}
return arr ; //返回该行数据

}
//----根据行号查信息----

//----把查询到的信息放入修改的表单里----
function insertInputFromQuery1(arr){
	document.getElementById("p1").value = arr[0];
	 document.getElementById("p2").value = arr[1];
	 document.getElementById("p3").value = arr[2];
	 document.getElementById("p4").value = arr[3];
	 document.getElementById("p5").value = arr[4];
	 document.getElementById("p6").value = arr[5];
	  document.getElementById("p7").value = arr[6];
	  document.getElementById("p8").value = arr[7];
	

}
//----把查询到的信息放入修改的表单里----


function updateInfo1(){
if(judge()==true){
alert("修改成功");
insertInfo1(); //插入修改后的值
hideAddInput1(); //隐藏添加模块
}else{
alert("修改失败");
hideAddInput1();
}
}

/**
 * 
 */
/*autodivheight();
function autodivheight(){ //函数：获取尺寸
    //获取浏览器窗口高度
    var winHeight=0;
    if (window.innerHeight)
        winHeight = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        winHeight = document.body.clientHeight;
    //通过深入Document内部对body进行检测，获取浏览器窗口高度
    if (document.documentElement && document.documentElement.clientHeight)
        winHeight = document.documentElement.clientHeight;
    //DIV高度为浏览器窗口的高度
    //document.getElementById("test").style.height= winHeight +"px";
    //DIV高度为浏览器窗口高度的一半
    document.getElementById("hid").style.height= winHeight +"px";
}
window.onresize=autodivheight; //浏览器窗口发生变化时同时变化DIV高度
*/
	function showDivFun() {
	
		 var s = document.getElementById("hid");
		    s.style.display = "block";
		    
		    var l = document.getElementById("popDiv");
		    l.style.display = "block";
	}
	//关闭事件
	function closeDivFun() {
		var s = document.getElementById("hid");
	    s.style.display = "none";
	    
	    var l = document.getElementById("popDiv");
	    l.style.display = "none";
	}
	
	function showDivFun1() {
		 var s = document.getElementById("hid");
		    s.style.display = "block";
		    var l = document.getElementById("popDiv1");
		    l.style.display = "block";
	}
	//关闭事件
	function closeDivFun1() {
	    var l = document.getElementById("popDiv1");
	    l.style.display = "none";
		var s = document.getElementById("hid");
	    s.style.display = "none";
	}
	function showDivFun2() {
		 var s = document.getElementById("hid");
		    s.style.display = "block";
		    var l = document.getElementById("popDiv2");
		    l.style.display = "block";
	}
	//关闭事件
	function closeDivFun2() {
	    var l = document.getElementById("popDiv2");
	    l.style.display = "none";
		var s = document.getElementById("hid");
	    s.style.display = "none";
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
 var judge = true ; //用于判断表单信息是否符合
  if(l2=="" ||l3==""||l4==""){
  judge = false ;
  layer.msg('不能为空',{time:1000});
 }
  
 return judge ;
}




//----根据行号修改信息-----
function updateRow(r){
 row = getRow(r); //把该行号赋值给全局变量
 showAddInput(); //显示修改表单
 //提交按钮替换

 document.getElementById("btn_update").style.display="" ;
 insertInputFromQuery(queryInfoByRow(row));
 
}



//----根据行号查信息----
function queryInfoByRow(r){
 
 var arr = new Array();
 for(var m=0 ; m<6;m++){
  arr[m] = document.getElementById("table").rows[row].cells[m].innerText;
 }
 return arr ; //返回该行数据
 
}


//----把查询到的信息放入修改的表单里----
function insertInputFromQuery(arr){
document.getElementById("l1").value = arr[0];
 document.getElementById("l2").value = arr[1];
 document.getElementById("l3").value = arr[2];
 document.getElementById("l4").value = arr[3];
  document.getElementById("l5").value = arr[4];
  document.getElementById("l6").value = arr[5];
 
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

}
//----清除添加信息框的内容-----

//----显示添加信息框-----
function showAddInput1(){
document.getElementById("update1").style.display="block" ;
document.getElementById("hid").style.display="block" ;
document.getElementById("btn_update1").style.display="none" ;
cleanAddInput(); 
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

var judge = true ; //用于判断表单信息是否符合
if(p2==""||p4==""){
judge = false ;
alert("值不能为空");
}

return judge ;
}
//----判断输入框的信息是否符合要求-----



//----根据行号修改信息-----
function updateRow1(r){
row = getRow(r); //把该行号赋值给全局变量
showAddInput1(); //显示修改表单
//提交按钮替换

document.getElementById("btn_update1").style.display="" ;
insertInputFromQuery1(queryInfoByRow1(row));

}
//----根据行号修改信息-----


//----根据行号查信息----
function queryInfoByRow1(r){

var arr = new Array();
for(var m=0 ; m<4;m++){
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



//----获取行号-----
function getRow2(r){
var i=r.parentNode.parentNode.rowIndex; 
return i ;
}



//----清除添加信息框的内容-----
function cleanAddInput2(){
document.getElementById("r1").value="";
document.getElementById("r2").value=""; 
document.getElementById("r3").value=""; 
}
//----清除添加信息框的内容-----

//----显示添加信息框-----
function showAddInput2(){
document.getElementById("update2").style.display="block" ;
document.getElementById("hid").style.display="block" ;
document.getElementById("btn_update2").style.display="none" ;
cleanAddInput(); 
}
//----显示添加信息框-----

//----隐藏添加信息框-----
function hideAddInput2(){
document.getElementById("update2").style.display="none" ;
document.getElementById("hid").style.display="none" ;
}
//----隐藏添加信息框-----

//----判断输入框的信息是否符合要求-----
function judge2(){
//根据id获取表单信息
var r1 = document.getElementById("r1").value;
var r2 = document.getElementById("r2").value; 
var r3 = document.getElementById("r3").value;
var judge = true ; //用于判断表单信息是否符合
if(r2==""){
judge = false ;
alert("值不能为空");
}

return judge ;
}
//----判断输入框的信息是否符合要求-----



//----根据行号修改信息-----
function updateRow2(r){
row = getRow(r); //把该行号赋值给全局变量
showAddInput2(); //显示修改表单
//提交按钮替换

document.getElementById("btn_update2").style.display="" ;
insertInputFromQuery2(queryInfoByRow2(row));

}
//----根据行号修改信息-----


//----根据行号查信息----
function queryInfoByRow2(r){

var arr = new Array();
for(var m=0 ; m<3;m++){
arr[m] = document.getElementById("table2").rows[row].cells[m].innerText;
}
return arr ; //返回该行数据

}
//----根据行号查信息----

//----把查询到的信息放入修改的表单里----
function insertInputFromQuery2(arr){
document.getElementById("r1").value = arr[0];
document.getElementById("r2").value = arr[1];
document.getElementById("r3").value = arr[2];
}
//----把查询到的信息放入修改的表单里----


function updateInfo2(){
if(judge()==true){
alert("修改成功");
insertInfo2(); //插入修改后的值
hideAddInput2(); //隐藏添加模块
}else{
alert("修改失败");
hideAddInput2();
}
}

/**
 * 
 */

	function loginOut(frm) {
		/* var f = confirm("确认退出吗？");
		if (f) {
			return true;
		} else {
			return false;
		} */

 layer.confirm('确定退出系统?', {icon: 3, title:'提示'}, function(index){
 layer.close(index);
logout();

});

	}
	function logout(){
		
		
		
		
		
		
	 $.ajax( {

       async : false,

       cache : false,

       type : 'POST',

       url :"Login_logout",// 请求的action路径

       success : function() { // 请求成功后处理函数。
      /*  parent.location = "index.jsp"; */
      parent.location="Login_logout";
      layer.msg("登出成功");

       },
        error : function(e) {
           layer.msg('登出失败!',{time:1000});
         }
       });
	} 
	
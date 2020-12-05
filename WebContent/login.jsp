<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登陆界面</title>
    <!--设置标签图标-->
    <link href="favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/footer.css">
</head>
<body>
<div id="header">
    <div class="h_center">

        <img src="images/log.png" height="60">
        <p>为确保您的账户安全及正常使用，依《网络安全法》规定，6月1日起会员需要绑定手机，如您还未绑定，请尽快完成，感谢您的理解和支持</p>
    </div>
</div>
<div id="login_body">
    <div class="login_b_center">
        <div class="login_bg">
            <h4>密码登录</h4>
            <div class="userName">
                <span></span>
                <input id="userName" type="text" placeholder="请输入用户名...">
            </div>
            <div class="password">
                <span></span>
                <input id="password" type="password" placeholder="请输密码...">
            </div>
            <div class="hrh">
                <input id="login" type="button" value="登录" >
            </div>
            <div class="password_forget">
                <a href="rePassword.jsp">修改密码</a>
                <a href="">忘记用户名</a>
                <a href="regist.jsp">免费注册</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
<script src="js/jquery.min.js"></script>
<script src="js/layer/layer.js"></script>
<script>
$(document).ready(function(){
	$("#login").click(function(){
		var userName = $("#userName").val();
		var password = $("#password").val();
		if(userName==""){
			layer.tips('请输入用户名', '.userName', {
				  tips: [4, '#78BA32']
				});
			return;
		}
		if(password==""){
			layer.tips('请输入密码', '.password', {
				  tips: [4, '#78BA32']
				});
			return;
		}
		$.post("LoginServlet",{
			userName:userName,
			password:password,
		},function(data){
			if(data=="OK"){
				layer.msg('登录成功', {icon: 1});
				setTimeout("userIn()",2000)
			} 
			if(data=="Error") {
				layer.msg('账号或密码，请重新输入', {icon: 2});
			}
			if(data=="admin"){
				layer.msg('登录成功,欢迎管理员', {icon: 1});
				setTimeout("adminIn()",2000)
			}
		})
	})
})
function userIn(){
	window.location.href="IndexGoodsServlet?category=1";
}
function adminIn(){
	window.location.href="admin/adminIndex.jsp";
}
</script>

</body>
</html>
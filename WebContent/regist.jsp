<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--设置标签图标-->
    <link href="favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/regist.css">
    <link rel="stylesheet" href="css/footer.css">
</head>
<body>
<!--头部-->
<div id="reg_header">
    <div class="reg_h_center">
        <div class="reg_h_left">
            <img src="images/logo.png" alt="">
            <h4>欢迎注册</h4>
        </div>

        <div class="reg_h_right">
            <a href="login.jsp">请登录</a>
            <span>已有账户</span>

        </div>
    </div>
</div>
<!--表单内容-->
<div id="reg_content">
    <div class="reg_content_left">
        <form action="#">

            <div>
                <label>用户名</label>
                <input id="userName" type="text" placeholder="请输入用户名">
            </div>
            <div>
                <label>密码</label>
                <input id="password" type="password" placeholder="请输入用户名">
            </div>
            <div>
                <label>确认密码</label>
                <input id="rePwd" type="password" placeholder="请输入用户名">
            </div>
            <div>
                <label>电话</label>
                <input id="phone" type="text" placeholder="请输入用户名">
            </div>
            <div class="check_box">
                <label>验证码</label>
                <input id="request" type="text">
                <img src="CheckingServlet" onclick="change(this)">
            </div>
            <div class="submit_button">
                <input type="button" id="regist" value="立即注册">
            </div>

        </form>
    </div>
    <div class="reg_content_right">
        <a href="#">
            <img src="images/reg_right.png" alt="">
        </a>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
<script src="js/jquery.min.js"></script>

<script>
$(document).ready(function(){
	$("#regist").click(function(){
		var userName = $("#userName").val();
		var password = $("#password").val();
		var rePwd =$("#rePwd").val();
		var phone =$("#phone").val();
		var checking = $("#request").val();
		if(userName=="" || password==""){
			alert("账号密码不能为空");
			return;
		}
		if(password!=rePwd){
			alert("两次密码输入不一致");
			return;
		}
		if(phone==""){
			alert("电话号码不能为空");
			return;
		}
		if(checking==""){
			alert("请输入验证码");
			return;
		}
		$.post("RegistServlet",{
			userName:userName,
			password:password,
			phone:phone,
			request:checking
		},function(data){
			if(data=="OK"){
				alert("注册成功");
				window.location.href="login.jsp";
			}
			if(data=="checkingError"){
				alert("验证码错误");
			}
			if(data=="Error1"){
				alert("用户已存在");
			}
		})
	})
})
function change(obj){
	obj.src = "CheckingServlet?time="+new Date().getTime();
}
</script>
</body>
</html>
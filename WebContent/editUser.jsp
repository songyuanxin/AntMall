<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:if test="${sessionScope.user==null }">
	请先登录。
</c:if>
<c:if test="${sessionScope.user!=null }">
<!--头部-->
<div id="reg_header">
    <div class="reg_h_center">
        <div class="reg_h_left">
            <img src="images/logo.png" alt="">
            <h4>修改信息</h4>
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
                <label style="color:red;">${sessionScope.user.username }</label>
                <input type="hidden" id="username" value="${sessionScope.user.username }">
            </div>
            <div>
                <label>电话</label>
                <input id="phone" type="text" value="${sessionScope.user.phone }">
            </div>
            <div class="check_box">
                <label>验证码</label>
                <input id="request" type="text">
                <img src="CheckingServlet" onclick="change(this)">
            </div>
            <div class="submit_button">
                <input type="button" id="regist" value="立即修改">
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
</c:if>
<script src="js/jquery.min.js"></script>

<script>
$(document).ready(function(){
	$("#regist").click(function(){
		var userName = $("#username").val();
		var phone =$("#phone").val();
		var checking = $("#request").val();
		if(phone==""){
			alert("电话号码不能为空");
			return;
		}
		if(checking==""){
			alert("请输入验证码");
			return;
		}
		$.post("EditUserServlet",{
			userName:userName,
			phone:phone,
			request:checking
		},function(data){
			if(data=="OK"){
				alert("修改成功");
				window.location.href="IndexGoodsServlet?";
			}
			if(data=="checkingError"){
				alert("验证码错误");
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
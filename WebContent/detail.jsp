<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Title</title>
<!--设置标签图标-->
<link href="favicon.ico" rel="shortcut icon">
<link rel="stylesheet" href="css/common.css">
<link rel="stylesheet" href="css/detail.css">

</head>
<body>
	<!--头部-->
	<jsp:include page="header.jsp"></jsp:include>
	<!--面包屑导航-->
	<div id="bread_crumb">
		<div class="bread_center">

			<a href="index.html">首页</a> <small>&gt</small> <a href="#">运动户外</a> <small>&gt</small>
			<a href="#">运动服饰</a>
		</div>
	</div>
	<!--详情展示-->
	<div id="detail">
		<!--左侧-->
		<div class="detail_img">
			<img src="images/goods/${Good.getPimage()}"
				alt="">
		</div>
		<!--右侧-->
		<div class="detail_price">
			<h3>${Good.getPname() }</h3>
			<div class="goods_price">
				<p class="ori_price">
					原价：￥${Good.getPprice() } <em></em>
				</p>
				<c:if test="${Good.getPid()>0 && Good.getPid()<6 }">
					<p>
						价格： <i class="yuan">￥</i><span class="price"><fmt:formatNumber
								type="number" pattern="0.00" value="${Good.getPprice()*0.5 } "
								maxFractionDigits="2" /></span>
					</p>
				</c:if>
				<c:if test="${Good.getPid()>6 }">
					<p>
						价格： <i class="yuan">￥</i><span class="price"><fmt:formatNumber
								type="number" pattern="0.00" value="${Good.getPprice()*0.8 } "
								maxFractionDigits="2" /></span>
					</p>
				</c:if>
			</div>
			<div class="goods_count">
				库存<label> ${Good.getStuck() }</label><br>
				<br>
				<br> 购买数量 <input type="text" id="count" value="1">
			</div>

			<div class="goods_buy mt50 ml5">
				<input id="btnJoin" type="button" value="加入购物车">
			</div>

		</div>

	</div>
	<!--尾部-->
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="js/jquery.min.js"></script>
	<script src="js/layer/layer.js"></script>
	<script>
$(document).ready(function(){
	$("#btnJoin").click(function(){
		var id = ${Good.getPid()};
		var count = $("#count").val();
		$.post("CartServlet",{
			id:id,
			count:count
		},function(data){
			if(data=="OK"){
				layer.msg('添加成功！', {icon: 1});
			}
			if(data=="Error2"){
				layer.msg('库存量不足！', {icon: 2});
			}
			if(data=="noLogin"){
				layer.msg('请先登录', {icon: 3});
				setTimeout("move()",2000);
			}
		})
	})
})
function move(){
	window.location.href='login.jsp';
}
</script>
</body>
</html>
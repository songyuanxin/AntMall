<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
	padding: 0;
	margin: 0;
}

.out {
	width: 1200px;
	margin: auto;
	border: 1px solid yellow;
}
</style>
</head>
<body>
	<!--头部-->
	<jsp:include page="header.jsp"></jsp:include>
	<div class="out">
		<table>
			<tr style="background: yellow; text-align: center;">
				<td style="width: 400px; height: 70px;">订单编号</td>
				<td style="width: 100px;">操作用户</td>
				<td style="width: 120px;">订购时间</td>
				<td style="width: 80px;">总价</td>
				<td style="width: 70px;">商品种类</td>
				<td style="width: 150px;">地址</td>
				<td style="width: 70px;">收件人</td>
				<td style="width: 130px;">电话</td>
				<td style="width: 80px;">操作</td>
			</tr>
			<c:if test="${empty orderList}">
				<tr>
					<td>当前没有订单信息</td>
				</tr>
			</c:if>
			<c:if test="${!empty orderList}">
				<c:forEach items="${orderList }" var="order">
					<tr style="height: 70px">
						<td>${order.getOrderId() }</td>
						<td>${order.getUsername() }</td>
						<td>${order.getOrderTime() }</td>
						<td>${order.getTotalPrice() }</td>
						<td>${order.getProductNum() }</td>
						<td>${order.getAddress() }</td>
						<td>${order.getReciever() }</td>
						<td>${order.getPhone() }</td>
						<td>
							<input type="button" value="修改" onclick="edit('${order.getOrderId() }')"> 
							<input type="button" value="删除" onclick="del('${order.getOrderId() }')">
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="js/jquery.min.js"></script>
	<script src="js/layer/layer.js"></script>
<script>
	
function edit(orderId){
	window.location.href="GetOrderToEditServlet?orderId="+orderId;
}

function del(orderId){
	$.post("DeleteOrderServlet",{
		orderId:orderId
	},function(data){
		if(data=="OK"){
			layer.msg('删除成功', {icon: 6});
			setTimeout("move()",2000);
		}
		if(data=="Error"){
			layer.msg('删除失败,商品可能已经被删除', {icon: 5});
		}
	})
}
function move(){
	window.location.href="OrderServlet";
}
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	width:1200px;
	margin:auto;
	border: 1px solid yellow;
}

.name {
	width:600px;
	font-size: 24px;
	color: gray;
}

.price {
	width:180px;
	font-size: 20px;
	color: red;
	text-align:center;
}

.count {
	width: 30px;
	text-align: center;
}
.countOut{
	width:150px;
	text-align:center;
}
.work{
	width:50px;
}
.img{
	width:233px;
	height:263px;
	text-align:center;
}
#goodImage,#goodName,#goodPrice,#goodCount,#goodWork{
	text-align:center;
	color:black;
	height:10px;
	background:yellow;
}
.submit{
	margin:auto;
	width:1200px;
	height:100px;
	line-height:100px;
	text-align:right;
}
#money{
	color:red;
	font-size:60px;
	margin-right:40px;
	display:inline-block;
}
.btn{
	width:100px;
	height:50px;
	background:red;
	color:white;
	font-size:30px;
	border:1px solid white;
}
</style>
</head>
<body>
<!--头部-->
<jsp:include page="header.jsp"></jsp:include>
	<div class="out">
		<table>
			<tr>
				<td class="img" id="goodImage"></td>
				<td class="name" id="goodName">商品名</td>
				<td class="price" id="goodPrice">价格</td>
				<td class="countOut" id="goodCount">数量</td>
				<td class="work" id="goodWork">操作</td>
			</tr>
			<c:if test="${mallList==null }">
				<tr>
					<td colspan="5">购物车中没有商品，请<a href="IndexGoodsServlet?category=1">添加</a></td>
				</tr>	
			</c:if>
			<c:if test="${mallList!=null }">
				<c:forEach items="${mallList.keySet() }" var="mall">
					<tr>
						<td class="img"><img style="width:233px;" src='images/goods/${mall.getPimage() }'
							alt=''></td>
						<td class="name">${mall.getPname() }</td>
						<td name="unitPrice" class="price">
						<fmt:formatNumber type="number"
								pattern="0.00" value="${mall.getPprice()*0.8 } "
								maxFractionDigits="2" /></td>
						<td name="quantity" class="countOut">${mallList.get(mall) }</td>
						<td><input type="button" value="删除" onclick="deleteGood(${mall.getPid()})"></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
	<div class="submit">
		<p id="money">总价:00.00</p>
		<input type="button" class="btn" value="结算" onclick="setINF()">
	</div>
<jsp:include page="footer.jsp"></jsp:include>
	<script src="js/jquery.min.js"></script>
	<script src="js/layer/layer.js"></script>
<script>
	var goods = document.getElementsByName("unitPrice");
	var quantity = document.getElementsByName("quantity")
	var all = 0;
	for(var i=0;i<goods.length;i++){
		all = all + (goods[i].innerHTML*quantity[i].innerHTML);
	}
	document.getElementById("money").innerHTML = "总价："+all;
	function setINF(){
		if(all==0){
			layer.msg('购物车中没有商品!', {icon: 2});
			return;
		}
		window.location = "InformationServlet?totalPrice="+all;
	}
	function deleteGood(id){
		layer.confirm('确认要将此产品移除购物车？', {
  		  btn: ['确认','取消'] //按钮
  		}, function(){
  			layer.msg('删除成功', {icon: 1});
  			window.location = "DeleteGoodServlet?id="+id;
  		}, function(){
  	
  		  });
	}
</script>
</body>
</html>
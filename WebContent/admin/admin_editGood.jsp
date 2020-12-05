<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="AdminEditGoodServlet" method="post" enctype="multipart/form-data">
		<table border="1" cellspacing="0" cellpadding="2">
			<tr>
				<td>商品名：</td>
				<td><input name="name" type="text" value="${Product.getPname() }"></td>
			</tr>
			<tr>
				<td>商品价格：</td>
				<td><input name="price" type="text" value="${Product.getPprice() }"></td>
			</tr>
			<tr>
				<td>商品库存：</td>
				<td><input name="stuck" type="text" value="${Product.getStuck() }"></td>
			</tr>
			<tr>
				<td>类别：</td>
				<td>
				<select name="category" id="category">
					<c:forEach items="${categoryList }" var="category">
						<option value="${category.getCategory() }">${category.getCategoryName() }</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td>供应商：</td>
				<td>
				<select name="provider" id="provider">
					<c:forEach items="${providerList }" var="provider">
						<option value="${provider.getProviderId() }">${provider.getProviderName() }</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td>当前图片：</td>
				<td>${Product.getPimage() }&nbsp;&nbsp;<input type="button" value="修改" onclick="setVisible()"></td>
			</tr>
			<tr id="file" style="display:none;">
				<td><input type="file" name="picture"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;"><input type="submit" value="提交"></td>
			</tr>
		</table>
		<input type="hidden" value="${Product.getPid() }" name="pid">
	</form>
	<input type="hidden" id="index" value="${Product.getCategory()-1}">
	<input type="hidden" id="index2" value="${Product.getProvider()-1}">
	<script>
		function setVisible(){
			document.getElementById("file").style.display="block";
		}
		var index = document.getElementById("index").value;
		var index2 = document.getElementById("index2").value;
		document.getElementById("category").selectedIndex = index;
		document.getElementById("provider").selectedIndex = index2;
	</script>
</body>
</html>
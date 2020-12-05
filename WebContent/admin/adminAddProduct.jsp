<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加商品</title>
</head>
<body>
	<form action="AdminAddGoodServlet" method="post" enctype="multipart/form-data">
		<table border="1" cellspacing="0" cellpadding="2">
			<tr>
				<td>商品名：</td>
				<td><input name="name" type="text"></td>
			</tr>
			<tr>
				<td>商品价格：</td>
				<td><input name="price" type="text"></td>
			</tr>
			<tr>
				<td>商品库存：</td>
				<td><input name="stuck" type="text"></td>
			</tr>
			<tr>
				<td>类别：</td>
				<td>
				<select name="category">
					<c:forEach items="${categoryList }" var="category">
						<option value="${category.getCategory() }">${category.getCategoryName() }</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td>供应商：</td>
				<td>
				<select name="provider">
					<c:forEach items="${providerList }" var="provider">
						<option value="${provider.getProviderId() }">${provider.getProviderName() }</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td>图片：</td>
				<td><input type="file" name="picture"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center;"><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>
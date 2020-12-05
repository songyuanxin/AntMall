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
	<table style="width:1000px;text-align:center;margin:auto;border:1px solid black;">
		<tr style="text-align:center;height:50px">
			<td style="width:200px">类别编号</td>
			<td style="width:200px">类别名称</td>
			<td style="width:200px">操作</td>
		</tr>
		<c:forEach items="${categoryList }" var="category">
		<tr>
			<td style="text-align:center;height:50px;">${category.category }</td>
			<td style="text-align:center;height:50px;">${category.categoryName }</td>
			<c:if test="${category.category<=6 }">
			<td style="text-align:center;height:50px;">默认分类，不可操作</td>
			</c:if>
			<c:if test="${category.category>6 }">
			<td style="text-align:center;height:50px;"><input type="button" value="修改" onclick="edit(${category.category })">&nbsp;&nbsp;&nbsp;<input type="button" value="删除" id="delete" onclick="del(${category.category })"></td>
			</c:if>
		</tr>
		</c:forEach>
		<tr style="text-align:center;">
			<td colspan="3"><a href="admin_addCategory.jsp">添加</a>&nbsp;&nbsp;&nbsp;<a href="adminIndex.jsp">返回</a></td>
		</tr>
	</table>
<script src="../js/jquery.min.js"></script>
<script src="../js/layer/layer.js"></script>
<script type="text/javascript">
	function edit(categoryId){
		window.location.href="AdminUpdateCategoryServlet?categoryId="+categoryId;
	}
	function del(categoryId){
		$.post("AdminDeleteCategoryServlet",{
			categoryId:categoryId
		},function(data){
			if(data="OK"){
				layer.msg("删除成功");
				setTimeout("move()",2000);
			} else {
				layer.msg("删除失败");
			}
		})
	}
	function move(){
		window.location.href="AdminCategoryServlet2";
	}
</script>
</body>
</html>
<%@page import="java.util.List"%>
<%@page import="cn.edu.svtcc.domain.Product"%>
<%@page import="cn.edu.svtcc.dao.UserDAOimp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>商品列表</title>
<!--设置标签图标-->
<link href="../favicon.ico" rel="/shortcut icon">
<link rel="stylesheet" href="../css/common.css">
<link rel="stylesheet"
	href="../css/goods_list.css">
<link rel="stylesheet" href="../css/pageStyle.css">
<link rel="stylesheet" href="../css/footer.css">
<style>
table {
	margin: auto;
	line-height: 50px;
}

.btn {
	width: 40px;
	height: 30px;
}

.search {
	width: 400px;
	text-align: center;
	margin: auto;
}

#search {
	width: 40px;
	height: 25px;
	border-radius: 5px;
	border: 1px solid black;
}

.page {
	width: 400px;
	text-align: center;
	margin: auto;
}

#back {
	position: relative;
	left: 350px;
}
</style>
</head>
<body>
	<c:if test="${sessionScope.admin!=null }">
		<table>
			<tr>
				<th style="width: 500px; font-size: 30px;">商品名</th>
				<th style="width: 100px; font-size: 30px;">库存</th>
				<th style="width: 100px; font-size: 30px;">价格</th>
				<th style="width: 100px; font-size: 30px;">类别</th>
				<th style="width: 100px; font-size: 30px;">供应商</th>
				<th style="width: 100px; font-size: 30px;">操作</th>
			</tr>
			<c:forEach items="${Goods }" var="good">
				<tr>
					<td>${good.getPname() }</td>
					<td style="text-align: center;">${good.getStuck() }</td>
					<td style="text-align: center;">${good.getPprice() }</td>
					<td style="text-align: center;">${good.getCategoryName() }</td>
					<td style="text-align: center;">${good.getProviderName() }</td>
					<td style="text-align: center;"><input type="button"
						class="btn" value="编辑" id="edit"
						onClick="editGood('${good.getPid()}')">&nbsp;&nbsp; <input
						type="button" class="btn" value="删除" id="delete"
						onClick="deleteGood('${good.getPid()}')"></td>
				</tr>
			</c:forEach>
		</table>
		<div class="page">
			<c:if test="${pageIndex>1 }">
				<a href="AdminGoodsListServlet?pageIndex=${pageIndex-1}">上一页</a>
			</c:if>
			当前是第${pageIndex }页
			<c:if test="${pageIndex<pageNum+1 }">
				<a href="AdminGoodsListServlet?pageIndex=${pageIndex+1}">下一页</a>
			</c:if>
			<a id="back" href="adminIndex.jsp">返回</a>
		</div>
	</c:if>


	<jsp:include page="../footer.jsp"></jsp:include>



	<script src="../js/jquery.min.js"></script>
	<script src="../js/layer/layer.js"></script>
	<script>
    function move(){
    	window.location.href="AdminGoodsListServlet";
    }
    function editGood(id){
    	window.location.href="AdminGetGoodToEditServlet?id="+id;
    }
    function deleteGood(id){
    	layer.confirm('确认要删除该产品？', {
    		  btn: ['删除','取消'] //按钮
    		}, function(){
    			$.post("AdminDeleteGoodServlet",{
    				id:id
    			},function(data){
    				if(data=="ok"){
    					layer.msg('删除成功', {icon: 1});
    					setTimeout("move()",2000);
    				}
    				if(data=="error"){
    					layer.msg('删除失败,文件可能已被删除', {icon: 2});
    				}
    			})
    		  
    		}, function(){
    	
    		  });
    	
    }
	</script>
</body>
</html>
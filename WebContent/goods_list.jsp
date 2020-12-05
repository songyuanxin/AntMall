<%@page import="java.util.List"%>
<%@page import="cn.edu.svtcc.domain.Product"%>
<%@page import="cn.edu.svtcc.dao.UserDAOimp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--设置标签图标-->
    <link href="favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/goods_list.css">
    <link rel="stylesheet" href="css/pageStyle.css">
    <link rel="stylesheet" href="css/footer.css">
    <style>
    	p{
    		overflow: hidden;
        	text-overflow: ellipsis;
        	display: -webkit-box;
        	-webkit-line-clamp:2;
        	-webkit-box-orient: vertical;
    	}
    	.page{
		width:400px;
		text-align:center;
		margin:auto;
	}
    </style>
</head>
<body>
<!--头部-->
<jsp:include page="header.jsp"></jsp:include>
<!--热卖-->
<div id="hot_goods">
    <h3>热卖商品</h3>
    <div class="hot_goods_body">
        <ul>
        	<c:forEach items="${Goods }" var="good">
        		<li>
        			<a href="DetailServlet?id=${good.getPid()}">
        				<img src='images/goods/${good.getPimage() }' alt=''>
        				<p>${good.getPname()}</p>
        				<i class="yuan">￥</i><span class="price"><fmt:formatNumber
								type="number" pattern="0.00" value="${good.getPprice()*0.8 } "
								maxFractionDigits="2" /></span>
        			</a>
        		</li>
        	</c:forEach>
        </ul>
    </div>
		<div class="page">
					<c:if test="${GoodsPageIndex>1 }">
						<a href="GoodsListServlet?category=${category }&pageIndex=${GoodsPageIndex-1}">上一页</a>
					</c:if>
					当前是第${GoodsPageIndex }页
					<c:if test="${GoodsPageIndex<pageNum+1 }">
						<a href="GoodsListServlet?category=${category }&pageIndex=${GoodsPageIndex+1}">下一页</a>
					</c:if>
					
				</div>
</div>


<jsp:include page="footer.jsp"></jsp:include>



<script src="js/jquery.min.js"></script>
</body>
</html>

<!--
1.把css引入到项目 当中
2.把分页的代码写到对应的位置  <div id="page" class="page_div"></div>
3.引入js   在尾部引入js

<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/paging.js"></script>
<script>
    //分页
    $("#page").paging({
        pageNo:3,  /*当前选中的是哪一页*/
        totalPage: 15, /*共多少页*/
        totalSize: 300,/*共多少条记录*/
        callback: function(num) {
           console.log(num);
        }
    })
</script>

-->
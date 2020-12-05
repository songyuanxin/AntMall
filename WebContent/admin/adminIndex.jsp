<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员页面</title>
<style>
        *{
            padding:0;
            margin:0;
        }
        body{
            background:#e4f3f8;
        }
        .out{
            width:1000px;
            padding-top:200px;
            padding-left: 150px;
            background-image: url(images/index1.jpg);
            margin:auto;
        }
        button{
            float:left;
            margin:30px;
        }
        .addProduct,.showProduct,.exit{
            width:150px;
            height:50px;
            border:1px solid white;
            border-radius:10px;
            text-align:center;
            background:#c0e2ed;
            font-size:20px;
            font-family:"幼圆";
            background:rgb(233, 245, 255);
        }
        .addProduct:hover,.showProduct:hover,.exit:hover{
            background:rgb(203, 225, 253);
            cursor: pointer;
        }
        #category,#provider{
            background:rgb(233, 245, 255);
            font-size:20px;
            border-radius:10px;
            font-family:"幼圆";
            width:150px;
            height:50px;
            border:1px solid white;
        }
        #category:hover,#provider:hover{
            background:rgb(203, 225, 253);
            cursor: pointer;
        }
    </style>
</head>
<body>
	<c:if test="${sessionScope.admin!=null }">
		<div class="out">
		<button class="addProduct" id="add">添加商品</button>
		<button class="showProduct" id="show">查看商品</button>
		<button class="category" id="category">类别管理</button>
		<button class="provider" id="provider">供应商管理</button>
		<button class="exit" id="exit">退出</button>
	</div>
	</c:if>
	
	<script src="../js/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#add").click(function(){
				window.location.href="AdminCategoryServlet";
			})
			$("#show").click(function(){
				window.location.href="AdminGoodsListServlet";
			})
			$("#exit").click(function(){
				window.location.href="/Shoping/ExitServlet";
			})
			$("#category").click(function(){
				window.location.href="AdminCategoryServlet2";
			})
			$("#provider").click(function(){
				window.location.href="AdminProviderServlet";
			})
		})
	</script>
</body>
</html>